package com.jogodavelha3d.service;

import java.io.IOException;
import com.googlecode.lanterna.screen.Screen;
import com.jogodavelha3d.controller.ControleControll;
import com.jogodavelha3d.controller.JogoController;
import com.jogodavelha3d.util.AudioPlayer;
import com.jogodavelha3d.util.PointsImageUtils;

public class JogoService {

    public static int pontosX = 0;
    public static int pontosO = 0;

    private static final String AUDIO_X_VITORIA = "/home/renato/Downloads/JogadorX-venceu.wav";
    private static final String AUDIO_O_VITORIA = "/home/renato/Downloads/JogadorOVenceu.wav";
    static final String AUDIO_RESULTADO_FINAL = "/home/renato/Downloads/final.wav";

    public static void iniciarJogo(Screen screen) throws IOException {
        AudioPlayer.playAudio("/home/renato/Downloads/2024-12-23-01-41-17.wav");

        JogoController[] controllers = new JogoController[6];
        for (int i = 0; i < 6; i++) {
            controllers[i] = new JogoController();
        }

        ControleControll controle = new ControleControll();
        TelaService.exibirApresentador(screen, PointsImageUtils.apresentador());
        int selectedTabuleiro = 0;

        for (int jogoAtual = 0; jogoAtual < 6; jogoAtual++) {
            if (controllers[jogoAtual].isGameOver()) {
                continue;
            }

            boolean playerXTurn = true;

            while (controllers[jogoAtual].GameRodando()) {
                screen.clear();
                TelaService.displayTabuleiros(screen, controllers, selectedTabuleiro);
                TelaService.exibirApresentador(screen, PointsImageUtils.apresentador());
                TelaService.displayTurnoJOgador(screen, playerXTurn ? "X" : "O");

                selectedTabuleiro = controle.ControlesJogador(screen, controllers, selectedTabuleiro);

                if (controle.EntradaUsuario()) {
                    if (controllers[selectedTabuleiro].marcarCelula(playerXTurn ? 'X' : 'O')) {
                        if (controllers[selectedTabuleiro].checkVictory()) {
                            pontosX += playerXTurn ? 1 : 0;
                            pontosO += playerXTurn ? 0 : 1;
                            AudioPlayer.playAudio(playerXTurn ? AUDIO_X_VITORIA : AUDIO_O_VITORIA);
                            TelaService.displayResult(screen, playerXTurn ? "X" : "O");
                        } else if (controllers[selectedTabuleiro].checkDraw()) {
                            TelaService.displayResult(screen, "Empate");
                        } else {
                            playerXTurn = !playerXTurn;
                        }
                    } else {
                        TelaService.JogadaInvalida(screen);
                    }
                }
            }
        }

        TelaService.displayFinalResulto(screen);
        screen.clear();
        screen.refresh();
    }
}
