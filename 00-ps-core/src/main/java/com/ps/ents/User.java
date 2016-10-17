package com.ps.ents;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ps.base.AbstractEntity;
import com.ps.base.UserType;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/7/16.
 */
@Entity
@Table(name="P_USER")
@SequenceGenerator(name = "seqGen", allocationSize = 1)
@NamedQueries({
        @NamedQuery(name=User.FIND_BY_USERNAME_EXACT, query = "from User u where username= :un"),
        @NamedQuery(name=User.FIND_BY_USERNAME_LIKE, query = "from User u where username like :un")

})
public class User extends AbstractEntity {
    public static final String FIND_BY_USERNAME_EXACT = "findByUsernameExact";
    public static final String FIND_BY_USERNAME_LIKE = "findByUsernameLike";


    /**
     * username = email
     */
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String username;

    @Column(name="first_name")
    public String firstName;

    @Column(name="last_name")
    public String lastName;

    @NotEmpty
    public String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    private String address;

    @NotEmpty
    @Column(unique = true)
    private String email;

    /***
     * Rating for a used is computed from reviews for a user
     */
    @NotNull
    @Column
    private Double rating;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Pet> pets = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade =  {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Request> requests = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade =  {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Response> responses = new HashSet<>();

    @Column
    private boolean active;

    //required by JPA
    public User() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public boolean addPet(Pet pet) {
        pet.setOwner(this);
        return pets.add(pet);
    }

    protected void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    protected void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public boolean addRequest(Request request) {
        request.setUser(this);
        return requests.add(request);
    }

    public Set<Response> getResponses() {
        return responses;
    }

    protected void setResponses(Set<Response> responses) {
        this.responses = responses;
    }

    public boolean addResponse(Response response) {
        response.setUser(this);
        return responses.add(response);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return userType == user.userType;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return super.toString() + ";" + String.format("User[username='%s', firstName='%s', lastName='%s', email='%s'" +
                        " userType='%s', activeSince='%s', rating=id='%f%n']", getUsername(),
                getFirstName(), getLastName(), getEmail(), getUserType().toString(),sdf.format(createdAt), rating);

    }
}
