package com.joseph.common.kit.sign.algorithms;

/**
 * @author Joseph
 * @since 2021-12-19 14:40
 */
public enum SignAlgorithmEnum {

    MD5,
    SHA256
    ;


    /**
     * 实例化一个签名算法对象
     *
     * @param signAlgorithmEnum signAlgorithmEnum
     * @return signAlgorithm
     */
    public static SignAlgorithm choose(SignAlgorithmEnum signAlgorithmEnum) {
        switch (signAlgorithmEnum) {
            default:
            case MD5:
                return new Md5Signer();

            case SHA256:
                return new Sha256Signer();
        }
    }

}
