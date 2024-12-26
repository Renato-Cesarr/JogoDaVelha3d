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

        boolean continuar = true;

        while (continuar) {
            screen.clear();
            exibirTitulo(graphics);
            exibirPontos(graphics, pointVideoGame);
            exibirMenu(graphics, selectedOption);

            screen.refresh();
            KeyStroke keyStroke = screen.readInput();
            selectedOption = processarNavegacao(keyStroke, selectedOption);

            if (keyStroke.getKeyType() == KeyType.Enter) {
                MenuOpcoes(selectedOption, screen, graphics);
                continuar = false;
            }
        }
    }

    private static void exibirTitulo(TextGraphics graphics) {
        graphics.putString(10, 5, "Bem-vindo ao Jogo da Velha 3D!", SGR.BOLD);
    }

    private static void exibirPontos(TextGraphics graphics, String[] pointVideoGame) {
        for (int i = 0; i < pointVideoGame.length; i++) {
            graphics.putString(10, 7 + i, pointVideoGame[i], SGR.BOLD);
        }
    }

    private static void exibirMenu(TextGraphics graphics, int selectedOption) {
        for (int i = 0; i < JogoModel.MENU_OPTIONS.length; i++) {
            SGR sgr = (i == selectedOption) ? SGR.REVERSE : SGR.BOLD;
            graphics.putString(10, 23 + i, JogoModel.MENU_OPTIONS[i], sgr);
        }
    }

    private static int processarNavegacao(KeyStroke keyStroke, int selectedOption) {
        if (keyStroke.getKeyType() == KeyType.ArrowDown) {
            return (selectedOption + 1) % JogoModel.MENU_OPTIONS.length;
        } else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
            return (selectedOption - 1 + JogoModel.MENU_OPTIONS.length) % JogoModel.MENU_OPTIONS.length;
        }
        return selectedOption;
    }

    public static void MenuOpcoes(int option, Screen screen, TextGraphics graphics) {
        switch (option) {
            case 0:
                iniciarJogoLocal(screen);
                break;
            case 3:
                System.out.println("Saindo...");
                System.exit(0);
                break;
        }
    }

    private static void iniciarJogoLocal(Screen screen) {
        System.out.println("Iniciar Jogo Local...");
        try {
            JogoService.iniciarJogo(screen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
