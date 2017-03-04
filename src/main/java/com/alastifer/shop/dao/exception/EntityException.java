package com.alastifer.shop.dao.exception;

public class EntityException extends Exception {

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }

}
