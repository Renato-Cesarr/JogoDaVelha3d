package com.jogodavelha3d.controller;

public class JogoController {

    private char[] tabuleiro = new char[9];
    private int selectedCell = 0;
    private boolean gameRunning = true;

    public char[] getTabuleiro() {
        return tabuleiro;
    }

    public int getSelectedCell() {
        return selectedCell;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void resetTabuleiro() {
        for (int i = 0; i < tabuleiro.length; i++) {
            tabuleiro[i] = ' ';
        }
        selectedCell = 0;
        gameRunning = true;
    }

    public boolean checkVictory() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i * 3] == tabuleiro[i * 3 + 1] && tabuleiro[i * 3 + 1] == tabuleiro[i * 3 + 2]
                    && tabuleiro[i * 3] != ' ') {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i] == tabuleiro[i + 3] && tabuleiro[i + 3] == tabuleiro[i + 6] && tabuleiro[i] != ' ') {
                return true;
            }
        }
        if (tabuleiro[0] == tabuleiro[4] && tabuleiro[4] == tabuleiro[8] && tabuleiro[0] != ' ') {
            return true;
        }
        if (tabuleiro[2] == tabuleiro[4] && tabuleiro[4] == tabuleiro[6] && tabuleiro[2] != ' ') {
            return true;
        }
        return false;
    }

    public boolean checkDraw() {
        for (char c : tabuleiro) {
            if (c == ' ') {
                return false;
            }
        }
        return true;
    }

    public boolean markCell(char player) {
        if (tabuleiro[selectedCell] == ' ') {
            tabuleiro[selectedCell] = player;
            return true;
        }
        return false;
    }

    public void moveSelection(int offset) {
        selectedCell = (selectedCell + offset + 9) % 9;
    }

    public void stopGame() {
        gameRunning = false;
    }
}
