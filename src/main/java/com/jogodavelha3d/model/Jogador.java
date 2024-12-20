package com.jogodavelha3d.model;

public enum Jogador {
    JOGADOR1('X'),
    JOGADOR2('O');

    private final char simbolo;

    Jogador(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }
}