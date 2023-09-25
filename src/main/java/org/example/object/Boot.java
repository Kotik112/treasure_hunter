package org.example.object;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Boot extends SuperObject {
	public Boot() {
		this.name = "boot";
		
		try {
		// Load the door image.
			image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/object/boots.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
