package github.com.bobgit.study.pinyin.service;

import com.google.common.collect.Lists;
import github.com.bobgit.study.pinyin.dao.WordMapper;
import github.com.bobgit.study.pinyin.model.*;
import github.com.bobgit.study.pinyin.po.ShowWord;
import github.com.bobgit.study.pinyin.requestParam.WordRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordService {
    private final Logger log = LoggerFactory.getLogger(WordService.class);
    @Autowired
    private WordMapper wordMapper;

    @Autowired
    private WordCiService wordCiService;
    @Autowired
    private WordIdiomService wordIdiomService;
    @Autowired
    private WordXhyService wordXhyService;


    public List<ShowWord> query(WordRequest params){
        List<WordWithBLOBs> listWord = this.listWord(params);
        List<ShowWord> showWordList = Lists.newArrayList();
        StringBuilder sb = new StringBuilder();
        listWord.forEach(w->{
            String word = w.getWord();
            WordRequest request = new WordRequest();
            request.setWord("%"+word);

            List<String> wordCi = Lists.newArrayList();
            List<String> wordIdiom = Lists.newArrayList();
            List<String> wordXhy = Lists.newArrayList();
            for (WordCi item: wordCiService.listWordCi(request)) {
                wordCi.add(item.getCi());
            }
            for (WordIdiom item: wordIdiomService.listWordIdiom(request)) {
                wordIdiom.add(item.getWord());
            }
            request.setOldword("%"+word);
            for (WordXhy item: wordXhyService.listWordXhy(request)) {
                wordXhy.add(item.getRiddle()+"-"+item.getAnswer());
            }                   ;
            if(wordCi.size()>0 || wordIdiom.size()>0 || wordXhy.size()>0){
                showWordList.add(new ShowWord(w).setWordCi(wordCi).setWordIdiom(wordIdiom).setWordXhy(wordXhy));
                sb.append(w.getWord());
            }
        });
        return showWordList;
    }

    @Transactional
    public void dowithAllDB(){

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


        int listCount = this.listWordCount(new WordRequest());
        int totalCount = listCount/1000+1;
        log.info("总数有--------------->：{}  totalCount:{}",listCount,totalCount);
        
        WordRequest wordRequest = new WordRequest();
        for(int count=0;count<totalCount;count++){
            wordRequest.setStart(count*1000);
            wordRequest.setSize(1000);
            log.info("request:{}",wordRequest);
            List<WordWithBLOBs> list = this.listWord(wordRequest);
            list.forEach(t->{
                log.info("初始情况:{}",t);
                boolean updateRecord = true;
                if(StringUtils.isNotBlank(t.getYin()))updateRecord = false;;
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

                if(updateRecord) this.updateByPrimaryKeySelective(t);

            });
        }
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





    // 根据条件查询列表
    public List<WordWithBLOBs> listWord(WordRequest params){
        return wordMapper.listWord(params);
    }
    // 根据条件查询列表 总数
    public int listWordCount(WordRequest params){
        return wordMapper.listWordCount(params);
    }

    public int deleteByPrimaryKey(Integer id){
        return wordMapper.deleteByPrimaryKey(id);
    }

    public int insertSelective(WordWithBLOBs record){
        return wordMapper.insertSelective(record);
    }

    public WordWithBLOBs selectByPrimaryKey(Integer id){
        return wordMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(WordWithBLOBs record){
        return wordMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKeyWithBLOBs(WordWithBLOBs record){
        return wordMapper.updateByPrimaryKeyWithBLOBs(record);
    }
}
