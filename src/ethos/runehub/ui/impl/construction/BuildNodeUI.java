package ethos.runehub.ui.impl.construction;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.construction.Hotspot;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.util.SkillDictionary;

public class BuildNodeUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Build Hotspot");
        listTitleLabel.setText("Available Nodes");
        rewardLabel.setText("Construction Materials");
        this.drawListItems();

        this.updateUI();
    }

    private void drawListItems() {
        for (int i = 0; i < hotspot.getAvailableNodes().length; i++) {
            int index = i;
            listItemLabels[i].setText(hotspot.getAvailableNodes()[i]);
            listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(index));
        }
    }

    private void drawSelectedIndex(int index) {
        infoH1Label.setText(hotspot.getAvailableNodes()[index]);
        infoH2Label.setText(hotspot.getDescription()[index]);
        infoLine1Label.setText("Construction Level: " + hotspot.getLevel()[index]);
        infoLine2Label.setText("XP for building: " + hotspot.getXp()[index]);
        this.drawItems(index);
        this.drawOption(index);
        this.updateUI();
    }

    private void drawItems(int index) {
        this.clearItems();
        for (int i = 0; i < hotspot.getConstructionCost()[index].length; i++) {
            items[i] = hotspot.getConstructionCost()[index][i];
        }
    }

    private void drawOption(int index) {
        optionOneLabel.setText("");
        for (int i = 0; i < hotspot.getConstructionCost()[index].length; i++) {
            GameItem item = new GameItem(hotspot.getConstructionCost()[index][i].getId(),hotspot.getConstructionCost()[index][i].getAmount());
            if (this.getPlayer().getItems().playerHasItem(item.getId(),item.getAmount())) {
                if (this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.CONSTRUCTION.getId()) >= hotspot.getLevel()[index]) {
                    optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Build"));
                    this.registerButton(actionEvent -> build(index), 150111);
                }
            } else {
                break;
            }
        }
    }

    private boolean hasMaterial(int index) {
        for (int i = 0; i < hotspot.getConstructionCost()[index].length; i++) {
            GameItem item = new GameItem(hotspot.getConstructionCost()[index][i].getId(),hotspot.getConstructionCost()[index][i].getAmount());
            if (!this.getPlayer().getItems().playerHasItem(item.getId(),item.getAmount())) {
                return false;
            }
        }
        return true;
    }

    private void build(int index) {
        if (this.hasMaterial(index)) {
            if (this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.CONSTRUCTION.getId()) >= hotspot.getLevel()[index]) {
                this.getPlayer().sendMessage("You build the " + hotspot.getAvailableNodes()[index]);
                for (int i = 0; i < hotspot.getConstructionCost()[index].length; i++) {
                    GameItem item = new GameItem(hotspot.getConstructionCost()[index][i].getId(), hotspot.getConstructionCost()[index][i].getAmount());
                    this.getPlayer().getItems().deleteItem(item.getId(), item.getAmount());
                }
                this.getPlayer().getPA().checkObjectSpawn(hotspot.getBuiltNodeId()[index], nodeX, nodeY, face, 10);
                this.close();
                this.getPlayer().getSkillController().addXP(SkillDictionary.Skill.CONSTRUCTION.getId(), hotspot.getXp()[index]);
                if (hotspot == Hotspot.LECTERN) {
                    this.getPlayer().getContext().getPlayerSaveData().setLecternHotspot(hotspot.getBuiltNodeId()[index]);
                }
            } else {
                this.getPlayer().sendMessage("You need a $" + RunehubUtils.getSkillName(SkillDictionary.Skill.CONSTRUCTION.getId()) + " level of at least $" +
                        hotspot.getLevel()[index] + ".");
            }
        } else {
            this.getPlayer().sendMessage("You do not have the required materials.");
        }
    }

    public BuildNodeUI(Player player, Hotspot hotspot,int nodeX, int nodeY, int face) {
        super(player);
        this.hotspot = hotspot;
        this.nodeX = nodeX;
        this.nodeY = nodeY;
        this.face = face;
    }

    private final int nodeX,nodeY,face;
    private final Hotspot hotspot;
}
