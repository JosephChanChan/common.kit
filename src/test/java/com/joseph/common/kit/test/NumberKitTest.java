package com.joseph.common.kit.test;

import com.joseph.common.kit.NumberKit;

/**
 * @author Joseph
 */
public class NumberKitTest {

    public static void main(String[] args) {
        NumberKitTest m = new NumberKitTest();
        m.t4();
    }

    void t1() {
        System.out.println("大于0");
        System.out.println(NumberKit.gt0((byte) 1));
        System.out.println(NumberKit.gt0((short) 1));
        System.out.println(NumberKit.gt0((int) 1));
        System.out.println(NumberKit.gt0((long) Integer.MAX_VALUE + 999));
        System.out.println(NumberKit.gt0(0.0000000000001f));
        System.out.println(NumberKit.gt0(0.00000000000000000000000000003d));
        System.out.println(NumberKit.gt0(-0.00000000000000000000000000003d));
        System.out.println(NumberKit.gt0(-0.0000000000000000001232534123f));
        System.out.println(NumberKit.gt0((byte) -1));
        System.out.println(NumberKit.gt0((short) -1));
        System.out.println(NumberKit.gt0((int) -1));
        System.out.println(NumberKit.gt0((long) Long.MIN_VALUE));
    }

    void t2() {
        System.out.println("小于0");
        System.out.println(NumberKit.lt0((byte) 1));
        System.out.println(NumberKit.lt0((short) 1));
        System.out.println(NumberKit.lt0((int) 1));
        System.out.println(NumberKit.lt0((long) Integer.MAX_VALUE + 999));
        System.out.println(NumberKit.lt0(0.0000000000001f));
        System.out.println(NumberKit.lt0(0.00000000000000000000000000003d));
        System.out.println(NumberKit.lt0(-0.00000000000000000000000000003d));
        System.out.println(NumberKit.lt0(-0.0000000000000000001232534123f));
        System.out.println(NumberKit.lt0((byte) -1));
        System.out.println(NumberKit.lt0((short) -1));
        System.out.println(NumberKit.lt0((int) -1));
        System.out.println(NumberKit.lt0((long) Long.MIN_VALUE));
        System.out.println(NumberKit.lt0(Integer.MIN_VALUE));
    }

    void t3() {
        System.out.println("大于等于0");
        System.out.println("0.0000000000001f "+NumberKit.gte0(0.0000000000001f));
        System.out.println("0.00000000000000000000000000003d " + NumberKit.gte0(0.00000000000000000000000000003d));
        System.out.println("0.00000000000f " + NumberKit.gte0(0.00000000000f));
        System.out.println("0.0000000000000d " + NumberKit.gte0(0.0000000000000d));
        System.out.println("-0.0000000000000f " + NumberKit.gte0(-0.0000000000000f));
        System.out.println("-0.0000000000000d " + NumberKit.gte0(-0.0000000000000d));
        System.out.println("-0.00000000000000000000000000000123 " + NumberKit.gte0(-0.00000000000000000000000000000123));
        System.out.println("0.000000000000000000000000000000321 " + NumberKit.gte0(0.000000000000000000000000000000321));
        System.out.println("3123123.9182309234 " + NumberKit.gte0(3123123.9182309234));
    }

    void t4() {
        System.out.println("小于等于0");
        System.out.println("0.0000000000001f "+NumberKit.lte0(0.0000000000001f));
        System.out.println("0.00000000000000000000000000003d " + NumberKit.lte0(0.00000000000000000000000000003d));
        System.out.println("0.00000000000f " + NumberKit.lte0(0.00000000000f));
        System.out.println("0.0000000000000d " + NumberKit.lte0(0.0000000000000d));
        System.out.println("-0.0000000000000f " + NumberKit.lte0(-0.0000000000000f));
        System.out.println("-0.0000000000000d " + NumberKit.lte0(-0.0000000000000d));
        System.out.println("-0.00000000000000000000000000000123 " + NumberKit.lte0(-0.00000000000000000000000000000123));
        System.out.println("0.000000000000000000000000000000321 " + NumberKit.lte0(0.000000000000000000000000000000321));
        System.out.println("3123123.9182309234 " + NumberKit.lte0(3123123.9182309234));
    }
}
