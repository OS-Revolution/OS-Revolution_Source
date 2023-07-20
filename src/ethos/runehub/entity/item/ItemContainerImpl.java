package ethos.runehub.entity.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ItemContainerImpl implements ItemContainer<GameItem>{

    @Override
    public GameItem get(int slot) {
        return items[slot];
    }

    @Override
    public int getSlot(GameItem item) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getId() == item.getId()) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int slotsRemaining() {
        return (int) Arrays.stream(items).filter(Objects::isNull).count();
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int getCount(GameItem item) {
        return Arrays.stream(items).filter(gameItem -> gameItem.getId() == item.getId())
                .mapToInt(GameItem::getAmount).sum();
    }

    @Override
    public GameItem[] getAll(GameItem item) {
        return (GameItem[]) Arrays.stream(items).filter(gameItem -> gameItem.getId() == item.getId()).toArray();
    }

    @Override
    public Integer[] getAllEmptySlots() {
        List<Integer> emptySlots = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                emptySlots.add(i);
            }
        }
        return emptySlots.toArray(new Integer[0]);
    }

    @Override
    public void swap(int var1, int var2) {

    }

    public ItemContainerImpl(int capacity) {
        this.items = new GameItem[capacity];
        this.capacity = capacity;
    }


    private final int capacity;
    private final GameItem[] items;
}
