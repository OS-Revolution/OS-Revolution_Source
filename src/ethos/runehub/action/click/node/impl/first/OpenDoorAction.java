package ethos.runehub.action.click.node.impl.first;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.entity.node.door.DoorController;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class OpenDoorAction extends ClickNodeAction {

    @Override
    protected void validateNode() {

    }

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if(this.getNodeX() == 3097 && this.getNodeY() == 3227) {
            if (this.getActor().getContext().getPlayerSaveData().isHomeUnlocked()) {
                DoorController.getInstance().openDoorForPlayer(this.getActor(), this.getNodeId(), this.getNodeX(), this.getNodeY());
            } else {
                this.getActor().sendMessage("The door is locked.");
            }
        } else if(this.getNodeId() == 4964) {
            if (Arrays.stream(SkillDictionary.Skill.values())
                    .noneMatch(skill -> this.getActor().getSkillController().getLevel(skill.getId()) >= 99)) {
                this.getActor().sendMessage("Only Heroes maye enter the $Hall $of $Heroes (Please return when you have at least one $99 )");
            } else {
                DoorController.getInstance().openDoorForPlayer(this.getActor(), this.getNodeId(), this.getNodeX(), this.getNodeY());
            }
        }else if(this.getNodeId() == 2100) {
            DoorController.getInstance().openDoorForPlayer(this.getActor(), this.getNodeId(), this.getNodeX(), this.getNodeY());
//            if (this.getActor().absY == 3554)
//                this.getActor().getPA().walkTo(0, 1);
//            else
//                this.getActor().getPA().walkTo(0, -1);
//            DoorController.getInstance().closeDoorForPlayer(this.getActor(), this.getNodeId(), this.getNodeX(), this.getNodeY());
        } else {
            DoorController.getInstance().openDoorForEveryone(this.getActor(), this.getNodeId(), this.getNodeX(), this.getNodeY());
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public OpenDoorAction(Player attachment, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(attachment, 1, nodeId, nodeX, nodeY);
        this.nodeZ = nodeZ;
    }

    private final int nodeZ;
}
