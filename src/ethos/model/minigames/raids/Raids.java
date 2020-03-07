package ethos.model.minigames.raids;

import ethos.Server;
import ethos.clip.doors.Location;
import ethos.model.items.ItemDefinition;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.net.discord.DiscordMessager;
import ethos.util.Misc;
import ethos.world.objects.GlobalObject;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author @ Goon_
 * www.rune-server.com
 */

public class Raids {
	
	public int[] damageDealt = new int[30];
	
	
	/**
	 * The player
	 */
	Player player;

	/**
	 * The raid leader
	 */
	public Player raidLeader;

	/**
	 * Group points
	 */
	public int groupPoints;

	/**
	 * Raid Group
	 */
	Player[] raidGroup;

	/**
	 * The current path
	 */
	private int path;

	/**
	 * The current way
	 */
	private int way;

	/**
	 * Current room
	 */
	public int currentRoom;


	/**
	 * Reached room
	 */
	public int reachedRoom;
	/**
	 * Monster spawns (No Double Spawning)
	 */
	public boolean lizards = false;
	public boolean vasa = false;
	public boolean vanguard = false;
	public boolean ice = false;
	public boolean chest = false;
	public boolean mystic = false;
	public boolean tekton = false;
	public boolean mutta = false;
	public boolean archers = false;
	public boolean olm = false;
	public boolean olmDead = false;
	public boolean rightHand = false;
	public boolean leftHand = false;

	/**
	 * The door location of the current paths
	 */
	public ArrayList<Location> roomPaths= new ArrayList<Location>();

	/**
	 * The names of the current rooms in path
	 */
	public  ArrayList<String> roomNames = new ArrayList<String>();

	/**
	 * Current monsters needed to kill
	 */
	public int mobAmount = 0;

	/**
	 * Constructs the raids class for the player
	 * @param player The player
	 */
	public Raids(Player player) {
		this.player=player;
	}

	/**
	 * Gets the height for the raid
	 * @return the height
	 */
	public int getHeight(Player player) {
		return raidLeader.getIndex()*4;
	}

	/**
	 * Gets the current path
	 * @return the path
	 */
	public int getPath() {
		return path;
	}

	/**
	 * Gets the current way
	 * @return the way
	 */
	public int getWay() {
		return way;
	}

	/**
	 * Sets the current path
	 * @param path
	 */
	public void setPath(int path) {
		this.path=path;
	}

	/**
	 * Gets the start location for the path
	 * @return path
	 */
	public Location getStartLocation() {
		switch(path) {
			case 0:
				return RaidRooms.STARTING_ROOM_2.doorLocation;
		}
		return RaidRooms.STARTING_ROOM.doorLocation;
	}
	/**
	 * Handles raid rooms
	 * @author Goon
	 *
	 */
	public enum RaidRooms{
		STARTING_ROOM("start_room",1,0,new Location(3299,5189)),
		LIZARDMEN_SHAMANS("lizardmen",1,0,new Location(3308,5208)),
		SKELETAL_MYSTIC("skeletal",1,0,new Location(3312,5217,1)),
		VASA_NISTIRIO("vasa",1,0,new Location(3312,5279)),
		VANGUARDS("vanguard",1,0,new Location(3312,5311)),
		ICE_DEMON("ice",1,0,new Location(3313,5346)),
		CHEST_ROOM("chest",1,0,new Location(3311,5374)),
		MUTTADILE("muttadile",1,0,new Location(3311,5309,1)),
		TEKTON("tekton",1,0,new Location(3310,5277,1)),
		ENERGY_ROOM("energy",1,0,new Location(3275,5159)),
		OLM_ROOM_WAIT("olm_wait",1,0,new Location(3232,5721)),
		OLM_ROOM("olm",1,0,new Location(3232,5730)),

		STARTING_ROOM_2("start_room",1,1,new Location(3299,5189)),
		MUTTADILE_2("muttadile",1,1,new Location(3311,5309,1)),
		VASA_NISTIRIO_2("vasa",1,1,new Location(3312,5279)),
		VANGUARDS_2("vanguard",1,1,new Location(3312,5311)),
		ICE_DEMON_2("ice",1,1,new Location(3313,5346)),
		CHEST_ROOM_2("chest",1,1,new Location(3311,5374)),
		SKELETAL_MYSTIC_2("skeletal",1,1,new Location(3312,5217,1)),
		TEKTON_2("tekton",1,1,new Location(3310,5277,1)),
		LIZARDMEN_SHAMANS_2("lizardmen",1,1,new Location(3308,5208)),
		ENERGY_ROOM_2("energy",1,1,new Location(3275,5159)),
		OLM_ROOM_WAIT_2("olm_wait",1,1,new Location(3232,5721)),
		OLM_ROOM_2("olm",1,1,new Location(3232,5730));

		private Location doorLocation;
		private int path;
		private int way;
		private String roomName;

		private RaidRooms(String name,int path1,int way1,Location door) {
			doorLocation=door;
			roomName=name;
			path=path1;
			way=way1;

		}

		public Location getDoor() {
			return doorLocation;
		}

		public int getPath() {
			return path;
		}
		public int getWay() {
			return way;
		}
		public String getRoomName() {
			return roomName;
		}


	}
	
	/**
	 * Kill all spawns for the raid leader if left
	 * @param player
	 */
	public void killAllSpawns(Player player) {
		if(raidLeader == null) { 
			return;
		}
		NPCHandler.kill(MUTTADILE, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(MUTTADILE, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(VASA, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(ICE_DEMON, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(TEKTON, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(7573, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(7573, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(7573, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(SKELETAL_MYSTIC1, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(SKELETAL_MYSTIC2, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(SKELETAL_MYSTIC3, player.getRaids().getHeight(player.getRaids().raidLeader));
		NPCHandler.kill(VANGUARD1, player.getRaids().getHeight(player.getRaids().raidLeader)); // melee vanguard
		NPCHandler.kill(VANGUARD2, player.getRaids().getHeight(player.getRaids().raidLeader)); // range vanguard
		NPCHandler.kill(VANGUARD3, player.getRaids().getHeight(player.getRaids().raidLeader)); // magic vanguard
		NPCHandler.kill(OLM_RIGHT_HAND, player.getRaids().getHeight(player.getRaids().raidLeader)); // magic vanguard
		NPCHandler.kill(OLM, player.getRaids().getHeight(player.getRaids().raidLeader)); // magic vanguard
		NPCHandler.kill(OLM_LEFT_HAND, player.getRaids().getHeight(player.getRaids().raidLeader)); // magic vanguard
	}

	/**
	 * Leaves the raid.
	 * @param player
	 */
	public void leaveGame(Player player) {
		player.sendMessage("@red@You have left the Chambers of Xeric.");
		player.getPA().movePlayer(1233, 3568, 0);
		killAllSpawns(player); 
        player.getRaids().roomNames = new ArrayList<String>();
        player.getRaids().roomPaths= new ArrayList<Location>();
        player.getRaids().currentRoom = 0;
        player.getRaids().mobAmount=0;
        player.getRaids().reachedRoom = 0;
        player.getRaids().raidLeader=null;
        player.getRaids().lizards = false;
        player.getRaids().vasa = false;
        player.getRaids().vanguard = false;
        player.getRaids().ice = false;
        player.getRaids().chest = false;
        player.getRaids().mystic = false;
        player.getRaids().tekton = false;
        player.getRaids().mutta = false;
        player.getRaids().archers = false;
        player.getRaids().olm = false;
        player.getRaids().olmDead = false;
        player.getRaids().rightHand = false;
        player.getRaids().leftHand = false;
	}
	
	public void reset() {
		this.damageDealt = new int[30];
	}
	
	public int getDamage(RaidMonsters monster) {
		return damageDealt[monster.ordinal()];
	}
	
	public int getTotalDamage() {
		return Arrays.stream(damageDealt).sum();
	}
	
	public void setDamage(RaidMonsters monster, int amount) {
		this.damageDealt[monster.ordinal()] = amount;
	}
	
	public void increaseDamage(RaidMonsters monster, int amount) {
		this.damageDealt[monster.ordinal()] += amount;
	}
	
	public void resetDamage() {
		this.damageDealt = new int[10];
	}
	
	/**
	 * Starts the raid.
	 */
	public void startRaid() {

		int memberCount = player.clan.activeMembers.size();

		if (memberCount < 1) {
			player.sendMessage("You don't have enough people in your clan to start a raid.");
			return;
		}

		if (memberCount > 22) {
			player.sendMessage("Your clan exceeds the max limit of 22 players in Raids.");
			return;
		}

		raidLeader=player;
		int path1 = 1;
		int way1=Misc.random(1);
		path = path1;
		way=way1;
		for (String username : player.clan.activeMembers) {
			Player p = PlayerHandler.getPlayer(username);
			if (p == null || !p.inRaidsMountain()) {
				continue;
			}
			if(p.combatLevel < 75 && p.totalLevel < 750){
				p.sendMessage("You need at least 75 combat and 750 total level to attend raids.");
				continue;
			}
			p.getRaids().raidLeader = player;
			p.getRaids().path = path1;
			p.getRaids().way= way1;
			for(RaidRooms room : RaidRooms.values()) {
				if(room.getWay() == way) {
					p.getRaids().roomNames.add(room.getRoomName());
					p.getRaids().roomPaths.add(room.getDoor());
				}
			}
			p.getPA().movePlayer(getStartLocation().getX(),getStartLocation().getY(),getHeight(player));
			p.sendMessage("@red@Welcome to the Chambers of Xeric!");
			p.hasOpenedChest = false;
			p.getPA().itemOnInterface(-1, -1, 64503, 0);
			p.getPA().itemOnInterface(-1, -1, 64503, 1);
			p.getPA().itemOnInterface(-1, -1, 64503, 2);
			p.getPA().itemOnInterface(-1, -1, 64503, 3);
		}
	}

	int[] rarerewards = {2697, 21079, 21034, 2698, 13346, 20784, 21000, 21006, 21009, 21012, 21015, 21018, 21021, 21024, 21028, 20784, 20997, 22324, 22325, 22326, 22327, 22328, 22323, 22322};
	int[] commonrewards1 = {19841, 11937, 13442, 11935, 13440, 1616, 824, 11232, 1516, 1514, 2362, 3025, 2435, 19484, 2364, 20849, 21326, 11212, 452, 2722, 11230}; //{item, maxAmount}
	int[] commonrewards2 = {19841, 11937, 13442, 11935, 13440, 1616, 824, 11232, 1516, 1514, 2362, 3025, 2435, 19484, 2364, 20849, 21326, 11212, 452, 2722, 11230};
	int[] commonrewards3 = {19841, 11937, 13442, 11935, 13440, 1616, 824, 11232, 1516, 1514, 2362, 3025, 2435, 19484, 2364, 20849, 21326, 11212, 452, 2722, 11230};
	
	/**
	 * Handles giving the raid reward
	 */
	static int rewardChance = Misc.random(100);
	
	
	public void giveReward() {
		if(rewardChance >= 99) {
			giveRareReward();
			giveCommonReward1();
			giveCommonReward2();
			giveCommonReward3();
			Server.getGlobalObjects().add(new GlobalObject(30030, 3232, 5751, player.getHeight(), 4, 10, 120, -1));
		}else {
			Server.getGlobalObjects().add(new GlobalObject(30029, 3232, 5751, player.getHeight(), 4, 10, 120, -1));
			giveCommonReward1();
			giveCommonReward2();
			giveCommonReward3();
		}
	}
	public void giveCommonReward1() {
		int commonitem1 = 0;
		commonitem1 = Misc.random(commonrewards1.length-1);
		player.raidCommonReward1[0][0] = commonrewards1[commonitem1];

		switch(player.raidCommonReward1[0][0]) {
		case 21326://amethyst arrow
			player.raidCommonReward1[0][1] = Misc.random(750) + 150;
			break;
		case 11212://dragon arrow
			player.raidCommonReward1[0][1] = Misc.random(650) + 150;
			break;
		case 452://runite ore
			player.raidCommonReward1[0][1] = Misc.random(50) + 15;
			break;
		case 2364://runite bar
			player.raidCommonReward1[0][1] = Misc.random(50) + 15;
			break;
		case 2722://hard clue
			player.raidCommonReward1[0][1] = Misc.random(2) + 1;
			break;
		case 20849://dragon throwing axe
			player.raidCommonReward1[0][1] = Misc.random(100) + 50;
			break;
		case 19484://dragon jav
			player.raidCommonReward1[0][1] = Misc.random(1250) + 50;
			break;
		case 11230://dragon dart
			player.raidCommonReward1[0][1] = Misc.random(250) + 50;
			break;
		case 2435://prayer pot
			player.raidCommonReward1[0][1] = Misc.random(15) + 5;
			break;
		case 3025://restore pot
			player.raidCommonReward1[0][1] = Misc.random(10) + 5;
			break;
		case 2362://adamant bar
			player.raidCommonReward1[0][1] = Misc.random(75) + 20;
			break;
		case 1514://magic log
			player.raidCommonReward1[0][1] = Misc.random(20) + 15;
			break;
		case 1516://yew log
			player.raidCommonReward1[0][1] = Misc.random(25) + 20;
			break;
		case 11232://dragon dart tip
			player.raidCommonReward1[0][1] = Misc.random(20) + 15;
			break;
		case 824://rune dart tip
			player.raidCommonReward1[0][1] = Misc.random(30) + 20;
			break;
		case 1616://dragonstone
			player.raidCommonReward1[0][1] = Misc.random(35) + 25;
			break;
		case 13440://raw anglerfish
			player.raidCommonReward1[0][1] = Misc.random(30) + 25;
			break;
		case 11935://raw dark crab
			player.raidCommonReward1[0][1] = Misc.random(35) + 30;
			break;
		case 13442://anglerfish
			player.raidCommonReward1[0][1] = Misc.random(30) + 25;
			break;
		case 11937://dark crab
			player.raidCommonReward1[0][1] = Misc.random(35) + 30;
			break;
			default:
				player.raidCommonReward1[0][1] = 1;
				break;

		}
	}

	public void giveCommonReward2() {
		int commonitem2 = 0;
		commonitem2 = Misc.random(commonrewards2.length-1);
		player.raidCommonReward2[0][0] = commonrewards2[commonitem2];

		switch(player.raidCommonReward2[0][0]) {
		case 21326://amethyst arrow
			player.raidCommonReward2[0][1] = Misc.random(750) + 150;
			break;
		case 11212://dragon arrow
			player.raidCommonReward2[0][1] = Misc.random(650) + 150;
			break;
		case 452://runite ore
			player.raidCommonReward2[0][1] = Misc.random(50) + 15;
			break;
		case 2364://runite bar
			player.raidCommonReward2[0][1] = Misc.random(50) + 15;
			break;
		case 2722://hard clue
			player.raidCommonReward2[0][1] = Misc.random(2) + 1;
			break;
		case 20849://dragon throwing axe
			player.raidCommonReward2[0][1] = Misc.random(100) + 50;
			break;
		case 19484://dragon jav
			player.raidCommonReward2[0][1] = Misc.random(1250) + 50;
			break;
		case 11230://dragon dart
			player.raidCommonReward2[0][1] = Misc.random(250) + 50;
			break;
		case 2435://prayer pot
			player.raidCommonReward2[0][1] = Misc.random(15) + 5;
			break;
		case 3025://restore pot
			player.raidCommonReward2[0][1] = Misc.random(10) + 5;
			break;
		case 2362://adamant bar
			player.raidCommonReward2[0][1] = Misc.random(75) + 20;
			break;
		case 1514://magic log
			player.raidCommonReward2[0][1] = Misc.random(20) + 15;
			break;
		case 1516://yew log
			player.raidCommonReward2[0][1] = Misc.random(25) + 20;
			break;
		case 11232://dragon dart tip
			player.raidCommonReward2[0][1] = Misc.random(20) + 15;
			break;
		case 824://rune dart tip
			player.raidCommonReward2[0][1] = Misc.random(30) + 20;
			break;
		case 1616://dragonstone
			player.raidCommonReward2[0][1] = Misc.random(35) + 25;
			break;
		case 13440://raw anglerfish
			player.raidCommonReward2[0][1] = Misc.random(30) + 25;
			break;
		case 11935://raw dark crab
			player.raidCommonReward2[0][1] = Misc.random(35) + 30;
			break;
		case 13442://anglerfish
			player.raidCommonReward2[0][1] = Misc.random(30) + 25;
			break;
		case 11937://dark crab
			player.raidCommonReward2[0][1] = Misc.random(35) + 30;
			break;
			default:
				player.raidCommonReward2[0][1] = 1;
				break;

		}
	}

	public void giveCommonReward3() {
		int commonitem3 = 0;
		commonitem3 = Misc.random(commonrewards3.length-1);
		player.raidCommonReward3[0][0] = commonrewards3[commonitem3];

		switch(player.raidCommonReward3[0][0]) {
		case 21326://amethyst arrow
			player.raidCommonReward3[0][1] = Misc.random(750) + 150;
			break;
		case 11212://dragon arrow
			player.raidCommonReward3[0][1] = Misc.random(650) + 150;
			break;
		case 452://runite ore
			player.raidCommonReward3[0][1] = Misc.random(50) + 15;
			break;
		case 2364://runite bar
			player.raidCommonReward3[0][1] = Misc.random(50) + 15;
			break;
		case 2722://hard clue
			player.raidCommonReward3[0][1] = Misc.random(2) + 1;
			break;
		case 20849://dragon throwing axe
			player.raidCommonReward3[0][1] = Misc.random(100) + 50;
			break;
		case 19484://dragon jav
			player.raidCommonReward3[0][1] = Misc.random(1250) + 50;
			break;
		case 11230://dragon dart
			player.raidCommonReward3[0][1] = Misc.random(250) + 50;
			break;
		case 2435://prayer pot
			player.raidCommonReward3[0][1] = Misc.random(15) + 5;
			break;
		case 3025://restore pot
			player.raidCommonReward3[0][1] = Misc.random(10) + 5;
			break;
		case 2362://adamant bar
			player.raidCommonReward3[0][1] = Misc.random(75) + 20;
			break;
		case 1514://magic log
			player.raidCommonReward3[0][1] = Misc.random(20) + 15;
			break;
		case 1516://yew log
			player.raidCommonReward3[0][1] = Misc.random(25) + 20;
			break;
		case 11232://dragon dart tip
			player.raidCommonReward3[0][1] = Misc.random(20) + 15;
			break;
		case 824://rune dart tip
			player.raidCommonReward3[0][1] = Misc.random(30) + 20;
			break;
		case 1616://dragonstone
			player.raidCommonReward3[0][1] = Misc.random(35) + 25;
			break;
		case 13440://raw anglerfish
			player.raidCommonReward3[0][1] = Misc.random(30) + 25;
			break;
		case 11935://raw dark crab
			player.raidCommonReward3[0][1] = Misc.random(35) + 30;
			break;
		case 13442://anglerfish
			player.raidCommonReward3[0][1] = Misc.random(30) + 25;
			break;
		case 11937://dark crab
			player.raidCommonReward3[0][1] = Misc.random(35) + 30;
			break;
			default:
				player.raidCommonReward3[0][1] = 1;
				break;

		}
	}

	/**
	 * Handles giving a rare reward.
	 */

	public void giveRareReward() {
		//p.gfx0(1368);
		int rareitem = 0;
		rareitem = Misc.random(rarerewards.length-1);
		if(rareitem < 0) {
			rareitem = Misc.random(rarerewards.length);
		}
		player.raidRareReward[0][0] = rarerewards[rareitem];
		PlayerHandler.executeGlobalMessage(
				"[@red@Lootations@bla@]@blu@ @cr9@ <col=255>" + player.playerName + "@bla@ received @blu@" + ItemDefinition.forId(player.raidRareReward[0][0]).getName() + " from raids!");
		DiscordMessager.sendLootations("[Lootations] " + player.playerName + " received " + ItemDefinition.forId(player.raidRareReward[0][0]).getName() + " from raids!");
		if(player.raidRareReward[0][0] == 20849) {
			player.raidRareReward[0][1] = 500;
		}else {
			player.raidRareReward[0][1] = 1;
		}

	}

	final int OLM = 7554;
	final int OLM_RIGHT_HAND= 7553;
	final int OLM_LEFT_HAND = 7555;
	final int TEKTON = 7544;
	final int SKELETAL_MYSTIC1 = 7604;
	final int SKELETAL_MYSTIC2 = 7605;
	final int SKELETAL_MYSTIC3 = 7606;
	final int MUTTADILE = 7563;
	final int VANGUARD1 = 7527;
	final int VANGUARD2 = 7528;
	final int VANGUARD3 = 7529;
	final int ICE_DEMON = 7585;
	final int VASA = 7566;
	
	public void handleMobDeath(int npcType) {
		player.getRaids().mobAmount-=1;
		switch(npcType) {
		
		case SKELETAL_MYSTIC1:
			for (String username : player.getRaids().raidLeader.clan.activeMembers) {
			Player p = PlayerHandler.getPlayer(username);
			int reward1 = p.getSkeletalMysticDamageCounter1();
			p.sendMessage("@dre@You dealt " + p.getSkeletalMysticDamageCounter1() + " damage towards skeletal mystic; granting " + reward1 + " raid points.");
			p.setRaidPoints(p.getRaidPoints() + p.getSkeletalMysticDamageCounter1());
			}
			return;
		
			case SKELETAL_MYSTIC2:
				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
					Player p = PlayerHandler.getPlayer(username);
					int reward2 = p.getSkeletalMysticDamageCounter2();
					p.sendMessage("@dre@You dealt " + p.getSkeletalMysticDamageCounter2() + " damage towards skeletal mystic; granting " + reward2 + " raid points.");
					p.setRaidPoints(p.getRaidPoints() + p.getSkeletalMysticDamageCounter2());
					}
					return;
			
			case SKELETAL_MYSTIC3:
				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
					Player p = PlayerHandler.getPlayer(username);
					int reward3 = p.getSkeletalMysticDamageCounter3();
					p.sendMessage("@dre@You dealt " + p.getSkeletalMysticDamageCounter3() + " damage towards skeletal mystic; granting " + reward3 + " raid points.");
					p.setRaidPoints(p.getRaidPoints() + p.getSkeletalMysticDamageCounter3());
					}
					return;
				
			case MUTTADILE:
				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
				Player p = PlayerHandler.getPlayer(username);
				int reward4 = p.getMuttadileDamageCounter();
				p.sendMessage("@dre@You dealt " + p.getMuttadileDamageCounter() + " damage towards muttadile; granting " + reward4 + " raid points.");
				p.setRaidPoints(p.getRaidPoints() + p.getMuttadileDamageCounter());
				}
				return;
			
				
			case VANGUARD1:
				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
				Player p = PlayerHandler.getPlayer(username);
				int reward5 = p.getVanguardDamageCounter1();
				p.sendMessage("@dre@You dealt " + p.getVanguardDamageCounter1() + " damage towards vanguard; granting " + reward5 + " raid points.");
				p.setRaidPoints(p.getRaidPoints() + p.getVanguardDamageCounter1());
				}
				return;
				
			case VANGUARD2:
				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
				Player p = PlayerHandler.getPlayer(username);
				int reward6 = p.getVanguardDamageCounter2();
				p.sendMessage("@dre@You dealt " + p.getVanguardDamageCounter2() + " damage towards vanguard; granting " + reward6 + " raid points.");
				p.setRaidPoints(p.getRaidPoints() + p.getVanguardDamageCounter2());
				}
				return;
				
			case VANGUARD3:
				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
				Player p = PlayerHandler.getPlayer(username);
				int reward7 = p.getVanguardDamageCounter3();
				p.sendMessage("@dre@You dealt " + p.getVanguardDamageCounter3() + " damage towards vanguard; granting " + reward7 + " raid points.");
				p.setRaidPoints(p.getRaidPoints() + p.getVanguardDamageCounter3());
				}
				return;
				
			case VASA:
				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
				Player p = PlayerHandler.getPlayer(username);
				int reward8 = p.getVasaDamageCounter();
				p.sendMessage("@dre@You dealt " + p.getVasaDamageCounter() + " damage towards vasa nistirio; granting " + reward8 + " raid points.");
				p.setRaidPoints(p.getRaidPoints() + p.getVasaDamageCounter());
				}
				return;
		
			case TEKTON:
				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
				Player p = PlayerHandler.getPlayer(username);
				int reward9 = p.getTektonDamageCounter();
				p.sendMessage("@dre@You dealt " + p.getTektonDamageCounter() + " damage towards tekton; granting " + reward9 + " raid points.");
				p.setRaidPoints(p.getRaidPoints() + p.getTektonDamageCounter());
				}
				return;
				
			case ICE_DEMON:
				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
				Player p = PlayerHandler.getPlayer(username);
				int reward10 = p.getIceDemonDamageCounter();
				p.sendMessage("@dre@You dealt " + p.getIceDemonDamageCounter() + " damage towards ice demon; granting " + reward10 + " raid points.");
				p.setRaidPoints(p.getRaidPoints() + p.getIceDemonDamageCounter());
				}
				return;
				
			case OLM:
				Server.getGlobalObjects().add(new GlobalObject(30028, 3233, 5751, player.getHeight(), 4, 10, 120, 30027));

				for (String username : player.getRaids().raidLeader.clan.activeMembers) {
					Player p = PlayerHandler.getPlayer(username);
					if (!p.inRaids()) {
						continue;
					}
					killAllSpawns(player); 
					p.raidCount+=1;
					p.sendMessage("@red@Congratulations you have defeated The Great Olm and completed the raid!");
					p.sendMessage("@red@You have completed "+p.raidCount+" raids." );
					int points = p.getRaidPoints();
					p.getItems().addItem(11179, points);
					p.setRaidPoints(0);
					this.reset();
					p.setTektonDamageCounter(0);
					p.setVanguardDamageCounter1(0);
					p.setVanguardDamageCounter2(0);
					p.setVanguardDamageCounter3(0);
					p.setIceDemonDamageCounter(0);
					p.setSkeletalMysticDamageCounter1(0);
					p.setSkeletalMysticDamageCounter2(0);
					p.setSkeletalMysticDamageCounter3(0);
					p.setVasaDamageCounter(0);
					p.setMuttadileDamageCounter(0);
					p.easyDifficulty = false;
					p.normalDifficulty = false;
					p.hardDifficulty = false;
				}

				return;
			case OLM_RIGHT_HAND:
				player.getRaids().raidLeader.getRaids().rightHand = true;
				if(player.getRaids().raidLeader.getRaids().leftHand == true) {
					player.sendMessage("@red@ You have defeated both of The Great Olm's hands he is now vulnerable.");
				}else {
					player.sendMessage("@red@ You have defeated one of The Great Olm's hands destroy the other one quickly!");
				}
				return;
			case OLM_LEFT_HAND:
				player.getRaids().raidLeader.getRaids().leftHand = true;
				if(player.getRaids().raidLeader.getRaids().rightHand == true) {
					player.sendMessage("@red@ You have defeated both of The Great Olm's hands he is now vulnerable.");
				}else {
					player.sendMessage("@red@ You have defeated one of The Great Olm's hands destroy the other one quickly!");
				}
				return;
		}
		if (player.getRaids().raidLeader.getRaids().mobAmount < 0) {
			player.getRaids().raidLeader.getRaids().mobAmount = 0;
		}

		if(player.getRaids().raidLeader.getRaids().mobAmount <= 0) {
			player.sendMessage("@red@The room has been cleared and you are free to pass.");
		}else {
			player.sendMessage("@red@There are "+player.getRaids().raidLeader.getRaids().mobAmount+" enemies remaining.");
		}
	}
	/**
	 * Spawns npc for the current room
	 * @param currentRoom The room
	 */
	public void spawnNpcs(int currentRoom) {

		int height = player.getRaids().getHeight(player);

		switch(player.getRaids().roomNames.get(currentRoom)) {
			case "lizardmen":
				if(lizards) {
					return;
				}
				if (player.easyDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7573, 3274, 5262, height, 1, 350, 25, 300, 300,true);
					NPCHandler.spawn(7573, 3282, 5266, height, 1, 350, 25, 300, 300,true);
					NPCHandler.spawn(7573, 3275, 5269, height, 1, 350, 25, 300, 300,true);
				}else {
					NPCHandler.spawn(7573, 3307, 5265, height, 1, 350, 25, 300, 300,true);
					NPCHandler.spawn(7573, 3314, 5265, height, 1, 350, 25, 300, 300,true);
					NPCHandler.spawn(7573, 3314, 5261, height, 1, 350, 25, 300, 300,true);
				}
				lizards = true;
				mobAmount+=3;
				}
				
				if (player.normalDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7573, 3274, 5262, height, 1, 650, 35, 400, 400,true);
					NPCHandler.spawn(7573, 3282, 5266, height, 1, 650, 35, 400, 400,true);
					NPCHandler.spawn(7573, 3275, 5269, height, 1, 650, 35, 400, 400,true);
				}else {
					NPCHandler.spawn(7573, 3307, 5265, height, 1, 650, 35, 400, 400,true);
					NPCHandler.spawn(7573, 3314, 5265, height, 1, 650, 35, 400, 400,true);
					NPCHandler.spawn(7573, 3314, 5261, height, 1, 650, 35, 400, 400,true);
				}
				lizards = true;
				mobAmount+=3;
				}
				
				if (player.hardDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7573, 3274, 5262, height, 1, 950, 45, 500, 500,true);
					NPCHandler.spawn(7573, 3282, 5266, height, 1, 950, 45, 500, 500,true);
					NPCHandler.spawn(7573, 3275, 5269, height, 1, 950, 45, 500, 500,true);
				}else {
					NPCHandler.spawn(7573, 3307, 5265, height, 1, 950, 45, 500, 500,true);
					NPCHandler.spawn(7573, 3314, 5265, height, 1, 950, 45, 500, 500,true);
					NPCHandler.spawn(7573, 3314, 5261, height, 1, 950, 45, 500, 500,true);
				}
				lizards = true;
				mobAmount+=3;
				}
				break;
			case "vasa":
				if(vasa) {
					return;
				}
				if (player.easyDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7566, 3280, 5295, height, -1, 650, 25, 250, 300,true);
				}else {
					NPCHandler.spawn(7566, 3311, 5295, height, -1, 650, 25, 250, 300,true);
				}
				vasa = true;
				mobAmount+=1;
				}
				
				if (player.normalDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7566, 3280, 5295, height, -1, 950, 35, 350, 400,true);
				}else {
					NPCHandler.spawn(7566, 3311, 5295, height, -1, 950, 35, 350, 400,true);
				}
				vasa = true;
				mobAmount+=1;
				}
				
				if (player.hardDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7566, 3280, 5295, height, -1, 1250, 45, 450, 500,true);
				}else {
					NPCHandler.spawn(7566, 3311, 5295, height, -1, 1250, 45, 450, 500,true);
				}
				vasa = true;
				mobAmount+=1;
				}
				break;
			case "vanguard":
				if(vanguard) {
					return;
				}
				if (player.easyDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7527, 3277,5326, height, -1, 300, 25, 140, 200,true); // melee vanguard
					NPCHandler.spawn(7528, 3277,5332, height, -1, 300, 25, 140, 200,true); // range vanguard
					NPCHandler.spawn(7529, 3285,5329, height, -1, 300, 25, 140, 200,true); // magic vanguard
				}else {
					NPCHandler.spawn(7527, 3310,5324, height, -1, 300, 25, 140, 200,true); // melee vanguard
					NPCHandler.spawn(7528, 3310,5331, height, -1, 300, 25, 140, 200,true); // range vanguard
					NPCHandler.spawn(7529, 3316,5331, height, -1, 300, 25, 140, 200,true);// magic vanguard
				}
				vanguard = true;
				mobAmount+=3;
				}
				
				if (player.normalDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7527, 3277,5326, height, -1, 600, 35, 240, 300,true); // melee vanguard
					NPCHandler.spawn(7528, 3277,5332, height, -1, 600, 35, 240, 300,true); // range vanguard
					NPCHandler.spawn(7529, 3285,5329, height, -1, 600, 35, 240, 300,true); // magic vanguard
				}else {
					NPCHandler.spawn(7527, 3310,5324, height, -1, 600, 35, 240, 300,true); // melee vanguard
					NPCHandler.spawn(7528, 3310,5331, height, -1, 600, 35, 240, 300,true); // range vanguard
					NPCHandler.spawn(7529, 3316,5331, height, -1, 600, 35, 240, 300,true);// magic vanguard
				}
				vanguard = true;
				mobAmount+=3;
				}
				
				if (player.hardDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7527, 3277,5326, height, -1, 900, 45, 340, 400,true); // melee vanguard
					NPCHandler.spawn(7528, 3277,5332, height, -1, 900, 45, 340, 400,true); // range vanguard
					NPCHandler.spawn(7529, 3285,5329, height, -1, 900, 45, 340, 400,true); // magic vanguard
				}else {
					NPCHandler.spawn(7527, 3310,5324, height, -1, 900, 45, 340, 400,true); // melee vanguard
					NPCHandler.spawn(7528, 3310,5331, height, -1, 900, 45, 340, 400,true); // range vanguard
					NPCHandler.spawn(7529, 3316,5331, height, -1, 900, 45, 340, 400,true);// magic vanguard
				}
				vanguard = true;
				mobAmount+=3;
				}
				break;
			case "ice":
				if(ice) {
					return;
				}
				if (player.easyDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7585, 3273,5365, height, -1, 750, 45, 350, 300,true);
				}else {
					NPCHandler.spawn(7585, 3310,5367, height, -1, 750, 45, 350, 300,true);
				}
				ice = true;
				mobAmount+=1;
				}
				
				if (player.normalDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7585, 3273,5365, height, -1, 1050, 55, 450, 400,true);
				}else {
					NPCHandler.spawn(7585, 3310,5367, height, -1, 1050, 55, 450, 400,true);
				}
				ice = true;
				mobAmount+=1;
				}
				
				if (player.hardDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7585, 3273,5365, height, -1, 1350, 65, 550, 500,true);
				}else {
					NPCHandler.spawn(7585, 3310,5367, height, -1, 1350, 65, 550, 500,true);
				}
				ice = true;
				mobAmount+=1;
				}
				break;
			case "skeletal":
				if(mystic) {
					return;
				}
				if (player.easyDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7604, 3279,5271, height+1, -1, 250, 25, 400, 250,true);
					NPCHandler.spawn(7605, 3290,5268, height+1, -1, 250, 25, 500, 250,true);
					NPCHandler.spawn(7606, 3279,5264, height+1, -1, 250, 25, 400, 250,true);
				}else {
					NPCHandler.spawn(7604, 3318,5262, height+1, -1, 250, 25, 400, 250,true);
					NPCHandler.spawn(7605, 3307,5258, height+1, -1, 250, 25, 500, 250,true);
					NPCHandler.spawn(7606, 3301,5262, height+1, -1, 250, 25, 400, 250,true);
				}
				mobAmount+=3;
				mystic = true;
				}
				
				if (player.normalDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7604, 3279,5271, height+1, -1, 550, 35, 500, 350,true);
					NPCHandler.spawn(7605, 3290,5268, height+1, -1, 550, 35, 600, 350,true);
					NPCHandler.spawn(7606, 3279,5264, height+1, -1, 550, 35, 500, 350,true);
				}else {
					NPCHandler.spawn(7604, 3318,5262, height+1, -1, 550, 35, 500, 350,true);
					NPCHandler.spawn(7605, 3307,5258, height+1, -1, 550, 35, 600, 350,true);
					NPCHandler.spawn(7606, 3301,5262, height+1, -1, 550, 35, 500, 350,true);
				}
				mobAmount+=3;
				mystic = true;
				}
				
				if (player.hardDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7604, 3279,5271, height+1, -1, 850, 45, 600, 450,true);
					NPCHandler.spawn(7605, 3290,5268, height+1, -1, 850, 45, 700, 450,true);
					NPCHandler.spawn(7606, 3279,5264, height+1, -1, 850, 45, 600, 450,true);
				}else {
					NPCHandler.spawn(7604, 3318,5262, height+1, -1, 850, 45, 600, 450,true);
					NPCHandler.spawn(7605, 3307,5258, height+1, -1, 850, 45, 700, 450,true);
					NPCHandler.spawn(7606, 3301,5262, height+1, -1, 850, 45, 600, 450,true);
				}
				mobAmount+=3;
				mystic = true;
				}
				break;
			case "tekton":
				if(tekton) {
					return;
				}
				if (player.easyDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7544, 3280, 5295, height+1, -1, 1200, 45, 450, 300,true);
				}else {
					NPCHandler.spawn(7544, 3310, 5293, height+1, -1, 1200, 45, 450, 300,true);
				}
				mobAmount+=1;
				tekton = true;
				}
				
				if (player.normalDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7544, 3280, 5295, height+1, -1, 1500, 55, 550, 400,true);
				}else {
					NPCHandler.spawn(7544, 3310, 5293, height+1, -1, 1500, 55, 550, 400,true);
				}
				mobAmount+=1;
				tekton = true;
				}
				
				if (player.hardDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7544, 3280, 5295, height+1, -1, 1800, 65, 650, 500,true);
				}else {
					NPCHandler.spawn(7544, 3310, 5293, height+1, -1, 1800, 65, 650, 500,true);
				}
				mobAmount+=1;
				tekton = true;
				}
				break;
			case "muttadile":
				if(mutta) {
					return;
				}
				if (player.easyDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7563, 3276,5331, height + 1, 1, 750, 25, 400, 400,true);
				}else {
					NPCHandler.spawn(7563, 3308,5331, height + 1, 1, 750, 25, 400, 400,true);
				}
				mobAmount+=1;
				mutta = true;
				}
				
				if (player.normalDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7563, 3276,5331, height + 1, 1, 1050, 35, 500, 500,true);
				}else {
					NPCHandler.spawn(7563, 3308,5331, height + 1, 1, 1050, 35, 500, 500,true);
				}
				mobAmount+=1;
				mutta = true;
				}
				
				if (player.hardDifficulty) {
				if(path == 0) {
					NPCHandler.spawn(7563, 3276,5331, height + 1, 1, 1350, 45, 600, 600,true);
				}else {
					NPCHandler.spawn(7563, 3308,5331, height + 1, 1, 1350, 45, 600, 600,true);
				}
				mobAmount+=1;
				mutta = true;
				}
				break;
			case "olm":
				if(olm) {
					return;
				}
				if (player.easyDifficulty) {
				NPCHandler.spawn(7553, 3223, 5733, height, -1, 500, 33, 272, 272,false); // left claw
				NPCHandler.spawn(7554, 3223, 5737, height, -1, 1600, 33, 272, 272,true); // olm head
				NPCHandler.spawn(7555, 3223, 5742, height, -1, 500, 33, 272, 272,false); // right claw

				Server.getGlobalObjects().add(new GlobalObject(29884, 3220, 5743, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(29887, 3220, 5733, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(29881, 3220, 5737, getHeight(player), 3, 10));

				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5732, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5733, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5734, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5735, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5736, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5737, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5738, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5739, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5740, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5741, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5742, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5743, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5744, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5745, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5746, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5747, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5748, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5749, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5750, getHeight(player), 3, 10));
				olm = true;
				mobAmount+=3;
				}
				
				if (player.normalDifficulty) {
				NPCHandler.spawn(7553, 3223, 5733, height, -1, 1000, 43, 372, 372,false); // left claw
				NPCHandler.spawn(7554, 3223, 5737, height, -1, 1900, 43, 372, 372,true); // olm head
				NPCHandler.spawn(7555, 3223, 5742, height, -1, 1000, 43, 372, 372,false); // right claw

				Server.getGlobalObjects().add(new GlobalObject(29884, 3220, 5743, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(29887, 3220, 5733, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(29881, 3220, 5737, getHeight(player), 3, 10));

				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5732, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5733, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5734, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5735, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5736, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5737, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5738, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5739, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5740, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5741, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5742, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5743, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5744, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5745, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5746, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5747, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5748, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5749, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5750, getHeight(player), 3, 10));
				olm = true;
				mobAmount+=3;
				}
				
				if (player.hardDifficulty) {
				NPCHandler.spawn(7553, 3223, 5733, height, -1, 1300, 53, 472, 472,false); // left claw
				NPCHandler.spawn(7554, 3223, 5737, height, -1, 2100, 53, 472, 472,true); // olm head
				NPCHandler.spawn(7555, 3223, 5742, height, -1, 1300, 53, 472, 472,false); // right claw

				Server.getGlobalObjects().add(new GlobalObject(29884, 3220, 5743, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(29887, 3220, 5733, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(29881, 3220, 5737, getHeight(player), 3, 10));

				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5732, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5733, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5734, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5735, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5736, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5737, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5738, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5739, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5740, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5741, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5742, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5743, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5744, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5745, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5746, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5747, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5748, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5749, getHeight(player), 3, 10));
				Server.getGlobalObjects().add(new GlobalObject(2376, 3227, 5750, getHeight(player), 3, 10));
				olm = true;
				mobAmount+=3;
				}
				break;
		}
		reachedRoom+=1;
	}
	/**
	 * Handles object clicking for raid objects
	 * @param player The player
	 * @param objectId The object id
	 * @return
	 */
	public boolean handleObjectClick(Player player, int objectId) {
		switch(objectId) {
			case 29789:
			case 29734:
			case 29879:
				player.getRaids().nextRoom();
				return true;
			case 29777:
		        player.getRaids().roomNames = new ArrayList<String>();
		        player.getRaids().roomPaths= new ArrayList<Location>();
		        player.getRaids().currentRoom = 0;
		        player.getRaids().mobAmount=0;
		        player.getRaids().reachedRoom = 0;
		        player.getRaids().raidLeader=null;
		        player.getRaids().lizards = false;
		        player.getRaids().vasa = false;
		        player.getRaids().vanguard = false;
		        player.getRaids().ice = false;
		        player.getRaids().chest = false;
		        player.getRaids().mystic = false;
		        player.getRaids().tekton = false;
		        player.getRaids().mutta = false;
		        player.getRaids().archers = false;
		        player.getRaids().olm = false;
		        player.getRaids().olmDead = false;
		        player.getRaids().rightHand = false;
		        player.getRaids().leftHand = false;
		        player.easyDifficulty = false;
		        player.normalDifficulty = false;
		        player.hardDifficulty = false;
				if (player.clan == null || !player.clan.isFounder(player.playerName)) {
					player.sendMessage("You're not in a clan that you own, and can not pass the door.");
					return true;
				}
				if(player.combatLevel < 75 && player.totalLevel < 750){
					player.sendMessage("You need at least 75 combat and 750 total level to attend raids.");
					return true;
				} else {
					player.getDH().sendDialogues(7500, 10);
					return true;
				}

			case 29778:
				player.getRaids().leaveGame(player);
				return true;

			case 30028:
				int spot = 0;
				if (!player.hasOpenedChest) {
				player.setRaidPoints(0);
				this.reset();
				player.getRaids().giveReward();
				player.relicUpgradeRaids();
				player.getPA().showInterface(64500);
				player.getPA().itemOnInterface(player.raidCommonReward1[0][0], player.raidCommonReward1[0][1], 64503, spot);
				spot++;
				player.getPA().itemOnInterface(player.raidCommonReward2[0][0], player.raidCommonReward2[0][1], 64503, spot);
				spot++;
				player.getPA().itemOnInterface(player.raidCommonReward3[0][0], player.raidCommonReward3[0][1], 64503, spot);
				spot++;
				
				if (rewardChance >= 99) {
					player.getPA().itemOnInterface(player.raidRareReward[0][0], player.raidRareReward[0][1], 64503, spot);
					player.getItems().addItemUnderAnyCircumstance(player.raidRareReward[0][0], player.raidRareReward[0][1]);
					spot++;
				}
				
				player.getItems().addItemUnderAnyCircumstance(player.raidCommonReward1[0][0], player.raidCommonReward1[0][1]);
				player.getItems().addItemUnderAnyCircumstance(player.raidCommonReward2[0][0], player.raidCommonReward2[0][1]);
				player.getItems().addItemUnderAnyCircumstance(player.raidCommonReward3[0][0], player.raidCommonReward3[0][1]);
				player.hasOpenedChest = true;
				
				} else {
					player.sendMessage("You already recieved your reward!");
					player.getPA().closeAllWindows();
					int points = player.getRaidPoints();
					player.getItems().addItem(11179, points);
					player.setRaidPoints(0);
					this.reset();
				}
				return true;
			}
		
		return false;
	}
	/**
	 * Goes to the next room, Handles spawning etc.
	 */
	public void nextRoom() {
		if(player.getRaids().raidLeader.playerName != player.playerName) { //this part handles for the players
				if (player.getRaids().currentRoom == player.getRaids().raidLeader.getRaids().reachedRoom && currentRoom != 0) { 
					if (player.getRaids().raidLeader.getRaids().mobAmount > 0) {
						player.sendMessage("@red@Please defeat all the monsters before going to the next room.");
						return;
					}
				}

		}else{ 
			if (player.getRaids().currentRoom == player.getRaids().raidLeader.getRaids().reachedRoom) { //this part for the raid leader
				if (player.getRaids().raidLeader.getRaids().mobAmount > 0) {
					player.sendMessage("@red@Please defeat all the monsters before going to the next room.");
					return;
				}
			}
		}
		
		player.getPA().movePlayer(roomPaths.get(currentRoom+1).getX(),roomPaths.get(currentRoom+1).getY(),roomPaths.get(currentRoom+1).getZ() == 1 ? getHeight(player) + 1 :getHeight(player));

		currentRoom+=1;

		if(player.getRaids().raidLeader.playerName != player.playerName) {
			return;
		}
		spawnNpcs(currentRoom);
	}
}