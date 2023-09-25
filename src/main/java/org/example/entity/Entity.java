package org.example.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Entity class that represents an entity object in the game.
 * The entity object is a parent class of the Player and Enemy/Monster classes.
 * */
public class Entity {
	public int worldX, worldY;
	public int speed;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle hitbox;
	public int hitboxAreaDefaultX, hitboxAreaDefaultY;
	public boolean collisionOn = false;
}
