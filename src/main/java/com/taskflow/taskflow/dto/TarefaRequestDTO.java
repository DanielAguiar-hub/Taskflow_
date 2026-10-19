package com.taskflow.taskflow.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TarefaRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String titulo;
    @NotNull
    private Boolean concluida;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluido) {
        this.concluida = concluido;
    }
}
