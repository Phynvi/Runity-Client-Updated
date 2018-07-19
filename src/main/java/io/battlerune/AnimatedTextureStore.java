package io.battlerune;
/*
 * Holds the texturing Data.
 */

public enum AnimatedTextureStore {

	WATER_DROPLETS(17, 1), WATER(24, 1), MAGIC_TREE_STARS(34, 1), LAVA(40, 1), CRIMSON_LAVA(56, 1), GRAY_LAVA(57, 1),
	INFERNAL_LAVA(52, 2);

	private final int material_id;
	private final int speed;

	AnimatedTextureStore(int material_id, int speed) {
		this.material_id = material_id;
		this.speed = speed;
	}

	public int getId() {
		return material_id;
	}

	public int getSpeed() {
		return speed;
	}

}