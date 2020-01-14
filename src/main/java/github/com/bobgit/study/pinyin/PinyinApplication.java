package github.com.bobgit.study.pinyin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("github.com.bobgit.study.pinyin.dao")  //自动扫描注册为 Spring Bean

//@EnableJms //允许消息队列
public class PinyinApplication {

    public static void main(String[] args) {
        SpringApplication.run(PinyinApplication.class, args);
    }

}
