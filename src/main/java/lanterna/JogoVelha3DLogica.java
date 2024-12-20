package lanterna;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import java.io.IOException;

public class JogoVelha3DLogica {

	private static char[] tabuleiro = new char[9];
	private static int selectedCell = 0;
	private static boolean gameRunning = true;
	private static int pontosX = 0;
	private static int pontosO = 0;
	private static int jogoAtual = 0;

	public static void iniciarJogo(Screen screen) throws IOException {
		while (jogoAtual < 6) {
			resetTabuleiro();

			boolean playerXTurn = true;
			while (gameRunning) {
				screen.clear();
				displayTabuleiro(screen);
				displayTurn(playerXTurn ? "X" : "O", screen);

				KeyStroke keyStroke = screen.readInput();

				if (keyStroke.getKeyType() == KeyType.ArrowUp) {
					selectedCell = (selectedCell - 3 + 9) % 9;
				} else if (keyStroke.getKeyType() == KeyType.ArrowDown) {
					selectedCell = (selectedCell + 3) % 9;
				} else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
					selectedCell = (selectedCell - 1 + 9) % 9;
				} else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
					selectedCell = (selectedCell + 1) % 9;
				}

				if (keyStroke.getKeyType() == KeyType.Enter) {
					if (tabuleiro[selectedCell] == ' ') {
						tabuleiro[selectedCell] = playerXTurn ? 'X' : 'O';

						if (checkVictory()) {
							if (playerXTurn) {
								pontosX++;
							} else {
								pontosO++;
							}
							displayResult(screen, playerXTurn ? "X" : "O");
							break;
						}

						if (checkDraw()) {
							displayResult(screen, "Empate");
							break;
						}

						playerXTurn = !playerXTurn;
					} else {
						screen.clear();
						displayTabuleiro(screen);
						screen.newTextGraphics().putString(10, 20, "Essa posição já foi! Escolha outra.");
						screen.refresh();
						screen.readInput();
					}
				}
			}
			jogoAtual++;
		}

		screen.clear();
		screen.newTextGraphics().putString(10, 5, "Jogo Finalizado!");
		screen.newTextGraphics().putString(10, 7, "Pontos de X: " + pontosX);
		screen.newTextGraphics().putString(10, 9, "Pontos de O: " + pontosO);
		screen.refresh();
		screen.readInput();
	}

	private static void resetTabuleiro() {
		for (int i = 0; i < tabuleiro.length; i++) {
			tabuleiro[i] = ' ';
		}
		selectedCell = 0;
		gameRunning = true;
	}

	private static boolean checkVictory() {
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

	private static boolean checkDraw() {
		for (char c : tabuleiro) {
			if (c == ' ') {
				return false;
			}
		}
		return true;
	}

	private static void displayResult(Screen screen, String resultado) throws IOException {
		screen.clear();
		displayTabuleiro(screen);
		screen.newTextGraphics().putString(10, 20, "Resultado: " + resultado);
		screen.refresh();
		screen.readInput();
	}

	public static void displayTabuleiro(Screen screen) throws IOException {
		int startX = 10;
		int startY = 5;

		screen.newTextGraphics().putString(startX - 4, startY - 1, "Jogo da Velha");

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int pos = i * 3 + j;
				String cell = String.valueOf(tabuleiro[pos]);

				if (pos == selectedCell) {
					cell = "[" + cell + "]";
				}

				TextGraphics graphics = screen.newTextGraphics();
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

		screen.newTextGraphics().putString(startX - 4, startY + 7, "Pressione ENTER para jogar");
		screen.newTextGraphics().putString(10, 18, "Pontos de X: " + pontosX + " | Pontos de O: " + pontosO);
		screen.refresh();
	}

	public static void displayTurn(String player, Screen screen) throws IOException {
		screen.newTextGraphics().putString(10, 20, "Vez do jogador: " + player);
		screen.refresh();
	}
}
