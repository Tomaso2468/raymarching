package io.github.tomaso2468.renderer.thing;

import java.util.ArrayList;
import java.util.List;

import io.github.tomaso2468.renderer.math.Vector3f;
import io.github.tomaso2468.renderer.ray.SDFResult;

/**
 * A group of things.
 * @author tomas
 *
 */
public class Group extends Thing {
	/**
	 * The list of things in this group.
	 */
	private final List<Thing> things = new ArrayList<Thing>();
	
	@Override
	public SDFResult localSDF(Vector3f point) {
		SDFResult current = new SDFResult(Double.POSITIVE_INFINITY, new Vector3f(0, 0, 0), new Vector3f(0, 0, 0), 0);
		
		for (Thing thing : things) {
			SDFResult result = thing.sdf(point);
			
			if (result.distance() <= current.distance()) {
				current = result;
			}
		}
		
		return current;
	}
	
	/**
	 * Add a thing to this scene.
	 * @param thing The thing to add.
	 */
	public void add(Thing thing) {
		things.add(thing);
	}

}
