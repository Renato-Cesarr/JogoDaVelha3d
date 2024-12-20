package com.jogodavelha3d.model;

public enum EstadoTabuleiro {
    VAZIO(' '),
    JOGADOR1('X'),
    JOGADOR2('O');

    private final char simbolo;

    EstadoTabuleiro(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }
}