package com.ps.ents;

import com.ps.base.AbstractEntity;
import com.ps.base.PetType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
@Entity
@Table(name="P_PET")
public class Pet extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private User owner;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "PET_TYPE")
    private PetType petType;

    @NotEmpty
    @Column
    @Size(max = 100)
    private String name;

    @Column
    @NotNull
    private Integer age;


    @Size(max = 500)
    @Column
    private String details;

    /**
     * The pet has a RFID microchip implant.
     */
    @NotEmpty
    @Column
    @Size(min = 10, max = 100)
    private String rfid;

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

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pet pet = (Pet) o;

        if (owner != null ? !owner.getId().equals(pet.owner.getId()) : pet.owner != null) return false;
        if (petType != pet.petType) return false;
        if (name != null ? !name.equals(pet.name) : pet.name != null) return false;
        if (age != null ? !age.equals(pet.age) : pet.age != null) return false;
        return rfid != null ? rfid.equals(pet.rfid) : pet.rfid == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (rfid != null ? rfid.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("Pet[id='%,.2f', owner='%s', pet type='%s', pet name='%s', age='%,.2f']", id, owner == null ? ""
                : owner.getId(), petType.toString(), name, age);
    }
}
