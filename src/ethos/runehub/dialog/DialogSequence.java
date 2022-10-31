package ethos.runehub.dialog;

import ethos.model.players.Player;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DialogSequence {

    public static class DialogSequenceBuilder {

        public DialogSequenceBuilder setMovementRestricted(boolean walkable) {
            player.getAttributes().setMovementResricted(walkable);
            return this;
        }

        public DialogSequenceBuilder setActionLocked(boolean locked) {
            player.getAttributes().setActionLocked(locked);
            return this;
        }

        public DialogSequenceBuilder addStatement(String...lines) {
            dialogMap.put(nextChatId(), new Dialog() {
                @Override
                public void onSend() {
                    player.getDH().sendStatement(lines);
                }
            });
            return this;
        }

        public DialogSequenceBuilder addNpcChat(int npcId, String...lines) {
            dialogMap.put(nextChatId(), new Dialog() {
                @Override
                public void onSend() {
                    player.talkingNpc = npcId;
                    player.getDH().sendNpcChat(lines);
                }
            });
            return this;
        }

        public DialogSequenceBuilder addNpcChat(String name, int npcId, String...lines) {
            dialogMap.put(nextChatId(), new Dialog() {
                @Override
                public void onSend() {
                    player.talkingNpc = npcId;
                    player.getDH().sendNpcChat(name,npcId,lines);
                }
            });
            return this;
        }

        public DialogSequenceBuilder addPlayerChat(int emoteId, String...lines) {
            dialogMap.put(nextChatId(), new Dialog() {
                @Override
                public void onSend() {
                    player.getDH().sendPlayerChat(emoteId,lines);
                }
            });
            return this;
        }

        public DialogSequenceBuilder addOptions(int optionId,String...options) {
            dialogMap.put(nextChatId(), new Dialog() {
                @Override
                public void onSend() {
//                    final String[] optionText = Arrays.stream(options).map(DialogOption::getOptionText).collect(Collectors.toList()).toArray(new String[0]);
                    player.getDH().sendOptions(options);
                    player.dialogueAction = optionId;
                }
            });
            return this;
        }

        public DialogSequenceBuilder addOptions(DialogOption...options) {
            dialogMap.put(nextChatId(), new Dialog(options) {
                @Override
                public void onSend() {
                    final String[] optionText = Arrays.stream(options).map(DialogOption::getOptionText).collect(Collectors.toList()).toArray(new String[0]);
                    player.getDH().sendOptions(optionText);
                }
            });
            return this;
        }

        public DialogSequenceBuilder addItemStatement(int itemId, String statement) {
            dialogMap.put(nextChatId(), new Dialog() {
                @Override
                public void onSend() {
                    player.getDH().sendItemStatement(statement,itemId);
                }
            });
            return this;
        }

        public DialogSequenceBuilder addDialogueAction(Dialog dialog) {
            int nextChat = chatId+2;
            dialogMap.put(nextChatId(), new Dialog() {
                @Override
                public void onSend() {
                    dialog.onSend();
                    player.getDH().sendDialogueSequence(nextChat);
                }
            });
            return this;
        }

        public DialogSequence build() {
            dialogMap.put(nextChatId(), new Dialog() {
                @Override
                public void onSend() {
                    player.talkingNpc = -1;
                    player.getAttributes().setActionLocked(false);
                    player.getAttributes().setMovementResricted(false);
                    player.getPA().removeAllWindows();
                    player.getPA().closeAllWindows();
                    player.getAttributes().setActiveDialogSequence(null);
                }
            });
            return new DialogSequence(this);
        }

        private int nextChatId() {
            return chatId +=1;
        }

        public DialogSequenceBuilder(Player player) {
            this.player = player;
            this.dialogMap = new HashMap<>();
            this.chatId = 0;
        }

        private final Map<Integer,Dialog> dialogMap;
        private final Player player;
        private int chatId;
    }

    public void start(int sequence) {
        this.currentSequence = sequence;
        dialogMap.getOrDefault(sequence, new Dialog() {
            @Override
            public void onSend() {
                player.getDH().sendStatement("No such sequence: " + sequence);
            }
        }).onSend();
    }

    public void next() {
        player.getPA().closeAllWindows();
        dialogMap.getOrDefault(currentSequence+=1, new Dialog() {
            @Override
            public void onSend() {
                player.getPA().resetVariables();
                player.getPA().removeAllWindows();
                player.getPA().closeAllWindows();
                currentSequence = 1;
                player.getAttributes().setActiveDialogSequence(null);
            }
        }).onSend();
    }

    public void onOptionOne() {
        try {
        dialogMap.getOrDefault(currentSequence, new Dialog() {
            @Override
            public void onSend() {
                player.getPA().resetVariables();
                player.getPA().removeAllWindows();
                player.getPA().closeAllWindows();
                currentSequence = 1;
                player.getAttributes().setActiveDialogSequence(null);
            }
        }).getOptions().get(0).onAction();
        } catch (IndexOutOfBoundsException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public void onOptionTwo() {
        try {
        dialogMap.getOrDefault(currentSequence, new Dialog() {
            @Override
            public void onSend() {
                player.getPA().resetVariables();
                player.getPA().removeAllWindows();
                player.getPA().closeAllWindows();
                currentSequence = 1;
                player.getAttributes().setActiveDialogSequence(null);
            }
        }).getOptions().get(1).onAction();
        } catch (IndexOutOfBoundsException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public void onOptionThree() {
        try {
        dialogMap.getOrDefault(currentSequence, new Dialog() {
            @Override
            public void onSend() {
                player.getPA().resetVariables();
                player.getPA().removeAllWindows();
                player.getPA().closeAllWindows();
                currentSequence = 1;
                player.getAttributes().setActiveDialogSequence(null);
            }
        }).getOptions().get(2).onAction();
        } catch (IndexOutOfBoundsException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public void onOptionFour() {
        try {
        dialogMap.getOrDefault(currentSequence, new Dialog() {
            @Override
            public void onSend() {
                player.getPA().resetVariables();
                player.getPA().removeAllWindows();
                player.getPA().closeAllWindows();
                currentSequence = 1;
                player.getAttributes().setActiveDialogSequence(null);
            }
        }).getOptions().get(3).onAction();
        } catch (IndexOutOfBoundsException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public void onOptionFive() {
        try {
            dialogMap.getOrDefault(currentSequence, new Dialog() {
                @Override
                public void onSend() {
                    player.getPA().resetVariables();
                    player.getPA().removeAllWindows();
                    player.getPA().closeAllWindows();
                    currentSequence = 1;
                    player.getAttributes().setActiveDialogSequence(null);
                }
            }).getOptions().get(4).onAction();
        } catch (IndexOutOfBoundsException e) {
            Logger.getGlobal().warning(e.getMessage());
        }
    }

    public DialogSequence(DialogSequenceBuilder builder) {
        this.dialogMap = builder.dialogMap;
        this.player = builder.player;
    }

    private final Map<Integer,Dialog> dialogMap;
    private final Player player;
    private int currentSequence = 1;
}
