package com.carpool.exception;

/**
 * Project: Carpool
 * Package: com.carpool.exception
 * Author:  Novemser
 * 2016/11/30
 */
public class RoomNullException extends GenericRunTimeException {
    public RoomNullException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
