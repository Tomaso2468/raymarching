package io.github.tomaso2468.renderer.thing;

import io.github.tomaso2468.renderer.math.Vector3f;

/**
 * A sphere primative.
 * @author tomas
 *
 */
public class Sphere extends SimpleThing {

	/**
	 * The radius of the sphere.
	 */
	public double radius;
	
	/**
	 * Construct a new sphere.
	 * @param pos The position of the sphere.
	 * @param r The radius of the sphere.
	 */
	public Sphere(Vector3f pos, double r) {
		super(pos);
		this.radius = r;
	}

	@Override
	public double simpleSDF(Vector3f point) {
		return point.getMagnitude() - radius;
	}

}
