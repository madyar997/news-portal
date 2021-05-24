package com.epam.rd.newsportal.exception;

public class NewsNotFoundException extends Exception {
    public NewsNotFoundException(String message){
        super(message);
    }
}
