package org.goaler.properties.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * spring cloud config通过这种方式获取配置中心的配置并添加到environment
 *
 * 配置读取之后，容器刷新（refresh()）之前调用
 *
 *
 *
 * 添加方式：
 * 1. SpringApplication的实例，通过方法加入
 *     application.addInitializers();
 * 2. 配置文件添加
 *     context.initializer.classes=org.goaler.properties.config.MyApplicationContextInitializer
 * 3. spring.factories中添加
 *     org.springframework.context.ApplicationContextInitializer=org.goaler.properties.config.MyApplicationContextInitializer
 */
public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /**
     * 这里可以对environment进行修改
     * @param applicationContext
     */
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println("ApplicationContextInitializer.initialize()");
    }
}
