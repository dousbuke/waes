package com.waes.error;

public class RecordMissingException extends RuntimeException {

    private static final long serialVersionUID = 9134199044324701243L;

    public RecordMissingException(String message) {
        super(message);
    }
    
}
