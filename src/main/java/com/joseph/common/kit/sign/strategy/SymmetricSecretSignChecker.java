package com.joseph.common.kit.sign.strategy;

import com.joseph.common.kit.sign.SignCheckContext;
import com.joseph.common.kit.sign.algorithms.SignAlgorithm;
import com.joseph.common.kit.sign.model.SignModel;
import com.joseph.common.kit.sign.model.SignedCheckResult;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Joseph
 * @since 2021-12-19 16:34
 */
@Slf4j
public class SymmetricSecretSignChecker implements SignChecker {

    private static final String NAME = "SymmetricSecretSignChecker";

    @Override
    public void signCheck(SignModel signModel, SignAlgorithm signAlgorithm) {
        if (!signModel.necessaryParamValid()) {
            SignCheckContext.setCheckResult(SignedCheckResult.signedCheckFail("rawMaterial or targetSign is absent!"));
            return;
        }
        String sign = signAlgorithm.sign(signModel.getRawMaterial());
        String targetSign = signModel.getTargetSign();
        if (null != sign && sign.equals(targetSign)) {
            log.info("Both sign and targetSign match!");
            SignCheckContext.setCheckResult(SignedCheckResult.signedCheckSuccess());
        }
        else {
            log.warn("Both sign and targetSign not match! sign {} and targetSign {}!", sign, targetSign);
            SignCheckContext.setCheckResult(SignedCheckResult.signedCheckFail("Both sign and targetSign not match!"));
        }
    }

    @Override
    public String getCheckerName() {
        return NAME;
    }
}
