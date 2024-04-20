package com.example.RideIt.exception;

import com.example.RideIt.model.Customer;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
