package org.example;

import org.example.object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
	GamePanel gp;
	Font font;
	BufferedImage keyImage;
	
	public String message = "";
	long displayDuration = 3000L, displayStartTime = 0L;
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		this.font = new Font("Arial", Font.BOLD, 30);
		Key key = new Key();
		this.keyImage = key.image;
	}
	
	public void draw(Graphics2D g) {
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
		g.drawString("x " + gp.player.hasKeys, 74, 65);
		
		// Draw temp message if it exists
		if(!message.isEmpty() && System.currentTimeMillis() - displayStartTime < displayDuration) {
			FontMetrics fm = g.getFontMetrics();
			int messageWidth = fm.stringWidth(message);
			int centerX = (gp.getWidth() - messageWidth) / 2;
			g.setColor(Color.RED);
			g.drawString(message, centerX, 200);
		} else {
			message = "";
		}
	}
	
	public void showTempMessage(String message, long duration) {
		this.message = message;
		this.displayDuration = duration;
		displayStartTime = System.currentTimeMillis();
		
	}
}
