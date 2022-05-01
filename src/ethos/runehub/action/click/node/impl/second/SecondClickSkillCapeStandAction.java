package ethos.runehub.action.click.node.impl.second;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.HallOfHeroesUtils;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.dialog.DialogSequence;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.util.logging.Logger;

public class SecondClickSkillCapeStandAction extends ClickNodeAction {

    private final static int CURRENCY = 995;
    private final static int COST = 99000;

    @Override
    protected void validate() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(CURRENCY, COST), "You need #" + HallOfHeroesUtils.STANDARD_CAPE_COST + " @" + CURRENCY + " to purchase this.");
        Preconditions.checkArgument(this.getActor().getItems().freeSlots() > 2, "You need at least #" + 2 + " free inventory slots.");
        Preconditions.checkArgument(this.getActor().getSkillController().getLevel(HallOfHeroesUtils.getSkillId(this.getNodeId())) >= 99, "You need #" + 99 + " $" + SkillDictionary.getSkillNameFromId(HallOfHeroesUtils.getSkillId(this.getNodeId())) + " to purchase this.");
    }

    @Override
    protected void onActionStart() {


    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                .addNpcChat(3953,
                        "Are you interested in purchasing that?",
                        "Only 99,000 Coins and it all goes to the guild!"
                )
                .addOptions(2, "Yes", "No")
                .build());
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    public SecondClickSkillCapeStandAction(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }
}
