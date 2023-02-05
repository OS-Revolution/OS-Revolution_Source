package ethos.runehub.action.click.node.impl.first;

import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.content.gambling.blackjack.impl.*;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.ui.impl.SmeltingUI;
import ethos.runehub.ui.impl.crafting.*;

import java.text.NumberFormat;

public class FirstClickFurnace extends ClickNodeAction {

    @Override
    protected void onActionStart() {
        this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                .addOptions(getOptionsPageOne(getActor()))
                .build());

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    private DialogOption[] getOptionsPageOne(Player player) {
        return new DialogOption[]{
                new DialogOption("Smelt Ore") {
                    @Override
                    public void onAction() {
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new SmeltingUI(player));
                    }
                },
                new DialogOption("Craft Bracelet") {
                    @Override
                    public void onAction() {
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new BraceletMakingUI(player));
                    }
                },
                new DialogOption("Craft Amulet") {
                    @Override
                    public void onAction() {
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new AmuletMakingUI(player));
                    }
                },
                new DialogOption("Craft Ring") {
                    @Override
                    public void onAction() {
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new RingMakingUI(player));
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

    private DialogOption[] getOptionsPageTwo(Player player ) {
        return new DialogOption[]{
                new DialogOption("Craft Necklace") {
                    @Override
                    public void onAction() {
                        System.out.println(1);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new NecklaceMakingUI(player));
                    }
                },
                new DialogOption("Craft Tiara") {
                    @Override
                    public void onAction() {
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new TiaraMakingUI(player));
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

    public FirstClickFurnace(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }
}
