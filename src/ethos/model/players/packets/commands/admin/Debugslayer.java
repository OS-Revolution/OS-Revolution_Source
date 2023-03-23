package ethos.model.players.packets.commands.admin;

import ethos.model.players.Player;
import ethos.model.players.packets.commands.Command;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import ethos.runehub.skill.support.slayer.*;
import ethos.runehub.skill.support.slayer.master.SlayerMaster;
import ethos.runehub.skill.support.slayer.master.SlayerMasterDAO;
import org.runehub.api.model.math.impl.AdjustableInteger;
import org.runehub.api.model.math.impl.IntegerRange;
import org.runehub.api.util.IDManager;

import java.util.Arrays;

public class Debugslayer extends Command {
    @Override
    public void execute(Player player, String input) {
        String[] args = input.split(" ");
        int argValue = Integer.parseInt(args[0]);

        switch (argValue) {
            case 0:
                generateTurael();
                break;
            case 1:
               assignTask(player);
                break;
            case 2:
                testforTask(-7701604779815252537L,100,player);
                break;
            case 3:
                testforTask(-4607650105403225453L,100,player);
                break;
            case 4:
                testforTask(-1548838874320387205L,100,player);
                break;
            case 5:
                player.getSkillController().getSlayer().cancelCurrentTask(401);
                break;
        }
    }

    private void testforTask(long assignmentId, int iterations,Player player) {
        final AdjustableInteger total = new AdjustableInteger(0);
        for (int i = 0; i < iterations; i++) {
            SlayerTask task = assignTask(player);
            if (task.getAssignmentId() == assignmentId) {
                total.increment();
            }
        }
        System.out.println(total.value() + "/" + iterations + " assigned tasks were the targeted " + SlayerAssignmentDAO.getInstance().read(assignmentId).getCategory());
    }

    private SlayerTask assignTask(Player player) {
        SlayerTask task = player.getSkillController().getSlayer().getSlayerTask(401);
        int taskAmount = task.getAssignedAmount().getRandomValue();
        if (Arrays.stream(player.getSlayerSave().getPreferredAssignments()).anyMatch(value -> value == task.getAssignmentId())) {
            taskAmount = (int) (taskAmount * 1.2);
        }
        System.out.println("You've been assigned " + taskAmount + " of " + SlayerAssignmentDAO.getInstance().read(task.getAssignmentId()).getCategory());
        player.getSlayerSave().setAssignmentId(task.getAssignmentId());
        player.getSlayerSave().setAssignedAmount(taskAmount);
        return task;
    }

    private void printTaskCompatibilityInfo(int taskIndex, int mobIndex) {
        SlayerAssignment task = SlayerAssignmentDAO.getInstance().getAllEntries().get(taskIndex);
        HostileMobContext context = HostileMobIdContextLoader.getInstance().read(mobIndex);
        System.out.println("Task Cat: " + task.getCategory());
        System.out.println("Context Cat: " + context.getCategory());
        System.out.println("Counts towards task: " + context.getCategory().stream().anyMatch(cat -> cat.contains(task.getCategory())));
    }

    private void generateTurael() {
        SlayerMaster master = new SlayerMaster(
                401,
                new SlayerTask[]{
                        new SlayerTask(1025526826525805341L, 20, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(3603297688527391667L, 5, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(6853472087209626868L, 1, 6, new long[]{-1813912594169246384L}, new IntegerRange(15,50),-1),
                        new SlayerTask(-8369018869365653266L, 13, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-1141411474617317903L, 1, 8, new long[]{}, new IntegerRange(15,50),-1),

                        new SlayerTask(4895597504303677792L, 10, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(538605612646288530L, 15, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(2875949606167658816L, 5, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-5812452093955977313L, 1, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(932877277895740829L, 15, 7, new long[]{}, new IntegerRange(15,50),-1),

                        new SlayerTask(5156721740240340862L, 6, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-1892045768008447818L, 13, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(7525515536511421085L, 1, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-3050582876871113953L, 20, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-518258673358258135L, 15, 6, new long[]{}, new IntegerRange(15,50),-1),

                        new SlayerTask(-8598879298038378780L, 1, 8, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-8564549073105370520L, 7, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-7096098567790965283L, 1, 6, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-3306869684914468602L, 1, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-1915300238322152628L, 7, 7, new long[]{}, new IntegerRange(15,50),-1),

                        new SlayerTask(-3730357465968524409L, 15, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(2633716304376317904L, 1, 6, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(-6095611682120842413L, 20, 7, new long[]{}, new IntegerRange(15,50),-1),
                        new SlayerTask(7037424871780140015L, 10, 7, new long[]{}, new IntegerRange(15,50),-1)
                },
                0, 0, 0, 0, 0, 0, false
        );

        SlayerMasterDAO.getInstance().create(master);
    }

    public static void generateTuraelAssignments() {
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "aberrant spectres",
                60,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "abyssal demons",
                85,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "adamant dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "ankous",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "aviansie",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "bandits",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(//x
                IDManager.getUUID(),
                "banshees",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "basilisks",
                40,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "bats",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "birds",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "bears",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "black demons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "black dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "black knights",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(//x
                IDManager.getUUID(),
                "bloodvelds",
                50,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "blue dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "brine rats",
                47,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "bronze dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "catablepon",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "cave bugs",
                7,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "cave crawlers",
                10,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "cave horrors",
                58,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "cave slime",
                17,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "cave kraken",
                87,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "chaos druids",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "cockatrices",
                25,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "cows",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(//x
                IDManager.getUUID(),
                "crawling hands",
                5,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "crocodiles",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "dagannoths",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "dark beasts",
                90,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "dark warriors",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "dogs",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(//x
                IDManager.getUUID(),
                "dust devils",
                65,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "dwarves",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "earth warriors",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "ent",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "fever spiders",
                42,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "fire giants",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "flesh crawlers",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "gargoyles",
                75,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "ghosts",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "ghouls",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "goblins",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "greater demons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "green dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "harpie bug swarms",
                33,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "hellhounds",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "hill giant",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "hobgoblins",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "icefiends",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "ice giants",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "ice warriors",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(//x
                IDManager.getUUID(),
                "infernal mages",
                45,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "iron dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "jellies",
                52,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "jungle horrors",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "kalphites",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "killerwatts",
                37,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "kurasks",
                70,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "lava dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "lesser demons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "lizardmen",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "lizards",
                22,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "magic axes",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "lizards",
                22,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "mammoths",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "mithril dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "minotaurs",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "mogres",
                32,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "molanisks",
                39,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "monkeys",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "moss giants",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "nechryael",
                80,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "ogres",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "otherworldy beings",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "pirate",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "pyrefiends",
                30,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "rats",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "red dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "revenants",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "rockslugs",
                20,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "rogues",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "rune dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "scabarites",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "scorpions",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "sea snakes",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "shades",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "shadow warriors",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "skeletal wyverns",
                72,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "skeletons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "smoke devils",
                93,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "spiders",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "spiritual creatures",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "steel dragons",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "suqahs",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "terror dogs",
                40,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "trolls",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "turoths",
                55,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "Vampyres",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "waterfiends",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "werewolves",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "wolves",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "zombies",
                1,
                false,
                new int[0]
        ));
        SlayerAssignmentDAO.getInstance().create(new SlayerAssignment(
                IDManager.getUUID(),
                "zygomites",
                1,
                false,
                new int[0]
        ));
    }

    private void generateSlayerMaster() {

    }
}
