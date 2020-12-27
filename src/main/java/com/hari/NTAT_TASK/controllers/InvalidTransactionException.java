package com.hari.NTAT_TASK.controllers;

public class InvalidTransactionException extends Exception{
    InvalidTransactionException(String msg){
        super(msg);
    }
}
