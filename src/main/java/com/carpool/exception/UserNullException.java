package com.carpool.exception;

/**
 * Project: Carpool
 * Package: com.carpool.exception
 * Author:  Novemser
 * 2016/11/30
 */
public class UserNullException extends GenericRunTimeException {

    public UserNullException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
