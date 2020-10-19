package com.boot.persist.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;

    private String password;

    private short enabled;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public short getEnabled() {
        return enabled;
    }

    public User() {
    }

    public User(String username, String password, short enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}