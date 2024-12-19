package lanterna;

import java.io.IOException;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class JogoVelha3D {

    private static final String[] MENU_OPTIONS = {
            "1. Jogar Local",
            "2. Jogar LAN",
            "3. Ranking",
            "4. Sair"
    };

    public static void main(String[] args) {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            Screen screen = new TerminalScreen(terminalFactory.createTerminal());
            screen.startScreen();

            TextGraphics graphics = screen.newTextGraphics();
            
            drawWizard(graphics, screen);
            drawLoadingBar(graphics, screen);

            displayMenu(graphics, screen);

            screen.stopScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void drawWizard(TextGraphics graphics, Screen screen) throws InterruptedException, IOException {
    	String wizard = ""
    	        + "          .--,-``-.                                                  \n"
    	        + "                                                                                                   ,--,      ,---,                      /   /     '.\n"
    	        + "                                                     ,---,                                       ,--.'|    ,--.' |                     / ../        ;        ,---,\n"
    	        + "      .--.    ,---.                  ,---.         ,---.'|                                       |  | :    |  |  :                     \\ ``\\  .`-    '     ,---.'|\n"
    	        + "    .--,`|   '   ,\\    ,----._,.   '   ,\\        |   | :                      .---.            :  : '    :  :  :                      \\___\\/   \\   :     |   | :\n"
    	        + "    |  |.   /   /   |  /   /  ' /  /   /   |       |   | |    ,--.--.         /.  ./|    ,---.   |  ' |    :  |  |,--.    ,--.--.            \\   :   |     |   | |\n"
    	        + "    '--`_  .   ; ,. : |   :     | .   ; ,. :     ,--.__| |   /       \\      .-' . ' |   /     \\  '  | |    |  :  '   |   /       \\           /  /   /    ,--.__| |\n"
    	        + "    ,--,'| '   | |: : |   | .\\  . '   | |: :    /   ,'   |  .--.  .-. |    /___/ \\: |  /    /  | |  | :    |  |   /' :  .--.  .-. |          \\  \\   \\   /   ,'   |\n"
    	        + "    |  | ' '   | .; : .   ; ';  | '   | .; :   .   '  /  |   \\__\\/ : . .    .   \\  ' . .    ' / | '  : |__  '  :  | | |   \\__\\/ : .      ___ /   :   | .   '  /  |\n"
    	        + "    :  | | |   :    | '   .   . | |   :    |   '   ; |:  |   ,\" .--.; |     \\   \\   ' '   ;   /| |  | '.'| |  |  ' | :   ,\" .--.; |     /   /\\   /   : '   ; |:  |\n"
    	        + "  __|  : '  \\   \\  /   `---`-'| |  \\   \\  /    |   | '/  '  /  /  ,.  |      \\   \\    '   |  / | ;  :    ; |  :  :_:,'  /  /  ,.  |    / ,,/  ',-    . |   | '/  '\n"
    	        + ".''__ /\\_: |   `----'    .''__ /\\_: |   `----'     |   :    :| ;  :   .'   \\      \\   \\ | |   :    | |  ,   /  |  | ,'     ;  :   .'   \\   \\ ''\\        ;  |   :    :|\n"
    	        + "|   :    :             |   :    :               \\   \\  /   |  ,     .-./       '---\"   \\   \\  /   ---`-'   `--''       |  ,     .-./    \\   \\     .'    \\   \\  /\n"
    	        + " \\   \\  /               \\   \\  /                 `----'     `--`---'                    `----'                          `--`---'         `--`-,,-'       `----'\n"
    	        + "  `--`-'                 `--`-'                                                                                                                        \n";


        graphics.putString(10, 5, "Bem-vindo ao Jogo da Velha 3D!", SGR.BOLD);
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        int y = 8;
        for (String line : wizard.split("\\n")) {
            graphics.putString(10, y++, line);
        }
        screen.refresh();
        Thread.sleep(2000);
    }

    private static void drawLoadingBar(TextGraphics graphics, Screen screen) throws InterruptedException, IOException {
        int barLength = 100;  
        int startX = 10;     
        int startY = 27;     
        String loadingText = "Carregando...";

        graphics.putString(startX, startY - 2, loadingText);

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.putString(startX, startY, "■".repeat(barLength)); 

        for (int i = 1; i <= barLength; i++) {
            StringBuilder progressBar = new StringBuilder();

            for (int j = 0; j < i; j++) {
                progressBar.append("■");  
            }

            for (int j = i; j < barLength; j++) {
                progressBar.append("□");  
            }

            graphics.putString(startX, startY, progressBar.toString());
            
            if (i <= barLength / 4) {
                graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));  
            } else if (i <= barLength / 2) {
                graphics.setForegroundColor(TextColor.Factory.fromString("#FF8000")); 
            } else if (i <= 3 * barLength / 4) {
                graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));  
            } else {
                graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));  
            }
            
            graphics.putString(startX, startY, progressBar.toString());
            screen.refresh();

            Thread.sleep(100);  
        }

        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        graphics.putString(startX, startY, "■".repeat(barLength));
        screen.refresh();
    }

    private static void displayMenu(TextGraphics graphics, Screen screen) throws Exception {
        int selectedOption = 0;
        while (true) {
            screen.clear();
            graphics.putString(10, 5, "Bem-vindo ao Jogo da Velha 3D!", SGR.BOLD);
            for (int i = 0; i < MENU_OPTIONS.length; i++) {
                if (i == selectedOption) {
                    graphics.putString(10, 8 + i, MENU_OPTIONS[i], SGR.REVERSE);
                } else {
                    graphics.putString(10, 8 + i, MENU_OPTIONS[i]);
                }
            }
            screen.refresh();

            KeyStroke keyStroke = screen.readInput();
            if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                selectedOption = (selectedOption + 1) % MENU_OPTIONS.length;
            } else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                selectedOption = (selectedOption - 1 + MENU_OPTIONS.length) % MENU_OPTIONS.length;
            } else if (keyStroke.getKeyType() == KeyType.Enter) {
                if (selectedOption == MENU_OPTIONS.length - 1) {
                    break;
                }
           
            }
        }
    }
}
