package dinga;

import java.awt.event.KeyEvent;

public class Player extends Actor {

	public static final int MAX_SHIELDS = 200;
	protected static final int PLAYER_SPEED = 4;
	protected int vx;
	protected int vy;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	private int score;
	private int shields;
	private int clusterBombs;
	
	public Player(Stage stage) {
		super(stage);
		setSpriteNames(new String[]{"dinga.gif"});
		clusterBombs = 5;
		shields = MAX_SHIELDS;
		score = 0;
	}
	
	public void act() {
	    super.act();
		x+=vx;
		y+=vy;
		if (x < 0) { 
		    x = 0;
		}    
		if (x > Stage.WIDTH - getWidth()) {
		    x = Stage.WIDTH - getWidth();
		}    
		if (y < 0) {
		    y = 0;
		}    
		if (y > Stage.PLAY_HEIGHT-getHeight()) {
		    y = Stage.PLAY_HEIGHT - getHeight();
		}    
	}


	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
	protected void updateSpeed() {
		vx = 0;
		vy = 0;
		if (down) {
			vy = PLAYER_SPEED;
		}
		if (up) {
			vy = -PLAYER_SPEED;
		}
		if (left) {
			vx = -PLAYER_SPEED;
		}
		if (right) {
			vx = PLAYER_SPEED;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		   case KeyEvent.VK_DOWN: {
			   down = false;
			   break;
		   }
		   case KeyEvent.VK_UP: {
			   up = false;
			   break;
		   }
		   case KeyEvent.VK_LEFT: {
			   left = false;
			   break;
		   }
		   case KeyEvent.VK_RIGHT: {
			   right = false;
			   break;
		   }
		}
		updateSpeed();
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		   case KeyEvent.VK_UP: {
			   up = true;
			   updateSpeed();
			   break;
		   }
		   case KeyEvent.VK_LEFT: {
			   left = true;
			   updateSpeed();
			   break;
		   }
		   case KeyEvent.VK_RIGHT: {
			   right = true;
			   updateSpeed();
			   break;
		   }
		   case KeyEvent.VK_DOWN: {
			   down = true;
			   updateSpeed();
			   break;
		   }
		   case KeyEvent.VK_SPACE: {
			   fire();
			   break;
		   }
		   case KeyEvent.VK_B: {
			   fireCluster();
			   break;
		   }
		}
	}
	
	public void fire() {
		Bullet bullet = new Bullet(stage);
		bullet.setX(x);
		bullet.setY(y - bullet.getHeight());
		stage.addActor(bullet);
		stage.getSoundCache().playSound("missile.wav");
	}
	
	public void fireCluster() {
		if (clusterBombs == 0) {
			return;
		}
		
		clusterBombs--;
		stage.addActor(new Bomb(stage, Bomb.UP_LEFT, x, y));
		stage.addActor(new Bomb(stage, Bomb.UP, x, y));
		stage.addActor(new Bomb(stage, Bomb.UP_RIGHT, x, y));
		stage.addActor(new Bomb(stage, Bomb.LEFT, x, y));
		stage.addActor(new Bomb(stage, Bomb.RIGHT, x, y));
		stage.addActor(new Bomb(stage, Bomb.DOWN_LEFT, x, y));
		stage.addActor(new Bomb(stage, Bomb.DOWN, x, y));
		stage.addActor(new Bomb(stage, Bomb.DOWN_RIGHT, x, y));
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getShields() {
		return shields;
	}

	public void setShields(int shields) {
		this.shields = shields;
	}

	public int getClusterBombs() {
		return clusterBombs;
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public void addShields(int shields) {
		this.shields += shields;
	}
	
	public void collision(Actor actor) {
		if (actor instanceof Monster) {
			actor.remove();
			addScore(40);
			addShields(-40);
			if (getShields() < 0) {
				stage.gameOver();
			}
		}
		
		if (actor instanceof Laser) {
			actor.remove();
			addShields(-10);
		}
		
		if (getShields() < 0) {
			stage.gameOver();
		}
		
	}
	
}
