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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@ApiModel(value = "用户请求接口")
//@RestController
@RequestMapping("/word")
@Slf4j
public class WordController {



    @ApiOperation(value = "ResponseEntity的优先级高于@ResponseBody 能够为不同API设置不同的返回响应头")
    @RequestMapping(value = "/search", method = RequestMethod.POST,consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } , produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CommonResponse<String>> search(@ApiParam(value = "用户信息") @RequestParam(value = "file")MultipartFile file) {
        log.info("user:{}",file);
        CommonResponse<String> res = new CommonResponse<String>();
        res.setData("由状态码、头部信息以及响应体内容三大块组成");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers", "AMP-Access-Control-Allow-Source-Origin");
        headers.add("bob", "bobHeaderValue");
        ResponseEntity<CommonResponse<String>> result = new ResponseEntity<CommonResponse<String>>(res,headers,HttpStatus.BAD_REQUEST);
        log.info("result:{}",result);
        result = ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
        return result;
    }
}
