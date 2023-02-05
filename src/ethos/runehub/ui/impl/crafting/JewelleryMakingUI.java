package ethos.runehub.ui.impl.crafting;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.construction.Hotspot;
import ethos.runehub.skill.artisan.crafting.action.CraftJewelleryAction;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryCache;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;
import ethos.runehub.skill.combat.magic.MagicTablet;
import ethos.runehub.skill.combat.magic.MagicTabletCache;
import ethos.runehub.skill.combat.magic.MagicTabletDAO;
import ethos.runehub.skill.combat.magic.action.CreateMagicTabletSkillAction;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

public class JewelleryMakingUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Craft Jewellery");
        rewardLabel.setText("Required Materials");
        this.drawListItems();
        this.updateUI();
    }

    @Override
    protected void onClose() {
        super.onClose();
    }

    private void drawListItems() {
        final List<Jewellery> items = JewelleryDAO.getInstance().getAllEntries().stream()
                .filter(jewellery -> jewellery.getMouldId() == mouldId)
                .collect(Collectors.toList());
        for (int i = 0; i < items.size(); i++) {
            Jewellery item = JewelleryCache.getInstance().read(items.get(i).getProductId());
            listItemLabels[i].setText(ItemIdContextLoader.getInstance().read(item.getProductId()).getName());
            listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(item));
        }
    }

    private void drawSelectedIndex(Jewellery item) {
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

    private void drawItems(Jewellery jewellery) {
        this.clearItems();
        for (int i = 0; i < jewellery.getMaterials().length; i++) {
            items[i] = jewellery.getMaterials()[i];
        }
    }

    private void drawOption(Jewellery jewellery) {
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        if (this.hasRequirements(jewellery)) {
            optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make 1"));
            optionTwoLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make 5"));
            optionThreeLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make 10"));
            optionFourLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make All"));
            this.registerButton(actionEvent -> create(jewellery,1), 150111);
            this.registerButton(actionEvent -> create(jewellery,5), 150112);
            this.registerButton(actionEvent -> create(jewellery,10), 150113);
            this.registerButton(actionEvent -> create(jewellery,0), 150114);
        }
    }

    protected boolean hasMaterial(Jewellery jewellery) {
        for (int i = 0; i < jewellery.getMaterials().length; i++) {
            GameItem item = new GameItem(jewellery.getMaterials()[i].getId(), jewellery.getMaterials()[i].getAmount());
            if (!this.getPlayer().getItems().playerHasItem(item.getId(), item.getAmount())) {
                return false;
            }
        }
        return true;
    }

    private boolean hasLevel(Jewellery jewellery) {
        return this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.CRAFTING.getId()) >= jewellery.getLevel();
    }

    private boolean hasRequirements(Jewellery jewellery) {
        return hasMaterial(jewellery) && hasLevel(jewellery) && isMembers(jewellery);
    }

    private boolean isMembers(Jewellery jewellery) {
        return (jewellery.isMembers() == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

    private void create(Jewellery jewellery, int actions) {
        this.getPlayer().getSkillController().getCrafting().train(new CraftJewelleryAction(this.getPlayer(), jewellery,actions));
        this.close();
    }

    public JewelleryMakingUI(Player player, int mouldId) {
        super(player);
        this.mouldId = mouldId;
    }

    private final int mouldId;
}
