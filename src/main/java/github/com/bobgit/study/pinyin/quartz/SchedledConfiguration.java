package github.com.bobgit.study.pinyin.quartz;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

//由于springboot追求零xml配置，所以下面会以配置Bean的方式来实现
//QuartzJobBean
//1、Job  表示一个工作，要执行的具体内容。此接口中只有一个方法 void execute(JobExecutionContext context)
//@Configuration
public class SchedledConfiguration {
 
 //(1)JobDetail(MethodInvokingJobDetailFactoryBean)的配置需要Job这个参数(具体执行调度任务的类,也就是例子中的ScheduledTasks ).
//可执行的调度程序，Job是这个可执行程调度程序所要执行的内容，另外JobDetail还包含了这个任务调度的方案和策略。
    @Bean(name = "detailFactoryBean")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduledTasks scheduledTasks){
        MethodInvokingJobDetailFactoryBean jobDetail  = new MethodInvokingJobDetailFactoryBean ();
        //这儿设置对应的Job对象
        jobDetail .setTargetObject (scheduledTasks);
        //这儿设置对应的方法名  与执行具体任务调度类中的方法名对应
        jobDetail .setTargetMethod ("work");
        jobDetail .setConcurrent (false);//是否并发执行
        return jobDetail ;
    }
 //(2)Trigger(CronTriggerFactoryBean)的配置需要JobDetail这个参数.同时需要配置cron表达式,这个下面谈.
    //代表一个调度参数的配置，什么时候去调。
    @Bean(name = "cronTriggerBean")
    public CronTriggerFactoryBean cronTriggerBean(MethodInvokingJobDetailFactoryBean detailFactoryBean){
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean ();
        trigger.setJobDetail (detailFactoryBean.getObject ());

        trigger.setCronExpression ("0/5 * * ? * *");//每5秒执行一次
//        try {
//            trigger.setCronExpression ("0/5 * * ? * *");//每5秒执行一次
//        } catch (ParseException e) {
//            e.printStackTrace ();
//        }
        return trigger;
 
    }
 //(3)Scheduler(SchedulerFactoryBean)的配置需要Trigger这个参数.
    //一个调度容器中可以注册多个JobDetail和Trigger。当Trigger与JobDetail组合，就可以被Scheduler容器调度了。
    @Bean
    public SchedulerFactoryBean schedulerFactory(CronTriggerFactoryBean cronTriggerBean){
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean ();
        schedulerFactory.setTriggers(cronTriggerBean.getObject());
        return schedulerFactory;
    }
}