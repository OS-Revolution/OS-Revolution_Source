package ethos.runehub.action.click.node.impl.first;

import com.google.common.base.Preconditions;
import ethos.Server;
import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.world.objects.GlobalObject;
import org.runehub.api.model.world.Face;
import org.runehub.api.model.world.region.location.Location;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class FirstClickHallofHeroesDoorAction extends ClickNodeAction {

    @Override
    protected void onActionStart() {
//        this.getActor().sendMessage("Welcome to the $Hall $of $Heroes!");
//        Region.getWorldObject(this.getNodeId(), this.getNodeX(), this.getNodeY(), this.getActor().height).get()

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (this.getActor().getY() < 3231) {
            this.getActor().getPA().movePlayer(this.getNodeX(), this.getNodeY() + 1);
            this.getActor().sendMessage("Welcome to the $Hall $of $Heroes!");
        } else {
            this.getActor().getPA().movePlayer(this.getNodeX(), this.getNodeY());
        }
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validate() {
        if (Arrays.stream(SkillDictionary.Skill.values())
                .noneMatch(skill -> this.getActor().getSkillController().getLevel(skill.getId()) >= 99)) {
            Preconditions.checkArgument(false, "Only Heroes maye enter the $Hall $of $Heroes (Please return when you have at least one $99");
        }
    }

    public FirstClickHallofHeroesDoorAction(Player attachment, int nodeX, int nodeY) {
        super(attachment, 1, 16902, nodeX, nodeY);
    }
}
