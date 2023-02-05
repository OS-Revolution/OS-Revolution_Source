package ethos.runehub.skill.artisan.herblore.action;

import ethos.Server;
import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.artisan.ArtisanSkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.util.Misc;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

public class MixUnfinishedPotionAction extends ArtisanSkillAction {

    @Override
    protected void onActionStart() {
        this.getActor().getPA().sendFrame164(1743);
        this.getActor().getPA().sendFrame246(13716, 190, this.getReaction().getProductItemId());
        this.getActor().getPA().sendFrame126("\\n\\n\\n\\n\\n" + ItemAssistant.getItemName(this.getReaction().getProductItemId()) + "", 13717);
        this.getActor().getPA().sendFrame126("How many would you like to make?", 13721);
        if (new Rectangle(new Point(this.getActor().absX - 3,this.getActor().absY -3),new Point(this.getActor().absX + 3,this.getActor().absY +3)).getAllPoints()
                .stream().anyMatch(point -> Server.getGlobalObjects().exists(878,point.getX(),point.getY()))) {
            this.getActor().getAttributes().setSkillStationId(878);
        }
//        for (int i = 13716; i < 13800; i++) {
//            this.getActor().getPA().sendFrame126("Frame: " + i, i);
//        }
    }

    @Override
    protected void onSuccess() {
        super.onSuccess();
        if (Misc.random(100) <= 5 && this.getActor().getAttributes().getSkillStationId() == 878) {
            this.getActor().getItems().addItemToBank(this.getReaction().getProductItemId(),1);
            this.getActor().sendMessage("You mix such a potent potion thanks to the well you fill a second vial.");
        }
    }

    @Override
    protected void validateWorldRequirements() {

    }

    public MixUnfinishedPotionAction(Player actor, ArtisanSkillItemReaction reaction, ItemInteractionContext context) {
        super(actor, 15, 3, reaction, context);
        this.setAnimationId(363);
    }

}
