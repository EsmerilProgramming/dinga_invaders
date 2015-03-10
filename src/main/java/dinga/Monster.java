package dinga;

public class Monster extends Actor {

	protected int vx;
	protected static final double FIRING_FREQUENCY = 0.01;

	public Monster(Stage stage) {
		super(stage);
		setSpriteNames(new String[] {"preto.gif", "preto01.gif"});
		setFrameSpeed(20);
	}

	public void act() {
		super.act();
		x += vx;
		if (x < 0 || x > Stage.WIDTH) {
			vx = -vx;
		}

		if (Math.random() < FIRING_FREQUENCY) {
			fire();
		}

	}

	public void fire() {
		Laser laser = new Laser(stage);
		laser.setX(x + getWidth() / 2);
		laser.setY(y + getHeight());
		stage.addActor(laser);
		stage.getSoundCache().playSound("photon.wav");
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}
	
	@Override
	public void collision(Actor actor) {
		if (actor instanceof Bullet || actor instanceof Bomb) {
			remove();
			stage.getSoundCache().playSound("explosion.wav");
			spawn();
			stage.getPlayer().addScore(20);
		}
	}
	
	public void spawn() {
	    Monster monster = new Monster(stage);
		monster.setX((int)(Math.random()*Stage.WIDTH) );
		monster.setY((int)(Math.random()*Stage.PLAY_HEIGHT/2));
		monster.setVx((int)(Math.random()*20-10));
		stage.addActor(monster);
	}

	
}
