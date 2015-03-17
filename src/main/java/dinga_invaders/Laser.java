package dinga_invaders;

public class Laser extends Actor {

	protected static final int BULLET_SPEED = 3;

	public Laser(Stage stage) {
		super(stage);
		setSpriteNames(new String[] {"lazer0.gif", "lazer1.gif", "lazer2.gif"});
		setFrameSpeed(10);
	}

	@Override
	public void act() {
		super.act();
		y += BULLET_SPEED;
		if (y > Stage.PLAY_HEIGHT) {
			remove();
		}
	}

}
