package com.kocaelli.koray.izinyonetimsistemi.api.advice;

public class EmployeeNotFound extends  RuntimeException {

    public EmployeeNotFound(String message) {
        super(message);
    }
}
