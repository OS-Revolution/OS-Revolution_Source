package ethos.model.players.packets.objectoptions;

import ethos.Server;
import ethos.model.players.Player;
import ethos.model.players.Right;
import ethos.runehub.entity.player.action.FirstClickNodeActionFactory;
import ethos.runehub.entity.player.action.FourthClickNodeActionFactory;
import ethos.runehub.loot.RewardCodeController;

public class ObjectOptionFour {

    public static void handleOption(final Player c, int objectType, int obX, int obY) {
        System.out.println("ObjectOptionFour: " + objectType);
        if (Server.getMultiplayerSessionListener().inAnySession(c)) {
            return;
        }
        c.clickObjectType = 0;

        if (c.getRightGroup().isOrInherits(Right.OWNER))
            c.sendMessage("Clicked Object Option 4:  " + objectType + "");

        switch (objectType) {

            case 8356://streehosidius
                c.getPA().movePlayer(1679, 3541, 0);
                break;
            case 3223:
                RewardCodeController.getInstance().requestCodeEntryFromPlayer(c);
                break;
        }
        try {
            c.getAttributes().getActionController().submit(FourthClickNodeActionFactory.getAction(c,obX,obY,objectType));
        } catch (NullPointerException e1) {
            c.sendMessage("Nothing interesting happens.");
        }
    }

}
