package com.joseph.common.kit.sign;

import com.joseph.common.kit.sign.model.SignedCheckResult;

/**
 * @author Joseph
 * @since 2021-12-19 14:46
 */
public class SignCheckContext {

    private static final ThreadLocal<SignedCheckResult> SIGNED_CHECK_RESULT = new ThreadLocal<>();

    private SignCheckContext() {}

    public static void setCheckResult(SignedCheckResult result) {
        SIGNED_CHECK_RESULT.set(result);
    }

    /**
     * 为了避免内存泄漏和线程处理下一个请求时的信息污染，在拿了签名结果后会立即清理线程内本次请求的签名结果。
     * 用户需要自己维护签名结果。
     *
     * @return SignedCheckResult
     */
    public static SignedCheckResult getCheckResult() {
        SignedCheckResult res ;
        try {
            res = SIGNED_CHECK_RESULT.get();
        }
        finally {
            SIGNED_CHECK_RESULT.remove();
        }
        return res;
    }

}
