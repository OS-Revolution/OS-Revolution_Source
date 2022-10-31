package ethos.runehub.editor;

import ethos.model.items.ItemDefinition;
import ethos.runehub.ConsoleEditor;
import ethos.runehub.entity.item.equipment.Equipment;
import ethos.runehub.entity.item.equipment.EquipmentDAO;
import ethos.runehub.entity.item.equipment.EquipmentSlot;

import java.io.IOException;

public class EquipmentEditor extends Editor {
    @Override
    public void run(ConsoleEditor console) {
        try {
            ItemDefinition.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int type = console.getInputAsInteger(console.getPrompt("Choose editing mode"));

        if (type == 0) {
            int itemId = console.getInputAsInteger(console.getPrompt("Enter Item ID to load stats from"));
            Equipment equipment = loadStatsFromExisting(itemId);
            System.out.println("Loaded Equipement: " + equipment);
            boolean confirm = console.getInputAsBoolean("Submit Equipment to DB?");
            if (confirm) {
                EquipmentDAO.getInstance().create(equipment);
            }
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
