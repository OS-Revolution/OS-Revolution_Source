package ethos.runehub.skill.artisan.fletching.ui;

import ethos.model.players.Player;
import ethos.runehub.skill.artisan.fletching.Fletchable;
import ethos.runehub.skill.artisan.fletching.FletchingSkillMenuUI;
import ethos.runehub.skill.artisan.fletching.action.FletchBoltTipsAction;
import ethos.runehub.skill.artisan.fletching.action.FletchProjectileAction;

public class FletchBoltTipsSkillMenuUI extends FletchingSkillMenuUI {

    @Override
    protected void create(Fletchable item, int actions) {
        this.close();
        this.getPlayer().getSkillController().getFletching().train(new FletchBoltTipsAction(this.getPlayer(),item,actions));
    }

    public FletchBoltTipsSkillMenuUI(Player player, int baseId) {
        super(player, baseId);
    }
}
