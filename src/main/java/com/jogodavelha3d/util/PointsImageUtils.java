package com.jogodavelha3d.util;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class PointsImageUtils {
	
	public static void desenhoJogo(Screen screen) {
	    TextGraphics graphics = screen.newTextGraphics();
	    int startX = 10;
	    int startY = 5;

	    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF")); 
	    graphics.putString(startX - 4, startY - 4, " _________________________________________________ ");
	    graphics.putString(startX - 4, startY - 3, "| Nintendo  64                                    |");
	    graphics.putString(startX - 4, startY - 2, "| .---------------------------------------------. |");
	    graphics.putString(startX - 4, startY - 1, "| |  .--------------------------------------------.| |");
	    graphics.putString(startX - 4, startY + 0, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 1, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 2, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 3, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 4, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 5, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 6, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 7, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 8, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 9, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 10, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 11, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 12, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 13, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 14, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 15, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 16, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 17, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 18, "| |                                                | |");

	}
	public static void desenhoJogoBaixo(Screen screen) {
	    TextGraphics graphics = screen.newTextGraphics();
	    int startX = 10;
	    int startY = 5;

	    graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
	    graphics.putString(startX - 4, startY + 19, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 20, "| |                                                | |");
	    graphics.putString(startX - 4, startY + 21, "| '-----------------------------------------------'  |");
	    graphics.putString(startX - 4, startY + 22, "|                     ________                       |");
	    graphics.putString(startX - 4, startY + 23, "|        .           (Nintendo)                      |");
	    graphics.putString(startX - 4, startY + 24, "|      _| |_          \"\"\"\"\"\"\"\"               .-.     |");
	    graphics.putString(startX - 4, startY + 25, "|    -[_   _]-                          .-. (   )    |");
	    graphics.putString(startX - 4, startY + 26, "|       |_|                            (   ) '-'     |");
	    graphics.putString(startX - 4, startY + 27, "|        '                              '-'   A      |");
	    graphics.putString(startX - 4, startY + 28, "|                                 ___   ___          |");
	    graphics.putString(startX - 4, startY + 29, "|                                (___) (___)  ,.,    |");
	    graphics.putString(startX - 4, startY + 30, "|                               select start ;:;:    |");
	    graphics.putString(startX - 4, startY + 31, "|                                             ,;:;' /");
	    graphics.putString(startX - 4, startY + 32, "|                                            ,:;:'.'");
	    graphics.putString(startX - 4, startY + 33, "'------------------------------------------------`");
	}

	public static String pointsJogoDaVelha3D() {
        String wizard = ""
                + "          .--,-``-.                                                  \n"
                + "                                                                                                   ,--,      ,---,                      /   /     '.\n"
                + "                                                     ,---,                                       ,--.'|    ,--.' |                     / ../        ;        ,---,\n"
                + "      .--.    ,---.                  ,---.         ,---.'|                                       |  | :    |  |  :                     \\ ``\\  .`-    '     ,---.'|\n"
                + "    .--,`|   '   ,\\    ,----._,.   '   ,\\        |   | :                      .---.            :  : '    :  :  :                      \\___\\/   \\   :     |   | :\n"
                + "    |  |.   /   /   |  /   /  ' /  /   /   |       |   | |    ,--.--.         /.  ./|    ,---.   |  ' |    :  |  |,--.    ,--.--.            \\   :   |     |   | |\n"
                + "    '--`_  .   ; ,. : |   :     | .   ; ,. :     ,--.__| |   /       \\      .-' . ' |   /     \\  '  | |    |  |   /' :  .--.  .-. |          /  /   /    ,--.__| |\n"
                + "    ,--,'| '   | |: : |   | .\\  . '   | |: :    /   ,'   |  .--.  .-. |    /___/ \\: |  /    /  | |  | :    |  |   /' :  .--.  .-. |          \\  \\   \\   /   ,'   |\n"
                + "    |  | ' '   | .; : .   ; ';  | '   | .; :   .   '  /  |   \\__\\/ : . .    .   \\  ' . .    ' / | '  : |__  '  :  | | |   \\__\\/ : .      ___ /   :   | .   '  /  |\n"
                + "    :  | | |   :    | '   .   . | |   :    |   '   ; |:  |   ,\" .--.; |     \\   \\   ' '   ;   /| |  | '.'| |  |  ' | :   ,\" .--.; |     /   /\\   /   : '   ; |:  |\n"
                + "  __|  : '  \\   \\  /   `---`-'| |  \\   \\  /    |   : '/  '  /  /  ,.  |      \\   \\    '   |  / | ;  :    ; |  :  :_:,'  /  /  ,.  |    / ,,/  ',-    . |   | '/  '\n"
                + ".''__ /\\_: |   `----'    .''__ /\\_: |   `----'     |   :    :| ;  :   .'   \\      \\   \\ | |   :    | |  ,   /  |  | ,'     ;  :   .'   \\   \\ ''\\        ;  |   ::|\n"
                + "|   :    :             |   :    :               \\   \\  /   |  ,     .-./       '---\"   \\   \\  /   ---`-'   `--''       |  ,     .-./    \\   \\     .'    \\   \\  /\n"
                + " \\   \\  /               \\   \\  /                 `----'     `--`---'                    `----'                          `--`---'         `--`-,,-'       `----'\n"
                + "  `--`-'                 `--`-'                                                                                                                        \n";
        return wizard;
	}
	public static String[] pointsGame() {
        String[] design = {
                "      _____________ ",
                "     | _\"_________ |",
                "     ||.---------.||",
                "     ||| _|__|__  |||",
                "     ||| _|__|__  |||",
                "     |||  |  |    |||",
                "     ||'---------'/|",
                "     | \"\"\"\"\"\"\"\"\"`  |",
                "     | ||  ^^^  () |",
                "     |[  ]    ()   |",
                "     | ||          |",
                "     |     _ _     |",
                "     |          :::|",
                "     |         .::'/",
                "     '\"\"\"\"\"\"\"\"\"\"\"\"`"
            };
        return design;
	}
	public static String[] apresentador() {
	    String[] apresentadorSilvio = new String[]{
	        "            _.-\"\"\"\"-._",
	        "           /.-......-.\\",
	        "          //          \\\\",
	        "          ||          ||",
	        "          ||.--    --.||",
	        "          /`  @ || @  '\\",
	        "          \\    (__)    /",
	        "           |  ,____,  |",
	        "            \\  `--'  /",
	        "         _./`'.____.'`\\._",
	        "     _.-'  |  \\    /  |  '-._",
	        "   .'       \\  \\  /  /       '.",
	        "  /         |/`\\/`\\|          \\"
	    };
	    return apresentadorSilvio;
	}
	public static String[] apresentadorFalando() {
	    String[] apresentadorSilvio = new String[]{
	        "            _.-\"\"\"\"-._",
	        "           /.-......-.\\",
	        "          //          \\\\",
	        "          ||          ||",
	        "          ||.--    --.||",
	        "          /`  @ || @  '\\",
	        "          \\    (__)    /",
	        "           |  ,_   _,  |",
	        "            \\  `--'  /",
	        "         _./`'.____.'`\\._",
	        "     _.-'  |  \\    /  |  '-._",
	        "   .'       \\  \\  /  /       '.",
	        "  /         |/`\\/`\\|          \\"
	    };
	    return apresentadorSilvio;
	}
}
