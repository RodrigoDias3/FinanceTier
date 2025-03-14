package com.cd.demo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id @GeneratedValue
    Long id;
    private String name;
    private String email;
    private String password;

    private Float saldo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getSaldo() {return saldo;}

    public void setSaldo(Float saldo) {this.saldo = saldo;}

    public boolean deposita(Float quantidade) {
        if (quantidade > 0){
            saldo += quantidade;
            return true;
        }
        return false;
    }

    public boolean levanta(Float quantidade) {
        if (this.saldo >= quantidade && quantidade > 0){
            saldo -= quantidade;
            return true;
        }
        return false;
    }
}

