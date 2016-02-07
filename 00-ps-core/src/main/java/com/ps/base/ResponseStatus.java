package com.ps.base;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
public enum ResponseStatus {
    PROPOSED(1),
    ACCEPTED(2),
    REJECTED(3);

    private int code;

    ResponseStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
