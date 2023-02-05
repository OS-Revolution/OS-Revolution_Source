package ethos.runehub.ui.impl.crafting;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.crafting.action.CraftJewelleryAction;
import ethos.runehub.skill.artisan.crafting.action.CraftLeatherAction;
import ethos.runehub.skill.artisan.crafting.armor.leather.Leather;
import ethos.runehub.skill.artisan.crafting.armor.leather.LeatherCache;
import ethos.runehub.skill.artisan.crafting.armor.leather.LeatherDAO;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryCache;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeatherCraftingUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Craft Leather");
        rewardLabel.setText("Required Materials");
        listTitleLabel.setText("Leather Armor");
        this.drawListItems();
        this.updateUI();
    }

    @Override
    protected void onClose() {
        super.onClose();
    }

    private void drawListItems() {
        final List<Leather> items = LeatherDAO.getInstance().getAllEntries().stream()
                .filter(leather -> Arrays.stream(leather.getMaterials()).anyMatch(gameItem -> gameItem.getId() == leatherId))
                .collect(Collectors.toList());
        for (int i = 0; i < items.size(); i++) {
            Leather item = LeatherCache.getInstance().read(items.get(i).getProductId());
            listItemLabels[i].setText(ItemIdContextLoader.getInstance().read(item.getProductId()).getName());
            listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(item));
        }
    }

    private void drawSelectedIndex(Leather item) {
        infoH1Label.setText(ItemIdContextLoader.getInstance().read(item.getProductId()).getName());
        infoH2Label.setText(ItemIdContextLoader.getInstance().read(item.getProductId()).getExamine());
        infoLine1Label.setText("Crafting Level: " + (hasLevel(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, String.valueOf(item.getLevel())) : MarkupUtils.highlightText(MarkupUtils.RED, String.valueOf(item.getLevel()))));
        infoLine2Label.setText("Crafting XP: " + (NumberFormat.getInstance().format(item.getXp())));
        infoLine3Label.setText("Members: " + (isMembers(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))
                : MarkupUtils.highlightText(MarkupUtils.RED, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))));
        this.drawItems(item);
        this.drawOption(item);
        this.updateUI();
    }

    private void drawItems(Leather item) {
        this.clearItems();
        for (int i = 0; i < item.getMaterials().length; i++) {
            items[i] = item.getMaterials()[i];
        }
    }

    private void drawOption(Leather item) {
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
            this.registerButton(actionEvent -> create(item,0), 150114);
        }
    }

    protected boolean hasMaterial(Leather jewellery) {
        for (int i = 0; i < jewellery.getMaterials().length; i++) {
            GameItem item = new GameItem(jewellery.getMaterials()[i].getId(), jewellery.getMaterials()[i].getAmount());
            if (!this.getPlayer().getItems().playerHasItem(item.getId(), item.getAmount())) {
                return false;
            }
        }
        return true;
    }

    private boolean hasLevel(Leather item) {
        return this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.CRAFTING.getId()) >= item.getLevel();
    }

    private boolean hasRequirements(Leather item) {
        return hasMaterial(item) && hasLevel(item) && isMembers(item);
    }

    private boolean isMembers(Leather item) {
        return (item.isMembers() == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

    private void create(Leather item, int actions) {
        this.getPlayer().getSkillController().getCrafting().train(new CraftLeatherAction(this.getPlayer(), item,actions));
        this.close();
    }

    public LeatherCraftingUI(Player player, int leatherId) {
        super(player);
        this.leatherId = leatherId;

    }

    private final int leatherId;
}
