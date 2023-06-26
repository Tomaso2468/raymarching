package io.github.tomaso2468.renderer.thing;

import io.github.tomaso2468.renderer.math.Vector3f;

/**
 * An infinite plane.
 * @author tomas
 *
 */
public class InfinitePlane extends SimpleThing {
	
	public InfinitePlane(Vector3f pos) {
		super(pos);
	}

	@Override
	public double simpleSDF(Vector3f point) {
		return point.y();
	}

}
