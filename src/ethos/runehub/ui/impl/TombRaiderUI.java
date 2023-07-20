package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.instance.impl.tomb.TombInstanceController;
import ethos.runehub.content.rift.RiftDifficulty;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryCache;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

public class TombRaiderUI extends SelectionParentUI{

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Tomb Raider");
        rewardLabel.setText("Entry Fee");
        listTitleLabel.setText("Difficulty");
        this.drawListItems();
        this.updateUI();
    }

    private void drawListItems() {
        for (int i = 0; i < RiftDifficulty.values().length; i++) {
            RiftDifficulty difficulty = RiftDifficulty.values()[i];
            listItemLabels[i].setText(difficulty.toString());
            listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(difficulty));
        }
    }

    private void drawSelectedIndex(RiftDifficulty difficulty) {
        infoH1Label.setText(difficulty.toString());
        infoH2Label.setText("Reward Bonus: " + difficulty.getRewardBonus() + "%");
        infoLine1Label.setText("Loot Rolls: " + Math.min(1,(difficulty.getRewardBonus() / 100)));
        infoLine2Label.setText("Magic Find bonus: " + Math.max(0.99,(difficulty.getRewardBonus() / 75) / 10));
        infoLine3Label.setText("Members: " + (isMembers(difficulty) ? MarkupUtils.highlightText(MarkupUtils.GREEN, RunehubUtils.getBooleanAsYesOrNo(difficulty.isMembers()))
                : MarkupUtils.highlightText(MarkupUtils.RED, RunehubUtils.getBooleanAsYesOrNo(difficulty.isMembers()))));
        infoLine4Label.setText("Enemy Health Bonus: " + (difficulty.getRewardBonus() * 0.5) + "%");
        infoLine5Label.setText("Enemy Stats Bonus: " + (difficulty.getRewardBonus() * 0.25) + "%");
        this.drawItems(difficulty);
        this.drawOption(difficulty);
        this.updateUI();
    }

    private void drawOption(RiftDifficulty jewellery) {
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        if (this.hasRequirements(jewellery)) {
            optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Start"));
            this.registerButton(actionEvent -> {
                if (TombInstanceController.getInstance().createTombRaiderPortal(getPlayer(),nodeX,nodeY,jewellery))
                    this.close();
            }, 150111);
        }
    }

    private void drawItems(RiftDifficulty jewellery) {
        this.clearItems();
        for (int i = 0; i < jewellery.getEntryFee().length; i++) {
            items[i] = jewellery.getEntryFee()[i];
        }
    }

    private boolean hasRequirements(RiftDifficulty jewellery) {
        return hasMaterial(jewellery) && isMembers(jewellery);
    }

    protected boolean hasMaterial(RiftDifficulty jewellery) {
        for (int i = 0; i < jewellery.getEntryFee().length; i++) {
            GameItem item = new GameItem(jewellery.getEntryFee()[i].getId(), jewellery.getEntryFee()[i].getAmount());
            if (!this.getPlayer().getItems().playerHasItem(item.getId(), item.getAmount())) {
                return false;
            }
        }
        return true;
    }

    private boolean isMembers(RiftDifficulty jewellery) {
        return (jewellery.isMembers() == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

    public TombRaiderUI(Player player, int nodeX, int nodeY) {
        super(player);
        this.nodeX = nodeX;
        this.nodeY = nodeY;
    }

    private int nodeX, nodeY;
}
