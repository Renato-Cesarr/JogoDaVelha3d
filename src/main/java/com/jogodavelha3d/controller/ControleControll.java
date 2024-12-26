package com.jogodavelha3d.controller;

import java.io.IOException;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class ControleControll {

    private boolean EntradaPressionada = false;

    public int ControlesJogador(Screen screen, JogoController[] controllers, int selectedTabuleiro) throws IOException {
        KeyStroke keyStroke = screen.readInput();
        EntradaPressionada = false;

        if (keyStroke.getKeyType() == KeyType.Character) {
            char c = keyStroke.getCharacter();
            if ((c == 'w' || c == 'W') && selectedTabuleiro > 0) selectedTabuleiro--;
            else if ((c == 's' || c == 'S') && selectedTabuleiro < 5) selectedTabuleiro++;
            else if ((c == 'a' || c == 'A') && selectedTabuleiro % 3 != 0) selectedTabuleiro--;
            else if ((c == 'd' || c == 'D') && selectedTabuleiro % 3 != 2) selectedTabuleiro++;
        } else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
            controllers[selectedTabuleiro].moveSelection(-3);
        } else if (keyStroke.getKeyType() == KeyType.ArrowDown) {
            controllers[selectedTabuleiro].moveSelection(3);
        } else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
            controllers[selectedTabuleiro].moveSelection(-1);
        } else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
            controllers[selectedTabuleiro].moveSelection(1);
        } else if (keyStroke.getKeyType() == KeyType.Enter) {
            EntradaPressionada = true;
        }

        return selectedTabuleiro;
    }

    public boolean EntradaUsuario() {
        return EntradaPressionada;
    }
}
