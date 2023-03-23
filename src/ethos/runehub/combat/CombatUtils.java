package ethos.runehub.combat;

import ethos.model.players.Equipment;
import ethos.model.players.Player;
import ethos.runehub.combat.style.WeaponType;
import org.runehub.api.io.load.impl.ItemIdContextLoader;

public class CombatUtils {

    public static void sendWeaponInterface(Player player, WeaponType weaponType) {
        final int weaponItemId = player.playerEquipment[Equipment.Slot.WEAPON.getSlot()];

        if(weaponType == WeaponType.UNARMED) {
            player.setSidebarInterface(0, weaponType.getSidebarInterfaceId());
            player.getPA().sendFrame126(weaponType.toString(), weaponType.getNameLineId() - 1);
        } else if (weaponType == WeaponType.STAFF || weaponType == WeaponType.BLADED_STAFF || weaponType == WeaponType.POWERED_STAFF) {
            final String weaponName = ItemIdContextLoader.getInstance().read(weaponItemId).getName();
            player.setSidebarInterface(0, weaponType.getSidebarInterfaceId());
            player.getPA().sendFrame126("Combat Lvl: " + player.combatLevel, weaponType.getNameLineId());
            player.getPA().sendFrame126(weaponName, 355);
//        } else if(weaponType == WeaponType.SCYTHE) {
//            player.setSidebarInterface(0, weaponType.getSidebarInterfaceId());
//            player.getPA().sendFrame246(7763, 200, weaponType.getNameLineId());
//            player.getPA().sendFrame126(weaponName, 7765);
        } else {
            final String weaponName = ItemIdContextLoader.getInstance().read(weaponItemId).getName();
            player.setSidebarInterface(0, weaponType.getSidebarInterfaceId());
            player.getPA().sendFrame126(weaponName, weaponType.getNameLineId());
        }
        player.getPA().sendFrame246(weaponType.getSidebarInterfaceId() + 1, 200, weaponItemId); //line under weapon name
    }
}
