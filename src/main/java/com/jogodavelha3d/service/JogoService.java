package com.jogodavelha3d.service;

import java.io.IOException;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.jogodavelha3d.controller.JogoController;
import com.jogodavelha3d.util.AudioPlayer;
import com.jogodavelha3d.util.PointsImageUtils;


public class JogoService {

    private static int pontosX = 0;
    private static int pontosO = 0;

    private static final String AUDIO_X_VITORIA = "/home/renato/Downloads/JogadorX-venceu.wav";
    private static final String AUDIO_O_VITORIA = "/home/renato/Downloads/JogadorOVenceu.wav";
    private static final String AUDIO_RESULTADO_FINAL = "/home/renato/Downloads/final.wav";

    public static void iniciarJogo(Screen screen) throws IOException {
        AudioPlayer.playAudio("/home/renato/Downloads/2024-12-23-01-41-17.wav");
        JogoController controller = new JogoController();

        exibirApresentador(screen, PointsImageUtils.apresentador());

        for (int jogoAtual = 0; jogoAtual < 6; jogoAtual++) {
            controller.resetTabuleiro();
            boolean playerXTurn = true;

            while (controller.isGameRunning()) {
                screen.clear();

                displayTabuleiro(screen, controller);
                exibirApresentador(screen, PointsImageUtils.apresentador());

                displayTurn(screen, playerXTurn ? "X" : "O");

                KeyStroke keyStroke = screen.readInput();

                if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                    controller.moveSelection(-3);
                } else if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                    controller.moveSelection(3);
                } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                    controller.moveSelection(-1);
                } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                    controller.moveSelection(1);
                }

                if (keyStroke.getKeyType() == KeyType.Enter) {
                    if (controller.markCell(playerXTurn ? 'X' : 'O')) {
                        if (controller.checkVictory()) {
                            if (playerXTurn) {
                                pontosX++;
                                AudioPlayer.playAudio(AUDIO_X_VITORIA);  
                            } else {
                                pontosO++;
                                AudioPlayer.playAudio(AUDIO_O_VITORIA);  
                            }
                            displayResult(screen, playerXTurn ? "X" : "O");
                            controller.stopGame();
                        } else if (controller.checkDraw()) {
                            displayResult(screen, "Empate");
                            controller.stopGame();
                        } else {
                            playerXTurn = !playerXTurn;
                        }
                    } else {
                        displayInvalidMove(screen);
                    }
                }
            }
        }

        displayFinalResult(screen);
    }

    private static void displayTabuleiro(Screen screen, JogoController controller) throws IOException {
        char[] tabuleiro = controller.getTabuleiro();
        int selectedCell = controller.getSelectedCell();
        TextGraphics graphics = screen.newTextGraphics();

        int startX = 10;
        int startY = 5;

        graphics.putString(startX - 4, startY - 1, "Jogo da Velha");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int pos = i * 3 + j;
                String cell = String.valueOf(tabuleiro[pos]);

                if (pos == selectedCell) {
                    cell = "[" + cell + "]";
                }

                if (cell.equals("X")) {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
                } else if (cell.equals("O")) {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#0000FF"));
                } else {
                    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
                }

                graphics.putString(startX + (j * 4), startY + (i * 2), cell);

                if (j < 2) {
                    graphics.putString(startX + (j * 4) + 3, startY + (i * 2), "|");
                }
                if (i < 2) {
                    graphics.putString(startX + (j * 4), startY + (i * 2) + 1, "-");
                }
            }
        }

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(startX - 4, startY + 7, "Pressione ENTER para jogar");
        graphics.putString(10, 18, "Pontos de X: " + pontosX + " | Pontos de O: " + pontosO);

        screen.refresh();
    }

    private static void displayTurn(Screen screen, String player) throws IOException {
        screen.newTextGraphics().putString(10, 20, "Vez do jogador: " + player);
        screen.refresh();
    }

    private static void displayResult(Screen screen, String resultado) throws IOException {
        screen.clear();
        screen.newTextGraphics().putString(10, 20, "Resultado: " + resultado);
        screen.refresh();
        screen.readInput();
    }

    private static void displayInvalidMove(Screen screen) throws IOException {
        screen.newTextGraphics().putString(10, 20, "Essa posição já foi! Escolha outra.");
        screen.refresh();
        screen.readInput();
    }

    private static void displayFinalResult(Screen screen) throws IOException {
        screen.clear();
        AudioPlayer.playAudio(AUDIO_RESULTADO_FINAL);
        screen.newTextGraphics().putString(10, 5, "Jogo Finalizado!");
        screen.newTextGraphics().putString(10, 7, "Pontos de X: " + pontosX);
        screen.newTextGraphics().putString(10, 9, "Pontos de O: " + pontosO);
        screen.refresh();
        screen.readInput();
    }
    public static void exibirApresentador(Screen screen, String[] apresentadorSilvio) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        int startX = 50;  
        int startY = 5;

        for (int i = 0; i < apresentadorSilvio.length; i++) {
            graphics.putString(startX, startY + i, apresentadorSilvio[i]);
        }

        screen.refresh();
    }
    
}
