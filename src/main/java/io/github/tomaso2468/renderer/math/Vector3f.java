package io.github.tomaso2468.renderer.math;

import java.awt.Color;

/**
 * A 3 dimensional vector.
 * @author tomas
 */
public record Vector3f(double x, double y, double z) {
	public Vector3f(double x) {
		this(x, x, x);
	}
			
	/**
	 * Get the magnitude of this vector.
	 * @return |v|
	 */
	public double getMagnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Subtract a vector from this vector.
	 * @param v The vector to subtract.
	 * @return A new vector.
	 */
	public Vector3f sub(Vector3f v) {
		return new Vector3f(x - v.x, y - v.y, z - v.z);
	}
	
	/**
	 * Add a vector to this vector.
	 * @param v The vector to add.
	 * @return A new vector.
	 */
	public Vector3f add(Vector3f v) {
		return new Vector3f(x + v.x, y + v.y, z + v.z);
	}
	
	/**
	 * Multiply each term of this vector by each term of another vector.
	 * @param v The vector to multiply.
	 * @return A new vector.
	 */
	public Vector3f mul(Vector3f v) {
		return new Vector3f(x * v.x, y * v.y, z * v.z);
	}
	
	/**
	 * Multiply each term of this vector by s;
	 * @param s The value to multiply by.
	 * @return A new vector.
	 */
	public Vector3f mul(double s) {
		return new Vector3f(x * s, y * s, z * s);
	}
	
	/**
	 * Pow each term of this vector by s;
	 * @param s The value to power by.
	 * @return A new vector.
	 */
	public Vector3f pow(double s) {
		return new Vector3f(Math.pow(x, s), Math.pow(y, s), Math.pow(z, s));
	}
	
	/**
	 * Divide each term of this vector by each term of another vector.
	 * @param v The vector to divide.
	 * @return A new vector.
	 */
	public Vector3f div(Vector3f v) {
		return new Vector3f(x / v.x, y / v.y, z / v.z);
	}

	/**
	 * Normalise this vector.
	 * @return This vector with a magnitude of 1.
	 */
	public Vector3f normalise() {
		return this.mul(1 / this.getMagnitude());
	}
	
	/**
	 * Take the scalar product of this and another vector.
	 * @param v The vector to divide.
	 * @return A new vector.
	 */
	public double dot(Vector3f v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	/**
	 * Take the vector product of this and another vector.
	 * @param v The vector to divide.
	 * @return A new vector.
	 */
	public Vector3f cross(Vector3f v) {
		return new Vector3f(y * v.z - z * v.y, + z * v.x - x * v.z, x * v.y - y * x);
	}
	
	/**
	 * Convert this color into an AWT color.
	 * @return The AWT color.
	 */
	public Color getAWTColor() {
		float r = (float) this.x;
		if (r < 0) {
			r = 0;
		} else if (r > 1) {
			r = 1;
		}
		
		float g = (float) this.y;
		if (g < 0) {
			g = 0;
		} else if (g > 1) {
			g = 1;
		}
		
		float b = (float) this.z;
		if (b < 0) {
			b = 0;
		} else if (b > 1) {
			b = 1;
		}
		
		return new Color(r, g, b);
	}

	/**
	 * Take the exponent of this vector.
	 * @return A new vector.
	 */
	public Vector3f exp() {
		return new Vector3f(Math.exp(x), Math.exp(y), Math.exp(z));
	}
}
