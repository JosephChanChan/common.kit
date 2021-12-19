package com.joseph.common.kit.sign.loader;

import com.joseph.common.kit.sign.model.SignedWith;

/**
 * @author Joseph
 * @since 2021-12-19 21:34
 */
public interface SecretLoader {

    /**
     * 根据签名模型从数据源加载密钥
     *
     * @param signedWith signedWith
     * @return 密钥
     */
    String loadSecret(SignedWith signedWith);

    /**
     * 签名原始串拼接密钥
     *
     * @param secret secret
     * @param rawMaterial rawMaterial
     * @return 准备进行加密的原始串
     */
    default String concatSecret(String secret, String rawMaterial) {
        return rawMaterial;
    }

}
