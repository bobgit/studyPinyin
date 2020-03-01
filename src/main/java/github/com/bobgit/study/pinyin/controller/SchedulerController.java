package github.com.bobgit.study.pinyin.controller;

import github.com.bobgit.study.pinyin.common.CommonResponse;
import github.com.bobgit.study.pinyin.quartz.task.QuartzManager;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ApiModel(value = "用户请求接口")
@RestController
@RequestMapping("/scheduler")
public class SchedulerController{
    @Autowired
    private QuartzManager quartzManager;


    @ApiOperation(value = "start")
    @RequestMapping(value = "/start", method = {RequestMethod.GET})
    public CommonResponse<String> dowithAllDB(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id", required = false, defaultValue = "1") Integer id) {
        CommonResponse<String> res = new CommonResponse<String>();
        try {
            quartzManager.start(id);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        return res;
    }


}