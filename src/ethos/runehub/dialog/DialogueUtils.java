package ethos.runehub.dialog;

import ethos.model.players.Player;

public class DialogueUtils {

    public static void sendEstateAgentTourDialog(Player c) {
        c.getDH().sendDialogueSequence(
                new DialogSequence.DialogSequenceBuilder(c)
                        .setActionLocked(true)
                        .setMovementRestricted(true)
                        .addDialogueAction(new Dialog() {
                            @Override
                            public void onSend() {
                                c.getPA().movePlayer(3098, 3225);
                            }
                        })
                        .addNpcChat("Estate Agent", 5419,
                                "Here you'll be able to build your Heraldry Station.",
                                "Used for the creation of certain items."
                        )
                        .addDialogueAction(new Dialog() {
                            @Override
                            public void onSend() {
                                c.getPA().movePlayer(3100, 3225);
                            }
                        })
                        .addNpcChat("Estate Agent", 5419,
                                "Here you'll be able to build your Crystal ball enchanter.",
                                "Used to imbue power magic."
                        )
                        .addDialogueAction(new Dialog() {
                            @Override
                            public void onSend() {
                                c.getPA().movePlayer(3101, 3225);
                            }
                        })
                        .addNpcChat("Estate Agent", 5419,
                                "Here you'll be able to build a display for your jewellery.",
                                "Useful for using it's effects."
                        )
                        .addDialogueAction(new Dialog() {
                            @Override
                            public void onSend() {
                                c.getPA().movePlayer(3094, 3225);
                            }
                        })
                        .addNpcChat("Estate Agent", 5419,
                                "Here you'll be able to build a pool.",
                                "Useful for refreshing and recharging after a long day."
                        )
                        .addDialogueAction(new Dialog() {
                            @Override
                            public void onSend() {
                                c.getPA().movePlayer(3093, 3223);
                            }
                        })
                        .addNpcChat("Estate Agent", 5419,
                                "Here you'll be able to build a lectern.",
                                "You can use this create teleport tablets"
                        )
                        .addDialogueAction(new Dialog() {
                            @Override
                            public void onSend() {
                                c.getPA().movePlayer(3099, 3222);
                            }
                        })
                        .addNpcChat("Estate Agent", 5419,
                                "Here is a space for your Magic Altar.",
                                "You can use this to swap spell books."
                        )
                        .addNpcChat("Estate Agent", 5419,
                                "I'm not really sure what the telescope is used for...",
                                "I'm sure you'll figure it out though"
                        )
                        .addDialogueAction(new Dialog() {
                            @Override
                            public void onSend() {
                                c.getPA().movePlayer(3097, 3227);
                            }
                        })
                        .build()
        );
    }
}
