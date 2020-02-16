package github.com.bobgit.study.pinyin;

import github.com.bobgit.study.pinyin.datasource.DynamicDataSourceConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class})
@MapperScan("github.com.bobgit.study.pinyin.dao")  //自动扫描注册为 Spring Bean

@EnableTransactionManagement //事务管理
//@EnableJms //允许消息队列
public class PinyinApplication {

    public static void main(String[] args) {
        SpringApplication.run(PinyinApplication.class, args);
    }

}
