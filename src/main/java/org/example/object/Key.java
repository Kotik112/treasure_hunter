package org.example.object;

import javax.imageio.ImageIO;

public class Key extends SuperObject {
	public Key() {
		this.name = "key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
