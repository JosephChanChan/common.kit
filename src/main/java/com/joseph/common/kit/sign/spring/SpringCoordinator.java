package com.joseph.common.kit.sign.spring;

import com.joseph.common.kit.sign.loader.SecretLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Joseph
 * @since 2021-12-19 21:50
 */
public class SpringCoordinator implements ApplicationContextAware {

    private ApplicationContext springContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }

    public SecretLoader getLoaderByName(String name) {
        if (null == name || name.length() == 0) {
            return null;
        }
        return springContext.getBean(name, SecretLoader.class);
    }

    public SecretLoader getLoaderByType(Class<?> clazz) {
        if (null == clazz) {
            return null;
        }
        Object bean = springContext.getBean(clazz);
        if (null != bean && !(bean instanceof SecretLoader)) {
            throw new IllegalArgumentException("clazz "+clazz.getSimpleName()+" is not a subclass from SecretLoader!");
        }
        return (SecretLoader) bean;
    }
}
