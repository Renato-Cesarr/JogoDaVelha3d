
package com.jogodavelha3d.controller;

public class JogoController {
	private char[] tabuleiro;
	private int selectedCell;
	private boolean gameRunning;
	private boolean gameOver;

	public JogoController() {
		resetTabuleiro();
	}

	public void resetTabuleiro() {
		tabuleiro = new char[9];
		for (int i = 0; i < tabuleiro.length; i++) {
			tabuleiro[i] = ' ';
		}
		selectedCell = 0;
		gameRunning = true;
	}

	public boolean GameRodando() {
		return gameRunning;
	}

	public void pararGame() {
		gameRunning = false;
	}

	public char[] getTabuleiro() {
		return tabuleiro;
	}

	public int getSelectedCell() {
		return selectedCell;
	}

	public void moveSelection(int offset) {
		selectedCell = (selectedCell + offset + 9) % 9;
	}

	public boolean marcarCelula(char player) {
		if (!gameRunning || tabuleiro[selectedCell] != ' ') {
			return false;
		}

		tabuleiro[selectedCell] = player;

		if (checkVictory() || checkDraw()) {
			gameRunning = false;
			gameOver = true;
		}

		return true;
	}

	public boolean checkDraw() {
		for (char cell : tabuleiro) {
			if (cell == ' ') {
				return false;
			}
		}
		return true;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public boolean checkVictory() {
		int[][] winningCombinations = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 },
				{ 0, 4, 8 }, { 2, 4, 6 } };
		for (int[] combination : winningCombinations) {
			if (tabuleiro[combination[0]] != ' ' && tabuleiro[combination[0]] == tabuleiro[combination[1]]
					&& tabuleiro[combination[1]] == tabuleiro[combination[2]]) {
				return true;
			}
		}
		return false;
	}
}
