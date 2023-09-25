package org.example.object;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Chest extends SuperObject {
	public BufferedImage imageOpen;
	public boolean soundPlayed = false;
	public Chest() {
		this.name = "chest";
		try {
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/chest.png")));
			imageOpen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/chest_opened.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.collision = true;
	}
}
