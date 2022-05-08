package ethos.runehub.entity.mob.passive;

import ethos.model.players.Player;
import ethos.runehub.dialog.Dialog;
import ethos.runehub.dialog.DialogSequence;

public class GuideNpcUtils {

    public static final int NPC_ID = 3308;
    
    
    public static DialogSequence getInitialSequence(Player player) {
        return new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(NPC_ID, "Hello there! Welcome to Draynor.", "Have I seen you before?")
                .addOptions(1, "Yes", "No")
                .build();
    }

    public static DialogSequence getOptionOneSequence(Player player) {
        return  new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat("Draynor Representative", NPC_ID, "I thought you looked familiar.")
                .build();
    }

    public static DialogSequence getOptionTwoSequence(Player player) {
        return   new DialogSequence.DialogSequenceBuilder(player)
                .setActionLocked(true)
                .setMovementRestricted(true)
                .addNpcChat("Draynor Representative", NPC_ID,
                        "I'm sorry, ever since they built the port here",
                        " there have been so many new people",
                        " it's difficult to keep track of.")
                .addNpcChat("Draynor Representative", NPC_ID,
                        "Let me show you around.",
                        "Afterwards I'll give you your initiative package.")
                .addDialogueAction(new Dialog() {
                    @Override
                    public void onSend() {
                        player.getPA().movePlayer(3104, 3249);
                    }
                })
                .addNpcChat("Draynor Representative", NPC_ID,
                        "This is the Draynor Marketplace!",
                        "It's a very popular hub for travelling merchants and",
                        "home of The Exchange!"
                )
                .addPlayerChat(player.getDH().CALM, "The Exchange?")
                .addNpcChat("Draynor Representative", NPC_ID,
                        "Yes! It's a global market where we can trade with people",
                        "all over the world. However space is limited so only 3 slots",
                        "are included in the initiative package"
                ).addDialogueAction(new Dialog() {
                    @Override
                    public void onSend() {
                        player.getPA().movePlayer(3118, 3247);
                    }
                })
                .addNpcChat("Draynor Representative", NPC_ID,
                        "Moving on we have the Apothecary, the finest in the land!",
                        "I'd recommend you stop by here if you ever want to learn",
                        "about making potions or foraging for ingredients."
                ).addDialogueAction(new Dialog() {
                    @Override
                    public void onSend() {
                        player.getPA().movePlayer(3117, 3230);
                    }
                }).addNpcChat("Draynor Representative", NPC_ID,
                        "Here is the famed Hall of Heroes.",
                        "Home of the finest adventurers, or at least the ones",
                        "who have achieved max level in a skill."
                ).addDialogueAction(new Dialog() {
                    @Override
                    public void onSend() {
                        player.getPA().movePlayer(3084, 3212);
                    }
                }).addNpcChat("Draynor Representative", NPC_ID,
                        "We have just about everything an adventurer",
                        "could ever need. Here's your initiative package",
                        "as promised to help get you started."
                ).addDialogueAction(new Dialog() {
                    @Override
                    public void onSend() {
                        player.getItems().addItem(6198, 1);
                        player.getItems().addItem(9704, 1);
                        player.getItems().addItem(9703, 1);
                        player.getItems().addItem(9705, 1);
                        player.getItems().addItem(9706, 50);
                        player.getItems().addItem(556, 30);
                        player.getItems().addItem(558, 30);
                        player.getItems().addItem(303, 1);
                        player.getItems().addItem(1351, 1);
                        player.getItems().addItem(1265, 1);
                        player.getItems().addItem(590, 1);
                        player.getItems().addItem(316, 10);
                    }
                }).addItemStatement(9703, "You receive your Initiative Package")
//                                .addDialogueAction(new Dialog() {
//                                    @Override
//                                    public void onSend() {
//                                        c.getPA().showInterface(3559);
//                                        c.canChangeAppearance = true;
//                                    }
//                                })
                .build();
    }
    
}
