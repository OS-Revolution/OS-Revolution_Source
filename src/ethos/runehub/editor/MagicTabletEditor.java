package ethos.runehub.editor;

import ethos.model.items.ItemDefinition;
import ethos.runehub.ConsoleEditor;
import ethos.runehub.entity.item.GameItem;
import ethos.runehub.entity.item.equipment.Equipment;
import ethos.runehub.entity.item.equipment.EquipmentDAO;
import ethos.runehub.entity.item.equipment.EquipmentSlot;
import ethos.runehub.skill.artisan.construction.Hotspot;
import ethos.runehub.skill.combat.magic.MagicTablet;
import ethos.runehub.skill.combat.magic.MagicTabletCache;

import java.io.IOException;

public class MagicTabletEditor extends Editor {
    @Override
    public void run(ConsoleEditor console) {
        int type = console.getInputAsInteger(console.getPrompt("Choose editing mode"));

        if (type == 0) {
            int itemId = console.getInputAsInteger(console.getPrompt("Enter Item ID to load stats from"));
            int level = console.getInputAsInteger(console.getPrompt("Enter level requirement"));
            int xp = console.getInputAsInteger(console.getPrompt("Enter XP earned"));
            boolean materials = console.getInputAsBoolean(console.getPrompt("Add Materials?"));
            GameItem[] items = new GameItem[6];
            boolean[] lectern = new boolean[Hotspot.LECTERN.getAvailableNodes().length];
            int index = 0;
            while (materials) {
                int matId = console.getInputAsInteger(console.getPrompt("Enter Item ID"));
                int matAmount = console.getInputAsInteger(console.getPrompt("Enter Quantity"));
                boolean cont = console.getInputAsBoolean(console.getPrompt("Add Materials?"));
                items[index] = new GameItem(matId,matAmount);
                index++;
                if (!cont || index == items.length) {
                    materials = false;
                    break;
                }
            }

            for (int i = 0; i < lectern.length; i++) {
                boolean cont = console.getInputAsBoolean(console.getPrompt("Available on " + Hotspot.LECTERN.getAvailableNodes()[i] + "?"));
                lectern[i] = cont;
            }

            MagicTabletCache.getInstance().createAndPush(itemId,new MagicTablet(itemId,level,xp,items,lectern,false));
        }
    }


}
