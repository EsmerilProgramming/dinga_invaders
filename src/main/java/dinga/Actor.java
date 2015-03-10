package dinga;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dinga.cache.SpriteCache;

public class Actor {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected String spriteName;
	protected Stage stage;
	protected SpriteCache spriteCache;
	protected String[] spriteNames;
	protected int currentFrame;
	protected int frameSpeed;
	protected int t;
	protected boolean markedForRemoval;
	
	public Actor(Stage stage) {
		this.stage = stage;
		spriteCache = stage.getSpriteCache();
		currentFrame = 0;
		frameSpeed = 1;
		t = 0;
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(spriteCache.getSprite(spriteNames[currentFrame]), x, y, stage);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getSpriteName() {
		return spriteName;
	}

	public void act() {
		t++;
		if (t % frameSpeed == 0) {
			t = 0;
			currentFrame = (currentFrame + 1) % spriteNames.length;
		}
	}

	public void setSpriteNames(String[] spriteNames) {
		this.spriteNames = spriteNames;
		height = 0;
		width = 0;
		for (String s : spriteNames) {
			BufferedImage bufferedImage = spriteCache.getSprite(s);
			height = Math.max(height, bufferedImage.getHeight());
			width = Math.max(width, bufferedImage.getWidth());
		}
	}

	public int getFrameSpeed() {
		return frameSpeed;
	}

	public void setFrameSpeed(int frameSpeed) {
		this.frameSpeed = frameSpeed;
	}
	
	public void remove() {
		markedForRemoval = true;
	}
	
	public boolean isMarkedForRemoval() {
		return markedForRemoval;
	}
	
	public void collision(Actor actor) {
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
}
