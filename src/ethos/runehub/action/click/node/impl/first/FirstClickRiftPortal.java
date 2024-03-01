package ethos.runehub.action.click.node.impl.first;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.action.click.node.ClickNodeAction;
import ethos.runehub.content.instance.impl.rift.RiftInstanceController;
import ethos.runehub.content.rift.Rift;
import ethos.runehub.content.rift.RiftDifficulty;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.node.Node;

import java.text.NumberFormat;

public class FirstClickRiftPortal extends ClickNodeAction {

    @Override
    protected void onActionStart() {

    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                .addOptions(getRiftOptions())
                .build());
        this.stop();
    }

    @Override
    protected void onUpdate() {

    }

    private boolean hasEntryFee() {
        if (this.getActor().getItems().playerHasItem(getRiftKey(), getFee())) {
            this.getActor().getItems().deleteItem2(getRiftKey(), getFee());
            this.getActor().sendMessage("You pay the entry fee and the rift opens.");
            return true;
        }
        this.getActor().sendMessage("You do not have enough @" + getRiftKey() + " to cover the entry fee #" +getFee());
        return false;
    }

    private DialogOption[] getRiftOptions() {
        return new DialogOption[]{
                new DialogOption("Nephalem Rift") {
                    @Override
                    public void onAction() {
                        riftType = 0;
                        getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(getActor())
                                .addOptions(getDifficultyOptionsPageOne())
                                .build());
                    }
                },
                new DialogOption("Greater Rift") {
                    @Override
                    public void onAction() {
                        riftType = 1;
                        getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(getActor())
                                .addOptions(getDifficultyOptionsPageOne())
                                .build());
                    }
                }
        };
    }

    private DialogOption[] getConfirmationOptions() {
        return new DialogOption[]{
                new DialogOption("Yes") {
                    @Override
                    public void onAction() {
                        if (hasEntryFee()) {
                            if (riftType == 0) {
                                Node node = new Node(13615,3087,3250,0,0,10);
                                getActor().getPA().checkObjectSpawn(node);
//                                Rift rift = new NephalemRift(getActor(),difficulty);
                                getActor().getAttributes().addInstanceNode(12338,node);
                                RiftInstanceController.getInstance().createNephalemRiftInstanceForPlayer(getActor(),difficulty);
//                                getActor().getAttributes().setRift(rift);
//                                Server.getEventHandler().submit(rift);
                            } else if(riftType == 1) {
                                getActor().getPA().checkObjectSpawn(13617,3087,3250,0,10);
                                Rift rift = new Rift(getActor(),difficulty);
                                getActor().getAttributes().setRift(rift);
                                Server.getEventHandler().submit(rift);
                            }
                            getActor().getPA().closeAllWindows();
                        }
                    }
                },
                new DialogOption("No") {
                    @Override
                    public void onAction() {
                        getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(getActor())
                                .addOptions(getDifficultyOptionsPageTwo())
                                .build());
                    }
                }
        };
    }

    private void sendConfirmationDialog() {
        this.getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getActor())
                        .addItemStatement(getRiftKey(),"The entry fee is " + NumberFormat.getInstance().format(getFee()))
                .addOptions(getConfirmationOptions())
                .build());
    }

    private int getRiftKey() {
        switch (riftType) {
            case 0:
                return 995;
            case 1:
                return 995; //todo change to keystone
        }
        return 995;
    }

    private int getFee() {
        switch (difficulty) {

            case NORMAL:
            case HARD :
            case MASTER:
            case TORMENT:
            case TORMENT_II :
            case TORMENT_III :
            case TORMENT_IV:
            case TORMENT_V :
                return Rift.NEPHALEM_RIFT_COST;
        }
        return Rift.NEPHALEM_RIFT_COST;
    }

    private DialogOption[] getDifficultyOptionsPageOne() {
        return new DialogOption[]{
                new DialogOption(RiftDifficulty.NORMAL.toString()) {
                    @Override
                    public void onAction() {
                        difficulty = RiftDifficulty.NORMAL;
                        sendConfirmationDialog();
                    }
                },
                new DialogOption(RiftDifficulty.HARD.toString()) {
                    @Override
                    public void onAction() {
                        difficulty = RiftDifficulty.HARD;
                        sendConfirmationDialog();
                    }
                },
                new DialogOption(RiftDifficulty.MASTER.toString()) {
                    @Override
                    public void onAction() {
                        difficulty = RiftDifficulty.MASTER;
                        sendConfirmationDialog();
                    }
                },
                new DialogOption(RiftDifficulty.TORMENT.toString()) {
                    @Override
                    public void onAction() {
                        difficulty = RiftDifficulty.TORMENT;
                        sendConfirmationDialog();
                    }
                },
                new DialogOption("Next") {
                    @Override
                    public void onAction() {
                        getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(getActor())
                                .addOptions(getDifficultyOptionsPageTwo())
                                .build());
                    }
                }
        };
    }

    private DialogOption[] getDifficultyOptionsPageTwo() {
        return new DialogOption[]{
                new DialogOption(RiftDifficulty.TORMENT_II.toString()) {
                    @Override
                    public void onAction() {
                        difficulty = RiftDifficulty.TORMENT_II;
                        sendConfirmationDialog();
                    }
                },
                new DialogOption(RiftDifficulty.TORMENT_III.toString()) {
                    @Override
                    public void onAction() {
                        difficulty = RiftDifficulty.TORMENT_III;
                        sendConfirmationDialog();
                    }
                },
                new DialogOption(RiftDifficulty.TORMENT_IV.toString()) {
                    @Override
                    public void onAction() {
                        difficulty = RiftDifficulty.TORMENT_IV;
                        sendConfirmationDialog();
                    }
                },
                new DialogOption(RiftDifficulty.TORMENT_V.toString()) {
                    @Override
                    public void onAction() {
                        difficulty = RiftDifficulty.TORMENT_V;
                        sendConfirmationDialog();
                    }
                },
                new DialogOption("Previous") {
                    @Override
                    public void onAction() {
                        getActor().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(getActor())
                                .addOptions(getDifficultyOptionsPageOne())
                                .build());
                    }
                }
        };
    }

    public FirstClickRiftPortal(Player attachment, int nodeId, int nodeX, int nodeY) {
        super(attachment, 1, nodeId, nodeX, nodeY);
    }

    private RiftDifficulty difficulty;
    private int riftType;
}
