package com.doubleDimple.activityDemo.config;

import com.doubleDimple.activityDemo.utils.DefIdGenerator;
import org.activiti.engine.impl.persistence.StrongUuidGenerator;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActivityConfig implements ProcessEngineConfigurationConfigurer {


    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setIdGenerator(new DefIdGenerator());
    }
}
