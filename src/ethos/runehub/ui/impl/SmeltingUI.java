package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.model.players.skills.Smelting;
import ethos.runehub.entity.item.ItemInteraction;
import ethos.runehub.entity.item.ItemInteractionContext;
import ethos.runehub.entity.item.ItemInteractionLoader;
import ethos.runehub.skill.artisan.smithing.Smeltable;
import ethos.runehub.skill.artisan.smithing.SmeltingItemReaction;
import ethos.runehub.skill.artisan.smithing.SmeltingItemReactionLoader;
import ethos.runehub.skill.artisan.smithing.action.SmeltAction;
import ethos.runehub.ui.GameUI;

public class SmeltingUI extends GameUI {

    @Override
    public void open() {
        this.onOpen();
        this.setShowing(true);
        this.setState(State.ACTIVE);
        this.getPlayer().getAttributes().setActiveUI(this);
        this.drawItems();
        this.getPlayer().getPA().sendFrame164(2400);

        this.registerButton(actionEvent -> this.smelt(15147),15147);
        this.registerButton(actionEvent -> this.smelt(15146),15146);
        this.registerButton(actionEvent -> this.smelt(10247),10247);
        this.registerButton(actionEvent -> this.smelt(9110),9110);

        this.registerButton(actionEvent -> this.smelt(15151),15151);
        this.registerButton(actionEvent -> this.smelt(15150),15150);
        this.registerButton(actionEvent -> this.smelt(15149),15149);
        this.registerButton(actionEvent -> this.smelt(15148),15148);

        this.registerButton(actionEvent -> this.smelt(15155),15155);
        this.registerButton(actionEvent -> this.smelt(15154),15154);
        this.registerButton(actionEvent -> this.smelt(15153),15153);
        this.registerButton(actionEvent -> this.smelt(15152),15152);

        this.registerButton(actionEvent -> this.smelt(15159),15159);
        this.registerButton(actionEvent -> this.smelt(15158),15158);
        this.registerButton(actionEvent -> this.smelt(15157),15157);
        this.registerButton(actionEvent -> this.smelt(15156),15156);

        this.registerButton(actionEvent -> this.smelt(15163),15163);
        this.registerButton(actionEvent -> this.smelt(15162),15162);
        this.registerButton(actionEvent -> this.smelt(15161),15161);
        this.registerButton(actionEvent -> this.smelt(15160),15160);

        this.registerButton(actionEvent -> this.smelt(29017),29017);
        this.registerButton(actionEvent -> this.smelt(29017),29017);
        this.registerButton(actionEvent -> this.smelt(24253),24253);
        this.registerButton(actionEvent -> this.smelt(16062),16062);

        this.registerButton(actionEvent -> this.smelt(29022),29022);
        this.registerButton(actionEvent -> this.smelt(29021),29021);
        this.registerButton(actionEvent -> this.smelt(29019),29019);
        this.registerButton(actionEvent -> this.smelt(29018),29018);

        this.registerButton(actionEvent -> this.smelt(29026),29026);
        this.registerButton(actionEvent -> this.smelt(29025),29025);
        this.registerButton(actionEvent -> this.smelt(29024),29024);
        this.registerButton(actionEvent -> this.smelt(29023),29023);

    }

    @Override
    protected void onOpen() {

    }

    @Override
    protected void onClose() {

    }

    protected void smelt(int buttonId) {
        switch (buttonId) {
            case 15147:// Bronze, 1
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 5, Smeltable.BRONZE, 1));
                break;
            case 15146:// Bronze, 5
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 5, Smeltable.BRONZE, 5));
                break;
            case 10247:// Bronze, 10
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 5, Smeltable.BRONZE, 10));
                break;
            case 9110:// Bronze, 28
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 5, Smeltable.BRONZE, 28));
                break;
            case 15151:// Iron, 1
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.IRON, 1));
                break;
            case 15150:// Iron, 5
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.IRON, 5));
                break;
            case 15149:// Iron, 10
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.IRON, 10));
                break;
            case 15148:// Iron, 28
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.IRON, 28));
                break;
            case 15155:// silver, 1
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.SILVER, 1));
                break;
            case 15154:// silver, 5
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.SILVER, 5));
                break;
            case 15153:// silver, 10
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.SILVER, 10));
                break;
            case 15152:// silver, 28
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.SILVER, 28));
                break;
            case 15159:// steel, 1
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.STEEL, 1));
                break;
            case 15158:// steel, 5
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.STEEL, 5));
                break;
            case 15157:// steel, 10
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.STEEL, 10));
                break;
            case 15156:// steel, 28
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.STEEL, 28));
                break;
            case 15163:// gold, 1
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.GOLD, 1));
                break;
            case 15162:// gold, 5
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.GOLD, 5));
                break;
            case 15161:// gold, 10
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.GOLD, 10));
                break;
            case 15160:// gold, 28
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.GOLD, 28));
                break;
            case 29017:// mithril, 1
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.MITHRIL, 1));
                break;
            case 29016:// mithril, 5
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.MITHRIL, 5));
                break;
            case 24253:// mithril, 10
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.MITHRIL, 10));
                break;
            case 16062:// mithril, 28
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.MITHRIL, 28));
                break;
            case 29022:// addy, 1
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.ADAMANTITE, 1));
                break;
            case 29021:// addy, 5
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.ADAMANTITE, 5));
                break;
            case 29019:// addy, 10
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.ADAMANTITE, 10));
                break;
            case 29018:// addy, 28
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.ADAMANTITE, 28));
                break;
            case 29026:// rune, 1
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.RUNITE, 1));
                break;
            case 29025:// rune, 5
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.RUNITE, 5));
                break;
            case 29024:// rune, 10
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.RUNITE, 10));
                break;
            case 29023:// rune, 28
                this.getPlayer().getSkillController().getSmithing().train(new SmeltAction(this.getPlayer(), 4, Smeltable.RUNITE, 28));
                break;
        }
        this.close();
    }

    @Override
    protected void onEvent() {

    }

    private void drawItems() {
        this.getPlayer().getPA().sendFrame246(2405, 150, 2349);//bronze
        this.getPlayer().getPA().sendFrame246(2406, 150, 2351);//iron
        this.getPlayer().getPA().sendFrame246(2407, 150, 2355);//silver
        this.getPlayer().getPA().sendFrame246(2409, 150, 2353);//steel
        this.getPlayer().getPA().sendFrame246(2410, 150, 2357);//gold
        this.getPlayer().getPA().sendFrame246(2411, 150, 2359);//mithril
        this.getPlayer().getPA().sendFrame246(2412, 150, 2361);//adamant
        this.getPlayer().getPA().sendFrame246(2413, 150, 2363);//runite
    }

    public SmeltingUI(Player player) {
        super(2400, player);
    }
}
