package com.lembremed.dto;

public class MedicamentoVisionDto {
    private String nome;
    private String dosagem;
    private String observacoes;

    public MedicamentoVisionDto() {}
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
