package ethos.runehub.entity.item.equipment;

public enum EquipmentSlot {

    HEAD(0),CAPE(1),NECK(2),MAIN_HAND(3),BODY(4),OFF_HAND(5),LEGS(7),HANDS(9),FEET(10),RING(12),AMMO(13),TWO_HAND(14);

    public int getSlotId() {
        return slotId;
    }

    private EquipmentSlot(int slotId) {
        this.slotId = slotId;
    }

    private final int slotId;

    @Override
    public String toString() {
        return name() + "[" + ordinal() + "]";
    }
}
