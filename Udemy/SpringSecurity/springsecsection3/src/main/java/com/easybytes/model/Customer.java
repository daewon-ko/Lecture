package com.easybytes.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String email;
    private String pwd;
    private String role;

    public void setId(final int id) {
        this.id = id;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setPwd(final String pwd) {
        this.pwd = pwd;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRole() {
        return role;
    }
}
