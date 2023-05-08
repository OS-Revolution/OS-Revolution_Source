package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.ui.GameUI;
import ethos.util.Misc;
import org.runehub.api.util.SkillDictionary;

public class SelectASkillUI extends GameUI {

    @Override
    protected void onOpen() {
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.ATTACK), 10252);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.STRENGTH), 10253);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.RANGED), 10254);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.MAGIC), 10255);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.DEFENCE), 11000);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.HITPOINTS), 11001);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.PRAYER), 11002);

        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.AGILITY), 11003);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.HERBLORE), 11004);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.THIEVING), 11005);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.CRAFTING), 11006);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.RUNECRAFTING), 11007);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.SLAYER), 47002);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.FARMING), 54090);

        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.MINING), 11008);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.SMITHING), 11009);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.FISHING), 11010);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.COOKING), 11011);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.FIREMAKING), 11012);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.WOODCUTTING), 11013);
        this.registerButton(actionEvent -> selectSkill(SkillDictionary.Skill.FLETCHING), 11014);

        this.registerButton(actionEvent -> {
            if(this.getPlayer().getAttributes().getSkillSelected() > -1) {
                this.onEvent();
            } else {
                this.getPlayer().sendMessage("You must select a skill first.");
            }
        }, 11015);
    }

    @Override
    protected void onClose() {
        this.getPlayer().getAttributes().setSkillSelected(-1);
    }

    @Override
    protected void onAction(int buttonId) {
        final SkillDictionary.Skill skill = this.getSkill(buttonId);


    }

    @Override
    protected void onEvent() {
        this.setState(State.COMPLETED);
    }

    private void selectSkill(SkillDictionary.Skill skill) {
        if (skill != null) {
            this.getPlayer().getAttributes().setSkillSelected(skill.getId());
            this.getPlayer().sendMessage("You've selected $" + this.adaptSkillName(skill));
        } else {
            this.close();
        }
    }

    private String adaptSkillName(SkillDictionary.Skill skill) {
        return RunehubUtils.getSkillName(skill.getId());
    }

    private SkillDictionary.Skill getSkill(int buttonId) {
        switch (buttonId) {
            case 10252:
                return SkillDictionary.Skill.ATTACK;
            case 10253:
                return SkillDictionary.Skill.STRENGTH;
            case 10254:
                return SkillDictionary.Skill.RANGED;
            case 10255:
                return SkillDictionary.Skill.MAGIC;
            case 11000:
                return SkillDictionary.Skill.DEFENCE;
            case 11001:
                return SkillDictionary.Skill.HITPOINTS;
            case 11002:
                return SkillDictionary.Skill.PRAYER;
            case 11003:
                return SkillDictionary.Skill.AGILITY;
            case 11004:
                return SkillDictionary.Skill.HERBLORE;
            case 11005:
                return SkillDictionary.Skill.THIEVING;
            case 11006:
                return SkillDictionary.Skill.CRAFTING;
            case 11007:
                return SkillDictionary.Skill.RUNECRAFTING;
            case 47002:
                return SkillDictionary.Skill.SLAYER;
            case 54090:
                return SkillDictionary.Skill.FARMING;
            case 11008:
                return SkillDictionary.Skill.MINING;
            case 11009:
                return SkillDictionary.Skill.SMITHING;
            case 11010:
                return SkillDictionary.Skill.FISHING;
            case 11011:
                return SkillDictionary.Skill.COOKING;
            case 11012:
                return SkillDictionary.Skill.FIREMAKING;
            case 11013:
                return SkillDictionary.Skill.WOODCUTTING;
            case 11014:
                return SkillDictionary.Skill.FLETCHING;
        }
        return null;
    }

    public SelectASkillUI(Player player) {
        super(2808, player);
    }
}
