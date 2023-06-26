package io.github.tomaso2468.renderer;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import io.github.tomaso2468.renderer.math.Vector3f;
import io.github.tomaso2468.renderer.ray.RayResult;
import io.github.tomaso2468.renderer.ray.SDFResult;
import io.github.tomaso2468.renderer.thing.Group;

/**
 * A scene.
 * @author tomas
 *
 */
public class Scene extends Group {
	/**
	 * The maximum number of bounces to perform.
	 */
	public static final int MAX_BOUNCES = 4;
	/**
	 * The maximum number of ray steps.
	 */
	public static final int MAX_STEPS = 200;
	/**
	 * The maximum distance to render to.
	 */
	public static final double MAX_DISTANCE = 100;
	/**
	 * The size of each step.
	 */
	public static final double STEP_SIZE =  MAX_DISTANCE / MAX_STEPS;
	/**
	 * The smallest distance to move/consider.
	 */
	public static final double EPSILON = 0.001;
	
	/**
	 * Estimate the normal of the scene at the specified point.
	 * @param p The point to check.
	 * @return The normal at that point.
	 */
	Vector3f estimateNormal(Vector3f p) {
	    return new Vector3f(
	        sdf(new Vector3f(p.x() + EPSILON, p.y(), p.z())).distance() - sdf(new Vector3f(p.x() - EPSILON, p.y(), p.z())).distance(),
	        sdf(new Vector3f(p.x(), p.y() + EPSILON, p.z())).distance() - sdf(new Vector3f(p.x(), p.y() - EPSILON, p.z())).distance(),
	        sdf(new Vector3f(p.x(), p.y(), p.z()  + EPSILON)).distance() - sdf(new Vector3f(p.x(), p.y(), p.z() - EPSILON)).distance()
	    ).normalise();
	}
	
	/**
	 * Reflect dir across normal
	 * @param dir The direction to reflect.
	 * @param normal The normal to reflect across.
	 * @return A new vector.
	 */
	Vector3f reflect(Vector3f dir, Vector3f normal) {
		return dir.sub(normal.mul(dir.dot(normal) * 2)).normalise();
	}
	
	/**
	 * Light the specified object.
	 */
	public Vector3f light(Vector3f color, Vector3f emmissive, Vector3f incomingSpec) {
		return color.mul(incomingSpec).add(emmissive);
	}
	
	/**
	 * Cast a ray.
	 * @param start The start location of the ray.
	 * @param direction The direction of the ray.
	 * @param n The number of the ray. Used to limit the number of bounces.
	 * @return The result of the ray.
	 */
	public RayResult castRay(Vector3f start, Vector3f direction, int n) {
		if (n >= MAX_BOUNCES) {
			return new RayResult(false, 0, new Vector3f(1));
		}
		
		Vector3f point = start.add(direction.mul(EPSILON));
		
		double totalDistance = STEP_SIZE;
		
		for (int i = 0; i < MAX_STEPS; i++) {
			SDFResult sdf = sdf(point);
			
			totalDistance += sdf.distance();
			
			if (sdf.distance() < EPSILON) {
				Random random = ThreadLocalRandom.current();
				
				Vector3f normal = estimateNormal(point);
				
				Vector3f randomDir = new Vector3f(random.nextDouble() * 2 - 1,
						random.nextDouble(), 
						random.nextDouble() * 2 - 1
						).normalise();
				Vector3f reflectDir = reflect(direction, normal);
				
				Vector3f axisX = reflectDir.cross(new Vector3f(random.nextDouble(), random.nextDouble(), random.nextDouble())).normalise();
				Vector3f axisZ = reflectDir.cross(axisX).normalise();
				
				Vector3f randomV = new Vector3f(randomDir.dot(axisX), randomDir.dot(normal), randomDir.dot(axisZ)).normalise();
				
				RayResult incoming = castRay(point, (reflectDir.mul(1 - sdf.roughness()).add(randomV.mul(sdf.roughness()))).normalise(), n + 1);
				
				Vector3f totalColor = light(sdf.color(), sdf.emmissive(), incoming.color());
				
				return new RayResult(true, totalDistance, totalColor);
			}
			
			point = point.add(direction.mul(sdf.distance()));
		}
		
		return new RayResult(false, totalDistance, new Vector3f(1));
	}
}










