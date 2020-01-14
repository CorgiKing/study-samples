package org.goaler.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class DataSourceConfig {


    @Bean(initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties("spring.datasource.test")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();


        try {

//            //设置统计sql信息的过滤器
//            dataSource.setFilters("stat");
//            Properties conProp = new Properties();
//            //合并相似sql
//            conProp.setProperty("druid.stat.mergeSql", "true");
//            dataSource.setConnectProperties(conProp);
            dataSource.setFilters("mergeStat");


            //防止sql注入
//            dataSource.setFilters("wall");

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return dataSource;
    }

}
