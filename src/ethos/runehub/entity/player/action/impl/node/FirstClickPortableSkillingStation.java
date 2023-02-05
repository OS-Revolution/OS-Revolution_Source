package ethos.runehub.entity.player.action.impl.node;

import ethos.Server;
import ethos.clip.Region;
import ethos.model.multiplayer_session.MultiplayerSessionType;
import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.ClickItemAction;
import ethos.world.objects.GlobalObject;

public class FirstClickPortableSkillingStation extends ClickItemAction {

    @Override
    public void perform() {
        if (
                !player.inDuel
                        && player.infernoWaveId <= 0
                        && !player.isDead
                        && player.underAttackBy <= 0
                        && player.underAttackBy2 <= 0
                        && !Server.getMultiplayerSessionListener().inSession(player, MultiplayerSessionType.TRADE)
        ) {
            this.createGlobalObject();
            player.sendMessage("You deploy the skilling station. It will expire in 5 minutes.");
            player.getItems().deleteItem2(itemId,1);

        } else {
            player.sendMessage("You can not deploy this right now.");
        }
    }

    private void createGlobalObject() {
        switch (itemId) {
            case 8554:
                Server.getGlobalObjects().add(new GlobalObject(6799, playerX, playerY, player.heightLevel, 0, 10, 500));
                break;
            case 8528:
                Server.getGlobalObjects().add(new GlobalObject(13542, playerX, playerY, player.heightLevel, 0, 10, 500));
                break;
            case 8530:
                Server.getGlobalObjects().add(new GlobalObject(878, playerX, playerY, player.heightLevel, 0, 10, 500));
                break;
            case 8532:
                Server.getGlobalObjects().add(new GlobalObject(15468, playerX, playerY, player.heightLevel, 0, 10, 500));
                break;
            case 8534:
                Server.getGlobalObjects().add(new GlobalObject(11017, playerX, playerY, player.heightLevel, 0, 10, 500));
                break;
        }

        if (Region.getClipping(player.getX() - 1, player.getY(), player.heightLevel, -1, 0)) {
            player.getPA().walkTo(-1, 0);
        } else if (Region.getClipping(player.getX() + 1, player.getY(), player.heightLevel, 1, 0)) {
            player.getPA().walkTo(1, 0);
        } else if (Region.getClipping(player.getX(), player.getY() - 1, player.heightLevel, 0, -1)) {
            player.getPA().walkTo(0, -1);
        } else if (Region.getClipping(player.getX(), player.getY() + 1, player.heightLevel, 0, 1)) {
            player.getPA().walkTo(0, 1);
        }
    }

    public FirstClickPortableSkillingStation(Player player, int npcX, int npcY, int npcId) {
        super(player, npcX, npcY, npcId);
    }
}
