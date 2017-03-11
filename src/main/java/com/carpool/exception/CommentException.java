package com.carpool.exception;

/**
 * Created by qi on 2016/12/1.
 */
public class CommentException extends GenericRunTimeException {
    public CommentException(String errCode, String errMsg)
    {
        super(errCode,errMsg);
    }
}
