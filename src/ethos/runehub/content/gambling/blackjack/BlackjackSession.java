package ethos.runehub.content.gambling.blackjack;

import com.google.common.base.Preconditions;
import com.thoughtworks.xstream.io.xml.XppDomReader;
import ethos.event.Event;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.TimeUtils;
import ethos.runehub.content.gambling.cards.Card;
import ethos.runehub.content.gambling.cards.Rank;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.util.Misc;
import ethos.util.PreconditionUtils;
import org.runehub.api.util.IDManager;

import java.time.Duration;
import java.util.*;
import java.util.logging.Logger;

public class BlackjackSession extends Event<Blackjack> {

    private static final int OPEN_DURATION = TimeUtils.getMsAsTicks(Duration.ofSeconds(10).toMillis());
    private static final int SESSION_DURATION = TimeUtils.getMsAsTicks(Duration.ofMinutes(2).toMillis());
    private static final int TURN_DURATION = TimeUtils.getMsAsTicks(Duration.ofSeconds(10).toMillis());

    @Override
    public void stop() {
        messageSession("This session is now over. Thanks for playing!");
        position.getPlayers().forEach(player -> player.getPlayer().getContext().getPlayerSaveData().setBlackjackGamesPlayed(player.getPlayer().getContext().getPlayerSaveData().getBlackjackGamesPlayed() + 1));
        super.stop();
    }

    @Override
    public void execute() {
        if (this.getElapsedTicks() < OPEN_DURATION) { //waiting for players to join
            Logger.getGlobal().finest("Queuing for session: " + (OPEN_DURATION - this.getElapsedTicks()));
            messageSession("Turn starts in: $" + (TURN_DURATION - turnTickCounter));
//            messageSession("Game starts in: $" + (OPEN_DURATION - this.getElapsedTicks()));
        } else if (this.getElapsedTicks() >= SESSION_DURATION) { //session is at time limit
            Logger.getGlobal().finest("Ending Session");
            this.stop();
        } else if (turnTickCounter >= TURN_DURATION) {
            if (turn == 0) { //duration of one turn
                this.dealPlayerFirstHand();
                this.dealDealerFirstHand();
            } else if (turn == 1) {
                this.requestPlayerAction();
            } else if (turn == 2) {
                this.resolveDealersHand();
                this.determineWinners();
            }
            turn++;
            if (turn < 3)
                messageSession("Next turn starts in: $" + (TURN_DURATION));
            Logger.getGlobal().finest("Start Turn: " + turn + " Session ends in: " + (SESSION_DURATION - this.getElapsedTicks()));
            turnTickCounter = 0;
        }
        turnTickCounter++;
        Logger.getGlobal().finest("Time Until Next Turn: " + (TURN_DURATION - turnTickCounter));
    }

    private double getOdds() {
        switch (attachment.getDecks().length) {
            case 1:
                return 2.5;
            case 2:
            case 3:
            case 4:
                return 2.75;
            case 5:
            case 6:
            case 7:
                return 3;
            case 8:
                return 3.5;
        }
        return 2.5;
    }

    private int getPayout(int wager) {
        return (int) Math.round(wager * getOdds());
    }

    private void determineWinners() {
        messageSession("$Dealer's Hand: $" + dealer.getHandTotal());
        Logger.getGlobal().finest("Dealer's Hand: " + dealer.getHand());

        position.getPlayers().forEach(player -> {
            Logger.getGlobal().finest("Player Hand: " + player.getHand());
            if (dealer.getHandTotal() < 21 && player.getHandTotal() < 21 && player.getHandTotal() > dealer.getHandTotal() || dealer.getHandTotal() > 21) {
                message(player, "$" + player + " won the round!");
                player.getPlayer().getContext().getPlayerSaveData().setBlackjackGamesWon(player.getPlayer().getContext().getPlayerSaveData().getBlackjackGamesWon() + 1);
                if (player.getPlayer().getContext().getPlayerSaveData().getBlackjackGamesWon() == 100) {
                    player.getPlayer().getItems().addItemUnderAnyCircumstance(13658, 1);
                    player.getPlayer().sendMessage("The stranger gives you a @13658 for winning $100 games.");
                }
                player.getPlayer().getItems().addItem(995, getPayout(player.getWager()));
            } else if (dealer.getHandTotal() == 21) {
                message(player, "$Dealer won the round!");
            } else if (dealer.getHandTotal() == 21 && player.getHandTotal() == 21) {
                message(player, "It's a $push. All bets have been returned.");
                player.getPlayer().getItems().addItem(995, player.getWager());
            } else {
                message(player, "You've $lost.");
            }
        });
        this.stop();
    }

    private void dealPlayerFirstHand() {
        position.getPlayers().forEach(player -> {
            if (validate(player.getPlayer())) {
                hit(player);
                hit(player);
            }
        });
    }

    private void requestPlayerAction() {
        position.getPlayers().forEach(player -> {
            if (validate(player.getPlayer())) {
                player.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player.getPlayer())
                        .setMovementRestricted(true)
                        .setActionLocked(true)
                        .addOptions(getActionOptions(player))
                        .build());
            }
        });
    }

    private void dealDealerFirstHand() {
        hit(dealer);
        Card hole = attachment.draw();
        dealer.getHand().add(hole);
        Logger.getGlobal().finest("Dealer's Hole Card: " + hole);
    }

    private void resolveDealersHand() {
        while (dealer.getHandTotal() <= 17) {
            Card card = attachment.draw();
            if (card.getRank() == Rank.ACE) {
                int totalA = 21 - (dealer.getHandTotal() + 1);
                int totalB = 21 - (dealer.getHandTotal() + 11);
                if (totalA > 0 && totalA <= totalB) {
                    card.setValue(1);
                } else if (totalB > 0 && totalB <= totalA) {
                    card.setValue(11);
                }
            }
            messageSession(dealer + " Received: $" + Misc.capitalize(card.getRank().name().toLowerCase()) + " of $" + Misc.capitalize(card.getSuit().name().toLowerCase()), dealer);
            addCardToHand(dealer, card);
        }
    }

    private void addCardToHand(BlackjackPlayer player, Card card) {
        player.getHand().add(card);
        if (turn == 1) {
            if (player.getHandTotal() == 21 && dealer.getHandTotal() != 21) {
                messageSession(player + " got a $Blackjack and wins!");
                message(player, "You got a $Blackjack!");
                player.getPlayer().getItems().addItem(995, getPayout(player.getWager()));
                player.getPlayer().getContext().getPlayerSaveData().setBlackjackGamesWon(player.getPlayer().getContext().getPlayerSaveData().getBlackjackGamesWon() + 1);
                if (player.getPlayer().getContext().getPlayerSaveData().getBlackjackGamesWon() == 100) {
                    player.getPlayer().getItems().addItemUnderAnyCircumstance(13658, 1);
                    player.getPlayer().sendMessage("The stranger gives you a @13658 for winning $100 games.");
                }
                player.getPlayer().getContext().getPlayerSaveData().setBlackjackTotalWagers(player.getPlayer().getContext().getPlayerSaveData().getBlackjackTotalWagers() + player.getWager());
                this.stop();
            } else if (player.getHandTotal() == 21 && dealer.getHandTotal() == 21) {
                messageSession("It's a $push. All bets have been returned.");
                player.getPlayer().getItems().addItem(995, player.getWager());
                this.stop();
            }
        }
    }

    private boolean validate(Player player) {
        try {
            Preconditions.checkArgument(PreconditionUtils.notNull(player), "Actor is Null");
            Preconditions.checkArgument(PreconditionUtils.notNull(player.getSession()), "Session is Null");
            Preconditions.checkArgument(PreconditionUtils.isFalse(player.disconnected), "Actor is Disconnected");
            Preconditions.checkArgument(PreconditionUtils.isFalse(player.hitUpdateRequired), "You can't do this while in combat");
            return true;
        } catch (Exception e) {
            if (player != null) {
                player.sendMessage(e.getMessage());
            } else {
                Logger.getGlobal().warning(e.getMessage());
            }
        }
        return false;
    }

    private void hit(BlackjackPlayer player) {
        Card card = attachment.draw();
        message(player, "Received: $" + Misc.capitalize(card.getRank().name().toLowerCase()) + " of $" + Misc.capitalize(card.getSuit().name().toLowerCase()));

        if (player.getPlayer() != null) {
            messageSession(player.getPlayer().playerName + " received: " + card.getRank() + " of " + card.getSuit(), player);
            if (card.getRank() == Rank.ACE) {
                player.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player.getPlayer())
                        .addOptions(getAceOptions(player, card))
                        .build());
            } else {
                this.addCardToHand(player, card);
            }
        } else {
            messageSession("Dealer Received: $" + Misc.capitalize(card.getRank().name().toLowerCase()) + " of $" + Misc.capitalize(card.getSuit().name().toLowerCase()), player);
            if (card.getRank() == Rank.ACE) {
                int totalA = 21 - (dealer.getHandTotal() + 1);
                int totalB = 21 - (dealer.getHandTotal() + 11);
                if (totalA > 0 && totalA <= totalB) {
                    card.setValue(1);
                } else if (totalB > 0 && totalB <= totalA) {
                    card.setValue(11);
                }
            }
            this.addCardToHand(dealer, card);
        }
    }

    private void stand(BlackjackPlayer player) {
        message(player, "Standing at: $" + player.getHandTotal());
    }

    private void doubleDown(BlackjackPlayer player) {
        if (player.getPlayer().getItems().playerHasItem(995, player.getWager())) {
            player.getPlayer().getItems().deleteItem2(995, player.getWager());
            player.setWager(player.getWager() * 2);
            message(player, "Doubled wager to: $" + (player.getWager()));
            player.getPlayer().getContext().getPlayerSaveData().setBlackjackTotalWagers(player.getPlayer().getContext().getPlayerSaveData().getBlackjackTotalWagers() + player.getWager());
            hit(player);
        }
    }

    private void surrender(BlackjackPlayer player) {
        player.getPlayer().getItems().addItem(995, (player.getWager() / 2));
        message(player, "You've surrendered and received $50% of your initial wager back.");
        this.stop();
    }

    private void message(BlackjackPlayer player, String message) {
        if (player.getPlayer() != null) {
            player.getPlayer().sendMessage("^Blackjack " + message);
        } else {
            System.out.println("[" + signature + " Dealer]" + message);
        }
    }

    private void messageSession(String message, BlackjackPlayer exclude) {
        position.getPlayers().stream().filter(Objects::nonNull).filter(blackjackPlayer -> blackjackPlayer != exclude).forEach(blackjackPlayer -> message(blackjackPlayer, message));
    }

    private void messageSession(String message) {
        position.getPlayers().stream().filter(Objects::nonNull).forEach(blackjackPlayer -> message(blackjackPlayer, message));
    }

    private DialogOption[] getAceOptions(BlackjackPlayer player, Card card) {
        return new DialogOption[]{
                new DialogOption("One") {
                    @Override
                    public void onAction() {
                        card.setValue(1);
                        addCardToHand(player, card);
                        player.getPlayer().getAttributes().getActiveDialogSequence().next();
                    }
                },
                new DialogOption("Eleven") {
                    @Override
                    public void onAction() {
                        card.setValue(11);
                        addCardToHand(player, card);
                        player.getPlayer().getAttributes().getActiveDialogSequence().next();
                    }
                }
        };
    }

    private DialogOption[] getActionOptions(BlackjackPlayer player) {
        return new DialogOption[]{
                new DialogOption("Hit") {
                    @Override
                    public void onAction() {
                        player.setTurnAction(Blackjack.Action.HIT);
                        hit(player);
                        player.getPlayer().getAttributes().getActiveDialogSequence().next();
                    }
                },
                new DialogOption("Stand") {
                    @Override
                    public void onAction() {
                        player.setTurnAction(Blackjack.Action.STAND);
                        stand(player);
                        player.getPlayer().getAttributes().getActiveDialogSequence().next();
                    }
                },
                new DialogOption("Double Down") {
                    @Override
                    public void onAction() {
                        player.setTurnAction(Blackjack.Action.DOUBLE_DOWN);
                        doubleDown(player);
                        player.getPlayer().getAttributes().getActiveDialogSequence().next();
                    }
                },
                new DialogOption("Surrender") {
                    @Override
                    public void onAction() {
                        player.setTurnAction(Blackjack.Action.SURRENDER);
                        surrender(player);
                        player.getPlayer().getAttributes().getActiveDialogSequence().next();
                    }
                }
        };
    }

    private void printBestAction() {
        Card playerFirstCard = attachment.getCards().toArray(new Card[0])[0];
        Card playerSecondCard = attachment.getCards().toArray(new Card[0])[1];

        Card dealerFirstCard = attachment.getCards().toArray(new Card[0])[2];
        Card dealerHoleCard = attachment.getCards().toArray(new Card[0])[3];

        Card fourthCard = attachment.getCards().toArray(new Card[0])[4];
        Card fifthCard = attachment.getCards().toArray(new Card[0])[5];

        int playerInitialHandTotal = playerFirstCard.getValue() + playerSecondCard.getValue();
        int dealerInitialHandTotal = dealerFirstCard.getValue() + dealerHoleCard.getValue();

        if (playerInitialHandTotal == 21 && dealerInitialHandTotal != 21) {
            System.out.println("Player has blackjack");
        } else if(playerInitialHandTotal == 21 && dealerInitialHandTotal == 21) {
            System.out.println("Game is a draw");
        } else if(playerInitialHandTotal < 21 && dealerInitialHandTotal == 21) {
            System.out.println("Dealer has blackjack");
        } else if((playerInitialHandTotal + fourthCard.getValue()) == 21) {

        }
    }

    public BlackjackSession(BettingPosition position, Blackjack attachment) {
        super("blackjack-session-" + IDManager.getUUID(), attachment, 1);
        this.position = position;
        this.dealer = new BlackjackPlayer(null);
    }

    private final BettingPosition position;
    private int turnTickCounter;
    private int turn;
    private final BlackjackPlayer dealer;

}
