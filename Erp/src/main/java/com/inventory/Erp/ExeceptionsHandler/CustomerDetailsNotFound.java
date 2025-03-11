package com.inventory.Erp.ExeceptionsHandler;

public class CustomerDetailsNotFound extends RuntimeException{
    public CustomerDetailsNotFound(String message) {
        super(message);
    }
}
