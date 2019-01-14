package com.legosoft.facultamiento.models.nuevo;

import java.io.Serializable;

public class Response implements Serializable {

    private int code;

    private String message;

    public Response(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
