package com.joseph.common.kit.sign;

import lombok.Data;
import lombok.Getter;

/**
 * @author Joseph
 * @since 2021-12-19 14:55
 */
@Getter
public class SignedCheckResult {

    private boolean checkSuccess;

    private String failReason;

    private SignedCheckResult(boolean checkSuccess) {
        this.checkSuccess = checkSuccess;
    }

    private SignedCheckResult(boolean checkSuccess, String failReason) {
        this.checkSuccess = checkSuccess;
        this.failReason = failReason;
    }

    public static SignedCheckResult signedCheckSuccess() {
        return new SignedCheckResult(true);
    }

    public static SignedCheckResult signedCheckFail(String reason) {
        return new SignedCheckResult(false, reason);
    }

}
