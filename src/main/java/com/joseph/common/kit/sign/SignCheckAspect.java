package com.joseph.common.kit.sign;

import com.joseph.common.kit.sign.algorithms.SignAlgorithm;
import com.joseph.common.kit.sign.algorithms.SignAlgorithmEnum;
import com.joseph.common.kit.sign.exception.SignException;
import com.joseph.common.kit.sign.loader.SecretLoader;
import com.joseph.common.kit.sign.model.SignModel;
import com.joseph.common.kit.sign.model.SignedCheckResult;
import com.joseph.common.kit.sign.model.SignedWith;
import com.joseph.common.kit.sign.spring.SpringCoordinator;
import com.joseph.common.kit.sign.strategy.SignChecker;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

/**
 * @author Joseph
 * @since 2021-12-19 15:06
 */
@Slf4j
@Aspect
public class SignCheckAspect {

    @Autowired
    private SpringCoordinator springCoordinator;

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

        try {
            SecretLoader secretLoader = getSecretLoader(joinPoint);
            String secret = secretLoader.loadSecret(signedWith);
            signModel.setRawMaterial(secretLoader.concatSecret(secret, signModel.getRawMaterial()));
        }
        catch (SignException se) {
            log.error("logStr {}, SignException occurred!", logStr, se);
            SignCheckContext.setCheckResult(SignedCheckResult.signedCheckFail(se.getMessage()));
            return;
        }
        catch (Throwable e) {
            log.error("logStr {}, loadSecret error!", logStr, e);
            SignCheckContext.setCheckResult(SignedCheckResult.signedCheckFail("signCheckIntercept error!"));
            return;
        }

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

    private SecretLoader getSecretLoader(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SignCheck annotation = method.getAnnotation(SignCheck.class);

        SecretLoader secretLoader = getSecretLoader(annotation.secretLoaderByName());
        if (null == secretLoader) {
            secretLoader = getSecretLoader(annotation.secretLoaderByClass());
        }
        if (null == secretLoader) {
            throw new IllegalArgumentException("method " + method.getName() +
                    " signCheck annotation get no SecretLoader by name and class!");
        }
        return secretLoader;
    }

    private SecretLoader getSecretLoader(String loaderName) {
        return springCoordinator.getLoaderByName(loaderName);
    }

    private SecretLoader getSecretLoader(Class<?> clazz) {
        return springCoordinator.getLoaderByType(clazz);
    }


}
