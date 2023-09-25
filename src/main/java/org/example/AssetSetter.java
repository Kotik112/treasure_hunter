package org.example;

import org.example.object.Boot;
import org.example.object.Chest;
import org.example.object.Door;
import org.example.object.Key;

public class AssetSetter {
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		// Key 1
		gp.objects[0] = new Key();
		gp.objects[0].worldX = 23 * gp.tileSize;
		gp.objects[0].worldY = 7 * gp.tileSize;
		
		// Key 2
		gp.objects[1] = new Key();
		gp.objects[1].worldX = 21 * gp.tileSize;
		gp.objects[1].worldY = 41 * gp.tileSize;
		
		// Key 3
		gp.objects[2] = new Key();
		gp.objects[2].worldX = 38 * gp.tileSize;
		gp.objects[2].worldY = 8 * gp.tileSize;
		
		// Door 1
		gp.objects[3] = new Door();
		gp.objects[3].worldX = 10 * gp.tileSize;
		gp.objects[3].worldY = 11 * gp.tileSize;
		
		// Door 2
		gp.objects[4] = new Door();
		gp.objects[4].worldX = 8 * gp.tileSize;
		gp.objects[4].worldY = 28 * gp.tileSize;
		
		// Door 3
		gp.objects[5] = new Door();
		gp.objects[5].worldX = 12 * gp.tileSize;
		gp.objects[5].worldY = 22 * gp.tileSize;
		
		// Chest 1
		gp.objects[6] = new Chest();
		gp.objects[6].worldX = 10 * gp.tileSize;
		gp.objects[6].worldY = 7 * gp.tileSize;
		
		// Boot 1
		gp.objects[7] = new Boot();
		gp.objects[7].worldX = 37 * gp.tileSize;
		gp.objects[7].worldY = 42 * gp.tileSize;
		/*
		// Chest temp
		gp.objects[8] = new Chest();
		gp.objects[8].worldX = 24 * gp.tileSize;
		gp.objects[8].worldY = 22 * gp.tileSize;*/
		
	}
}
