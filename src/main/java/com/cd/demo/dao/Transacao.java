package com.cd.demo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Transacao {
    @Id @GeneratedValue
    Long id;
    Long user;
    Float quantidade;
    String categoria;
    String data;

    boolean eReceita;

    public Long getUser() {
        return user;
    }

    public void setUser(Long id_user) {
        this.user = id_user;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean iseReceita() {
        return eReceita;
    }

    public void seteReceita(boolean eReceita) {
        this.eReceita = eReceita;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
