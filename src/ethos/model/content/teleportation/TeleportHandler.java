package ethos.model.content.teleportation;

import ethos.Config;
import ethos.event.impl.WheatPortalEvent;
import ethos.model.players.Player;

/**
 * Teleport Handler Class - Used for our new teleporting system Probably should
 * have used enums, oh well..
 * 
 * @author Tyler
 */
public class TeleportHandler {

	private Player c;

	public TeleportHandler(Player player) {
		this.c = player;
	}

	public String[] monsterNames = { "Rock Crabs", "Sand Crabs", "Yaks", "Mithril Dragons", "Kalphite", "Demonic Gorillas", "Wyverns", "Mini Hydra", "Cows", "Desert Bandits", "Elf Warriors", "Dagganoths", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A",};
	
	public String[] monsterPrices = { "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE"};
	
	public String[] monsterDangers = { "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe",
			"@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous",};

	
	public String[] dungeonNames = { "Slayer Tower", "Edgeville Dungeon", "Taverley Dungeon", "Brimhaven Dungeon", "Rellekka Dungeon", "Iletya Elves", "Catacombs", "Karuulm Dungeon", "Lithkren Vault", "Ice Dungeon", "Smoke Dungeon", "Kraken Cave", "Nieve's Cave", "Crystal Cave", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A",};
	public String[] dungeonPrices = { "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE"};
	public String[] dungeonDangers = { "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe",
					"@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe",};

	
	public String[] bossNames = { "Hydra", "Zulrah", "Godwars", "Vorkath", "Lizardman Shaman", "King Black Dragon", "Abyssal Sire", "Corporeal Beast", "Chaos Elemental", "Callisto", "Venenatis", "Vet'ion", "Crazy Archaeologist", "Giant Mole", "Barrelchest", "Dagannoth Kings", "Cerberus", "Thermonuclear smoke devil", "N/A", "N/A",};
	public String[] bossPrices = { "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE"};
	public String[] bossDangers = { "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@red@Dangerous", "@red@Dangerous", "@red@Dangerous", "@red@Dangerous",
			"@red@Dangerous", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe",};

	
	public String[] minigameNames = { "Gambling", "Pest control", "Chambers Of Xeric", "Gulag", "Fight Caves", "Inferno", "Warriors Guild", "Barrows", "Mage Arena", "Mage Arena 2", "Duel Arena", "Lighthouse", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A",};
	public String[] minigamePrices = { "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE"};
	public String[] minigameDangers = { "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe",
			"@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe",};
	public String[] skillingNames = { "Skilling Centre", "Woodcutting Guild", "Fishing Guild", "Mining Guild", "Barb Fishing", "Crafting guild", "Gnome Agility", "Barb Agility", "Wildy Agility", "Lumbridge", "Varrock", "Falador", "Camelot", "Ardougne", "Hunter", "Puro Puro", "Farming", "N/A", "N/A", "N/A",};
	public String[] skillingPrices = { "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE"};
	public String[] skillingDangers = { "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@red@Dangerous", "@gre@Safe", "@gre@Safe", "@gre@Safe",
			"@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe", "@gre@Safe",};
	
	public String[] playerKillingNames = { "Information Center", "Grand Exchange", "West Dragons", "Chaos Temple", "Chinchompa Hills", "Revenants Cave", "Graves", "Greator Demons", "Level 44 obelisk", "Level 50 obelisk", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A",};
	public String[] playerKillingPrices = { "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE", "FREE"};
	public String[] playerKillingDangers = { "@gre@Safe", "@gre@Safe", "@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous",
			"@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous", "@gre@Safe", "@red@Dangerous",};
	
	public void openInterface() {
		c.getPA().showInterface(31000);
		c.getTeleport().selection(c, 0);
	}
	public void loadMonsterTab() {
		c.getPA().sendFrame126("Monsters", 31003);
		c.getPA().sendFrame126(""+c.recent1_Name+"", 31017);
		c.getPA().sendFrame126(""+c.recent2_Name+"", 31018);
		c.getPA().sendFrame126(""+c.recent3_Name+"", 31019);
		
		c.getPA().sendFrame126(""+c.getFavouriteOne()+"", 31009);
		c.getPA().sendFrame126(""+c.Favourites_2+"", 31010);
		c.getPA().sendFrame126(""+c.Favourites_3+"", 31011);
		c.getPA().sendFrame126(""+c.Favourites_4+"", 31012);
		c.getPA().sendFrame126(""+c.Favourites_5+"", 31013);
		c.getPA().sendFrame126(""+c.Favourites_6+"", 31014);
		c.getPA().sendFrame126(""+c.Favourites_7+"", 31015);
		c.getPA().sendFrame126(""+c.Favourites_8+"", 31016);
		int[] cost = { 31053, 31058, 31063, 31068, 31073, 31078, 31083, 31088, 31093, 31098, 31103, 31108, 31113, 31118, 31123, 31128, 31133, 31138, 31143, 31148};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(monsterPrices[j], cost[j]);

		}
		int[] zones = { 31054, 31059, 31064, 31069, 31074, 31079, 31084, 31089, 31094, 31099, 31104, 31109, 31114, 31119, 31124, 31129, 31134, 31139, 31144, 31149};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(monsterDangers[j], zones[j]);

		}
		int[] ids = { 31052, 31057, 31062, 31067, 31072, 31077, 31082, 31087, 31092, 31097, 31102, 31107, 31112, 31117, 31122, 31127, 31132, 31137, 31142, 31147};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(monsterNames[j], ids[j]);

		}
	}

	public void loadDungeonsTab() {
		c.getPA().sendFrame126("Dungeons", 31003);	
		c.getPA().sendFrame126(""+c.recent1_Name+"", 31017);
		c.getPA().sendFrame126(""+c.recent2_Name+"", 31018);
		c.getPA().sendFrame126(""+c.recent3_Name+"", 31019);
		
		c.getPA().sendFrame126(""+c.Favourites_1+"", 31009);
		c.getPA().sendFrame126(""+c.Favourites_2+"", 31010);
		c.getPA().sendFrame126(""+c.Favourites_3+"", 31011);
		c.getPA().sendFrame126(""+c.Favourites_4+"", 31012);
		c.getPA().sendFrame126(""+c.Favourites_5+"", 31013);
		c.getPA().sendFrame126(""+c.Favourites_6+"", 31014);
		c.getPA().sendFrame126(""+c.Favourites_7+"", 31015);
		c.getPA().sendFrame126(""+c.Favourites_8+"", 31016);
		
		int[] cost = { 31053, 31058, 31063, 31068, 31073, 31078, 31083, 31088, 31093, 31098, 31103, 31108, 31113, 31118, 31123, 31128, 31133, 31138, 31143, 31148};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(dungeonPrices[j], cost[j]);

		}
		int[] zones = { 31054, 31059, 31064, 31069, 31074, 31079, 31084, 31089, 31094, 31099, 31104, 31109, 31114, 31119, 31124, 31129, 31134, 31139, 31144, 31149};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(dungeonDangers[j], zones[j]);

		}
		int[] ids = { 31052, 31057, 31062, 31067, 31072, 31077, 31082, 31087, 31092, 31097, 31102, 31107, 31112, 31117, 31122, 31127, 31132, 31137, 31142, 31147};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(dungeonNames[j], ids[j]);

		}
	}

	public void loadBossesTab() {
		c.getPA().sendFrame126("Bosses", 31003);	
		c.getPA().sendFrame126(""+c.recent1_Name+"", 31017);
		c.getPA().sendFrame126(""+c.recent2_Name+"", 31018);
		c.getPA().sendFrame126(""+c.recent3_Name+"", 31019);
		
		c.getPA().sendFrame126(""+c.Favourites_1+"", 31009);
		c.getPA().sendFrame126(""+c.Favourites_2+"", 31010);
		c.getPA().sendFrame126(""+c.Favourites_3+"", 31011);
		c.getPA().sendFrame126(""+c.Favourites_4+"", 31012);
		c.getPA().sendFrame126(""+c.Favourites_5+"", 31013);
		c.getPA().sendFrame126(""+c.Favourites_6+"", 31014);
		c.getPA().sendFrame126(""+c.Favourites_7+"", 31015);
		c.getPA().sendFrame126(""+c.Favourites_8+"", 31016);

		int[] cost = { 31053, 31058, 31063, 31068, 31073, 31078, 31083, 31088, 31093, 31098, 31103, 31108, 31113, 31118, 31123, 31128, 31133, 31138, 31143, 31148};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(bossPrices[j], cost[j]);

		}
		int[] zones = { 31054, 31059, 31064, 31069, 31074, 31079, 31084, 31089, 31094, 31099, 31104, 31109, 31114, 31119, 31124, 31129, 31134, 31139, 31144, 31149};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(bossDangers[j], zones[j]);

		}
		int[] ids = { 31052, 31057, 31062, 31067, 31072, 31077, 31082, 31087, 31092, 31097, 31102, 31107, 31112, 31117, 31122, 31127, 31132, 31137, 31142, 31147};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(bossNames[j], ids[j]);

		}
	} 
	
	public void loadMinigamesTab() {
		c.getPA().sendFrame126("Minigames", 31003);	
		c.getPA().sendFrame126(""+c.recent1_Name+"", 31017);
		c.getPA().sendFrame126(""+c.recent2_Name+"", 31018);
		c.getPA().sendFrame126(""+c.recent3_Name+"", 31019);
		
		c.getPA().sendFrame126(""+c.Favourites_1+"", 31009);
		c.getPA().sendFrame126(""+c.Favourites_2+"", 31010);
		c.getPA().sendFrame126(""+c.Favourites_3+"", 31011);
		c.getPA().sendFrame126(""+c.Favourites_4+"", 31012);
		c.getPA().sendFrame126(""+c.Favourites_5+"", 31013);
		c.getPA().sendFrame126(""+c.Favourites_6+"", 31014);
		c.getPA().sendFrame126(""+c.Favourites_7+"", 31015);
		c.getPA().sendFrame126(""+c.Favourites_8+"", 31016);
		

		int[] cost = { 31053, 31058, 31063, 31068, 31073, 31078, 31083, 31088, 31093, 31098, 31103, 31108, 31113, 31118, 31123, 31128, 31133, 31138, 31143, 31148};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(minigamePrices[j], cost[j]);

		}
		int[] zones = { 31054, 31059, 31064, 31069, 31074, 31079, 31084, 31089, 31094, 31099, 31104, 31109, 31114, 31119, 31124, 31129, 31134, 31139, 31144, 31149};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(minigameDangers[j], zones[j]);

		}
		int[] ids = { 31052, 31057, 31062, 31067, 31072, 31077, 31082, 31087, 31092, 31097, 31102, 31107, 31112, 31117, 31122, 31127, 31132, 31137, 31142, 31147};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(minigameNames[j], ids[j]);

		}
	}
	public void loadSkillingTab() {
		c.getPA().sendFrame126("Skilling", 31003);	
		c.getPA().sendFrame126(""+c.recent1_Name+"", 31017);
		c.getPA().sendFrame126(""+c.recent2_Name+"", 31018);
		c.getPA().sendFrame126(""+c.recent3_Name+"", 31019);
		c.getPA().sendFrame126(""+c.Favourites_1+"", 31009);
		c.getPA().sendFrame126(""+c.Favourites_2+"", 31010);
		c.getPA().sendFrame126(""+c.Favourites_3+"", 31011);
		c.getPA().sendFrame126(""+c.Favourites_4+"", 31012);
		c.getPA().sendFrame126(""+c.Favourites_5+"", 31013);
		c.getPA().sendFrame126(""+c.Favourites_6+"", 31014);
		c.getPA().sendFrame126(""+c.Favourites_7+"", 31015);
		c.getPA().sendFrame126(""+c.Favourites_8+"", 31016);

		int[] cost = { 31053, 31058, 31063, 31068, 31073, 31078, 31083, 31088, 31093, 31098, 31103, 31108, 31113, 31118, 31123, 31128, 31133, 31138, 31143, 31148};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(skillingPrices[j], cost[j]);

		}
		int[] zones = { 31054, 31059, 31064, 31069, 31074, 31079, 31084, 31089, 31094, 31099, 31104, 31109, 31114, 31119, 31124, 31129, 31134, 31139, 31144, 31149};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(skillingDangers[j], zones[j]);

		}
		int[] ids = { 31052, 31057, 31062, 31067, 31072, 31077, 31082, 31087, 31092, 31097, 31102, 31107, 31112, 31117, 31122, 31127, 31132, 31137, 31142, 31147};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(skillingNames[j], ids[j]);

		}
	}
	
	public void loadPlayerKillingTab() {
		c.getPA().sendFrame126("Locations", 31003);	
		c.getPA().sendFrame126(""+c.recent1_Name+"", 31017);
		c.getPA().sendFrame126(""+c.recent2_Name+"", 31018);
		c.getPA().sendFrame126(""+c.recent3_Name+"", 31019);
		
		c.getPA().sendFrame126(""+c.Favourites_1+"", 31009);
		c.getPA().sendFrame126(""+c.Favourites_2+"", 31010);
		c.getPA().sendFrame126(""+c.Favourites_3+"", 31011);
		c.getPA().sendFrame126(""+c.Favourites_4+"", 31012);
		c.getPA().sendFrame126(""+c.Favourites_5+"", 31013);
		c.getPA().sendFrame126(""+c.Favourites_6+"", 31014);
		c.getPA().sendFrame126(""+c.Favourites_7+"", 31015);
		c.getPA().sendFrame126(""+c.Favourites_8+"", 31016);

		int[] cost = { 31053, 31058, 31063, 31068, 31073, 31078, 31083, 31088, 31093, 31098, 31103, 31108, 31113, 31118, 31123, 31128, 31133, 31138, 31143, 31148};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(playerKillingPrices[j], cost[j]);

		}
		int[] zones = { 31054, 31059, 31064, 31069, 31074, 31079, 31084, 31089, 31094, 31099, 31104, 31109, 31114, 31119, 31124, 31129, 31134, 31139, 31144, 31149};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(playerKillingDangers[j], zones[j]);

		}
		int[] ids = { 31052, 31057, 31062, 31067, 31072, 31077, 31082, 31087, 31092, 31097, 31102, 31107, 31112, 31117, 31122, 31127, 31132, 31137, 31142, 31147};
		for (int j = 0; j <= 19; j++) {
			c.getPA().sendFrame126(playerKillingNames[j], ids[j]);

		}
	}


	public void loadTab(Player player, int tab) {
		if (player.teleSelected == 0) {
			loadMonsterTab();
		} else if (player.teleSelected == 1) {
			loadDungeonsTab();
		} else if (player.teleSelected == 2) {
			loadBossesTab();
		} else if (player.teleSelected == 3) {
			loadMinigamesTab();
		} else if (player.teleSelected == 4) {
			loadSkillingTab();
		} else if (player.teleSelected == 5) {
			loadPlayerKillingTab();
		}
	}

	public void selection(Player player, int i) {
		player.teleSelected = i;
		loadTab(player, i);
	}
	public void handleRecentsOne(Player c) {
		if (c.teleSelected == 0) {
			if (c.recent1_Name == c.getTeleport().monsterNames[c.teleportSelected]) {
			} else {
				c.recent1_Name = c.getTeleport().monsterNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 1) {
			if (c.recent1_Name == c.getTeleport().dungeonNames[c.teleportSelected]) {
			} else {
				c.recent1_Name = c.getTeleport().dungeonNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 2) {
			if (c.recent1_Name == c.getTeleport().bossNames[c.teleportSelected]) {
			} else {
				c.recent1_Name = c.getTeleport().bossNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 3) {
			if (c.recent1_Name == c.getTeleport().minigameNames[c.teleportSelected]) {
			} else {
				c.recent1_Name = c.getTeleport().minigameNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 4) {
			if (c.recent1_Name == c.getTeleport().skillingNames[c.teleportSelected]) {
			} else {
				c.recent1_Name = c.getTeleport().skillingNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 5) {
			if (c.recent1_Name == c.getTeleport().playerKillingNames[c.teleportSelected]) {
			} else {
				c.recent1_Name = c.getTeleport().playerKillingNames[c.teleportSelected];
			}
		}
	}
	public void handleRecentsTwo(Player c) {
		if (c.teleSelected == 0) {
			if (c.recent2_Name == c.getTeleport().monsterNames[c.teleportSelected]) {
			} else {
				c.recent2_Name = c.getTeleport().monsterNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 1) {
			if (c.recent2_Name == c.getTeleport().dungeonNames[c.teleportSelected]) {
			} else {
				c.recent2_Name = c.getTeleport().dungeonNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 2) {
			if (c.recent2_Name == c.getTeleport().bossNames[c.teleportSelected]) {
			} else {
				c.recent2_Name = c.getTeleport().bossNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 3) {
			if (c.recent2_Name == c.getTeleport().minigameNames[c.teleportSelected]) {
			} else {
				c.recent2_Name = c.getTeleport().minigameNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 4) {
			if (c.recent2_Name == c.getTeleport().skillingNames[c.teleportSelected]) {
			} else {
				c.recent2_Name = c.getTeleport().skillingNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 5) {
			if (c.recent2_Name == c.getTeleport().playerKillingNames[c.teleportSelected]) {
			} else {
				c.recent2_Name = c.getTeleport().playerKillingNames[c.teleportSelected];
			}
		}
	}
	public void handleRecentsThree(Player c) {
		if (c.teleSelected == 0) {
			if (c.recent3_Name == c.getTeleport().monsterNames[c.teleportSelected]) {
			} else {
				c.recent3_Name = c.getTeleport().monsterNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 1) {
			if (c.recent3_Name == c.getTeleport().dungeonNames[c.teleportSelected]) {
			} else {
				c.recent3_Name = c.getTeleport().dungeonNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 2) {
			if (c.recent3_Name == c.getTeleport().bossNames[c.teleportSelected]) {
			} else {
				c.recent3_Name = c.getTeleport().bossNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 3) {
			if (c.recent3_Name == c.getTeleport().minigameNames[c.teleportSelected]) {
			} else {
				c.recent3_Name = c.getTeleport().minigameNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 4) {
			if (c.recent3_Name == c.getTeleport().skillingNames[c.teleportSelected]) {
			} else {
				c.recent3_Name = c.getTeleport().skillingNames[c.teleportSelected];
			}
		} else if (c.teleSelected == 5) {
			if (c.recent3_Name == c.getTeleport().playerKillingNames[c.teleportSelected]) {
			} else {
				c.recent3_Name = c.getTeleport().playerKillingNames[c.teleportSelected];
			}
		}
	}
	public void updateInterface() {
		c.getPA().showInterface(31000);
		c.getTeleport().selection(c, c.teleSelected);
	}
	public void handleFavourites() { 
		if (!c.Favourites_1.isEmpty() && c.Favourites_1 == c.getTeleport().monsterNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_2.isEmpty() && c.Favourites_2 == c.getTeleport().monsterNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_3.isEmpty() && c.Favourites_3 == c.getTeleport().monsterNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_4.isEmpty() && c.Favourites_4 == c.getTeleport().monsterNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_5.isEmpty() && c.Favourites_5 == c.getTeleport().monsterNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_6.isEmpty() && c.Favourites_6 == c.getTeleport().monsterNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_7.isEmpty() && c.Favourites_7 == c.getTeleport().monsterNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_8.isEmpty() && c.Favourites_8 == c.getTeleport().monsterNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_1.isEmpty() && c.Favourites_1 == c.getTeleport().dungeonNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_2.isEmpty() && c.Favourites_2 == c.getTeleport().dungeonNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_3.isEmpty() && c.Favourites_3 == c.getTeleport().dungeonNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_4.isEmpty() && c.Favourites_4 == c.getTeleport().dungeonNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_5.isEmpty() && c.Favourites_5 == c.getTeleport().dungeonNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_6.isEmpty() && c.Favourites_6 == c.getTeleport().dungeonNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_7.isEmpty() && c.Favourites_8 == c.getTeleport().dungeonNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_8.isEmpty() && c.Favourites_8 == c.getTeleport().dungeonNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_1.isEmpty() && c.Favourites_1 == c.getTeleport().bossNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_2.isEmpty() && c.Favourites_2 == c.getTeleport().bossNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_3.isEmpty() && c.Favourites_3 == c.getTeleport().bossNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_4.isEmpty() && c.Favourites_4 == c.getTeleport().bossNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_5.isEmpty() && c.Favourites_5 == c.getTeleport().bossNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_6.isEmpty() && c.Favourites_6 == c.getTeleport().bossNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_7.isEmpty() && c.Favourites_7 == c.getTeleport().bossNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_8.isEmpty() && c.Favourites_8 == c.getTeleport().bossNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_1.isEmpty() && c.Favourites_1 == c.getTeleport().minigameNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_2.isEmpty() && c.Favourites_2 == c.getTeleport().minigameNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_3.isEmpty() && c.Favourites_3 == c.getTeleport().minigameNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_4.isEmpty() && c.Favourites_4 == c.getTeleport().minigameNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_5.isEmpty() && c.Favourites_5 == c.getTeleport().minigameNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_6.isEmpty() && c.Favourites_6 == c.getTeleport().minigameNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_7.isEmpty() && c.Favourites_7 == c.getTeleport().minigameNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_8.isEmpty() && c.Favourites_8 == c.getTeleport().minigameNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_1.isEmpty() && c.Favourites_1 == c.getTeleport().skillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_2.isEmpty() && c.Favourites_2 == c.getTeleport().skillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_3.isEmpty() && c.Favourites_3 == c.getTeleport().skillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_4.isEmpty() && c.Favourites_4 == c.getTeleport().skillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_5.isEmpty() && c.Favourites_5 == c.getTeleport().skillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_6.isEmpty() && c.Favourites_6 == c.getTeleport().skillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_7.isEmpty() && c.Favourites_7 == c.getTeleport().skillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_8.isEmpty() && c.Favourites_8 == c.getTeleport().skillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_1.isEmpty() && c.Favourites_1 == c.getTeleport().playerKillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_2.isEmpty() && c.Favourites_2 == c.getTeleport().playerKillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_3.isEmpty() && c.Favourites_3 == c.getTeleport().playerKillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_4.isEmpty() && c.Favourites_4 == c.getTeleport().playerKillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_5.isEmpty() && c.Favourites_5 == c.getTeleport().playerKillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_6.isEmpty() && c.Favourites_6 == c.getTeleport().playerKillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_7.isEmpty() && c.Favourites_7 == c.getTeleport().playerKillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (!c.Favourites_8.isEmpty() && c.Favourites_8 == c.getTeleport().playerKillingNames[c.favouritesSelected]) {
			c.sendMessage("This location is already listed as a favourite.");
			return;
		}
		if (c.teleSelected == 0 && c.Favourites_1.isEmpty()) {
			c.Favourites_1 = c.getTeleport().monsterNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 1 && c.Favourites_1.isEmpty()) {
			c.Favourites_1 = c.getTeleport().dungeonNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 2 && c.Favourites_1.isEmpty()) {
			c.Favourites_1 = c.getTeleport().bossNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 3 && c.Favourites_1.isEmpty()) {
			c.Favourites_1 = c.getTeleport().minigameNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 4 && c.Favourites_1.isEmpty()) {
			c.Favourites_1 = c.getTeleport().skillingNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 5 && c.Favourites_1.isEmpty()) {
			c.Favourites_1 = c.getTeleport().playerKillingNames[c.favouritesSelected];
			updateInterface();
			return;
		}
		if (c.teleSelected == 0 && c.Favourites_2.isEmpty()) {
			c.Favourites_2 = c.getTeleport().monsterNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 1 && c.Favourites_2.isEmpty()) {
			c.Favourites_2 = c.getTeleport().dungeonNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 2 && c.Favourites_2.isEmpty()) {
			c.Favourites_2 = c.getTeleport().bossNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 3 && c.Favourites_2.isEmpty()) {
			c.Favourites_2 = c.getTeleport().minigameNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 4 && c.Favourites_2.isEmpty()) {
			c.Favourites_2 = c.getTeleport().skillingNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 5 && c.Favourites_2.isEmpty()) {
			c.Favourites_2 = c.getTeleport().playerKillingNames[c.favouritesSelected];
			updateInterface();
			return;
		}
		if (c.teleSelected == 0 && c.Favourites_3.isEmpty()) {
			c.Favourites_3 = c.getTeleport().monsterNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 1 && c.Favourites_3.isEmpty()) {
			c.Favourites_3 = c.getTeleport().dungeonNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 2 && c.Favourites_3.isEmpty()) {
			c.Favourites_3 = c.getTeleport().bossNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 3 && c.Favourites_3.isEmpty()) {
			c.Favourites_3 = c.getTeleport().minigameNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 4 && c.Favourites_3.isEmpty()) {
			c.Favourites_3 = c.getTeleport().skillingNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 5 && c.Favourites_3.isEmpty()) {
			c.Favourites_3 = c.getTeleport().playerKillingNames[c.favouritesSelected];
			updateInterface();
			return;
		}
		if (c.teleSelected == 0 && c.Favourites_4.isEmpty()) {
			c.Favourites_4 = c.getTeleport().monsterNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 1 && c.Favourites_4.isEmpty()) {
			c.Favourites_4 = c.getTeleport().dungeonNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 2 && c.Favourites_4.isEmpty()) {
			c.Favourites_4 = c.getTeleport().bossNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 3 && c.Favourites_4.isEmpty()) {
			c.Favourites_4 = c.getTeleport().minigameNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 4 && c.Favourites_4.isEmpty()) {
			c.Favourites_4 = c.getTeleport().skillingNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 5 && c.Favourites_4.isEmpty()) {
			c.Favourites_4 = c.getTeleport().playerKillingNames[c.favouritesSelected];
			updateInterface();
			return;
		}
		if (c.teleSelected == 0 && c.Favourites_5.isEmpty()) {
			c.Favourites_5 = c.getTeleport().monsterNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 1 && c.Favourites_5.isEmpty()) {
			c.Favourites_5 = c.getTeleport().dungeonNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 2 && c.Favourites_5.isEmpty()) {
			c.Favourites_5 = c.getTeleport().bossNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 3 && c.Favourites_5.isEmpty()) {
			c.Favourites_5 = c.getTeleport().minigameNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 4 && c.Favourites_5.isEmpty()) {
			c.Favourites_5 = c.getTeleport().skillingNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 5 && c.Favourites_5.isEmpty()) {
			c.Favourites_5 = c.getTeleport().playerKillingNames[c.favouritesSelected];
			updateInterface();
			return;
		}
		if (c.teleSelected == 0 && c.Favourites_6.isEmpty()) {
			c.Favourites_6 = c.getTeleport().monsterNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 1 && c.Favourites_6.isEmpty()) {
			c.Favourites_6 = c.getTeleport().dungeonNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 2 && c.Favourites_6.isEmpty()) {
			c.Favourites_6 = c.getTeleport().bossNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 3 && c.Favourites_6.isEmpty()) {
			c.Favourites_6 = c.getTeleport().minigameNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 4 && c.Favourites_6.isEmpty()) {
			c.Favourites_6 = c.getTeleport().skillingNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 5 && c.Favourites_6.isEmpty()) {
			c.Favourites_6 = c.getTeleport().playerKillingNames[c.favouritesSelected];
			updateInterface();
			return;
		}
		if (c.teleSelected == 0 && c.Favourites_7.isEmpty()) {
			c.Favourites_7 = c.getTeleport().monsterNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 1 && c.Favourites_7.isEmpty()) {
			c.Favourites_7 = c.getTeleport().dungeonNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 2 && c.Favourites_7.isEmpty()) {
			c.Favourites_7 = c.getTeleport().bossNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 3 && c.Favourites_7.isEmpty()) {
			c.Favourites_7 = c.getTeleport().minigameNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 4 && c.Favourites_7.isEmpty()) {
			c.Favourites_7 = c.getTeleport().skillingNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 5 && c.Favourites_7.isEmpty()) {
			c.Favourites_7 = c.getTeleport().playerKillingNames[c.favouritesSelected];
			updateInterface();
			return;
		}
		if (c.teleSelected == 0 && c.Favourites_8.isEmpty()) {
			c.Favourites_8 = c.getTeleport().monsterNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 1 && c.Favourites_8.isEmpty()) {
			c.Favourites_8 = c.getTeleport().dungeonNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 2 && c.Favourites_8.isEmpty()) {
			c.Favourites_8 = c.getTeleport().bossNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 3 && c.Favourites_8.isEmpty()) {
			c.Favourites_8 = c.getTeleport().minigameNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 4 && c.Favourites_8.isEmpty()) {
			c.Favourites_8 = c.getTeleport().skillingNames[c.favouritesSelected];
			updateInterface();
			return;
		} else if (c.teleSelected == 5 && c.Favourites_8.isEmpty()) {
			c.Favourites_8 = c.getTeleport().playerKillingNames[c.favouritesSelected];
			updateInterface();
			return;
		}
	}
	public void teleportToFavourites(Player c) {
			if (c.selectedFavourite == 1 && c.Favourites_1 == "Rock Crabs") {
				c.getPA().spellTeleport(2672, 3710, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Sand Crabs") {
				c.getPA().spellTeleport(1683, 3487, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Yaks") {
				c.getPA().spellTeleport(2323, 3804, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Mithril Dragons") {
				c.getPA().spellTeleport(1748, 5330, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Kalphite") {
				c.getPA().spellTeleport(3507,9493, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Demonic Gorillas") {
				c.getPA().spellTeleport(2130,5646, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Wyverns") {
				c.getPA().spellTeleport(3056,9555, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Mini Hydra") {
				c.getPA().spellTeleport(1351,10259, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Cows") {
				c.getPA().spellTeleport(3259,3261, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Desert Bandits") {
				c.getPA().spellTeleport(3176,2987, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Elf Warriors") {
				c.getPA().spellTeleport(2897,2725, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Dagganoths") {
				c.getPA().spellTeleport(1913, 4367, 0, false);

				//favorite 2
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Rock Crabs") {
					c.getPA().spellTeleport(2672, 3710, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Sand Crabs") {
					c.getPA().spellTeleport(1683, 3487, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Yaks") {
					c.getPA().spellTeleport(2323, 3804, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Mithril Dragons") {
					c.getPA().spellTeleport(1748, 5330, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Kalphite") {
					c.getPA().spellTeleport(3507,9493, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Demonic Gorillas") {
					c.getPA().spellTeleport(2130,5646, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Wyverns") {
					c.getPA().spellTeleport(3056,9555, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Mini Hydra") {
					c.getPA().spellTeleport(1351,10259, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Cows") {
					c.getPA().spellTeleport(3259,3261, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Desert Bandits") {
					c.getPA().spellTeleport(3176,2987, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Elf Warriors") {
					c.getPA().spellTeleport(2897,2725, 0, false);
				} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Dagganoths") {
					c.getPA().spellTeleport(1913,4367, 0, false);


				//favorite 3
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Rock Crabs") {
				c.getPA().spellTeleport(2672, 3710, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Sand Crabs") {
				c.getPA().spellTeleport(1683, 3487, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Yaks") {
				c.getPA().spellTeleport(2323, 3804, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Mithril Dragons") {
				c.getPA().spellTeleport(1748, 5330, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Kalphite") {
				c.getPA().spellTeleport(3507,9493, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Demonic Gorillas") {
				c.getPA().spellTeleport(2130,5646, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Wyverns") {
				c.getPA().spellTeleport(3056,9555, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Mini Hydra") {
				c.getPA().spellTeleport(1351,10259, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Cows") {
				c.getPA().spellTeleport(3259,3261, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Desert Bandits") {
				c.getPA().spellTeleport(3176,2987, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Elf Warriors") {
				c.getPA().spellTeleport(2897,2725, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Dagganoths") {
				c.getPA().spellTeleport(1913,4367, 0, false);

				//favorite 4
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Rock Crabs") {
				c.getPA().spellTeleport(2672, 3710, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Sand Crabs") {
				c.getPA().spellTeleport(1683, 3487, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Yaks") {
				c.getPA().spellTeleport(2323, 3804, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Mithril Dragons") {
				c.getPA().spellTeleport(1748, 5330, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Kalphite") {
				c.getPA().spellTeleport(3507,9493, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Demonic Gorillas") {
				c.getPA().spellTeleport(2130,5646, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Wyverns") {
				c.getPA().spellTeleport(3056,9555, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Mini Hydra") {
				c.getPA().spellTeleport(1351,10259, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Cows") {
				c.getPA().spellTeleport(3259,3261, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Desert Bandits") {
				c.getPA().spellTeleport(3176,2987, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Elf Warriors") {
				c.getPA().spellTeleport(2897,2725, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Dagganoths") {
				c.getPA().spellTeleport(1913,4367, 0, false);

				//favorite 5
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Rock Crabs") {
				c.getPA().spellTeleport(2672, 3710, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Sand Crabs") {
				c.getPA().spellTeleport(1683, 3487, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Yaks") {
				c.getPA().spellTeleport(2323, 3804, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Mithril Dragons") {
				c.getPA().spellTeleport(1748, 5330, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Kalphite") {
				c.getPA().spellTeleport(3507,9493, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Demonic Gorillas") {
				c.getPA().spellTeleport(2130,5646, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Wyverns") {
				c.getPA().spellTeleport(3056,9555, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Mini Hydra") {
				c.getPA().spellTeleport(1351,10259, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Cows") {
				c.getPA().spellTeleport(3259,3261, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Desert Bandits") {
				c.getPA().spellTeleport(3176,2987, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Elf Warriors") {
				c.getPA().spellTeleport(2897,2725, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Dagganoths") {
				c.getPA().spellTeleport(1913,4367, 0, false);


				//favorite 6
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Rock Crabs") {
				c.getPA().spellTeleport(2672, 3710, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Sand Crabs") {
				c.getPA().spellTeleport(1683, 3487, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Yaks") {
				c.getPA().spellTeleport(2323, 3804, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Mithril Dragons") {
				c.getPA().spellTeleport(1748, 5330, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Kalphite") {
				c.getPA().spellTeleport(3507,9493, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Demonic Gorillas") {
				c.getPA().spellTeleport(2130,5646, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Wyverns") {
				c.getPA().spellTeleport(3056,9555, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Mini Hydra") {
				c.getPA().spellTeleport(1351,10259, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Cows") {
				c.getPA().spellTeleport(3259,3261, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Desert Bandits") {
				c.getPA().spellTeleport(3176,2987, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Elf Warriors") {
				c.getPA().spellTeleport(2897,2725, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Dagganoths") {
				c.getPA().spellTeleport(1913,4367, 0, false);

				//favorite 7
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Rock Crabs") {
				c.getPA().spellTeleport(2672, 3710, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Sand Crabs") {
				c.getPA().spellTeleport(1683, 3487, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Yaks") {
				c.getPA().spellTeleport(2323, 3804, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Mithril Dragons") {
				c.getPA().spellTeleport(1748, 5330, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Kalphite") {
				c.getPA().spellTeleport(3507,9493, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Demonic Gorillas") {
				c.getPA().spellTeleport(2130,5646, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Wyverns") {
				c.getPA().spellTeleport(3056,9555, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Mini Hydra") {
				c.getPA().spellTeleport(1351,10259, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Cows") {
				c.getPA().spellTeleport(3259,3261, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Desert Bandits") {
				c.getPA().spellTeleport(3176,2987, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Elf Warriors") {
				c.getPA().spellTeleport(2897,2725, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Dagganoths") {
				c.getPA().spellTeleport(1913,4367, 0, false);

				//favorite 8
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Rock Crabs") {
				c.getPA().spellTeleport(2672, 3710, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Sand Crabs") {
				c.getPA().spellTeleport(1683, 3487, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Yaks") {
				c.getPA().spellTeleport(2323, 3804, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Mithril Dragons") {
				c.getPA().spellTeleport(1748, 5330, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Kalphite") {
				c.getPA().spellTeleport(3507,9493, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Demonic Gorillas") {
				c.getPA().spellTeleport(2130,5646, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Wyverns") {
				c.getPA().spellTeleport(3056,9555, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Mini Hydra") {
				c.getPA().spellTeleport(1351,10259, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Cows") {
				c.getPA().spellTeleport(3259,3261, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Desert Bandits") {
				c.getPA().spellTeleport(3176,2987, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Elf Warriors") {
				c.getPA().spellTeleport(2897,2725, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Dagganoths") {
				c.getPA().spellTeleport(1913,4367, 0, false);
				//monster tab

				// dungeons tab
				//favorite 1
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Slayer Tower") {
				c.getPA().spellTeleport(3425, 3538, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Edveville Dungeon") {
				c.getPA().spellTeleport(3097, 9869, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Taverly Dungeon") {
				c.getPA().spellTeleport(2884, 9798, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Brimhaven Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Rellekka Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Iletya Elves") {
				c.getPA().spellTeleport(2352, 3156, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Catacombs") {
				c.getPA().spellTeleport(1666, 10047, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Karuulm Dungeon") {
				c.getPA().spellTeleport(1311, 10222, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Lithkren Vault") {
				c.getPA().spellTeleport(1567, 5074, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Ice Dungeon") {
				c.getPA().spellTeleport(3035, 9581, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Smoke Dungeon") {
				c.getPA().spellTeleport(2381, 9463, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Kraken") {
				c.getPA().spellTeleport(2276, 9989, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Nieve's Cave") {
				c.getPA().spellTeleport(2412, 9785, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Crystal Cave") {
				c.getPA().spellTeleport(3274, 6054, 0, false);

				//favourite 2
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Slayer Tower") {
				c.getPA().spellTeleport(3425, 3538, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Edveville Dungeon") {
				c.getPA().spellTeleport(3097, 9869, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Taverly Dungeon") {
				c.getPA().spellTeleport(2884, 9798, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Brimhaven Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Rellekka Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Iletya Elves") {
				c.getPA().spellTeleport(2352, 3156, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Catacombs") {
				c.getPA().spellTeleport(1666, 10047, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Karuulm Dungeon") {
				c.getPA().spellTeleport(1311, 10222, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Lithkren Vault") {
				c.getPA().spellTeleport(1567, 5074, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Ice Dungeon") {
				c.getPA().spellTeleport(3035, 9581, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Smoke Dungeon") {
				c.getPA().spellTeleport(2381, 9463, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Kraken") {
				c.getPA().spellTeleport(2276, 9989, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Nieve's Cave") {
				c.getPA().spellTeleport(2412, 9785, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Crystal Cave") {
				c.getPA().spellTeleport(3274, 6054, 0, false);

				//favorite 3
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Slayer Tower") {
				c.getPA().spellTeleport(3425, 3538, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Edveville Dungeon") {
				c.getPA().spellTeleport(3097, 9869, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Taverly Dungeon") {
				c.getPA().spellTeleport(2884, 9798, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Brimhaven Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Rellekka Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Iletya Elves") {
				c.getPA().spellTeleport(2352, 3156, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Catacombs") {
				c.getPA().spellTeleport(1666, 10047, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Karuulm Dungeon") {
				c.getPA().spellTeleport(1311, 10222, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Lithkren Vault") {
				c.getPA().spellTeleport(1567, 5074, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Ice Dungeon") {
				c.getPA().spellTeleport(3035, 9581, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Smoke Dungeon") {
				c.getPA().spellTeleport(2381, 9463, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Kraken") {
				c.getPA().spellTeleport(2276, 9989, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Nieve's Cave") {
				c.getPA().spellTeleport(2412, 9785, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Crystal Cave") {
				c.getPA().spellTeleport(3274, 6054, 0, false);

				//favorite 4
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Slayer Tower") {
				c.getPA().spellTeleport(3425, 3538, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Edveville Dungeon") {
				c.getPA().spellTeleport(3097, 9869, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Taverly Dungeon") {
				c.getPA().spellTeleport(2884, 9798, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Brimhaven Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Rellekka Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Iletya Elves") {
				c.getPA().spellTeleport(2352, 3156, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Catacombs") {
				c.getPA().spellTeleport(1666, 10047, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Karuulm Dungeon") {
				c.getPA().spellTeleport(1311, 10222, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Lithkren Vault") {
				c.getPA().spellTeleport(1567, 5074, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Ice Dungeon") {
				c.getPA().spellTeleport(3035, 9581, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Smoke Dungeon") {
				c.getPA().spellTeleport(2381, 9463, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Kraken") {
				c.getPA().spellTeleport(2276, 9989, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Nieve's Cave") {
				c.getPA().spellTeleport(2412, 9785, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Crystal Cave") {
				c.getPA().spellTeleport(3274, 6054, 0, false);

				//favorite 5
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Slayer Tower") {
				c.getPA().spellTeleport(3425, 3538, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Edveville Dungeon") {
				c.getPA().spellTeleport(3097, 9869, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Taverly Dungeon") {
				c.getPA().spellTeleport(2884, 9798, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Brimhaven Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Rellekka Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Iletya Elves") {
				c.getPA().spellTeleport(2352, 3156, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Catacombs") {
				c.getPA().spellTeleport(1666, 10047, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Karuulm Dungeon") {
				c.getPA().spellTeleport(1311, 10222, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Lithkren Vault") {
				c.getPA().spellTeleport(1567, 5074, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Ice Dungeon") {
				c.getPA().spellTeleport(3035, 9581, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Smoke Dungeon") {
				c.getPA().spellTeleport(2381, 9463, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Kraken") {
				c.getPA().spellTeleport(2276, 9989, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Nieve's Cave") {
				c.getPA().spellTeleport(2412, 9785, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Crystal Cave") {
				c.getPA().spellTeleport(3274, 6054, 0, false);

				//favorite 6
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Slayer Tower") {
				c.getPA().spellTeleport(3425, 3538, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Edveville Dungeon") {
				c.getPA().spellTeleport(3097, 9869, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Taverly Dungeon") {
				c.getPA().spellTeleport(2884, 9798, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Brimhaven Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Rellekka Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Iletya Elves") {
				c.getPA().spellTeleport(2352, 3156, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Catacombs") {
				c.getPA().spellTeleport(1666, 10047, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Karuulm Dungeon") {
				c.getPA().spellTeleport(1311, 10222, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Lithkren Vault") {
				c.getPA().spellTeleport(1567, 5074, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Ice Dungeon") {
				c.getPA().spellTeleport(3035, 9581, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Smoke Dungeon") {
				c.getPA().spellTeleport(2381, 9463, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Kraken") {
				c.getPA().spellTeleport(2276, 9989, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Nieve's Cave") {
				c.getPA().spellTeleport(2412, 9785, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Crystal Cave") {
				c.getPA().spellTeleport(3274, 6054, 0, false);

				//favorite 7
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Slayer Tower") {
				c.getPA().spellTeleport(3425, 3538, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Edveville Dungeon") {
				c.getPA().spellTeleport(3097, 9869, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Taverly Dungeon") {
				c.getPA().spellTeleport(2884, 9798, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Brimhaven Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Rellekka Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Iletya Elves") {
				c.getPA().spellTeleport(2352, 3156, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Catacombs") {
				c.getPA().spellTeleport(1666, 10047, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Karuulm Dungeon") {
				c.getPA().spellTeleport(1311, 10222, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Lithkren Vault") {
				c.getPA().spellTeleport(1567, 5074, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Ice Dungeon") {
				c.getPA().spellTeleport(3035, 9581, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Smoke Dungeon") {
				c.getPA().spellTeleport(2381, 9463, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Kraken") {
				c.getPA().spellTeleport(2276, 9989, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Nieve's Cave") {
				c.getPA().spellTeleport(2412, 9785, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Crystal Cave") {
				c.getPA().spellTeleport(3274, 6054, 0, false);

				//favorite 8
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Slayer Tower") {
				c.getPA().spellTeleport(3425, 3538, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Edveville Dungeon") {
				c.getPA().spellTeleport(3097, 9869, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Taverly Dungeon") {
				c.getPA().spellTeleport(2884, 9798, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Brimhaven Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Rellekka Dungeon") {
				c.getPA().spellTeleport(2688, 9564, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Iletya Elves") {
				c.getPA().spellTeleport(2352, 3156, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Catacombs") {
				c.getPA().spellTeleport(1666, 10047, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Karuulm Dungeon") {
				c.getPA().spellTeleport(1311, 10222, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Lithkren Vault") {
				c.getPA().spellTeleport(1567, 5074, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Ice Dungeon") {
				c.getPA().spellTeleport(3035, 9581, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Smoke Dungeon") {
				c.getPA().spellTeleport(2381, 9463, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Kraken") {
				c.getPA().spellTeleport(2276, 9989, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Nieve's Cave") {
				c.getPA().spellTeleport(2412, 9785, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Crystal Cave") {
				c.getPA().spellTeleport(3274, 6054, 0, false);

				//bosses tab
				//favorite 1
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Hydra") {
				c.getPA().spellTeleport(1351, 10259, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Zulrah") {
				c.getPA().spellTeleport(2199, 3056, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Godwars") {
				c.getPA().spellTeleport(2880, 5313, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Vorkath") {
				c.getPA().spellTeleport(2272, 4048, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Lizardman Shaman") {
				c.getPA().spellTeleport(1477, 3690, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "King Black Dragon") {
				c.getPA().spellTeleport(2271, 4680, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Abyssal Sire") {
				c.getPA().spellTeleport(3039, 4976, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Corporeal Beast") {
				c.getPA().spellTeleport(2969, 4384, 2, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Chaos Elemental") {
				c.getPA().spellTeleport(3285, 3907, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Callisto") {
				c.getPA().spellTeleport(3313, 3826, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Venenatis") {
				c.getPA().spellTeleport(3343, 3741, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Vet'ion") {
				c.getPA().spellTeleport(3179, 3774, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Crazy Archaeologist") {
				c.getPA().spellTeleport(2983, 3675, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Giant Mole") {
				c.getPA().spellTeleport(1760, 5163, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Barrelchest") {
				c.getPA().spellTeleport(1226, 3498, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Dagannoth Kings") {
				c.getPA().spellTeleport(1913, 4367, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Cerberus") {
				c.getPA().spellTeleport(1309, 1253, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Thermonuclear smoke devil") {
				c.getPA().spellTeleport(2376, 9452, 0, false);

				//favorite 2
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Hydra") {
				c.getPA().spellTeleport(1351, 10259, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Zulrah") {
				c.getPA().spellTeleport(2199, 3056, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Godwars") {
				c.getPA().spellTeleport(2880, 5313, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Vorkath") {
				c.getPA().spellTeleport(2272, 4048, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Lizardman Shaman") {
				c.getPA().spellTeleport(1477, 3690, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "King Black Dragon") {
				c.getPA().spellTeleport(2271, 4680, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Abyssal Sire") {
				c.getPA().spellTeleport(3039, 4976, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Corporeal Beast") {
				c.getPA().spellTeleport(2969, 4384, 2, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Chaos Elemental") {
				c.getPA().spellTeleport(3285, 3907, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Callisto") {
				c.getPA().spellTeleport(3313, 3826, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Venenatis") {
				c.getPA().spellTeleport(3343, 3741, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Vet'ion") {
				c.getPA().spellTeleport(3179, 3774, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Crazy Archaeologist") {
				c.getPA().spellTeleport(2983, 3675, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Giant Mole") {
				c.getPA().spellTeleport(1760, 5163, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Barrelchest") {
				c.getPA().spellTeleport(1226, 3498, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Dagannoth Kings") {
				c.getPA().spellTeleport(1913, 4367, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Cerberus") {
				c.getPA().spellTeleport(1309, 1253, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Thermonuclear smoke devil") {
				c.getPA().spellTeleport(2376, 9452, 0, false);

				//favorite 3
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Hydra") {
				c.getPA().spellTeleport(1351, 10259, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Zulrah") {
				c.getPA().spellTeleport(2199, 3056, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Godwars") {
				c.getPA().spellTeleport(2880, 5313, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Vorkath") {
				c.getPA().spellTeleport(2272, 4048, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Lizardman Shaman") {
				c.getPA().spellTeleport(1477, 3690, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "King Black Dragon") {
				c.getPA().spellTeleport(2271, 4680, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Abyssal Sire") {
				c.getPA().spellTeleport(3039, 4976, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Corporeal Beast") {
				c.getPA().spellTeleport(2969, 4384, 2, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Chaos Elemental") {
				c.getPA().spellTeleport(3285, 3907, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Callisto") {
				c.getPA().spellTeleport(3313, 3826, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Venenatis") {
				c.getPA().spellTeleport(3343, 3741, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Vet'ion") {
				c.getPA().spellTeleport(3179, 3774, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Crazy Archaeologist") {
				c.getPA().spellTeleport(2983, 3675, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Giant Mole") {
				c.getPA().spellTeleport(1760, 5163, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Barrelchest") {
				c.getPA().spellTeleport(1226, 3498, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Dagannoth Kings") {
				c.getPA().spellTeleport(1913, 4367, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Cerberus") {
				c.getPA().spellTeleport(1309, 1253, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Thermonuclear smoke devil") {
				c.getPA().spellTeleport(2376, 9452, 0, false);

				//favorite 4
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Hydra") {
				c.getPA().spellTeleport(1351, 10259, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Zulrah") {
				c.getPA().spellTeleport(2199, 3056, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Godwars") {
				c.getPA().spellTeleport(2880, 5313, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Vorkath") {
				c.getPA().spellTeleport(2272, 4048, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Lizardman Shaman") {
				c.getPA().spellTeleport(1477, 3690, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "King Black Dragon") {
				c.getPA().spellTeleport(2271, 4680, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Abyssal Sire") {
				c.getPA().spellTeleport(3039, 4976, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Corporeal Beast") {
				c.getPA().spellTeleport(2969, 4384, 2, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Chaos Elemental") {
				c.getPA().spellTeleport(3285, 3907, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Callisto") {
				c.getPA().spellTeleport(3313, 3826, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Venenatis") {
				c.getPA().spellTeleport(3343, 3741, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Vet'ion") {
				c.getPA().spellTeleport(3179, 3774, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Crazy Archaeologist") {
				c.getPA().spellTeleport(2983, 3675, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Giant Mole") {
				c.getPA().spellTeleport(1760, 5163, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Barrelchest") {
				c.getPA().spellTeleport(1226, 3498, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Dagannoth Kings") {
				c.getPA().spellTeleport(1913, 4367, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Cerberus") {
				c.getPA().spellTeleport(1309, 1253, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Thermonuclear smoke devil") {
				c.getPA().spellTeleport(2376, 9452, 0, false);

				//favorite 5
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Hydra") {
				c.getPA().spellTeleport(1351, 10259, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Zulrah") {
				c.getPA().spellTeleport(2199, 3056, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Godwars") {
				c.getPA().spellTeleport(2880, 5313, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Vorkath") {
				c.getPA().spellTeleport(2272, 4048, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Lizardman Shaman") {
				c.getPA().spellTeleport(1477, 3690, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "King Black Dragon") {
				c.getPA().spellTeleport(2271, 4680, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Abyssal Sire") {
				c.getPA().spellTeleport(3039, 4976, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Corporeal Beast") {
				c.getPA().spellTeleport(2969, 4384, 2, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Chaos Elemental") {
				c.getPA().spellTeleport(3285, 3907, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Callisto") {
				c.getPA().spellTeleport(3313, 3826, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Venenatis") {
				c.getPA().spellTeleport(3343, 3741, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Vet'ion") {
				c.getPA().spellTeleport(3179, 3774, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Crazy Archaeologist") {
				c.getPA().spellTeleport(2983, 3675, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Giant Mole") {
				c.getPA().spellTeleport(1760, 5163, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Barrelchest") {
				c.getPA().spellTeleport(1226, 3498, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Dagannoth Kings") {
				c.getPA().spellTeleport(1913, 4367, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Cerberus") {
				c.getPA().spellTeleport(1309, 1253, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Thermonuclear smoke devil") {
				c.getPA().spellTeleport(2376, 9452, 0, false);

				//favorite 6
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Hydra") {
				c.getPA().spellTeleport(1351, 10259, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Zulrah") {
				c.getPA().spellTeleport(2199, 3056, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Godwars") {
				c.getPA().spellTeleport(2880, 5313, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Vorkath") {
				c.getPA().spellTeleport(2272, 4048, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Lizardman Shaman") {
				c.getPA().spellTeleport(1477, 3690, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "King Black Dragon") {
				c.getPA().spellTeleport(2271, 4680, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Abyssal Sire") {
				c.getPA().spellTeleport(3039, 4976, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Corporeal Beast") {
				c.getPA().spellTeleport(2969, 4384, 2, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Chaos Elemental") {
				c.getPA().spellTeleport(3285, 3907, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Callisto") {
				c.getPA().spellTeleport(3313, 3826, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Venenatis") {
				c.getPA().spellTeleport(3343, 3741, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Vet'ion") {
				c.getPA().spellTeleport(3179, 3774, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Crazy Archaeologist") {
				c.getPA().spellTeleport(2983, 3675, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Giant Mole") {
				c.getPA().spellTeleport(1760, 5163, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Barrelchest") {
				c.getPA().spellTeleport(1226, 3498, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Dagannoth Kings") {
				c.getPA().spellTeleport(1913, 4367, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Cerberus") {
				c.getPA().spellTeleport(1309, 1253, 0, false);
			} else if (c.selectedFavourite == 6&& c.Favourites_6 == "Thermonuclear smoke devil") {
				c.getPA().spellTeleport(2376, 9452, 0, false);


				//favorite 7
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Hydra") {
				c.getPA().spellTeleport(1351, 10259, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Zulrah") {
				c.getPA().spellTeleport(2199, 3056, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Godwars") {
				c.getPA().spellTeleport(2880, 5313, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Vorkath") {
				c.getPA().spellTeleport(2272, 4048, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Lizardman Shaman") {
				c.getPA().spellTeleport(1477, 3690, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "King Black Dragon") {
				c.getPA().spellTeleport(2271, 4680, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Abyssal Sire") {
				c.getPA().spellTeleport(3039, 4976, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Corporeal Beast") {
				c.getPA().spellTeleport(2969, 4384, 2, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Chaos Elemental") {
				c.getPA().spellTeleport(3285, 3907, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Callisto") {
				c.getPA().spellTeleport(3313, 3826, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Venenatis") {
				c.getPA().spellTeleport(3343, 3741, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Vet'ion") {
				c.getPA().spellTeleport(3179, 3774, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Crazy Archaeologist") {
				c.getPA().spellTeleport(2983, 3675, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Giant Mole") {
				c.getPA().spellTeleport(1760, 5163, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Barrelchest") {
				c.getPA().spellTeleport(1226, 3498, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Dagannoth Kings") {
				c.getPA().spellTeleport(1913, 4367, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Cerberus") {
				c.getPA().spellTeleport(1309, 1253, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7== "Thermonuclear smoke devil") {
				c.getPA().spellTeleport(2376, 9452, 0, false);


				//favorite 8
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Hydra") {
				c.getPA().spellTeleport(1351, 10259, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Zulrah") {
				c.getPA().spellTeleport(2199, 3056, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Godwars") {
				c.getPA().spellTeleport(2880, 5313, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Vorkath") {
				c.getPA().spellTeleport(2272, 4048, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Lizardman Shaman") {
				c.getPA().spellTeleport(1477, 3690, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "King Black Dragon") {
				c.getPA().spellTeleport(2271, 4680, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Abyssal Sire") {
				c.getPA().spellTeleport(3039, 4976, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Corporeal Beast") {
				c.getPA().spellTeleport(2969, 4384, 2, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Chaos Elemental") {
				c.getPA().spellTeleport(3285, 3907, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Callisto") {
				c.getPA().spellTeleport(3313, 3826, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Venenatis") {
				c.getPA().spellTeleport(3343, 3741, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Vet'ion") {
				c.getPA().spellTeleport(3179, 3774, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Crazy Archaeologist") {
				c.getPA().spellTeleport(2983, 3675, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Giant Mole") {
				c.getPA().spellTeleport(1760, 5163, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Barrelchest") {
				c.getPA().spellTeleport(1226, 3498, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Dagannoth Kings") {
				c.getPA().spellTeleport(1913, 4367, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Cerberus") {
				c.getPA().spellTeleport(1309, 1253, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Thermonuclear smoke devil") {
				c.getPA().spellTeleport(2376, 9452, 0, false);

			//minigames tab
				//favorites 1
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Gambling") {
				c.getPA().spellTeleport(3160, 9491, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Pest control") {
				c.getPA().spellTeleport(2657, 2649, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Chambers Of Xeric") {
				c.getPA().spellTeleport(3033, 6067, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Gulag") {
				c.getPA().spellTeleport(3080, 3504, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Fight Caves") {
				c.getPA().spellTeleport(2439, 5169, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Warriors Guild") {
				c.getPA().spellTeleport(2496, 5113, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Barrows") {
				c.getPA().spellTeleport(3565, 3307, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Mage Arena") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Mage Arena 2") {
				c.sendMessage("No Teleport Found");
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Duel Arena") {
				c.getPA().spellTeleport(3364, 3267, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Lighthouse") {
				c.getPA().spellTeleport(2514, 3621, 0, false);

				//favorites 1
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Gambling") {
				c.getPA().spellTeleport(3160, 9491, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Pest control") {
				c.getPA().spellTeleport(2657, 2649, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Chambers Of Xeric") {
				c.getPA().spellTeleport(3033, 6067, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Gulag") {
				c.getPA().spellTeleport(3080, 3504, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Fight Caves") {
				c.getPA().spellTeleport(2439, 5169, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Warriors Guild") {
				c.getPA().spellTeleport(2496, 5113, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Barrows") {
				c.getPA().spellTeleport(3565, 3307, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Mage Arena") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Mage Arena 2") {
				c.sendMessage("No Teleport Found");
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Duel Arena") {
				c.getPA().spellTeleport(3364, 3267, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Lighthouse") {
				c.getPA().spellTeleport(2514, 3621, 0, false);

				//favorites 2
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Gambling") {
				c.getPA().spellTeleport(3160, 9491, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Pest control") {
				c.getPA().spellTeleport(2657, 2649, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Chambers Of Xeric") {
				c.getPA().spellTeleport(3033, 6067, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Gulag") {
				c.getPA().spellTeleport(3080, 3504, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Fight Caves") {
				c.getPA().spellTeleport(2439, 5169, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Warriors Guild") {
				c.getPA().spellTeleport(2496, 5113, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Barrows") {
				c.getPA().spellTeleport(3565, 3307, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Mage Arena") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Mage Arena 2") {
				c.sendMessage("No Teleport Found");
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Duel Arena") {
				c.getPA().spellTeleport(3364, 3267, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Lighthouse") {
				c.getPA().spellTeleport(2514, 3621, 0, false);

				//favorites 3
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Gambling") {
				c.getPA().spellTeleport(3160, 9491, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Pest control") {
				c.getPA().spellTeleport(2657, 2649, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Chambers Of Xeric") {
				c.getPA().spellTeleport(3033, 6067, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Gulag") {
				c.getPA().spellTeleport(3080, 3504, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Fight Caves") {
				c.getPA().spellTeleport(2439, 5169, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Warriors Guild") {
				c.getPA().spellTeleport(2496, 5113, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Barrows") {
				c.getPA().spellTeleport(3565, 3307, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Mage Arena") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Mage Arena 2") {
				c.sendMessage("No Teleport Found");
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Duel Arena") {
				c.getPA().spellTeleport(3364, 3267, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Lighthouse") {
				c.getPA().spellTeleport(2514, 3621, 0, false);

				//favorites 4
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Gambling") {
				c.getPA().spellTeleport(3160, 9491, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Pest control") {
				c.getPA().spellTeleport(2657, 2649, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Chambers Of Xeric") {
				c.getPA().spellTeleport(3033, 6067, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Gulag") {
				c.getPA().spellTeleport(3080, 3504, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Fight Caves") {
				c.getPA().spellTeleport(2439, 5169, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Warriors Guild") {
				c.getPA().spellTeleport(2496, 5113, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Barrows") {
				c.getPA().spellTeleport(3565, 3307, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Mage Arena") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Mage Arena 2") {
				c.sendMessage("No Teleport Found");
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Duel Arena") {
				c.getPA().spellTeleport(3364, 3267, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Lighthouse") {
				c.getPA().spellTeleport(2514, 3621, 0, false);

				//favorites 5
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Gambling") {
				c.getPA().spellTeleport(3160, 9491, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Pest control") {
				c.getPA().spellTeleport(2657, 2649, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Chambers Of Xeric") {
				c.getPA().spellTeleport(3033, 6067, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Gulag") {
				c.getPA().spellTeleport(3080, 3504, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Fight Caves") {
				c.getPA().spellTeleport(2439, 5169, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Warriors Guild") {
				c.getPA().spellTeleport(2496, 5113, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Barrows") {
				c.getPA().spellTeleport(3565, 3307, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Mage Arena") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Mage Arena 2") {
				c.sendMessage("No Teleport Found");
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Duel Arena") {
				c.getPA().spellTeleport(3364, 3267, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Lighthouse") {
				c.getPA().spellTeleport(2514, 3621, 0, false);

				//favorites 6
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Gambling") {
				c.getPA().spellTeleport(3160, 9491, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Pest control") {
				c.getPA().spellTeleport(2657, 2649, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Chambers Of Xeric") {
				c.getPA().spellTeleport(3033, 6067, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Gulag") {
				c.getPA().spellTeleport(3080, 3504, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Fight Caves") {
				c.getPA().spellTeleport(2439, 5169, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Warriors Guild") {
				c.getPA().spellTeleport(2496, 5113, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Barrows") {
				c.getPA().spellTeleport(3565, 3307, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Mage Arena") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Mage Arena 2") {
				c.sendMessage("No Teleport Found");
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Duel Arena") {
				c.getPA().spellTeleport(3364, 3267, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Lighthouse") {
				c.getPA().spellTeleport(2514, 3621, 0, false);

				//favorites 7
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Gambling") {
				c.getPA().spellTeleport(3160, 9491, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Pest control") {
				c.getPA().spellTeleport(2657, 2649, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Chambers Of Xeric") {
				c.getPA().spellTeleport(3033, 6067, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Gulag") {
				c.getPA().spellTeleport(3080, 3504, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Fight Caves") {
				c.getPA().spellTeleport(2439, 5169, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Warriors Guild") {
				c.getPA().spellTeleport(2496, 5113, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Barrows") {
				c.getPA().spellTeleport(3565, 3307, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Mage Arena") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Mage Arena 2") {
				c.sendMessage("No Teleport Found");
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Duel Arena") {
				c.getPA().spellTeleport(3364, 3267, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Lighthouse") {
				c.getPA().spellTeleport(2514, 3621, 0, false);

				//favorites 8
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Gambling") {
				c.getPA().spellTeleport(3160, 9491, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Pest control") {
				c.getPA().spellTeleport(2657, 2649, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Chambers Of Xeric") {
				c.getPA().spellTeleport(3033, 6067, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Gulag") {
				c.getPA().spellTeleport(3080, 3504, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Fight Caves") {
				c.getPA().spellTeleport(2439, 5169, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Warriors Guild") {
				c.getPA().spellTeleport(2496, 5113, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Barrows") {
				c.getPA().spellTeleport(3565, 3307, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Mage Arena") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Mage Arena 2") {
				c.sendMessage("No Teleport Found");
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Duel Arena") {
				c.getPA().spellTeleport(3364, 3267, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Lighthouse") {
				c.getPA().spellTeleport(2514, 3621, 0, false);

			//skilling
				//favorite 1
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Skilling Centre") {
				c.getPA().spellTeleport(3803, 3539, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Woodcutting Guild") {
				c.getPA().spellTeleport(1656, 3505, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Fishing Guild") {
				c.getPA().spellTeleport(2611, 3400, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Mining Guild") {
				c.getPA().spellTeleport(3046, 9754, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Barb Fishing") {
				c.getPA().spellTeleport(2505, 3488, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Crafting guild") {
				c.getPA().spellTeleport(2933, 3285, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Gnome Agility") {
				c.getPA().spellTeleport(2468, 3435, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Barb Agility") {
				c.getPA().spellTeleport(2552, 3558, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Wildy Agility") {
				c.getPA().spellTeleport(2998, 3913, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Lumbridge") {
				c.getPA().spellTeleport(3222, 3219, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Varrock") {
				c.getPA().spellTeleport(3212, 3429, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Falador") {
				c.getPA().spellTeleport(2964, 3379, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Camelot") {
				c.getPA().spellTeleport(2757, 3477, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Ardougne") {
				c.getPA().spellTeleport(2661, 3310, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Hunter") {
				c.getPA().spellTeleport(1580, 3437, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Puro Puro") {
				c.getPA().spellTeleport(2592, 4317, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Farming") {
				c.getPA().spellTeleport(3003, 3376, 0, false);

				//favorite 2
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Skilling Centre") {
				c.getPA().spellTeleport(3803, 3539, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Woodcutting Guild") {
				c.getPA().spellTeleport(1656, 3505, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Fishing Guild") {
				c.getPA().spellTeleport(2611, 3400, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Mining Guild") {
				c.getPA().spellTeleport(3046, 9754, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Barb Fishing") {
				c.getPA().spellTeleport(2505, 3488, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Crafting guild") {
				c.getPA().spellTeleport(2933, 3285, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Gnome Agility") {
				c.getPA().spellTeleport(2468, 3435, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Barb Agility") {
				c.getPA().spellTeleport(2552, 3558, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Wildy Agility") {
				c.getPA().spellTeleport(2998, 3913, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Lumbridge") {
				c.getPA().spellTeleport(3222, 3219, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Varrock") {
				c.getPA().spellTeleport(3212, 3429, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Falador") {
				c.getPA().spellTeleport(2964, 3379, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Camelot") {
				c.getPA().spellTeleport(2757, 3477, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Ardougne") {
				c.getPA().spellTeleport(2661, 3310, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Hunter") {
				c.getPA().spellTeleport(1580, 3437, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Puro Puro") {
				c.getPA().spellTeleport(2592, 4317, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Farming") {
				c.getPA().spellTeleport(3003, 3376, 0, false);

				//favourite 3
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Skilling Centre") {
				c.getPA().spellTeleport(3803, 3539, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Woodcutting Guild") {
				c.getPA().spellTeleport(1656, 3505, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Fishing Guild") {
				c.getPA().spellTeleport(2611, 3400, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Mining Guild") {
				c.getPA().spellTeleport(3046, 9754, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Barb Fishing") {
				c.getPA().spellTeleport(2505, 3488, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Crafting guild") {
				c.getPA().spellTeleport(2933, 3285, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Gnome Agility") {
				c.getPA().spellTeleport(2468, 3435, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Barb Agility") {
				c.getPA().spellTeleport(2552, 3558, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Wildy Agility") {
				c.getPA().spellTeleport(2998, 3913, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Lumbridge") {
				c.getPA().spellTeleport(3222, 3219, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Varrock") {
				c.getPA().spellTeleport(3212, 3429, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Falador") {
				c.getPA().spellTeleport(2964, 3379, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Camelot") {
				c.getPA().spellTeleport(2757, 3477, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Ardougne") {
				c.getPA().spellTeleport(2661, 3310, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Hunter") {
				c.getPA().spellTeleport(1580, 3437, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Puro Puro") {
				c.getPA().spellTeleport(2592, 4317, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Farming") {
				c.getPA().spellTeleport(3003, 3376, 0, false);

				//favorite 4
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Skilling Centre") {
				c.getPA().spellTeleport(3803, 3539, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Woodcutting Guild") {
				c.getPA().spellTeleport(1656, 3505, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Fishing Guild") {
				c.getPA().spellTeleport(2611, 3400, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Mining Guild") {
				c.getPA().spellTeleport(3046, 9754, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Barb Fishing") {
				c.getPA().spellTeleport(2505, 3488, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Crafting guild") {
				c.getPA().spellTeleport(2933, 3285, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Gnome Agility") {
				c.getPA().spellTeleport(2468, 3435, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Barb Agility") {
				c.getPA().spellTeleport(2552, 3558, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Wildy Agility") {
				c.getPA().spellTeleport(2998, 3913, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Lumbridge") {
				c.getPA().spellTeleport(3222, 3219, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Varrock") {
				c.getPA().spellTeleport(3212, 3429, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Falador") {
				c.getPA().spellTeleport(2964, 3379, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Camelot") {
				c.getPA().spellTeleport(2757, 3477, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Ardougne") {
				c.getPA().spellTeleport(2661, 3310, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Hunter") {
				c.getPA().spellTeleport(1580, 3437, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Puro Puro") {
				c.getPA().spellTeleport(2592, 4317, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Farming") {
				c.getPA().spellTeleport(3003, 3376, 0, false);

				//favorite 5
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Skilling Centre") {
				c.getPA().spellTeleport(3803, 3539, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Woodcutting Guild") {
				c.getPA().spellTeleport(1656, 3505, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Fishing Guild") {
				c.getPA().spellTeleport(2611, 3400, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Mining Guild") {
				c.getPA().spellTeleport(3046, 9754, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Barb Fishing") {
				c.getPA().spellTeleport(2505, 3488, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Crafting guild") {
				c.getPA().spellTeleport(2933, 3285, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Gnome Agility") {
				c.getPA().spellTeleport(2468, 3435, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Barb Agility") {
				c.getPA().spellTeleport(2552, 3558, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Wildy Agility") {
				c.getPA().spellTeleport(2998, 3913, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Lumbridge") {
				c.getPA().spellTeleport(3222, 3219, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Varrock") {
				c.getPA().spellTeleport(3212, 3429, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Falador") {
				c.getPA().spellTeleport(2964, 3379, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Camelot") {
				c.getPA().spellTeleport(2757, 3477, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Ardougne") {
				c.getPA().spellTeleport(2661, 3310, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Hunter") {
				c.getPA().spellTeleport(1580, 3437, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Puro Puro") {
				c.getPA().spellTeleport(2592, 4317, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Farming") {
				c.getPA().spellTeleport(3003, 3376, 0, false);

				//favorite 6
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Skilling Centre") {
				c.getPA().spellTeleport(3803, 3539, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Woodcutting Guild") {
				c.getPA().spellTeleport(1656, 3505, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Fishing Guild") {
				c.getPA().spellTeleport(2611, 3400, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Mining Guild") {
				c.getPA().spellTeleport(3046, 9754, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Barb Fishing") {
				c.getPA().spellTeleport(2505, 3488, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Crafting guild") {
				c.getPA().spellTeleport(2933, 3285, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Gnome Agility") {
				c.getPA().spellTeleport(2468, 3435, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Barb Agility") {
				c.getPA().spellTeleport(2552, 3558, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Wildy Agility") {
				c.getPA().spellTeleport(2998, 3913, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Lumbridge") {
				c.getPA().spellTeleport(3222, 3219, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Varrock") {
				c.getPA().spellTeleport(3212, 3429, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Falador") {
				c.getPA().spellTeleport(2964, 3379, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Camelot") {
				c.getPA().spellTeleport(2757, 3477, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Ardougne") {
				c.getPA().spellTeleport(2661, 3310, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Hunter") {
				c.getPA().spellTeleport(1580, 3437, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Puro Puro") {
				c.getPA().spellTeleport(2592, 4317, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Farming") {
				c.getPA().spellTeleport(3003, 3376, 0, false);


				//favorite 7
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Skilling Centre") {
				c.getPA().spellTeleport(3803, 3539, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Woodcutting Guild") {
				c.getPA().spellTeleport(1656, 3505, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Fishing Guild") {
				c.getPA().spellTeleport(2611, 3400, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Mining Guild") {
				c.getPA().spellTeleport(3046, 9754, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Barb Fishing") {
				c.getPA().spellTeleport(2505, 3488, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Crafting guild") {
				c.getPA().spellTeleport(2933, 3285, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Gnome Agility") {
				c.getPA().spellTeleport(2468, 3435, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Barb Agility") {
				c.getPA().spellTeleport(2552, 3558, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Wildy Agility") {
				c.getPA().spellTeleport(2998, 3913, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Lumbridge") {
				c.getPA().spellTeleport(3222, 3219, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Varrock") {
				c.getPA().spellTeleport(3212, 3429, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Falador") {
				c.getPA().spellTeleport(2964, 3379, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Camelot") {
				c.getPA().spellTeleport(2757, 3477, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Ardougne") {
				c.getPA().spellTeleport(2661, 3310, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Hunter") {
				c.getPA().spellTeleport(1580, 3437, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Puro Puro") {
				c.getPA().spellTeleport(2592, 4317, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Farming") {
				c.getPA().spellTeleport(3003, 3376, 0, false);

				//favorite 8
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Skilling Centre") {
				c.getPA().spellTeleport(3803, 3539, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Woodcutting Guild") {
				c.getPA().spellTeleport(1656, 3505, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Fishing Guild") {
				c.getPA().spellTeleport(2611, 3400, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Mining Guild") {
				c.getPA().spellTeleport(3046, 9754, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Barb Fishing") {
				c.getPA().spellTeleport(2505, 3488, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Crafting guild") {
				c.getPA().spellTeleport(2933, 3285, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Gnome Agility") {
				c.getPA().spellTeleport(2468, 3435, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Barb Agility") {
				c.getPA().spellTeleport(2552, 3558, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Wildy Agility") {
				c.getPA().spellTeleport(2998, 3913, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Lumbridge") {
				c.getPA().spellTeleport(3222, 3219, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Varrock") {
				c.getPA().spellTeleport(3212, 3429, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Falador") {
				c.getPA().spellTeleport(2964, 3379, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Camelot") {
				c.getPA().spellTeleport(2757, 3477, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Ardougne") {
				c.getPA().spellTeleport(2661, 3310, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Hunter") {
				c.getPA().spellTeleport(1580, 3437, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Puro Puro") {
				c.getPA().spellTeleport(2592, 4317, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Farming") {
				c.getPA().spellTeleport(3003, 3376, 0, false);

				//Locations
				//favourite 1
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Information Center") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Grand Exchange") {
				c.getPA().spellTeleport(3319, 3690, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Casino") {
				c.getPA().spellTeleport(2985, 3596, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Chaos Temple") {
				c.getPA().spellTeleport(3237, 3633, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Chinchompa Hills") {
				c.getPA().spellTeleport(3138, 3785, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Revenants Cave") {
				c.getPA().spellTeleport(3127, 3832, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Graves") {
				c.getPA().spellTeleport(3149, 3671, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Greator Demons") {
				c.getPA().spellTeleport(3288, 3866, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Level 44 obelisk") {
				c.getPA().spellTeleport(2980, 3866, 0, false);
			} else if (c.selectedFavourite == 1 && c.Favourites_1 == "Level 50 obelisk") {
				c.getPA().spellTeleport(3307, 3916, 0, false);

				//favourite 2
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Information Center") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Grand Exchange") {
				c.getPA().spellTeleport(3319, 3690, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Casino") {
				c.getPA().spellTeleport(2985, 3596, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Chaos Temple") {
				c.getPA().spellTeleport(3237, 3633, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Chinchompa Hills") {
				c.getPA().spellTeleport(3138, 3785, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Revenants Cave") {
				c.getPA().spellTeleport(3127, 3832, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Graves") {
				c.getPA().spellTeleport(3149, 3671, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Greator Demons") {
				c.getPA().spellTeleport(3288, 3866, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Level 44 obelisk") {
				c.getPA().spellTeleport(2980, 3866, 0, false);
			} else if (c.selectedFavourite == 2 && c.Favourites_2 == "Level 50 obelisk") {
				c.getPA().spellTeleport(3307, 3916, 0, false);

				//favourite 3
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Information Center") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Grand Exchange") {
				c.getPA().spellTeleport(3319, 3690, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "West Dragons") {
				c.getPA().spellTeleport(2985, 3596, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Chaos Temple") {
				c.getPA().spellTeleport(3237, 3633, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Chinchompa Hills") {
				c.getPA().spellTeleport(3138, 3785, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Revenants Cave") {
				c.getPA().spellTeleport(3127, 3832, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Graves") {
				c.getPA().spellTeleport(3149, 3671, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Greator Demons") {
				c.getPA().spellTeleport(3288, 3866, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Level 44 obelisk") {
				c.getPA().spellTeleport(2980, 3866, 0, false);
			} else if (c.selectedFavourite == 3 && c.Favourites_3 == "Level 50 obelisk") {
				c.getPA().spellTeleport(3307, 3916, 0, false);

				//favourite 4
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Information Center") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Grand Exchange") {
				c.getPA().spellTeleport(3319, 3690, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "West Dragons") {
				c.getPA().spellTeleport(2985, 3596, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Chaos Temple") {
				c.getPA().spellTeleport(3237, 3633, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Chinchompa Hills") {
				c.getPA().spellTeleport(3138, 3785, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Revenants Cave") {
				c.getPA().spellTeleport(3127, 3832, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Graves") {
				c.getPA().spellTeleport(3149, 3671, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Greator Demons") {
				c.getPA().spellTeleport(3288, 3866, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Level 44 obelisk") {
				c.getPA().spellTeleport(2980, 3866, 0, false);
			} else if (c.selectedFavourite == 4 && c.Favourites_4 == "Level 50 obelisk") {
				c.getPA().spellTeleport(3307, 3916, 0, false);

				//favourite 5
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Information Center") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Grand Exchange") {
				c.getPA().spellTeleport(3319, 3690, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "West Dragons") {
				c.getPA().spellTeleport(2985, 3596, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Chaos Temple") {
				c.getPA().spellTeleport(3237, 3633, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Chinchompa Hills") {
				c.getPA().spellTeleport(3138, 3785, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Revenants Cave") {
				c.getPA().spellTeleport(3127, 3832, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Graves") {
				c.getPA().spellTeleport(3149, 3671, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Greator Demons") {
				c.getPA().spellTeleport(3288, 3866, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Level 44 obelisk") {
				c.getPA().spellTeleport(2980, 3866, 0, false);
			} else if (c.selectedFavourite == 5 && c.Favourites_5 == "Level 50 obelisk") {
				c.getPA().spellTeleport(3307, 3916, 0, false);

				//favourite 6
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Information Center") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Grand Exchange") {
				c.getPA().spellTeleport(3319, 3690, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "West Dragons") {
				c.getPA().spellTeleport(2985, 3596, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Chaos Temple") {
				c.getPA().spellTeleport(3237, 3633, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Chinchompa Hills") {
				c.getPA().spellTeleport(3138, 3785, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Revenants Cave") {
				c.getPA().spellTeleport(3127, 3832, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Graves") {
				c.getPA().spellTeleport(3149, 3671, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Greator Demons") {
				c.getPA().spellTeleport(3288, 3866, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Level 44 obelisk") {
				c.getPA().spellTeleport(2980, 3866, 0, false);
			} else if (c.selectedFavourite == 6 && c.Favourites_6 == "Level 50 obelisk") {
				c.getPA().spellTeleport(3307, 3916, 0, false);

				//favourite 6
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Information Center") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Grand Exchange") {
				c.getPA().spellTeleport(3319, 3690, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "West Dragons") {
				c.getPA().spellTeleport(2985, 3596, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Chaos Temple") {
				c.getPA().spellTeleport(3237, 3633, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Chinchompa Hills") {
				c.getPA().spellTeleport(3138, 3785, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Revenants Cave") {
				c.getPA().spellTeleport(3127, 3832, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Graves") {
				c.getPA().spellTeleport(3149, 3671, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Greator Demons") {
				c.getPA().spellTeleport(3288, 3866, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Level 44 obelisk") {
				c.getPA().spellTeleport(2980, 3866, 0, false);
			} else if (c.selectedFavourite == 7 && c.Favourites_7 == "Level 50 obelisk") {
				c.getPA().spellTeleport(3307, 3916, 0, false);

				//favourite 8
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Information Center") {
				c.getPA().spellTeleport(2541, 4715, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Grand Exchange") {
				c.getPA().spellTeleport(3319, 3690, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "West Dragons") {
				c.getPA().spellTeleport(2985, 3596, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Chaos Temple") {
				c.getPA().spellTeleport(3237, 3633, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Chinchompa Hills") {
				c.getPA().spellTeleport(3138, 3785, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Revenants Cave") {
				c.getPA().spellTeleport(3127, 3832, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Graves") {
				c.getPA().spellTeleport(3149, 3671, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Greator Demons") {
				c.getPA().spellTeleport(3288, 3866, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Level 44 obelisk") {
				c.getPA().spellTeleport(2980, 3866, 0, false);
			} else if (c.selectedFavourite == 8 && c.Favourites_8 == "Level 50 obelisk") {
				c.getPA().spellTeleport(3307, 3916, 0, false);


			}
		}



	public void handleTeleports(Player player, int Id) {


		if (player.inWild() && player.wildLevel > Config.NO_TELEPORT_WILD_LEVEL) {
			return;
		}
		switch (Id) {
		case 121033:
			c.selectedFavourite = 1;
			c.getDH().sendDialogues(2879, 5314);
			break;
		case 121034:
			c.selectedFavourite = 2;
			c.getDH().sendDialogues(2879, 5314);
			break;
		case 121035:
			c.selectedFavourite = 3;
			c.getDH().sendDialogues(2879, 5314);
			break;
		case 121036:
			c.selectedFavourite = 4;
			c.getDH().sendDialogues(2879, 5314);
			break;
		case 121037:
			c.selectedFavourite = 5;
			c.getDH().sendDialogues(2879, 5314);
			break;
		case 121038:
			c.selectedFavourite = 6;
			c.getDH().sendDialogues(2879, 5314);
			break;
		case 121039:
			c.selectedFavourite = 7;
			c.getDH().sendDialogues(2879, 5314);
			break;
		case 121040:
			c.selectedFavourite = 8;
			c.getDH().sendDialogues(2879, 5314);
			break;
		case 121026: //close button
			player.getPA().closeAllWindows();
			break;
		case 121079:
			c.favouritesSelected = 0;
			handleFavourites();
			break;
		case 121084:
			c.favouritesSelected = 1;
			handleFavourites();
			break;
		case 121089:
			c.favouritesSelected = 2;
			handleFavourites();
			break;
		case 121094:
			c.favouritesSelected = 3;
			handleFavourites();
			break;
		case 121099:
			c.favouritesSelected = 4;
			handleFavourites();
			break;
		case 121104:
			c.favouritesSelected = 5;
			handleFavourites();
			break;
		case 121109:
			c.favouritesSelected = 6;
			handleFavourites();
			break;
		case 121114:
			c.favouritesSelected = 7;
			handleFavourites();
			break;
		case 121119:
			c.favouritesSelected = 8;
			handleFavourites();
			break;
		case 121124:
			c.favouritesSelected = 9;
			handleFavourites();
			break;
		case 121129:
			c.favouritesSelected = 10;
			handleFavourites();
			break;
		case 121134:
			c.favouritesSelected = 11;
			handleFavourites();
			break;
		case 121139:
			c.favouritesSelected = 12;
			handleFavourites();
			break;
		case 121144:
			c.favouritesSelected = 13;
			handleFavourites();
			break;
		case 121149:
			c.favouritesSelected = 14;
			handleFavourites();
			break;
		case 121154:
			c.favouritesSelected = 15;
			handleFavourites();
			break;
		case 121159:
			c.favouritesSelected = 16;
			handleFavourites();
			break;
		case 121164:
			c.favouritesSelected = 17;
			handleFavourites();
			break;
		case 121169:
			c.favouritesSelected = 18;
			handleFavourites();
			break;
		case 121174:
			c.favouritesSelected = 19;
			handleFavourites();
			break;
		case 121041: //Recents 1
			if (player.recent1_TeleportX == 0 && player.recent1_TeleportY == 0 && player.recent1_TeleportZ == 0) {
				player.sendMessage("You need to teleport somewhere first.");
				return;
			}
			player.usingRecents = true;
			player.getPA().startTeleport(player.recent1_TeleportX, player.recent1_TeleportY, player.recent1_TeleportZ, "modern", false);
			break;
		case 121042: //recents 2
			if (player.recent2_TeleportX == 0 && player.recent2_TeleportY == 0 && player.recent2_TeleportZ == 0) {
				player.sendMessage("You need to teleport somewhere first.");
				return;
			}
			player.usingRecents = true;
			player.getPA().startTeleport(player.recent1_TeleportX, player.recent1_TeleportY, player.recent1_TeleportZ, "modern", false);
			break;
		case 121043: //recents 3
			if (player.recent3_TeleportX == 0 && player.recent3_TeleportY == 0 && player.recent3_TeleportZ == 0) {
				player.sendMessage("You need to teleport somewhere first.");
				return;
			}
			player.usingRecents = true;
			player.getPA().startTeleport(player.recent1_TeleportX, player.recent1_TeleportY, player.recent1_TeleportZ, "modern", false);
			break;

		case 121045: // Monsters Tab
			selection(player, 0);
			break;
		case 121046: // Dungeons Tab
			selection(player, 1);
			break;
		case 121047: // Bosses Tab
			selection(player, 2);
			break;
		case 121048: // Minigames Tab
			selection(player, 3);
			break;
		case 121049: // Skilling Tab
			selection(player, 4);
			break;
		case 121050: // Locations Tab
			selection(player, 5);
			break;
			//NOTE put the spell teleport underneeth teleport select because if not the code runs to fast and ignores the code until next time.

		case 121075: //LOCATION 1
			if (player.teleSelected == 0) {
						c.getPA().spellTeleport(2672, 3710, 0, false);//Rock Crabs
						player.teleportSelected = 0;
						player.getPA().startTeleport(player.recent1_TeleportX, player.recent1_TeleportY, player.recent1_TeleportZ, "modern", false);
	            } else if (player.teleSelected == 1) {
	            	c.getPA().spellTeleport(3425, 3538, 0, false);//Slayer Tower
	            		player.teleportSelected = 0;
	    				player.getPA().startTeleport(3428, 3538, 0, "modern", false);
				} else if (player.teleSelected == 2) {
						player.teleportSelected = 0;
						c.getPA().spellTeleport(1351,10259, 0, false);//Hydra
				} else if (player.teleSelected == 3) {
						player.teleportSelected = 0;
						c.getPA().spellTeleport(3160, 3491, 0, false);//Gambling zone
				} else if (player.teleSelected == 4) {
						player.teleportSelected = 0;
						player.getPA().startTeleport(3803, 3539, 0, "modern", false);
				} else if (player.teleSelected == 5) {
						player.teleportSelected = 0;
						c.getPA().spellTeleport(2541,4715, 0, false);//Mage Bank
				}
			break;
		case 121080: //LOCATION 2
			if (player.teleSelected == 0) {
					c.getPA().spellTeleport(1683, 3487, 0, false);//Sand Crabs
					c.teleportSelected = 1;
					player.getPA().startTeleport(player.recent1_TeleportX, player.recent1_TeleportY, player.recent1_TeleportZ, "modern", false);
				} else if (player.teleSelected == 1) {
				       c.getPA().spellTeleport(3097, 9869, 0, false);//Edge Dung
		               player.teleportSelected = 1;
				} else if (player.teleSelected == 2) {
		               player.teleportSelected = 1;
		               c.getPA().spellTeleport(2199, 3056, 0, false);//Zulrah
				} else if (player.teleSelected == 3) {
		               player.teleportSelected = 1;
		               c.getPA().spellTeleport(2657, 2649, 0, false);//Pest control
				} else if (player.teleSelected == 4) {
		               player.teleportSelected = 1;
		               c.getPA().spellTeleport(1656, 3505, 0, false);//Woodcutting guild
				} else if (player.teleSelected == 5) {
		               player.teleportSelected = 1;
		               c.getPA().spellTeleport(3319, 3690, 0, false);//East dragons (22)
				}
			break;
		case 121085: //LOCATION 3
			if (player.teleSelected == 0) {
				c.getPA().spellTeleport(2323, 3804, 0, false);//Yaks
	            player.teleportSelected = 2;
				player.getPA().startTeleport(player.recent1_TeleportX, player.recent1_TeleportY, player.recent1_TeleportZ, "modern", false);
	            } else if (player.teleSelected == 1) {
	            	  c.getPA().spellTeleport(2884, 9798, 0, false);//taverly dung
		               player.teleportSelected = 2;
				} else if (player.teleSelected == 2) {
		               player.teleportSelected = 2;
		               c.getPA().spellTeleport(2880, 5313, 2, false);
				} else if (player.teleSelected == 3) {
		               player.teleportSelected = 2;
		               c.getPA().spellTeleport(3033, 6067, 0, false);//Chambers of xeric
				} else if (player.teleSelected == 4) {
		               player.teleportSelected = 2;
		               c.getPA().spellTeleport(2611, 3400, 0, false);//Fishcutting guild
				} else if (player.teleSelected == 5) {
		               player.teleportSelected = 2;
		               c.getPA().spellTeleport(2985, 3596, 0, false);//Woodcutting guild
				}
			break;
		case 121090: //LOCATION 4
			if (player.teleSelected == 0) {
				c.getPA().spellTeleport(1748, 5330, 0, false);//Mithril Dragons
	               player.teleportSelected = 3;
	            } else if (player.teleSelected == 1) {
	            	c.getPA().spellTeleport(2688, 9564, 0, false);//Brimhaven Dung
		               player.teleportSelected = 3;
				} else if (player.teleSelected == 2) {
		               player.teleportSelected = 3;
		               c.getPA().spellTeleport(2272, 4048, 0, false);//Vorkath
				} else if (player.teleSelected == 3) { //gulag
				     	c.getPA().startTeleport(3080, 3504, 0, "modern", false);
		               player.teleportSelected = 3;
				} else if (player.teleSelected == 4) {
					   player.getPA().spellTeleport(3046, 9754, 0, false);//mining guild
		               player.teleportSelected = 3;
				} else if (player.teleSelected == 5) {
		               player.teleportSelected = 3;
		               c.getPA().spellTeleport(3237, 3633, 0, false);//Chaos temple (15)
				}
			break;
		case 121095: //LOCATION 5
			if (player.teleSelected == 0) {
				c.getPA().spellTeleport(3507, 9493, 0, false);//Kalphite
	               player.teleportSelected = 4;
	            } else if (player.teleSelected == 1) {
	            	c.getPA().spellTeleport(2806, 10002, 0, false);//Rellekka dung
		               player.teleportSelected = 4;
				} else if (player.teleSelected == 2) {
		               player.teleportSelected = 4;
		               c.getPA().spellTeleport(1477, 3690, 0, false);//Lizardman
				} else if (player.teleSelected == 3) {
					c.getPA().spellTeleport(2439, 5169, 0, false);//Fight caves
		               player.teleportSelected = 4;
				} else if (player.teleSelected == 4) {
					c.getPA().spellTeleport(2505, 3488, 0, false);//Barb fishing
		               player.teleportSelected = 4;
				} else if (player.teleSelected == 5) {
					c.getPA().spellTeleport(3138, 3785, 0, false);//Chincompia hills
		               player.teleportSelected = 4;
				}
			break;
		case 121100: //LOCATION 6
			if (player.teleSelected == 0) {
				c.getPA().spellTeleport(2130, 5646, 0, false);//Demonic Gorillas
	               player.teleportSelected = 5;
	            } else if (player.teleSelected == 1) {
	            	c.getPA().spellTeleport(2352, 3156, 0, false);//lletya elves
		               player.teleportSelected = 5;
				} else if (player.teleSelected == 2) {
					c.getPA().spellTeleport(2271, 4680, 0, false);//KBD
		               player.teleportSelected = 5;
				} else if (player.teleSelected == 3) {
					c.getPA().spellTeleport(2496, 5113, 0, false);//inferno
		               player.teleportSelected = 5;
				} else if (player.teleSelected == 4) {
					c.getPA().spellTeleport(2933,3285, 0, false);//crafting guild
		               player.teleportSelected = 5;
				} else if (player.teleSelected == 5) {
					c.getPA().spellTeleport(3127,3832, 0, false);//Rev caves
		               player.teleportSelected = 5;
				}
			break;
		case 121105: //LOCATION 7
			if (player.teleSelected == 0) {
					c.getPA().spellTeleport(3056, 9555, 0, false);//werns
	               player.teleportSelected = 6;
	            } else if (player.teleSelected == 1) {
	            	c.getPA().spellTeleport(1666, 10047, 0, false);//Catacombs
		               player.teleportSelected = 6;
				} else if (player.teleSelected == 2) {
					c.getPA().spellTeleport(3039, 4796, 0, false);//abyssal sire
		               player.teleportSelected = 6;
				} else if (player.teleSelected == 3) {
					c.getPA().spellTeleport(2869, 3545, 0, false);//warriors guild
		               player.teleportSelected = 6;
				} else if (player.teleSelected == 4) {
					c.getPA().spellTeleport(2468, 3435, 0, false);//gnome agility
		               player.teleportSelected = 6;
				} else if (player.teleSelected == 5) {
					c.getPA().spellTeleport(3149, 3671, 0, false);//Graves
		               player.teleportSelected = 6;
				}
			break;
		case 121110: //LOCATION 8
			if (player.teleSelected == 0) {
					c.getPA().spellTeleport(1309, 10232, 0, false);//mini hydra
	               player.teleportSelected = 7;
	            } else if (player.teleSelected == 1) {
	            	c.getPA().spellTeleport(1311, 10222, 0, false);//Krumm dung
		               player.teleportSelected = 7;
				} else if (player.teleSelected == 2) { //corps beast (add later)
	            	c.getPA().spellTeleport(2969, 4384, 2, false);//Krumm dung
		               player.teleportSelected = 7;
				} else if (player.teleSelected == 3) {
					c.getPA().spellTeleport(3565, 3307, 0, false);//Barrows
		               player.teleportSelected = 7;
				} else if (player.teleSelected == 4) {
					c.getPA().spellTeleport(2552, 3558, 0, false);//barb agility
		               player.teleportSelected = 7;
				} else if (player.teleSelected == 5) {
					c.getPA().spellTeleport(3288, 3886, 0, false);//greater demons
		               player.teleportSelected = 7;
				}
			break;
		case 121115: //LOCATION 9
			if (player.teleSelected == 0) {
				   player.getPA().startTeleport(3259, 3261, 0, "modern", false);
	               player.teleportSelected = 8;
	            } else if (player.teleSelected == 1) {
	            	c.getPA().spellTeleport(1567, 5074, 0, false);//LITHKREN VAULT
		               player.teleportSelected = 8;
				} else if (player.teleSelected == 2) {
					c.getPA().spellTeleport(3285, 3907, 0, false);//Chaos elemental
		               player.teleportSelected = 8;
				} else if (player.teleSelected == 3) {
					c.getPA().spellTeleport(2541,4715, 0, false);//Mage arena
		               player.teleportSelected = 8;
				} else if (player.teleSelected == 4) {
					c.getPA().spellTeleport(2998, 3913, 0, false);//wildy agility
		               player.teleportSelected = 8;
				} else if (player.teleSelected == 5) {
					c.getPA().spellTeleport(2980, 3866, 0, false);//obelisk 44wild
		               player.teleportSelected = 8;
				}
			break;
		case 121120: //LOCATION 10
			if (player.teleSelected == 0) {
				   player.getPA().startTeleport(3176, 2987, 0, "modern", false); //desert bandit
	               player.teleportSelected = 9;
            } else if (player.teleSelected == 1) {
            	c.getPA().spellTeleport(3035, 9581, 0, false);//ice dungeon
	               player.teleportSelected = 9;
			} else if (player.teleSelected == 2) {
				c.getPA().spellTeleport(3313, 3826, 0, false);//callisto
	               player.teleportSelected = 9;
			} else if (player.teleSelected == 3) {
				//Mage arena 2
	               player.teleportSelected = 9;
			} else if (player.teleSelected == 4) {
				   player.getPA().spellTeleport(3222, 3219, 0, false);//lumbridge
	               player.teleportSelected = 9;
			} else if (player.teleSelected == 5) {
				c.getPA().spellTeleport(3307, 3916, 0, false);//50s obelisk
	               player.teleportSelected = 9;
			}
			break;
		case 121125: //LOCATION 11
			if (player.teleSelected == 0) {
				   player.getPA().startTeleport(2897, 2725, 0, "modern", false); //elf warriors
	               player.teleportSelected = 10;
            } else if (player.teleSelected == 1) {
            	   c.getPA().spellTeleport(2381, 9463, 0, false);//smoke dungeon
	               player.teleportSelected = 10;
			} else if (player.teleSelected == 2) {
				c.getPA().spellTeleport(3343, 3741, 0, false);//venenatis
	               player.teleportSelected = 10;
			} else if (player.teleSelected == 3) {
				c.getPA().spellTeleport(3364, 3267, 0, false);//duel arena
	               player.teleportSelected = 10;
			} else if (player.teleSelected == 4) {
				   player.getPA().spellTeleport(3212, 3429, 0, false);//varrock
	               player.teleportSelected = 10;
			} else if (player.teleSelected == 5) {
				//PVP tab #11
	               player.teleportSelected = 10;
			}
			break;
		case 121130: //LOCATION 12
			if (player.teleSelected == 0) {
				   player.getPA().startTeleport(1913, 4367, 0, "modern", false); //dagganoths
	               player.teleportSelected = 11;
            } else if (player.teleSelected == 1) {
            	c.getPA().spellTeleport(2276, 9989, 0, false);//Kraken Caves
	               player.teleportSelected = 11;
			} else if (player.teleSelected == 2) {
				c.getPA().spellTeleport(3179, 3774, 0, false);//vet'ion
	               player.teleportSelected = 11;
			} else if (player.teleSelected == 3) {
				c.getPA().spellTeleport(2514, 3621, 0, false);//Lighthouse
	               player.teleportSelected = 11;
			} else if (player.teleSelected == 4) {
				   player.getPA().spellTeleport(2964, 3379, 0, false);//falador
	               player.teleportSelected = 11;
			} else if (player.teleSelected == 5) {
				//PVP #10
	               player.teleportSelected = 11;
			}
			break;
		case 121135: //LOCATION 13
			if (player.teleSelected == 0) {
				//Monster #10
	               player.teleportSelected = 12;
            } else if (player.teleSelected == 1) {
            	c.getPA().spellTeleport(2412, 9785, 0, false);//Nieves cave
	               player.teleportSelected = 12;
			} else if (player.teleSelected == 2) {
				c.getPA().spellTeleport(2983, 3675, 0, false);//crazy arch
	               player.teleportSelected = 12;
			} else if (player.teleSelected == 3) {
				//Minigame #13
	               player.teleportSelected = 12;
			} else if (player.teleSelected == 4) {
				   player.getPA().spellTeleport(2757,3477, 0, false);//camelot
	               player.teleportSelected = 12;
			} else if (player.teleSelected == 5) {
				//Pvp #13
	               player.teleportSelected = 12;
			}
			break;
		case 121140: //LOCATION 14
			if (player.teleSelected == 0) {
				//Monster #14
	               player.teleportSelected = 13;
            } else if (player.teleSelected == 1) {
				   c.getPA().spellTeleport(3274, 6054, 0, false);//crystal  cave
	               player.teleportSelected = 13;
			} else if (player.teleSelected == 2) {
				c.getPA().spellTeleport(1760, 5163, 0, false);//Mole
	               player.teleportSelected = 13;
			} else if (player.teleSelected == 3) {
				//Minigames #14
	               player.teleportSelected = 13;
			} else if (player.teleSelected == 4) {
				   player.getPA().spellTeleport(2661, 3310, 0, false);//ardougne
	               player.teleportSelected = 13;
			} else if (player.teleSelected == 5) {
				//Pvp #14
	               player.teleportSelected = 13;
			}
			break;
		case 121145: //LOCATION 15
			if (player.teleSelected == 0) {
				//Monster #15
	               player.teleportSelected = 14;
            } else if (player.teleSelected == 1) {
            	//Dungeon #15
	               player.teleportSelected = 14;
			} else if (player.teleSelected == 2) {
			       player.getPA().startTeleport(1226, 3498, 0, "modern", false);
	               player.teleportSelected = 14;
			} else if (player.teleSelected == 3) {
				//Minigames #15
	               player.teleportSelected = 14;
			} else if (player.teleSelected == 4) {
				   player.getPA().startTeleport(1580, 3437, 0, "modern", false);
	               player.teleportSelected = 14;
			} else if (player.teleSelected == 5) {
				//Pvp #15
	               player.teleportSelected = 14;
			}
			break;
		case 121150: //LOCATION 16
			if (player.teleSelected == 0) {
	               player.teleportSelected = 15;
            } else if (player.teleSelected == 1) {
            	//Dungeon #16
	               player.teleportSelected = 15;
			} else if (player.teleSelected == 2) {
				   player.getPA().startTeleport(1913, 4367, 0, "modern", false); //dagganoths kings
	               player.teleportSelected = 15;
			} else if (player.teleSelected == 3) {
				//Minigame #16
	               player.teleportSelected = 15;
			} else if (player.teleSelected == 4) {
				   player.getPA().startTeleport(2592, 4317, 0, "modern", false); //dagganoths kings
	               player.teleportSelected = 15;
			} else if (player.teleSelected == 5) {
				//Pvp #16
	               player.teleportSelected = 15;
			}
			break;
		case 121155: //LOCATION 17
			if (player.teleSelected == 0) {
	               player.teleportSelected = 16;
            } else if (player.teleSelected == 1) {
            	//Dungeon #17
	               player.teleportSelected = 16;
			} else if (player.teleSelected == 2) {
				   player.getPA().startTeleport(1309, 1253, 0, "modern", false); //cerberus
	               player.teleportSelected = 16;
			} else if (player.teleSelected == 3) {
				//Minigame #17
	               player.teleportSelected = 16;
			} else if (player.teleSelected == 4) {
				player.getPA().startTeleport(3003, 3376, 0, "modern", false); //farming
	               player.teleportSelected = 16;
			} else if (player.teleSelected == 5) {
				//Pvp #17
	               player.teleportSelected = 16;
			}
			break;
		case 121160: //LOCATION 18
			if (player.teleSelected == 0) {
				//Monster #18
	               player.teleportSelected = 17;
            } else if (player.teleSelected == 1) {
            	//Dungeon #18
	               player.teleportSelected = 17;
			} else if (player.teleSelected == 2) {
					if (player.playerLevel[18] < 93) { //thermo
						player.sendMessage("You need a Slayer level of 93 to kill these.");
						return;
					}
					player.getPA().startTeleport(2376, 9452, 0, "modern", false);
	               player.teleportSelected = 17;
			} else if (player.teleSelected == 3) {
				//Minigame #18
	               player.teleportSelected = 17;
			} else if (player.teleSelected == 4) {
				//skilling #18
	               player.teleportSelected = 17;
			} else if (player.teleSelected == 5) {
				//Pvp #18
	               player.teleportSelected = 17;
			}
			break;
		case 121165: //LOCATION 19
			if (player.teleSelected == 0) {
				//Monster #19
	               player.teleportSelected = 18;
            } else if (player.teleSelected == 1) {
            	//dungeon #19
	               player.teleportSelected = 18;
			} else if (player.teleSelected == 2) {
				//bossing #19
	               player.teleportSelected = 18;
			} else if (player.teleSelected == 3) {
				//Minigame #19
	               player.teleportSelected = 18;
			} else if (player.teleSelected == 4) {
				//Skilling #19
	               player.teleportSelected = 18;
			} else if (player.teleSelected == 5) {
				//Pvp #19
	               player.teleportSelected = 18;
			}
			break;
		case 121170: //LOCATION 20
			if (player.teleSelected == 0) {
				//Monster #20
	               player.teleportSelected = 19;
            } else if (player.teleSelected == 1) {
            	//Dungeon #20
	               player.teleportSelected = 19;
			} else if (player.teleSelected == 2) {
				//bosses #20
	               player.teleportSelected = 19;
			} else if (player.teleSelected == 3) {
				//minigame #20
	               player.teleportSelected = 19;
			} else if (player.teleSelected == 4) {
				//skilling #20
	               player.teleportSelected = 19;
			} else if (player.teleSelected == 5) {
				//pvp #20
				player.teleportSelected = 19;
			}
			break;

		}
	}
}