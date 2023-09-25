package org.example.object;

import org.example.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle hitbox = new Rectangle(0,0,48,48);
	public int defaultHitboxX = 0, defaultHitboxY = 0;
	
	public void draw(Graphics2D g, GamePanel gp) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
				    worldX - gp.tileSize  < gp.player.worldX + gp.player.screenX &&
				    worldY + gp.tileSize  > gp.player.worldY - gp.player.screenY &&
				    worldY - gp.tileSize  < gp.player.worldY + gp.player.screenY
		) {
			g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}
}
