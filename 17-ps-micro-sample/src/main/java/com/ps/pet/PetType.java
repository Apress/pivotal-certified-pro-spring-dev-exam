package com.ps.pet;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
public enum PetType {
    CAT(1),
    DOG(2),
    BIRD(3);

    private int code;

    PetType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
