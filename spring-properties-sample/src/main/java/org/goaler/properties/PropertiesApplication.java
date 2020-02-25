package org.goaler.properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
public class PropertiesApplication implements ApplicationRunner {

    @Value("${server.tag}")
    private String tag;

    @Autowired
    Environment env;

    public static void main(String[] args) {
        System.out.println(Arrays.asList(args));
        SpringApplication.run(PropertiesApplication.class, args);
    }

    public void run(ApplicationArguments args) throws Exception {
        System.out.println(tag);
        System.out.println(env);
    }
}
