package ethos.runehub.entity.player.action.impl.item;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.player.action.impl.ClickItemAction;

public class SecondClickWoodcuttingSkillRing extends ClickItemAction {


    @Override
    public void perform() {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addOptions(new DialogOption("Teleport") {
                                @Override
                                public void onAction() {
                                    player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                            .addOptions(getOptionsPageOne(player))
                                            .build());
                                }
                            },
                        new DialogOption("Toggle Burn Mode") {
                            @Override
                            public void onAction() {
                                player.getContext().getPlayerSaveData().setBurnLogs(!player.getContext().getPlayerSaveData().isBurnLogs());
                                player.sendMessage("You toggle burn mode $" + RunehubUtils.getBooleanAsOnOrOff(player.getContext().getPlayerSaveData().isBurnLogs()));
                                player.getAttributes().getActiveDialogSequence().next();
                            }
                        })
                .build());
    }

    private void doTeleport(int x, int y) {
        if ((itemId == 8125 && player.getContext().getPlayerSaveData().getDailyEliteWoodcuttingRingTeleports() > 0) || player.getContext().getPlayerSaveData().getDailyWoodcuttingTeleports() > 0) {
            if (itemId == 8125) {
                player.getContext().getPlayerSaveData().setDailyEliteWoodcuttingRingTeleports(player.getContext().getPlayerSaveData().getDailyEliteWoodcuttingRingTeleports() - 1);
                player.sendMessage("You have $" + player.getContext().getPlayerSaveData().getDailyEliteWoodcuttingRingTeleports() + " daily teleports remaining.");
            } else {
                player.getContext().getPlayerSaveData().setDailyWoodcuttingTeleports(player.getContext().getPlayerSaveData().getDailyWoodcuttingTeleports() - 1);
                player.sendMessage("You have $" + player.getContext().getPlayerSaveData().getDailyWoodcuttingTeleports() + " daily teleports remaining.");
            }
            player.getPA().spellTeleport(x, y, 0, false);

        } else {
            player.sendMessage("You've used all your daily teleports.");
        }
        player.getAttributes().getActiveDialogSequence().next();
    }

    private DialogOption[] getOptionsPageOne(Player player) {
        return new DialogOption[]{
                new DialogOption("Trees") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(new DialogOption("West Varrock") {
                                                @Override
                                                public void onAction() {
                                                    doTeleport(3129, 3435);
                                                }
                                            },
                                        new DialogOption("East Varrock") {
                                            @Override
                                            public void onAction() {
                                                doTeleport(3280, 3476);
                                            }
                                        })
                                .build());
                    }
                },
                new DialogOption("Oak trees") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(new DialogOption("West Varrock") {
                                                @Override
                                                public void onAction() {
                                                    doTeleport(3129, 3435);
                                                }
                                            },
                                        new DialogOption("East Varrock") {
                                            @Override
                                            public void onAction() {
                                                doTeleport(3280, 3476);
                                            }
                                        })
                                .build());
                    }
                },
                new DialogOption("Willow trees") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(new DialogOption("Catherby") {
                                                @Override
                                                public void onAction() {
                                                    doTeleport(2775, 3434);
                                                }
                                            },
                                        new DialogOption("Taverly") {
                                            @Override
                                            public void onAction() {
                                                doTeleport(2925, 3405);
                                            }
                                        })
                                .build());
                    }
                },
                new DialogOption("Maple trees") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(new DialogOption("Seers' Village") {
                                                @Override
                                                public void onAction() {
                                                    doTeleport(2727, 3500);
                                                }
                                            },
                                        new DialogOption("Sinclair Mansion") {
                                            @Override
                                            public void onAction() {
                                                doTeleport(2721, 3556);
                                            }
                                        })
                                .build());
                    }
                },
                new DialogOption("Next") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getOptionsPageTwo(player))
                                .build());
                    }
                }
        };
    }

    private DialogOption[] getOptionsPageTwo(Player player) {
        return new DialogOption[]{
                new DialogOption("Yew trees") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(new DialogOption("Seers' Village") {
                                                @Override
                                                public void onAction() {
                                                    doTeleport(2711, 3462);
                                                }
                                            },
                                        new DialogOption("Catherby") {
                                            @Override
                                            public void onAction() {
                                                doTeleport(2775, 3434);
                                            }
                                        })
                                .build());
                    }
                },
                new DialogOption("Magic trees") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(new DialogOption("Ranging Guild") {
                                                @Override
                                                public void onAction() {
                                                    doTeleport(2693, 3425);
                                                }
                                            },
                                        new DialogOption("Mage Training Arena") {
                                            @Override
                                            public void onAction() {
                                                doTeleport(3362, 3295);
                                            }
                                        })
                                .build());
                    }
                },
                new DialogOption("Previous") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getOptionsPageOne(player))
                                .build());
                    }
                }
        };
    }

    public SecondClickWoodcuttingSkillRing(Player player, int playerX, int playerY, int itemId) {
        super(player, playerX, playerY, itemId);
    }
}
