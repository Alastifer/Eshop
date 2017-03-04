package com.alastifer.shop.dao.exception;

public class NoSuchEntityException extends EntityException {

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }

}
