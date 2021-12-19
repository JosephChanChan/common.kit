package com.joseph.common.kit.sign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Joseph
 * @since 2021-12-19 14:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SignCheck {

    /**
     * 从IOC容器拿到用户指定的密钥加载器。
     * 需实现SecretLoader接口
     *
     * @return 用户自定义的加载器名称
     */
    String secretLoaderByName();

    /**
     * 从IOC容器拿到用户指定的密钥加载器。
     * 需实现SecretLoader接口
     *
     * @return 用户自定义的加载器Class
     */
    Class<?> secretLoaderByClass();

}
