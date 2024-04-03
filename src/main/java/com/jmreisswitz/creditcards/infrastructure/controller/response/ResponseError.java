package com.jmreisswitz.creditcards.infrastructure.controller.response;

import java.io.Serializable;

public class ResponseError implements Serializable {

    private String message;

    public ResponseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
