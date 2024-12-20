package com.jogodavelha3d.model;

public class Cubo3D {
    private EstadoTabuleiro[] cubo;  // 6 faces com 9 células cada
    private final int FACES = 6;
    private final int LINHAS = 3;
    private final int COLUNAS = 3;

    public Cubo3D() {
        cubo = new EstadoTabuleiro[FACES * LINHAS * COLUNAS];
        for (int i = 0; i < cubo.length; i++) {
            cubo[i] = EstadoTabuleiro.VAZIO;
        }
    }

    public void exibirCubo() {
        // Exibir o cubo "desmontado"
        for (int i = 0; i < FACES; i++) {
            System.out.println("Face " + (i + 1) + ":");
            for (int j = 0; j < LINHAS; j++) {
                for (int k = 0; k < COLUNAS; k++) {
                    char simbolo = cubo[i * 9 + j * 3 + k].getSimbolo();
                    System.out.print(simbolo + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public boolean jogar(int face, int linha, int coluna, Jogador jogador) {
        int index = face * 9 + linha * 3 + coluna;
        if (cubo[index] == EstadoTabuleiro.VAZIO) {
            cubo[index] = (jogador == Jogador.JOGADOR1) ? EstadoTabuleiro.JOGADOR1 : EstadoTabuleiro.JOGADOR2;
            return true;
        }
        return false;
    }

    public boolean verificarVitoria() {
        // Verificar vitória nas faces
        for (int i = 0; i < FACES; i++) {
            for (int j = 0; j < LINHAS; j++) {
                // Verificar linhas e colunas em cada face
                if (verificarLinha(i, j) || verificarColuna(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarLinha(int face, int linha) {
        int start = face * 9 + linha * 3;
        return cubo[start] == cubo[start + 1] && cubo[start + 1] == cubo[start + 2] && cubo[start] != EstadoTabuleiro.VAZIO;
    }

    private boolean verificarColuna(int face, int coluna) {
        int start = face * 9 + coluna;
        return cubo[start] == cubo[start + 3] && cubo[start + 3] == cubo[start + 6] && cubo[start] != EstadoTabuleiro.VAZIO;
    }

    public boolean isCompleto() {
        for (int i = 0; i < cubo.length; i++) {
            if (cubo[i] == EstadoTabuleiro.VAZIO) {
                return false;
            }
        }
        return true;
    }
}
