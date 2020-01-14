package github.com.bobgit.study.pinyin.controller;

import github.com.bobgit.study.pinyin.common.CommonResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApiModel(value = "用户请求接口")
@RestController
//@Controller
@RequestMapping("/word")
@Slf4j
public class WordController {


    @ApiOperation(value = "test")
    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public CommonResponse<String> aesTest(HttpServletRequest request, HttpServletResponse response) {
        CommonResponse<String> res = new CommonResponse<String>();
        res.setData("0 Encrypt 加密，1 解密");
        int other = 122/0;
        return res;
    }

    @ApiOperation(value = "ResponseEntity的优先级高于@ResponseBody 能够为不同API设置不同的返回响应头")
    @RequestMapping(value = "/search", method = {RequestMethod.GET,RequestMethod.POST} , produces = {MediaType.APPLICATION_JSON_VALUE})//,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }
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
