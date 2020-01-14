package org.goaler.druid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DruidApplication implements ApplicationRunner {

    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        SpringApplication.run(DruidApplication.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void run(ApplicationArguments args) throws Exception {
        System.out.println("start...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; ++i){
                    List<Map<String, Object>> rets = jdbcTemplate.queryForList("select * from test where id =" + i);
                    System.out.println(rets);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        System.out.println("end");
    }
}
