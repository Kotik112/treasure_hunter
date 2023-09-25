package org.example.entity;

import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.object.Door;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyHandler;
	int hasKeys = 0;
	public final int screenX, screenY;
	
	public Player(GamePanel gp, KeyHandler keyHandler) {
		this.gp = gp;
		this.keyHandler = keyHandler;
		screenX = gp.screenWidth / 2 - gp.tileSize / 2;
		screenY = gp.screenHeight / 2 - gp.tileSize / 2;
		
		// Defining the hitbox of the player
		hitbox = new Rectangle();
		hitbox.x = 8;
		hitbox.y = 16;
		hitboxAreaDefaultX = hitbox.x;
		hitboxAreaDefaultY = hitbox.y;
		hitbox.width = gp.tileSize - 16;
		hitbox.height = gp.tileSize - 16;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = 23 * gp.tileSize;
		worldY = 21 * gp.tileSize;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		// Animate the player sprite if the player is moving
		if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
			spriteCounter++;
			if (spriteCounter % 12 == 0) {
				spriteNum = (spriteNum == 1) ? 2 : 1;
				spriteCounter = 0;
			}
			
			// Set the direction of the player based on the key pressed
			if (keyHandler.upPressed) {
				direction = "up";
			}
			if (keyHandler.downPressed) {
				direction = "down";
			}
			if (keyHandler.leftPressed) {
				direction = "left";
			}
			if (keyHandler.rightPressed) {
				direction = "right";
			}
			
			// Check tile collision
			collisionOn = false;
			gp.cc.checkTile(this);
			// Check object collision
			int objectIndex = gp.cc.checkObject(this, true);
			pickUpObject(objectIndex);
			
			// If collision is false, the player can move
			if (!collisionOn) {
				switch (direction) {
					case "up" -> worldY -= speed;
					case "down" -> worldY += speed;
					case "left" -> worldX -= speed;
					case "right" -> worldX += speed;
				}
			}
		}
	}
	
	public void pickUpObject (int index) {
		if (index != -1) {
			String objectName = gp.objects[index].name;
			switch (objectName) {
				case "key" -> {
					hasKeys++;
					gp.objects[index] = null;
					System.out.println("Player has " + hasKeys + " keys");
				}
				case "door" -> {
					Door door = (Door) gp.objects[index];
					if (door.collision) {
						if (hasKeys > 0) {
							door.open();
							hasKeys--;
							System.out.println("Player has " + hasKeys + " keys");
						}
					}
				}
				case "chest" -> {
					gp.objects[index] = null;
					System.out.println("Player has opened a chest");
				}
			}
			
		}
		
		
	}
	
	public void draw(Graphics2D g) {
		/*graphics2D.setColor(Color.WHITE);
		graphics2D.fillRect(x, y, gp.tileSize, gp.tileSize);*/
		
		BufferedImage image = null;
		switch (direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;
				} else {
					image = up2;
				}
				
				break;
			case "down":
				if (spriteNum == 1) {
					image = down1;
				} else {
					image = down2;
				}
				break;
				
			case "left":
				if (spriteNum == 1) {
					image = left1;
				} else {
					image = left2;
				}
				break;
				
			case "right":
				if (spriteNum == 1) {
					image = right1;
				} else {
					image = right2;
				}
				break;
		}

		g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}
