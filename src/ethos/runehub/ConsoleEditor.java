package ethos.runehub;

import ethos.runehub.editor.*;
import ethos.runehub.entity.item.ItemInteraction;
import ethos.runehub.entity.item.ItemInteractionLoader;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReaction;
import ethos.runehub.skill.artisan.herblore.HerbloreItemReactionLoader;
import ethos.runehub.skill.artisan.runecraft.RunecraftMultiplier;
import ethos.runehub.skill.artisan.runecraft.RunecraftMultiplierSerializer;
import ethos.runehub.skill.artisan.runecraft.RunecraftMultipliers;
import org.python.parser.ast.Str;
import org.runehub.api.io.load.JsonSerializer;
import org.runehub.api.util.IDManager;
import org.runehub.api.util.SkillDictionary;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleEditor {

    public static void main(String[] args) {
        final ConsoleEditor editor = new ConsoleEditor();


        editor.runEditor(editor.getPrompt("Enter an Editor Type"));
    }

    private static void runeMultipliers() {
        RunecraftMultipliers runecraftMultipliers = new RunecraftMultipliers(new HashMap<>());
        RunecraftMultiplier air = new RunecraftMultiplier(556,new HashMap<>());
        RunecraftMultiplier mind = new RunecraftMultiplier(558,new HashMap<>());
        RunecraftMultiplier water = new RunecraftMultiplier(555,new HashMap<>());
        RunecraftMultiplier earth = new RunecraftMultiplier(557,new HashMap<>());
        RunecraftMultiplier fire = new RunecraftMultiplier(554,new HashMap<>());
        RunecraftMultiplier body = new RunecraftMultiplier(559,new HashMap<>());
        RunecraftMultiplier cosmic = new RunecraftMultiplier(564,new HashMap<>());
        RunecraftMultiplier chaos = new RunecraftMultiplier(562,new HashMap<>());
        RunecraftMultiplier astral = new RunecraftMultiplier(9075,new HashMap<>());
        RunecraftMultiplier nature = new RunecraftMultiplier(561,new HashMap<>());
        RunecraftMultiplier law = new RunecraftMultiplier(563,new HashMap<>());
        RunecraftMultiplier death = new RunecraftMultiplier(560,new HashMap<>());

        air.getMultiplierMap().put(1,1);
        air.getMultiplierMap().put(11,2);
        air.getMultiplierMap().put(22,3);
        air.getMultiplierMap().put(33,4);
        air.getMultiplierMap().put(44,5);
        air.getMultiplierMap().put(55,6);
        air.getMultiplierMap().put(66,7);
        air.getMultiplierMap().put(77,8);
        air.getMultiplierMap().put(88,9);
        air.getMultiplierMap().put(99,10);

        mind.getMultiplierMap().put(2,1);
        mind.getMultiplierMap().put(14,2);
        mind.getMultiplierMap().put(28,3);
        mind.getMultiplierMap().put(42,4);
        mind.getMultiplierMap().put(56,5);
        mind.getMultiplierMap().put(70,6);
        mind.getMultiplierMap().put(84,7);
        mind.getMultiplierMap().put(98,8);

        water.getMultiplierMap().put(5,1);
        water.getMultiplierMap().put(19,2);
        water.getMultiplierMap().put(38,3);
        water.getMultiplierMap().put(57,4);
        water.getMultiplierMap().put(76,5);
        water.getMultiplierMap().put(95,6);

        earth.getMultiplierMap().put(9,1);
        earth.getMultiplierMap().put(26,2);
        earth.getMultiplierMap().put(52,3);
        earth.getMultiplierMap().put(78,4);

        fire.getMultiplierMap().put(14,1);
        fire.getMultiplierMap().put(35,2);
        fire.getMultiplierMap().put(70,3);

        body.getMultiplierMap().put(20,1);
        body.getMultiplierMap().put(46,2);
        body.getMultiplierMap().put(92,3);

        cosmic.getMultiplierMap().put(27,1);
        cosmic.getMultiplierMap().put(59,2);

        chaos.getMultiplierMap().put(35,1);
        chaos.getMultiplierMap().put(74,2);

        astral.getMultiplierMap().put(40,1);
        astral.getMultiplierMap().put(82,2);

        nature.getMultiplierMap().put(44,1);
        nature.getMultiplierMap().put(91,2);

        law.getMultiplierMap().put(54,1);
        law.getMultiplierMap().put(95,2);

        death.getMultiplierMap().put(65,1);
        death.getMultiplierMap().put(99,2);

        runecraftMultipliers.getMultiplierMap().put(air.getRuneId(),air);
        runecraftMultipliers.getMultiplierMap().put(mind.getRuneId(),mind);
        runecraftMultipliers.getMultiplierMap().put(water.getRuneId(),water);
        runecraftMultipliers.getMultiplierMap().put(earth.getRuneId(),earth);
        runecraftMultipliers.getMultiplierMap().put(fire.getRuneId(),fire);
        runecraftMultipliers.getMultiplierMap().put(body.getRuneId(),body);
        runecraftMultipliers.getMultiplierMap().put(cosmic.getRuneId(),cosmic);
        runecraftMultipliers.getMultiplierMap().put(chaos.getRuneId(),chaos);
        runecraftMultipliers.getMultiplierMap().put(astral.getRuneId(),astral);
        runecraftMultipliers.getMultiplierMap().put(nature.getRuneId(),nature);
        runecraftMultipliers.getMultiplierMap().put(law.getRuneId(),law);
        runecraftMultipliers.getMultiplierMap().put(death.getRuneId(),death);

        JsonSerializer<RunecraftMultipliers> serializer =  new JsonSerializer<>(RunecraftMultipliers.class);

       serializer.write(new File("./Data/runehub/skills/rune-multipliers.json"),serializer.serialize(runecraftMultipliers));
    }

    public String getPrompt(String prompt) {
        System.out.println("[Runehub] " + prompt);
        return scanner.nextLine();
    }

    private void runEditor(String editorType) {
        switch (editorType) {
            case "herblore-item-action":
                HerbloreItemActionEditor.getInstance().run(this);
                break;
            case "runecraft-altar-node":
                RunecraftAltarNodeEditor.getInstance().run(this);
                break;
            case "stall-editor":
                new ThievingStallEditor().run(this);
                break;
            case "smelt-editor":
                SmeltingItemActionEditor.getInstance().run(this);
                break;
            case "equipment-editor":
                new EquipmentEditor().run(this);
                break;
            case "mob":
                new MobEditor().run(this);
                break;
            case "tablet":
                new MagicTabletEditor().run(this);
                break;
            default:
                runEditor(this.getPrompt("No Such Editor. Enter an Editor Type"));
                break;
        }
    }

    public int getInputAsInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return this.getInputAsInteger(this.getPrompt("Please Enter a Valid Integer"));
        }
    }

    public boolean getInputAsBoolean(String input) {
        try {
            return Boolean.parseBoolean(input);
        } catch (NumberFormatException e) {
            return this.getInputAsBoolean(this.getPrompt("Please enter true or false"));
        }
    }



    private ConsoleEditor() {
        this.scanner = new Scanner(System.in);
    }

    private String editorType;
    private final Scanner scanner;
}
