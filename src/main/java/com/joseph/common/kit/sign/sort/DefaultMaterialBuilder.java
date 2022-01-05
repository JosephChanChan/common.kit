package com.joseph.common.kit.sign.sort;

import com.joseph.common.kit.ClassKit;
import com.joseph.common.kit.collections.CollectionsKit;
import com.joseph.common.kit.sign.SignField;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 默认的签名原始串构造器。
 * 按照字典序将字段排序，将字段值转为String。
 * 只能处理简单的字段值，例如八大数据类型。对复杂自定义对象或日期类对象，请自行处理。
 *
 * @author Joseph Chan
 * @since 2021/12/21 16:34
 */
public class DefaultMaterialBuilder {

    /**
     * 将实例中带有SignField注解的字段参与签名原始串构造。
     * 只能处理简单字段值。
     * 对于没有值的字段会跳过，不参与原始串构造。
     *
     * @param bean bean
     * @param <T> T
     * @return material
     * @throws IllegalAccessException IllegalAccessException
     */
    public static <T> String getRawMaterial(T bean) throws IllegalAccessException {
        if (null == bean) {
            return null;
        }
        Map<String, Field> fieldMap = ClassKit.getFieldsWithAnnotation(bean, SignField.class);
        if (CollectionsKit.isEmpty(fieldMap)) {
            return null;
        }
        StringBuilder raw = new StringBuilder();
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            String name = entry.getKey();
            Field field = entry.getValue();
            String val = fieldStringVal(field, bean);
            if (null != val) {
                raw.append(name).append("=").append(val).append("&");
            }
        }
        if (raw.length() > 0) {
            raw.deleteCharAt(raw.length()-1);
        }
        return raw.toString();
    }

    private static <T> String fieldStringVal(Field field, T bean) throws IllegalAccessException {
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        Object val = field.get(bean);
        if (null == val) {
            return null;
        }
        return val.toString();
    }
}
