package io.battlerune.dumper;

public class ItemDefDumperData {

	private int id;
	private String name;
	private int[] modifiedColors, orginalColors;

	public ItemDefDumperData(int id, String name, int[] modifiedColors, int[] orginalColors) {
		this.id = id;
		this.name = name;
		this.modifiedColors = modifiedColors;
		this.orginalColors = orginalColors;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int[] getModifiedColors() {
		return modifiedColors;
	}

	public int[] getOrginalColors() {
		return orginalColors;
	}

}
