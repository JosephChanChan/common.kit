package com.joseph.common.kit;

import com.joseph.common.kit.constant.Constant;

import java.util.Random;

/**
 * @author Joseph
 * @since 2022/5/8
 */
public class StringKit {


    public static String randomNumString(int count) {
        Random random = new Random();
        StringBuilder b = new StringBuilder();
        int length = Constant.NUM_STRING.length();
        for (int i = 0; i < count; i++) {
            b.append(Constant.NUM_STRING.charAt(random.nextInt(length)));
        }
        return b.toString();
    }

    public static String randomNumString() {
        return randomNumString(6);
    }

    public static String randomString(int count) {
        Random random = new Random();
        StringBuilder b = new StringBuilder();
        int length = Constant.RANDOM_STRING.length();
        for (int i = 0; i < count; i++) {
            b.append(Constant.RANDOM_STRING.charAt(random.nextInt(length)));
        }
        return b.toString();
    }

    public static String randomString() {
        return randomString(6);
    }

    public static boolean blank(CharSequence cs) {
        if (cs != null) {
            int length = cs.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean notBlank(CharSequence cs) {
        return !blank(cs);
    }


}
