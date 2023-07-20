package ethos.runehub.skill.support.sailing;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.skill.support.sailing.io.PlayerSailingSaveDAO;
import ethos.runehub.skill.support.sailing.io.SailingSaveData;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.voyage.Voyage;

public class SailingController {

    private static SailingController instance = null;

    public static SailingController getInstance() {
        if (instance == null)
            instance = new SailingController();
        return instance;
    }

    private void onlineCompletion(Player player, int slot) {
        player.getSkillController().getSailing().completeVoyage(slot);
        player.sendMessage("^Sailing One of your ships has completed a voyage.");
//        PlayerSailingSaveDAO.getInstance().update(player.getSailingSaveData());
        System.out.println("online");
    }

    private void offlineCompletion(SailingSaveData sailingSaveData, int slot) {
        Ship ship = Ship.fromLong(sailingSaveData.getShipSlot()[slot]);
        ship.completeVoyage(Voyage.fromLong(sailingSaveData.getActiveVoyages()[slot]));
        PlayerSailingSaveDAO.getInstance().update(sailingSaveData);
        System.out.println("offline");
    }

    public void completeVoyage(int slot, long playerId) {
        if (PlayerHandler.getPlayer(playerId).isPresent()) {
            this.onlineCompletion(PlayerHandler.getPlayer(playerId).get(), slot);
        } else {
            this.offlineCompletion(PlayerSailingSaveDAO.getInstance().read(playerId), slot);
        }
    }


    private SailingController() {

    }
}
