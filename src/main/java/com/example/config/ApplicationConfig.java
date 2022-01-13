package com.example.config;

import com.example.vo.CardFormType;
import com.example.vo.MyFormEngine;
import org.flowable.engine.form.AbstractFormType;
import org.flowable.engine.impl.form.FormEngine;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {
    @Bean
    public BeanPostProcessor activitiConfigurer() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof SpringProcessEngineConfiguration) {
                    List<AbstractFormType> customFormTypes = Arrays.<AbstractFormType>asList(new CardFormType());
                    ((SpringProcessEngineConfiguration)bean).setCustomFormTypes(customFormTypes);
                    List<FormEngine> customFormEngines = Arrays.<FormEngine>asList(new MyFormEngine());
                    ((SpringProcessEngineConfiguration)bean).setCustomFormEngines(customFormEngines);
                }
                return bean;
            }
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                return bean;
            }
        };
    }
}
