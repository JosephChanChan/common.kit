package com.joseph.common.kit.sign.spring;

import com.joseph.common.kit.sign.SignCheckAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Joseph
 * @since 2021-12-19 22:10
 */
@Configuration
public class SignContextAutoEnable {

    @Bean
    public SignCheckAspect signCheckAspect() {
        return new SignCheckAspect();
    }

    @Bean
    public SpringCoordinator springCoordinator() {
        return new SpringCoordinator();
    }
}
