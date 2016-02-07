package com.ps.ents;

import com.ps.base.AbstractEntity;
import com.ps.base.ReviewGrade;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
public class Review extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "AUTHOR_ID", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "RECEIVER_ID", nullable = false)
    private User receiver;

    @NotEmpty
    @Column
    @Enumerated(EnumType.ORDINAL)
    private ReviewGrade grade;

    @Size(max = 500)
    @NotEmpty
    @Column
    private String details;

    //required by JPA
    public Review() {
        super();
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public ReviewGrade getGrade() {
        return grade;
    }

    public void setGrade(ReviewGrade grade) {
        this.grade = grade;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    //TODO add  equals, hashcode & toString
}
