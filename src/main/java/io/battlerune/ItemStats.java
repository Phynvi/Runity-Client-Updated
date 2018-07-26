package io.battlerune;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ItemStats {

	public static final int STAB = 0;
	public static final int SLASH = 1;
	public static final int CRUSH = 2;
	public static final int MAGIC = 3;
	public static final int RANGED = 4;

	public static ItemStats[] itemstats = new ItemStats[22520];

	public int itemId;
	public int[] attackBonus;
	public int[] defenceBonus;
	public int prayerBonus;
	public int strengthBonus;
	public int healAmount;
	public int type;
	 public int[][] rewards;
	    public String information;

	public ItemStats(int id, int typeOfStat) {
		this.itemId = id;
		this.attackBonus = new int[] { 0, 0, 0, 0, 0 };
		this.defenceBonus = new int[] { 0, 0, 0, 0, 0 };
		this.prayerBonus = 0;
		this.strengthBonus = 0;
		this.healAmount = 0;
		this.type = typeOfStat;
	}

	private static int readType = 0;

	public static void readDefinitions() {
		try {
			File file = new File(Utility.findcachedir() + "itemstats.dat");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.equals("[STATS]")) {
					readType = 1;
					continue;
				}
				if (line.equals("[FOOD]")) {
					readType = 2;
					continue;
				}
				if (line.equals("[MYSTERY BOXES]")) {
            		readType = 3;
            		continue;
            	}
				if (readType == 1) {
					String[] data = line.split(" ");
					int slot = 0;
					int id = Integer.parseInt(data[slot++]);
					itemstats[id] = new ItemStats(id, readType);
					for (int i = 0; i < 5; ++i) {
						itemstats[id].attackBonus[i] = Integer.parseInt(data[slot++]);
					}
					for (int i = 0; i < 5; ++i) {
						itemstats[id].defenceBonus[i] = Integer.parseInt(data[slot++]);
					}
					itemstats[id].strengthBonus = Integer.parseInt(data[slot++]);
					itemstats[id].prayerBonus = Integer.parseInt(data[slot++]);
				} else if (readType == 2) {
					String[] data = line.split(" ");
					int id = Integer.parseInt(data[0]);
					itemstats[id] = new ItemStats(id, readType);
					itemstats[id].healAmount = Integer.parseInt(data[1]);
				} else if (readType == 3) {
            		String[] data = line.split(" ");
            		int id = Integer.parseInt(data[0]);
            		itemstats[id] = new ItemStats(id, readType);
            		itemstats[id].rewards = new int[data.length - 1][2];
            		for (int i = 1; i < data.length; ++i) {
            			int commaPos = data[i].indexOf(',');
            			int reward = Integer.parseInt(data[i].substring(0, commaPos));
            			int amount = Integer.parseInt(data[i].substring(commaPos + 1));
            			itemstats[id].rewards[i - 1] = new int[] {reward, amount};
            		}
            		itemstats[id].information = bufferedReader.readLine();
            	}
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
