package com.carpool.exception;

/**
 * Project: Carpool
 * Package: com.carpool.exception
 * Author:  Novemser
 * 2016/12/21
 */
public class RoomFullException extends GenericRunTimeException {

    public RoomFullException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
