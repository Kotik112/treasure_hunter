package org.example.object;

import javax.imageio.ImageIO;

public class Chest extends SuperObject {
	public Chest() {
		this.name = "chest";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/chest.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void open() {
		this.collision = false;
	}
	
	public void close() {
		this.collision = true;
	}
}
