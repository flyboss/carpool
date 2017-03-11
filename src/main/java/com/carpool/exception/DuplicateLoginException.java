package com.carpool.exception;

/**
 * Created by deado on 2016/12/20.
 */
public class DuplicateLoginException extends GenericRunTimeException{
    public DuplicateLoginException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }


}
