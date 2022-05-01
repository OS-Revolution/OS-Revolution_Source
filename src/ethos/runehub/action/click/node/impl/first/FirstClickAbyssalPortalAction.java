package ethos.runehub.action.click.node.impl.first;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;

import java.util.Arrays;
import java.util.stream.Stream;

public class FirstClickAbyssalPortalAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {
        try {
            this.canTeleportToAltar(this.getNodeId());
            if (this.getNodeId() == 25378) {
                this.getActor().getPA().spellTeleport(2841, 4829, 0, false);
            } else if (this.getNodeId() == 25379) {
                this.getActor().getPA().spellTeleport(2793, 4828, 0, false);
            } else if (this.getNodeId() == 25376) {
                this.getActor().getPA().spellTeleport(2726, 4832, 0, false);
            } else if (this.getNodeId() == 24972) {
                this.getActor().getPA().spellTeleport(2655, 4830, 0, false);
            } else if (this.getNodeId() == 24971) {
                this.getActor().getPA().spellTeleport(2574, 4849, 0, false);
            } else if (this.getNodeId() == 24973) {
                this.getActor().getPA().spellTeleport(2520, 4833, 0, false);
            } else if (this.getNodeId() == 24974) {
                this.getActor().getPA().spellTeleport(2122, 4833, 0, false);
            } else if (this.getNodeId() == 24976) {
                this.getActor().getPA().spellTeleport(2281, 4837, 0, false);
            } else if (this.getNodeId() == 24975) {
                this.getActor().getPA().spellTeleport(2400, 4835, 0, false);
            } else if (this.getNodeId() == 25034) {
                this.getActor().getPA().spellTeleport(2464, 4818, 0, false);
            } else if (this.getNodeId() == 25035) {
                this.getActor().getPA().spellTeleport(2208, 4830, 0, false);
            } else if(this.getNodeId() == 14892
                    || this.getNodeId() == 14841
                    || this.getNodeId() == 14842
                    || this.getNodeId() == 14843
                    || this.getNodeId() == 14844
                    || this.getNodeId() == 14845
                    || this.getNodeId() == 14846
                    || this.getNodeId() == 14893
                    || this.getNodeId() == 14848
                    || this.getNodeId() == 14894
                    || this.getNodeId() == 14847
            ) {
                this.getActor().getPA().spellTeleport(3115,3168,0,false);
            }
        } catch (Exception e) {
            this.getActor().sendMessage(e.getMessage());
        }
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {

        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

//    private boolean hasTalismanOrTiara(int talismanId, int tiaraId) {
//        return this.getActor().getItems().playerHasItem(talismanId) || this.getActor().getItems().isWearingItem(tiaraId);
//    }

    private boolean hasItem(int itemId) {
        return this.getActor().getItems().playerHasItem(itemId);
    }

    private boolean wearingItem(int itemId) {
        return this.getActor().getItems().isWearingItem(itemId);
    }


    private boolean hasACondition(boolean... conditions) {
        for (boolean b : conditions) if (b) return true;
        return false;
    }


    private void canTeleportToAltar(int altar) {
        switch (altar) {
            case 25378: //air
                Preconditions.checkArgument(this.hasACondition(hasItem(1438), wearingItem(5527)), "The portal does not react to your touch.");
                break;
            case 25379: //mind
                Preconditions.checkArgument(this.hasACondition(hasItem(1448), wearingItem(5529)), "The portal does not react to your touch.");
                break;
            case 25376://water
                Preconditions.checkArgument(this.hasACondition(hasItem(1444), wearingItem(5531)), "The portal does not react to your touch.");
                break;
            case 24972://earth
                Preconditions.checkArgument(this.hasACondition(hasItem(1440), wearingItem(5535)), "The portal does not react to your touch.");
                break;
            case 24971://fire
                Preconditions.checkArgument(this.hasACondition(hasItem(1442), wearingItem(5537)), "The portal does not react to your touch.");
                break;
            case 24973://body
                Preconditions.checkArgument(this.hasACondition(hasItem(1446), wearingItem(5533)), "The portal does not react to your touch.");
                break;
            case 24974://cosmic
                Preconditions.checkArgument(this.hasACondition(hasItem(1454), wearingItem(5539)), "The portal does not react to your touch.");
                break;
            case 24976://chaos
                Preconditions.checkArgument(this.hasACondition(hasItem(1452), wearingItem(5543)), "The portal does not react to your touch.");
                break;
            case 24975://nature
                Preconditions.checkArgument(this.hasACondition(hasItem(1462), wearingItem(5541)), "The portal does not react to your touch.");
                break;
            case 25034://law
                Preconditions.checkArgument(this.hasACondition(hasItem(1458), wearingItem(5545)), "The portal does not react to your touch.");
                break;
            case 25035://death
                Preconditions.checkArgument(this.hasACondition(hasItem(1456), wearingItem(5547)), "The portal does not react to your touch.");
                break;
        }
    }


    public FirstClickAbyssalPortalAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 4, nodeId, nodeX, nodeY);
    }
}
