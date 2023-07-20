package ethos.runehub.entity.item;

public interface ItemContainer<T extends GameItem> {

    T get(int slot);

    int getSlot(T item);

    int slotsRemaining();

    int capacity();

    int getCount(T item);

    T[] getAll(T item);

    Integer[] getAllEmptySlots();

    void swap(int var1, int var2);

    default int getNextAvailableSlot() {
        return this.getAllEmptySlots().length > 0 ? this.getAllEmptySlots()[0] : -1;
    }
}
