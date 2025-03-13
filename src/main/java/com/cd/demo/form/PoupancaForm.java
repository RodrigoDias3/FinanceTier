package com.cd.demo.form;

import jakarta.validation.constraints.NotNull;

public class PoupancaForm {

    @NotNull(message = "O valor não pode ser nulo.")
    Float quantidade;

    @NotNull(message = "O valor não pode ser nulo.")
    Float rate;

    @NotNull(message = "O valor não pode ser nulo.")
    Integer prazo;

    public @NotNull(message = "O valor não pode ser nulo.") Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(@NotNull(message = "O valor não pode ser nulo.") Float quantidade) {
        this.quantidade = quantidade;
    }

    public @NotNull(message = "O valor não pode ser nulo.") Float getRate() {
        return rate;
    }

    public void setRate(@NotNull(message = "O valor não pode ser nulo.") Float rate) {
        this.rate = rate;
    }

    public @NotNull(message = "O valor não pode ser nulo.") Integer getPrazo() {
        return prazo;
    }

    public void setPrazo(@NotNull(message = "O valor não pode ser nulo.") Integer prazo) {
        this.prazo = prazo;
    }
}
