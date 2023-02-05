package ethos.runehub.skill.artisan.crafting.action;

import com.google.common.base.Preconditions;
import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

public class CutGemAction extends SkillAction {

    @Override
    protected void onActionStart() {
//        this.getActor().getPA().sendFrame164(1743);
//        this.getActor().getPA().sendFrame246(13716, 150, this.getReaction().getProductItemId());
//        this.getActor().getPA().sendFrame126("\\n\\n\\n\\n\\n" + ItemAssistant.getItemName(this.getReaction().getProductItemId()) + "", 13717);
//        this.getActor().getPA().sendFrame126("How many would you like to make?", 13721);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().startAnimation(this.getAnimation());
        if (this.isSuccessful(reaction.getLow(),reaction.getHigh())) {
            this.getActor().getItems().deleteItem(gemId,1);
            this.getActor().getItems().addItem(reaction.getProductItemId(),1);
            this.getActor().getSkillController().addXP(this.getSkillId(),reaction.getReactionXp());
        } else {
            this.getActor().getItems().deleteItem(gemId,1);
            this.getActor().getItems().addItem(reaction.getFailedItemId(),1);
            if (gemId == 1625) {
                this.getActor().getSkillController().addXP(this.getSkillId(),4);
            } else if (gemId == 1627) {
                this.getActor().getSkillController().addXP(this.getSkillId(),5);
            } else if (gemId == 1629) {
                this.getActor().getSkillController().addXP(this.getSkillId(),6);
            } else {
                this.getActor().getSkillController().addXP(this.getSkillId(),1);
            }
        }
    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {

    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= reaction.getLevelRequired()),
                "You need a "
                        + RunehubUtils.getSkillName(this.getSkillId())
                        + " level of at least #"
                        + reaction.getLevelRequired()
                        + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(gemId), "You've run out of @" + gemId);
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(1755), "You need a chisel");
    }

    @Override
    protected void validateWorldRequirements() {

    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void addItems(int id, int amount) {

    }

    private int getAnimation() {
        switch (reaction.getProductItemId()) {
            case 1609:
            case 1611:
            case 1601:
                return 886;
            case 1613:
            case 1603:
                return 887;
            case 1607:
                return 888;
            case 1605:
                return 889;
            case 1615:
                return 885;
            case 6573:
                return 2717;
        }
        return 886;
    }

    public CutGemAction(Player actor, ArtisanSkillItemReaction reaction, ItemInteractionContext context) {
        super(actor, SkillDictionary.Skill.CRAFTING.getId(), 2);
        this.reaction = reaction;
        this.context = context;
        this.gemId = context.getUsedId() == 1755 ? context.getUsedWithId() : context.getUsedId();
    }

    private int gemId;
    private final ArtisanSkillItemReaction reaction;
    private final ItemInteractionContext context;
}
