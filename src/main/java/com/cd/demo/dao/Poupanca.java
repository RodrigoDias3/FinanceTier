package com.cd.demo.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Poupanca {
    @Id
    @GeneratedValue
    Long id;
    Long user;
    Float quantidade;
    Float rate;
    Integer prazo;

    public Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(Integer prazo) {
        this.prazo = prazo;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void recebeJuros(){
        float jurosBrutos = (float) (quantidade * (rate / 100) * (prazo / 365.0));
        float retencao = (float) (jurosBrutos * 0.28);
        float jurosLiquidos = jurosBrutos - retencao;

        quantidade += jurosLiquidos;
    }
}
