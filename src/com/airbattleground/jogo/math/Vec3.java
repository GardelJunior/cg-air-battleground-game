package com.airbattleground.jogo.math;

public class Vec3 {
	private float x, y, z;

	public Vec3() {
		x = y = z = 0;
	}

	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public Vec3 add(Vec3 vec) {
		return new Vec3(vec.x + x, vec.y + y, vec.z + z);
	}

	public Vec3 sub(Vec3 vec) {
		return new Vec3(x - vec.x, y - vec.y, z - vec.z);
	}

	public Vec3 mul(float scalar) {
		return new Vec3(x * scalar, y * scalar, z * scalar);
	}

	public float mul(Vec3 vec) {
		return x * vec.x + y * vec.y + z * vec.z;
	}

	public Vec3 normalize() {
		float len = length();
		return new Vec3(x / len, y / len, z / len);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

}
