package ethos.runehub.skill.artisan.smithing.action;

import com.google.common.base.Preconditions;

import ethos.model.content.achievement.AchievementType;
import ethos.model.content.achievement.Achievements;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.runehub.skill.artisan.smithing.Smeltable;
import ethos.util.PreconditionUtils;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.util.logging.Logger;

public class SmeltAction extends SkillAction {

    @Override
    protected void onActionStart() {
        this.updateAnimation();
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        if (iterations == 0) {
            this.stop();
            return;
        }
        this.updateAnimation();
        this.getActor().getItems().deleteItem(smeltable.getPrimaryOre(), smeltable.getPrimaryOreAmount());
        this.getActor().getItems().deleteItem(smeltable.getSecondaryOre(), smeltable.getSecondaryOreAmount());
        if (this.isSuccessful(smeltable.getLowRoll(), smeltable.getHighRoll()) || this.getActor().getItems().isWearingItem(2568)) {
            this.getActor().getItems().addItem(smeltable.getProductId(), getAmountSmelted(smeltable.getProductAmount()));
            if (this.getActor().getItems().isWearingItem(776) && smeltable == Smeltable.GOLD) {
                this.addXp((int) (smeltable.getXpPerAction() * 2.5));
            } else {
                this.addXp(smeltable.getXpPerAction());
                
            }
            this.getActor().sendMessage("You successfully smelt the @" + smeltable.getProductId());
        } else {
            this.getActor().sendMessage("You fail to smelt the @" + smeltable.getPrimaryOre());
        }
        iterations--;
		Achievements.increase(this.getActor(), AchievementType.MINE, 1);

    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= smeltable.getLevelRequired()),
                "You need a "
                        + SkillDictionary.getSkillNameFromId(this.getSkillId())
                        + " level of at least #"
                        + smeltable.getLevelRequired()
                        + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(smeltable.getPrimaryOre(), smeltable.getPrimaryOreAmount()),
                "You need at least #" + smeltable.getPrimaryOreAmount() + " @" + smeltable.getPrimaryOre() + " to smelt "
                        + RunehubUtils.getIndefiniteArticle(ItemIdContextLoader.getInstance().read(smeltable.getProductId()).getName())
                        + " @" + smeltable.getProductId());
        if (smeltable.getSecondaryOre() != -1)
            Preconditions.checkArgument(this.getActor().getItems().playerHasItem(smeltable.getSecondaryOre(), smeltable.getSecondaryOreAmount()),
                    "You need at least #" + smeltable.getSecondaryOreAmount() + " @" + smeltable.getSecondaryOre() + " to smelt "
                            + RunehubUtils.getIndefiniteArticle(ItemIdContextLoader.getInstance().read(smeltable.getProductId()).getName())
                            + " @" + smeltable.getProductId());
    }

    @Override
    protected void validateWorldRequirements() {
    }

    @Override
    protected void updateAnimation() {
        this.getActor().startAnimation(899);
    }

    @Override
    protected void addItems(int id, int amount) {
    }

    private int getAmountSmelted(int baseAmount) {
        int amount = baseAmount;
        if (Skill.SKILL_RANDOM.nextInt(100) <= 10) {
            if (this.getActor().getItems().isWearingItem(13104) && smeltable.ordinal() <= Smeltable.STEEL.ordinal()
                    || this.getActor().getItems().isWearingItem(13105) && smeltable.ordinal() <= Smeltable.MITHRIL.ordinal()
                    || this.getActor().getItems().isWearingItem(13106) && smeltable.ordinal() <= Smeltable.ADAMANTITE.ordinal()
                    || this.getActor().getItems().isWearingItem(13107) && smeltable.ordinal() <= Smeltable.RUNITE.ordinal()
            ) {
                amount = baseAmount * 2;
            }
        }
        return amount;
    }

    public SmeltAction(Player actor, int ticks, Smeltable smeltable,int iterations){
            super(actor, SkillDictionary.Skill.SMITHING.getId(), ticks);
            this.smeltable = smeltable;
            this.iterations = iterations;
        }

        private int iterations;
        private final Smeltable smeltable;
    }
