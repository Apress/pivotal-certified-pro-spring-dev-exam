package com.ps.beans.others;

import com.ps.beans.SimpleBean;

import java.util.Date;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class MultipleTypesBean implements SimpleBean {

    private int noOne;
    private Integer noTwo;

    private long longOne;
    private Long longTwo;

    private float floatOne;
    private Float floatTwo;

    private double doubleOne;
    private Double doubleTwo;

    private boolean boolOne;
    private Boolean boolTwo;

    private char charOne;
    private Character charTwo;

    private Date date;

    public void setNoOne(int noOne) {
        this.noOne = noOne;
    }

    public void setNoTwo(Integer noTwo) {
        this.noTwo = noTwo;
    }

    public void setFloatOne(float floatOne) {
        this.floatOne = floatOne;
    }

    public void setFloatTwo(Float floatTwo) {
        this.floatTwo = floatTwo;
    }

    public void setDoubleOne(double doubleOne) {
        this.doubleOne = doubleOne;
    }

    public void setDoubleTwo(Double doubleTwo) {
        this.doubleTwo = doubleTwo;
    }

    public void setLongOne(long longOne) {
        this.longOne = longOne;
    }

    public void setLongTwo(Long longTwo) {
        this.longTwo = longTwo;
    }

    public void setBoolOne(boolean boolOne) {
        this.boolOne = boolOne;
    }

    public void setBoolTwo(Boolean boolTwo) {
        this.boolTwo = boolTwo;
    }

    public void setCharOne(char charOne) {
        this.charOne = charOne;
    }

    public void setCharTwo(Character charTwo) {
        this.charTwo = charTwo;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
