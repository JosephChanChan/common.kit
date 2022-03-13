package com.joseph.common.kit;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Joseph Chan
 */
@Slf4j
public class ClassKit {

    /**
     * 获取实例中带有注解的字段
     *
     * @param bean bean
     * @param annotation annotation
     * @param <T> T
     * @return fields
     */
    public static <T, A extends Annotation> Map<String, Field> getFieldsWithAnnotation(T bean, Class<A> annotation) {
        if (null == bean || null == annotation) {
            return null;
        }
        Map<String, Field> fieldMap = new TreeMap<>();
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            if (null == field.getAnnotation(annotation)) {
                continue;
            }
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    /**
     * 对指定实例进行深拷贝
     *
     * @param bean bean
     * @param <T> T
     * @return T
     */
    public static <T> T deepCopyWithNoArgsConstructor(T bean) {
        if (null == bean) {
            return bean;
        }

        Object to = instanceWithNoArgsConstructor(bean.getClass());
        if (null == to) {
            return null;
        }

        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            Object val ;
            try {
                if (null != (val = field.get(bean))) {
                    field.set(to, val);
                }
            } catch (IllegalAccessException e) {
                log.error("ClassKit#deepCopyWithNoArgsConstructor error! field {} from {} access error!",
                        field.getName(), bean.getClass().getSimpleName(), e);
                return null;
            }
        }
        return (T) to;
    }

    /**
     * 用无参构造器实例化
     *
     * @param clazz clazz
     * @param <T> T
     * @return instance of T
     */
    public static <T> T instanceWithNoArgsConstructor(Class<T> clazz) {
        if (null == clazz) {
            return null;
        }
        try {
            Constructor<T> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("ClassKit#instanceWithNoArgsConstructor error!", e);
        }
        return null;
    }

    /**
     * 获取指定实例中的指定注解信息
     *
     * @param instance instance
     * @param annotationClass annotationClass
     * @return annotation
     */
    public static <T, A extends Annotation> A getAnnotation(T instance, Class<A> annotationClass) {
        if (null == instance || null == annotationClass) {
            return null;
        }
        return instance.getClass().getAnnotation(annotationClass);
    }

    /**
     * 获取指定类中的指定注解信息
     *
     * @param clazz clazz
     * @param annotationClass annotationClass
     * @return annotation
     */
    public static <T, A extends Annotation> A getAnnotation(Class<T> clazz, Class<A> annotationClass) {
        if (null == clazz || null == annotationClass) {
            return null;
        }
        return clazz.getAnnotation(annotationClass);
    }

}
