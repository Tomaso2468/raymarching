package io.github.tomaso2468.renderer.thing;

import io.github.tomaso2468.renderer.math.Vector3f;
import io.github.tomaso2468.renderer.ray.SDFResult;

/**
 * A class representing a thing.
 * @author tomas
 *
 */
public abstract class Thing {
	/**
	 * The position of this thing.
	 */
	public Vector3f position = new Vector3f(0, 0, 0);
	/**
	 * The scale of this thing.
	 */
	public double scale = 1;
	
	/**
	 * Construct a new Thing at the given position.
	 * @param pos The position to use.
	 */
	public Thing(Vector3f pos) {
		this.position = pos;
	}
	
	/**
	 * Construct a new Thing 
	 */
	public Thing() {
	}
	
	/**
	 * Get the SDF of this thing.
	 * @param point The point to check.
	 * @return The SDF of this thing.
	 */
	public SDFResult sdf(Vector3f point) {
		return localSDF(point.sub(position).mul(1 / scale)).scale(scale);
	}
	
	/**
	 * Get the local SDF of this thing. This is in model space.
	 * @param point The point to check.
	 * @return The SDF of this thing.
	 */
	public abstract SDFResult localSDF(Vector3f point);
}
