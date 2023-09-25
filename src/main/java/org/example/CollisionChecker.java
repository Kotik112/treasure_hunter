package org.example;

import org.example.entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.hitbox.x;
		int entityRightWorldX = entity.worldX + entity.hitbox.x + entity.hitbox.width;
		int entityTopWorldY = entity.worldY + entity.hitbox.y;
		int entityBottomWorldY = entity.worldY + entity.hitbox.y + entity.hitbox.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch (entity.direction) {
			case "up" -> {
				entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
				tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
				if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
			}
			case "down" -> {
				entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
				tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = gp.tm.mapTileNum[entityRightCol][entityBottomRow];
				if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
			}
			case "left" -> {
				entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
				tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tm.mapTileNum[entityLeftCol][entityBottomRow];
				if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
			}
			case "right" -> {
				entityTopRow = (entityTopWorldY + entity.speed) / gp.tileSize;
				tileNum1 = gp.tm.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gp.tm.mapTileNum[entityRightCol][entityTopRow];
				if (gp.tm.tile[tileNum1].collision || gp.tm.tile[tileNum2].collision) {
					entity.collisionOn = true;
				}
			}
			default -> throw new IllegalStateException("Unexpected value: " + entity.direction);
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		int index = -1;
		for (int i = 0; i < gp.objects.length; i++) {
			if (gp.objects[i] != null) {
				// Get entity's hithox position
				entity.hitbox.x = entity.worldX + entity.hitbox.x;
				entity.hitbox.y = entity.worldY + entity.hitbox.y;
				
				// Get object's hitbox position
				gp.objects[i].hitbox.x = gp.objects[i].worldX + gp.objects[i].hitbox.x;
				gp.objects[i].hitbox.y = gp.objects[i].worldY + gp.objects[i].hitbox.y;
				
				switch (entity.direction) {
					case "up" -> {
						entity.hitbox.y -= entity.speed;
						if (entity.hitbox.intersects(gp.objects[i].hitbox)) {
							if (gp.objects[i].collision) {
								entity.collisionOn = true;
							}
							if (player)
								index = i;
						}
					}
					case "down" -> {
						entity.hitbox.y += entity.speed;
						if (entity.hitbox.intersects(gp.objects[i].hitbox)) {
							if (gp.objects[i].collision) {
								entity.collisionOn = true;
							}
							if (player)
								index = i;
						}
					}
					case "left" -> {
						entity.hitbox.x -= entity.speed;
						if (entity.hitbox.intersects(gp.objects[i].hitbox)) {
							if (gp.objects[i].collision) {
								entity.collisionOn = true;
							}
							if (player)
								index = i;
						}
					}
					case "right" -> {
						entity.hitbox.x += entity.speed;
						if (entity.hitbox.intersects(gp.objects[i].hitbox)) {
							if (gp.objects[i].collision) {
								entity.collisionOn = true;
							}
							if (player)
								index = i;
						}
					}
				}
				// Reset entity's hitbox position
				entity.hitbox.x = entity.hitboxAreaDefaultX;
				entity.hitbox.y = entity.hitboxAreaDefaultY;
				gp.objects[i].hitbox.x = gp.objects[i].defaultHitboxX;
				gp.objects[i].hitbox.y = gp.objects[i].defaultHitboxY;
			}
		}
		
		return index;
	}
}
