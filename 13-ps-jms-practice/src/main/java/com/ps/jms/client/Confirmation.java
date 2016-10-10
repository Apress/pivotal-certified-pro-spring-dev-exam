package com.ps.jms.client;

import java.io.Serializable;

/**
 * Created by iuliana.cosmina on 10/9/16.
 */
public class Confirmation implements Serializable {

    private String ackNumber;

    private int verificationComment;

    public Confirmation(String ackNumber, int verificationComment) {
        this.ackNumber = ackNumber;
        this.verificationComment = verificationComment;
    }

    public String getAckNumber() {
        return ackNumber;
    }

    public void setAckNumber(String ackNumber) {
        this.ackNumber = ackNumber;
    }

    public int getVerificationComment() {
        return verificationComment;
    }

    public void setVerificationComment(int verificationComment) {
        this.verificationComment = verificationComment;
    }
}
