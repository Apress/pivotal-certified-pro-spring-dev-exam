package com.ps.base;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
public enum ReviewGrade {
    WORST(0),
    BAD(1),
    ACCEPTABLE(2),
    GOOD(3),
    WONDERFUL(4);

    private int grade;

    ReviewGrade(int code) {
        this.grade = code;
    }

    public int getGrade() {
        return grade;
    }
}
