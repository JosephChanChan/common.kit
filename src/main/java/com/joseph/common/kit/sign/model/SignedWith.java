package com.joseph.common.kit.sign.model;

import com.joseph.common.kit.sign.strategy.SignChecker;
import com.joseph.common.kit.sign.strategy.SymmetricSecretSignChecker;
import com.joseph.common.kit.sign.algorithms.SignAlgorithmEnum;

/**
 * @author Joseph
 * @since 2021-12-19 14:33
 */
public interface SignedWith {

    /**
     * 获取签名对象，其中必须包含已经构造好的原始签名串
     *
     * @return 签名对象
     */
    default SignModel getSignModel() {
        SignModel signModel = new SignModel();
        signModel.setRawMaterial(constructRawMaterial());
        signModel.setTargetSign(getTargetSign());
        return signModel;
    }

    /**
     * 构造签名原始串
     *
     * @return rawMaterial
     */
    String constructRawMaterial();

    /**
     * 获取待验签的签名
     *
     * @return sign to compared
     */
    String getTargetSign();

    /**
     * 选择签名校验对象
     *
     * @return SignChecker
     */
    default SignChecker chooseSignChecker() {
        return new SymmetricSecretSignChecker();
    }

    /**
     * 选择签名算法
     *
     * @return 默认md5
     */
    default SignAlgorithmEnum signAlgorithmChoose() {
        return SignAlgorithmEnum.MD5;
    }

    /**
     * 结合转转日志
     *
     * @return logStr
     */
    default String getLogStr() {
        return null;
    }


}
