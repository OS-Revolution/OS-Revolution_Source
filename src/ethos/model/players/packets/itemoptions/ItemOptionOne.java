package ethos.model.players.packets.itemoptions;

import ethos.Server;
import ethos.model.content.DiceHandler;
import ethos.model.content.LootingBag.LootingBag;
import ethos.model.content.Packs;
import ethos.model.content.PlayerEmotes;
import ethos.model.content.RunePouch;
import ethos.model.content.achievement.AchievementType;
import ethos.model.content.achievement.Achievements;
import ethos.model.content.barrows.Barrows;
import ethos.model.content.teleportation.TeleportTablets;
import ethos.model.content.trails.MasterClue;
import ethos.model.content.trails.RewardLevel;
import ethos.model.multiplayer_session.MultiplayerSessionType;
import ethos.model.multiplayer_session.duel.DuelSession;
import ethos.model.multiplayer_session.duel.DuelSessionRules.Rule;
import ethos.model.players.*;
import ethos.model.players.combat.Hitmark;
import ethos.model.players.combat.magic.NonCombatSpells;
import ethos.model.players.skills.agility.AgilityHandler;
import ethos.model.players.skills.hunter.Hunter;
import ethos.model.players.skills.hunter.trap.impl.BirdSnare;
import ethos.model.players.skills.hunter.trap.impl.BoxTrap;
import ethos.model.players.skills.prayer.Bone;
import ethos.model.players.skills.prayer.Prayer;
import ethos.model.players.skills.runecrafting.Pouches;
import ethos.model.players.skills.runecrafting.Pouches.Pouch;
import ethos.net.discord.DiscordMessager;
import ethos.runehub.action.click.item.ClickItemActionFactory;
import ethos.runehub.loot.Lootbox;
import ethos.runehub.skill.artisan.herblore.action.CleanHerbAction;
import ethos.util.Misc;
import ethos.world.objects.GlobalObject;

import java.util.Objects;
import java.util.Optional;

import static ethos.model.content.DiceHandler.DICING_AREA;

/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ItemOptionOne implements PacketType {
    /**
     * The last planted flower
     */
    private int lastPlantedFlower;

    /**
     /**
     * If the entity is in the midst of planting mithril seeds
     */
    private static boolean plantingMithrilSeeds = false;

    /**
     * The position the mithril seed was planted
     */
    private static final int[] position = new int[2];

    /**
     * Chances of getting to array flower1
     */
    private int flowerChance = Misc.random(100);


    /**
     * Array containing flower object ids without black and white flowers
     */
    private int flower[] = {
            2980, 2981, 2982,
            2983, 2984, 2985,
            2986
    };

    /**
     * Array containing flower object ids along with black and white flowers
     */
    private int[] flower1 = {
            2980, 2981, 2982,
            2983, 2984, 2985,
            2986, 2987, 2988
    };

    /**
     * Draws a random flower object from the flower arrays
     * @return
     * 			the flower object chosen
     */
    private int randomFlower() {
        if (flowerChance > 29) {
            return flower[(int) (Math.random() * flower.length)];
        }
        return flower[(int) (Math.random() * flower1.length)];
    }
	    private void plantBank(Player c) {
	    	if (c.inWild()) {
	    		c.sendMessage("You can't use this in the wilderness.");
				return;
	    	}
	    	position[0] = c.absX;
	        position[1] = c.absY;
	        GlobalObject object1 = new GlobalObject(24101, position[0], position[1], c.getHeight(), 3, 10, 120, -1);
	        Server.getGlobalObjects().add(object1);
	        c.sendMessage("You placed a portable bank!");
	        plantingMithrilSeeds = true;
	        c.getItems().deleteItem(19668, c.getItems().getItemSlot(19668), 1);
	    	}
    private void plantMithrilSeed(Player c) {
        position[0] = c.absX;
        position[1] = c.absY;
        lastPlantedFlower = randomFlower();
        GlobalObject object1 = new GlobalObject(lastPlantedFlower, position[0], position[1], c.getHeight(), 3, 10, 120, -1);
        Server.getGlobalObjects().add(object1);
        c.getPA().walkTo(1, 0);
        c.turnPlayerTo(position[0] -1, position[1]);
        c.sendMessage("You planted a flower!");
        plantingMithrilSeeds = true;
        c.getItems().deleteItem(299, c.getItems().getItemSlot(299), 1);
    }
    public static void plantMithrilSeedRigged(Player c,int flowerId) {
        position[0] = c.absX;
        position[1] = c.absY;
        GlobalObject object1 = new GlobalObject(flowerId, position[0], position[1], c.getHeight(), 3, 10, 120, -1);
        Server.getGlobalObjects().add(object1);
        c.getPA().walkTo(1, 0);
        c.turnPlayerTo(position[0] -1, position[1]);
        c.sendMessage("You planted a flower!");
        plantingMithrilSeeds = true;
    }
    @Override
    public void processPacket(Player c, int packetType, int packetSize) {
        c.getInStream().readSignedShortLittleEndianA();
        int itemSlot = c.getInStream().readSignedWordA();
        int itemId = c.getInStream().readSignedWordBigEndian();
        if (itemSlot >= c.playerItems.length || itemSlot < 0) {
            return;
        }
        if (itemId != c.playerItems[itemSlot] - 1) {
            return;
        }
        if (c.isDead || c.getHealth().getAmount() <= 0) {
            return;
        }
        if (Server.getHolidayController().clickItem(c, itemId)) {
            return;
        }
        if (c.getInterfaceEvent().isActive()) {
            c.sendMessage("Please finish what you're doing.");
            return;
        }
        if (c.getBankPin().requiresUnlock()) {
            c.getBankPin().open(2);
            return;
        }
        c.lastClickedItem = itemId;
        ClickItemActionFactory.onClick(c,itemId,itemSlot);
//        c.getHerblore().clean(itemId);
        if (c.getFood().isFood(itemId)) {
            c.getFood().eat(itemId, itemSlot);
        } else if (c.getPotions().isPotion(itemId)) {

//            c.getPotions().handlePotion(itemId, itemSlot);

        }
        Optional<Bone> bone = Prayer.isOperableBone(itemId);
        if (bone.isPresent()) {
            c.getPrayer().bury(bone.get());
            return;
        }
        TeleportTablets.operate(c, itemId);
        Packs.openPack(c, itemId);
        if (LootingBag.isLootingBag(c, itemId)) {
            c.getLootingBag().openWithdrawalMode();
            return;
        }
        if (RunePouch.isRunePouch(c, itemId)) {
            c.getRunePouch().openRunePouch();
            return;
        }
        if(Lootbox.isLootbox(itemId)) {
                final Lootbox lootbox = new Lootbox(itemId, c);
                lootbox.open();
        }
        switch (itemId) {
            case 199:
            case 201:
            case 203:
            case 205:
            case 207:
            case 3049:
            case 209:
            case 211:
            case 213:
            case 3051:
            case 215:
            case 2485:
            case 217:
            case 219:
                c.getSkillController().getHerblore().train(new CleanHerbAction(
                        c,
                        itemId
                ));
                break;
            case 716:
                c.startAnimation(PlayerEmotes.PLAYER_ANIMATION_DATA.WAVE.getAnimation());
                break;
        case 11171:
        	c.hasHouse = false;
        	c.isInHouse = false;
        	c.sendMessage("Do you have a house?: " +c.hasHouse);
        	break;
        	
        case 11173:
        	Server.getGlobalObjects().add(new GlobalObject(2693, 2455, 2588, c.getHeight(), 3, 10, 10, 2693));
        	break;
        
        case 7630:
        	c.nlevel = 0;
        	c.nxp = 0;
        	break;
        	
        case 4597:
        	c.getPA().sendFrame107();
        	break;
        
        case 19668:
        	if(System.currentTimeMillis() - c.lastPlant < 120000) {
                return;
            }
        	c.lastPlant = System.currentTimeMillis();
        	plantBank(c);
        	break;
        	
        case 8152:
			if (c.inWild() && c.inDuelArena() && c.isInJail() && c.inGodwars() && c.inCwGame) {
				c.sendMessage("@red@You are not allowed to use this item in this location!");
				return;
			} else {
			if (c.bankCharges < 1) {
				c.sendMessage("You have no charges left.");
				return;
			} else {
			if (c.bankCharges > 0) {
				c.getPA().openUpBank();
				c.sendAudio(1877);
				c.bankCharges --;
				c.sendMessage("@red@You have @dre@"+c.bankCharges+" @red@charges left.");
				return;
				}
			}
		}
		break;
			
		case 3495:
			c.bankCharges++;
			c.getItems().deleteItem(3495, 1);
			c.sendMessage("You gained one bank charge and now have " + c.bankCharges + ".");
			break;
			
		case 13190:
			if (!c.membership && c.getItems().playerHasItem(13190)) {
					c.getItems().deleteItem(13190, 1);
					Membership.giveMembership(c);
				}
					c.sendMessage("You already have member benefits.");
			break;
			
        	//StaffPotato
		case 5733:
			c.getDH().sendDialogues(857, 2108);
			break;
            case 299:
                if(!Boundary.isIn(c, DICING_AREA)) {
                    c.sendMessage("You can not be in edge when planting mithril seeds.");
                    return;
                }
                if(System.currentTimeMillis() - c.lastPlant < 250) {
                    return;
                }
                c.lastPlant = System.currentTimeMillis();
                plantMithrilSeed(c);
                break;
            case 13346:
                if (c.getItems().playerHasItem(13346)) {
                    c.getUltraMysteryBox().openInterface();
                    return;
                }
                break;
                
            case 21034:
            	if (c.rigour == 0) {
            	c.getItems().deleteItem(21034, 1);
            	c.rigour = 1;
            	c.sendMessage("You can make out some faded words on the ancient parchment.");
            	c.sendMessage("It appears to be an archaic invocation of the gods!");
            	} else {
            		c.sendMessage("You already have this learned!");
            		return;
            	}
            	break;
            	
            case 21079:
            	if (c.augury == 0) {
            	c.getItems().deleteItem(21079, 1);
            	c.augury = 1;
            	c.sendMessage("You can make out some faded words on the ancient parchment.");
            	c.sendMessage("It appears to be an archaic invocation of the gods!");
            	} else {
            		c.sendMessage("You already have this learned!");
            		return;
            	}
            	break;
            	
            case 21347:
                c.boltTips = true;
                c.arrowTips = false;
                c.javelinHeads = false;
                c.sendMessage("Your Amethyst method is now Bolt Tips!");
                break;
            case 20724:
                DuelSession session = (DuelSession) Server.getMultiplayerSessionListener().getMultiplayerSession(c, MultiplayerSessionType.DUEL);
                if (Objects.nonNull(session)) {
                    if (session.getRules().contains(Rule.NO_DRINKS)) {
                        c.sendMessage("Using the imbued heart with 'No Drinks' option is forbidden.");
                        return;
                    }
                }
                if (System.currentTimeMillis() - c.lastHeart < 420000) {
                    c.sendMessage("You must wait 7 minutes between each use.");
                } else {
                    c.getPA().imbuedHeart();
                    c.lastHeart = System.currentTimeMillis();
                }
                break;
            case 10006:
                Hunter.lay(c, new BirdSnare(c));
                break;
            case 10008:
                Hunter.lay(c, new BoxTrap(c));
                break;
            case 13249:
                if (!c.getSlayer().isCerberusRoute()) {
                    c.sendMessage("You have no clue how to navigate in here, you should find a slayer master to learn.");
                    return;
                }
                AgilityHandler.delayFade(c, "", 1310, 1237, 0, "You teleport into the cave", "and end up at the main room.", 3);
                c.getItems().deleteItem(13249, 1);
                break;

            case 13226:
                c.getHerbSack().fillSack();
                break;

            case 12020:
                c.getGemBag().fillBag();
                break;

            case 5509:
                Pouches.fill(c, Pouch.forId(itemId), itemId, 0);
                break;
            case 5510:
                Pouches.fill(c, Pouch.forId(itemId), itemId, 1);
                break;
            case 5512:
                Pouches.fill(c, Pouch.forId(itemId), itemId, 2);
                break;

            case 952: //Spade
                int x = c.absX;
                int y = c.absY;
                if (Boundary.isIn(c, Barrows.GRAVEYARD)) {
                    c.getBarrows().digDown();
                }
                if (x == 3005 && y == 3376 || x == 2999 && y == 3375 || x == 2996 && y == 3377) {
                    if (!c.getRechargeItems().hasItem(13120)) {
                        c.sendMessage("You must have the elite falador shield to do this.");
                        return;
                    }
                    c.getPA().movePlayer(1760, 5163, 0);
                }
                break;
        }

        if (itemId == 2678) {
            c.getDH().sendDialogues(657, -1);
            return;
        }
        if (itemId == 8015 || itemId == 8014) {
            NonCombatSpells.attemptDate(c, itemId);
        }
        if (itemId == 9553) {
            c.getPotions().eatChoc(9553, -1, itemSlot, 1, true);
        }
        if (itemId == 12846) {
            c.getDH().sendDialogues(578, -1);
        }
        if (itemId == 12938) {
            if (c.getZulrahEvent().getInstancedZulrah() != null) {
                c.sendMessage("It seems your currently in the zulrah instance, relog if this is false.");
                return;
            }
            c.getDH().sendDialogues(625, -1);
            return;
        }
        /*if (itemId == 405) {
			if (c.getItems().freeSlots() < 2) {
				c.sendMessage("You need at least 2 free slots to open this.");
				return;
			}
			c.getItems().deleteItem2(itemId, 1);
			c.getItems().addItem(995, 10000 + Misc.random(90000));
			c.getItems().addItem(1624, 1 + Misc.random(9));
			c.sendMessage("You open the casket and find some treasure inside.");
		}*/
        if (itemId == 4155) {
            if (!c.getSlayer().getTask().isPresent()) {
                c.sendMessage("You do not have a task, please talk with a slayer master!");
                return;
            }
            c.sendMessage("I currently have " + c.getSlayer().getTaskAmount() + " " + c.getSlayer().getTask().get().getPrimaryName() + "'s to kill.");
            c.getPA().closeAllWindows();
        }
        if (itemId == 2839) {
            if (c.getSlayer().isHelmetCreatable() == true) {
                c.sendMessage("You have already learned this recipe. You have no more use for this scroll.");
                return;
            }
            if (c.getItems().playerHasItem(2839)) {
                c.getSlayer().setHelmetCreatable(true);
                c.sendMessage("You have learned the slayer helmet recipe. You can now assemble it");
                c.sendMessage("using a Black Mask, Facemask, Nose peg, Spiny helmet and Earmuffs.");
                c.getItems().deleteItem(2839, 1);
            }
        }
        if (itemId == DiceHandler.DICE_BAG) {
            DiceHandler.selectDice(c, itemId);
        }
        if (itemId > DiceHandler.DICE_BAG && itemId <= 15100) {
            DiceHandler.rollDice(c);
        }
        if (itemId == 2697) {
            c.sendMessage("You can't redeem this scroll on yourself!");
            c.sendMessage("Use this on another player to transfer $5 to their amount donated");
            c.sendMessage("This does NOT give the other player donator points.");
			/*if (c.inWild() || c.inDuelArena() || Server.getMultiplayerSessionListener().inAnySession(c)) {
				return;
			}
			if (c.getItems().playerHasItem(2697, 1)) {
				c.getDH().sendDialogues(4000, -1);
			}*/
        }
        if (itemId == 2698) {
            c.sendMessage("You can't redeem this scroll on yourself!");
            c.sendMessage("Use this on another player to transfer $15 to their amount donated");
            c.sendMessage("This does NOT give the other player donator points.");
			/*if (c.inWild() || c.inDuelArena() || Server.getMultiplayerSessionListener().inAnySession(c)) {
				return;
			}
			if (c.getItems().playerHasItem(2697, 1)) {
				c.getDH().sendDialogues(4000, -1);
			}*/
        }
        if (itemId == 2699) {
            c.sendMessage("You can't redeem this scroll on yourself!");
            c.sendMessage("Use this on another player to transfer $35 to their amount donated");
            c.sendMessage("This does NOT give the other player donator points.");
			/*if (c.inWild() || c.inDuelArena() || Server.getMultiplayerSessionListener().inAnySession(c)) {
				return;
			}
			if (c.getItems().playerHasItem(2697, 1)) {
				c.getDH().sendDialogues(4000, -1);
			}*/
        }
        if (itemId == 2700) {
            c.sendMessage("You can't redeem this scroll on yourself!");
            c.sendMessage("Use this on another player to transfer $50 to their amount donated");
            c.sendMessage("This does NOT give the other player donator points.");
			/*if (c.inWild() || c.inDuelArena() || Server.getMultiplayerSessionListener().inAnySession(c)) {
				return;
			}
			if (c.getItems().playerHasItem(2697, 1)) {
				c.getDH().sendDialogues(4000, -1);
			}*/
        }
        if (itemId == 2701) {
            if (c.inWild() || c.inDuelArena() || Server.getMultiplayerSessionListener().inAnySession(c)) {
                return;
            }
            if (c.getItems().playerHasItem(2701, 1)) {
                c.getDH().sendDialogues(4004, -1);
            }
        }
        if (itemId == 7509) {
            if (c.inWild() || c.inDuelArena() || Boundary.isIn(c, Boundary.DUEL_ARENA)) {
                c.sendMessage("You cannot do this here.");
                return;
            }
            if (c.getHealth().getStatus().isPoisoned() || c.getHealth().getStatus().isVenomed()) {
                c.sendMessage("You are effected by venom or poison, you should cure this first.");
                return;
            }
            if (c.getHealth().getAmount() <= 1) {
                c.sendMessage("I better not do that.");
                return;
            }
            c.forcedChat("Ow! I nearly broke a tooth!");
            c.startAnimation(829);
            // c.getHealth().reduce(1);
            c.appendDamage(1, Hitmark.HIT);
            return;
        }
        if (itemId == 10269) {
            if (c.inWild() || c.inDuelArena()) {
                return;
            }
            if (c.getItems().playerHasItem(10269, 1)) {
                c.getItems().addItem(995, 30000);
                c.getItems().deleteItem(10269, 1);
            }
        }
        if (itemId == 10271) {
            if (c.inWild() || c.inDuelArena()) {
                return;
            }
            if (c.getItems().playerHasItem(10271, 1)) {
                c.getItems().addItem(995, 10000);
                c.getItems().deleteItem(10271, 1);
            }
        }
        if (itemId == 10273) {
            if (c.inWild() || c.inDuelArena()) {
                return;
            }
            if (c.getItems().playerHasItem(10273, 1)) {
                c.getItems().addItem(995, 14000);
                c.getItems().deleteItem(10273, 1);
            }
        }
        if (itemId == 10275) {
            if (c.inWild() || c.inDuelArena()) {
                return;
            }
            if (c.getItems().playerHasItem(10275, 1)) {
                c.getItems().addItem(995, 18000);
                c.getItems().deleteItem(10275, 1);
            }
        }
        if (itemId == 10277) {
            if (c.inWild() || c.inDuelArena()) {
                return;
            }
            if (c.getItems().playerHasItem(10277, 1)) {
                c.getItems().addItem(995, 22000);
                c.getItems().deleteItem(10277, 1);
            }
        }
        if (itemId == 10279) {
            if (c.inWild() || c.inDuelArena()) {
                return;
            }
            if (c.getItems().playerHasItem(10279, 1)) {
                c.getItems().addItem(995, 26000);
                c.getItems().deleteItem(10279, 1);
            }
        }
		/* Mystery box */
        if (itemId == 6199)
            if (c.getItems().playerHasItem(6199)) {
                c.getMysteryBox().open();
                return;
            }
//        if (itemId == 11739)
//            if (c.getItems().playerHasItem(11739)) {
//                c.getHourlyRewardBox().open();
//                return;
//            }
        if (itemId == 405) //Pvm Casket
            if (c.getItems().playerHasItem(405)) {
                c.getPvmCasket().open();
                return;
            }
        if (itemId == 11738) //Herb Box
            if (c.getItems().playerHasItem(11738)) {
                c.getHerbBox().open();
                return;
            }
        if (itemId == 21307) //Pursuit Crate
            if (c.getItems().playerHasItem(21307)) {
                c.getWildyCrate().open();
                return;
            }
        if (itemId == 20703) //Daily Gear Box
            if (c.getItems().playerHasItem(20703)) {
                c.getDailyGearBox().open();
                return;
            }
        if (itemId == 20791) //Daily Skilling Box
            if (c.getItems().playerHasItem(20791)) {
                c.getDailySkillBox().open();
                return;
            }
		/*if (itemId == 7310) //Skill Casket
			if (c.getItems().playerHasItem(7310)) {
				c.getSkillCasket().open();
				return;
			}*/
        if (itemId == 6542)
            if (c.getItems().playerHasItem(6542)) {
                c.getChristmasPresent().open();
                return;
            }
        if (itemId == 2714) { // Easy Clue Scroll Casket
            c.getItems().deleteItem(itemId, 1);
            c.getTrails().addRewards(RewardLevel.EASY);
            c.setEasyClueCounter(c.getEasyClueCounter() + 1);
            c.sendMessage("@blu@You have completed " + c.getEasyClueCounter() + " easy Treasure Trails.");
        }
        if (itemId == 2802) { // Medium Clue Scroll Casket
            c.getItems().deleteItem(itemId, 1);
            c.getTrails().addRewards(RewardLevel.MEDIUM);
            c.setMediumClueCounter(c.getMediumClueCounter() + 1);
            c.sendMessage("@blu@You have completed " + c.getMediumClueCounter() + " medium Treasure Trails.");
        }
        if (itemId == 2775) { // Hard Clue Scroll Casket
            c.getItems().deleteItem(itemId, 1);
            c.getTrails().addRewards(RewardLevel.HARD);
            c.setHardClueCounter(c.getHardClueCounter() + 1);
            c.sendMessage("@blu@You have completed " + c.getHardClueCounter() + " hard Treasure Trails.");
        }
        if (itemId == 19841) { // Master Clue Scroll Casket
            if (c.getItems().playerHasItem(19841)) {
                c.getItems().deleteItem(itemId, 1);
                c.getTrails().addRewards(RewardLevel.MASTER);
                c.setMasterClueCounter(c.getMasterClueCounter() + 1);
                c.sendMessage("@blu@You have completed " + c.getMasterClueCounter() + " master Treasure Trails.");
                if (Misc.random(200) == 2 && c.getItems().getItemCount(19730, true) == 0 && c.summonId != 19730) {
                    PlayerHandler.executeGlobalMessage("[<col=CC0000>Clue</col>] @cr20@ <col=255>" + c.playerName
                            + "</col> hit the jackpot and got a <col=CC0000>Bloodhound</col> pet!");
                    DiscordMessager.sendLootations("[Clue] " + c.playerName
                            + " hit the jackpot and got a Bloodhound pet!");
                    c.getItems().addItemUnderAnyCircumstance(19730, 1);
                }
            }
        }
        if (itemId == 2677) {
            Achievements.increase(c, AchievementType.CLUES, 1);
            c.getItems().deleteItem(itemId, 1);
            c.getItems().addItem(2714, 1);
            c.sendMessage("You've recieved a easy clue scroll casket.");
        }
        if (itemId == 2801) {
            Achievements.increase(c, AchievementType.CLUES, 1);
            c.getItems().deleteItem(itemId, 1);
            c.getItems().addItem(2802, 1);
            c.sendMessage("You've recieved a medium clue scroll casket.");
        }
        if (itemId == 2722) {
            Achievements.increase(c, AchievementType.CLUES, 1);
            c.getItems().deleteItem(itemId, 1);
            c.getItems().addItem(2775, 1);
            c.sendMessage("You've recieved a hard clue scroll casket.");
        }
        /**
         * Master clue scroll
         */
        if (itemId == 19835) {
            MasterClue.progressScroll(c);
        }
        if (itemId == 2528) {
            c.usingLamp = true;
            c.normalLamp = true;
            c.antiqueLamp = false;
            c.sendMessage("You rub the lamp...");
            c.getPA().showInterface(2808);
        }
    }
}