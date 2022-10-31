package ethos.runehub.action.click.item.consumable.booster;

import ethos.model.players.Player;
import ethos.runehub.action.click.item.ClickConsumableItemAction;
import ethos.runehub.world.WorldSettingsController;

public class EfficiencyBooster extends ClickConsumableItemAction {

    @Override
    protected void consume() {
        WorldSettingsController.getInstance().addSkillEfficiency(this.getActor(),time,skillId);
    }

    public EfficiencyBooster(Player attachment, int itemId, int itemAmount, int itemSlot, int time, int skillId) {
        super(attachment, 1, itemId, itemAmount, itemSlot);
        this.time = time;
        this.skillId = skillId;
    }

    private final int time, skillId;
}
