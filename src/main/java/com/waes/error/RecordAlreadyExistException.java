package com.waes.error;

public class RecordAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 9134199044324701243L;

    public RecordAlreadyExistException(String message) {
        super(message);
    }
    
}
