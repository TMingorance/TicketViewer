package com.TicketViewer.Exceptions;

public class ResourceNotFoundException extends Exception{

    public int errorCode;

    public ResourceNotFoundException(int errorCode){
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
