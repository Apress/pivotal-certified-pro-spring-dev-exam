package com.ps.ents;

import com.ps.base.AbstractEntity;
import com.ps.base.ResponseStatus;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
public class Response extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "REQUEST_ID", nullable = false)
    private Request request;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "RESPONSE_STATUS")
    private ResponseStatus responseStatus;

    @Size(max = 500)
    @NotEmpty
    @Column
    private String details;

    //required by JPA
    public Response() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    //TODO add  equals, hashcode & toString
}
