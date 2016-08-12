package com.ps.ents;

import com.ps.base.AbstractEntity;
import com.ps.base.ResponseStatus;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
@Entity
@Table(name="P_RESPONSE")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Response response = (Response) o;

        if (user != null ? !user.getId().equals(response.user.getId()) : response.user != null) return false;
        if (request != null ? !request.getId().equals(response.request.getId()) : response.request != null) return false;
        return responseStatus == response.responseStatus;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (request != null ? request.hashCode() : 0);
        result = 31 * result + (responseStatus != null ? responseStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("Request[id='%,.2f', user='%s', request='%s', responseStatus='%s']",
                id, user == null ? "" : user.getId(), request == null ? "" : request.getId(), responseStatus);
    }
}
