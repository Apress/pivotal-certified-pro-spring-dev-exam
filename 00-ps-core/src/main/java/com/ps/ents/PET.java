package com.ps.ents;

import com.ps.base.AbstractEntity;
import com.ps.base.PetType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
public class Pet extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private User owner;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "PET_TYPE")
    private PetType petType;

    @Size(max = 500)
    @NotEmpty
    @Column
    private String details;

    //required by JPA
    public Pet() {
        super();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    //TODO add  equals, hashcode & toString
}
