package github.com.bobgit.study.pinyin.quartz.task;

import github.com.bobgit.study.pinyin.service.CronTableService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SmsTask implements Job {
    private final Logger logger = LoggerFactory.getLogger(SmsTask.class);

    @Autowired
    private CronTableService iCronService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("SmsTask task start execute.");
        //模拟任务执行
        iCronService.sendSms(jobExecutionContext.getJobDetail().getKey().getName());
    }
}