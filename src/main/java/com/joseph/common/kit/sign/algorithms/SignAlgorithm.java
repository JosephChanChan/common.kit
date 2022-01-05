package com.joseph.common.kit.sign.algorithms;

/**
 * @author Joseph
 * @since 2021-12-19 14:37
 */
public interface SignAlgorithm {

    /**
     * 对原始串做特定的签名算法
     *
     * @param rawMaterial rawMaterial
     * @return sign
     */
    String sign(String rawMaterial);

}
