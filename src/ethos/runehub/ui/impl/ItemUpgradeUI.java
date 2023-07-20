package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.PointController;
import ethos.runehub.content.upgrading.UpgradeRule;
import ethos.runehub.content.upgrading.UpgradeRuleCache;
import ethos.runehub.content.upgrading.UpgradeRuleDatabase;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.support.sailing.ship.Ship;
import ethos.runehub.skill.support.sailing.ship.Shipwright;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.model.entity.item.ItemContext;

import java.util.Objects;

public class ItemUpgradeUI extends SelectionParentUI {

    @Override
    protected void onOpen() {
        super.onOpen();
        titleLabel.setText("Upgrade Bench");
        listTitleLabel.setText("Available Upgrades");
        rewardLabel.setText("Potential Upgrade");
        this.loadUpgradeList();
        this.updateUI();
    }

    private void loadUpgradeList() {
        int index = 0;
        for (int i = 0; i < this.getPlayer().playerItems.length; i++) {
            int itemId = this.getPlayer().playerItems[i] - 1;

            if (itemId > 0) {
                if (UpgradeRuleCache.getInstance().getUpgradeRules(itemId, -1) != null
                        && !UpgradeRuleCache.getInstance().getUpgradeRules(itemId, -1).isEmpty()) {
                    UpgradeRule rule = UpgradeRuleCache.getInstance().getUpgradeRules(itemId, -1).get(0);
                    if (!rule.isMembers() || this.getPlayer().getAttributes().isMember()) {
                        ItemContext sourceContext = ItemIdContextLoader.getInstance().read(itemId);
                        listItemLabels[index].setText(sourceContext.getName());
                        listItemButtons[index].addActionListener(actionEvent -> selectUpgrade(rule));
                        index++;
                    }
                }
            }
        }
    }

    private void selectUpgrade(UpgradeRule upgrade) {
        ItemContext sourceContext = ItemIdContextLoader.getInstance().read(upgrade.getSourceItemId());
        ItemContext upgradeContext = ItemIdContextLoader.getInstance().read(upgrade.getUpgradedItemId());
        infoH1Label.setText("Upgrade Available");
        infoH2Label.setText("Upgrade your " + sourceContext.getName() + " to " + upgradeContext.getName());

        infoLine1Label.setText("PvM Point Cost: " + this.getPlayer().getAttributes().getCurrentUpgradeCost(upgrade));
        infoLine2Label.setText("Success Chance: " + RunehubUtils.getPercentageStringFromFloat(this.getPlayer().getAttributes().getCurrentUpgradeSuccessChance(upgrade)));
        infoLine3Label.setText("Consume Chance: " + RunehubUtils.getPercentageStringFromFloat(upgrade.getBaseConsumeChance()));
        infoLine4Label.setText("Failure Chance: " + RunehubUtils.getPercentageStringFromFloat(this.getPlayer().getAttributes().getCurrentUpgradeFailureChance(upgrade)));
        infoLine5Label.setText("Smithing Bonus: " + RunehubUtils.getPercentageStringFromFloat(this.getPlayer().getSkillController().getSmithing().getUpgradeBonus()));
        infoLine6Label.setText("Crafting Bonus: " + RunehubUtils.getPercentageStringFromFloat(this.getPlayer().getSkillController().getCrafting().getUpgradeBonus()));
        this.drawItem(upgrade);

        int pointCost = this.getPlayer().getAttributes().getCurrentUpgradeCost(upgrade);
        if (this.getPlayer().getAttributes().getPointController().getPoints(PointController.PointType.PVM) >= pointCost
                && this.getPlayer().getItems().playerHasItem(upgrade.getSourceItemId(), upgrade.getSourceItemAmount())) {
            optionOneLabel.setText("@gre@Upgrade");
            this.registerButton(actionEvent -> {
                this.upgrade(upgrade);

            }, 150111);
        } else {
            optionOneLabel.setText("@red@Upgrade");
            this.registerButton(actionEvent -> {
            }, 150111);
        }
        this.updateUI();
    }

    private void drawItem(UpgradeRule rule) {
        items[0] = new GameItem(rule.getUpgradedItemId(), rule.getUpgradedItemAmount());
    }

    private void upgrade(UpgradeRule rule) {
        final float roll = Skill.SKILL_RANDOM.nextFloat();
        this.getPlayer().getAttributes().getPointController().subtractPoints(PointController.PointType.PVM, this.getPlayer().getAttributes().getCurrentUpgradeCost(rule));

        if (roll <= this.getPlayer().getAttributes().getCurrentUpgradeSuccessChance(rule)) {
            this.getPlayer().getItems().delete(rule.getSourceItemId(), rule.getSourceItemAmount());
            this.getPlayer().getItems().addOrDropItem(rule.getUpgradedItemId(), rule.getUpgradedItemAmount());
            this.getPlayer().sendMessage("@gre@Your upgrade was successful!");
            this.getPlayer().getAttributes().setUpgradeAttempts(0);
            this.onOpen();
        } else {
            final float failureRoll = Skill.SKILL_RANDOM.nextFloat();
            if (failureRoll <= rule.getBaseConsumeChance()) {
                this.getPlayer().getItems().delete(rule.getSourceItemId(), rule.getSourceItemAmount());
                this.getPlayer().sendMessage("@red@Your upgrade failed and the item was consumed.");
                this.getPlayer().getAttributes().setUpgradeAttempts(0);
                this.onOpen();
            } else {
                this.getPlayer().sendMessage("@red@Your upgrade failed.");
                this.getPlayer().getAttributes().setUpgradeAttempts(this.getPlayer().getAttributes().getUpgradeAttempts() + 1);
                this.selectUpgrade(rule);
            }
        }
    }

    public ItemUpgradeUI(Player player) {
        super(player);
    }
}
