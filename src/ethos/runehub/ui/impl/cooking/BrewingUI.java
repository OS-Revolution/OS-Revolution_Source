package ethos.runehub.ui.impl.cooking;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.TimeUtils;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.cooking.food.Brew;
import ethos.runehub.skill.artisan.cooking.food.BrewDAO;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class BrewingUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Brewing");
        rewardLabel.setText("Required Materials");
        listTitleLabel.setText("Brews");
        this.drawListItems();
        this.updateUI();
    }

    @Override
    protected void onClose() {
        super.onClose();
    }

    private void drawListItems() {
        final List<Brew> items = BrewDAO.getInstance().getAllEntries();
        for (int i = 0; i < items.size(); i++) {
            Brew brew = items.get(i);
            listItemLabels[i].setText(this.getBrewNameLabel(brew));
            listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(brew));
        }
    }

    private String getBrewNameLabel(Brew brew) {
        if (this.getPlayer().getContext().getPlayerSaveData().getIdleBrewId() == brew.getBaseBrewedItemId())
            return MarkupUtils.highlightText("@whi@",ItemIdContextLoader.getInstance().read(brew.getBaseBrewedItemId()).getName());
        else if (hasRequirements(brew))
            return MarkupUtils.highlightText(MarkupUtils.GREEN,ItemIdContextLoader.getInstance().read(brew.getBaseBrewedItemId()).getName());
        else if (hasMaterial(brew))
            return MarkupUtils.highlightText(MarkupUtils.YELLOW,ItemIdContextLoader.getInstance().read(brew.getBaseBrewedItemId()).getName());
        return MarkupUtils.highlightText(MarkupUtils.RED,ItemIdContextLoader.getInstance().read(brew.getBaseBrewedItemId()).getName());
    }

    private void drawSelectedIndex(Brew item) {
        infoH1Label.setText(ItemIdContextLoader.getInstance().read(item.getBaseBrewedItemId()).getName());
        infoH2Label.setText(ItemIdContextLoader.getInstance().read(item.getBaseBrewedItemId()).getExamine());
        infoLine1Label.setText("Cooking Level: " + (hasLevel(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, String.valueOf(item.getLevel())) : MarkupUtils.highlightText(MarkupUtils.RED, String.valueOf(item.getLevel()))));
        infoLine2Label.setText("Cooking XP: " + (NumberFormat.getInstance().format(item.getXp())));
        infoLine3Label.setText("Premium Brew: " + ItemIdContextLoader.getInstance().read(item.getPremiumBrewedItemId()));
        infoLine3Label.setText("Members: " + (isMembers(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))
                : MarkupUtils.highlightText(MarkupUtils.RED, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))));
        infoLine4Label.setText("Brew Cycle: " + TimeUtils.getDurationString(Duration.ofMinutes(90).toMillis()));
        this.drawItems(item);
        this.drawOption(item);
        this.updateUI();
    }

    private void drawItems(Brew brew) {
        this.clearItems();
        for (int i = 0; i < brew.getMaterials().length; i++) {
            items[i] = brew.getMaterials()[i];
        }
    }

    private void drawOption(Brew brew) {
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        System.out.println(hasMaterial(brew));
        if (this.hasRequirements(brew)) {
            optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Brew"));
            this.registerButton(actionEvent -> create(brew), 150111);
        }
    }

    protected boolean hasMaterial(Brew brew) {
        return Arrays.stream(brew.getMaterials())
                .map(mat -> new GameItem(mat.getId(), mat.getAmount()))
                .allMatch(item -> this.getPlayer().getContext().getPlayerSaveData().getIdleBrewingStation().stream()
                        .anyMatch(gameItem -> gameItem.getId() == item.getId() && gameItem.getAmount() >= item.getAmount()));
    }

    private boolean hasLevel(Brew brew) {
        return this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.COOKING.getId()) >= brew.getLevel();
    }

    private boolean hasRequirements(Brew brew) {
        return hasMaterial(brew) && hasLevel(brew) && isMembers(brew);
    }

    private boolean isMembers(Brew brew) {
        return (brew.isMembers() == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

    private void create(Brew brew) {
        this.getPlayer().getContext().getPlayerSaveData().setIdleBrewId(brew.getBaseBrewedItemId());
        this.close();
    }

    public BrewingUI(Player player) {
        super(player);
    }

}
