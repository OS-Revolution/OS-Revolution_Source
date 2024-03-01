package ethos.runehub.skill.artisan.herblore.action;

import com.google.common.base.Preconditions;

import ethos.model.content.achievement.AchievementType;
import ethos.model.content.achievement.Achievements;
import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.model.players.skills.herblore.Herb;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillAction;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.SkillDictionary;

import java.util.Arrays;

public class CleanHerbAction extends SkillAction {


    @Override
    protected void onActionStart() {
        System.out.println("Start");
//        this.getActor().getPA().sendFrame164(8880);
//        this.getActor().getPA().sendFrame126("What would you like to make?", 8879);
//        this.getActor().getPA().sendFrame246(8883, 190, -1);
//        this.getActor().getPA().sendFrame246(8884, 190, herb.getClean());
//        this.getActor().getPA().sendFrame246(8885, 190, -1);
//        this.getActor().getPA().sendFrame126(null, 8889);
//        this.getActor().getPA().sendFrame126(ItemAssistant.getItemName(herb.getClean()), 8893);
//        this.getActor().getPA().sendFrame126(null, 8897);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.addItems(-1, 1);
        this.addXp(herb.getExperience());
		Achievements.increase(this.getActor(), AchievementType.HERB, 1);

    }

    @Override
    protected void onUpdate() {

    }

    @Override
    protected void validateInventory() {

    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getLevel(this.getSkillId()) >= herb.getLevel()),
                "You need a "
                        + SkillDictionary.getSkillNameFromId(this.getSkillId())
                        + " level of at least #"
                        + herb.getLevel()
                        + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(this.getActor().getItems().playerHasItem(herb.getGrimy()), "You've run out of grimy herbs.");
    }

    @Override
    protected void validateWorldRequirements() {

    }

    @Override
    protected void updateAnimation() {

    }

    @Override
    protected void addItems(int id, int amount) {
        this.getActor().getItems().addItem(herb.getClean(), amount);
        this.getActor().getItems().deleteItem(herb.getGrimy(), amount);
    }

    public CleanHerbAction(Player actor, int herbId) {
        super(actor, 15, 1);
        this.herb = Arrays.stream(Herb.values()).filter(h -> h.getGrimy() == herbId).findAny().orElse(null);
    }

    //    private int amountSelected;
    private final Herb herb;
}
