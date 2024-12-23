package com.jogodavelha3d.controller;

import java.io.IOException;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.jogodavelha3d.util.PointsImageUtils;
import com.jogodavelha3d.model.*;
import com.jogodavelha3d.service.JogoService;

public class ControleTelas {
	
	public static void displayMenu(TextGraphics graphics, Screen screen) throws Exception {
		int selectedOption = 0;
		String[] pointVideoGame = PointsImageUtils.pointsGame();

		while (true) {
			screen.clear();
			graphics.putString(10, 5, "Bem-vindo ao Jogo da Velha 3D!", SGR.BOLD);

			for (int i = 0; i < pointVideoGame.length; i++) {
				graphics.putString(10, 7 + i, pointVideoGame[i], SGR.BOLD);
			}

			for (int i = 0; i < JogoModel.MENU_OPTIONS.length; i++) {
				if (i == selectedOption) {
					graphics.putString(10, 23 + i, JogoModel.MENU_OPTIONS[i], SGR.REVERSE);
				} else {
					graphics.putString(10, 23 + i, JogoModel.MENU_OPTIONS[i]);
				}
			}
			screen.refresh();

			KeyStroke keyStroke = screen.readInput();
			if (keyStroke.getKeyType() == KeyType.ArrowDown) {
				selectedOption = (selectedOption + 1) % JogoModel.MENU_OPTIONS.length;
			} else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
				selectedOption = (selectedOption - 1 + JogoModel.MENU_OPTIONS.length) % JogoModel.MENU_OPTIONS.length;
			} else if (keyStroke.getKeyType() == KeyType.Enter) {
				handleMenuOption(selectedOption, screen, graphics);
				break;
			}
		}
	}

	public static void handleMenuOption(int option, Screen screen, TextGraphics graphics) {
		switch (option) {
		case 0:
			System.out.println("Iniciar Jogo Local...");
			try {
				JogoService.iniciarJogo(screen);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 1:
			System.out.println("Iniciar Jogo LAN...");
			break;
		case 2:
			System.out.println("Visualizar Ranking...");
			break;
		case 3:
			System.out.println("Saindo...");
			System.exit(0);
			break;
		}
	}
}
