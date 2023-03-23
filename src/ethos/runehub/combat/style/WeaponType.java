package ethos.runehub.combat.style;

public enum WeaponType {


    WHIP(12290),
    BOW(1764),
    STAFF(328),
    TWO_HANDED_SWORD(7762), //shows claws
    AXE(1698),
    BANNER(4679), //using spear
    BLUNT(425),
    BLUDGEON(425), //using blunt
    BULWARK(0),
    CLAW(7762),
    PARTISAN(0),
    PICKAXE(5570),
    POLEARM(8460),
    POLESTAFF(0),
    SCYTHE(8460), //shows polearm
    SLASH_SWORD(0),
    SPEAR(4679),
    SPIKED(3796),
    STAB_SWORD(2276),
    BLASTER(0),
    CHINCHOMPA(0),
    CROSSBOW(1764),
    THROWN(4446),
    BLADED_STAFF(28500),
    POWERED_STAFF(0),
    SALAMANDER(0),
    UNARMED(5855);

    public static WeaponType forId(int itemId) {
        switch (itemId) {

        }
        return UNARMED;
    }

    public int getSidebarInterfaceId() {
        return sidebarInterfaceId;
    }

    public int getNameLineId() {
        return sidebarInterfaceId + 3;
    }

    private WeaponType(int sidebarInterfaceId) {
        this.sidebarInterfaceId = sidebarInterfaceId;
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }

    private final int sidebarInterfaceId;
}
