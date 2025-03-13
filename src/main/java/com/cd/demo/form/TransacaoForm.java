package com.cd.demo.form;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class TransacaoForm {
    @NotEmpty(message = "Erro: A data tem que estar preenchida")
    public String data;
    @NotEmpty(message = "Erro: A categoria tem que estar preenchida")
    public String categoria;
    @NotNull(message = "O valor não pode ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que 0.")
    public Float valor;

    @NotNull(message = "Erro: O tipo tem que estar preenchido")
    public boolean eReceita;

    public @NotEmpty(message = "Erro: A categoria tem que estar preenchida") String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotEmpty(message = "Erro: A categoria tem que estar preenchida") String categoria) {
        this.categoria = categoria;
    }

    public @NotEmpty(message = "Erro: A data tem que estar preenchida") String getData() {
        return data;
    }

    public void setData(@NotEmpty(message = "Erro: A data tem que estar preenchida") String data) {
        this.data = data;
    }

    public @NotNull(message = "Erro: O valor tem que estar preenchido") @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que 0.") Float getValor() {
        return valor;
    }

    public void setValor(@NotNull(message = "Erro: O valor tem que estar preenchido") @DecimalMin(value = "0.0", inclusive = false, message = "O valor deve ser maior que 0.") Float valor) {
        this.valor = valor;
    }

    @NotNull(message = "Erro: O tipo tem que estar preenchido")
    public boolean iseReceita() {
        return eReceita;
    }

    public void seteReceita(@NotNull(message = "Erro: O tipo tem que estar preenchido") boolean eReceita) {
        this.eReceita = eReceita;
    }
}
