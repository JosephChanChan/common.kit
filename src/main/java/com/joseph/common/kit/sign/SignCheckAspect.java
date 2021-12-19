package com.joseph.common.kit.sign;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;

/**
 * @author Joseph
 * @since 2021-12-19 15:06
 */
@Slf4j
@Aspect
public class SignCheckAspect {


    @Before("@annotation(com.joseph.common.kit.sign.SignCheck)")
    public void signCheckIntercept(JoinPoint joinPoint) {
        log.info("SignCheckAspect.signCheckIntercept working!");

        Object[] args = joinPoint.getArgs();
        if (null == args || args.length <= 0) {
            return;
        }

        SignedWith signedWith = getSignedWith(args);
        if (null == signedWith) {
            log.warn("SignCheckAspect.signCheckIntercept not found SignedWith!");
            return;
        }

        String logStr = signedWith.getLogStr();
        SignModel signModel = signedWith.getSignModel();
        SignChecker signChecker = signedWith.chooseSignChecker();
        SignAlgorithm signAlgorithm = SignAlgorithmEnum.choose(signedWith.signAlgorithmChoose());

        log.info("logStr {}, signChecker {}, signAlgorithm {}, rawMaterial {}, begin check!",
                logStr, signChecker.getCheckerName(), signedWith.signAlgorithmChoose().name(), signModel.getRawMaterial());

        signModel.setRawMaterial(signedWith.concatSecret(signModel.getRawMaterial()));

        try {
            signChecker.signCheck(signModel, signAlgorithm);
            log.info("logStr {}, signCheckIntercept done!", logStr);
        }
        catch (Throwable e) {
            log.error("logStr {}, signCheckIntercept error!", logStr, e);
            SignCheckContext.setCheckResult(SignedCheckResult.signedCheckFail("signCheckIntercept error!"));
        }
    }

    private SignedWith getSignedWith(Object[] args) {
        SignedWith signedWith = null;
        for (Object param : args) {
            if (param instanceof SignedWith) {
                signedWith = (SignedWith) param;
                break;
            }
        }
        return signedWith;
    }


}
