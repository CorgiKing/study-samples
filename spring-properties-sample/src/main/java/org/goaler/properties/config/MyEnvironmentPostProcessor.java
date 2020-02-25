package org.goaler.properties.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * 这种扩展方式需要在META-INFO/spring.factories中加入
 * org.springframework.boot.env.EnvironmentPostProcessor=org.goaler.properties.config.MyEnvironmentPostProcessor
 */
public class MyEnvironmentPostProcessor implements EnvironmentPostProcessor {

    /**
     * 实现该方法，获取propertySource，并添加到environment中
     * @param environment
     * @param application
     */
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        //environment.getPropertySources().addLast(propertySource);
    }
}
