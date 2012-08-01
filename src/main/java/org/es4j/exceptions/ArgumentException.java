package org.es4j.exceptions;


public class ArgumentException extends IllegalArgumentException {

    public ArgumentException(String typeMustBeInterface, String instance) {
    }

    public ArgumentException(String pageSize) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
