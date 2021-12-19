package com.joseph.common.kit.sign;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Joseph
 * @since 2021-12-19 14:39
 */
@Slf4j
public class Md5Signer implements SignAlgorithm {

    @Override
    public String sign(String rawMaterial) {
        if (null == rawMaterial || rawMaterial.length() == 0) {
            return null;
        }
        String sign = null;
        try {
            sign = md5(rawMaterial);
        } catch (NoSuchAlgorithmException e) {
            log.error("Md5Signer NoSuchAlgorithmException!", e);
        }
        return sign;
    }

    /**
     * 生成 md5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public String md5(String data) throws NoSuchAlgorithmException {
        java.security.MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString().toUpperCase();
    }
}
