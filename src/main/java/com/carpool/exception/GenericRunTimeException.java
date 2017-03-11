package com.carpool.exception;

/**
 * Project: Carpool
 * Package: com.carpool.exception
 * Author:  Novemser
 * 2016/11/30
 */
public class GenericRunTimeException extends RuntimeException {
    private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public GenericRunTimeException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
