package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.ui.GameUI;
import org.runehub.api.util.SkillDictionary;

public class LampUI extends GameUI {

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
            if (this.getPlayer().getAttributes().getSkillSelected() != -1) {
                this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                        .addOptions(new DialogOption("Confirm " + RunehubUtils.getSkillName(this.getPlayer().getAttributes().getSkillSelected())) {
                                        @Override
                                        public void onAction() {
                                            close();
                                            getPlayer().getAttributes().getActiveDialogSequence().next();
                                        }
                                    },
                                new DialogOption("Nevermind") {
                                    @Override
                                    public void onAction() {
                                        getPlayer().getAttributes().getActiveDialogSequence().next();
                                    }
                                })
                        .build());
            } else {
                this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                        .addStatement("You must select a skill first.")
                        .build());
            }
        }, 11015);

    }

    @Override
    protected void onClose() {
        this.getPlayer().getAttributes().setSkillSelected(-1);
    }

    @Override
    protected void onEvent() {

    }

    private void selectSkill(SkillDictionary.Skill skill) {
        this.getPlayer().getAttributes().setSkillSelected(skill.getId());
    }

    public LampUI(Player player) {
        super(2808, player);
    }
}
