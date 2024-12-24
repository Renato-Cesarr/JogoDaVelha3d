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

	    JogoController[] controllers = new JogoController[6];
		exibirApresentador(screen, PointsImageUtils.apresentador());

	    for (int i = 0; i < 6; i++) {
	        controllers[i] = new JogoController(); 
	    }

	    int selectedTabuleiro = 0;
	    for (int jogoAtual = 0; jogoAtual < 6; jogoAtual++) {
	        if (!controllers[jogoAtual].isGameOver()) {
	            controllers[jogoAtual].resetTabuleiro();
	        }

	        boolean playerXTurn = true;

	        while (controllers[jogoAtual].isGameRunning()) {
	            screen.clear();
	            displayTabuleiros(screen, controllers, selectedTabuleiro); 
				exibirApresentador(screen, PointsImageUtils.apresentador());
	            displayTurn(screen, playerXTurn ? "X" : "O");

	            KeyStroke keyStroke = screen.readInput();

	            if (keyStroke.getKeyType() == KeyType.Character) {
	                char c = keyStroke.getCharacter();
	                if (c == 'w' || c == 'W') {
	                    if (selectedTabuleiro > 0) {
	                        selectedTabuleiro--;
	                    }
	                } else if (c == 's' || c == 'S') {
	                    if (selectedTabuleiro < 5) {
	                        selectedTabuleiro++;
	                    }
	                } else if (c == 'a' || c == 'A') {
	                    if (selectedTabuleiro % 3 != 0) {
	                        selectedTabuleiro--; 
	                    }
	                } else if (c == 'd' || c == 'D') {
	                    if (selectedTabuleiro % 3 != 2) {
	                        selectedTabuleiro++; 
	                    }
	                }
	            }

	            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
	                controllers[selectedTabuleiro].moveSelection(-3);
	            } else if (keyStroke.getKeyType() == KeyType.ArrowDown) {
	                controllers[selectedTabuleiro].moveSelection(3);
	            } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
	                controllers[selectedTabuleiro].moveSelection(-1);
	            } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
	                controllers[selectedTabuleiro].moveSelection(1);
	            }

	            if (keyStroke.getKeyType() == KeyType.Enter) {
	                if (controllers[selectedTabuleiro].markCell(playerXTurn ? 'X' : 'O')) {
	                    if (controllers[selectedTabuleiro].checkVictory()) {
	                        if (playerXTurn) {
	                            pontosX++;
	                            AudioPlayer.playAudio(AUDIO_X_VITORIA);
	                        } else {
	                            pontosO++;
	                            AudioPlayer.playAudio(AUDIO_O_VITORIA);
	                        }
	                        displayResult(screen, playerXTurn ? "X" : "O");
	                        controllers[selectedTabuleiro].stopGame();
	                        controllers[selectedTabuleiro].setGameOver();
	                    } else if (controllers[selectedTabuleiro].checkDraw()) {
	                        displayResult(screen, "Empate");
	                        controllers[selectedTabuleiro].stopGame();
	                        controllers[selectedTabuleiro].setGameOver();
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
	    screen.clear();
	    screen.refresh();
	}


	private static void displayTabuleiros(Screen screen, JogoController[] controllers, int selectedTabuleiro) throws IOException {
	    TextGraphics graphics = screen.newTextGraphics();
	    int startX = 10;
	    int startY = 5;

	    graphics.putString(startX - 4, startY - 2, "Jogo da Velha 3D");
	    PointsImageUtils.desenhoJogo(screen);

	    int offsetXFirst = startX + 12;
	    displaySingleTabuleiro(graphics, controllers[0], selectedTabuleiro == 0, offsetXFirst, startY);

	    int offsetY = startY + 7;
	    for (int j = 0; j < 4; j++) {
	        int offsetX = startX + (j * 12);
	        displaySingleTabuleiro(graphics, controllers[j + 1], selectedTabuleiro == j + 1, offsetX, offsetY);
	    }

	    int offsetXLast = startX + 12;
	    displaySingleTabuleiro(graphics, controllers[5], selectedTabuleiro == 5, offsetXLast, startY + 13);
	    
	    PointsImageUtils.desenhoJogoBaixo(screen);
	    graphics.putString(10, 40, "Pontos de X: " + pontosX + " | Pontos de O: " + pontosO);
	    graphics.putString(10, 41, "Pressione ENTER para jogar");
	    screen.refresh();
	}




	private static void displaySingleTabuleiro(TextGraphics graphics, JogoController controller, boolean isSelected, int startX, int startY) {
	    char[] tabuleiro = controller.getTabuleiro();
	    int selectedCell = controller.getSelectedCell();

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

	            if (isSelected && pos == selectedCell) {
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



	private static void displayTurn(Screen screen, String player) throws IOException {
		screen.newTextGraphics().putString(10, 39, "Vez do jogador: " + player);
		screen.refresh();
	}

	private static void displayResult(Screen screen, String resultado) throws IOException {
		screen.clear();
		screen.newTextGraphics().putString(10, 39, "Resultado: " + resultado);
		screen.refresh();
		screen.readInput();
	}

	private static void displayInvalidMove(Screen screen) throws IOException {
		screen.newTextGraphics().putString(10, 39, "Essa posição já foi! Escolha outra.");
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
		int startX = 60;
		int startY = 5;

		for (int i = 0; i < apresentadorSilvio.length; i++) {
			graphics.putString(startX, startY + i, apresentadorSilvio[i]);
		}

		screen.refresh();
	}

}