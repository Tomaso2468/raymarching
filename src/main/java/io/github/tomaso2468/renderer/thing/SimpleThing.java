package io.github.tomaso2468.renderer.thing;

import io.github.tomaso2468.renderer.math.Vector3f;
import io.github.tomaso2468.renderer.ray.SDFResult;

/**
 * A simple primative.
 * @author tomas
 *
 */
public abstract class SimpleThing extends Thing {
	/**
	 * The base color of this thing.
	 */
	public Vector3f color = new Vector3f(1, 1, 1);
	/**
	 * The emmisive color of this thing.
	 */
	public Vector3f emmisive = new Vector3f(0, 0, 0);
	/**
	 * How much this thing diffuses light.
	 */
	public double roughness = 1;

	/**
	 * Construct a new SimpleThing.
	 * @param pos The position of the thing.
	 */
	public SimpleThing(Vector3f pos) {
		super(pos);
	}

	@Override
	public SDFResult localSDF(Vector3f point) {
		return new SDFResult(simpleSDF(point), emmisive, color, roughness);
	}
	
	/**
	 * A SDF for this thing,
	 * @param point The point to check.
	 * @return The signed distance to this thing.
	 */
	public abstract double simpleSDF(Vector3f point);

}
