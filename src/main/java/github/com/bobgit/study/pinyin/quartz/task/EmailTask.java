package github.com.bobgit.study.pinyin.quartz.task;

import github.com.bobgit.study.pinyin.service.CronTableService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailTask implements Job {
    private final Logger logger = LoggerFactory.getLogger(EmailTask.class);
    @Autowired
    private CronTableService iCronService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("EmailTask task start execute.");
        //模拟任务执行
        iCronService.sendEmail(jobExecutionContext.getJobDetail().getKey().getName());
    }
}