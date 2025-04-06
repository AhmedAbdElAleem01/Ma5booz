package com.bakefinity.model.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Double creditLimit;
    private LocalDate birthDate;
    private String job;

    private LocalDateTime createdAt;

    public User() {}
    

    public User(String username, String name, String email, String password, String phoneNumber, Double creditLimit) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditLimit = creditLimit;
    }

    

    public User(int id, String username, String name, String email, String password, String phoneNumber,
            Double creditLimit, LocalDate birthDate, String job, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditLimit = creditLimit;
        this.birthDate = birthDate;
        this.job = job;
        this.createdAt = createdAt;
    }


    @Override
    public String toString() {
        return "User [name=" + name + ", email=" + email + ", password=" + password + ", creditLimit=" + creditLimit
                + "]";
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public Double getCreditLimit() { return creditLimit; }
    public void setCreditLimit(Double creditLimit) { this.creditLimit = creditLimit; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}