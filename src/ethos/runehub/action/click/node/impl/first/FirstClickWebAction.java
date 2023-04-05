package ethos.runehub.action.click.node.impl.first;

import ethos.Server;
import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.entity.item.equipment.Equipment;
import ethos.runehub.entity.item.equipment.EquipmentCache;
import ethos.util.Misc;
import ethos.world.objects.GlobalObject;

public class FirstClickWebAction extends ClickNodeAction {


    @Override
    protected void onActionStart() {
        if (this.getChance() != 0) {
            this.getActor().startAnimation(451);
        } else {
            this.getActor().sendMessage("You need something sharp to slash through this.");
            this.stop();
        }

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        final int roll = Misc.random(100);

        if (this.getChance() >= roll) {
            int face = Region.getWorldObject(this.getNodeId(),this.getNodeX(),this.getNodeY(),this.getActor().heightLevel).get().face;
            Server.getGlobalObjects().add(new GlobalObject(this.getNodeId() + 1,this.getNodeX(),this.getNodeY(),this.getActor().heightLevel,face,10,50,this.getNodeId()));
            this.getActor().sendMessage("You slash the web apart.");
        } else {
            this.getActor().sendMessage("You fail to cut through it.");
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    private int getChance() {
        int chance = 0;
        int equippedWeapon = this.getActor().playerEquipment[this.getActor().playerWeapon];
        if (this.getActor().getItems().playerHasItem(946)) {
            chance = 50;
        } else if (equippedWeapon > 0 && EquipmentCache.getInstance().read(equippedWeapon).getAttackBonus()[Equipment.SLASH_BONUS] >= 100) {
            chance = 100;
        } else if (equippedWeapon > 0 && EquipmentCache.getInstance().read(equippedWeapon).getAttackBonus()[Equipment.SLASH_BONUS] >= 1) {
            chance = 20;
        }
        return chance;
    }

    public FirstClickWebAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 3, nodeId, nodeX, nodeY);
    }
}
