package org.example;

import org.example.entity.Player;
import org.example.object.SuperObject;
import org.example.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
	// SCREEN SETTINGS
	final int originalTileSize = 16;    // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;  // 48x48 tiles
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	
	public int screenWidth = tileSize * maxScreenCol;  // 768 px
	public int screenHeight = tileSize * maxScreenRow;  // 576 px
	
	TileManager tm = new TileManager(this);
	KeyHandler keyHandler = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cc = new CollisionChecker(this);
	public AssetSetter as = new AssetSetter(this);
	public Player player = new Player(this, keyHandler);
	private final int MAX_OBJECTS = 10;
	public SuperObject[] objects = new SuperObject[MAX_OBJECTS];
	
	// World settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// Game loop settings
	int fps = 60;
	
	// Sound class
	Sound sound = new Sound();
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		this.requestFocus();
	}
	
	public void setupGame() {
		as.setObject();
		playMusic(0);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		double delay = (double) 1_000_000_000 / fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long now;

		
		while (gameThread != null) {
			now = System.nanoTime();
			delta += (now - lastTime) / delay;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				// Update swing component
				repaint();
				//SwingUtilities.invokeLater(this::repaint);
				delta--;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		tm.draw(g2);
		for (SuperObject object : objects) {
			if (object != null) {
				object.draw(g2, this);
			}
		}
		
		player.draw(g2);
		g2.dispose();
	}
	
	/**
	 * Plays a music track in a loop..
	 * Index summary:
	 * 0 = Player walking
	 * 1 = Coin Sound
	 * 2 = Level Up Sound
	 * 3 = Unlock Sound
	 * 4 = Fanfare Sound
	 *
	 * @param soundIndex The index of the sound to be played.
	 * */
	public void playMusic(int soundIndex) {
		sound.setFile(soundIndex);
		sound.play();
		sound.loop();
	}
	
	/**
	 * Stops the currently playing music track.
	 * */
	public void stopMusic() {
		sound.stop();
	}
	
/**
	 * Plays a sound effect.
	 * Index summary:
	 * 0 = Player walking
	 * 1 = Coin Sound
	 * 2 = Level Up Sound
	 * 3 = Unlock Sound
	 * 4 = Fanfare Sound
	 *
	 * @param soundIndex The index of the sound to be played.
	 * */
	public void playSoundEffect(int soundIndex) {
		sound.setFile(soundIndex);
		sound.play();
	}
}

