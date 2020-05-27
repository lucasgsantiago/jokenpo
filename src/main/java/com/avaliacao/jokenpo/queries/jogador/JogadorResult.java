package com.avaliacao.jokenpo.queries.jogador;

public class JogadorResult {
    private String id ;
    private String nome;
    
    public JogadorResult() {
    }

    public JogadorResult(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}