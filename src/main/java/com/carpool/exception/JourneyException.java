package com.carpool.exception;

/**
 * Created by qi on 2016/12/1.
 */
public class JourneyException extends GenericRunTimeException {
    public JourneyException(String errCode, String errMsg)
    {
        super(errCode,errMsg);
    }
}
