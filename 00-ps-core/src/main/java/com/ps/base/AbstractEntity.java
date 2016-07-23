package com.ps.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by iuliana.cosmina on 2/7/16.
 * Description:A template class which defines the common template for all entities in the project.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    protected Long id;

    /**
     * This field is used for auditory and logging purposes. It is populated by the system when an entity instance is created.
     */
    @JsonIgnore
    @Column(name = "CREATED_AT", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date createdAt;

    /**
     * This field is used for auditory and logging purposes. It is populated by the system when an entity instance is modified.
     */
    @JsonIgnore
    @Column(name = "MODIFIED_AT", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date modifiedAt;

    @JsonIgnore
    @Version
    public int version;

    /**
     * This constructor is required by JPA. All subclasses of this class will inherit this constructor.
     */
    protected AbstractEntity() {
        createdAt = new Date();
        modifiedAt = new Date();
    }

    /**
     * Returns the entity identifier. This identifier is unique per entity. It is used by persistence frameworks used in a project,
     * and although is public, it should not be used by application code.
     * This identifier is mapped by ORM (Object Relational Mapper) to the database primary key of the Person record to which
     * the entity instance is mapped.
     *
     * @return the unique entity identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the entity identifier. This identifier is unique per entity.  Is is used by persistence frameworks
     * and although is public, it should never be set by application code.
     *
     * @param id the unique entity identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    // IDE generated methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("AbstractEntity[id='%d%n', createdAt='%s', modifiedAt='%s', version='%d%n']",
                id, sdf.format(createdAt), sdf.format(modifiedAt), version);

    }
}

