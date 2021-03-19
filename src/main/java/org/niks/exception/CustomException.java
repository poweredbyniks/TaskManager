package org.niks.exception;

import java.util.NoSuchElementException;

public class CustomException extends NoSuchElementException {
    public CustomException(String methodName, String className, Exception e) {
        super(methodName + " exception (" + className + ")");
    }
}
