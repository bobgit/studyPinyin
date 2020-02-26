package github.com.bobgit.study.pinyin.controller;

import com.github.liuzhuoming23.pinyin4j.builder.HanyuPinyinOutputFormatBuilder;
import com.github.liuzhuoming23.pinyin4j.helper.HanyuPinyinHelper;
import com.google.common.collect.Lists;
import github.com.bobgit.study.pinyin.common.CommonResponse;
import github.com.bobgit.study.pinyin.common.ListResponse;
import github.com.bobgit.study.pinyin.datasource.DynamicDataSource;
import github.com.bobgit.study.pinyin.model.Word;
import github.com.bobgit.study.pinyin.model.WordWithBLOBs;
import github.com.bobgit.study.pinyin.po.ShowWord;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import github.com.bobgit.study.pinyin.service.WordService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ApiModel(value = "用户请求接口")
@RestController
//@Controller
@RequestMapping("/word")
@Slf4j
public class WordController {
    @Autowired
    private HanyuPinyinOutputFormatBuilder hanyuPinyinOutputFormatBuilder;
    @Autowired
    private WordService wordService;


    @ApiOperation(value = "晨皓要求 查询押韵的接口，只用输入韵母就可以获取一堆字")
    @RequestMapping(value = "/query", method = {RequestMethod.POST})
    public ListResponse<ShowWord> query(HttpServletRequest request, HttpServletResponse response,@ApiParam(value = "请求接口") @RequestBody WordRequest params) {
        ListResponse<ShowWord> res = new ListResponse<>();
        List<ShowWord> list =  wordService.query(params);
        res.setData(list);
        res.setTotal(list.size());
        return res;
    }

    @ApiOperation(value = "retainAll")
    @RequestMapping(value = "/retainAll", method = {RequestMethod.POST})
    public ListResponse<WordWithBLOBs> retainAll(HttpServletRequest request, HttpServletResponse response, @ApiParam(value = "请求接口") @RequestBody WordRequest params, @ApiParam(value = "数据源 ONE TWO THREE")@RequestParam(value = "type", required = false, defaultValue = "ONE") String type) {
        DynamicDataSource.setDataSource(type);


//  固定长度  从源码的注释中可以看到，调用asList方法返回的是一个固定大小的集合，所以再往里添加元素必然会报错
//        List<String> list1 =  Arrays.asList("无","高血压","糖尿病","肾病","脑卒中","心脏类疾病","免疫系统疾病","过敏","其他");//  Arrays.asList(pinyin.toCharArray());
//        List<String> list2 = Arrays.asList("无1","高血压","糖尿病3","肾病","脑卒中5","心脏类疾病","免疫系统疾病7","过敏","其他9");

        List<String> list1 = Lists.newArrayList(Arrays.asList("无","高血压","糖尿病","肾病","脑卒中","心脏类疾病","免疫系统疾病","过敏","其他")) ;//  Arrays.asList(pinyin.toCharArray());
        List<String> list2 = Lists.newArrayList(Arrays.asList("无1","高血压","糖尿病3","肾病","脑卒中5","心脏类疾病","免疫系统疾病7","过敏","其他9"));


        //交集
        List<String> collect1 = list1.stream().filter(num -> list2.contains(num)).collect(Collectors.toList());
        log.info("拼音的交集是:{}",collect1);
        collect1.stream().forEach(System.out::println);

        //差集 list1-list2
        List<String> collect2 = list1.stream().filter(num -> !list2.contains(num)).collect(Collectors.toList());
        log.info("拼音的差集:{}",collect2);
//        collect2.stream().forEach(log::info);


        //并集  不去重
        log.info("list1:{}  list1=!=null:{}",list1,list1==null);
        list1.addAll(list2);
        log.info("并集  不去重:{}",list1);
        //并集  去重
        List<String> collect4 = list1.stream().distinct().collect(Collectors.toList());
        log.info("并集  去重:{}",collect4);


        ListResponse<WordWithBLOBs> res = new ListResponse<>();
params.setStart(0);
params.setSize(10);
log.info("输入参数params:{}",params);

        String shengmuDouble = "zh ch sh";
        String[] shengmuArrayDouble = shengmuDouble.split(" ");
        List<String> shengmuListDouble = Lists.newArrayList(shengmuArrayDouble);
        String shengmu = "b p m f d t n l g k h j q x r z c s y w";
        String[] shengmuArray = shengmu.split(" ");
        List<String> shengmuList = Lists.newArrayList(shengmuArray);
        log.info("声母 数量:{} 结果:{}",shengmuList.size(),shengmuList);
        String aeiou = "āáǎàaēéěèeīíǐìiōóǒòoūúǔùuǖǘǚǜü";
        String sone1 = "āēīōūǖ";
        String sone2 = "áéíóúǘ";
        String sone3 = "ǎěǐǒǔǚ";
        String sone4 = "àèìòùǜ";
        List<String> sone1List = stringToListString(sone1);
        List<String> sone2List = stringToListString(sone2);
        List<String> sone3List = stringToListString(sone3);
        List<String> sone4List = stringToListString(sone4);

        int listCount = wordService.listWordCount(new WordRequest());
        log.info("总数有--------------->：{}",listCount);


        List<WordWithBLOBs> list = wordService.listWord(params);
//        log.info("list:{}",list);
        res.setData(list);
        list.forEach(t->{
            log.info("初始情况:{}",t);
            String pinyin = t.getPinyin();
            log.info("pinyin:{}",pinyin);
            List<String> pinyinList = stringToListString(pinyin);

            List<String> collectsoneShengMuDoubleList = shengmuListDouble.stream().filter(num->pinyin.contains(num)).collect(Collectors.toList());
            log.info("双声母列表collectsoneShengMuDoubleList:{}",collectsoneShengMuDoubleList);
            if(collectsoneShengMuDoubleList.size()>0){
                t.setSyllable(collectsoneShengMuDoubleList.get(0));
            }
            else{
                List<String> collectsoneShengMuList = pinyinList.stream().filter(num -> shengmuList.contains(num)).collect(Collectors.toList());
                log.info("声母列表collectsoneShengMuList:{}",collectsoneShengMuList);
                if(collectsoneShengMuList.size()>0){
                    t.setSyllable(collectsoneShengMuList.get(0));
                }
            }

            List<String> collectsone1List = pinyinList.stream().filter(num -> sone1List.contains(num)).collect(Collectors.toList());
            log.info("音调是1声的:{} 拼音列表：{} 音调列表：{}",collectsone1List,pinyinList,sone1List);
            if(collectsone1List.size()>0){
                log.info("-----------------：{}",collectsone1List);
                t.setTone("1");
            }
            else{
                List<String> collectsone2List = pinyinList.stream().filter(num -> sone2List.contains(num)).collect(Collectors.toList());
                log.info("音调是2声的:{} 拼音列表：{} 音调列表：{}",collectsone2List,pinyinList,sone2List);
                if(collectsone2List.size()>0){
                    log.info("=============：{}",collectsone2List);
                    t.setTone("2");
                }
                else{
                    List<String> collectsone3List = pinyinList.stream().filter(num -> sone3List.contains(num)).collect(Collectors.toList());
                    log.info("音调是3声的:{} 拼音列表：{} 音调列表：{}",collectsone3List,pinyinList,sone3List);
                    if(collectsone3List.size()>0){
                        log.info("##################：{}",collectsone3List);
                        t.setTone("3");
                    }
                    else{
                        List<String> collectsone4List = pinyinList.stream().filter(num -> sone4List.contains(num)).collect(Collectors.toList());
                        log.info("音调是4声的:{} 拼音列表：{} 音调列表：{}",collectsone4List,pinyinList,sone4List);
                        if(collectsone4List.size()>0){
                            log.info("4444444444444444444：{}",collectsone4List);
                            t.setTone("4");
                        }
                        else{
                            log.info("竟然是  轻声轻声轻声轻声轻声轻声轻声轻声轻声轻声");
                            t.setTone("0");
                        }
                    }
                }
            }
            t.setYin(pingyinRemove(pinyin));
            t.setHeadlVowel(StringUtils.replace(t.getYin(),t.getSyllable(),""));
            t.setTailVowel(doubleVowel(t.getHeadlVowel()));
            log.info("目前情况:{}",t);

        });
        return res;
    }

    public String doubleVowel(String doubleVowel){
        String doubleVowelStr = "ia ian iang iao iou iong ua uai uei uan uo uang ueng üan ng";
        String[] doubleVowelArray = doubleVowelStr.split(" ");
        List<String> doubleVoweList = Lists.newArrayList(doubleVowelArray);
        final String doubleVowelFinal = doubleVowel;
        List<String> withDoubleVoweList = doubleVoweList.stream().filter(num -> doubleVowelFinal.contains(num)).collect(Collectors.toList());
        if(withDoubleVoweList.size()>0){
            if(StringUtils.equalsIgnoreCase(doubleVowel,"ng")){

            }
            else{
                doubleVowel = doubleVowel.substring(1);
            }
        }
        return doubleVowel;
    }

    public String pingyinRemove(String pinyinwithSone){
        List<String> pinyinList = stringToListString(pinyinwithSone);
        String vowela = "āáǎà";
        String vowele = "ēéěè";
        String voweli = "īíǐì";
        String vowelo = "ōóǒò";
        String vowelu = "ūúǔùǖǘǚǜü";
        List<String> vowelaList = stringToListString(vowela);
        List<String> voweleList = stringToListString(vowele);
        List<String> voweliList = stringToListString(voweli);
        List<String> voweloList = stringToListString(vowelo);
        List<String> voweluList = stringToListString(vowelu);
        String replaceString = "";
        String replaceWith = "";
        List<String> collectsoneaList = pinyinList.stream().filter(num -> vowelaList.contains(num)).collect(Collectors.toList());
        if(collectsoneaList.size()>0){
            log.info("-----------------：{}",collectsoneaList);
            replaceString = collectsoneaList.get(0);
            replaceWith = "a";
        }
        else{
            List<String> collectsoneeList = pinyinList.stream().filter(num -> voweleList.contains(num)).collect(Collectors.toList());
            if(collectsoneeList.size()>0){
                log.info("-----------------：{}",collectsoneeList);
                replaceString = collectsoneeList.get(0);
                replaceWith = "e";
            }
            else{
                List<String> collectsoneiList = pinyinList.stream().filter(num -> voweliList.contains(num)).collect(Collectors.toList());
                if(collectsoneiList.size()>0){
                    log.info("-----------------：{}",collectsoneiList);
                    replaceString = collectsoneiList.get(0);
                    replaceWith = "i";
                }
                else{
                    List<String> collectsoneoList = pinyinList.stream().filter(num -> voweloList.contains(num)).collect(Collectors.toList());
                    if(collectsoneoList.size()>0){
                        log.info("-----------------：{}",collectsoneoList);
                        replaceString = collectsoneoList.get(0);
                        replaceWith = "o";
                    }
                    else{
                        List<String> collectsoneuList = pinyinList.stream().filter(num -> voweluList.contains(num)).collect(Collectors.toList());
                        if(collectsoneuList.size()>0){
                            log.info("-----------------：{}",collectsoneuList);
                            replaceString = collectsoneuList.get(0);
                            replaceWith = "u";
                        }
                        else{

                        }
                    }
                }
            }
        }

        return pinyinwithSone.replace(replaceString,replaceWith);
    }

    public List<String> stringToListString(String str){
        List<String> charListString = Lists.newArrayList();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            charListString.add(""+charArray[i]);
        }
        log.info("数组情况charListString:{}",charListString);
        return charListString;
    }

    @ApiOperation(value = "dowithAllDB")
    @RequestMapping(value = "/dowithAllDB", method = {RequestMethod.GET})
    public CommonResponse<String> dowithAllDB(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "file", required = false, defaultValue = "社会主义核心价值观") String file) {
        CommonResponse<String> res = new CommonResponse<String>();
        wordService.dowithAllDB();
        return res;
    }

    @ApiOperation(value = "pinyin")
    @RequestMapping(value = "/pinyin", method = {RequestMethod.GET})
    public CommonResponse<String> pinyin(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "file", required = false, defaultValue = "社会主义核心价值观") String file) {
        CommonResponse<String> res = new CommonResponse<String>();
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = hanyuPinyinOutputFormatBuilder
                .toneType(HanyuPinyinToneType.WITH_TONE_MARK)
                .vCharType(HanyuPinyinVCharType.WITH_U_UNICODE)
                .build();
        String pinyin = null;
        try {
            pinyin = HanyuPinyinHelper.toHanYuPinyinString(file, hanyuPinyinOutputFormat, " ", true);
        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
            badHanyuPinyinOutputFormatCombination.printStackTrace();
        }
        String aeiou = "āáǎàaēéěèeīíǐìiōóǒòoūúǔùuǖǘǚǜü";
        String sone1 = "āēīōūǖ";
        String sone2 = "áéíóúǘ";
        String sone3 = "ǎěǐǒǔǚ";
        String sone4 = "àèìòùǜ";
        res.setData(pinyin);
        return res;
    }


    @ApiOperation(value = "test")
    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public CommonResponse<String> aesTest(HttpServletRequest request, HttpServletResponse response) {
        CommonResponse<String> res = new CommonResponse<String>();
        res.setData("0 Encrypt 加密，1 解密");
        int other = 122/0;
        return res;
    }

    @ApiOperation(value = "ResponseEntity的优先级高于@ResponseBody 能够为不同API设置不同的返回响应头")
    @RequestMapping(value = "/search", method = {RequestMethod.GET,RequestMethod.POST} , produces = {MediaType.APPLICATION_JSON_VALUE})//,consumes = { MediaType.MuLTIPART_FORM_DATA_VALuE }
    public ResponseEntity<CommonResponse<String>> search(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "file", required = false, defaultValue = "1") String file) {
        log.info("输入信息为:{}",file);
        CommonResponse<String> res = new CommonResponse<String>();
        res.setData("由状态码、头部信息以及响应体内容三大块组成");
        Integer resInt = Integer.parseInt(file);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers", "AMP-Access-Control-Allow-Source-Origin");
        headers.add("bob", "bobHeaderValue");
        ResponseEntity<CommonResponse<String>> result = new ResponseEntity<CommonResponse<String>>(res,headers,HttpStatus.BAD_REQUEST);
        log.info("result:{}",result);
        result = ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
        return result;
    }
}
