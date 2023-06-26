package io.github.tomaso2468.renderer.ray;

import io.github.tomaso2468.renderer.math.Vector3f;

public record RayResult(boolean hit, double distance, Vector3f color) {

}
