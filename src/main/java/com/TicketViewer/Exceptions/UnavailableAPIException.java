package com.TicketViewer.Exceptions;

public class UnavailableAPIException extends Throwable {
    public int errorCode;

    public UnavailableAPIException(int errorCode){
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
