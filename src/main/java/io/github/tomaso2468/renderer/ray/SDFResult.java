package io.github.tomaso2468.renderer.ray;

import io.github.tomaso2468.renderer.math.Vector3f;

/**
 * The result of a ray.
 * 
 * @author tomas
 */
public record SDFResult(double distance, Vector3f emmissive, Vector3f color, double roughness) {
	/**
	 * Scale this ray's distance.
	 * @param scale The amount to scale by.
	 * @return A new vector.
	 */
	public SDFResult scale(double scale) {
		return new SDFResult(scale * distance, emmissive, color, roughness);
	}
}
