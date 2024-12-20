package com.jogodavelha3d.controller;
import com.jogodavelha3d.model.*;
public class GameController {
    private Cubo3D cubo;
    private Jogador jogadorAtual;
    private boolean jogoFinalizado;

    public GameController() {
        this.cubo = new Cubo3D();
        this.jogadorAtual = Jogador.JOGADOR1;  // Começa com o Jogador 1
        this.jogoFinalizado = false;
    }

    public void iniciarJogo() {
        while (!jogoFinalizado) {
            cubo.exibirCubo(); // Exibe o cubo "desmontado"
            boolean jogadaValida = fazerJogada();
            if (jogadaValida) {
                if (cubo.verificarVitoria()) {
                    System.out.println("O jogador " + jogadorAtual + " venceu!");
                    jogoFinalizado = true;
                } else if (cubo.isCompleto()) {
                    System.out.println("Empate!");
                    jogoFinalizado = true;
                } else {
                    alternarJogador();
                }
            }
        }
    }

    private boolean fazerJogada() {
        // Lógica para pegar a jogada do jogador e processá-la
        System.out.println("Vez de " + jogadorAtual + " - Escolha uma face (0-5), linha (0-2) e coluna (0-2): ");
        // Aqui você pode pedir a entrada do jogador e verificar se é válida
        int face = 0; // Exemplo de valor
        int linha = 0; // Exemplo de valor
        int coluna = 0; // Exemplo de valor

        return cubo.jogar(face, linha, coluna, jogadorAtual);
    }

    private void alternarJogador() {
        jogadorAtual = (jogadorAtual == Jogador.JOGADOR1) ? Jogador.JOGADOR2 : Jogador.JOGADOR1;
    }
}
