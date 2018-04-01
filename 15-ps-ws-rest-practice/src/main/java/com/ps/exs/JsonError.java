package com.ps.exs;

/**
 * Created by iuliana.cosmina on 10/13/15.
 */
public class JsonError {
    private String error;

    public JsonError(){}

    public JsonError(String message) {
        this.error = message;
    }

    public String getError() {
        return error;
    }


    public void setError(String error) {
        this.error = error;
    }
}
