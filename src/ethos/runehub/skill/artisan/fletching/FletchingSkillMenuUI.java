package ethos.runehub.skill.artisan.fletching;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.crafting.action.CraftJewelleryAction;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryCache;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.io.data.impl.beta.BetaAbstractDataAcessObject;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FletchingSkillMenuUI extends SelectionParentUI {

    protected abstract void create(Fletchable item, int actions);

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Fletching");
        rewardLabel.setText("Required Materials");
        this.drawListItems();
        this.updateUI();
    }

    @Override
    protected void onClose() {
        super.onClose();
    }

    private void drawListItems() {
        final List<Fletchable> items = FletchableDAO.getInstance().getAllEntries().stream()
                .filter(fletchable -> Arrays.stream(fletchable.getMaterials()).anyMatch(gameItem -> gameItem.getId() == baseId))
                .collect(Collectors.toList());
        for (int i = 0; i < items.size(); i++) {
            Fletchable item = FletchableCache.getInstance().read(items.get(i).getProductId());
            listItemLabels[i].setText(ItemIdContextLoader.getInstance().read(item.getProductId()).getName());
            listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(item));
        }
    }

    protected void drawSelectedIndex(Fletchable item) {
        infoH1Label.setText(ItemIdContextLoader.getInstance().read(item.getProductId()).getName());
        infoH2Label.setText(ItemIdContextLoader.getInstance().read(item.getProductId()).getExamine());
        infoLine1Label.setText("Fletching Level: " + (hasLevel(item.getLevelRequirement()) ? MarkupUtils.highlightText(MarkupUtils.GREEN, String.valueOf(item.getLevelRequirement())) : MarkupUtils.highlightText(MarkupUtils.RED, String.valueOf(item.getLevelRequirement()))));
        infoLine2Label.setText("Fletching XP: " + (NumberFormat.getInstance().format(item.getBaseXp())));
        infoLine3Label.setText("Members: " + (isMembers(item.isMembers()) ? MarkupUtils.highlightText(MarkupUtils.GREEN, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))
                : MarkupUtils.highlightText(MarkupUtils.RED, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))));
        this.drawItems(item);
        this.drawOption(item);
        this.updateUI();
    }

    protected void drawItems(Fletchable item) {
        this.clearItems();
        for (int i = 0; i < item.getMaterials().length; i++) {
            items[i] = item.getMaterials()[i];
        }
    }

    protected void drawOption(Fletchable item) {
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        if (this.hasRequirements(item)) {
            optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make 1"));
            optionTwoLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make 5"));
            optionThreeLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make 10"));
            optionFourLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make All"));
            this.registerButton(actionEvent -> create(item,1), 150111);
            this.registerButton(actionEvent -> create(item,5), 150112);
            this.registerButton(actionEvent -> create(item,10), 150113);
            this.registerButton(actionEvent -> create(item,this.getPlayer().getItems().getItemAmount(baseId)), 150114);
        }
    }

    protected boolean hasMaterial(GameItem[] materials) {
        for (GameItem material : materials) {
            GameItem item = new GameItem(material.getId(), material.getAmount());
            if (!this.getPlayer().getItems().playerHasItem(item.getId(), item.getAmount())) {
                return false;
            }
        }
        return true;
    }

    protected boolean hasLevel(int level) {
        return this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.FLETCHING.getId()) >= level;
    }

    protected boolean hasRequirements(Fletchable item) {
        return hasMaterial(item.getMaterials()) && hasLevel(item.getLevelRequirement()) && isMembers(item.isMembers());
    }

    protected boolean isMembers(boolean members) {
        return (members == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

//    private void create(Jewellery jewellery, int actions) {
//        this.getPlayer().getSkillController().getCrafting().train(new CraftJewelleryAction(this.getPlayer(), jewellery,actions));
//        this.close();
//    }

    public FletchingSkillMenuUI(Player player, int baseId) {
        super(player);
        this.baseId = baseId;
    }

    private final int baseId;
}
