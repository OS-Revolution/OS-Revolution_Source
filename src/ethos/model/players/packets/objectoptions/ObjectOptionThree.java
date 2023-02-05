package ethos.model.players.packets.objectoptions;

import ethos.Server;
import ethos.model.content.tradingpost.Listing;
import ethos.model.players.Player;
import ethos.model.players.Right;
import ethos.runehub.action.click.node.SecondClickNodeActionListener;
import ethos.runehub.entity.merchant.MerchantCache;
import ethos.runehub.entity.player.action.SecondClickNodeActionFactory;
import ethos.runehub.entity.player.action.ThirdClickNodeActionFactory;

/*
 * @author Matt
 * Handles all 3rd options for objects.
 */

public class ObjectOptionThree {

	public static void handleOption(final Player c, int objectType, int obX, int obY) {
		if (Server.getMultiplayerSessionListener().inAnySession(c)) {
			return;
		}
		c.clickObjectType = 0;
		// c.sendMessage("Object type: " + objectType);
		if (Server.getHolidayController().clickObject(c, 3, objectType, obX, obY)) {
			return;
		}
		
		if (c.getRightGroup().isOrInherits(Right.OWNER))
			c.sendMessage("Clicked Object Option 3:  "+objectType+"");
		
		switch (objectType) {
			case 6945:

				MerchantCache.getInstance().read(2149).openShop(c);
				break;
		case 22819:
		case 24101:
			//if (c.debugMessage) {
			//	Listing.openPost(c, false, true);
			//} else {
			//	c.sendMessage("Trading post is currently disabled until further notice.");
			//}
			Listing.openPost(c, false, true);
			break;
		case 8356://streexerics
			c.getPA().movePlayer(1311, 3614, 0);
			break;
		case 7811:
			if (!c.inClanWarsSafe()) {
				return;
			}
			c.getDH().sendDialogues(818, 6773);
			break;
		}
//		try {
//			Server.getEventHandler().stop("click-object");
//			Server.getEventHandler().submit(ThirdClickNodeActionFactory.onClick(c,objectType,obX,obY,c.heightLevel));
//		} catch (NullPointerException e) {
			try {
				c.getAttributes().getActionController().submit(ThirdClickNodeActionFactory.getAction(c,obX,obY,objectType));
			} catch (NullPointerException e1) {
				c.sendMessage("Nothing interesting happens.");
			}
//		}
	}

}
