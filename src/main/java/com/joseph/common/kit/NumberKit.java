package com.joseph.common.kit;

import java.math.BigDecimal;

/**
 * @author Joseph
 */
public class NumberKit {

    private static final BigDecimal DECIMAL_ZERO = new BigDecimal("0");

    public static boolean gt0(Number number) {
        if (null == number) {
            return false;
        }
        if (number instanceof Float) {
            return number.floatValue() > 0.0f;
        }
        else if (number instanceof Double) {
            return number.doubleValue() > 0.0d;
        }
        else if (number instanceof BigDecimal) {
            return ((BigDecimal) number).compareTo(DECIMAL_ZERO) > 0;
        }
        else {
            return number.longValue() > 0;
        }
    }

    public static boolean lt0(Number number) {
        if (null == number) {
            return false;
        }
        if (number instanceof Float) {
            return number.floatValue() < 0.0f;
        }
        else if (number instanceof Double) {
            return number.doubleValue() < 0.0d;
        }
        else if (number instanceof BigDecimal) {
            return ((BigDecimal) number).compareTo(DECIMAL_ZERO) < 0;
        }
        else {
            return number.longValue() < 0;
        }
    }

    public static boolean gte0(Number number) {
        if (gt0(number)) {
            return true;
        }
        return null != number && !lt0(number);
    }

    public static boolean lte0(Number number) {
        if (lt0(number)) {
            return true;
        }
        return null != number && !gt0(number);
    }
}
