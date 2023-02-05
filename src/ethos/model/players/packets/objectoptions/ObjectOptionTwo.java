package ethos.model.players.packets.objectoptions;

import ethos.Server;
import ethos.clip.ObjectDef;
import ethos.model.players.Player;
import ethos.model.players.Right;
import ethos.model.players.packets.objectoptions.impl.DarkAltar;
import ethos.model.players.skills.FlaxPicking;
import ethos.model.players.skills.necromancy.Necromancy;
import ethos.model.players.skills.thieving.Thieving.Stall;
import ethos.runehub.action.click.node.SecondClickNodeActionListener;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.player.action.SecondClickNodeActionFactory;
import ethos.util.Location3D;
import ethos.util.Misc;

/*
 * @author Matt
 * Handles all 2nd options for objects.
 */

public class ObjectOptionTwo {

	public static void handleOption(final Player c, int objectType, int obX, int obY) {
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
		c.clickObjectType = 0;
		if (Server.getHolidayController().clickObject(c, 2, objectType, obX, obY)) {
			return;
		}
		Location3D location = new Location3D(obX, obY, c.heightLevel);
		ObjectDef def = ObjectDef.getObjectDef(objectType);
		if ((def!=null ? def.name : null)!= null && def.name.toLowerCase().contains("bank")) {
			c.sendAudio(1877);
			c.getPA().openUpBank();
		}
		if (c.getRightGroup().isOrInherits(Right.OWNER))
			c.sendMessage("Clicked Object Option 2:  "+objectType+"");
		switch (objectType) {
			case 6945:
				MerchantCache.getInstance().read(2148).openShop(c);
				break;
//			case 20927:
//				c.getSkillController().getFishing().train(
//						new FishingSkillAction(
//								c,
//								new FishingNodeContext(
//										2,
//										obX,
//										obY,
//										c.heightLevel
//								),
//								objectType
//						)
//				);
//				break;
//			case 20926:
//				c.getSkillController().getFishing().train(
//						new FishingSkillAction(
//								c,
//								new FishingNodeContext(
//										8,
//										obX,
//										obY,
//										c.heightLevel
//								),
//								objectType
//						)
//				);
//				break;
//			case 20929:
//				c.getSkillController().getFishing().train(
//						new FishingSkillAction(
//								c,
//								new FishingNodeContext(
//										6,
//										obX,
//										obY,
//										c.heightLevel
//								),
//								objectType
//						)
//				);
//				break;
//			case 20928:
//				c.getSkillController().getFishing().train(
//						new FishingSkillAction(
//								c,
//								new FishingNodeContext(
//										4,
//										obX,
//										obY,
//										c.heightLevel
//								),
//								objectType
//						)
//				);
//				break;
		case 29778:
			c.sendMessage("hello");
			break;
		case 28900:
			DarkAltar.handleRechargeInteraction(c);
			break;
		
		case 7811:
			if (!c.inClanWarsSafe()) {
				return;
			}
			c.getShops().openShop(115);
			break;
			
		case 30008:
			if (c.playerLevel[c.playerFarming] >= 56) {
				Necromancy.farmMysteriousHerb(c);
			} else {
				c.sendMessage("You need a farming level of 56 to do this.");
			}
			break;
		/**
		 * Iron Winch - peek
		 */
		case 23104:
			c.getDH().sendDialogues(110, 5870);
			break;
			
		case 2118:
			c.getPA().movePlayer(3434, 3537, 0);
			break;

		case 2114:
			c.getPA().movePlayer(3433, 3537, 1);
			break;
		case 25824:
		case 4309:
			c.turnPlayerTo(obX, obY);
			c.getDH().sendDialogues(40, -1);
			break;
		case 26260:
			c.getDH().sendDialogues(55874, -1);
			break;
		case 14896:
			
			FlaxPicking.getInstance().pick(c, new Location3D(obX, obY, c.heightLevel));
			break; 
//		case 11730:
//			c.getThieving().steal(Stall.Baker, objectType, location);
//			break;
//		case 11733:
//			c.getThieving().steal(Stall.Spice, objectType, location);
//			break;
		//bar stool test
		case 2312:
			c.turnPlayerTo(obX, obY);
			c.getPA().movePlayer(3086, 3259, 0);
			c.startAnimation(2939);
			break;
		case 3840: // Compost Bin
			c.getFarming().handleCompostRemoval();
		break;
		case 4874:
		case 4877:
	/*	case 11731:
			c.getThieving().steal(Stall.Magic, objectType, location);
			break; */
//		case 14011:
//			c.getThieving().steal(Stall.Wine, objectType, location);
//			break;
//		// STALLS ADDED 3/4/21
//		case 4277:
//		case 3860:
//		case 4275:
//			c.getThieving().steal(Stall.Fish, objectType, location);
//			break;
//		case 11734:
//			c.getThieving().steal(Stall.Silver, objectType, location);
//			break;
//
//		case 7053:
//			c.getThieving().steal(Stall.Seeds, objectType, location);
//			break;
//		case 5595:
//			c.getThieving().steal(Stall.Toys, objectType, location);
//			break;
//		case 629:
//		case 11729:
//			c.getThieving().steal(Stall.Silk, objectType, location);
//			break;
//		// END
//		case 4876:
//			c.getThieving().steal(Stall.General, objectType, location);
//			break;
//		case 4878:
//			c.getThieving().steal(Stall.Scimitar, objectType, location);
//			break;
//		case 4875:
//			c.getThieving().steal(Stall.Food, objectType, location);
//			break;
//		case 11732:
//			c.getThieving().steal(Stall.Fur, objectType, location);
//			break;
			
		case 23609:
			c.getPA().movePlayer(3507, 9494, 0);
			break;
			
			//Donator Area doors
		case 12617:
			c.getPA().movePlayer(2905, 5470, 0);
			break;
		case 11987:
			c.getPA().movePlayer(2910, 5478, 0);
			break;
		case 12719:
			c.getPA().movePlayer(2918, 5473, 0);
			break;
		case 12639:
			c.getPA().movePlayer(2913, 5465, 0);
			break;
			
		case 2558:
		case 8356://streequid
			c.getPA().movePlayer(1255, 3568, 0);
			break;
		case 2557:
			if (System.currentTimeMillis() - c.lastLockPick < 1000 || c.freezeTimer > 0) {
				return;
			}
			c.lastLockPick = System.currentTimeMillis();
			if (c.getItems().playerHasItem(1523, 1)) {

				if (Misc.random(10) <= 2) {
					c.sendMessage("You fail to pick the lock.");
					break;
				}
				if (c.objectX == 3044 && c.objectY == 3956) {
					if (c.absX == 3045) {
						c.getPA().walkTo(-1, 0);
					} else if (c.absX == 3044) {
						c.getPA().walkTo(1, 0);
					}

				} else if (c.objectX == 3038 && c.objectY == 3956) {
					if (c.absX == 3037) {
						c.getPA().walkTo(1, 0);
					} else if (c.absX == 3038) {
						c.getPA().walkTo(-1, 0);
					}
				} else if (c.objectX == 3041 && c.objectY == 3959) {
					if (c.absY == 3960) {
						c.getPA().walkTo(0, -1);
					} else if (c.absY == 3959) {
						c.getPA().walkTo(0, 1);
					}
				} else if (c.objectX == 3191 && c.objectY == 3963) {
					if (c.absY == 3963) {
						c.getPA().walkTo(0, -1);
					} else if (c.absY == 3962) {
						c.getPA().walkTo(0, 1);
					}
				} else if (c.objectX == 3190 && c.objectY == 3957) {
					if (c.absY == 3957) {
						c.getPA().walkTo(0, 1);
					} else if (c.absY == 3958) {
						c.getPA().walkTo(0, -1);
					}
				}
			} else {
				c.sendMessage("I need a lockpick to pick this lock.");
			}
			break;
		case 7814:
			if (c.playerMagicBook == 0) {
				c.playerMagicBook = 1;
				c.setSidebarInterface(6, 838);
				c.sendMessage("An ancient wisdomin fills your mind.");
			} else if (c.playerMagicBook == 1) {
				c.sendMessage("You switch to the lunar spellbook.");
				c.setSidebarInterface(6, 29999);
				c.playerMagicBook = 2;
			} else if (c.playerMagicBook == 2) {
				c.setSidebarInterface(6, 938);
				c.playerMagicBook = 0;
				c.sendMessage("You feel a drain on your memory.");
			}
			break;
		case 11731:
			c.getThieving().steal(Stall.Gem, objectType, location);
			break;
		case 17010:
			if (c.playerMagicBook == 0) {
				c.sendMessage("You switch spellbook to lunar magic.");
				c.setSidebarInterface(6, 838);
				c.playerMagicBook = 2;
				c.autocasting = false;
				c.autocastId = -1;
				c.getPA().resetAutocast();
				break;
			}
			
			if (c.playerMagicBook == 1) {
				c.sendMessage("You switch spellbook to lunar magic.");
				c.setSidebarInterface(6, 29999);
				c.playerMagicBook = 2;
				c.autocasting = false;
				c.autocastId = -1;
				c.getPA().resetAutocast();
				break;
			}
			if (c.playerMagicBook == 2) {
				c.setSidebarInterface(6, 938);
				c.playerMagicBook = 0;
				c.autocasting = false;
				c.sendMessage("You feel a drain on your memory.");
				c.autocastId = -1;
				c.getPA().resetAutocast();
				break;
			}
			break;
			// Boxing mat txt
		case 13129: 
			c.turnPlayerTo(obX, obY);
			c.getDH().sendDialogues(77700, -1);
			//c.sendMessage("I'm really not about that life. I should come back...");
			break;
		case 16670: 
			c.sendMessage("I don't think there's anything up there yet.");
			break;
		/*
		 * One stall that will give different amount of money depending on your thieving level, also different amount of xp.
		 */
		case 2781:
		case 26814:
		case 11666:
		case 3044:
		case 16469:
		case 2030:
		case 6189:
		case 24009:
		case 26300:
			c.getSmithing().sendSmelting();
			break;
			
			/**
		 * Opening the bank.
		 */
		case 24101:
		case 14367:
		case 11758:
		case 10517:
		case 26972:
		case 25808:
		case 11744:
		case 11748:
		case 10060:
		case 24347:
		case 16700:
			c.sendAudio(1877);
			c.getPA().openUpBank();
			break;

		}
		try {
			Server.getEventHandler().stop("click-object");
			Server.getEventHandler().submit(SecondClickNodeActionListener.onClick(c,objectType,obX,obY,c.heightLevel));
		} catch (NullPointerException e) {
			try {
				c.getAttributes().getActionController().submit(SecondClickNodeActionFactory.getAction(c,obX,obY,objectType));
			} catch (NullPointerException e1) {
				c.sendMessage("Nothing interesting happens.");
			}
		}
	}
}