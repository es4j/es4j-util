package org.es4j.util.exceptions;


public class ArgumentException extends RuntimeException/*IllegalArgumentException*/ {

    public ArgumentException(String typeMustBeInterface, String instance) {
    }

    public ArgumentException(String pageSize) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
