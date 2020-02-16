package github.com.bobgit.study.pinyin.datasource;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@ConditionalOnProperty(name="spring.profiles.active", havingValue="dev", matchIfMissing=true)
public class DynamicDataSourceConfig {
    /**
     * 创建 ChangeDataSource Bean
     * */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.one")
    public DataSource oneDataSource(){
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.two")
    public DataSource twoDataSource(){
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.three")
    public DataSource threeDataSource(){
        DataSource dataSource = DruidDataSourceBuilder.create().build();
        return dataSource;
    }
    /**
     * 如果还有数据源,在这继续添加 ChangeDataSource Bean
     * */
    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource oneDataSource, DataSource twoDataSource, DataSource threeDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>(2);
        targetDataSources.put("ONE", oneDataSource);
        targetDataSources.put("TWO", twoDataSource);
        targetDataSources.put("THREE", threeDataSource);
        // 还有数据源,在targetDataSources中继续添加
        System.out.println("DataSources:" + targetDataSources);
        //默认的数据源是oneDataSource
        return new DynamicDataSource(oneDataSource, targetDataSources);
    }
}
