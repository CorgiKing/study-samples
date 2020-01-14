package org.goaler.druid.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    /**
     * 监控页面 相关配置
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean servletBean = new ServletRegistrationBean();
        servletBean.setServlet(new StatViewServlet());
        servletBean.addUrlMappings("/druid/*");

        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("loginUsername", "test");
        initParameters.put("loginPassword", "test");
        initParameters.put("resetEnable", "false");//页面上的“Reset All”功能
        initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
        initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow)
        servletBean.setInitParameters(initParameters);
        return servletBean;
    }

    /**
     * 统计webUrl相关数据
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean frb = new FilterRegistrationBean();
        frb.setFilter(new WebStatFilter());
        frb.addUrlPatterns("/*");
        frb.addInitParameter("exclusions", "/druid/*");
        return frb;
    }

}
