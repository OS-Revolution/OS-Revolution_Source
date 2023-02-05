package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.construction.Hotspot;
import ethos.runehub.skill.combat.magic.MagicTablet;
import ethos.runehub.skill.combat.magic.MagicTabletCache;
import ethos.runehub.skill.combat.magic.MagicTabletDAO;
import ethos.runehub.skill.combat.magic.action.CreateMagicTabletSkillAction;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

public class MagicTabletCreationUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Create Magic Tablets");
        listTitleLabel.setText("Available Tablets");
        rewardLabel.setText("Required Materials");
        this.drawListItems();
        this.updateUI();
    }

    private void drawListItems() {
        final List<MagicTablet> tablets = MagicTabletDAO.getInstance().getAllEntries();
        for (int i = 0; i < tablets.size(); i++) {
            MagicTablet tablet = MagicTabletCache.getInstance().read(tablets.get(i).getItemId());
            if (tablet.getLectern()[this.getExistingLecternIndex()]) {
                listItemLabels[i].setText(ItemIdContextLoader.getInstance().read(tablet.getItemId()).getName());
                listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(tablet));
            }
        }
    }

    private void drawSelectedIndex(MagicTablet tablet) {
        infoH1Label.setText(ItemIdContextLoader.getInstance().read(tablet.getItemId()).getName());
        infoH2Label.setText(ItemIdContextLoader.getInstance().read(tablet.getItemId()).getExamine());
        infoLine1Label.setText("Magic Level: " + (hasLevel(tablet) ? MarkupUtils.highlightText(MarkupUtils.GREEN, String.valueOf(tablet.getLevel())) : MarkupUtils.highlightText(MarkupUtils.RED, String.valueOf(tablet.getLevel()))));
        infoLine2Label.setText("Magic XP: " + (NumberFormat.getInstance().format(tablet.getXp())));
        infoLine3Label.setText("Members: " + (isMembers(tablet) ? MarkupUtils.highlightText(MarkupUtils.GREEN,RunehubUtils.getBooleanAsYesOrNo(tablet.isMembers()))
                : MarkupUtils.highlightText(MarkupUtils.RED,RunehubUtils.getBooleanAsYesOrNo(tablet.isMembers()))));
        this.drawItems(tablet);
        this.drawOption(tablet);
        this.updateUI();
    }

    private void drawItems(MagicTablet tablet) {
        this.clearItems();
        for (int i = 0; i < tablet.getMaterials().length; i++) {
            items[i] = tablet.getMaterials()[i];
        }
    }

    private void drawOption(MagicTablet tablet) {
        optionOneLabel.setText("");
        if (this.hasRequirements(tablet)) {
            optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Craft All"));
            this.registerButton(actionEvent -> create(tablet), 150111);
        }
    }

    private boolean hasMaterial(MagicTablet tablet) {
        for (int i = 0; i < tablet.getMaterials().length; i++) {
            GameItem item = new GameItem(tablet.getMaterials()[i].getId(), tablet.getMaterials()[i].getAmount());
            if (!this.getPlayer().getItems().playerHasItem(item.getId(), item.getAmount())) {
                return false;
            }
        }
        return true;
    }

    private boolean hasLevel(MagicTablet tablet) {
        return this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.MAGIC.getId()) >= tablet.getLevel();
    }

    private boolean hasRequirements(MagicTablet tablet) {
        return hasMaterial(tablet) && hasLevel(tablet) && isMembers(tablet);
    }

    private boolean isMembers(MagicTablet tablet) {
        return (tablet.isMembers() == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

    private void create(MagicTablet tablet) {
        this.getPlayer().getSkillController().getMagic().train(new CreateMagicTabletSkillAction(this.getPlayer(), tablet));
        this.close();
    }

    private int getExistingLecternIndex() {
        for (int i = 0; i < Hotspot.LECTERN.getBuiltNodeId().length; i++) {
            if (Hotspot.LECTERN.getBuiltNodeId()[i] == this.getPlayer().getContext().getPlayerSaveData().getLecternHotspot()) {
                return i;
            }
        }
        return -1;
    }

    public MagicTabletCreationUI(Player player) {
        super(player);
    }
}
