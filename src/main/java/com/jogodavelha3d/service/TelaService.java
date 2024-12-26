package com.jogodavelha3d.service;

import java.io.IOException;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.jogodavelha3d.controller.JogoController;
import com.jogodavelha3d.util.AudioPlayer;
import com.jogodavelha3d.util.PointsImageUtils;

public class TelaService {

    public static void displayTabuleiros(Screen screen, JogoController[] controllers, int selectedTabuleiro) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        int startX = 10;
        int startY = 5;

        graphics.putString(startX - 4, startY - 2, "Jogo da Velha 3D");
        PointsImageUtils.desenhoJogo(screen);

        int offsetXFirst = startX + 12;
        displayTabuleiroUnico(graphics, controllers[0], selectedTabuleiro == 0, offsetXFirst, startY);

        int offsetY = startY + 7;
        for (int j = 0; j < 4; j++) {
            int offsetX = startX + (j * 12);
            displayTabuleiroUnico(graphics, controllers[j + 1], selectedTabuleiro == j + 1, offsetX, offsetY);
        }

        int offsetXLast = startX + 12;
        displayTabuleiroUnico(graphics, controllers[5], selectedTabuleiro == 5, offsetXLast, startY + 13);

        PointsImageUtils.desenhoJogoBaixo(screen);
        graphics.putString(10, 40, "Pontos de X: " + JogoService.pontosX + " | Pontos de O: " + JogoService.pontosO);
        graphics.putString(10, 41, "Pressione ENTER para jogar");
        screen.refresh();
    }

    private static void displayTabuleiroUnico(TextGraphics graphics, JogoController controller, boolean isSelected, int startX, int startY) {
        char[] tabuleiro = controller.getTabuleiro();
        int lugarSelecionado = controller.getSelectedCell();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int pos = i * 3 + j;
                char cellChar = tabuleiro[pos];
                String cell = (cellChar == ' ') ? " " : String.valueOf(cellChar);

                if (cellChar == 'X') {
                    graphics.setForegroundColor(TextColor.ANSI.RED);
                } else if (cellChar == 'O') {
                    graphics.setForegroundColor(TextColor.ANSI.BLUE);
                } else {
                    graphics.setForegroundColor(TextColor.ANSI.WHITE);
                }

                if (isSelected && pos == lugarSelecionado) {
                    cell = "[" + cell + "]";
                }

                int cellX = startX + (j * 4);
                int cellY = startY + (i * 2);
                graphics.putString(cellX, cellY, cell);

                graphics.setForegroundColor(TextColor.ANSI.WHITE);
                if (j < 2) {
                    graphics.putString(startX + (j * 4) + 2, cellY, "|");
                }
                if (i < 2) {
                    graphics.putString(cellX, cellY + 1, "-");
                }
            }
        }
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
    }

    public static void displayTurnoJOgador(Screen screen, String player) throws IOException {
        screen.newTextGraphics().putString(10, 39, "Vez do jogador: " + player);
        screen.refresh();
    }

    public static void displayResult(Screen screen, String resultado) throws IOException {
        screen.clear();
        screen.newTextGraphics().putString(10, 39, "Resultado: " + resultado);
        screen.refresh();
        screen.readInput();
    }

    public static void JogadaInvalida(Screen screen) throws IOException {
        screen.newTextGraphics().putString(10, 39, "Essa posição já foi! Escolha outra.");
        screen.refresh();
        screen.readInput();
    }

    public static void displayFinalResulto(Screen screen) throws IOException {
        screen.clear();
        AudioPlayer.playAudio(JogoService.AUDIO_RESULTADO_FINAL);
        screen.newTextGraphics().putString(10, 5, "Jogo Finalizado!");
        screen.newTextGraphics().putString(10, 7, "Pontos de X: " + JogoService.pontosX);
        screen.newTextGraphics().putString(10, 9, "Pontos de O: " + JogoService.pontosO);
        screen.refresh();
        screen.readInput();
    }

    public static void exibirApresentador(Screen screen, String[] apresentadorSilvio) throws IOException {
        TextGraphics graphics = screen.newTextGraphics();
        int startX = 60;
        int startY = 5;

        for (int i = 0; i < apresentadorSilvio.length; i++) {
            graphics.putString(startX, startY + i, apresentadorSilvio[i]);
        }

        screen.refresh();
    }
}
