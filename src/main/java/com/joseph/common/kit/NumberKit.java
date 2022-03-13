package com.joseph.common.kit;

/**
 * @author Joseph
 */
public class NumberKit {

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
