package com.carpool.exception;

/**
 * Project: Carpool
 * Package: com.carpool.exception
 * Author:  Novemser
 * 2016/12/22
 */
public class DuplicateJoiningRoomException extends GenericRunTimeException {
    public DuplicateJoiningRoomException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
