package org.example.object;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Chest extends SuperObject {
	public Chest() {
		this.name = "chest";
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/chest.png")));
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
