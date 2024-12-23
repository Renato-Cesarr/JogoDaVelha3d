package com.jogodavelha3d.view;

import java.io.IOException;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.jogodavelha3d.util.*;

public class JogoTela {

	public static void drawWizard(TextGraphics graphics, Screen screen) throws InterruptedException, IOException {
		String logoJogo = PointsImageUtils.pointsJogoDaVelha3D();

		graphics.putString(10, 5, "Bem-vindo ao Jogo da Velha 3D!", SGR.BOLD);
		graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
		int y = 8;
		for (String line : logoJogo.split("\\n")) {
			graphics.putString(10, y++, line);
		}
		screen.refresh();
		Thread.sleep(2000);
	}

	public static void drawLoadingBar(TextGraphics graphics, Screen screen) throws InterruptedException, IOException {
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

			screen.refresh();
			Thread.sleep(100);
		}

		graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
		graphics.putString(startX, startY, "■".repeat(barLength));
		screen.refresh();
	}

	
}
