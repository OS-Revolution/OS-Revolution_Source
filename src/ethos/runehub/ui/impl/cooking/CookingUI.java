package ethos.runehub.ui.impl.cooking;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.entity.item.*;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.runehub.skill.artisan.cooking.action.CookOnFireAction;
import ethos.runehub.skill.artisan.cooking.action.CookOnRangeAction;
import ethos.runehub.skill.artisan.cooking.food.Cookable;
import ethos.runehub.skill.artisan.crafting.action.CraftJewelleryAction;
import ethos.runehub.skill.artisan.crafting.jewellery.Jewellery;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryCache;
import ethos.runehub.skill.artisan.crafting.jewellery.JewelleryDAO;
import ethos.runehub.ui.impl.SelectionParentUI;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.util.SkillDictionary;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Collectors;

public class CookingUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Cook Food");
        rewardLabel.setText("Required Materials");
        this.drawListItems();
        this.updateUI();
    }

    @Override
    protected void onClose() {
        super.onClose();
    }

    private void drawListItems() {
        final List<ItemInteraction> items = ItemInteractionDAO.getInstance().getAllEntries()
                .stream()
                .filter(interaction -> interaction.getInteractionTypeId() == 1)
                .filter(interaction -> interaction.getReactionKey() == SkillDictionary.Skill.COOKING.getId())
                .filter(interaction -> this.getPlayer().getItems().playerHasItem(interaction.getUsedId()))
                .filter(interaction -> interaction.getUsedOnId() == context.getUsedWithId())
                .collect(Collectors.toList());
        for (int i = 0; i < items.size(); i++) {
            ItemInteraction interaction = items.get(i);
            ArtisanSkillItemReaction item = ArtisanSkillItemReactionDAO.getInstance().read(items.get(i).getReactionUuid());
            listItemLabels[i].setText(ItemIdContextLoader.getInstance().read(item.getProductItemId()).getName());
            listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(item,interaction));
        }
    }

    private void drawSelectedIndex(ArtisanSkillItemReaction item, ItemInteraction interaction) {
        infoH1Label.setText(ItemIdContextLoader.getInstance().read(item.getProductItemId()).getName());
        infoH2Label.setText(ItemIdContextLoader.getInstance().read(item.getProductItemId()).getExamine());
        infoLine1Label.setText("Cooking Level: " + (hasLevel(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, String.valueOf(item.getLevelRequired())) : MarkupUtils.highlightText(MarkupUtils.RED, String.valueOf(item.getLevelRequired()))));
        infoLine2Label.setText("Cooking XP: " + (NumberFormat.getInstance().format(item.getReactionXp())));
        infoLine3Label.setText("Burn Chance: " + getBurnString(item.getLow(),item.getHigh()));
//        infoLine3Label.setText("Members: " + (isMembers(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))
//                : MarkupUtils.highlightText(MarkupUtils.RED, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))));
        this.drawItems(interaction);
        this.drawOption(item,interaction);
        this.updateUI();
    }

    private String getBurnString(int low, int high) {
        double baseChance = 1.0f;
        DecimalFormat decimalFormat = new DecimalFormat("###.##");
        if (context.getUsedWithId() != 5249) {
            baseChance = baseChance - this.getPlayer().getSkillController().getCooking().getActionSuccessChance((int) (low * 1.1D), (int) (high * 1.1D));
        } else {
            baseChance = baseChance - this.getPlayer().getSkillController().getCooking().getActionSuccessChance(low,high);
        }
        if (baseChance < 0.4) {
            return "@gre@" + decimalFormat.format((baseChance * 100.0D)) + "%";
        } else if (baseChance > 0.4 && baseChance < 0.7) {
            return "@yel@" + decimalFormat.format((baseChance * 100.0D)) + "%";
        } else {
            return "@red@" + decimalFormat.format((baseChance * 100.0D)) + "%";
        }
    }

    private void drawItems(ItemInteraction interaction) {
        this.clearItems();
        items[0] = new GameItem(interaction.getUsedId(),1);
    }

    private void drawOption(ArtisanSkillItemReaction jewellery, ItemInteraction interaction) {
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        if (this.hasRequirements(jewellery,interaction)) {
            optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Cook 1"));
            optionTwoLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Cook 5"));
            optionThreeLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Cook 10"));
            optionFourLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Cook All"));
            this.registerButton(actionEvent -> create(jewellery, 1,interaction), 150111);
            this.registerButton(actionEvent -> create(jewellery, 5,interaction), 150112);
            this.registerButton(actionEvent -> create(jewellery, 10,interaction), 150113);
            this.registerButton(actionEvent -> create(jewellery, 0,interaction), 150114);
        }
    }

    protected boolean hasMaterial(ItemInteraction interaction) {
        GameItem item = new GameItem(interaction.getUsedId(),1);
        if (!this.getPlayer().getItems().playerHasItem(item.getId(), item.getAmount())) {
            return false;
        }
        return true;
    }

    private boolean hasLevel(ArtisanSkillItemReaction jewellery) {
        return this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.COOKING.getId()) >= jewellery.getLevelRequired();
    }

    private boolean hasRequirements(ArtisanSkillItemReaction jewellery,ItemInteraction interaction) {
        return hasMaterial(interaction) && hasLevel(jewellery) && isMembers(jewellery);
    }

    private boolean isMembers(ArtisanSkillItemReaction jewellery) {
        return true;
//        return (jewellery.isMembers() == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

    private void create(ArtisanSkillItemReaction jewellery, int actions, ItemInteraction interaction) {
        if (List.of(5249).contains(context.getUsedWithId())) {
            this.getPlayer().getSkillController().getCooking().train(new CookOnFireAction(this.getPlayer(), jewellery, context, actions));
        } else{
            context.setUsedId(interaction.getUsedId());
            this.getPlayer().getSkillController().getCooking().train(new CookOnRangeAction(this.getPlayer(), jewellery, context, actions));
        }
        this.close();
    }

    public CookingUI(Player player, ItemInteractionContext context) {
        super(player);
        this.context = context;
    }

    private final ItemInteractionContext context;
}
