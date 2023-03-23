package ethos.runehub.skill.support.firemaking.action;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.clip.WorldObject;
import ethos.model.players.Player;
import ethos.runehub.skill.node.impl.support.SupportNode;
import ethos.runehub.skill.support.SupportSkillAction;
import ethos.util.Misc;
import ethos.world.objects.GlobalObject;
import org.runehub.api.util.SkillDictionary;

import java.util.Optional;

public class LightBurnerAction extends SupportSkillAction {

    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(896);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().getItems().deleteItem(251,1);
        Server.getGlobalObjects().add(new GlobalObject(13213,nodeX,nodeY,nodeZ,0,10,this.getDurationInTicks(),13212));
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {

    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(251),"You need a @" + 251 + " to light this.");
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(590),"You need a @" + 590 + " to light this.");
    }

    @Override
    protected void validateWorldRequirements() {
        Preconditions.checkArgument(
                Region.getWorldObject(13212,nodeX,nodeY,nodeZ).isPresent(),
                "You can not light this right now.");
    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void onEvent() {

    }

    private int getDurationInTicks() {
        return 200 + this.getActor().getSkillController().getLevel(this.getSkillId()) + Misc.random(this.getActor().getSkillController().getLevel(this.getSkillId())) - 1;
    }

    public LightBurnerAction(Player actor, int nodeId, int nodeX, int nodeY, int nodeZ) {
        super(actor, SkillDictionary.Skill.FIREMAKING.getId(), 3, new SupportNode(nodeId,1,0, SkillDictionary.Skill.FIREMAKING.getId()));
        this.nodeX = nodeX;
        this.nodeY = nodeY;
        this.nodeZ = nodeZ;
    }

    private final int nodeX, nodeY, nodeZ;
}
