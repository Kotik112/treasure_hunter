package org.example.object;

import javax.imageio.ImageIO;

/**
* Door class that represents a door object that can be opened and closed in the game.
* The door object is a child class of the SuperObject class and includes functionality
* for managing it's state.
* */
public class Door extends SuperObject {
	/**
	* Constructs a door object with the default name "door" and image.
	* The door object is set to be collidable by default.
	* */
	public Door() {
		this.name = "door";
		
		try {
			// Load the door image.
			image = ImageIO.read(getClass().getResourceAsStream("/object/door.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		collision = true;
	}

	/**
	* Opens the door by setting the collision to false.
	* */
	public void open() {
		this.collision = false;
	}

	/**
	* Close the door by setting the collision to true.
	* */
	public void close() {
		this.collision = true;
	}
}
