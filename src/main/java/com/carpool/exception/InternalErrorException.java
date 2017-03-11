package com.carpool.exception;

/**
 * Project: Carpool
 * Package: com.carpool.exception
 * Author:  Novemser
 * 2016/12/20
 */
public class InternalErrorException extends GenericRunTimeException {
    public InternalErrorException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
