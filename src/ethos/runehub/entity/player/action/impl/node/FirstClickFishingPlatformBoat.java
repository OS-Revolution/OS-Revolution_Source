package ethos.runehub.entity.player.action.impl.node;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;
import org.runehub.api.util.SkillDictionary;

public class FirstClickFishingPlatformBoat extends ClickNodeAction {


    @Override
    public void perform() {
        try {
            Preconditions.checkArgument(player.getSkillController().getLevel(SkillDictionary.Skill.FISHING.getId()) >= 70,"You need a $Fishing level of at least $70 to go to the fishing platform.");
            Preconditions.checkArgument(player.getItems().playerHasItem(995,toll),"You need $" + toll + " $coins to use the boat.");
            player.getItems().deleteItem2(995,toll);
            player.getPA().movePlayer(2615, 3440);
            player.sendMessage("You pay the $" + toll + "gp toll and paddle out to the $fishing $platform" );
        } catch (Exception e) {
            player.sendMessage(e.getMessage());
        }
    }

    public FirstClickFishingPlatformBoat(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
        //2615 3440 boat to platform 3081 3211 from platform
    }

    private final int toll = 500;
}
