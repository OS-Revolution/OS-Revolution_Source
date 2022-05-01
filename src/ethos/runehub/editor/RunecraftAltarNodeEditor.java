package ethos.runehub.editor;

import ethos.runehub.ConsoleEditor;
import ethos.runehub.skill.artisan.runecraft.RunecraftingAltarNodeDAO;
import ethos.runehub.skill.node.impl.artisan.RunecraftingAltarNode;
import org.runehub.api.util.SkillDictionary;

public class RunecraftAltarNodeEditor extends Editor{

    private static RunecraftAltarNodeEditor instance = null;


    public static RunecraftAltarNodeEditor getInstance() {
        if (instance == null)
            instance = new RunecraftAltarNodeEditor();
        return instance;
    }

    @Override
    public void run(ConsoleEditor console) {
        int altarId = console.getInputAsInteger(console.getPrompt("Enter Altar Node ID"));
        int levelRequired = console.getInputAsInteger(console.getPrompt("Enter Level Required"));
        int xpPerRune = console.getInputAsInteger(console.getPrompt("Enter XP Per Rune"));
        int essenceType = console.getInputAsInteger(console.getPrompt("Enter Essence Item ID"));
        int craftedRuneId = console.getInputAsInteger(console.getPrompt("Enter Crafted Rune Item ID"));

        RunecraftingAltarNodeDAO.getInstance().create(new RunecraftingAltarNode(altarId,levelRequired,xpPerRune, SkillDictionary.Skill.RUNECRAFTING.getId(),essenceType,craftedRuneId));

        System.out.println("[Runehub] Successfully Created!");
        if (console.getInputAsBoolean(console.getPrompt("Continue? (true/false)"))) {
            this.run(console);
        }
    }


}
