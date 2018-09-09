package io.battlerune.dumper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import io.battlerune.ItemDefinition;

public class ItemDefDumper {

	private int totalItems;

	public ItemDefDumper(int totalItems) {
		this.totalItems = totalItems;
	}

	public void execute() {
		for (int i = 0; i < totalItems; i++) {
			ItemDefinition def = ItemDefinition.lookup(i);
			if (def != null) {
				//if
				DUMP.add(new ItemDefDumperData(def.id, def.name, def.modifiedModelColors, def.originalModelColors));
			}
		}
		dump();
	}

	private void dump() {
		try (PrintWriter file = new PrintWriter(new FileOutputStream(new File("item_output"), true))) {

			DUMP.forEach(definition -> {
				file.append("Id: " + definition.getId());
				file.println();
				file.append("Name: " + definition.getName());
				file.println();
				file.append("Orginal Colors: " + definition.getOrginalColors());
				file.println();
				file.append("Modified Colors: " + definition.getModifiedColors());
				file.println();
			});

			file.flush();
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finished Dumping Item Definition!");
		}
	}

	private List<ItemDefDumperData> DUMP = new ArrayList<>();
}
