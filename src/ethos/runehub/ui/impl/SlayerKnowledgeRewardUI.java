package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.crafting.action.CraftJewelleryAction;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryCache;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;
import ethos.runehub.skill.support.slayer.SlayerKnowledgeReward;
import ethos.runehub.skill.support.slayer.SlayerKnowledgeRewardCache;
import ethos.runehub.skill.support.slayer.SlayerKnowledgeRewardDAO;
import org.apache.commons.lang3.text.WordUtils;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

public class SlayerKnowledgeRewardUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Slayer Rewards");
        rewardLabel.setText("Point Cost");
        listTitleLabel.setText("Rewards");
        tabTwoLabel.setText("Slayer Points: " + this.getPlayer().getSlayerSave().getSlayerPoints());
        this.drawListItems();
        this.updateUI();
    }

    @Override
    protected void onClose() {
        super.onClose();
    }

    private void drawListItems() {
        this.clearList();
        final List<SlayerKnowledgeReward> items = SlayerKnowledgeRewardDAO.getInstance().getAllEntries().stream().collect(Collectors.toList());
        for (int i = 0; i < items.size(); i++) {
            SlayerKnowledgeReward item = SlayerKnowledgeRewardCache.getInstance().read(items.get(i).getId());
            listItemLabels[i].setText(this.getPlayer().getSlayerSave().getUnlockedKnowledge()[i] ? MarkupUtils.highlightText(MarkupUtils.GREEN,item.getName())
                    :MarkupUtils.highlightText(MarkupUtils.RED,item.getName()));
            listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(item));
        }
    }

    private void drawSelectedIndex(SlayerKnowledgeReward item) {
        infoH1Label.setText(item.getName());
        String[] desc = WordUtils.wrap(item.getDescription(), 45).split("\n");
        infoH2Label.setText("Description");
        infoLine3Label.setText(desc[0]);
        if (desc.length > 1)
            infoLine4Label.setText(desc[1]);
        infoLine5Label.setText("Point Cost: " + (hasMaterial(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, String.valueOf(item.getCost())) : MarkupUtils.highlightText(MarkupUtils.RED, String.valueOf(item.getCost()))));
        infoLine6Label.setText("Members: " + (isMembers(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))
                : MarkupUtils.highlightText(MarkupUtils.RED, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))));
        this.drawItems(item);
        this.drawOption(item);
        this.updateUI();
    }

    private void drawItems(SlayerKnowledgeReward item) {
        this.clearItems();
        items[0] = new GameItem(13316, item.getCost());
    }

    private void drawOption(SlayerKnowledgeReward item) {
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        if (this.hasMaterial(item) && this.isMembers(item) && !this.getPlayer().getSlayerSave().getUnlockedKnowledge()[item.getId()]) {
            optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Unlock"));
            this.registerButton(actionEvent -> unlock(item), 150111);
        }
    }

    protected boolean hasMaterial(SlayerKnowledgeReward reward) {
        if (this.getPlayer().getSlayerSave().getSlayerPoints() < reward.getCost()) {
            return false;
        }
        return true;
    }

    private boolean isMembers(SlayerKnowledgeReward item) {
        return (item.isMembers() == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

    private void unlock(SlayerKnowledgeReward item) {
        this.getPlayer().getSlayerSave().setSlayerPoints(this.getPlayer().getSlayerSave().getSlayerPoints() - item.getCost());
        this.getPlayer().getSlayerSave().getUnlockedKnowledge()[item.getId()] = true;
        this.getPlayer().sendMessage("You've unlocked the $" + item.getName().replaceAll(" ","_") + " Slayer reward for $" + item.getCost() + " Slayer points.");
        tabTwoLabel.setText("Slayer Points: " + this.getPlayer().getSlayerSave().getSlayerPoints());
        this.drawListItems();
        this.updateUI();
    }

    public SlayerKnowledgeRewardUI(Player player) {
        super(player);
    }
}
