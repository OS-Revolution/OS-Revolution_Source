package ethos.model.content;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.dialog.DialogSequence;
import ethos.util.Misc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class Tutorial {

    private final DialogSequence dialogSequence;
    /**
     * The stage of the tutorial
     */
    private Stage stage = null;

    /**
     * The final stage of the tutorial
     */
    private static final Stage FINAL_STAGE = Stage.END;

    /**
     * te
     * The player in this tutorial
     */
    private Player player;

    /**
     * The interface for iron man selection
     */
    private final IronManInterface ironManInterface;

    /**
     * Creates a new tutorial for the player
     *
     * @param player the player the tutorial is created for
     */
    public Tutorial(Player player) {
        this.player = player;
        this.ironManInterface = new IronManInterface(player);
        this.dialogSequence = new DialogSequence.DialogSequenceBuilder(player)
                .addNpcChat(3308, "Hello there! Welcome to Draynor.", "Have I seen you before?")
                .addOptions(1, "Yes", "No")
                .build();
    }

    /**
     * A method used to continue the tutorial to the next stage
     */
    public void proceed() {
        if (stage == Stage.END) {
            return;
        }
        stage = stage.next();
        if (stage == Tutorial.FINAL_STAGE) {
            player.getItems().addItem(3451, 2);
            stop();
            return;
        }
        refresh();
    }

    /**
     * Refreshes the current stage so that it shows properly for the player. In some cases a player may accidently close an interface, in which case that interface should be
     * re-opened.
     */
    public void refresh() {
        if (stage == null) {
            return;
        }
        if (stage == Stage.END) {
            return;
        }
        stage.getScene().display(player);
    }

    /**
     * Stops the tutorial for the player and finalizes it in some fashion.
     */
    private void stop() {

    }

    /**
     * Automatically finishes the tutorial in some fashion without completing it properly.
     */
    public void autoComplete() {
        stage = Stage.END;
    }

    /**
     * Determines if the tutorial is still active
     *
     * @return true if the event is active, otherwise false
     */
    public boolean isActive() {
        return stage != Stage.END;
    }

    /**
     * The stage for the tutorial.
     *
     * @return the stage the player is on
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Modifies the stage
     *
     * @param stage the new stage
     * @return the stage
     */
    public Stage setStage(Stage stage) {
        return this.stage = stage;
    }

    public IronManInterface getIronmanInterface() {
        return ironManInterface;
    }

    /**
     * An interface used to display information to the player about a particular scene. Each stage has a scene that displays information to the player.
     */
    private interface Scene {

        /**
         * Displays some information about the stage to the player
         *
         * @param player the player
         */
        void display(Player player);
    }

    /**
     * The stages of the tutorial
     */
    public enum Stage {
        START(0, new Scene() {

            @Override
            public void display(Player player) {
                //player.getDH().sendDialogues(645, 311);
                player.getDH().sendDialogues(673, 2123);
                PlayerHandler.executeGlobalMessage("^New Player " + Misc.capitalizeJustFirst(player.playerName) + " has logged in! Welcome!");
            }

        }), MODE_SELECTION(1, new Scene() {

            @Override
            public void display(Player player) {
//                player.getPA().showInterface(42400);
//                player.getTutorial().getIronmanInterface().refresh();
                //PlayerHandler.executeGlobalMessage("[@blu@New Player@bla@] " + Misc.capitalizeJustFirst(player.playerName) + " @bla@has logged in! Welcome!");
            }
        }), MODE_DIALOGUE(2, new Scene() {

            @Override
            public void display(Player player) {
                player.getDH().sendDialogues(647, 2123);
            }
        }), APPEND_STARTER(3, new Scene() {

            @Override
            public void display(Player player) {
                player.getPA().addStarter();
                player.getTutorial().proceed();
            }

        }), END_DIALOGUE(4, new Scene() {

            @Override
            public void display(Player player) {
                player.getDH().sendDialogues(649, 2123);
            }

        }), END(5, new Scene() {

            @Override
            public void display(Player player) {
                player.getPA().showInterface(3559);
                player.canChangeAppearance = true;
            }

        });

        /**
         * The identification value of the stage, representing where this {@link Stage} object exists amongst others in chronological order. The order should initially start at 0
         * and increase by 1 for every element.
         */
        private final int id;

        /**
         * The scene for the stage
         */
        private final Scene scene;

        /**
         * Creates a new Stage with an identification value
         *
         * @param id the id of the stage
         */
        private Stage(int id, Scene scene) {
            this.id = id;
            this.scene = scene;
        }

        /**
         * Used to compare the {@link Stage#id} of one {@link Stage} to another.
         */
        private static final Comparator<Stage> COMPARE_STAGES = (one, two) -> Integer.compare(one.id, two.id);

        /**
         * An unmodifiable {@link Set} of all elements in the {@link Stage} enumeration.
         */
        private static final List<Stage> ALL_STAGES = ImmutableList.copyOf(Ordering.from(COMPARE_STAGES).sortedCopy(Arrays.asList(values())));

        /**
         * Retrieves the next element in an ordered list based on where this element exists in that ordered list. The list is ordered by the {@link Stage#id}, in chronological
         * order.
         *
         * @return
         */
        public final Stage next() {
            int currentIndex = ALL_STAGES.indexOf(this);

            if (currentIndex == ALL_STAGES.size() - 1) {
                return Stage.END;
            }

            return ALL_STAGES.get(currentIndex + 1);
        }

        /**
         * The {@link Scene} used to display information about the tutorial to the player
         *
         * @return the scene
         */
        public final Scene getScene() {
            return scene;
        }
    }
}
