package com.carpool.exception;

/**
 * Project: Carpool
 * Package: com.carpool.exception
 * Author:  Novemser
 * 2016/12/8
 */
public class ResourceNotFoundException extends GenericRunTimeException {
    public ResourceNotFoundException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }

    public ResourceNotFoundException() {
        super("404", "Resource not found!");
    }
}
