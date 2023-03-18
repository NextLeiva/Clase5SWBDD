package com.pe.screenplaybdd.model;

public class UserResponse {

    private int code;
    private String type;
    private String message;

    public UserResponse(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
