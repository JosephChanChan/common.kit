package com.joseph.common.kit.sign.strategy;

import com.joseph.common.kit.sign.algorithms.SignAlgorithm;
import com.joseph.common.kit.sign.model.SignModel;

/**
 * @author Joseph
 * @since 2021-12-19 16:32
 */
public interface SignChecker {

    String getCheckerName();

    void signCheck(SignModel signModel, SignAlgorithm signAlgorithm);
}
