package io.battlerune;

import java.util.logging.Logger;

public final class ItemDefinition {

/** Easier way to pack custom models -- Adam. **/
	public static void defaultConfig(ItemDefinition itemDef, int groundModel, int maleModel, String name) {
		defaultConfig(itemDef, groundModel, maleModel, maleModel, name);
	}

	public static void defaultConfig(ItemDefinition itemDef, int groundModel, int maleModel, int femaleModel, String name) {
		itemDef.modelID = groundModel;
		itemDef.maleWield = maleModel;
		itemDef.femaleWield = femaleModel;
		itemDef.name = name;
		itemDef.description = ("It's an " + itemDef.name);


		itemDef.modelZoom = 1500;
		itemDef.modelOffset1 = 0;
		itemDef.modelOffset2 = 0;
		itemDef.modelRotationY = 900;
		itemDef.modelRotationX = 1200;
		itemDef.groundActions = new String[5];
		itemDef.groundActions[2] = "Take";

		itemDef.stackable = false;
	}

	/**
	 * The single logger for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ItemDefinition.class.getName());

	public static void nullLoader() {
		mruNodes2 = null;
		mruNodes1 = null;
		BufferIndices = null;
		cache = null;
		Buffer = null;
	}

	public boolean method192(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.isCached(k))
			flag = false;
		if (l != -1 && !Model.isCached(l))
			flag = false;
		return flag;
	}

	public static void dumpList() {
		/*
		 * JsonArray array = new JsonArray();
		 * 
		 * for (int index = 0; index < totalItems; index++) { try { ItemDefinition
		 * definition = lookup(index);
		 * 
		 * if (definition.name == null || definition.name.equals("null") ||
		 * definition.name.isEmpty()) continue;
		 * 
		 * JsonObject object = new JsonObject(); array.add(object);
		 * 
		 * object.addProperty("id", definition.id); object.addProperty("name",
		 * definition.name); object.addProperty("examine", definition.description);
		 * 
		 * if (definition.stackable) { object.addProperty("stackable", true); } if
		 * (definition.itemActions != null) { for (int idx = 0; idx <
		 * definition.itemActions.length; idx++) { String action =
		 * definition.itemActions[idx]; if (action != null) { if
		 * (action.contains("Wear") || action.contains("Wield")) {
		 * object.addProperty("equipable", true); } if (action.contains("Destroy")) {
		 * object.addProperty("destroyable", true); } } } } if
		 * (definition.certTemplateID == -1 && definition.certID != -1) {
		 * object.addProperty("noted-id", definition.certID); } if
		 * (definition.certTemplateID != -1 && definition.certID != definition.id) {
		 * object.addProperty("unnoted-id", definition.certID); } if (definition.value >
		 * 1) { object.addProperty("base-value", definition.value); } if
		 * (definition.itemActions != null) { for (int idx = 0; idx <
		 * definition.itemActions.length; idx++) { String action =
		 * definition.itemActions[idx]; if (action != null) { if
		 * (action.contains("Wield")) { object.addProperty("equipment-type", "WEAPON");
		 * } break; } } }
		 * 
		 * } catch (Exception e) { System.out.println(index); e.printStackTrace(); } }
		 * 
		 * try { Files.write(Paths.get("./item_dump.json"), new
		 * GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create().toJson(array
		 * ).getBytes()); } catch (IOException e) { e.printStackTrace(); }
		 */
	}

	public static void unpackConfig(StreamLoader archive) {
		Buffer = new Buffer(archive.getDataForName("obj.dat"));
		Buffer stream = new Buffer(archive.getDataForName("obj.idx"));
		totalItems = stream.readUShort();
		System.out.println("Total Items: " + totalItems);
		BufferIndices = new int[totalItems];
		int offset = 2;
		for (int _ctr = 0; _ctr < totalItems; _ctr++) {
			BufferIndices[_ctr] = offset;
			offset += stream.readUShort();
		}
		cache = new ItemDefinition[10];

		for (int _ctr = 0; _ctr < 10; _ctr++) {
			cache[_ctr] = new ItemDefinition();
		}
		
	}

	// Buffer = new Buffer(FileUtility.readFile(SignLink.findcachedir() +
	// "obj.dat"));
	// Buffer Buffer = new Buffer(FileUtility.readFile(SignLink.findcachedir() +
	// "obj.idx"));
	// totalItems = Buffer.readUShort() + 21;
	// BufferIndices = new int[totalItems + 50000];
	// int i = 2;
	// for (int j = 0; j < totalItems - 21; j++) {
	// BufferIndices[j] = i;
	// i += Buffer.readUShort();
	// }
	//
	// cache = new ItemDefinition[10];
	// for (int k = 0; k < 10; k++)
	// cache[k] = new ItemDefinition();

	Model method194(int j) {
		int k = anInt175;
		int l = anInt166;
		if (j == 1) {
			k = anInt197;
			l = anInt173;
		}
		if (k == -1)
			return null;
		Model model = Model.getModel(k);
		if (l != -1) {
			Model model_1 = Model.getModel(l);
			Model aclass30_sub2_sub4_sub6s[] = { model, model_1 };
			model = new Model(2, aclass30_sub2_sub4_sub6s);
		}
		if (modifiedModelColors != null) {
			for (int i1 = 0; i1 < modifiedModelColors.length; i1++)
				model.recolor(modifiedModelColors[i1], originalModelColors[i1]);

		}
		return model;
	}

	public boolean method195(int j) {
		int k = maleWield;
		int l = anInt188;
		int i1 = anInt185;
		if (j == 1) {
			k = femaleWield;
			l = anInt164;
			i1 = anInt162;
		}
		if (k == -1)
			return true;
		boolean flag = true;
		if (!Model.isCached(k))
			flag = false;
		if (l != -1 && !Model.isCached(l))
			flag = false;
		if (i1 != -1 && !Model.isCached(i1))
			flag = false;
		return flag;
	}

	public Model method196(int i) {
		int j = maleWield;
		int k = anInt188;
		int l = anInt185;
		if (i == 1) {
			j = femaleWield;
			k = anInt164;
			l = anInt162;
		}
		if (j == -1)
			return null;
		Model model = Model.getModel(j);
		if (k != -1)
			if (l != -1) {
				Model model_1 = Model.getModel(k);
				Model model_3 = Model.getModel(l);
				Model aclass30_sub2_sub4_sub6_1s[] = { model, model_1, model_3 };
				model = new Model(3, aclass30_sub2_sub4_sub6_1s);
			} else {
				Model model_2 = Model.getModel(k);
				Model aclass30_sub2_sub4_sub6s[] = { model, model_2 };
				model = new Model(2, aclass30_sub2_sub4_sub6s);
			}
		if (i == 0 && aByte205 != 0)
			model.method475(0, aByte205, 0);
		if (i == 1 && aByte154 != 0)
			model.method475(0, aByte154, 0);
		if (modifiedModelColors != null) {
			for (int i1 = 0; i1 < modifiedModelColors.length; i1++)
				model.recolor(modifiedModelColors[i1], originalModelColors[i1]);

		}

		return model;
	}

	private void setDefaults() {
		modelID = 0;
		name = null;
		description = null;
		modifiedModelColors = null; // here Adam
		originalModelColors = null;
		modelZoom = 2000;
		modelRotationY = 0;
		modelRotationX = 0;
		anInt204 = 0;
		modelOffset1 = 0;
		modelOffset2 = 0;
		stackable = false;
		value = 1;
		membersObject = false;
		groundActions = null;
		itemActions = null;
		maleWield = -1;
		anInt188 = -1;
		aByte205 = 0;
		femaleWield = -1;
		anInt164 = -1;
		aByte154 = 0;
		anInt185 = -1;
		anInt162 = -1;
		anInt175 = -1;
		anInt166 = -1;
		anInt197 = -1;
		anInt173 = -1;
		stackIDs = null;
		stackAmounts = null;
		certID = -1;
		certTemplateID = -1;
		anInt167 = 128;
		anInt192 = 128;
		anInt191 = 128;
		brightness = 0;
		anInt184 = 0;
		team = 0;
	}


	public static ItemDefinition lookup(int i) {
		for (int j = 0; j < 10; j++)
			if (cache[j].id == i)
				return cache[j];

		cacheIndex = (cacheIndex + 1) % 10;
		ItemDefinition itemDef = cache[cacheIndex];
		Buffer.currentOffset = BufferIndices[i];
		itemDef.id = i;
		itemDef.setDefaults();
		itemDef.readValues(Buffer);
		/* Customs added here? */

		switch (i) {
		
		
		case 34:
			itemDef.modelID = 56159;
			itemDef.description = "";
			itemDef.name = "Owl Helm";
			itemDef.stackable = false;
			itemDef.maleWield = 56160;
			itemDef.femaleWield = 56160;
			itemDef.modelZoom = 1010;
			itemDef.modelRotationY = 16;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 60000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 79:
			itemDef.name = "Owl Platebody";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 56149;
			itemDef.stackable = false;
			itemDef.modelZoom = 1312;
			itemDef.modelRotationY = 272;
			itemDef.modelRotationX = 2047;
			itemDef.modelOffset2 = 39;
			itemDef.modelOffset1 = -2;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 56148;
			itemDef.femaleWield = 56148;
			itemDef.description = "Statius's Platebody, a reward from PVP.";
			break;
			
		case 80:
			itemDef.name = "Owl legs";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 56151;
			itemDef.modelZoom = 1753;
			itemDef.stackable = false;
			itemDef.modelRotationY = 562;
			itemDef.modelRotationX = 1;
			itemDef.modelOffset2 = 11;
			itemDef.modelOffset1 = -3;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 56150;
			itemDef.femaleWield = 56150;
			itemDef.description = "Vesta's Plateskirt, a reward from PVP.";
		break;
		case 20005:
			itemDef.name = "50% Drop Rate Ring";
			break;
			
		case 21752:
			itemDef.name = "50% Drop Rate Ring";
			break; 
			
		case 9074:
			itemDef.name = "25% Drop Rate Cape";
			break;
		case 20031:
			itemDef.modelID = 56120;
			itemDef.description = "";
			itemDef.name = "Animal Helm";
			itemDef.maleWield = 56121;
			itemDef.femaleWield = 56121;
			itemDef.modelZoom = 1010;
			itemDef.modelRotationY = 16;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 60000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 20043:
			itemDef.name = "Animal Platebody";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 56123;
			itemDef.modelZoom = 1312;
			itemDef.modelRotationY = 272;
			itemDef.modelRotationX = 2047;
			itemDef.modelOffset2 = 39;
			itemDef.modelOffset1 = -2;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 56124;
			itemDef.femaleWield = 56124;
			break;
			
		case 20052:
			itemDef.name = "Animal Legs";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 56125;
			itemDef.modelZoom = 1753;
			itemDef.modelRotationY = 562;
			itemDef.modelRotationX = 1;
			itemDef.modelOffset2 = 11;
			itemDef.modelOffset1 = -3;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 56125;
			itemDef.femaleWield = 56125;
		break;
		
		case 20151:
			itemDef.name = "Animal bow";
			itemDef.modelID = 56127;
			itemDef.maleWield = 56126;
			itemDef.femaleWield = 56126;
			itemDef.modelZoom = 2000;
			itemDef.modelRotationY = 720;
			itemDef.modelRotationX = 1500;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = 1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 90000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;
			
		
		case 21081:
			itemDef.name = "15% Drop rate ring";
			break;
			
		case 21143:
			itemDef.name = "15% Drop rate necklace";
			break;
		
		case 20049:
			itemDef.name = "Samurai kasa (u)";
			itemDef.modelID = 31972;
			itemDef.modelZoom = 1296;
			itemDef.modelRotationY = 212;
			itemDef.modelRotationX = 1408;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = -33;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 4000;
			itemDef.maleWield = 31756;
			itemDef.femaleWield = 31830;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 13284:
			itemDef.name = "Samurai shirt (u)";
			break;
		case 20055:
			itemDef.name = "Samurai gloves (u)";
			itemDef.modelID = 32019;
			itemDef.modelZoom = 789;
			itemDef.modelRotationY = 609;
			itemDef.modelRotationX = 111;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 3200;
			itemDef.maleWield = 31776;
			itemDef.femaleWield = 31847;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 20058:
			itemDef.name = "Samurai greaves (u)";
			itemDef.modelID = 32018;
			itemDef.modelZoom = 1720;
			itemDef.modelRotationY = 450;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 13;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 3200;
			itemDef.maleWield = 31786;
			itemDef.femaleWield = 31860;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
			
		case 20061:	
			itemDef.name = "Samurai boots (u)";
			itemDef.modelID = 32046;
			itemDef.modelZoom = 700;
			itemDef.modelRotationY = 131;
			itemDef.modelRotationX = 69;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -7;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 4500;
			itemDef.maleWield = 31816;
			itemDef.femaleWield = 31882;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
			
		case 13835:
			itemDef.modelID = 68;
			itemDef.name = "Sesmic Staff";
			itemDef.description = "Sesmicy staff";
			itemDef.modelZoom = 1874;
			itemDef.modelOffset2 = 2;
			itemDef.modelOffset1 = -3;
			itemDef.modelRotationX = 1499;
			itemDef.modelRotationY = 292;
			itemDef.maleWield = 69;
			itemDef.femaleWield = 69;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.stackable = false;

			break;
		case 13834: 
			defaultConfig(itemDef, 66, 67, 67, "Sesmic scythe");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "WOW! Its on fire!";
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;
		case 13833:
			itemDef.modelID = 60;
			itemDef.name = "Elder battleaxe";
			itemDef.description = "Elder battleaxe";
			itemDef.modelZoom = 1660;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 1;
			itemDef.modelRotationX = 2020;
			itemDef.modelRotationY = 420;
			itemDef.maleWield = 61;
			itemDef.femaleWield = 61;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wield", null, null, "Drop" };
			break;
		case 13832:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.modelID = 64;
			itemDef.femaleWield = 65;
			itemDef.maleWield = 65;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.modelZoom = 1300;
			itemDef.modelRotationY = 498;
			itemDef.modelRotationX = 1300;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -1;
			itemDef.name = "Dark Karth longsword";
			itemDef.description = "A weapon of incredible accuracy.";
			itemDef.aByte205 = -11;
			itemDef.aByte154 = -11;
			break;
		
		case 13831:
			itemDef.name = "Saradomin bow";
			itemDef.modelID = 58;
			itemDef.maleWield = 59;
			itemDef.femaleWield = 59;
			itemDef.modelZoom = 2000;
			itemDef.modelRotationY = 720;
			itemDef.modelRotationX = 1500;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = 1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 90000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;
		
		case 13824:
			itemDef.modelID = 70;
			itemDef.description = "";
			itemDef.name = "Elder Helm";
			itemDef.maleWield = 71;
			itemDef.femaleWield = 71;
			itemDef.modelZoom = 1010;
			itemDef.modelRotationY = 16;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 60000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 13825:
			itemDef.name = "Elder Platebody";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 72;
			itemDef.modelZoom = 1312;
			itemDef.modelRotationY = 272;
			itemDef.modelRotationX = 2047;
			itemDef.modelOffset2 = 39;
			itemDef.modelOffset1 = -2;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 73;
			itemDef.femaleWield = 73;
			itemDef.description = "Statius's Platebody, a reward from PVP.";
			break;
			
		case 13826:
			itemDef.name = "Elder Plateskirt";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 74;
			itemDef.modelZoom = 1753;
			itemDef.modelRotationY = 562;
			itemDef.modelRotationX = 1;
			itemDef.modelOffset2 = 11;
			itemDef.modelOffset1 = -3;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 75;
			itemDef.femaleWield = 75;
			itemDef.description = "Vesta's Plateskirt, a reward from PVP.";
		break;
		
		case 13805:
			itemDef.name = "Dark Karth cape";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 84;
			itemDef.modelZoom = 2232;
			itemDef.modelRotationY = 687;
			itemDef.modelRotationX = 27;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -5;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 1;
			itemDef.femaleWield = 84;
			itemDef.maleWield = 84;
			break;
		
		case 13813:
			itemDef.name = "Tekton's ring";
			itemDef.modelID = 53;
			itemDef.modelZoom = 630;
			itemDef.modelRotationY = 332;
			itemDef.modelRotationX = 1904;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -14;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 10000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
			
		case 13814:
			itemDef.name = "Dark Karth's ring";
			itemDef.modelID = 54;
			itemDef.modelZoom = 630;
			itemDef.modelRotationY = 332;
			itemDef.modelRotationX = 1904;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -14;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 10000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 13815:
			itemDef.name = "Zaros ring";
			itemDef.modelID = 55;
			itemDef.modelZoom = 630;
			itemDef.modelRotationY = 332;
			itemDef.modelRotationX = 1904;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -14;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 10000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
			
		case 13816: 
			itemDef.name = "Dark Karth's Boots";
			itemDef.modelID = 56112;
			itemDef.maleWield = 9;
			itemDef.femaleWield = 9;
			itemDef.modelZoom = 855;
			itemDef.modelRotationY = 215;
			itemDef.modelRotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -32;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		
		case 13808:
			itemDef.modelID = 43;
			itemDef.description = "";
			itemDef.name = "Tekton Helm";
			itemDef.maleWield = 44;
			itemDef.femaleWield = 44;
			itemDef.modelZoom = 1010;
			itemDef.modelRotationY = 16;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 60000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 13809:
			itemDef.name = "Tekton Platebody";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 45;
			itemDef.modelZoom = 1312;
			itemDef.modelRotationY = 272;
			itemDef.modelRotationX = 2047;
			itemDef.modelOffset2 = 39;
			itemDef.modelOffset1 = -2;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 46;
			itemDef.femaleWield = 46;
			itemDef.description = "Statius's Platebody, a reward from PVP.";
			break;
			
		case 13810:
			itemDef.name = "Tekton Plateskirt";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 47;
			itemDef.modelZoom = 1753;
			itemDef.modelRotationY = 562;
			itemDef.modelRotationX = 1;
			itemDef.modelOffset2 = 11;
			itemDef.modelOffset1 = -3;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 48;
			itemDef.femaleWield = 48;
			itemDef.description = "Vesta's Plateskirt, a reward from PVP.";
		break;
		
		case 13811:
			itemDef.modelID = 49;
			itemDef.description = "";
			itemDef.name = "Tekton Gloves";
			itemDef.maleWield = 50;
			itemDef.femaleWield = 50;
			itemDef.modelZoom = 830;
			itemDef.modelRotationY = 536;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 10000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		
		case 13812: 
			itemDef.name = "Tekton Boots";
			itemDef.modelID = 51;
			itemDef.maleWield = 52;
			itemDef.femaleWield = 52;
			itemDef.modelZoom = 855;
			itemDef.modelRotationY = 215;
			itemDef.modelRotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -32;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		/** START OF CUSTOMS **/
		case 13807: 
			defaultConfig(itemDef, 27, 28, 28, "Zaros scythe");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "WOW! Its on fire!";
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;
		case 13706:
			itemDef.modelID = 23;
			itemDef.description = "";
			itemDef.name = "Zaros Gloves";
			itemDef.maleWield = 24;
			itemDef.femaleWield = 24;
			itemDef.modelZoom = 830;
			itemDef.modelRotationY = 536;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 10000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		
		case 13707: 
			itemDef.name = "Zaros Boots";
			itemDef.modelID = 26;
			itemDef.maleWield = 26;
			itemDef.femaleWield = 26;
			itemDef.modelZoom = 855;
			itemDef.modelRotationY = 215;
			itemDef.modelRotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -32;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		
		case 13708:
			itemDef.name = "Lion's Bane";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.modelID = 25061;
			itemDef.modelZoom = 2000;
			itemDef.modelRotationY = 366;
			itemDef.modelRotationX = 3;
			itemDef.modelOffset2 = 0;
			itemDef.modelOffset1 = 0;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 25062;
			itemDef.femaleWield = 25062;
			itemDef.description = "Zuriel's Staff, a reward from PVP.";
		break;
		case 13709:
			itemDef.name = "Zamorak bow";
			itemDef.modelID = 15;
			itemDef.maleWield = 16;
			itemDef.femaleWield = 16;
			itemDef.modelZoom = 2000;
			itemDef.modelRotationY = 720;
			itemDef.modelRotationX = 1500;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = 1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 90000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;
		
		case 13715:
			itemDef.modelID = 56100;
			itemDef.description = "";
			itemDef.name = "Dark Karth Helm";
			itemDef.maleWield = 56101;
			itemDef.femaleWield = 56101;
			itemDef.modelZoom = 1010;
			itemDef.modelRotationY = 16;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 60000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 13714:
			itemDef.name = "Dark Karth Platebody";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 56106;
			itemDef.modelZoom = 1312;
			itemDef.modelRotationY = 272;
			itemDef.modelRotationX = 2047;
			itemDef.modelOffset2 = 39;
			itemDef.modelOffset1 = -2;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 56107;
			itemDef.femaleWield = 56107;
			itemDef.description = "Statius's Platebody, a reward from PVP.";
			break;
		case 13713:
			itemDef.name = "Dark Karth Twisted bow";
			itemDef.modelID = 56102;
			itemDef.modelZoom = 2000;
			itemDef.modelRotationY = 720;
			itemDef.modelRotationX = 1500;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = 1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 90000;
			itemDef.maleWield = 56103;
			itemDef.femaleWield = 56103;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;
		case 13712:
			itemDef.name = "Dark Karth crossbow";
			itemDef.modelID = 56104;
			itemDef.modelZoom = 926;
			itemDef.modelRotationY = 432;
			itemDef.modelRotationX = 258;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 9;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 80000;
			itemDef.maleWield = 56105;
			itemDef.femaleWield = 56105;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;
		case 13711:
			itemDef.name = "Dark Karth shield";
			itemDef.modelID = 56108;
			itemDef.modelZoom = 2022;
			itemDef.modelRotationY = 540;
			itemDef.modelRotationX = 123;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 2000000;
			itemDef.maleWield = 56109;
			itemDef.femaleWield = 56109;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[2] = "Inspect";
			break;
		
		case 13710:
			itemDef.name = "Dark Karth Plateskirt";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 56110;
			itemDef.modelZoom = 1753;
			itemDef.modelRotationY = 562;
			itemDef.modelRotationX = 1;
			itemDef.modelOffset2 = 11;
			itemDef.modelOffset1 = -3;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 56111;
			itemDef.femaleWield = 56111;
			itemDef.description = "Vesta's Plateskirt, a reward from PVP.";
		break;
			
			
			
		case 7029:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.modelID = 35092;
			itemDef.femaleWield = 35085;
			itemDef.maleWield = 35085;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.modelZoom = 1400;
			itemDef.modelRotationY = 498;
			itemDef.modelRotationX = 1300;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -1;
			itemDef.name = "Chaotic rapier";
			itemDef.description = "A weapon of amazing speed.";
			itemDef.aByte205 = -11;
			itemDef.aByte154 = -11;
			break;
			
		case 7030:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.modelID = 35086;
			itemDef.femaleWield = 35087;
			itemDef.maleWield = 35087;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.modelZoom = 1300;
			itemDef.modelRotationY = 498;
			itemDef.modelRotationX = 1300;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -1;
			itemDef.name = "Chaotic longsword";
			itemDef.description = "A weapon of incredible accuracy.";
			itemDef.aByte205 = -11;
			itemDef.aByte154 = -11;
			break;
		case 7031:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.aByte205 = -10;
			itemDef.aByte154 = -10;
			itemDef.modelID = 35088;
			itemDef.modelZoom = 1360;
			itemDef.modelRotationY = 498;
			itemDef.modelRotationX = 354;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = 2;
			itemDef.femaleWield = 35089;
			itemDef.maleWield = 35089;
			itemDef.anInt188 = -1;
			itemDef.anInt164 = -1;
			itemDef.name = "Chaotic maul";
			itemDef.description = "A weapon of brute strength.";
			break;
		case 19687:
			itemDef.name = "Vesta's full helm";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.description = "Vesta's full helm, a reward from PVP.";

			break;
		case 8798:
				itemDef.name = "Vesta's Chainbody";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42593;
				itemDef.modelZoom = 1440;
				itemDef.modelRotationY = 545;
				itemDef.modelRotationX = 2;
				itemDef.modelOffset2 = 5;
				itemDef.modelOffset1 = 4;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42624;
				itemDef.femaleWield = 42644;
				itemDef.description = "Vesta's Chainbody, a reward from PVP.";
			break;
		case 8799:
				itemDef.name = "Vesta's Plateskirt";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42581;
				itemDef.modelZoom = 1753;
				itemDef.modelRotationY = 562;
				itemDef.modelRotationX = 1;
				itemDef.modelOffset2 = 11;
				itemDef.modelOffset1 = -3;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42633;
				itemDef.femaleWield = 42647;
				itemDef.description = "Vesta's Plateskirt, a reward from PVP.";
			break;

		case 13770:
				itemDef.name = "Vesta's Longsword";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wield";
				itemDef.modelID = 42597;
				itemDef.modelZoom = 1744;
				itemDef.modelRotationY = 738;
				itemDef.modelRotationX = 1985;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42615;
				itemDef.femaleWield = 42615;
				itemDef.description = "Vesta's Longsword, a reward from PVP.";
			break;

		case 13716:
				itemDef.name = "Vesta's Spear";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wield";
				itemDef.modelID = 42599;
				itemDef.modelZoom = 2022;
				itemDef.modelRotationY = 480;
				itemDef.modelRotationX = 15;
				itemDef.modelOffset2 = 5;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42614;
				itemDef.femaleWield = 42614;
				itemDef.description = "Vesta's Spear, a reward from PVP.";
			break;

		case 8802:
				itemDef.name = "Zuriel's Robe Top";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42591;
				itemDef.modelZoom = 1373;
				itemDef.modelRotationY = 373;
				itemDef.modelRotationX = 0;
				itemDef.modelOffset2 = -7;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42627;
				itemDef.femaleWield = 42642;
				itemDef.description = "Zuriel's Robe Top, a reward from PVP.";
			break;

		case 8803:
				itemDef.name = "Zuriel's Robe Bottom";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42588;
				itemDef.modelZoom = 1697;
				itemDef.modelRotationY = 512;
				itemDef.modelRotationX = 0;
				itemDef.modelOffset2 = -9;
				itemDef.modelOffset1 = 2;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42634;
				itemDef.femaleWield = 42645;
				itemDef.description = "Zuriel's Robe Bottom, a reward from PVP.";
			break;

		case 8804:
				itemDef.name = "Zuriel's Staff";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wield";
				itemDef.modelID = 42595;
				itemDef.modelZoom = 2000;
				itemDef.modelRotationY = 366;
				itemDef.modelRotationX = 3;
				itemDef.modelOffset2 = 0;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42617;
				itemDef.femaleWield = 42617;
				itemDef.description = "Zuriel's Staff, a reward from PVP.";
			break;

		case 8805:
				itemDef.name = "Zuriel's Hood";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42604;
				itemDef.modelZoom = 720;
				itemDef.modelRotationY = 28;
				itemDef.modelRotationX = 0;
				itemDef.modelOffset2 = 1;
				itemDef.modelOffset1 = 1;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42638;
				itemDef.femaleWield = 42653;
				itemDef.description = "Zuriel's Hood, a reward from PVP.";
			break;
		case 8806:
				itemDef.name = "Morrigan's leather body";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42578;
				itemDef.modelZoom = 1184;
				itemDef.modelRotationY = 545;
				itemDef.modelRotationX = 2;
				itemDef.modelOffset2 = 5;
				itemDef.modelOffset1 = 4;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42626;
				itemDef.femaleWield = 42643;
				itemDef.description = "Morrigan's Leather Body, a reward from PVP.";
			break;

		case 8807:
			itemDef.name = "Morrigan's Leather Chaps";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42603;
				itemDef.modelZoom = 1753;
				itemDef.modelRotationY = 482;
				itemDef.modelRotationX = 1;
				itemDef.modelOffset2 = 11;
				itemDef.modelOffset1 = -3;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42631;
				itemDef.femaleWield = 42646;
				itemDef.description = "Morrigan's Leather Chaps, a reward from PVP.";
			break;

		case 8808:
				itemDef.name = "Morrigan's Coif";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42583;
				itemDef.modelZoom = 592;
				itemDef.modelRotationY = 537;
				itemDef.modelRotationX = 5;
				itemDef.modelOffset2 = 6;
				itemDef.modelOffset1 = -3;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42636;
				itemDef.femaleWield = 42652;
				itemDef.description = "Morrigan's Coif, a reward from PVP.";
			break;

		case 8809:
				itemDef.name = "Statius's Platebody";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42602;
				itemDef.modelZoom = 1312;
				itemDef.modelRotationY = 272;
				itemDef.modelRotationX = 2047;
				itemDef.modelOffset2 = 39;
				itemDef.modelOffset1 = -2;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42625;
				itemDef.femaleWield = 42641;
				itemDef.description = "Statius's Platebody, a reward from PVP.";
			break;

		case 8810:
				itemDef.name = "Statius's Platelegs";
				itemDef.itemActions = new String[5];
				itemDef.itemActions[1] = "Wear";
				itemDef.modelID = 42590;
				itemDef.modelZoom = 1625;
				itemDef.modelRotationY = 355;
				itemDef.modelRotationX = 2046;
				itemDef.modelOffset2 = -11;
				itemDef.modelOffset1 = 0;
				itemDef.anInt204 = 0;
				itemDef.maleWield = 42632;
				itemDef.femaleWield = 42649;
				itemDef.description = "Statius's Platelegs, a reward from PVP.";
			break;
		case 8811:
			itemDef.name = "Statius's Full Helm";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelID = 42596;
			itemDef.modelZoom = 789;
			itemDef.modelRotationY = 96;
			itemDef.modelRotationX = 2039;
			itemDef.modelOffset2 = -7;
			itemDef.modelOffset1 = 2;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 42639;
			itemDef.femaleWield = 42655;
			itemDef.description = "Statius's Full Helm, a reward from PVP.";
			break;
		case 13769:
			itemDef.name = "Statius's Warhammer";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.modelID = 42577;
			itemDef.modelZoom = 1360;
			itemDef.modelRotationY = 507;
			itemDef.modelRotationX = 27;
			itemDef.modelOffset2 = 6;
			itemDef.modelOffset1 = 7;
			itemDef.anInt204 = 0;
			itemDef.maleWield = 42623;
			itemDef.femaleWield = 42623;
			itemDef.description = "Statius's Warhammer, a reward from PVP.";
			break;
			
			
			
			
			
		case 21277:
			itemDef.modelID = 47096;
			itemDef.name = "Runity Helm";
			itemDef.description = "Runity Helm";
			itemDef.modelZoom = 760;
			itemDef.modelRotationY = 552;
			itemDef.modelRotationX = 28;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 2;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 289000;
			itemDef.maleWield = 47096;
			itemDef.femaleWield = 47096;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 21274:
			
			itemDef.modelID = 47092;
			itemDef.name = "Runity Body";
			itemDef.description = "Runity body";
			itemDef.modelZoom = 854;
			itemDef.modelRotationY = 453;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -5;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 290000;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.maleWield = 47092;
			itemDef.femaleWield = 47092;
			break;

		case 21272:

			itemDef.modelID = 47094;
			itemDef.name = "Runity chaps";
			itemDef.description = "Runity chaps";
			itemDef.modelZoom = 1957;
			itemDef.modelRotationY = 555;
			itemDef.modelRotationX = 2036;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 289900;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.maleWield = 47094;
			itemDef.femaleWield = 47094;
			break;
		
		

		case 13703:          
			defaultConfig(itemDef, 47134, 47135, 47135, "Chi full helm");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelRotationY = 1900;
			itemDef.modelRotationX = 0;
			itemDef.modelZoom = 752;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = -1;
			break;
		case 13704: 
			defaultConfig(itemDef, 47136, 47137, 47137, "Chi platebody");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelRotationY = 452;
			itemDef.modelRotationX = 0;
			itemDef.modelZoom = 1300;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 3;
			break;
		case 13705: 
			defaultConfig(itemDef, 47138, 47139, 47139, "Chi platelegs");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelRotationY = 517;
			itemDef.modelRotationX = 0;
			itemDef.modelZoom = 1673;
			itemDef.modelOffset1 = 11;
			itemDef.modelOffset2 = 3;
			break;
		case 13692:
			itemDef.modelID = 47008;
			itemDef.description = "";
			itemDef.name = "Soul Gloves";
			itemDef.maleWield = 65215;
			itemDef.femaleWield = 65215;
			itemDef.modelZoom = 830;
			itemDef.modelRotationY = 536;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 10000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 13693:
			itemDef.modelID = 47010;
			itemDef.description = "";
			itemDef.name = "Soul Full Helm";
			itemDef.maleWield = 65212;
			itemDef.femaleWield = 65212;
			itemDef.modelZoom = 1010;
			itemDef.modelRotationY = 16;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 60000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 13695:
			itemDef.modelID = 47013;
			itemDef.description = "";
			itemDef.name = "Soul Platelegs";
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 65214;
			itemDef.femaleWield = 65214;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 13696:
			itemDef.modelID = 47019;
			itemDef.maleWield = 65213;
			itemDef.femaleWield = 65213;
			itemDef.description = "";
			itemDef.name = "Soul Platebody";
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
			
			
		

		case 13697: 
			itemDef.modelID = 65253;
			itemDef.name = "Cryptic full helm";

			itemDef.maleWield = 65254;
			itemDef.femaleWield = 65254;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelZoom = 676;
			itemDef.modelRotationY = 200;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -14;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13698: 
			itemDef.modelID = 65257;
			itemDef.name = "Cryptic Platelegs";

			itemDef.maleWield = 65258;
			itemDef.femaleWield = 65258;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13699: 
			itemDef.name = "Cryptic Boots";
			itemDef.modelID = 65259;
			itemDef.maleWield = 65259;
			itemDef.femaleWield = 65259;
			itemDef.modelZoom = 855;
			itemDef.modelRotationY = 215;
			itemDef.modelRotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -32;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13700: 
			itemDef.modelID = 65262;
			itemDef.name = "Cryptic Wings";
			itemDef.maleWield = 65263;
			itemDef.femaleWield = 65263;
			itemDef.modelZoom = 1700;
			itemDef.modelRotationY = 500;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -6;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13701: 
			itemDef.modelID = 65264;
			itemDef.name = "Cryptic Sword";
			itemDef.maleWield = 65265;
			itemDef.femaleWield = 65265;
			itemDef.modelZoom = 1850;
			itemDef.modelRotationY = 324;
			itemDef.modelRotationX = 1808;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -2;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13702: 
			itemDef.modelID = 65264;
			itemDef.name = "Cryptic offhand";
			itemDef.maleWield = 65142;
			itemDef.femaleWield = 65142;
			itemDef.modelZoom = 1850;
			itemDef.modelRotationY = 324;
			itemDef.modelRotationX = 1808;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -2;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 22317: 
			itemDef.modelID = 65255;
			itemDef.maleWield = 65256;
			itemDef.femaleWield = 65256;
			itemDef.name = "Cryptic Platebody";
			itemDef.modelZoom = 1513;
			itemDef.modelRotationY = 566;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -8;
			itemDef.modelOffset2 = 1;
			itemDef.stackable = false;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 22280: 
			itemDef.name = "Cryptic Gloves";
			itemDef.modelZoom = 830;
			itemDef.modelRotationY = 536;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 10000;
			itemDef.modelID = 65157;
			itemDef.maleWield = 65158;
			itemDef.femaleWield = 65158;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;


		case 13738: //47109
			defaultConfig(itemDef, 47109, 47110, 47110, "Lava virtus mask");
			itemDef.modelZoom = 928;
			itemDef.modelRotationY = 406;
			itemDef.modelRotationX = 2041;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -5;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			//itemDef.maleHeadModel1 = 62728;
			//itemDef.femaleHeadModel1 = 62728;
			break;
		case 13739: 
			defaultConfig(itemDef, 47111, 47112, 47112, "@red@Lava scythe");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "WOW! Its on fire!";
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;
		case 13740: 
			itemDef.name = "Lava Santa";
			itemDef.modelID = 47113;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.modelRotationY = 72;
			itemDef.modelRotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 160;
			itemDef.maleWield = 47114;
			itemDef.femaleWield = 47114;
			break;
		case 13741: 
			defaultConfig(itemDef, 47115, 47116, 47116, "Lava Helm");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "WOW! Its a Lava helm!";
			itemDef.modelZoom = 1100;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;//upto here.
		case 13742: 
			defaultConfig(itemDef, 47117, 47118, 47118, "Lava Platebody");
			itemDef.description = "A Powerful Item";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13743: 
			defaultConfig(itemDef, 47119, 47120, 47120, "@or1@Lava pernix cowl");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "It's a Lava pernix cowl";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;
		case 13744: 
			defaultConfig(itemDef, 47121, 47122, 47122, "@or1@Lava pernix body");

			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "It's a Lava pernix body";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;
		case 13745: 
			defaultConfig(itemDef, 47123, 47124, 47124, "@or1@Lava pernix chaps");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "It's a Lava pernix chaps";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;

		case 13746: 
			defaultConfig(itemDef, 47125, 47126, 47126, "Lava Legs");
			itemDef.modelZoom = 1550;
			itemDef.modelRotationY = 344;
			itemDef.modelRotationX = 186;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 11;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13747: 
			defaultConfig(itemDef, 47127, 47128, 47128, "Lava virtus top");
			itemDef.modelZoom = 1122;
			itemDef.modelRotationY = 488;
			itemDef.modelRotationX = 3;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 0;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13748: 
			defaultConfig(itemDef, 47129, 47130, 47130, "Lava virtus bottom");
			itemDef.modelZoom = 1740;
			itemDef.modelRotationY = 498;
			itemDef.modelRotationX = 2045;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 4;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			break;




		case 13749: 
			defaultConfig(itemDef, 47131, 47132, 47132, "Lava bow");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelZoom = 2000;
			itemDef.modelRotationY = 720;
			itemDef.modelRotationX = 1500;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = 1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 90000;
			break;





		case 16628:
			itemDef.modelID = 47062; //47062
			itemDef.name = "Lava Logs"; //47062
			itemDef.description = "Hot...hot...hot!!!!";
			itemDef.modelZoom = 1180;
			itemDef.modelRotationY = 120;
			itemDef.modelRotationX = 1852;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -7;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 160;
			break;
		case 16629:
			itemDef.modelID = 47063;
			itemDef.maleWield = 47064;
			itemDef.femaleWield = 47064;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "WOW! Its a Lava Helmet!";
			itemDef.modelZoom = 2100;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;
		case 16630:
			itemDef.name = "Lava Bow?";
			itemDef.modelID = 47065;
			itemDef.maleWield = 47066;
			itemDef.femaleWield = 47066;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "WOW! Its on fire!";
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;

		case 16631:
			itemDef.name = "Lava Santa Hat";
			itemDef.modelID = 47067;
			itemDef.maleWield = 47068;
			itemDef.femaleWield = 47068;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = " I can feel the lava flowing.!";
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -1;
			break;


		case 16647: 
			itemDef.modelID = 47069;
			itemDef.name = "Elite torva body";
			itemDef.description = "A Powerful Item";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.maleWield = 47070;
			itemDef.femaleWield = 47070;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 16648: 
			itemDef.modelID = 47071;
			itemDef.name = "Elite torva helm";
			itemDef.description = "A Powerful Item";

			itemDef.modelZoom = 676;
			itemDef.modelRotationY = 0;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -14;
			itemDef.maleWield = 47072;
			itemDef.femaleWield = 47072;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 16649: 
			itemDef.modelID = 47073;
			itemDef.name = "Elite torva legs";
			itemDef.description = "A Powerful item.";
			itemDef.modelZoom = 1550;
			itemDef.modelRotationY = 344;
			itemDef.modelRotationX = 186;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 11;
			itemDef.maleWield = 47074;
			itemDef.femaleWield = 47074;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 58383;
			itemDef.originalModelColors[0] = 10756;
			break;
		case 16650: 
			itemDef.modelID = 47075;
			itemDef.maleWield = 47076;
			itemDef.femaleWield = 47076;
			itemDef.name = "Elite virtus mask";
			itemDef.description = "Elite Virtus mask";
			itemDef.modelZoom = 928;
			itemDef.modelRotationY = 406;
			itemDef.modelRotationX = 2041;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -5;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			//itemDef.maleHeadModel1 = 62728;
			//itemDef.femaleHeadModel1 = 62728;
			break;
		case 16651: 
			itemDef.modelID = 47077;
			itemDef.maleWield = 47078;
			itemDef.femaleWield = 47078;
			itemDef.name = "Elite virtus robe top";
			itemDef.description = "Elite Virtus robe top";
			itemDef.modelZoom = 1122;
			itemDef.modelRotationY = 488;
			itemDef.modelRotationX = 3;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 0;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			break;
		case 16653: 
			itemDef.modelID = 47079;
			itemDef.maleWield = 47080;
			itemDef.femaleWield = 47080;
			itemDef.name = "Elite Virtus robe legs";
			itemDef.description = "Elite Virtus robe legs";
			itemDef.modelZoom = 1740;
			itemDef.modelRotationY = 498;
			itemDef.modelRotationX = 2045;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 4;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			break;

		case 16654:
			itemDef.modelID = 47081;
			itemDef.name = "Elite Pernix cowl";
			itemDef.description = "Elite Pernix cowl";
			itemDef.modelZoom = 800;
			itemDef.modelRotationY = 532;
			itemDef.modelRotationX = 14;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 1;
			itemDef.maleWield = 47082;
			itemDef.femaleWield = 47082;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			// maleHeadModel1 = 79301;
			// femaleHeadModel1 = 79302;
			break;
		case 16655:
			itemDef.modelID = 47083;
			itemDef.name = "Elite Pernix body";
			itemDef.description = "Elite Pernix body";
			itemDef.modelZoom = 1378;
			itemDef.modelRotationY = 485;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 7;
			itemDef.maleWield = 47084;
			itemDef.femaleWield = 47084;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 16656:

			itemDef.modelID = 47085;
			itemDef.name = "Elite Pernix chaps";
			itemDef.description = "Elite Pernix chaps";
			itemDef.modelZoom = 1740;
			itemDef.modelRotationY = 504;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = 3;
			itemDef.maleWield = 47086;
			itemDef.femaleWield = 47086;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;


		case 19923:
			itemDef.name = "Groudon Pet";
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelID = 47087;
			itemDef.modelRotationY = 0;
			itemDef.modelRotationX = 0;
			itemDef.modelZoom = 1849;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 89;
			break;
 /*
		case 16657:
			defaultConfig(itemDef, 47088, 47089, 47090, "@zol@Aphrodite's helm");
			itemDef.modelID = 47088;
			itemDef.maleWield = 47089;
			itemDef.femaleWield = 47090;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "\"A piece of Aphrodite's beautiful armour, only the most beautiful can wear\"";
			itemDef.modelZoom = 676;
			itemDef.modelRotationY = 0;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -14;
			break;
		case 16658: 
			defaultConfig(itemDef, 47091, 47092, 47093, "@zol@Aphrodite's chestplate");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "\"A piece of Aphrodite's beautiful armour, only the most beautiful can wear\"";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;
		case 16659: 
			defaultConfig(itemDef, 47094, 47095, 47096, "@zol@Aphrodite's platelegs");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "\"A piece of Aphrodite's beautiful armour, only the most beautiful can wear\"";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			break;
		case 16660: 
			defaultConfig(itemDef, 47097, 47098, 47098, "@zol@Aphrodite's gauntlets");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "\"A piece of Aphrodite's beautiful armour, only the most beautiful can wear\"";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.stackable = false;
			break;
		case 16661: 
			defaultConfig(itemDef, 47099, 47099, 47099, "@zol@Aphrodite's greaves");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "\"A piece of Aphrodite's beautiful armour, only the most beautiful can wear\"";
			itemDef.modelZoom = forID11732itemDef.modelZoom;
			itemDef.modelOffset1 = forID11732itemDef.modelOffset1;
			itemDef.modelOffset2 = forID11732itemDef.modelOffset2;
			itemDef.modelRotationY = forID11732itemDef.modelRotationY;
			itemDef.modelRotationX = forID11732itemDef.modelRotationX;
			break; */

		case 16662: 
			defaultConfig(itemDef, 47100, 47101, 47101, "@zo1@Aphrodite Bow");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.description = "When you hear its whisper, it's already too late.";
			itemDef.modelZoom = 1506;
			itemDef.modelRotationY = 473;
			itemDef.modelRotationX = 2042;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.stackable = false;
			break;

		case 16663:
			itemDef.modelID = 47102;
			itemDef.name = "Aphrodite Cape";
			itemDef.description = "An co-oporation between Ima Katana & Zeven.";
			itemDef.modelZoom = 1579;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.modelRotationY = 533;
			itemDef.modelRotationY = 333;
			itemDef.femaleWield = 91274;
			itemDef.femaleWield = 91274;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 16664:
			defaultConfig(itemDef, 39, 40, "Infernal boots");
			itemDef.modelZoom = 750;
			itemDef.modelRotationY = 164;
			itemDef.modelRotationX = 248;
			itemDef.modelOffset1 = 2;
			itemDef.modelOffset2 = -5;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 12000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 16665:

			defaultConfig(itemDef, 37, 38, "Infernal gloves");
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modelRotationY = 1025;
			itemDef.modelRotationX = 776;
			itemDef.modelZoom = 995;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 47;
			break;
		case 16666:

			defaultConfig(itemDef, 31, 32, "Infernal hat");
			itemDef.modelZoom = 1236;
			itemDef.modelRotationY = 118;
			itemDef.modelRotationX = 10;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -12;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 45000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 16667:

			defaultConfig(itemDef, 41, 42, "Infernal kiteshield");
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 16668:

			defaultConfig(itemDef, 33, 34, "Infernal top");
			itemDef.modelZoom = 1358;
			itemDef.modelRotationY = 514;
			itemDef.modelRotationX = 2041;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 120000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 16669:

			defaultConfig(itemDef, 35, 36, "Infernal bottoms");
			itemDef.modelZoom = 1690;
			itemDef.modelRotationY = 435;
			itemDef.modelRotationX = 9;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 7;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 80000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;	



		case 13717:
			itemDef.modelID = 40920;
			itemDef.name = "Icy Spirit Shield";
			itemDef.description = "It's a spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 105;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 105;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 105;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 105;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 105;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 105;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 105;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 105;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 105;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 105;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 105;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 105;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13718:
			itemDef.modelID = 40920;
			itemDef.name = "Lava spirit shield";
			itemDef.description = "It's a spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 6073;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 6073;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 6073;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 6073;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 6073;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 6073;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 6073;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 6073;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 105;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 6073;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 6073;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 6073;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47052;
			itemDef.femaleWield = 47052;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Inspect";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13719:
			itemDef.modelID = 40920;
			itemDef.name = "Elite spirit shield";
			itemDef.description = "It's a spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 49950;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 49950;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 49950;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 49950;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 49950;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 49950;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 49950;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 49950;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 105;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 49950;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 49950;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 49950;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47051;
			itemDef.femaleWield = 47051;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13720:
			itemDef.modelID = 40920;
			itemDef.name = "Aqua spirit shield";
			itemDef.description = "It's a spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 34503;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 34503;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 34503;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 34503;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 34503;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 34503;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 34503;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 34503;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 105;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 34503;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 34503;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 34503;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47053;
			itemDef.femaleWield = 47053;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13721:
			itemDef.modelID = 47050;
			itemDef.name = "Candy spirit shield";
			itemDef.description = "It's a spirit shield";
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13722:
			itemDef.modelID = 47046;
			itemDef.name = "Lava spirit shield";
			itemDef.description = "It's a spirit shield";
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47061;
			itemDef.femaleWield = 47061;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13723:
			itemDef.modelID = 40920;
			itemDef.name = "Mystical Spirit Shield";
			itemDef.description = "It's a mystical spirit shield";
			itemDef.originalModelColors = new int[] { 32703, 33727, 34751, 35775, 36799, 37823, 38847, 39871, 43967,
					40895, 41919, 42943 };
			itemDef.modifiedModelColors = new int[] { 44635, 44612, 44606, 44615, 44641, 44564, 44575, 44618, 105,
					44603, 44570, 4500 };
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13724:
			itemDef.modelID = 40920;
			itemDef.name = "Deathly Spirit Shield";
			itemDef.description = "It's a deathly spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 959;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 1983;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 3007;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 4031;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 5055;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 6079;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 7103;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 8127;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 0;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 9151;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 11199;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 12223;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13725:
			itemDef.modelID = 40920;
			itemDef.name = "Lime Spirit Shield";
			itemDef.description = "It's a lime spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 22463;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 22463;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 22463;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 22463;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 22463;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 22463;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 22463;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 22463;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 127;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 22463;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 22463;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 22463;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13726:
			itemDef.modelID = 40920;
			itemDef.name = "Pink Spirit Shield";
			itemDef.description = "It's a pink spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 54207;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 54207;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 54207;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 54207;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 54207;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 54207;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 54207;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 54207;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 127;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 54207;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 54207;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 54207;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13727:
			itemDef.modelID = 40920;
			itemDef.name = "Green Spirit Shield";
			itemDef.description = "It's a green spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 22425;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 22425;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 22425;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 22425;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 22425;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 22425;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 22425;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 22425;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 127;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 22425;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 22425;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 22425;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 13728:
			itemDef.modelID = 40920;
			itemDef.name = "Purple Spirit Shield";
			itemDef.description = "It's a purple spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 52127;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 52127;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 52127;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 52127;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 52127;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 52127;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 52127;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 52127;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 127;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 52127;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 52127;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 52127;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		case 13729:
			itemDef.modelID = 40920;
			itemDef.name = "Lime Spirit Shield";
			itemDef.description = "It's a spirit shield";
			itemDef.modifiedModelColors = new int[13];
			itemDef.originalModelColors = new int[13];
			itemDef.modifiedModelColors[0] = 44635;
			itemDef.originalModelColors[0] = 17350;
			itemDef.modifiedModelColors[1] = 44612;
			itemDef.originalModelColors[1] = 17350;
			itemDef.modifiedModelColors[2] = 44606;
			itemDef.originalModelColors[2] = 17350;
			itemDef.modifiedModelColors[3] = 44615;
			itemDef.originalModelColors[3] = 17350;
			itemDef.modifiedModelColors[4] = 44641;
			itemDef.originalModelColors[4] = 17350;
			itemDef.modifiedModelColors[5] = 44564;
			itemDef.originalModelColors[5] = 17350;
			itemDef.modifiedModelColors[6] = 44575;
			itemDef.originalModelColors[6] = 17350;
			itemDef.modifiedModelColors[7] = 44618;
			itemDef.originalModelColors[7] = 17350;
			itemDef.modifiedModelColors[8] = 105;
			itemDef.originalModelColors[8] = 105;
			itemDef.modifiedModelColors[9] = 44603;
			itemDef.originalModelColors[9] = 17350;
			itemDef.modifiedModelColors[10] = 44570;
			itemDef.originalModelColors[10] = 17350;
			itemDef.modifiedModelColors[11] = 4500;
			itemDef.originalModelColors[11] = 17350;
			itemDef.modifiedModelColors[12] = 936;
			itemDef.originalModelColors[12] = 17350;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 4062:
			itemDef.name = "Inferno spirit shield";
			itemDef.description = "It's a Inferno spirit shield";
			itemDef.modelID = 56509;
			itemDef.modelZoom = 1616;
			itemDef.modelRotationY = 396;
			itemDef.modelRotationX = 1050;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 4;
			itemDef.maleWield = 47047;
			itemDef.femaleWield = 47047;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 17161:
			itemDef.modelID = 47017;

			itemDef.description = "Minigun";
			itemDef.name = "Minigun";
			itemDef.stackable = false;
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 47018;
			itemDef.femaleWield = 47018;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;

		case 11642:
			itemDef.modelID = 47000;// 86252;
			itemDef.maleWield = 47001;// 86253;
			itemDef.femaleWield = 47001;// 86253;
			itemDef.name = "@bla@Death@bla@@red@ Katana@red@";
			itemDef.description = "Katana.";
			itemDef.modelZoom = 2100;
			itemDef.modelRotationY = 336;
			itemDef.modelRotationX = 336;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = 17;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			break;

		case 17153:
			itemDef.modelID = 47002;
			itemDef.description = "";
			itemDef.name = "Lava Torva Platebody";
			itemDef.modelZoom = 1513;
			itemDef.modelRotationY = 566;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -8;
			itemDef.maleWield = 47003;
			itemDef.femaleWield = 47003;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 17154:
			itemDef.modelID = 47004;

			itemDef.description = "";
			itemDef.name = "Lava Torva Full Helm";
			itemDef.modelZoom = 928;
			itemDef.modelRotationY = 406;
			itemDef.modelRotationX = 2041;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -5;
			itemDef.maleWield = 47005;
			itemDef.femaleWield = 47005;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 17155:
			itemDef.modelID = 47006;

			itemDef.description = "";
			itemDef.name = "Lava Torva Platelegs";
			itemDef.modelZoom = 1550;
			itemDef.modelRotationY = 344;
			itemDef.modelRotationX = 186;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = 11;
			itemDef.maleWield = 47007;
			itemDef.femaleWield = 47007;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Check-charges";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;

		case 17156:
			itemDef.modelID = 47008;

			itemDef.description = "";
			itemDef.name = "Death Gloves";
			itemDef.maleWield = 47009;
			itemDef.femaleWield = 47009;
			itemDef.modelZoom = 830;
			itemDef.modelRotationY = 536;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 10000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 17157:
			itemDef.modelID = 47010;
			itemDef.description = "";
			itemDef.name = "Death Full Helm";
			itemDef.maleWield = 47011;
			itemDef.femaleWield = 47011;
			itemDef.modelZoom = 1010;
			itemDef.modelRotationY = 16;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 60000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 17158:
			itemDef.modelID = 47012;
			itemDef.maleWield = 47012;
			itemDef.femaleWield = 47012;
			itemDef.description = "";
			itemDef.name = "Death Boots";
			itemDef.modelZoom = 870;
			itemDef.modelRotationY = 184;
			itemDef.modelRotationX = 204;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 250;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 17159:
			itemDef.modelID = 47013;

			itemDef.description = "";
			itemDef.name = "Death Platelegs";
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 47014;
			itemDef.femaleWield = 47014;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 17160:
			itemDef.modelID = 47015;
			itemDef.description = "Soulflare";
			itemDef.name = "Soulflare";
			itemDef.stackable = false;
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 47016;
			itemDef.femaleWield = 47016;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17162:
			itemDef.modelID = 47019;
			itemDef.maleWield = 47020;
			itemDef.femaleWield = 47020;
			itemDef.description = "";
			itemDef.name = "Death Platebody";
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;

		case 17163:
			itemDef.modelID = 47021;

			itemDef.description = "";
			itemDef.name = "Raptor Helm";
			itemDef.maleWield = 47022;
			itemDef.femaleWield = 47022;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modelZoom = 1010;
			itemDef.modelRotationY = 16;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 60000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 17164:
			itemDef.modelID = 47023;

			itemDef.description = "";
			itemDef.name = "Raptor Platebody";
			itemDef.stackable = false;
			itemDef.modelZoom = 1827;
			itemDef.modelRotationY = 512;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 4;
			itemDef.maleWield = 47024;
			itemDef.femaleWield = 47024;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17165:
			itemDef.modelID = 47025;

			itemDef.description = "";
			itemDef.name = "Raptor Platelegs";
			itemDef.stackable = false;
			itemDef.modelZoom = 1827;
			itemDef.modelRotationY = 512;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 4;
			itemDef.maleWield = 47026;
			itemDef.femaleWield = 47026;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17166:
			itemDef.modelID = 47027;
			itemDef.maleWield = 47028;
			itemDef.femaleWield = 47028;
			itemDef.description = "";
			itemDef.name = "Flame Raptor Helm";
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			itemDef.modelZoom = 1010;
			itemDef.modelRotationY = 16;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = -4;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 60000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 17167:
			itemDef.modelID = 47029;

			itemDef.description = "";
			itemDef.name = "Flame Raptor Platebody";
			itemDef.stackable = false;
			itemDef.modelZoom = 1827;
			itemDef.modelRotationY = 512;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 4;
			itemDef.maleWield = 47030;
			itemDef.femaleWield = 47030;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17168:
			itemDef.modelID = 47031;
			itemDef.description = "";
			itemDef.name = "Flame Raptor Platelegs";
			itemDef.stackable = false;
			itemDef.modelZoom = 1827;
			itemDef.modelRotationY = 512;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 4;
			itemDef.maleWield = 47032;
			itemDef.femaleWield = 47032;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;

		case 17172:
			itemDef.modelID = 47033;

			itemDef.description = "";
			itemDef.name = "Legion Helm";

			itemDef.modelZoom = 1827;
			itemDef.modelRotationY = 512;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 4;
			itemDef.maleWield = 47033;
			itemDef.femaleWield = 47033;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 17173:
			itemDef.modelID = 47034;

			itemDef.description = "";
			itemDef.name = "Legion Platebody";

			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 47034;
			itemDef.femaleWield = 47034;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 17174:
			itemDef.modelID = 47035;

			itemDef.description = "";
			itemDef.name = "Legion Platelegs";

			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 47035;
			itemDef.femaleWield = 47035;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 17175:
			itemDef.modelID = 47036;

			itemDef.description = "";
			itemDef.name = "Corrupt Torva Boots";
			itemDef.stackable = false;
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 47036;
			itemDef.femaleWield = 47036;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17176:
			itemDef.modelID = 47037;

			itemDef.description = "";
			itemDef.name = "Corrupt Torva Gloves";
			itemDef.stackable = false;
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 47037;
			itemDef.femaleWield = 47037;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17177:
			itemDef.modelID = 47038;

			itemDef.description = "";
			itemDef.name = "Corrupt Torva Helm";
			itemDef.stackable = false;
			itemDef.modelZoom = 1827;
			itemDef.modelRotationY = 512;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 4;
			itemDef.maleWield = 47038;
			itemDef.femaleWield = 47038;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17178:
			itemDef.modelID = 47039;

			itemDef.description = "";
			itemDef.name = "Corrupt Torva Platebody";
			itemDef.stackable = false;
			itemDef.modelZoom = 1827;
			itemDef.modelRotationY = 512;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 4;
			itemDef.maleWield = 47039;
			itemDef.femaleWield = 47039;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17179:
			itemDef.modelID = 47040;

			itemDef.description = "";
			itemDef.name = "Corrupt Torva Platelegs";
			itemDef.stackable = false;
			itemDef.modelZoom = 1827;
			itemDef.modelRotationY = 512;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 4;
			itemDef.maleWield = 47040;
			itemDef.femaleWield = 47040;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17180:
			itemDef.modelID = 47041;

			itemDef.description = "";
			itemDef.name = "Corrupt Whip";
			itemDef.stackable = false;
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 47041;
			itemDef.femaleWield = 47041;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;
		case 17181:
			itemDef.modelID = 47042;

			itemDef.description = "";
			itemDef.name = "Corrupt Torva Wings";
			itemDef.stackable = false;
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 47042;
			itemDef.femaleWield = 47042;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Destroy";
			break;

		case 19914:
			itemDef.modelID = 46000;
			itemDef.description = "";
			itemDef.name = "Imperial Helm";
			itemDef.modelZoom = 720;
			itemDef.modelRotationY = 2005;
			itemDef.modelRotationX = 121;
			itemDef.modelOffset1 = 0;
			itemDef.femaleWield = 46001;
			itemDef.maleWield = 46001;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 19917:
			itemDef.modelID = 46002;
			itemDef.description = "";
			itemDef.name = "Imperial platebody";
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.maleWield = 46003;
			itemDef.femaleWield = 46003;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;
		case 19920:
			itemDef.modelID = 46004;
			itemDef.description = "";
			itemDef.name = "Imperial Platelegs";
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 228;
			itemDef.modelRotationX = 1830;
			itemDef.modelOffset1 = 5;
			itemDef.modelOffset2 = -55;
			itemDef.femaleWield = 46005;
			itemDef.maleWield = 46005;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.stackable = false;
			break;

	/*	case 20250:
			itemDef.modelID = 55057;
			itemDef.name = "@red@Amer@whi@ican @blu@Whip";
			itemDef.description = "It's a american whip.";
			itemDef.modelZoom = 900;
			itemDef.modelRotationY = 324;
			itemDef.modelRotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = 3;
			itemDef.maleWield = 55058;
			itemDef.femaleWield = 55058;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;*/
		case 290:
			itemDef.name = "All Vs One Box";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 6830:
			itemDef.name = "Inferno Box";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 12641:
			itemDef.name = "Dark Karth Set";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 7959:
			itemDef.name = "Death Set";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 7775:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 20666:
			itemDef.name = "Pet Yoshi";
			itemDef.modelID = 13850;
			itemDef.modelZoom = 976;
			itemDef.modelRotationY = 2042;
			itemDef.modelRotationX = 1892;
			itemDef.modelOffset1 = -20;
			itemDef.modelOffset2 = 0;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 1;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[4] = "Drop";
			break;

			/*
		case 20251:
			itemDef.modelID = 55055;
			itemDef.name = "@red@American @whi@Torva @blu@platelegs";
			itemDef.description = "It's a set of american torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.modelRotationY = 474;
			itemDef.modelRotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -5;
			itemDef.maleWield = 55056;
			itemDef.femaleWield = 55056;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 20252:
			itemDef.modelID = 55053;
			itemDef.name = "@red@American @whi@Torva @blu@Fullhelm";
			itemDef.description = "It's a american torva fullhelm.";
			itemDef.modelZoom = 676;
			itemDef.modelRotationY = 0;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -14;
			itemDef.maleWield = 55054;
			itemDef.femaleWield = 55054;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 20253:
			itemDef.modelID = 55051;
			itemDef.name = "@red@American @whi@Torva @blu@Platebody";
			itemDef.description = "It's a american torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -8;
			itemDef.modelZoom = 1513;
			itemDef.modelRotationY = 566;
			itemDef.modelRotationX = 0;
			itemDef.maleWield = 55052;
			itemDef.femaleWield = 55052;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 14556:
			itemDef.name = "@red@Amer@whi@ican @blu@Gloves";
			itemDef.description = "It's a pair of american gloves.";
			itemDef.modelZoom = 592;
			itemDef.modelRotationY = 323;
			itemDef.modelRotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 5;
			itemDef.modelID = 65532;
			itemDef.maleWield = 65533;
			itemDef.femaleWield = 65533;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 14557:
			itemDef.name = "@red@Amer@whi@ican @blu@Boots";
			itemDef.description = "It's a pair of american boots.";
			itemDef.modelID = 65531;
			itemDef.modelZoom = 855;
			itemDef.modelRotationY = 215;
			itemDef.modelRotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -32;
			itemDef.maleWield = 65530;
			itemDef.femaleWield = 65530;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 14558:
			itemDef.modelID = 65534;
			itemDef.name = "@red@Amer@whi@ican @blu@Wings";
			itemDef.description = "It's a pair of american wings.";
			itemDef.modelZoom = 1700;
			itemDef.modelRotationY = 500;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.maleWield = 65394;
			itemDef.femaleWield = 65394;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 3086:
			itemDef.modelID = 65375;
			itemDef.name = "American Bones";
			itemDef.description = "Bones";
			itemDef.modelZoom = 1410;
			itemDef.modelOffset1 = 2;
			itemDef.modelRotationX = 368;
			itemDef.modelRotationY = 400;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { "Bury", null, null, null, "Drop" };
			break;*/

		case 13689:
			itemDef.modelID = 45000;
			itemDef.name = "Silver Chain";
			itemDef.description = "Silver Chain";
			itemDef.modelZoom = 620;
			itemDef.modelOffset2 = 16;
			itemDef.modelOffset1 = 1;
			itemDef.modelRotationX = 68;
			itemDef.modelRotationY = 424;
			itemDef.maleWield = 283;
			itemDef.femaleWield = 283;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 13686:
			itemDef.modelID = 47349;
			itemDef.name = "Magma shield";
			itemDef.description = "kiteshield";
			itemDef.modelZoom = 1450;
			itemDef.modelOffset2 = -14;
			itemDef.modelOffset1 = -6;
			itemDef.modelRotationX = 1825;
			itemDef.modelRotationY = 344;
			itemDef.maleWield = 17120;
			itemDef.femaleWield = 17120;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wear", null, null, "Drop" };
			break;
		case 13687:
			itemDef.modelID = 2838;
			itemDef.name = "Magma battleaxe";
			itemDef.description = "Magma battleaxe";
			itemDef.modelZoom = 1660;
			itemDef.modelOffset2 = -3;
			itemDef.modelOffset1 = 1;
			itemDef.modelRotationX = 2020;
			itemDef.modelRotationY = 420;
			itemDef.maleWield = 3902;
			itemDef.femaleWield = 3902;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wield", null, null, "Drop" };
			break;

		case 11609:
			itemDef.modelID = 65211;
			itemDef.name = "Bullets";
			itemDef.description = "Bullets";
			itemDef.modelZoom = 720;
			itemDef.modelOffset2 = 5;
			itemDef.modelOffset1 = 3;
			itemDef.modelRotationX = 117;
			itemDef.modelRotationY = 477;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.stackable = true;
			break;
		case 6508:
			itemDef.modelID = 2426;
			itemDef.name = "Mega Mystery Box";
			itemDef.description = "Mega Mystery box";
			itemDef.modelZoom = 1180;
			itemDef.modelOffset2 = -14;
			itemDef.modelRotationX = 172;
			itemDef.modelRotationY = 160;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { "Open", null, null, null, null };
			itemDef.originalModelColors = new int[] { 12, 6, 63 };
			itemDef.modifiedModelColors = new int[] { 22410, 926, 2999 };
			itemDef.stackable = false;
			break;
		case 20690:
			itemDef.modelID = 65526;
			itemDef.name = "Darth Maul Whip";
			itemDef.description = "It's a darth maul whip.";
			itemDef.modelZoom = 900;
			itemDef.modelRotationY = 324;
			itemDef.modelRotationX = 1808;
			itemDef.modelOffset1 = -2;
			itemDef.modelOffset2 = 3;
			itemDef.maleWield = 65527;
			itemDef.femaleWield = 65527;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 14581:
			itemDef.modelID = 65524;
			itemDef.name = "Darth Maul Torva Platelegs";
			itemDef.description = "It's a set of darth maul torva platelegs.";
			itemDef.modelZoom = 1740;
			itemDef.modelRotationY = 474;
			itemDef.modelRotationX = 2045;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -5;
			itemDef.maleWield = 65525;
			itemDef.femaleWield = 65525;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 14583:
			itemDef.modelID = 65516;
			itemDef.name = "Darth Maul Torva Platebody";
			itemDef.description = "It's a darth maul torva platebody.";
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -8;
			itemDef.modelZoom = 1513;
			itemDef.modelRotationY = 566;
			itemDef.modelRotationX = 0;
			itemDef.maleWield = 65517;
			itemDef.femaleWield = 65517;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 14584:
			itemDef.name = "Darth Maul Gloves";
			itemDef.description = "It's a pair of darth maul gloves.";
			itemDef.modelZoom = 592;
			itemDef.modelRotationY = 323;
			itemDef.modelRotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 5;
			itemDef.modelID = 65521;
			itemDef.maleWield = 65520;
			itemDef.femaleWield = 65520;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;
		case 14585:
			itemDef.name = "Darth Maul Boots";
			itemDef.description = "It's a pair of darth maul boots.";
			itemDef.modelID = 65519;
			itemDef.modelZoom = 855;
			itemDef.modelRotationY = 215;
			itemDef.modelRotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -32;
			itemDef.maleWield = 65518;
			itemDef.femaleWield = 65518;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.description = "Some boots.";
			break;
		case 14586:
			itemDef.modelID = 65529;
			itemDef.name = "Darth Maul Wings";
			itemDef.description = "It's a pair of darth maul wings.";
			itemDef.modelZoom = 1700;
			itemDef.modelRotationY = 500;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -6;
			itemDef.modelOffset2 = 1;
			itemDef.maleWield = 65528;
			itemDef.femaleWield = 65528;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			break;

		/*case 14587:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors[0] = 933;
			itemDef.originalModelColors[0] = 7849;
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.modelRotationY = 72;
			itemDef.modelRotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.maleWield = -1;
			itemDef.femaleWield = -1;
			itemDef.name = "Gold santa hat";
			itemDef.description = "It's a gold santa hat.";
			break;*/
		/**case 14588:
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[4] = "Drop";
			itemDef.modifiedModelColors[0] = 926;
			itemDef.originalModelColors[0] = 7849;
			itemDef.modelID = 2635;
			itemDef.modelZoom = 440;
			itemDef.modelRotationY = 121;
			itemDef.modelRotationX = 1845;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 1;
			itemDef.maleWield = -1;
			itemDef.femaleWield = -1;
			itemDef.name = "Gold partyhat";
			itemDef.description = "It's a gold partyhat.";
			break;
*/
		case 14589:
			itemDef.modelID = 32565;
			itemDef.name = "Staff of gods";
			itemDef.description = "godly staff";
			itemDef.modelZoom = 1874;
			itemDef.modelOffset2 = 2;
			itemDef.modelOffset1 = -3;
			itemDef.modelRotationX = 1499;
			itemDef.modelRotationY = 292;
			itemDef.maleWield = 32566;
			itemDef.femaleWield = 2493;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.stackable = false;

			break;

		case 13207:
			itemDef.modelID = 65448;
			itemDef.name = "Purgatory Offhand";
			itemDef.description = "It's a purgatory offhand.";
			itemDef.modelZoom = 1519;
			itemDef.modelRotationY = 595;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.maleWield = 65447;
			itemDef.femaleWield = 65447;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.stackable = false;
			break;
		case 13209:
			itemDef.modelID = 65462;
			itemDef.name = "Purgatory Full Helmet";
			itemDef.description = "It's a purgatory full helmet.";
			itemDef.modelZoom = 1000;
			itemDef.modelRotationY = 595;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.maleWield = 65461;
			itemDef.femaleWield = 65461;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.stackable = false;
			break;
		case 13210:
			itemDef.modelID = 65460;
			itemDef.name = "Purgatory Platebody";
			itemDef.description = "It's a purgatory platebody.";
			itemDef.modelZoom = 1400;
			itemDef.modelRotationY = 595;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.maleWield = 65459;
			itemDef.femaleWield = 65459;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.stackable = false;
			break;
		case 13212:
			itemDef.modelID = 65458;
			itemDef.name = "Purgatory Platelegs";
			itemDef.description = "It's a pair of purgatory platelegs.";
			itemDef.modelZoom = 1500;
			itemDef.modelRotationY = 595;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.maleWield = 65457;
			itemDef.femaleWield = 65457;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.stackable = false;
			break;
		case 13213:
			itemDef.modelID = 65455;
			itemDef.name = "Purgatory Boots";
			itemDef.description = "It's a pair of purgatory boots.";
			itemDef.modelZoom = 855;
			itemDef.modelRotationY = 215;
			itemDef.modelRotationX = 94;
			itemDef.modelOffset1 = 4;
			itemDef.modelOffset2 = -32;
			itemDef.maleWield = 65455;
			itemDef.femaleWield = 65455;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.stackable = false;
			break;
		case 13214:
			itemDef.modelID = 65454;
			itemDef.name = "Purgatory Gauntlets";
			itemDef.description = "It's a pair of purgatory gauntlets.";
			itemDef.modelZoom = 592;
			itemDef.modelRotationY = 323;
			itemDef.modelRotationX = 1710;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = 5;
			itemDef.maleWield = 65453;
			itemDef.femaleWield = 65453;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.stackable = false;
			break;
		case 13661:
			itemDef.modelID = 65452;
			itemDef.name = "Purgatory Wings";
			itemDef.description = "It's a pair of purgatory wings.";
			itemDef.modelZoom = 1519;
			itemDef.modelRotationY = 595;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.maleWield = 65451;
			itemDef.femaleWield = 65451;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wear", null, null, "Drop" };
			itemDef.stackable = false;
			break;
		case 13662:
			itemDef.modelID = 65450;
			itemDef.name = "Purgatory Longsword";
			itemDef.description = "It's a purgatory longsword.";
			itemDef.modelZoom = 1519;
			itemDef.modelRotationY = 595;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 0;
			itemDef.maleWield = 65449;
			itemDef.femaleWield = 65449;
			itemDef.groundActions = new String[] { null, null, "Take", null, null };
			itemDef.itemActions = new String[] { null, "Wield", null, null, "Drop" };
			itemDef.stackable = false;
			break;

		case 13189:
			itemDef.name = "KBD Helm";
			itemDef.modelID = 27001;
			itemDef.maleWield = 27001;
			itemDef.femaleWield = 27001;
			itemDef.modelZoom = 789;
			itemDef.modelRotationY = 135;
			itemDef.modelRotationX = 123;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 0;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 150000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 10028:
			itemDef.name = "Starter Box";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;

		case 13299:
			itemDef.name = "KBD Platebody";
			itemDef.modelID = 27002;
			itemDef.maleWield = 27002;
			itemDef.femaleWield = 27002;
			itemDef.modelZoom = 1250;
			itemDef.modelRotationY = 488;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 0;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 2000000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 13206:
			itemDef.name = "KBD Platelegs";
			itemDef.modelID = 27003;
			itemDef.maleWield = 27003;
			itemDef.femaleWield = 27003;
			itemDef.modelZoom = 1740;
			itemDef.modelRotationY = 444;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -8;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 6;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 13300:
			itemDef.name = "KBD Boots";
			itemDef.modelID = 27004;
			itemDef.maleWield = 27004;
			itemDef.femaleWield = 27004;
			itemDef.modelZoom = 724;
			itemDef.modelRotationY = 171;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -7;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 289010;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 15177:
			itemDef.name = "KBD Gloves";
			itemDef.modelID = 27005;
			itemDef.maleWield = 27005;
			itemDef.femaleWield = 27005;
			itemDef.modelZoom = 789;
			itemDef.modelRotationY = 609;
			itemDef.modelRotationX = 111;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 100000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 10577:
			itemDef.name = "KBD Sword";
			itemDef.modelID = 27006;
			itemDef.maleWield = 27006;
			itemDef.femaleWield = 27006;
			itemDef.modelZoom = 860;
			itemDef.modelRotationY = 100;
			itemDef.modelRotationX = 1348;
			itemDef.modelOffset1 = -13;
			itemDef.modelOffset2 = -2;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 0;
			itemDef.modifiedModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 61;
			itemDef.originalModelColors = new int[1];
			itemDef.originalModelColors[0] = 36133;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;

		case 21777:
			itemDef.name = "Dragon Slayer Helm";
			itemDef.modelID = 25001;
			itemDef.maleWield = 25000;
			itemDef.femaleWield = 25000;
			itemDef.modelZoom = 779;
			itemDef.modelRotationY = 30;
			itemDef.modelRotationX = 1773;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = -3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 13208:
			itemDef.modelID = 65504;
			itemDef.maleWield = 65204; // done
			itemDef.femaleWield = 65204;
			itemDef.modelZoom = 2022;
			itemDef.name = "Malevolent 2H Sword";
			itemDef.description = "It's a Malevolent 2H Sword.";
			itemDef.modelZoom = 2022;
			itemDef.modelRotationY = 636;
			itemDef.modelRotationX = 1065;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = 3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 220000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.stackable = false;

			break;

		case 22123:
			itemDef.name = "Dragon Slayer Platebody";
			itemDef.modelID = 25003;
			itemDef.maleWield = 25002;
			itemDef.femaleWield = 25002;
			itemDef.modelZoom = 1250;
			itemDef.modelRotationY = 488;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 0;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 2000000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 21954:
			itemDef.name = "Dragon Slayer Platelegs";
			itemDef.modelID = 25005;
			itemDef.maleWield = 25004;
			itemDef.femaleWield = 25004;
			itemDef.modelZoom = 1740;
			itemDef.modelRotationY = 444;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -8;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 270000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 5020:
			itemDef.name = "1Bil Ticket";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Claim";
			break;
		case 5021:
			itemDef.name = "500Mil Ticket";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Claim";
			break;
		case 22099:
			itemDef.name = "Dragon Slayer Boots";
			itemDef.modelID = 25007;
			itemDef.maleWield = 25006;
			itemDef.femaleWield = 25006;
			itemDef.modelRotationY = 164;
			itemDef.modelRotationX = 156;
			itemDef.modelOffset1 = 3;
			itemDef.modelOffset2 = -3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 12500;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 22078:
			itemDef.name = "Dragon Slayer Wings";
			itemDef.modelID = 25009;
			itemDef.maleWield = 25008;
			itemDef.femaleWield = 25008;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 22301:
			itemDef.name = "Zaros Helmet";
			itemDef.modelID = 25010;
			itemDef.maleWield = 25011;
			itemDef.femaleWield = 25011;
			itemDef.modelZoom = 653;
			itemDef.modelRotationY = 539;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 1;
			itemDef.modelOffset2 = -3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 5000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 22304:
			itemDef.name = "Zaros Platebody";
			itemDef.modelID = 25012;
			itemDef.maleWield = 25013;
			itemDef.femaleWield = 25013;
			itemDef.modelZoom = 1250;
			itemDef.modelRotationY = 488;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 0;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 65000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 22307:
			itemDef.name = "Zaros Platelegs";
			itemDef.modelID = 25014;
			itemDef.maleWield = 25015;
			itemDef.femaleWield = 25015;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.modelZoom = 1740;
			itemDef.modelRotationY = 444;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -8;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 64000;
			itemDef.value = 270000;
			break;

		case 15011:
			itemDef.name = "Zaros Scythe";
			itemDef.modelID = 10000;
			itemDef.maleWield = 10001;
			itemDef.femaleWield = 10001;
			itemDef.modelZoom = 1579;
			itemDef.modelRotationY = 533;
			itemDef.modelRotationX = 333;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 10000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[4] = "Destroy";
			break;

		case 3273:
			itemDef.name = "Ice katana";
			itemDef.description = "Runity's ninjas only";
			itemDef.modelID = 25054;
			itemDef.maleWield = 25054;
			itemDef.femaleWield = 25054;
			itemDef.modelZoom = 2105;
			itemDef.modelRotationY = 431;
			itemDef.modelRotationX = 768;
			itemDef.modelOffset1 = 9;
			itemDef.modelOffset2 = 1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 24000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;

		case 3274:
			itemDef.name = "Phoenix bow";
			itemDef.modelID = 25059;
			itemDef.maleWield = 25060;
			itemDef.femaleWield = 25060;
			itemDef.modelZoom = 2000;
			itemDef.modelRotationY = 720;
			itemDef.modelRotationX = 1500;
			itemDef.modelOffset1 = -3;
			itemDef.modelOffset2 = 1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 90000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;

		case 3275:
			itemDef.name = "King Black Dragon Platebody";
			itemDef.modelID = 25057;
			itemDef.maleWield = 25057;
			itemDef.femaleWield = 25057;
			itemDef.modelZoom = 1250;
			itemDef.modelRotationY = 488;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 0;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 2000000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 3276:

			break;

		case 15300:
			itemDef.name = "Runity Wing Boots";
			itemDef.description = "You are now hired.";
			itemDef.modelID = 14623;
			itemDef.maleWield = 14623;
			itemDef.femaleWield = 14623;
			itemDef.modelRotationY = 209;
			itemDef.modelRotationX = 239;
			itemDef.modelZoom = 928;
			itemDef.modelOffset1 = -5;
			itemDef.modelOffset2 = -5;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			break;

		case 15307:
			itemDef.name = "Lime Wing Boots";
			itemDef.description = "Mysterious boots made of lime...";
			itemDef.modelZoom = 825;
			itemDef.modelOffset1 = -5;
			itemDef.modelOffset2 = -5;
			itemDef.modelRotationY = 209;
			itemDef.modelRotationX = 239;
			itemDef.modelID = 14623;
			itemDef.maleWield = 14623;
			itemDef.femaleWield = 14623;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 8334;
			itemDef.originalModelColors[0] = 17350;
			break;

		case 10860:
			itemDef.name = "Legendary Wing Boots";
			itemDef.description = "Mysterious boots made for legends...";
			itemDef.modelZoom = 769;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -5;
			itemDef.modelRotationY = 119;
			itemDef.modelRotationX = 169;
			itemDef.modelID = 14623;
			itemDef.maleWield = 14623;
			itemDef.femaleWield = 14623;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 8334;
			itemDef.originalModelColors[0] = 9528;
			break;

		case 10861:
			itemDef.name = "Icy Wing Boots";
			itemDef.description = "Mysterious boots made for legends...";
			itemDef.modelZoom = 769;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -5;
			itemDef.modelRotationY = 119;
			itemDef.modelRotationX = 169;
			itemDef.modelID = 14623;
			itemDef.maleWield = 14623;
			itemDef.femaleWield = 14623;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			itemDef.modifiedModelColors = new int[1];
			itemDef.originalModelColors = new int[1];
			itemDef.modifiedModelColors[0] = 8334;
			itemDef.originalModelColors[0] = 32703;
			break;

		case 12898:
			itemDef.name = "Anti-santa box.";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			itemDef.description = "Dayum dude, you got the big stacks in the bank!";
			itemDef.value = 0;
			break;

		case 15301:
			itemDef.name = "Bandos spear";
			itemDef.description = "New bandos item";
			itemDef.modelRotationY = 269;
			itemDef.modelRotationX = 1940;
			itemDef.modelZoom = 2000;
			itemDef.modelOffset1 = 23;
			itemDef.modelOffset2 = 27;
			itemDef.modelID = 14077;
			itemDef.maleWield = 14077;
			itemDef.femaleWield = 14077;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			break;
		case 15302:
			itemDef.name = "Mod Cape";
			itemDef.description = "For the trusted";
			itemDef.modelZoom = 1579;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.modelRotationY = 533;
			itemDef.modelRotationX = 333;
			itemDef.modelID = 14126;
			itemDef.maleWield = 14126;
			itemDef.femaleWield = 14126;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			break;
		case 15303:
			itemDef.name = "Administrator cape";
			itemDef.description = "For the loyal";
			itemDef.modelZoom = 1579;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.modelRotationY = 533;
			itemDef.modelRotationX = 333;
			itemDef.modelID = 14128;
			itemDef.maleWield = 14128;
			itemDef.femaleWield = 14128;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			break;
		case 15304:
			itemDef.name = "Owner cape";
			itemDef.description = "Thank you for donating! The most powerful cape in game ;)";
			itemDef.modelZoom = 2086;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 25;
			itemDef.modelRotationY = 333;
			itemDef.modelRotationX = 1030;
			itemDef.modelID = 25040;
			itemDef.maleWield = 25040;
			itemDef.femaleWield = 25040;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.itemActions[2] = "Teleport";
			itemDef.stackable = false;
			break;

		case 3929:
			itemDef.modelID = 47057;
			itemDef.name = "Owner Cape";
			itemDef.description = "It's a " + itemDef.name + ".";
			itemDef.modelZoom = 1579;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.modelRotationY = 533;
			itemDef.modelRotationX = 333;
			itemDef.femaleWield = 47058;
			itemDef.maleWield = 47058;
			itemDef.groundActions = new String[5];
			itemDef.groundActions[2] = "Take";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 15308:
			itemDef.name = "Rainbow Boots";
			itemDef.description = "Funky colors.";
			itemDef.modelID = 16831;
			itemDef.maleWield = 16831;
			itemDef.femaleWield = 16831;
			itemDef.modelRotationY = 533;
			itemDef.modelRotationX = 333;
			itemDef.modelZoom = 1579;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			break;

		case 15309:
			itemDef.name = "Rainbow Gnome Scarf";
			itemDef.description = "Funky colors, to keep you warm.";
			itemDef.modelID = 16829;
			itemDef.maleWield = 16830;
			itemDef.femaleWield = 16830;
			itemDef.modelRotationY = 533;
			itemDef.modelRotationX = 333;
			itemDef.modelZoom = 1579;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			break;

		case 15310:
			itemDef.name = "Adam's Dildo";
			itemDef.description = "I go hard when I use this.";
			itemDef.modelID = 20;
			itemDef.maleWield = 20;
			itemDef.femaleWield = 20;
			itemDef.modelRotationY = 533;
			itemDef.modelRotationX = 333;
			itemDef.modelZoom = 1579;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			itemDef.stackable = false;
			break;

			/** END OF CUSTOMS **/

		case 21795:
			itemDef.name = "Imbued zamorak cape";
			itemDef.modelID = 34166;
			itemDef.modelZoom = 2140;
			itemDef.modelRotationY = 424;
			itemDef.modelRotationX = 528;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -2;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 80000;
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 33;
			itemDef.modifiedModelColors[1] = 24;
			itemDef.originalModelColors = new int[2];
			itemDef.originalModelColors[0] = 16;
			itemDef.originalModelColors[1] = 12;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		case 13190:
			itemDef.name = "$10 Donator bond";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Redeem";
			itemDef.modelID = 29210;
			itemDef.modelZoom = 2300;
			itemDef.modelRotationY = 512;
			itemDef.originalModelColors = new int[] { 5652, 5652, 5652, 5652, 5652, 5652, 5652 };
			itemDef.modifiedModelColors = new int[] { 22464, 20416, 22451, 22181, 22449, 22305, 21435 };
			break;
		case 13191:
			itemDef.name = "$50 Donator bond";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Redeem";
			itemDef.modelID = 29210;
			itemDef.modelZoom = 2300;
			itemDef.modelRotationY = 512;
			itemDef.originalModelColors = new int[] { -21568, -21568, -21568, -21568, -21568, -21568, -21568 };
			itemDef.modifiedModelColors = new int[] { 22464, 20416, 22451, 22181, 22449, 22305, 21435 };
			break;
		case 13192:
			itemDef.name = "$100 Donator bond";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Redeem";
			itemDef.modelID = 29210;
			itemDef.modelZoom = 2300;
			itemDef.modelRotationY = 512;
			itemDef.originalModelColors = new int[] { 22464, 22464, 22464, 22464, 22464, 22464, 22464 };
			itemDef.modifiedModelColors = new int[] { 22464, 20416, 22451, 22181, 22449, 22305, 21435 };
			break;
		case 13193:
			itemDef.name = "$200 Donator bond";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Redeem";
			itemDef.modelID = 29210;
			itemDef.modelZoom = 2300;
			itemDef.modelRotationY = 512;
			itemDef.originalModelColors = new int[] { -31858, -31858, -31858, -31858, -31858, -31858, -31858 };
			itemDef.modifiedModelColors = new int[] { 22464, 20416, 22451, 22181, 22449, 22305, 21435 };
			break;
		case 13194:
			itemDef.name = "$500 Donator bond";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Redeem";
			itemDef.modelID = 29210;
			itemDef.modelZoom = 2300;
			itemDef.modelRotationY = 512;
			itemDef.originalModelColors = new int[] { 8125, 8125, 8125, 8125, 8125, 8125, 8125 };
			itemDef.modifiedModelColors = new int[] { 22464, 20416, 22451, 22181, 22449, 22305, 21435 };
			itemDef.certID = -1;
			itemDef.certTemplateID = -1;
			break;
		case 13111:
			itemDef.name = "Valyrian Sword";
			itemDef.description = "The most rarest sword, forged from great Valyrian steel.";

			break;
		case 6798:
			itemDef.name = "Arrowhead scroll";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Unlock";
			break;
		case 6799:
			itemDef.name = "Masterbaiter scroll";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Unlock";
			break;
		case 6800:
			itemDef.name = "Double wood scroll";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Unlock";
			break;
		case 6801:
			itemDef.name = "Little birdy scroll";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Unlock";
			break;
		case 6802:
			itemDef.name = "The rock scroll";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Unlock";
			break;
		case 6803:
			itemDef.name = "Flame on scroll";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Unlock";
			break;
		case 11941:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Check";
			break;
		case 7478:
			itemDef.itemActions = new String[5];
			itemDef.name = "Vote token";
			itemDef.description = "Exchange these tokens for a mystery box and vote points by speaking to Hopleez!";
			break;
		case 8038:
			itemDef.name = "Pet mystery box";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 3062:
		case 6828:
			itemDef.stackable = false;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;

		case 6199:
			itemDef.name = "Bronze mystery box";
			itemDef.modelID = 2426;
			itemDef.modelZoom = 1180;
			itemDef.modelRotationX = 172;
			itemDef.modelRotationY = 160;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			itemDef.modifiedModelColors = new int[] { 22410, 2999 };
			itemDef.originalModelColors = new int[] { 5652, 7050 };
			break;
		case 12955:
			itemDef.name = "Silver mystery box";
			itemDef.modelID = 2426;
			itemDef.modelZoom = 1180;
			itemDef.modelRotationX = 172;
			itemDef.modelRotationY = 160;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			itemDef.modifiedModelColors = new int[] { 22410, 2999 };
			itemDef.originalModelColors = new int[] { 115, 61 };
			break;

		case 11739:
			itemDef.name = "Gold mystery box";
			itemDef.modelID = 2426;
			itemDef.modelZoom = 1180;
			itemDef.modelRotationX = 172;
			itemDef.modelRotationY = 160;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			itemDef.modifiedModelColors = new int[] { 22410, 2999 };
			itemDef.originalModelColors = new int[] { 8128, 6073 };
			break;

		case 21216:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "White whip"; // Name
			itemDef.description = "A white whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 100;
			break;
		case 21217:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Iron whip"; // Name
			itemDef.description = "A Iron whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 20;
			break;
		case 21218:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Steel whip"; // Name
			itemDef.description = "A Steel whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 70;
			break;
		case 21219:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Black whip"; // Name
			itemDef.description = "A Black whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 0;
			break;
		case 21220:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Mithril whip"; // Name
			itemDef.description = "A Mithril whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 43297;
			break;
		case 21221:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Adamant whip"; // Name
			itemDef.description = "A Adamant whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 21662;
			break;
		case 21222:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Rune whip"; // Name
			itemDef.description = "A Rune whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 36133;
			break;
		case 21223:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Dragon whip"; // Name
			itemDef.description = "A Dragon whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 926;
			break;
		case 21224:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Barrows whip"; // Name
			itemDef.description = "A Barrows whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 5652;
			break;
		case 21225:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Lime whip"; // Name
			itemDef.description = "A Lime whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 17350;
			break;
		case 21292:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Lime whip (u)"; // Name
			itemDef.description = "A Lime whip upgraded.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 17350;
			break;
		case 21294:
			itemDef.name = "Ice katana (u)";
			itemDef.description = "Runity's ninjas only";
			itemDef.modelID = 25054;
			itemDef.maleWield = 25054;
			itemDef.femaleWield = 25054;
			itemDef.modelZoom = 2105;
			itemDef.modelRotationY = 431;
			itemDef.modelRotationX = 768;
			itemDef.modelOffset1 = 9;
			itemDef.modelOffset2 = 1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 24000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;

		case 21226:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Pink whip"; // Name
			itemDef.description = "A Pink whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 257770;
			break;
		case 17804:
			itemDef.name = "Zaros godsword";
			itemDef.modelZoom = 1957;
			itemDef.modelID = 9004;
			itemDef.maleWield = 9004;
			itemDef.femaleWield = 9004;
			itemDef.modelZoom = 1957;
			itemDef.modelRotationY = 498;
			itemDef.modelRotationX = 484;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = -1;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 1250000;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.itemActions[2] = "Dismantle";
			break;

		case 11063:
			itemDef.name = "Toxic Glaive";
			itemDef.modelZoom = 1579;
			itemDef.modelOffset1 = -4;
			itemDef.modelOffset2 = 0;
			itemDef.modelRotationY = 533;
			itemDef.modelRotationX = 333;
			itemDef.modelID = 24005;
			itemDef.maleWield = 24004;
			itemDef.femaleWield = 24004;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.value = 1250000;
			break;

		case 21227:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Elemental whip"; // Name
			itemDef.description = "A Elemental whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 51120;
			break;
		case 12006:
			itemDef.name = "Abyssal whip(p+)"; // Name
			itemDef.description = "A Poisoned a Abbysal whip.";
			break;
			// Pickup the fucking phone you mother fucker
		case 21228:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			itemDef.name = "Frost whip"; // Name
			itemDef.description = "A Frost whip.";
			itemDef.modelRotationY = 280;
			itemDef.modelRotationX = 0;
			itemDef.modelOffset1 = -1;
			itemDef.modelOffset2 = 56;
			itemDef.modelID = 5412;
			itemDef.maleWield = 5409;
			itemDef.femaleWield = 5409;
			itemDef.modelZoom = 840;
			itemDef.originalModelColors = new int[2];
			itemDef.modifiedModelColors = new int[2];
			itemDef.modifiedModelColors[0] = 528;
			itemDef.originalModelColors[0] = 34770;
			break;
		case 12789:
			itemDef.name = "Starter Box V2";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 6640:
			itemDef.name = "Blood Crystal";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 6833:
			itemDef.name = "All Vs One V3";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 22092:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;
		case 13654:
			itemDef.name = "Soul set";
			itemDef.itemActions[0] = "Open";
			break;
		case 12792:
			itemDef.name = "Chi set";
			itemDef.itemActions[0] = "Open";
			break;
		case 12793:
			itemDef.name = "Zaros set";
			itemDef.itemActions[0] = "Open";
			break;
		case 12794:
			itemDef.name = "Elite Sets";
			itemDef.itemActions[0] = "Open";
			break;
		case 13438:
			itemDef.name = "Purgatory Sets";
			itemDef.itemActions[0] = "Open";
			break;
		case 5509:
		case 5510:
		case 5512:
		case 5514:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Fill";
			itemDef.itemActions[2] = "Empty";
			break;

		case 5733:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Yum";
			break;

		case 4155:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Check";
			break;
		case 15098:
			itemDef.name = "Dice (up to 100)";
			itemDef.modelID = 47852;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Private-roll";
			itemDef.itemActions[2] = "Clan-roll";
			itemDef.modelRotationX = 215;
			itemDef.modelRotationY = 94;
			itemDef.modelZoom = 1104;
			itemDef.brightness = 25;
			break;
		case 6831:
			itemDef.name = "Mime Box";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			itemDef.description = "Contains a random reward of mime costume, mime emote or 75k coins.";
			break;
		case 6832:
			itemDef.name = "Drill Demon Box";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			itemDef.description = "Contains a random reward of drill demon costume, drill demon emote or 75k coins.";
			break;
		case 13307:
			itemDef.name = "Credits";
			itemDef.description = "Lovely credits!";
			break;
		case 12897:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			itemDef.name = "Clan Showcase Box";
			itemDef.description = "Opening this box will award a random showcase item based on the clan's level.";
			break;
		case 6854:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			itemDef.name = "Clan Resource Box";
			itemDef.description = "Inside this box contains random resources for the clan's bank.";
			break;
		case 2568:
			itemDef.itemActions[2] = "Check charges";
			break;
		case 13188:
			itemDef.name = "Dragon claws";
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wield";
			break;
		case 8013:
			itemDef.name = "Home teleport";
			break;
		case 20527:
			itemDef.name = "Donate Pet tokens";
			itemDef.description = "Used to purchase from the Donator Pet Store";
			itemDef.stackable = true;
			break;

		case 12746:
		case 12748:
		case 12749:
		case 12750:
		case 12751:
		case 12752:
		case 12753:
		case 12754:
		case 12755:
		case 12756:
			itemDef.itemActions[0] = "Claim";
			break;
		case 10834:
			itemDef.name = "@gre@100M Cash Bag";
			itemDef.itemActions[0] = "Claim";
			itemDef.description = "Can be claimed for 100 Mill Coins";
			itemDef.stackable = true;
			break;

		case 10835:
			itemDef.name = "@gre@500M Cash Bag";
			itemDef.itemActions[0] = "Claim";
			itemDef.description = "Can be claimed for 500 Mill Coins";
			itemDef.stackable = true;
			break;
		case 11188:
			itemDef.name = "2x experience (1hr)";
			itemDef.description = "This lamp will give clan members double experience for a random skill for 1 hour.";
			break;
		case 11189:
			itemDef.name = "2x experience (3hr)";
			itemDef.description = "This lamp will give clan members double experience for a random skill for 3 hour.";
			break;
		case 11679:
			itemDef.name = "2x experience (5hr)";
			itemDef.description = "This lamp will give clan members double experience for a random skill for 5 hour.";
			break;
		case 11187:
			itemDef.name = "250k experience lamp";
			itemDef.description = "This lamp will give clan members 250,000 experience for a random skill.";
			break;
		case 4447:
			itemDef.name = "500k experience lamp";
			itemDef.description = "This lamp will give clan members 500,000 experience for a random skill.";
			break;
		case 6543:
			itemDef.name = "Drop rate increase (1.5x)";
			itemDef.description = "This lamp will give clan members a 1x drop rate increase for a random boss. (15min)";
			break;
		case 7498:
			itemDef.name = "Drop rate increase (2x)";
			itemDef.description = "This lamp will give clan members a 3x drop rate increase for a random boss. (15min)";
			break;
		case 11137:
			itemDef.name = "Drop rate increase (2.5x)";
			itemDef.description = "This lamp will give clan members a 5x drop rate increase for a random boss. (15min)";
			break;
		case 11139:
			itemDef.name = "2x resource (15min)";
			itemDef.description = "This lamp will give clan members double resource rewards for 15 minutes.";
			break;
		case 12690:// Armour sets
		case 12873:
		case 12875:
		case 12877:
		case 12879:
		case 12881:
		case 12883:
		case 12962:
		case 12972:
		case 12974:
		case 12984:
		case 12986:
		case 12988:
		case 12990:
		case 13000:
		case 13002:
		case 13012:
		case 13014:
		case 13024:
		case 13026:
		case 11738:
		case 9666:
		case 9670:
		case 12865:
		case 12867:
		case 12869:
		case 12871:
		case 12966:
		case 12964:
		case 12968:
		case 12970:
		case 12976:
		case 12978:
		case 12980:
		case 12982:
		case 12992:
		case 12994:
		case 12996:
		case 12998:
		case 13004:
		case 13006:
		case 13008:
		case 13010:
		case 13016:
		case 13018:
		case 13020:
		case 13022:
		case 13028:
		case 13030:
		case 13032:
		case 13034:
		case 13036:
		case 13038:
		case 12960:
		case 13173:
		case 13175:
		case 13064:
		case 13066:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Unpack";
			break;
		case 455:
			itemDef.name = "Golden Scratch Card";
			break;
			
		case 21807:
		case 21813:
		case 21810:
			itemDef.itemActions = new String[5];
			itemDef.itemActions[0] = "Open";
			break;

		case 1050:
			itemDef.name = "Santa hat";
			itemDef.modelID = 2537;
			itemDef.modelZoom = 540;
			itemDef.modelRotationY = 72;
			itemDef.modelRotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 160;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;

		case 13343:
			itemDef.modelZoom = 540;
			itemDef.modelRotationY = 72;
			itemDef.modelRotationX = 136;
			itemDef.modelOffset1 = 0;
			itemDef.modelOffset2 = -3;
			itemDef.anInt167 = 128;
			itemDef.anInt192 = 128;
			itemDef.anInt191 = 128;
			itemDef.value = 160;
			itemDef.itemActions = new String[5];
			itemDef.itemActions[1] = "Wear";
			break;
		}

		if (itemDef.certTemplateID != -1)
			itemDef.toNote();
		return itemDef;
	}

	private void toNote() {
		ItemDefinition noted = lookup(certTemplateID);
		modelID = noted.modelID;
		modelZoom = noted.modelZoom;
		modelRotationY = noted.modelRotationY;
		modelRotationX = noted.modelRotationX;

		anInt204 = noted.anInt204;
		modelOffset1 = noted.modelOffset1;
		modelOffset2 = noted.modelOffset2;
		modifiedModelColors = noted.modifiedModelColors; // here Adam
		originalModelColors = noted.originalModelColors;
		ItemDefinition unnoted = lookup(certID);
		name = unnoted.name;
		membersObject = unnoted.membersObject;
		value = unnoted.value;
		String s = "a";
		char c = unnoted.name.charAt(0);
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
			s = "an";
		description = "Swap this note at any bank for " + s + " " + unnoted.name + ".";
		stackable = true;
	}

	public static Sprite getSprite(int item, int amount, int k) {
		if (k == 0) {
			Sprite sprite = (Sprite) mruNodes1.get(item);
			if (sprite != null && sprite.resizeHeight != amount && sprite.resizeHeight != -1) {

				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}
		ItemDefinition itemDef = lookup(item);
		if (itemDef.stackIDs == null)
			amount = -1;
		if (amount > 1) {
			int i1 = -1;
			for (int j1 = 0; j1 < 10; j1++)
				if (amount >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];

			if (i1 != -1)
				itemDef = lookup(i1);
		}
		Model model = itemDef.method201(1);
		if (model == null)
			return null;
		Sprite sprite = null;
		if (itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if (sprite == null)
				return null;
		}
		Sprite enabledSprite = new Sprite(32, 32);
		int k1 = Rasterizer.textureInt1;
		int l1 = Rasterizer.textureInt2;
		int ai[] = Rasterizer.anIntArray1472;
		int ai1[] = Raster.pixels;
		int i2 = Raster.width;
		int j2 = Raster.height;
		int k2 = Raster.topX;
		int l2 = Raster.bottomX;
		int i3 = Raster.topY;
		int j3 = Raster.bottomY;
		Rasterizer.aBoolean1464 = false;
		Raster.initDrawingArea(enabledSprite.raster, 32, 32);
		Raster.fillRectangle(0, 0, 32, 32, 0);
		Raster.resetDepthBuffer();
		Rasterizer.method364();
		int k3 = itemDef.modelZoom;
		if (k == -1)
			k3 = (int) ((double) k3 * 1.5D);
		if (k > 0)
			k3 = (int) ((double) k3 * 1.04D);
		int l3 = Rasterizer.anIntArray1470[itemDef.modelRotationY] * k3 >> 16;
			int i4 = Rasterizer.anIntArray1471[itemDef.modelRotationY] * k3 >> 16;
		model.method482(itemDef.modelRotationX, itemDef.anInt204, itemDef.modelRotationY, itemDef.modelOffset1,
				l3 + model.modelHeight / 2 + itemDef.modelOffset2, i4 + itemDef.modelOffset2);
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--)
				if (enabledSprite.raster[i5 + j4 * 32] == 0)
					if (i5 > 0 && enabledSprite.raster[(i5 - 1) + j4 * 32] > 1)
						enabledSprite.raster[i5 + j4 * 32] = 1;
					else if (j4 > 0 && enabledSprite.raster[i5 + (j4 - 1) * 32] > 1)
						enabledSprite.raster[i5 + j4 * 32] = 1;
					else if (i5 < 31 && enabledSprite.raster[i5 + 1 + j4 * 32] > 1)
						enabledSprite.raster[i5 + j4 * 32] = 1;
					else if (j4 < 31 && enabledSprite.raster[i5 + (j4 + 1) * 32] > 1)
						enabledSprite.raster[i5 + j4 * 32] = 1;

		}

		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--)
					if (enabledSprite.raster[j5 + k4 * 32] == 0)
						if (j5 > 0 && enabledSprite.raster[(j5 - 1) + k4 * 32] == 1)
							enabledSprite.raster[j5 + k4 * 32] = k;
						else if (k4 > 0 && enabledSprite.raster[j5 + (k4 - 1) * 32] == 1)
							enabledSprite.raster[j5 + k4 * 32] = k;
						else if (j5 < 31 && enabledSprite.raster[j5 + 1 + k4 * 32] == 1)
							enabledSprite.raster[j5 + k4 * 32] = k;
						else if (k4 < 31 && enabledSprite.raster[j5 + (k4 + 1) * 32] == 1)
							enabledSprite.raster[j5 + k4 * 32] = k;

			}

		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (enabledSprite.raster[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0
					&& enabledSprite.raster[(k5 - 1) + (l4 - 1) * 32] > 0)
						enabledSprite.raster[k5 + l4 * 32] = 0x302020;

			}

		}
		if (itemDef.certTemplateID != -1) {
			int l5 = sprite.resizeWidth;
			int j6 = sprite.resizeHeight;
			sprite.resizeWidth = 32;
			sprite.resizeHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.resizeWidth = l5;
			sprite.resizeHeight = j6;
		}
		if (k == 0)
			mruNodes1.put(enabledSprite, item);
		Raster.initDrawingArea(ai1, i2, j2);
		Raster.setDrawingArea(k2, i3, l2, j3);
		Rasterizer.textureInt1 = k1;
		Rasterizer.textureInt2 = l1;
		Rasterizer.anIntArray1472 = ai;
		Rasterizer.aBoolean1464 = true;
		if (itemDef.stackable)
			enabledSprite.resizeWidth = 33;
		else
			enabledSprite.resizeWidth = 32;
		enabledSprite.resizeHeight = amount;
		return enabledSprite;
	}

	public static Sprite getSprite(int item, int amount, int k, int zoom) {
		if (k == 0) {
			Sprite sprite = (Sprite) mruNodes1.get(item);
			if (sprite != null && sprite.resizeHeight != amount && sprite.resizeHeight != -1) {

				sprite.unlink();
				sprite = null;
			}
			if (sprite != null)
				return sprite;
		}
		ItemDefinition itemDef = lookup(item);
		if (itemDef.stackIDs == null)
			amount = -1;
		if (amount > 1) {
			int i1 = -1;
			for (int j1 = 0; j1 < 10; j1++)
				if (amount >= itemDef.stackAmounts[j1] && itemDef.stackAmounts[j1] != 0)
					i1 = itemDef.stackIDs[j1];

			if (i1 != -1)
				itemDef = lookup(i1);
		}
		Model model = itemDef.method201(1);
		if (model == null)
			return null;
		Sprite sprite = null;
		if (itemDef.certTemplateID != -1) {
			sprite = getSprite(itemDef.certID, 10, -1);
			if (sprite == null)
				return null;
		}
		Sprite enabledSprite = new Sprite(32, 32);
		int k1 = Rasterizer.textureInt1;
		int l1 = Rasterizer.textureInt2;
		int ai[] = Rasterizer.anIntArray1472;
		int ai1[] = Raster.pixels;
		int i2 = Raster.width;
		int j2 = Raster.height;
		int k2 = Raster.topX;
		int l2 = Raster.bottomX;
		int i3 = Raster.topY;
		int j3 = Raster.bottomY;
		Rasterizer.aBoolean1464 = false;
		Raster.initDrawingArea(enabledSprite.raster, 32, 32);
		Raster.fillRectangle(0, 0, 32, 32, 0);
		Raster.resetDepthBuffer();
		Rasterizer.method364();
		int k3 = itemDef.modelZoom;
		if (k == -1)
			k3 = (int) ((double) k3 * 1.5D);
		if (k > 0)
			k3 = (int) ((double) k3 * 1.04D);
		int l3 = Rasterizer.anIntArray1470[itemDef.modelRotationY] * k3 >> 16;
		int i4 = Rasterizer.anIntArray1471[itemDef.modelRotationY] * k3 >> 16;
		model.method482(itemDef.modelRotationX, itemDef.anInt204, itemDef.modelRotationY, itemDef.modelOffset1,
				l3 + model.modelHeight / 2 + itemDef.modelOffset2, i4 + itemDef.modelOffset2);
		for (int i5 = 31; i5 >= 0; i5--) {
			for (int j4 = 31; j4 >= 0; j4--)
				if (enabledSprite.raster[i5 + j4 * 32] == 0)
					if (i5 > 0 && enabledSprite.raster[(i5 - 1) + j4 * 32] > 1)
						enabledSprite.raster[i5 + j4 * 32] = 1;
					else if (j4 > 0 && enabledSprite.raster[i5 + (j4 - 1) * 32] > 1)
						enabledSprite.raster[i5 + j4 * 32] = 1;
					else if (i5 < 31 && enabledSprite.raster[i5 + 1 + j4 * 32] > 1)
						enabledSprite.raster[i5 + j4 * 32] = 1;
					else if (j4 < 31 && enabledSprite.raster[i5 + (j4 + 1) * 32] > 1)
						enabledSprite.raster[i5 + j4 * 32] = 1;

		}

		if (k > 0) {
			for (int j5 = 31; j5 >= 0; j5--) {
				for (int k4 = 31; k4 >= 0; k4--)
					if (enabledSprite.raster[j5 + k4 * 32] == 0)
						if (j5 > 0 && enabledSprite.raster[(j5 - 1) + k4 * 32] == 1)
							enabledSprite.raster[j5 + k4 * 32] = k;
						else if (k4 > 0 && enabledSprite.raster[j5 + (k4 - 1) * 32] == 1)
							enabledSprite.raster[j5 + k4 * 32] = k;
						else if (j5 < 31 && enabledSprite.raster[j5 + 1 + k4 * 32] == 1)
							enabledSprite.raster[j5 + k4 * 32] = k;
						else if (k4 < 31 && enabledSprite.raster[j5 + (k4 + 1) * 32] == 1)
							enabledSprite.raster[j5 + k4 * 32] = k;

			}

		} else if (k == 0) {
			for (int k5 = 31; k5 >= 0; k5--) {
				for (int l4 = 31; l4 >= 0; l4--)
					if (enabledSprite.raster[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0
					&& enabledSprite.raster[(k5 - 1) + (l4 - 1) * 32] > 0)
						enabledSprite.raster[k5 + l4 * 32] = 0x302020;

			}

		}
		if (itemDef.certTemplateID != -1) {
			int l5 = sprite.resizeWidth;
			int j6 = sprite.resizeHeight;
			sprite.resizeWidth = 32;
			sprite.resizeHeight = 32;
			sprite.drawSprite(0, 0);
			sprite.resizeWidth = l5;
			sprite.resizeHeight = j6;
		}
		if (k == 0)
			mruNodes1.put(enabledSprite, item);
		Raster.initDrawingArea(ai1, i2, j2);
		Raster.setDrawingArea(k2, i3, l2, j3);
		Rasterizer.textureInt1 = k1;
		Rasterizer.textureInt2 = l1;
		Rasterizer.anIntArray1472 = ai;
		Rasterizer.aBoolean1464 = true;
		if (itemDef.stackable)
			enabledSprite.resizeWidth = 33;
		else
			enabledSprite.resizeWidth = 32;
		enabledSprite.resizeHeight = amount;
		return enabledSprite;
	}

	public Model method201(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];

			if (j != -1)
				return lookup(j).method201(1);
		}
		Model model = (Model) mruNodes2.get(id);
		if (model != null)
			return model;
		model = Model.getModel(modelID);
		if (model == null)
			return null;
		if (anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
			model.method478(anInt167, anInt191, anInt192);
		if (modifiedModelColors != null) {
			for (int l = 0; l < modifiedModelColors.length; l++)
				model.recolor(modifiedModelColors[l], originalModelColors[l]);

		}
		model.light(64 + brightness, 768 + anInt184, -50, -10, -50, true);
		model.aBoolean1659 = true;
		mruNodes2.put(model, id);
		return model;
	}

	public Model method202(int i) {
		if (stackIDs != null && i > 1) {
			int j = -1;
			for (int k = 0; k < 10; k++)
				if (i >= stackAmounts[k] && stackAmounts[k] != 0)
					j = stackIDs[k];

			if (j != -1)
				return lookup(j).method202(1);
		}
		Model model = Model.getModel(modelID);
		if (model == null)
			return null;
		if (modifiedModelColors != null) {
			for (int l = 0; l < modifiedModelColors.length; l++)
				model.recolor(modifiedModelColors[l], originalModelColors[l]);

		}
		return model;
	}

	// private void readValues(Buffer stream) {
	// do {
	// int opcode = stream.readUByte();
	// if (opcode == 0)
	// return;
	// if (opcode == 1)
	// modelID = stream.readUShort();
	// else if (opcode == 2)
	// name = stream.readString();
	// else if (opcode == 3)
	// description = stream.readString();
	// else if (opcode == 4)
	// modelZoom = stream.readUShort();
	// else if (opcode == 5)
	// modelRotationX = stream.readUShort();
	// else if (opcode == 6)
	// modelRotationY = stream.readUShort();
	// else if (opcode == 7) {
	// modelOffset1 = stream.readUShort();
	// if (modelOffset1 > 32767)
	// modelOffset1 -= 0x10000;
	// } else if (opcode == 8) {
	// modelOffset2 = stream.readUShort();
	// if (modelOffset2 > 32767)
	// modelOffset2 -= 0x10000;
	// } else if (opcode == 11)
	// stackable = true;
	// else if (opcode == 12)
	// value = stream.readInt();
	// else if (opcode == 16)
	// membersObject = true;
	// else if (opcode == 23) {
	// maleWield = stream.readUShort();
	// aByte205 = stream.readSignedByte();
	// } else if (opcode == 24)
	// anInt188 = stream.readUShort();
	// else if (opcode == 25) {
	// femaleWield = stream.readUShort();
	// aByte154 = stream.readSignedByte();
	// } else if (opcode == 26)
	// anInt164 = stream.readUShort();
	// else if (opcode >= 30 && opcode < 35) {
	// if (groundActions == null)
	// groundActions = new String[5];
	// groundActions[opcode - 30] = stream.readString();
	// if (groundActions[opcode - 30].equalsIgnoreCase("hidden"))
	// groundActions[opcode - 30] = null;
	// } else if (opcode >= 35 && opcode < 40) {
	// if (itemActions == null)
	// itemActions = new String[5];
	// itemActions[opcode - 35] = stream.readString();
	// } else if (opcode == 40) {
	// int j = stream.readUByte();
	// originalModelColors = new int[j];
	// modifiedModelColors = new int[j];
	// for (int k = 0; k < j; k++) {
	// originalModelColors[k] = stream.readUShort();
	// modifiedModelColors[k] = stream.readUShort();
	// }
	// } else if (opcode == 41) {
	// int j = stream.readUByte();
	// originalTexture = new int[j];
	// modifiedTexture = new int[j];
	// for (int k = 0; k < j; k++) {
	// originalTexture[k] = stream.readUShort();
	// modifiedTexture[k] = stream.readUShort();
	// }
	// } else if (opcode == 42) {
	// shiftDrop = stream.readUShort();
	// } else if (opcode == 65) {
	// stockMarket = true;
	// } else if (opcode == 78)
	// anInt185 = stream.readUShort();
	// else if (opcode == 79)
	// anInt162 = stream.readUShort();
	// else if (opcode == 90)
	// anInt175 = stream.readUShort();
	// else if (opcode == 91)
	// anInt197 = stream.readUShort();
	// else if (opcode == 92)
	// anInt166 = stream.readUShort();
	// else if (opcode == 93)
	// anInt173 = stream.readUShort();
	// else if (opcode == 95)
	// anInt204 = stream.readUShort();
	// else if (opcode == 97)
	// certID = stream.readUShort();
	// else if (opcode == 98)
	// certTemplateID = stream.readUShort();
	// else if (opcode >= 100 && opcode < 110) {
	// if (stackIDs == null) {
	// stackIDs = new int[10];
	// stackAmounts = new int[10];
	// }
	// stackIDs[opcode - 100] = stream.readUShort();
	// stackAmounts[opcode - 100] = stream.readUShort();
	// } else if (opcode == 110)
	// anInt167 = stream.readUShort();
	// else if (opcode == 111)
	// anInt192 = stream.readUShort();
	// else if (opcode == 112)
	// anInt191 = stream.readUShort();
	// else if (opcode == 113)
	// brightness = stream.readSignedByte();
	// else if (opcode == 114)
	// anInt184 = stream.readSignedByte();
	// else if (opcode == 115)
	// team = stream.readUByte();
	// else if (opcode == 139)
	// stream.readUShort();
	// else if (opcode == 140)
	// stream.readUShort();
	//
	// /*else if (opcode == 148)
	// opcode148 = stream.readUShort();
	// else if (opcode == 149)
	// opcode149 = stream.readUShort();*/
	//
	// } while (true);
	// }

	private void readValues(Buffer buffer) {
		do {
			int opcode = buffer.readUByte();
			if (opcode == 0)
				return;
			if (opcode == 1)
				modelID = buffer.readUShort();
			else if (opcode == 2)
				name = buffer.readString();
			else if (opcode == 3)
				description = buffer.readString();
			else if (opcode == 4)
				modelZoom = buffer.readUShort();
			else if (opcode == 5)
				modelRotationY = buffer.readUShort();
			else if (opcode == 6)
				modelRotationX = buffer.readUShort();
			else if (opcode == 7) {
				modelOffset1 = buffer.readUShort();
				if (modelOffset1 > 32767)
					modelOffset1 -= 0x10000;
			} else if (opcode == 8) {
				modelOffset2 = buffer.readUShort();
				if (modelOffset2 > 32767)
					modelOffset2 -= 0x10000;
			} else if (opcode == 10)
				buffer.readUShort();
			else if (opcode == 11)
				stackable = true;
			else if (opcode == 12)
				value = buffer.readInt();
			else if (opcode == 16)
				membersObject = true;
			else if (opcode == 23) {
				maleWield = buffer.readUShort();
				aByte205 = buffer.readSignedByte();
			} else if (opcode == 24)
				anInt188 = buffer.readUShort();
			else if (opcode == 25) {
				femaleWield = buffer.readUShort();
				aByte154 = buffer.readSignedByte();
			} else if (opcode == 26)
				anInt164 = buffer.readUShort();
			else if (opcode >= 30 && opcode < 35) {
				if (groundActions == null)
					groundActions = new String[5];
				groundActions[opcode - 30] = buffer.readString();
				if (groundActions[opcode - 30].equalsIgnoreCase("hidden"))
					groundActions[opcode - 30] = null;
			} else if (opcode >= 35 && opcode < 40) {
				if (itemActions == null)
					itemActions = new String[5];
				itemActions[opcode - 35] = buffer.readString();
			} else if (opcode == 40) {
				int j = buffer.readUByte();
				originalModelColors = new int[j];
				modifiedModelColors = new int[j];
				for (int k = 0; k < j; k++) {
					originalModelColors[k] = buffer.readUShort();
					modifiedModelColors[k] = buffer.readUShort();
				}
			} else if (opcode == 41) {
				int j = buffer.readUByte();
				originalTexture = new short[j];
				modifiedTexture = new short[j];
				for (int k = 0; k < j; k++) {
					originalTexture[k] = (short) buffer.readUShort();
					modifiedTexture[k] = (short) buffer.readUShort();
				}

			} else if (opcode == 78)
				anInt185 = buffer.readUShort();
			else if (opcode == 79)
				anInt162 = buffer.readUShort();
			else if (opcode == 90)
				anInt175 = buffer.readUShort();
			else if (opcode == 91)
				anInt197 = buffer.readUShort();
			else if (opcode == 92)
				anInt166 = buffer.readUShort();
			else if (opcode == 93)
				anInt173 = buffer.readUShort();
			else if (opcode == 95)
				anInt204 = buffer.readUShort();
			else if (opcode == 97)
				certID = buffer.readUShort();
			else if (opcode == 98)
				certTemplateID = buffer.readUShort();
			else if (opcode >= 100 && opcode < 110) {
				if (stackIDs == null) {
					stackIDs = new int[10];
					stackAmounts = new int[10];
				}
				stackIDs[opcode - 100] = buffer.readUShort();
				stackAmounts[opcode - 100] = buffer.readUShort();
			} else if (opcode == 110)
				anInt167 = buffer.readUShort();
			else if (opcode == 111)
				anInt192 = buffer.readUShort();
			else if (opcode == 112)
				anInt191 = buffer.readUShort();
			else if (opcode == 113)
				brightness = buffer.readSignedByte();
			else if (opcode == 114)
				anInt184 = buffer.readSignedByte() * 5;
			else if (opcode == 115)
				team = buffer.readUByte();
			else if (opcode == 139)
				buffer.readUShort();
			else if (opcode == 140)
				buffer.readUShort();
			else if (opcode == 148)
				buffer.readUShort();
			else if (opcode == 149)
				buffer.readUShort();
		} while (true);
	}

	private ItemDefinition() {
		id = -1;
	}

	private byte aByte154;
	private byte aByte205;
	public int id;// anInt157
	public int team;
	public int value;// anInt155
	public int certID;
	public int modelID;// dropModel
	public int anInt175;
	public int femaleWield;// femWieldModel
	public int anInt204;// modelPositionUp
	public int anInt164;// femArmModel
	public int anInt197;
	public int anInt188;// maleArmModel
	public int maleWield;// maleWieldModel
	public int modelZoom;
	public int modelOffset1;
	public int modelOffset2;//
	public int certTemplateID;
	public int modelRotationX;// modelRotateRight
	public int modelRotationY;// modelRotateUp
	public static int totalItems;
	public int[] stackIDs;// modelStack
	public int[] stackAmounts;// itemAmount
	public int[] modifiedModelColors;// newModelColor
	public int[] originalModelColors;
	public short[] modifiedTexture;
	public short[] originalTexture;
	private int brightness;
	private int anInt162;
	private int anInt166;
	private int anInt184;
	private int anInt185;
	private int anInt191;
	private int anInt192;
	private int anInt167;
	private int anInt173;
	private int shiftDrop;
	private boolean stockMarket;
	private static int cacheIndex;
	private static int[] BufferIndices;
	public boolean stackable;// itemStackable
	public boolean membersObject;// aBoolean161
	public static boolean isMembers = true;
	public String name;// itemName
	public String description;// itemExamine
	public String itemActions[];// itemMenuOption
	public String groundActions[];
	private static Buffer Buffer;
	private static ItemDefinition[] cache;
	public static Cache mruNodes1 = new Cache(100);
	public static Cache mruNodes2 = new Cache(50);
}