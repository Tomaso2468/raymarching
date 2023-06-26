package io.github.tomaso2468.renderer;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import io.github.tomaso2468.renderer.math.Vector3f;

/**
 * A renderer that renders to an AWT image.
 * @author tomas
 *
 */
public class AWTRenderer extends Renderer {

	/**
	 * Construct a new Renderer.
	 * @param scene The scene to use.
	 * @param width The width of the image.
	 * @param height The height of the image.
	 */
	public AWTRenderer(Scene scene, int width, int height) {
		super(scene, width, height);
	}

	/**
	 * Render to a BufferedImage.
	 * @param img The image to render to.
	 * @return The image with the rendered content.
	 */
	public BufferedImage render(BufferedImage img) {
		for (int y = 0; y < height; y++) {
			System.out.println(y);
			for (int x = 0; x < width; x++) {
				img.setRGB(x, y, getColorAtPixel(x, y).getAWTColor().getRGB());
			}
		}
		return img;
	}
	
	/**
	 * Render to a BufferedImage.
	 * @param img The image to render to.
	 * @param painer The component to paint.
	 * @return The image with the rendered content.
	 */
	public BufferedImage render(BufferedImage img, Component painter) {
		Random random = ThreadLocalRandom.current();
		for (int y = 0; y < height; y++) {
			System.out.println(y);
			for (int x = 0; x < width; x++) {
				Vector3f sum = new Vector3f(0, 0, 0);
				
				int rays = 256;
				
				for (int i = 0; i < rays; i++) {
					sum = sum.add(getColorAtPixel(x + random.nextDouble(), y + random.nextDouble()));
				}
				
				sum = sum.mul(1.0 / rays);
				
				double exposure = 1.0;
				
				Vector3f mapped = new Vector3f(1).sub(sum.mul(-exposure).exp());
				
				Vector3f gamma = mapped.pow(1.0 / 1.2);
				
				img.setRGB(x, y, gamma.getAWTColor().getRGB());
			}
			painter.repaint();
		}
		return img;
	}
}
