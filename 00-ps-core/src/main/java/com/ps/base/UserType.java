package com.ps.base;

/**
 * Created by iuliana.cosmina on 2/7/16.
 * Description: Type of users using the site
 */
public enum UserType {
    OWNER(1),
    SITTER(2),
    BOTH(3),
    ADMIN(4);

    private int code;

    UserType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
