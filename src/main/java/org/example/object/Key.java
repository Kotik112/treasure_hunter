package org.example.object;

import javax.imageio.ImageIO;

/**
* Key class that represents a key object that can be picked up in the game.
* The key object is a child class of the SuperObject class and includes functionality
* for managing it's state.
* */
public class Key extends SuperObject {
	/**
	* Constructs a key object with the default name "key" and image.
	* The key object does not have collision by default as it can be picked up.
	 */
	public Key() {
		this.name = "key";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/key.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
