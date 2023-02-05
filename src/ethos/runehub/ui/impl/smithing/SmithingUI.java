package ethos.runehub.ui.impl.smithing;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.smithing.Smeltable;
import ethos.runehub.skill.artisan.smithing.SmithingItem;
import ethos.runehub.skill.artisan.smithing.SmithingItemCache;
import ethos.runehub.skill.artisan.smithing.SmithingItemDAO;
import ethos.runehub.skill.artisan.smithing.action.SmithItemAction;
import ethos.runehub.skill.gathering.tool.GatheringTool;
import ethos.runehub.skill.gathering.tool.GatheringToolDAO;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SmithingUI extends SelectionParentUI {

//    public static final int[] BARS = {2349, 2351, 2353, 2355, 2357, 2359, 2361, 2363};

    @Override
    protected void onOpen() {
        super.onOpen();
        this.currentIndex = this.getIndexOfBar(bestBarId);
        titleLabel.setText("Smith Item");
        rewardLabel.setText("Required Materials");
        tabOneLabel.setText("Previous");
        tabTwoLabel.setText(ItemIdContextLoader.getInstance().read(this.getBarFromIndex(currentIndex)).getName());
        listTitleLabel.setText(ItemIdContextLoader.getInstance().read(this.getBarFromIndex(currentIndex)).getName());
        tabThreeLabel.setText("Next");

        this.registerButton(actionEvent -> next(),187136);
        this.registerButton(actionEvent -> previous(),187134);
        this.drawListItems();
        this.updateUI();
    }

    private int getIndexOfBar(int barId) {

        for (int i = 0; i < Smeltable.values().length; i++) {
            if (Smeltable.values()[i].getProductId() == barId)
                return i;
        }
        return 0;
    }

    private int getBarFromIndex(int index) {
        return Smeltable.values()[index].getProductId();
    }


    private void next() {
        if (currentIndex < Smeltable.values().length - 1) {
            currentIndex = Arrays.stream(Smeltable.values())
                    .filter(smeltable -> this.getPlayer().getItems().playerHasItem(smeltable.getProductId()))
                    .filter(smeltable -> smeltable.ordinal() != currentIndex)
                    .findFirst().orElse(Smeltable.values()[currentIndex]).ordinal();
            tabTwoLabel.setText(ItemIdContextLoader.getInstance().read(this.getBarFromIndex(currentIndex)).getName());
            listTitleLabel.setText(ItemIdContextLoader.getInstance().read(this.getBarFromIndex(currentIndex)).getName());
            this.clearList();
            this.clearSelected();
            this.drawListItems();
            this.updateUI();
        }
    }

    private void previous() {
        if (currentIndex > 0) {
            currentIndex = Arrays.stream(Smeltable.values())
                    .filter(smeltable -> this.getPlayer().getItems().playerHasItem(smeltable.getProductId()))
                    .filter(smeltable -> smeltable.ordinal() < currentIndex)
                    .findFirst().orElse(Smeltable.values()[currentIndex]).ordinal();
            tabTwoLabel.setText(ItemIdContextLoader.getInstance().read(this.getBarFromIndex(currentIndex)).getName());
            listTitleLabel.setText(ItemIdContextLoader.getInstance().read(this.getBarFromIndex(currentIndex)).getName());
            this.clearList();
            this.clearSelected();
            this.drawListItems();
            this.updateUI();
        }
    }

    private void clearSelected() {
        infoH1Label.setText("");
        infoH2Label.setText("");
        infoLine1Label.setText("");
        infoLine2Label.setText("");
        infoLine3Label.setText("");
        infoLine4Label.setText("");
        this.clearItems();
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
    }

    private void drawListItems() {
        final List<SmithingItem> items = SmithingItemDAO.getInstance().getAllEntries().stream()
                .filter(item -> Arrays.stream(item.getMaterials()).anyMatch(gameItem -> gameItem.getId() == this.getBarFromIndex(currentIndex)))
                .collect(Collectors.toList());
        items.sort(Comparator.comparingInt(SmithingItem::getLevelRequired));
        for (int i = 0; i < items.size(); i++) {
            SmithingItem item = SmithingItemCache.getInstance().read(items.get(i).getItemId());
            listItemLabels[i].setText(ItemIdContextLoader.getInstance().read(item.getItemId()).getName());
            listItemButtons[i].getActionListeners().clear();
            listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(item));
        }
    }

    private void drawSelectedIndex(SmithingItem item) {
        infoH1Label.setText(ItemIdContextLoader.getInstance().read(item.getItemId()).getName());
        infoH2Label.setText(ItemIdContextLoader.getInstance().read(item.getItemId()).getExamine());
        infoLine1Label.setText("Smithing Level: " + (hasLevel(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, String.valueOf(item.getLevelRequired())) : MarkupUtils.highlightText(MarkupUtils.RED, String.valueOf(item.getLevelRequired()))));
        infoLine2Label.setText("Smithing XP: " + (NumberFormat.getInstance().format((long) item.getXp() * item.getMaterials()[0].getAmount())));
        infoLine3Label.setText("Members: " + (isMembers(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))
                : MarkupUtils.highlightText(MarkupUtils.RED, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))));
        infoLine4Label.setText("Makes: x" + item.getAmountProduced());
        this.drawItems(item);
        this.drawOption(item);
        this.updateUI();
    }

    private void drawItems(SmithingItem jewellery) {
        this.clearItems();
        for (int i = 0; i < jewellery.getMaterials().length; i++) {
            items[i] = jewellery.getMaterials()[i];
        }
    }

    private void drawOption(SmithingItem jewellery) {
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        if (this.hasRequirements(jewellery)) {
            optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make 1"));
            optionTwoLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make 5"));
            optionThreeLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make 10"));
            optionFourLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Make All"));
            this.registerButton(actionEvent -> create(jewellery, 1), 150111);
            this.registerButton(actionEvent -> create(jewellery, 5), 150112);
            this.registerButton(actionEvent -> create(jewellery, 10), 150113);
            this.registerButton(actionEvent -> create(jewellery, 0), 150114);
        }
    }

    protected boolean hasMaterial(SmithingItem item) {
        for (int i = 0; i < item.getMaterials().length; i++) {
            GameItem material = new GameItem(item.getMaterials()[i].getId(), item.getMaterials()[i].getAmount());
            if (!this.getPlayer().getItems().playerHasItem(material.getId(), material.getAmount())) {
                return false;
            }
        }
        return true;
    }

    private boolean hasLevel(SmithingItem item) {
        return this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.SMITHING.getId()) >= item.getLevelRequired();
    }

    private boolean hasRequirements(SmithingItem jewellery) {
        return hasMaterial(jewellery) && hasLevel(jewellery) && isMembers(jewellery);
    }

    private boolean isMembers(SmithingItem jewellery) {
        return (jewellery.isMembers() == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

    private void create(SmithingItem jewellery, int actions) {
        this.getPlayer().getSkillController().getSmithing().train(new SmithItemAction(this.getPlayer(), jewellery, actions));
        this.close();
    }

    public SmithingUI(Player player, int bestBarId) {
        super(player);
        this.bestBarId = bestBarId;


    }

    private int currentIndex;
    private final int bestBarId;
}
