package com.jmreisswitz.creditcards.infrastructure.persistence.jpa.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "user")
public class JpaUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private Date createdAt;

    public JpaUser() {
    }

    public JpaUser(Integer id, String username, String password, Date createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
