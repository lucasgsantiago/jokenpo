package com.avaliacao.jokenpo.command.jogador;

public class AdicinarJogadorCommand {
    
    private String nome;
    
    public AdicinarJogadorCommand() {
    }
    
    public AdicinarJogadorCommand(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    
}