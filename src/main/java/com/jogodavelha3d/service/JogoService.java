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

        JogoController[] controllers = inicializarControladores();
        ControleControll controle = new ControleControll();

        TelaService.exibirApresentador(screen, PointsImageUtils.apresentador());
        int selectedTabuleiro = 0;

        for (int jogoAtual = 0; jogoAtual < controllers.length; jogoAtual++) {
            if (controllers[jogoAtual].isGameOver()) {
                continue;
            }
            executarJogo(screen, controllers, controle, jogoAtual);
        }

        TelaService.displayFinalResulto(screen);
        finalizarTela(screen);
    }

    private static JogoController[] inicializarControladores() {
        JogoController[] controllers = new JogoController[6];
        for (int i = 0; i < controllers.length; i++) {
            controllers[i] = new JogoController();
        }
        return controllers;
    }

    private static void executarJogo(Screen screen, JogoController[] controllers, ControleControll controle, int jogoAtual) throws IOException {
        boolean playerXTurn = true;
        int selectedTabuleiro = 0;

        while (controllers[jogoAtual].GameRodando()) {
            TelaService.displayTabuleiros(screen, controllers, selectedTabuleiro);
            TelaService.exibirApresentador(screen, PointsImageUtils.apresentador());
            TelaService.displayTurnoJOgador(screen, playerXTurn ? "X" : "O");

            selectedTabuleiro = controle.ControlesJogador(screen, controllers, selectedTabuleiro);
            if (controle.EntradaUsuario()) {
                processarJogada(screen, controllers, jogoAtual, selectedTabuleiro, playerXTurn);
                playerXTurn = !playerXTurn;
            }
        }
    }

    private static void processarJogada(Screen screen, JogoController[] controllers, int jogoAtual, int selectedTabuleiro, boolean playerXTurn) throws IOException {
        char jogadorAtual = playerXTurn ? 'X' : 'O';
        JogoController controllerAtual = controllers[selectedTabuleiro];

        if (controllerAtual.marcarCelula(jogadorAtual)) {
            if (controllerAtual.checkVictory()) {
                registrarVitoria(screen, jogadorAtual);
            } else if (controllerAtual.checkDraw()) {
                TelaService.displayResult(screen, "Empate");
            }
        } else {
            TelaService.JogadaInvalida(screen);
        }
    }

    private static void registrarVitoria(Screen screen, char jogadorAtual) throws IOException {
        if (jogadorAtual == 'X') {
            pontosX++;
            AudioPlayer.playAudio(AUDIO_X_VITORIA);
        } else {
            pontosO++;
            AudioPlayer.playAudio(AUDIO_O_VITORIA);
        }
        TelaService.displayResult(screen, String.valueOf(jogadorAtual));
    }

    private static void finalizarTela(Screen screen) throws IOException {
        screen.clear();
        screen.refresh();
    }

}
