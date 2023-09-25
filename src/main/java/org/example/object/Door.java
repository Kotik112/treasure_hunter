package org.example.object;

import javax.imageio.ImageIO;

public class Door extends SuperObject {
	public Door() {
		this.name = "door";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/door.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		collision = true;
	}
	
	public void open() {
		this.collision = false;
	}
	
	public void close() {
		this.collision = true;
	}
}
