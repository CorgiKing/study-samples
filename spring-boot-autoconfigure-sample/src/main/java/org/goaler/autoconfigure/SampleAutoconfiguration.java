package org.goaler.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AutoconfigureProperties.class)
public class SampleAutoconfiguration {


    @Bean
    @ConditionalOnProperty(name = "goaler.config.enable", havingValue = "true")
    public void testAutoConfig(AutoconfigureProperties properties){

    }
}
