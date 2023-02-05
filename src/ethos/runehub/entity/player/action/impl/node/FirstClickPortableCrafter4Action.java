package ethos.runehub.entity.player.action.impl.node;

import ethos.model.players.Player;
import ethos.runehub.dialog.DialogOption;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.player.action.impl.node.ClickNodeAction;
import ethos.runehub.ui.impl.crafting.*;

public class FirstClickPortableCrafter4Action extends ClickNodeAction {

    @Override
    public void perform() {
        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                .addOptions(getOptionsPageOne())
                .build());
    }

    private DialogOption[] getOptionsPageOne() {
        return new DialogOption[]{
                new DialogOption("Craft  Hide") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getLeatherOptions1())
                                .build());
                    }
                },
                new DialogOption("Craft Jewellery") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getJewelleryOptions1())
                                .build());
                    }
                },
        };
    }

    private DialogOption[] getLeatherOptions1( ) {
        return new DialogOption[]{
                new DialogOption("Craft Leather") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new LeatherCraftingUI(player,1741));
                    }
                },
                new DialogOption("Craft Hardleather") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new LeatherCraftingUI(player,1743));
                    }
                },
                new DialogOption("Craft Green Dragon Leather") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new LeatherCraftingUI(player,1745));
                    }
                },
                new DialogOption("Craft Blue Dragon Leather") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new LeatherCraftingUI(player,2505));
                    }
                },
                new DialogOption("Next") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getLeatherOptions2())
                                .build());
                    }
                }
        };
    }

    private DialogOption[] getLeatherOptions2() {
        return new DialogOption[]{
                new DialogOption("Craft Red Dragon Leather") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new LeatherCraftingUI(player,2507));
                    }
                },
                new DialogOption("Craft Black Dragon Leather") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new LeatherCraftingUI(player,2509));
                    }
                },
                new DialogOption("Craft Snakeskin") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new LeatherCraftingUI(player,6289));
                    }
                },
                new DialogOption("Craft Yak-Hide") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new LeatherCraftingUI(player,10820));
                    }
                },
                new DialogOption("Previous") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getLeatherOptions1())
                                .build());
                    }
                }
        };
    }

    private DialogOption[] getJewelleryOptions1() {
        return new DialogOption[]{
                new DialogOption("Craft Necklace") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new NecklaceMakingUI(player));
                    }
                },
                new DialogOption("Craft Bracelet") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new BraceletMakingUI(player));
                    }
                },
                new DialogOption("Craft Amulet") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new AmuletMakingUI(player));
                    }
                },
                new DialogOption("Craft Ring") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new RingMakingUI(player));
                    }
                },
                new DialogOption("Next") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getJewelleryOptions2())
                                .build());
                    }
                }
        };
    }

    private DialogOption[] getJewelleryOptions2() {
        return new DialogOption[]{
                new DialogOption("Craft Tiara") {
                    @Override
                    public void onAction() {
                        player.getAttributes().setSkillStationId(6799);
                        player.getAttributes().getActiveDialogSequence().next();
                        player.sendUI(new TiaraMakingUI(player));
                    }
                },
                new DialogOption("Previous") {
                    @Override
                    public void onAction() {
                        player.getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(player)
                                .addOptions(getJewelleryOptions1())
                                .build());
                    }
                }
        };
    }

    public FirstClickPortableCrafter4Action(Player player, int nodeX, int nodeY, int nodeId) {
        super(player, nodeX, nodeY, nodeId);
    }
}
