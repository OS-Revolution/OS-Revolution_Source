package ethos.runehub.skill.artisan.fletching.ui;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.fletching.Fletchable;
import ethos.runehub.skill.artisan.fletching.FletchingSkillMenuUI;
import ethos.runehub.skill.artisan.fletching.action.FletchProjectileAction;
import ethos.runehub.skill.artisan.fletching.action.StringBowAction;
import ethos.runehub.skill.support.slayer.SlayerKnowledgeRewardCache;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

import java.text.NumberFormat;

public class FletchProjectileSkillMenuUI extends FletchingSkillMenuUI {

    @Override
    protected void create(Fletchable item, int actions) {
        this.close();
        this.getPlayer().getSkillController().getFletching().train(new FletchProjectileAction(this.getPlayer(),item,actions));
    }

    protected void drawSelectedIndex(Fletchable item) {
        if (item.getProductId() != 11875 && item.getProductId() != 4160) {
            super.drawSelectedIndex(item);
        } else {
            infoH1Label.setText(ItemIdContextLoader.getInstance().read(item.getProductId()).getName());
            infoH2Label.setText(ItemIdContextLoader.getInstance().read(item.getProductId()).getExamine());
            infoLine1Label.setText("Fletching Level: " + (hasLevel(item.getLevelRequirement()) ? MarkupUtils.highlightText(MarkupUtils.GREEN, String.valueOf(item.getLevelRequirement())) : MarkupUtils.highlightText(MarkupUtils.RED, String.valueOf(item.getLevelRequirement()))));
            infoLine2Label.setText("Fletching XP: " + (NumberFormat.getInstance().format(item.getBaseXp())));
            infoLine3Label.setText("Members: " + (isMembers(item.isMembers()) ? MarkupUtils.highlightText(MarkupUtils.GREEN, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))
                    : MarkupUtils.highlightText(MarkupUtils.RED, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))));
            infoLine4Label.setText("Requirement: " + (this.getPlayer().getSlayerSave().getUnlockedKnowledge()[0] ? MarkupUtils.highlightText(MarkupUtils.GREEN, SlayerKnowledgeRewardCache.getInstance().read(0).getName())
                    : MarkupUtils.highlightText(MarkupUtils.RED, SlayerKnowledgeRewardCache.getInstance().read(0).getName()) ));
            this.drawItems(item);
            this.drawOption(item);
            this.updateUI();
        }

    }

    public FletchProjectileSkillMenuUI(Player player, int baseId) {
        super(player, baseId);
    }
}
