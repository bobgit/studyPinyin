package github.com.bobgit.study.pinyin.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.jdbc.DatabaseDriver;

import java.util.HashMap;
import java.util.Map;

//import org.springframework.boot.bind.RelaxedDataBinder;

public class DruidDataSourceBuilder {

    private Map<String, String> properties = new HashMap<String, String>();

    public static DruidDataSourceBuilder create() {
        return new DruidDataSourceBuilder();
    }

    public DruidDataSource build() {
        DruidDataSource dataSource = new DruidDataSource();
        maybeGetDriverClassName();
        bind(dataSource);
        return dataSource;
    }

    //use spring boot relaxed binding by reflection config druid . detail see Spring Boot Reference Relaxed binding section.
    private void bind(DruidDataSource result) {
        MutablePropertyValues properties = new MutablePropertyValues(this.properties);
/*        new RelaxedDataBinder(result)
                .withAlias("url", "jdbcUrl")
                .withAlias("username", "user")
                .bind(properties);*/
    }

    private void maybeGetDriverClassName() {
        if (!this.properties.containsKey("driverClassName")
                && this.properties.containsKey("url")) {
            String url = this.properties.get("url");
            String driverClass = DatabaseDriver.fromJdbcUrl(url).getDriverClassName();
            this.properties.put("driverClassName", driverClass);
        }
    }
}
