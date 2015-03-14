package dinga_invaders;

import java.awt.image.ImageObserver;

import dinga_invaders.cache.SoundCache;
import dinga_invaders.cache.SpriteCache;

public interface Stage extends ImageObserver {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int SPEED = 15;
	public static final int PLAY_HEIGHT = 550; 

	public SpriteCache getSpriteCache();
	public SoundCache getSoundCache();
	public void addActor(Actor a);
	public Player getPlayer();
	public void gameOver();
	
}
