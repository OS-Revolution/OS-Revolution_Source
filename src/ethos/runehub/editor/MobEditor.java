package ethos.runehub.editor;

import ethos.model.items.ItemDefinition;
import ethos.runehub.ConsoleEditor;
import ethos.runehub.entity.item.equipment.Equipment;
import ethos.runehub.entity.item.equipment.EquipmentDAO;
import ethos.runehub.entity.item.equipment.EquipmentSlot;
import ethos.runehub.entity.mob.AnimationDefinition;
import ethos.runehub.entity.mob.AnimationDefinitionDAO;

import java.io.IOException;

public class MobEditor extends Editor {
    @Override
    public void run(ConsoleEditor console) {
        int type = console.getInputAsInteger(console.getPrompt("Choose editing mode"));

        if (type == 0) {
            runAnimationEditor(console);
        } else if (type == 1) {
            ItemDefinition.getDefinitions().keySet().forEach(id -> {
                try {
                    Equipment equipment = loadStatsFromExisting(id);
                    EquipmentDAO.getInstance().create(equipment);
                } catch (Exception e) {
                    System.out.println("Failed to load: " + id);
                }
            });
        }
    }

    private void runAnimationEditor(ConsoleEditor console) {
        int id = console.getInputAsInteger(console.getPrompt("Enter Mob ID"));
        int walkAnim = console.getInputAsInteger(console.getPrompt("Enter Walk Animation ID"));
        int attackAnim = console.getInputAsInteger(console.getPrompt("Enter Attack Animation ID"));
        int blockAnim = console.getInputAsInteger(console.getPrompt("Enter Block Animation ID"));
        int deathAnim = console.getInputAsInteger(console.getPrompt("Enter Death ID"));

        AnimationDefinitionDAO.getInstance().create(new AnimationDefinition(id, new int[]{walkAnim, attackAnim, blockAnim, deathAnim}));

        System.out.println("Created Entry!");
        runAnimationEditor(console);
    }

    private Equipment loadStatsFromExisting(int itemId) {

        ItemDefinition definition = ItemDefinition.forId(itemId);

        Equipment equipment = new Equipment(
                itemId,
                new int[]{definition.getBonus()[0], definition.getBonus()[1], definition.getBonus()[2], definition.getBonus()[3], definition.getBonus()[4]},
                new int[]{definition.getBonus()[5], definition.getBonus()[6], definition.getBonus()[7], definition.getBonus()[8], definition.getBonus()[9]},
                definition.getBonus()[10],
                0,
                0,
                definition.getBonus()[11],
                definition.isTwoHanded() ? EquipmentSlot.TWO_HAND.ordinal() : definition.getSlot(),
                6,
                1
        );
        return equipment;
    }
}
