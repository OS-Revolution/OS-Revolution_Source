package ethos.runehub.entity.mob.passive;

import ethos.model.players.Player;
import ethos.runehub.action.click.npc.ClickNpcAction;
import ethos.runehub.content.gambling.blackjack.BettingPosition;
import ethos.runehub.content.gambling.blackjack.Blackjack;
import ethos.runehub.content.gambling.blackjack.BlackjackPlayer;
import ethos.runehub.content.gambling.blackjack.impl.*;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;

public class GamblingManHostileMob extends PassiveHostileMob {

    private static GamblingManHostileMob instance = null;

    public static GamblingManHostileMob getInstance(int index) {
        if (instance == null)
            instance = new GamblingManHostileMob(index);
        return instance;
    }

    @Override
    public ClickNpcAction talkTo(Player player, int index) {
        return new ClickNpcAction(player, 1, this.getId(), index) {
            @Override
            protected void onActionStart() {
                this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                        .addNpcChat("Stranger", getNpcId(), "Can I interest you in a game of Blackjack?")
                        .addOptions(new DialogOption("Yes") {
                                        @Override
                                        public void onAction() {
                                            getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(getActor())
                                                    .addNpcChat("Stranger", getNpcId(), "How many decks do you want to play with?")
                                                    .addOptions(getDeckOptionsPageOne(player, true))
                                                    .build());
                                        }
                                    },
                                new DialogOption("No") {
                                    @Override
                                    public void onAction() {
                                        getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(getActor())
                                                .addStatement("The Stranger goes back to ignoring you.")
                                                .build()
                                        );
                                    }
                                })
                        .build());
            }

            @Override
            protected void onActionStop() {

            }

            @Override
            protected void onTick() {

            }

            @Override
            protected void onUpdate() {

            }
        };
    }

    public Blackjack getBlackjack() {
        return blackjack;
    }

    private boolean hasWager(Player player, int wager) {
        return player.getItems().playerHasItem(995, wager);
    }

    private boolean offerWager(Player player, int wager) {
        if (this.hasWager(player, wager)) {
            player.getItems().deleteItem2(995, wager);
            player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                    .setActionLocked(true)
                    .setMovementRestricted(true)
                    .addStatement("The game will start soon.",
                            "If you leave the area, logout,",
                            "or enter combat you will be disqualified",
                            "without refund.")
                    .build());
            return true;
        } else {
            player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                    .addStatement("You don't have enough coins to cover your wager.")
                    .build());
        }
        return false;
    }

    private DialogOption[] getWagerOptions(Player player, boolean create) {
        return new DialogOption[]{
                new DialogOption("10k") {
                    @Override
                    public void onAction() {
                        if (create) {
                            if (offerWager(player, 10000)) {
                                BettingPosition position = new BettingPosition();
                                BlackjackPlayer blackjackPlayer = new BlackjackPlayer(player);
                                blackjackPlayer.setWager(10000);
                                position.getPlayers().add(blackjackPlayer);
                                blackjack.start(position);
                            }
                        }
                    }
                },
                new DialogOption("100k") {
                    @Override
                    public void onAction() {
                        if (create) {
                            if (offerWager(player, 100000)) {
                                BettingPosition position = new BettingPosition();
                                BlackjackPlayer blackjackPlayer = new BlackjackPlayer(player);
                                blackjackPlayer.setWager(100000);
                                position.getPlayers().add(blackjackPlayer);
                                blackjack.start(position);
                            }
                        }
                    }
                },
                new DialogOption("1m") {
                    @Override
                    public void onAction() {
                        if (create) {
                            if (offerWager(player, 1000000)) {
                                BettingPosition position = new BettingPosition();
                                BlackjackPlayer blackjackPlayer = new BlackjackPlayer(player);
                                blackjackPlayer.setWager(1000000);
                                position.getPlayers().add(blackjackPlayer);
                                blackjack.start(position);
                            }
                        }
                    }
                },
                new DialogOption("10m") {
                    @Override
                    public void onAction() {
                        if (create) {
                            if (offerWager(player, 10000000)) {
                                BettingPosition position = new BettingPosition();
                                BlackjackPlayer blackjackPlayer = new BlackjackPlayer(player);
                                blackjackPlayer.setWager(10000000);
                                position.getPlayers().add(blackjackPlayer);
                                blackjack.start(position);
                            }
                        }
                    }
                },
                new DialogOption("100m") {
                    @Override
                    public void onAction() {
                        if (create) {
                            if (offerWager(player, 100000000)) {
                                BettingPosition position = new BettingPosition();
                                BlackjackPlayer blackjackPlayer = new BlackjackPlayer(player);
                                blackjackPlayer.setWager(100000000);
                                position.getPlayers().add(blackjackPlayer);
                                blackjack.start(position);
                            }
                        }
                    }
                }
        };
    }

    private void sendWagerDialogSequence(Player player, boolean create) {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat("Stranger", 5055, "How much will you wager?")
                .addOptions(getWagerOptions(player, create))
                .build());
    }

    private DialogOption[] getDeckOptionsPageOne(Player player, boolean create) {
        return new DialogOption[]{
                new DialogOption("One (3:2)") {
                    @Override
                    public void onAction() {
                        blackjack = new SingleDeckBlackjack();
                        sendWagerDialogSequence(player, create);
                    }
                },
                new DialogOption("Two (7:4)") {
                    @Override
                    public void onAction() {
                        blackjack = new TwoDeckBlackjack();
                        sendWagerDialogSequence(player, create);
                    }
                },
                new DialogOption("Three (7:4)") {
                    @Override
                    public void onAction() {
                        blackjack = new ThreeDeckBlackjack();
                        sendWagerDialogSequence(player, create);
                    }
                },
                new DialogOption("Four (7:4)") {
                    @Override
                    public void onAction() {
                        blackjack = new FourDeckBlackjack();
                        sendWagerDialogSequence(player, create);
                    }
                },
                new DialogOption("Next") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getDeckOptionsPageTwo(player, create))
                                .build());
                    }
                }
        };
    }

    private DialogOption[] getDeckOptionsPageTwo(Player player, boolean create) {
        return new DialogOption[]{
                new DialogOption("Five (2:1)") {
                    @Override
                    public void onAction() {
                        blackjack = new FiveDeckBlackjack();
                        sendWagerDialogSequence(player, create);
                    }
                },
                new DialogOption("Six (2:1)") {
                    @Override
                    public void onAction() {
                        blackjack = new SixDeckBlackjack();
                        sendWagerDialogSequence(player, create);
                    }
                },
                new DialogOption("Seven (2:1)") {
                    @Override
                    public void onAction() {
                        blackjack = new SevenDeckBlackjack();
                        sendWagerDialogSequence(player, create);
                    }
                },
                new DialogOption("Eight (5:2)") {
                    @Override
                    public void onAction() {
                        blackjack = new EightDeckBlackjack();
                        sendWagerDialogSequence(player, create);
                    }
                },
                new DialogOption("Previous") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getDeckOptionsPageOne(player, create))
                                .build());
                    }
                }
        };
    }

    private GamblingManHostileMob(int npcIndex) {
        super(5055);
        this.npcIndex = npcIndex;
        this.blackjack = new SingleDeckBlackjack();
    }

    private Blackjack blackjack;
    private final int npcIndex;
}
