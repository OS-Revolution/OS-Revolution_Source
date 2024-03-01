package ethos.model.players.skills.firemake;

import java.util.Random;

import ethos.Config;
import ethos.Server;
import ethos.clip.Region;
import ethos.event.CycleEvent;
import ethos.event.CycleEventContainer;
import ethos.event.CycleEventHandler;
import ethos.model.content.achievement.AchievementType;
import ethos.model.content.achievement.Achievements;
import ethos.model.content.achievement_diary.lumbridge_draynor.LumbridgeDraynorDiaryEntry;
import ethos.model.content.dailytasks.DailyTasks;
import ethos.model.content.dailytasks.DailyTasks.PossibleTasks;
import ethos.model.players.Boundary;
import ethos.model.players.Player;
import ethos.model.players.mode.ModeType;
import ethos.model.players.skills.Skill;
import ethos.util.Misc;
import ethos.world.objects.GlobalObject;

public class Firemaking {

	public static int[] pyromancerOutfit = { 20704, 20706, 20708, 20710 };

	public static void lightFire(final Player player, final int logUsed, final String usage) {		
		final LogData log = LogData.getLogData(player, logUsed);
		if(log == null)
			return;

		final int level = log.getlevelRequirement();
		final String name = log.name().toLowerCase().replaceAll("_", " ");

		if (Region.getClipping(player.getX(), player.getY(), player.getHeight()) != 0
				|| Server.getGlobalObjects().anyExists(player.getX(), player.getY(), player.getHeight()) || player.inBank()
				|| Boundary.isIn(player, Boundary.DUEL_ARENA) || Boundary.isIn(player, Boundary.HALLOWEEN_ORDER_MINIGAME)) {
			player.sendMessage("You cannot light a fire here.");
			return;
		}
		if (player.getLevelForXP(player.playerXP[Skill.FIREMAKING.getId()]) < level) {
			player.sendMessage("You need a firemaking level of at least " + level + " to light the " + name + ".");
			return;
		}
		if (System.currentTimeMillis() - player.lastFire < 1200) {
			return;
		}
		if (player.playerIsFiremaking) {
			return;
		}

		if (usage.equals("infernal_axe")) {
			if (!player.getItems().playerHasItem(logUsed)) {
				player.sendMessage("You do not have anymore of this log.");
				return;
			}
		}

		int x = player.absX, y = player.absY, z = player.heightLevel;

		if (usage.equals("tinderbox")) {
			player.startAnimation(733);
			player.sendMessage("You Attempt To Light the Logs");
			player.playerIsFiremaking = true;

			player.getItems().deleteItem(log.getlogId(), player.getItems().getItemSlot(log.getlogId()), 1);
			Server.itemHandler.createGroundItem(player, log.getlogId(), x, y, player.getHeight(), 1, player.getIndex());

			CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
				int tick = 0;

				@Override
				public void execute(CycleEventContainer container) {
					if(player.playerIsFiremaking == false) {
						container.stop();
						return;
					}
					if(tick != 0 && tick % 8 == 0) {
						player.startAnimation(733);
						player.sendMessage("You Attempt To light the fire");
					}

					if(tick % 2 == 0) {
						if(Server.itemHandler.itemExists(log.getlogId(),  x, y, player.getHeight()) == false) {
							container.stop();
							return;
						}
						if(succesful(player.playerLevel[player.playerFiremaking], log.getlevelRequirement(), System.currentTimeMillis() - player.lastFire < 1000 ? 100 : 0)) {
							createFire(player, usage, log, x, y, z);
							container.stop();
							return;
						}
					}
					tick++;
				
				}
			}, 1);
		}
		
		if (usage.equals("infernal_axe")) {
			double osrsExperience = 0;
			double regExperience = 0;
			int pieces = 0;
			for (int i = 0; i < pyromancerOutfit.length; i++) {
				if (player.getItems().isWearingItem(pyromancerOutfit[i])) {
					pieces++;
				}
			}

			osrsExperience = log.getExperience() * Config.FIREMAKING_EXPERIENCE + log.getExperience() * Config.FIREMAKING_EXPERIENCE / 10 * pieces;
			regExperience = log.getExperience() * Config.FIREMAKING_EXPERIENCE + log.getExperience() * Config.FIREMAKING_EXPERIENCE / 10 * pieces;

			if (usage.equals("infernal_axe")) {
				player.getPA().addSkillXP((int) (regExperience / 2), 11, true);
			} else {
				player.getPA().addSkillXP((int) regExperience, 11, true);
			}
			if (Misc.random(25) == 0) {
				int sPoints = Misc.random(1, 5);
				//   player.skillPoints += sPoints;
				player.sendMessage("@pur@You receive " + sPoints + " Skill Points.");
			}
			/*if (Misc.random(6000) == 1) {
			if (!SkillPets.FIREMAKING.hasPet(player)) {
				PetHandler.skillPet(player, SkillPets.FIREMAKING);
			}

			}*/

			//final String name = log.name().toLowerCase().replaceAll("_", " ");
			player.sendMessage("You light the " + name + ".");
		}
	}

	public static boolean succesful(int level, int levelRequired, int bonus) {
		double chance = 0.0;
		double baseChance = Math.pow(10d-levelRequired/10d, 2d)/2d;
		chance = baseChance + ((level - levelRequired) / 2d) + (bonus / 10d);
	/*	if (level == 99) {
			return chance >= (new Random().nextDouble() * 50.0);
		}*/
		return chance >= (new Random().nextDouble() * 50.0);
	}

	public static void createFire(final Player player, final String usage, final LogData log, final int x, final int y, final int z) {
		if(player.playerIsFiremaking == false)
			return;

		player.playerIsFiremaking = false;
		player.lastFire = System.currentTimeMillis();

		Server.getGlobalObjects().add(new GlobalObject(5249, x, y, z, 0, 10, 100 + log.getlevelRequirement(), -1));
		CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				Server.itemHandler.createUnownedGroundItem(592, x, y, z, 1);
				container.stop(); // whoops!
			}
		}, 100 + log.getlevelRequirement());

		Server.itemHandler.removeGroundItem(player, log.getlogId(), x, y, z, false);

		moveOneStep(player);
		player.startAnimation(65535);

		Achievements.increase(player, AchievementType.FIRE, 1);

		if (log.getlogId() == 1521)
			player.getDiaryManager().getLumbridgeDraynorDiary().progress(LumbridgeDraynorDiaryEntry.BURN_OAK);

		if (log.getlogId() == 1515)
			DailyTasks.increase(player, PossibleTasks.LIGHT_YEW_LOGS);
		if (log.getlogId() == 1513)
			DailyTasks.increase(player, PossibleTasks.LIGHT_MAGIC_LOGS);

		CycleEventHandler.getSingleton().addEvent(player, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				player.turnPlayerTo(x, y);
				//player.startAnimation(65535);
				container.stop();
			}
		}, 2);

		/**
		 * Experience calculation
		 */
		double osrsExperience = 0;
		double regExperience = 0;
		int pieces = 0;
		for (int i = 0; i < pyromancerOutfit.length; i++) {
			if (player.getItems().isWearingItem(pyromancerOutfit[i])) {
				pieces++;
			}
		}

		osrsExperience = log.getExperience() * Config.FIREMAKING_EXPERIENCE + log.getExperience() * Config.FIREMAKING_EXPERIENCE / 10 * pieces;
		regExperience = log.getExperience() * Config.FIREMAKING_EXPERIENCE + log.getExperience() * Config.FIREMAKING_EXPERIENCE / 10 * pieces;

		if (usage.equals("infernal_axe")) {
			player.getPA().addSkillXP((int) (player.getMode().getType().equals(ModeType.OSRS) ? osrsExperience / 2 : regExperience / 2), 11, true);
		} else {
			player.getPA().addSkillXP((int) (player.getMode().getType().equals(ModeType.OSRS) ? osrsExperience : regExperience), 11, true);
		}
		if (Misc.random(25) == 0) {
			int sPoints = Misc.random(1, 5);
			//   player.skillPoints += sPoints;
			player.sendMessage("@pur@You receive " + sPoints + " Skill Points.");
		}
		/*if (Misc.random(6000) == 1) {
		if (!SkillPets.FIREMAKING.hasPet(player)) {
			PetHandler.skillPet(player, SkillPets.FIREMAKING);
		}

		}*/

		final String name = log.name().toLowerCase().replaceAll("_", " ");
		player.sendMessage("You light the " + name + ".");
	}

	/**
	 * Handles movement direction.
	 * @param c 
	 */
	private static void moveOneStep(Player c){
		if (Region.getClipping(c.getX() - 1, c.getY(), c.heightLevel, -1, 0) && !Server.getGlobalObjects().anyExists(c.getX() - 1, c.getY(), c.getHeight())) {
			c.getPA().walkTo(-1, 0);
		} else if (Region.getClipping(c.getX() + 1, c.getY(), c.heightLevel, 1, 0) && !Server.getGlobalObjects().anyExists(c.getX() + 1, c.getY(), c.getHeight())) {
			c.getPA().walkTo(1, 0);
		} else if (Region.getClipping(c.getX(), c.getY() - 1, c.heightLevel, 0, -1) && !Server.getGlobalObjects().anyExists(c.getX(), c.getY() - 1, c.getHeight())) {
			c.getPA().walkTo(0, -1);
		} else if (Region.getClipping(c.getX(), c.getY() + 1, c.heightLevel, 0, 1) && !Server.getGlobalObjects().anyExists(c.getX(), c.getY() + 1, c.getHeight())) {
			c.getPA().walkTo(0, 1);
		}  else if (Region.getClipping(c.getX() - 1, c.getY(), c.heightLevel, -1, 0)) {
			c.getPA().walkTo(-1, 0);
		} else if (Region.getClipping(c.getX() + 1, c.getY(), c.heightLevel, 1, 0)) {
			c.getPA().walkTo(1, 0);
		} else if (Region.getClipping(c.getX(), c.getY() - 1, c.heightLevel, 0, -1)) {
			c.getPA().walkTo(0, -1);
		} else if (Region.getClipping(c.getX(), c.getY() + 1, c.heightLevel, 0, 1)) {
			c.getPA().walkTo(0, 1);
		} else {
			c.sendMessage("Nowhere left to go");
		}

	}
}
