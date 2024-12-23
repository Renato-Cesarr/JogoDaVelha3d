package com.jogodavelha3d.main;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.jogodavelha3d.controller.ControleTelas;
import com.jogodavelha3d.view.*;

public class JogoMain {
    public static void main(String[] args) {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            Screen screen = new TerminalScreen(terminalFactory.createTerminal());
            screen.startScreen();

            TextGraphics graphics = screen.newTextGraphics();

            JogoTela.drawWizard(graphics, screen); 

            JogoTela.drawLoadingBar(graphics, screen);

            ControleTelas.displayMenu(graphics, screen);

            screen.stopScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
