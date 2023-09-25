package org.example.entity;

import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.object.Door;
import org.example.object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Player class that represents a player entity in the game. The player object is a child class of the Entity class.
 * The player object includes functionality for managing the player's state. The player object also includes
 * functionality for managing the player's sprite animation and collision.
 * */
public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyHandler;
	public final int screenX, screenY;
	//Player Inventory
	public List<SuperObject> inventory;
	int hasKeys = 0;

	/**
	 * Constructs a new player object with the default screenX and screenY values.
	 * This constructor initializes the player object with the provided GamePanel and KeyHandler.
	 * It also sets the initial screen position of the player and defines the player's hitbox.
	 *
	 * @param gp the GamePanel object that the player object is in.
	 * @param keyHandler the KeyHandler object that the player object is in.

	 * */
	public Player(GamePanel gp, KeyHandler keyHandler) {
		inventory = new ArrayList<>();
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

	/**
	 * The update method is called by the GamePanel class in the game loop. This method is used to update the
	 * player's state and animation based on the player's input. This method also checks for collision with
	 * "collision" tiles and objects. The player's position is updated if there is no collision.
	 * */
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

	/**
	 * Method responsible for the logic of picking up an object. This method is called by the update method.
	 *
	 * @param index The index of the object in the objects array (GamePanel class). If the index is -1, then
	 *              there is no object to pick up.
	 * WIP: This method is incomplete and will be updated in the future.
	 * */
	public void pickUpObject (int index) {
		if (index != -1) {
			String objectName = gp.objects[index].name;
			switch (objectName) {
				case "key" -> {
					hasKeys++;
					inventory.add(gp.objects[index]);
					gp.objects[index] = null;
				}
				case "door" -> {
					Door door = (Door) gp.objects[index];
					if (door.collision) {
						if (hasKeys > 0) {
							door.open();
							hasKeys--;
							// inventory.add(gp.objects[index]); // Doors should not go into inventory
							gp.objects[index] = null;
						}
					}
				}
				case "chest" ->
					gp.objects[index] = null;

			}
			System.out.println("Player inventory: " + inventory);
		}
	}

	/**
	 * Draw method is responsible for drawing the player entity on the screen. This method is called by the
	 * GamePanel class in the game loop.
	 *
	 * @param g The Graphics2D object that is used to draw the player entity.
	 * */
	public void draw(Graphics2D g) {
		BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
            }
        }

		g.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

	/**
	 * Private method that sets the default values of the player object. This method is called by the constructor.
	 * */
	private void setDefaultValues() {
		worldX = 23 * gp.tileSize;
		worldY = 21 * gp.tileSize;
		speed = 4;
		direction = "down";
	}

	/**
	 * Private method that loads the player's sprite images. This method is called by the constructor.
	 * */
	private void getPlayerImage() {
		try {
			up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
			up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
			down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
			down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
			left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
			left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
			right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
			right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
