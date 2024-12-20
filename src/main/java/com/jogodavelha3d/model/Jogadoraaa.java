package com.jogodavelha3d.model;

public class Jogadoraaa {

    private String nome;
    private char simbolo; // 'X' ou 'O'
    private int qtdVitorias;

    public Jogadoraaa(String nome, char simbolo) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.qtdVitorias = 0;
    }

    public String getNome() {
        return nome;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public int getQtdVitorias() {
        return qtdVitorias;
    }

    public void incrementarVitoria() {
        this.qtdVitorias++;
    }
}
