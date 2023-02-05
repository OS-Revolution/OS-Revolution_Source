package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.instance.BossArenaInstanceController;
import ethos.runehub.content.instance.impl.BossArenaInstance;
import ethos.runehub.content.instance.impl.InstanceBoss;
import ethos.runehub.content.instance.impl.InstanceBossCache;
import ethos.runehub.content.instance.impl.InstanceBossDAO;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.ui.UIUtils;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class BossInstanceUI extends SelectionParentUI {


    @Override
    public void onOpen() {
        this.clearUI();
        titleLabel.setText("Select Instance");
        tabOneLabel.setText("Tier 1");
        tabTwoLabel.setText("Tier 2");
        tabThreeLabel.setText("Tier 3");
        rewardLabel.setText("Payment");
        listTitleLabel.setText("Bosses");
        selectedItem = InstanceBossDAO.getInstance().getAllEntries().get(0);
        this.drawListItems();
        this.selectTab(187134);
        this.drawSelectedIndex(selectedItem, HostileMobIdContextLoader.getInstance().read(selectedItem.getNpcId()));
        this.registerButton(new UICloseActionListener(), 148114);
        this.registerButton(actionEvent -> selectTab(187134), 187134);
        this.registerButton(actionEvent -> selectTab(187135), 187135);
        this.registerButton(actionEvent -> selectTab(187136), 187136);
        this.updateUI();
    }

    private void drawListItems() {
        final List<InstanceBoss> items = InstanceBossDAO.getInstance().getAllEntries().stream()
                .collect(Collectors.toList());
        for (int i = 0; i < items.size(); i++) {
            InstanceBoss item = InstanceBossCache.getInstance().read(items.get(i).getNpcId());
            HostileMobContext ctx = HostileMobIdContextLoader.getInstance().read(item.getNpcId());
            if (ctx != null) {
                if (item.isMembers() && !this.getPlayer().getAttributes().isMember()) {
                    listItemLabels[i].setText("@red@" + ctx.getName());
                } else {
                    listItemLabels[i].setText(ctx.getName());
                }
                listItemButtons[i].addActionListener(actionEvent -> drawSelectedIndex(item, ctx));
            }
        }
    }

    private void drawSelectedIndex(InstanceBoss item, HostileMobContext ctx) {
        selectedItem = item;
        infoH1Label.setText(ctx.getName());
        infoH2Label.setText(ctx.getExamine());
        infoLine1Label.setText("Respawn Timer: " + ((this.getRespawnTime(item) * 600) / 1000) + " seconds");
        infoLine2Label.setText("Session Duration: " + this.getSessionDurationMinutes() + " minutes ");
        infoLine3Label.setText("Members: " + (isMembers(item) ? MarkupUtils.highlightText(MarkupUtils.GREEN, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))
                : MarkupUtils.highlightText(MarkupUtils.RED, RunehubUtils.getBooleanAsYesOrNo(item.isMembers()))));
        this.drawItems(item);
        this.drawOption(item);
        this.updateUI();
    }

    private void drawItems(InstanceBoss item) {
        this.clearItems();
        for (int i = 0; i < item.getPayment().length; i++) {
            items[i] = new GameItem(item.getPayment()[i].getId(), item.getPayment()[i].getAmount() * selectedTab);
        }
    }

    private int getSessionDurationMinutes() {
        switch (selectedTab) {
            case 1:
                return 10;
            case 2:
                return 30;
            case 3:
                return 60;
        }
        return 10;
    }

    private boolean hasRequirements(InstanceBoss item) {
        return hasMaterial(item) && isMembers(item);
    }

    private boolean isMembers(InstanceBoss item) {
        return (item.isMembers() == this.getPlayer().getAttributes().isMember()) || this.getPlayer().getAttributes().isMember();
    }

    private void drawOption(InstanceBoss item) {
        optionOneLabel.setText("");
        optionTwoLabel.setText("");
        optionThreeLabel.setText("");
        optionFourLabel.setText("");
        if (this.hasRequirements(item)) {
            optionOneLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Start Session"));
//            optionTwoLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Start Session (20 Min)"));
//            optionThreeLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Start Session (30 Min)"));
//            optionFourLabel.setText(MarkupUtils.highlightText(MarkupUtils.GREEN, "Start Session (60 Min)"));
            this.registerButton(actionEvent -> startSession(Duration.ofMinutes(this.getSessionDurationMinutes()), item), 150111);
//            this.registerButton(actionEvent -> create(jewellery, 5), 150112);
//            this.registerButton(actionEvent -> create(jewellery, 10), 150113);
//            this.registerButton(actionEvent -> create(jewellery, 0), 150114);
        }
    }

    private int getRespawnTime(InstanceBoss boss) {
        final int baseTime = boss.getRespawnTime();
        final double reduction = this.getRespawnTimerReduction();
        final int ticksReduced = (int) (baseTime * reduction);
        return( baseTime - ticksReduced);
    }

    private double getRespawnTimerReduction() {
       switch (selectedTab) {
           case 1:
               return 0;
           case 2:
               return 0.15;
           case 3:
               return 0.25;
       }
       return 0;
    }

    protected boolean hasMaterial(InstanceBoss item) {
        for (int i = 0; i < item.getPayment().length; i++) {
            GameItem material = new GameItem(item.getPayment()[i].getId(), item.getPayment()[i].getAmount());
            if (!this.getPlayer().getItems().playerHasItem(material.getId(), material.getAmount())) {
                return false;
            }
        }
        return true;
    }


    private void startSession(Duration duration, InstanceBoss boss) {
        if (this.getPlayer().getAttributes().getInstanceId() == -1) {
            final int instanceIndex = BossArenaInstanceController.getInstance().getFirstAvailableInstanceIndex();
            if (instanceIndex != -1) {
                final int floorId = (instanceIndex * 4) + 6;
                final BossArenaInstance instance = new BossArenaInstance(
                        instanceIndex,
                        this.getPlayer(),
                        new Rectangle(new Point(1807, 5135), new Point(1841, 5168)),
                        duration.toMillis(),
                        System.currentTimeMillis(),
                        floorId);
                boss.setRespawnTime(this.getRespawnTime(boss));
                instance.setInstanceBoss(boss);
                this.getPlayer().getAttributes().setInstanceId(instanceIndex);
                BossArenaInstanceController.getInstance().createInstance(instance);
                this.close();
            } else {
                this.getPlayer().sendMessage("There are no available instances. Please try again later.");
            }
        } else {
            this.getPlayer().sendMessage("You already have an active instance.");
        }


    }

    private void selectTab(int tabId) {
        UIUtils.removeHighlight(tabOneLabel);
        UIUtils.removeHighlight(tabTwoLabel);
        UIUtils.removeHighlight(tabThreeLabel);
        switch (tabId) {
            case 187134:
                UIUtils.highlightText("@whi@", tabOneLabel);
                selectedTab = 1;
                break;
            case 187135:
                UIUtils.highlightText("@whi@", tabTwoLabel);
                selectedTab = 2;
                break;
            case 187136:
                if (this.getPlayer().getAttributes().isMember()) {
                    UIUtils.highlightText("@whi@", tabThreeLabel);
                    selectedTab = 3;
                } else {
                    this.getPlayer().sendMessage("You must be a $member to use $Tier $3.");
                }
                break;
        }
        this.drawSelectedIndex(selectedItem, HostileMobIdContextLoader.getInstance().read(selectedItem.getNpcId()));
        this.drawItems(selectedItem);
        this.updateUI();
    }

    public BossInstanceUI(Player player) {
        super(player);

    }


    private InstanceBoss selectedItem;
    private int selectedTab;
}
