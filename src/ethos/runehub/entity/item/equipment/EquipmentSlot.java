package ethos.runehub.entity.item.equipment;

public enum EquipmentSlot {

    HEAD,CAPE,NECK,MAIN_HAND,BODY,OFF_HAND,LEGS,HANDS,FEET,RING,AMMO,TWO_HAND;

    @Override
    public String toString() {
        return name() + "[" + ordinal() + "]";
    }
}
