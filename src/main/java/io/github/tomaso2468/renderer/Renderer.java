package io.github.tomaso2468.renderer;

import io.github.tomaso2468.renderer.math.Vector3f;
import io.github.tomaso2468.renderer.ray.RayResult;

/**
 * A renderer.
 * @author tomas
 *
 */
public class Renderer {
	/**
	 * The scene to render.
	 */
	public Scene scene;
	
	/**
	 * The width of the renderer.
	 */
	public int width;
	
	/**
	 * The height of the renderer.
	 */
	public int height;
	
	/**
	 * Construct a new renderer.
	 * @param scene The scene to render.
	 * @param width The width of the renderer.
	 * @param height The height of the renderer.
	 */
	public Renderer(Scene scene, int width, int height) {
		super();
		this.scene = scene;
		this.width = width;
		this.height = height;
	}

	/**
	 * Get the color at the specified screen coords. This is a raw color and is not tonemapped.
	 * @param x The X position.
	 * @param y The Y position.
	 * @return The ray color.
	 */
	public Vector3f getColorAtScreenCoords(double x, double y) {
		Vector3f rayPoint = new Vector3f(x, y, -1);
		Vector3f rayStart = new Vector3f(0, 0, 0);
		Vector3f rayDirection = rayPoint.sub(rayStart).normalise();
		
		RayResult result = scene.castRay(rayStart, rayDirection, 0);
		
		return result.color();
	}
	
	/**
	 * Get the color at the specified pixel. This is a raw color and is not tonemapped.
	 * @param x The X position.
	 * @param y The Y position.
	 * @return The ray color.
	 */
	public Vector3f getColorAtPixel(double x, double y) {
		return getColorAtScreenCoords((x - width / 2) / (width / 2), -(y - height / 2) / (height / 2));
	}
}
