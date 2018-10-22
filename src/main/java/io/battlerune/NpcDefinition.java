package io.battlerune;

public final class NpcDefinition {

	public static void unpackConfig(StreamLoader archive) {
		buffer = new Buffer(archive.getDataForName("npc.dat"));
		Buffer metaBuf = new Buffer(archive.getDataForName("npc.idx"));
		int totalNPCs = metaBuf.readUShort();

		System.out.println(String.format("Loaded: %d npcs", totalNPCs));

		offsets = new int[totalNPCs];
		int metaOffset = 2;
		for (int i = 0; i < totalNPCs; i++) {
			offsets[i] = metaOffset;
			metaOffset += metaBuf.readUShort();
		}

		cache = new NpcDefinition[20];

		for (int i = 0; i < 20; i++) {
			cache[i] = new NpcDefinition();
		}

		// ClassFieldPrinter printer = new ClassFieldPrinter();

//        try(PrintWriter writer = new PrintWriter(new File("npc_fields.txt"))) {
//            for (int i = 0; i < totalNPCs; i++) {
//                NpcDefinition def = lookup(i);
//                if (def == null || def.name == null) {
//                    continue;
//                }
//                try {
//                    printer.printFields(def, "", true);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                    continue;
//                }
//
//            }
//            writer.print(printer.getBuilder().toString());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

	}

	public static NpcDefinition lookup(int id) {
		for (int i = 0; i < 20; i++) {
			if (cache[i].interfaceType == (long) id) {
				return cache[i];
			}
		}

		anInt56 = (anInt56 + 1) % 20;
		NpcDefinition entityDef = cache[anInt56] = new NpcDefinition();
		buffer.currentOffset = offsets[id];
		entityDef.interfaceType = id;
		entityDef.decode(buffer);

		switch (id) {
		
		/**
		 * Illerai NPC's.
		 */
		/*  271:     */     case 1202: 
			/*  272: 290 */       entityDef.name = "Vine Growth";
			/*  273: 291 */       entityDef.combatLevel = 128;
			/*  274: 292 */       entityDef.description = "Grown by the Baron Maple's magic.".getBytes();
			/*  275: 293 */       entityDef.walkingAnimation = 3617;
			/*  276: 294 */       entityDef.standingAnimation = 3617;
			/*  277: 295 */       entityDef.actions = new String[5];
			/*  278: 296 */       entityDef.actions[1] = "Attack";
			/*  279: 297 */       entityDef.modelId = new int[] { 38578 };
			/*  280: 298 */       entityDef.scaleXZ = 200;
			/*  281: 299 */       entityDef.scaleY = 200;
			/*  282: 300 */       entityDef.size = 2;
			/*  283: 301 */       break;
			/*  284:     */     case 1203: 
			/*  285: 304 */       entityDef.name = "Seed";
			/*  286: 305 */       entityDef.description = "Was that there before...?".getBytes();
			/*  287: 306 */       entityDef.walkingAnimation = 65535;
			/*  288: 307 */       entityDef.standingAnimation = 65535;
			/*  289: 308 */       entityDef.actions = new String[5];
			/*  290: 309 */       entityDef.modelId = new int[] { 38582 };
			/*  291: 310 */       entityDef.scaleXZ = 200;
			/*  292: 311 */       entityDef.scaleY = 200;
			/*  293: 312 */       entityDef.size = 2;
			/*  294: 313 */       break;
	    case 253: 
	    	/*  476: 502 */       entityDef.name = "Spirit Black Dragon";
	    	/*  477: 503 */       entityDef.combatLevel = 255;
	    	/*  478: 504 */       entityDef.description = "It's a Spirit Black Dragon".getBytes();
	    	/*  479: 505 */       entityDef.walkingAnimation = 79;
	    	/*  480: 506 */       entityDef.standingAnimation = 90;
	    	/*  481: 507 */       entityDef.actions = new String[5];
	    	/*  482: 508 */       entityDef.actions[1] = "Attack";
	    	/*  483: 509 */       entityDef.recolorOriginal = new int[] { 2469, 912, 1938, 1814, 2588, 910 };
	    	/*  484: 510 */       entityDef.recolorTarget = new int[] { 24, 898, 42398, 4, 16 };
	    	/*  485: 511 */       entityDef.scaleXZ = 80;
	    	/*  486: 512 */       entityDef.scaleY = 80;
	    	/*  487: 513 */       entityDef.size = 2;
	    	/*  488: 514 */       break;
	    /*  180:     */     case 1198: 
	    	/*  181: 195 */       entityDef.name = "Baron Maple";
	    	/*  182: 196 */       entityDef.combatLevel = 612;
	    	/*  183: 197 */       entityDef.description = "A long forgotten soul encased in the branches of a maple tree.".getBytes();
	    	/*  184: 198 */       entityDef.walkingAnimation = 4654;
	    	/*  185: 199 */       entityDef.standingAnimation = 4656;
	    	/*  186: 200 */       entityDef.actions = new String[5];
	    	/*  187: 201 */       entityDef.actions[1] = "Attack";
	    	/*  188: 202 */       entityDef.modelId = new int[] { 38576, 38577, 38579 };
	    	/*  189: 203 */       entityDef.scaleXZ = 140;
	    	/*  190: 204 */       entityDef.scaleY = 140;
	    	/*  191: 205 */       entityDef.size = 4;
	    	/*  192: 206 */       break;
		 /*  245:     */     case 4708: 
			 /*  246: 262 */       entityDef.name = "King Chimera";
			 /*  247: 263 */       entityDef.combatLevel = 646;
			 /*  248: 264 */       entityDef.description = "A mythical beast of chaos.".getBytes();
			 /*  249: 265 */       entityDef.walkingAnimation = 7033;
			 /*  250: 266 */       entityDef.standingAnimation = 7032;
			 /*  251: 267 */       entityDef.actions = new String[5];
			 /*  252: 268 */       entityDef.actions[1] = "Attack";
			 /*  253: 269 */       entityDef.modelId = new int[] { 47007 };
			 /*  254: 270 */       entityDef.scaleXZ = 250;
			 /*  255: 271 */       entityDef.scaleY = 250;
			 /*  256: 272 */       entityDef.size = 5;
			 /*  257: 273 */       break;
			 /*  258:     */     case 3829: 
			 /*  259: 276 */       entityDef.name = "Chimera's cub";
			 /*  260: 277 */       entityDef.description = "It's glare is intimidating even for a cub.".getBytes();
			 /*  261: 278 */       entityDef.walkingAnimation = 7033;
			 /*  262: 279 */       entityDef.standingAnimation = 7032;
			 /*  263: 280 */       entityDef.actions = new String[5];
			 /*  264: 281 */       entityDef.actions[0] = "Talk-to";
			 /*  265: 282 */       entityDef.actions[2] = "Pick-up";
			 /*  266: 283 */       entityDef.modelId = new int[] { 47007 };
			 /*  267: 284 */       entityDef.scaleXZ = 50;
			 /*  268: 285 */       entityDef.scaleY = 50;
			 /*  269: 286 */       entityDef.size = 1;
			 /*  270: 287 */       break;
			 
			 /**
			  * end of Illerai NPC's.
			  */
			 case 1389:
				    entityDef.name = "Referral Manager";
					entityDef.actions = new String[5];
					entityDef.actions[0] = "Talk-to";
					entityDef.actions[2] = "Trade";
				 break;
		case 5129:
			entityDef.combatLevel = 806;
			break;
		case 6773:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;
		case 7481:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Trade";
			break;
		case 7601:
			entityDef.name = "Shady Insurance Agent";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Open";
			break;
		case 2462:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;
		case 1207:
			entityDef.name = "Rock Crab"; //BLUE ROCK CRAB
			entityDef.combatLevel = 13;
			entityDef.walkingAnimation = 1311;
			entityDef.standingAnimation = 1310;
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.modelId = new int[2];
			entityDef.modelId[0] = 4399;
			entityDef.modelId[1] = 47100; //MODELID [1] = ROCK CRAB SHELL.
			break;
			
		case 102:
			entityDef.name = "Rock Crab"; //YELLOW ROCK CRAB
			entityDef.combatLevel = 13;
			entityDef.walkingAnimation = 1311;
			entityDef.standingAnimation = 1310;
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.modelId = new int[2];
			entityDef.modelId[0] = 4399;
			entityDef.modelId[1] = 47104; //MODELID [1] = ROCK CRAB SHELL.
			break;
			
		case 761:
			entityDef.name = "Rock Crab"; //PURPLE ROCK CRAB
			entityDef.combatLevel = 13;
			entityDef.walkingAnimation = 1311;
			entityDef.standingAnimation = 1310;
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.modelId = new int[2];
			entityDef.modelId[0] = 4399;
			entityDef.modelId[1] = 4400;
			entityDef.recolorOriginal = new int[1];
			entityDef.recolorOriginal[0] = 10396;
			entityDef.recolorTarget = new int[1];
			entityDef.recolorTarget[0] = 48914;
			break;
			
		case 769:
			entityDef.name = "Rock Crab"; //PINK ROCK CRAB
			entityDef.combatLevel = 13;
			entityDef.walkingAnimation = 1311;
			entityDef.standingAnimation = 1310;
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.modelId = new int[2];
			entityDef.modelId[0] = 4399;
			entityDef.modelId[1] = 47112; //MODELID [1] = ROCK CRAB SHELL.
			break;
		case 7785:
            entityDef.actions = new String[5];
            entityDef.actions[0] = "Talk-to";
            entityDef.actions[2] = "Pick-up";
			break;
		case 6477:
			entityDef.combatLevel = 693;
			break;
			
		case 770:
			entityDef.name = "Abyssal demon"; //blue demon
			entityDef.combatLevel = 124; 
			entityDef.walkingAnimation = 1534;
			entityDef.standingAnimation = 1536;
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.modelId = new int[1];
			entityDef.modelId[0] = 47101;
		/*	entityDef.anInt91 = 64;
			entityDef.anInt86 = 64;
			entityDef.aByte68 = 1;*/
		break;
		case 7836:
			entityDef.name = "Mini Corporeal Beast";
			entityDef.combatLevel = 785;
			entityDef.walkingAnimation = 1684;
			entityDef.standingAnimation = 1678;
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			entityDef.modelId = new int[1];
			entityDef.modelId[0] = 11056;
			break;
		case 3216:
			entityDef.name = "Melee store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;
		case 3217:
			entityDef.name = "Ranged store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;
		case 3218:
			entityDef.name = "Magic store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;

		case 7062:
			entityDef.name = "Ensouled Hunter";
			break;
		/* Fishing */
		case 1518:
			entityDef.name = "Shimps & Anchovies";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Net";
			break;
		case 1526:
			entityDef.name = "Trout & Salmon";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Bait";
			break;
		case 311:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Trade";
			entityDef.actions[3] = "Claim-armour";
			break;
		case 1519:
			entityDef.name = "Lobster & Swordfish";
			break;
		case 1520:
			entityDef.name = "Shark";
			break;
		case 1521:
			entityDef.name = "Manta ray";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Net";
			break;
		case 1534:
			entityDef.name = "Monkfish";
			break;
		case 1536:
			entityDef.name = "Dark crab";
			break;
		case 1600:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Bank";
			break;
		case 1603:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Trade";

			break;
		case 326:
			
		case 3220:
			entityDef.actions[2] = "Crafting Tele";
			entityDef.actions[3] = "Crafting Tele 1";
			break;
		case 321:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Dismiss";
			break;
		case 5419:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			entityDef.actions[3] = "Trade";
			break;
		/** Crafting master. */
		case 473:
		case 137:
		case 8091:
		case 3354:
		 case 787:
	         	entityDef.scaleXZ = 64;
	         	entityDef.scaleY = 64;
	             entityDef.actions = new String[5];
	               entityDef.actions[0] = "Talk-to";
	               entityDef.actions[2] = "Pick-up";
	         break;
		case 5811:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Tan";
			entityDef.actions[2] = "Trade";
			break;
		 case 2641:
         	entityDef.scaleXZ = 64;
         	entityDef.scaleY = 64;
         	entityDef.description = "Pet Dragonkin, increases @ore@special attack@ore@ restore!".getBytes();
             entityDef.actions = new String[5];
               entityDef.actions[0] = "Talk-to";
               entityDef.actions[2] = "Pick-up";
         break;
         case 3456:
         	entityDef.scaleXZ = 64;
         	entityDef.scaleY = 64;
         	entityDef.description = "Fallen God, allows you to teleport out of any @ore@ wilderness level!".getBytes();
             entityDef.actions = new String[5];
               entityDef.actions[0] = "Talk-to";
               entityDef.actions[2] = "Pick-up";
         	
         	break;
         case 5504:
         	entityDef.scaleXZ = 64;
         	entityDef.scaleY = 64;
         	entityDef.description = "Pet Morduat, does pretty much nothing.".getBytes();
             entityDef.actions = new String[5];
               entityDef.actions[0] = "Talk-to";
               entityDef.actions[2] = "Pick-up";
         break;
         case 1636:
         case 1635:
        	 entityDef.actions = new String[5];
        	 entityDef.actions[0] = "Catch";
        	 break;
         case 1637:
        	 entityDef.actions = new String[5];
        	 entityDef.actions[1] = "Catch";
        	 break;
        	 
         	
         case 1873:
         	entityDef.name = "Pet Yoshi";
         	entityDef.description = "Pet Yoshi, has magical Drop rate powers!".getBytes();
             entityDef.actions = new String[5];
               entityDef.actions[0] = "Talk-to";
               entityDef.actions[2] = "Pick-up";
         
         	//entityDef.actions[0] = "Talk-to";
 		//	entityDef.actions[1] = "Pick-up";
         	break;
         case 457:
          	entityDef.name = "Pet Crawling Hand";
              entityDef.actions = new String[5];
                entityDef.actions[0] = "Talk-to";
                entityDef.actions[2] = "Pick-up";
             	entityDef.scaleXZ = 64;
             	entityDef.scaleY = 64;
          	break;
		case 4052:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Pick-up";
         	entityDef.scaleXZ = 64;
         	entityDef.scaleY = 64;
         	break;
		/* Clanmaster */
		case 1143:
			entityDef.name = "Clanmaster";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;
		/* Clothing */
		case 534:
			entityDef.name = "Clothing store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;

		/* Pure */
		case 5440:
			entityDef.name = "Pure store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;

		/* Mage */
		case 4400:
			entityDef.name = "Mage store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;

		/* Range */
		case 1576:
			entityDef.name = "Range store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;
		case 8099:
		case 1157:
		case 1158:
		case 1160:
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Attack";
			break;

		/* Skill */
		case 505:
			entityDef.name = "Skilling store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;

		/* Hunter */
		case 1504:
			entityDef.name = "Hunter store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;

		/* Cook */
		case 1199:
			entityDef.name = "Consumable store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;

		/* Farming */
		case 3258:
			entityDef.name = "Farming store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Open";
			break;

		/* Achievement */
		case 5527:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Trade";
			break;

		/* Banker */
		case 1480:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Bank";
			break;

		/* Voting */
		case 3531:
			entityDef.name = "Vote";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Trade";
			entityDef.actions[3] = "Claim";
			break;

		/* Spellbook */
		case 4397:
			entityDef.name = "Spellbook";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Change";
			break;

		/* Royal Points */
		case 5523:
			entityDef.name = "Donator Store";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Open-store";
			entityDef.actions[3] = "Claim-purchase";
			entityDef.description = "What more is there to say about The Royal King?.".getBytes();
			break;

		/* Clothing */
		case 1307:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Makeover";
			break;

		/* Thieving Stalls */
		case 3439:
			entityDef.name = "Merchant";
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Sell goods";
			break;

		case 5919:
		case 1755:
		case 2186:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Trade";
			break;

		case 2180:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Exchange for firecape";
			break;

		/* Nieve */
		case 490:
		case 6797:
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Open-interface";
			entityDef.actions[3] = "Upgrade-items";
			break;
		case 1756:
			entityDef.name = "Void Knight";
			entityDef.combatLevel = 0;
			entityDef.actions = new String[5];
			entityDef.actions[1] = "Talk-to";
			break;
		case 315:
			entityDef.actions = new String[5]; // Actions for the npc
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Trade";
			entityDef.modelId = new int[11]; // Number of models it uses
			entityDef.modelId[0] = 181;
			entityDef.modelId[1] = 28285;
			entityDef.modelId[2] = 249;
			entityDef.modelId[3] = 28226;
			entityDef.modelId[4] = 176;
			entityDef.modelId[5] = 5232; // sotd
			entityDef.modelId[6] = 28445; // Occult
			entityDef.modelId[7] = 31772; // Team cape 0
			entityDef.modelId[8] = 28223;
			entityDef.modelId[9] = 28286;
			entityDef.modelId[10] = 556;
			entityDef.recolorOriginal = new int[] { 103, 0, 43439, 94, 43340, 43115, 43228, 43449, 929, 914, 17467,
					16578, 16582, 5018, 11177, 10351 }; // Original colour
			entityDef.recolorTarget = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 5, 900, 900, 900, 20, 900, 5 }; // Colour
																														// you
																														// want
																														// to
																														// change
																														// to
			entityDef.standingAnimation = 813; // Npc's Stand Emote
			entityDef.walkingAnimation = 1146;
			entityDef.name = "Bounty Hunter";
			entityDef.description = "The hunter of bounties. ".getBytes();
			break;
		case 345:
			entityDef.name = "Polly";
			entityDef.description = "She takes pride in prestiging.".getBytes();
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.actions[2] = "Trade";
			entityDef.actions[3] = "Prestige-panel";
			entityDef.actions[4] = "Perk-information";
			entityDef.walkingAnimation = 819;
			entityDef.standingAnimation = 808;
			entityDef.modelId = new int[10];
			entityDef.modelId[0] = 391;
			entityDef.modelId[1] = 414;
			entityDef.modelId[2] = 18983;
			entityDef.modelId[3] = 361;
			entityDef.modelId[4] = 356;
			entityDef.modelId[5] = 556;
			entityDef.modelId[6] = 332;
			entityDef.modelId[7] = 474;
			entityDef.modelId[8] = 433;
			entityDef.modelId[9] = 16348;
			entityDef.recolorTarget = new int[] { 127, 127, 127, 127, 9168, -22419, 9143, 9168, 9143, 7744, 127, 127 }; // Colour
																														// you
																														// want
																														// to
																														// change
																														// to//
			entityDef.recolorOriginal = new int[] { 4626, 10128, 10004, 5018, 61, 10351, 57280, 54183, 54503, 6798,
					8741, 25238 }; // Original colour
			break;
		case 6481:
			entityDef.name = "Mac";
			entityDef.description = "Only the most knowledgeable players of Runity are worthy of such a cape."
					.getBytes();
			entityDef.combatLevel = 126;
			entityDef.walkingAnimation = 819;
			// entityDef.standingAnimation = 808;
			entityDef.actions = new String[5];
			entityDef.actions[0] = "Talk-to";
			entityDef.modelId = new int[8];
			entityDef.modelId[0] = 29615;
			entityDef.modelId[1] = 29618;
			entityDef.modelId[2] = 29621;
			entityDef.modelId[3] = 29616;
			entityDef.modelId[4] = 29620;
			entityDef.modelId[5] = 176;
			entityDef.modelId[6] = 29619;
			entityDef.modelId[7] = 29622;
			break;
		case 367:
			entityDef.actions = new String[5]; // Actions for the npc
			entityDef.modelId = new int[9]; // Number of models it uses
			entityDef.modelId[0] = 27636; // Bandos chest
			entityDef.modelId[1] = 27625; // Tassets
			entityDef.modelId[2] = 13307; // Barrows gloves
			entityDef.modelId[3] = 28826; // BCP (arms)
			entityDef.modelId[4] = 29250; // Primordial boots
			entityDef.modelId[5] = 32678; // Elder maul
			entityDef.modelId[6] = 14424; // Serp helm
			entityDef.modelId[7] = 31227; // Torture
			entityDef.modelId[8] = 29616; // Max cape
			entityDef.standingAnimation = 7518; // Npc's Stand Emote
			entityDef.walkingAnimation = 7520;
			entityDef.recolorOriginal = new int[] { 668, 675, 673, 815, 784 };
			entityDef.recolorTarget = new int[] { 947, 960, 7104, 8146, 0 };
			entityDef.name = "Donator Guard";
			entityDef.combatLevel = 420;
			entityDef.description = "The protector of the donator zone.".getBytes(); // NPC description
			break;
		}
		return entityDef;
	}

	public Model method160() {
		if (morphisms != null) {
			NpcDefinition entityDef = morph();
			if (entityDef == null)
				return null;
			else
				return entityDef.method160();
		}
		if (additionalModels == null)
			return null;
		boolean flag1 = false;
		for (int i = 0; i < additionalModels.length; i++)
			if (!Model.isCached(additionalModels[i]))
				flag1 = true;

		if (flag1)
			return null;
		Model aclass30_sub2_sub4_sub6s[] = new Model[additionalModels.length];
		for (int j = 0; j < additionalModels.length; j++)
			aclass30_sub2_sub4_sub6s[j] = Model.getModel(additionalModels[j]);

		Model model;
		if (aclass30_sub2_sub4_sub6s.length == 1)
			model = aclass30_sub2_sub4_sub6s[0];
		else
			model = new Model(aclass30_sub2_sub4_sub6s.length, aclass30_sub2_sub4_sub6s);
		if (recolorOriginal != null) {
			for (int k = 0; k < recolorOriginal.length; k++)
				model.recolor(recolorOriginal[k], recolorTarget[k]);

		}
		return model;
	}

	public Model getAnimatedModel(int primaryFrame, int secondaryFrame, int interleaveOrder[]) {
		if (morphisms != null) {
			NpcDefinition definition = morph();
			if (definition == null)
				return null;
			else
				return definition.getAnimatedModel(primaryFrame, secondaryFrame, interleaveOrder);
		}
		Model model = (Model) modelCache.get(interfaceType);
		if (model == null) {
			boolean flag = false;
			for (int index = 0; index < modelId.length; index++)
				if (!Model.isCached(modelId[index]))
					flag = true;
			if (flag) {
				return null;
			}
			Model models[] = new Model[modelId.length];
			for (int index = 0; index < modelId.length; index++)
				models[index] = Model.getModel(modelId[index]);

			if (models.length == 1)
				model = models[0];
			else
				model = new Model(models.length, models);
			if (recolorOriginal != null) {
				for (int index = 0; index < recolorOriginal.length; index++)
					model.recolor(recolorOriginal[index], recolorTarget[index]);

			}
			model.skin();
			model.light(64 + lightModifier, 850 + shadowModifier, -30, -50, -30, true);
			modelCache.put(model, interfaceType);
		}
		Model model_1 = Model.EMPTY_MODEL;
		model_1.method464(model, Frame.method532(secondaryFrame) & Frame.method532(primaryFrame));
		if (secondaryFrame != -1 && primaryFrame != -1)
			model_1.method471(interleaveOrder, primaryFrame, secondaryFrame);
		else if (secondaryFrame != -1)
			model_1.method470(secondaryFrame);
		if (scaleXZ != 128 || scaleY != 128)
			model_1.method478(scaleXZ, scaleXZ, scaleY);
		model_1.method466();
		model_1.anIntArrayArray1658 = null;
		model_1.anIntArrayArray1657 = null;
		if (size == 1)
			model_1.aBoolean1659 = true;
		return model_1;
	}

	public NpcDefinition morph() {
		int j = -1;
		if (varbit != -1) {
			Varbit varBit = Varbit.cache[varbit];
			int k = varBit.configId;
			int l = varBit.lsb;
			int i1 = varBit.msb;
			int j1 = Client.BIT_MASKS[i1 - l];
			j = clientInstance.settings[k] >> l & j1;
		} else if (varp != -1)
			j = clientInstance.settings[varp];
		if (j < 0 || j >= morphisms.length || morphisms[j] == -1)
			return null;
		else
			return lookup(morphisms[j]);
	}

	public static void clear() {
		modelCache = null;
		offsets = null;
		cache = null;
		buffer = null;
	}

	Model method164(int j, int currAnim, int nextAnim, int currCycle, int nextCycle, int ai[]) {
		if (morphisms != null) {
			final NpcDefinition type = morph();
			if (type == null) {
				return null;
			} else {
				return type.method164(j, currAnim, nextAnim, currCycle, nextCycle, ai);
			}
		}
		Model model = (Model) modelCache.get(interfaceType);
		if (model == null) {
			boolean flag = false;
			for (int i1 = 0; i1 < modelId.length; i1++) {
				if (!Model.isCached(modelId[i1])) {
					flag = true;
				}
			}
			if (flag) {
				return null;
			}
			final Model[] parts = new Model[modelId.length];
			for (int j1 = 0; j1 < modelId.length; j1++) {
				parts[j1] = Model.getModel(modelId[j1]);
			}
			if (parts.length == 1) {
				model = parts[0];
			} else {
				model = new Model(parts.length, parts);
			}
			if (recolorOriginal != null) {
				for (int k1 = 0; k1 < recolorOriginal.length; k1++) {
					model.recolor(recolorOriginal[k1], recolorTarget[k1]);
				}
			}
			model.skin();
			if (Settings.CUSTOM_LIGHTING) {
				model.light(84, 1000, -90, -580, -90, true);
			} else {
				model.light(64 + lightModifier, 850 + shadowModifier, -30, -50, -30, true);
			}
			modelCache.put(model, interfaceType);
		}
		final Model model_1 = Model.EMPTY_MODEL;
		model_1.method464(model, Frame.method532(currAnim) & Frame.method532(j));
		if (currAnim != -1 && j != -1) {
			model_1.method471(ai, j, currAnim);
		} else if (currAnim != -1) {
			if (Settings.TWEENING) {
				model_1.interpolateFrames(currAnim, nextAnim, nextCycle, currCycle);
			} else {
				model_1.method470(currAnim);
			}
		}
		if (scaleXZ != 128 || scaleY != 128) {
			model_1.method478(scaleXZ, scaleY, scaleXZ);
		}
		model_1.method466();
		model_1.anIntArrayArray1658 = null;
		model_1.anIntArrayArray1657 = null;
		if (size == 1) {
			model_1.aBoolean1659 = true;
		}
		return model_1;
	}

	public void decode(Buffer buffer) {
		while (true) {
			int opcode = buffer.readUByte();
			if (opcode == 0) {
				return;
			} else if (opcode == 1) {
				int len = buffer.readUByte();
				modelId = new int[len];
				for (int i = 0; i < len; i++) {
					modelId[i] = buffer.readUShort();
				}
			} else if (opcode == 2) {
				name = buffer.readString();
			} else if (opcode == 12) {
				size = buffer.readUByte();
			} else if (opcode == 13) {
				standingAnimation = buffer.readUShort();
			} else if (opcode == 14) {
				walkingAnimation = buffer.readUShort();
			} else if (opcode == 15) {
				buffer.readUShort();
			} else if (opcode == 16) {
				buffer.readUShort();
			} else if (opcode == 17) {
				walkingAnimation = buffer.readUShort();
				halfTurnAnimation = buffer.readUShort();
				quarterClockwiseTurnAnimation = buffer.readUShort();
				quarterAnticlockwiseTurnAnimation = buffer.readUShort();
				if (halfTurnAnimation == 65535) {
					halfTurnAnimation = walkingAnimation;
				}
				if (quarterClockwiseTurnAnimation == 65535) {
					quarterClockwiseTurnAnimation = walkingAnimation;
				}
				if (quarterAnticlockwiseTurnAnimation == 65535) {
					quarterAnticlockwiseTurnAnimation = walkingAnimation;
				}
			} else if (opcode >= 30 && opcode < 35) {
				if (actions == null) {
					actions = new String[5];
				}

				actions[opcode - 30] = buffer.readString();

				if (actions[opcode - 30].equalsIgnoreCase("Hidden")) {
					actions[opcode - 30] = null;
				}
			} else if (opcode == 40) {
				int len = buffer.readUByte();
				recolorOriginal = new int[len];
				recolorTarget = new int[len];
				for (int i = 0; i < len; i++) {
					recolorOriginal[i] = buffer.readUShort();
					recolorTarget[i] = buffer.readUShort();
				}

			} else if (opcode == 41) {
				int len = buffer.readUByte();

				for (int i = 0; i < len; i++) {
					buffer.readUShort(); // textures
					buffer.readUShort();
				}
			} else if (opcode == 60) {
				int len = buffer.readUByte();
				additionalModels = new int[len];
				for (int i = 0; i < len; i++) {
					additionalModels[i] = buffer.readUShort();
				}
			} else if (opcode == 93) {
				aBoolean87 = false;
			} else if (opcode == 95)
				combatLevel = buffer.readUShort();
			else if (opcode == 97)
				scaleXZ = buffer.readUShort();
			else if (opcode == 98)
				scaleY = buffer.readUShort();
			else if (opcode == 99)
				aBoolean93 = true;
			else if (opcode == 100)
				lightModifier = buffer.readSignedByte();
			else if (opcode == 101)
				shadowModifier = buffer.readSignedByte();
			else if (opcode == 102)
				headIcon = buffer.readUShort();
			else if (opcode == 103)
				rotation = buffer.readUShort();
			else if (opcode == 106 || opcode == 118) {
				varbit = buffer.readUShort();

				if (varbit == 65535) {
					varbit = -1;
				}

				varp = buffer.readUShort();

				if (varp == 65535) {
					varp = -1;
				}

				int value = -1;

				if (opcode == 118) {
					value = buffer.readUShort();
				}

				int len = buffer.readUByte();
				morphisms = new int[len + 2];
				for (int i = 0; i <= len; i++) {
					morphisms[i] = buffer.readUShort();
					if (morphisms[i] == 65535) {
						morphisms[i] = -1;
					}
				}
				morphisms[len + 1] = value;
			} else if (opcode == 109) {
				aBoolean84 = false;
			} else if (opcode == 107 || opcode == 111) {

			} else {
				System.out.println(String.format("npc def invalid opcode: %d", opcode));
			}
		}
	}

	public NpcDefinition() {
		quarterAnticlockwiseTurnAnimation = -1;
		varbit = -1;
		halfTurnAnimation = -1;
		varp = -1;
		combatLevel = -1;
		anInt64 = 1834;
		walkingAnimation = -1;
		size = 1;
		headIcon = -1;
		standingAnimation = -1;
		interfaceType = -1L;
		rotation = 32;
		quarterClockwiseTurnAnimation = -1;
		aBoolean84 = true;
		scaleY = 128;
		aBoolean87 = true;
		scaleXZ = 128;
		aBoolean93 = false;
	}

	public int quarterAnticlockwiseTurnAnimation;
	public static int anInt56;
	public int varbit;
	public int halfTurnAnimation;
	public int varp;
	public static Buffer buffer;
	public int combatLevel;
	public final int anInt64;
	public String name;
	public String actions[];
	public int walkingAnimation;
	public int size;
	public int[] recolorTarget;
	public static int[] offsets;
	public int[] additionalModels;
	public int headIcon;
	public int[] recolorOriginal;
	public int standingAnimation;
	public long interfaceType;
	public int rotation;
	public static NpcDefinition[] cache;
	public static Client clientInstance;
	public int quarterClockwiseTurnAnimation;
	public boolean aBoolean84;
	public int lightModifier;
	public int scaleY;
	public boolean aBoolean87;
	public int morphisms[];
	public byte description[];
	public int scaleXZ;
	public int shadowModifier;
	public boolean aBoolean93;
	public int[] modelId;
	public int interfaceZoom = 0;
	public static Cache modelCache = new Cache(30);

}
