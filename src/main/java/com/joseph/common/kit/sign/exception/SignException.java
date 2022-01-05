package com.joseph.common.kit.sign.exception;

/**
 * @author Joseph Chan
 * @since 2021/12/24 17:39
 */
public class SignException extends RuntimeException {

    private String code = "-1";

    public SignException(String message) {
        super(message);
    }

    public SignException(Throwable cause) {
        super(cause);
    }

    public SignException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SignException of(String errorMsg){
        return new SignException(errorMsg);
    }

}
