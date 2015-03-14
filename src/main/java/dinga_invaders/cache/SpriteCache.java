package dinga_invaders.cache;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteCache extends ResourceCache {

	protected Object loadResource(URL url) {
		try {
			return ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("No foi possvel carregar a imagem de: " + url);
			System.out.println("ERROR: " + e.getClass() + " " + e.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	public BufferedImage getSprite(String name) {
		return (BufferedImage)getResource(name);
	}
}
