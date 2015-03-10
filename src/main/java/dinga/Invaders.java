package dinga;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import dinga.cache.SoundCache;
import dinga.cache.SpriteCache;

@SuppressWarnings("serial")
public class Invaders extends Canvas implements Stage, KeyListener {

	private BufferStrategy bufferStrategy;
	private long usedTime;
	private SpriteCache spriteCache;
	private SoundCache soundCache;
	private ArrayList <Actor> actors;
	private Player player;
	private boolean gameEnded = false;

	private BufferedImage space;
	private int t;

	public void gameOver() {
		gameEnded = true;
	}

	public void paintGameOver() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("GAME OVER", Stage.WIDTH / 2 - 50, Stage.HEIGHT / 2);
		bufferStrategy.show();
	}

	public Invaders() {

		spriteCache = new SpriteCache();
		soundCache = new SoundCache();

		JFrame window = new JFrame("Dinga Invaders");
		JPanel panel = (JPanel) window.getContentPane();
		setBounds(0, 0, Stage.WIDTH, Stage.HEIGHT);
		panel.setPreferredSize(new Dimension(Stage.WIDTH, Stage.HEIGHT));
		panel.setLayout(null);
		panel.add(this);

		window.setBounds(0, 0, Stage.WIDTH, Stage.HEIGHT);
		window.setVisible(true);

		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		window.setResizable(false);
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();
        requestFocus();
        addKeyListener(this);
	}

	public void initWorld() {
		actors = new ArrayList<Actor>();
		for (int i = 0; i < 8; i++) {
			Monster monster = new Monster(this);
			monster.setX((int)(Math.random() * Stage.WIDTH));
			monster.setY(i * 20);
			monster.setVx((int)((Math.random() * 20) -10));
			actors.add(monster);
		}

		player = new Player(this);
		player.setX(Stage.WIDTH / 2);
		player.setY(Stage.HEIGHT - 2 * player.getHeight());
		player.setVx(5);

		soundCache.loopSound("musica.wav");
	}

	public SoundCache getSoundCache() {
		return soundCache;
	}

	public void addActor(Actor actor) {
		actors.add(actor);
	}

	public void updateWorld() {
		int i = 0;
		while (i < actors.size()) {
			Actor actor = actors.get(i);
			if (actor.isMarkedForRemoval()) {
				actors.remove(i);
			} else {
				actor.act();
				i++;
			}
		}
		player.act();
	}

	public void paintWorld() {
		Graphics2D g = (Graphics2D)bufferStrategy.getDrawGraphics();

		space = spriteCache.getSprite("back.gif");
		g.setPaint(new TexturePaint(space, new Rectangle(0, t, space.getWidth(), space.getHeight())));
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < actors.size(); i++) {
			actors.get(i).paint(g);
		}
		player.paint(g);
		paintStatus(g);
		bufferStrategy.show();
	}

	public SpriteCache getSpriteCache() {
		return spriteCache;
	}

	public void game() {
		usedTime = 1000;
		t = 0;
		initWorld();
		while(isVisible() && !gameEnded) {
			t++;

			long startTime = System.currentTimeMillis();
			updateWorld();
			checkCollisions();
			paintWorld();
			usedTime = System.currentTimeMillis() - startTime;
			try {
				Thread.sleep(SPEED);
			} catch (InterruptedException e) {

			}
		}
		paintGameOver();
	}

	public void checkCollisions() {
		Rectangle playerBounds = player.getBounds();

		for (int i = 0; i < actors.size(); i++) {
			Rectangle actorBounds = actors.get(i).getBounds();
			if (actorBounds.intersects(playerBounds)) {
				player.collision(actors.get(i));
				actors.get(i).collision(player);
			}
			for (int j = i + 1 ; j < actors.size(); j++) {
				Rectangle actorBounds2 = actors.get(j).getBounds();
			    if (actorBounds.intersects(actorBounds2)) {
			        actors.get(i).collision(actors.get(j));
			        actors.get(j).collision(actors.get(i));
			    }
			}
		}
	}

	public void paintShields(Graphics2D g) {
		g.setPaint(Color.red);
		g.fillRect(280, Stage.PLAY_HEIGHT, Player.MAX_SHIELDS, 30);
		g.setPaint(Color.blue);
		g.fillRect(280+Player.MAX_SHIELDS-player.getShields(),Stage.PLAY_HEIGHT,player.getShields(),30);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setPaint(Color.green);
        g.drawString("Shields", 170, Stage.PLAY_HEIGHT + 20);
	}

	public void paintScore(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setPaint(Color.green);
        g.drawString("Score:", 20, Stage.PLAY_HEIGHT + 20);
        g.setPaint(Color.red);
        g.drawString(String.valueOf(player.getScore()), 100, Stage.PLAY_HEIGHT + 20);
	}

	public void paintAmmo(Graphics2D g) {
		int xBase = 280 + Player.MAX_SHIELDS + 10;
		for (int i = 0; i < player.getClusterBombs(); i++) {
			BufferedImage bomb = spriteCache.getSprite("bomb_ul.gif");
			g.drawImage(bomb, xBase + i * bomb.getWidth(), Stage.PLAY_HEIGHT, this);
		}
	}

	public void paintFps(Graphics2D g) {
		g.setFont(new Font("Arial",Font.BOLD,12));
        g.setColor(Color.white);
        if (usedTime > 0) {
        	g.drawString(String.valueOf(1000/usedTime) + "fps", Stage.WIDTH - 50, Stage.PLAY_HEIGHT);
        } else {
        	g.drawString("--- fps", Stage.WIDTH - 50, Stage.PLAY_HEIGHT);
        }
	}

	public void paintStatus(Graphics2D g) {
		paintScore(g);
		paintShields(g);
		paintAmmo(g);
		paintFps(g);
	}

	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
       player.keyReleased(e);
	}

    public void keyTyped(KeyEvent e) {}

    public Player getPlayer() {
    	return player;
    }

	public static void main(String args[]) {
		Invaders invaders = new Invaders();
		invaders.game();
	}

}
