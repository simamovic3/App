package com.tim4.app.account.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.tim4.app.account.helpers.ValidPassword;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;

    @NotNull(message = "Name cannot be null")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Last name cannot be null")
    @Column(name = "lastname")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull(message = "Username cannot be null")
    @Column(name = "username")
    private String username;

    @ValidPassword
    @NotNull()
    @Column(name="password")
    private String password;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 90, message = "Age should not be greater than 90")
    @Column(name="age")
    private Integer age;

    @NotNull(message = "Role cannot be null")
    @Column(name = "role")
    private Integer role;


    public User() {
    }

    public User(String name, String lastName, String email, String username, String password, Integer age, Integer role) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
