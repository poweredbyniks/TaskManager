package org.niks.exception;

public class RepositoryException extends RuntimeException {
    public RepositoryException(String methodName, String className, Exception e) {
        super(methodName + " exception (" + className + ")");
    }
}
