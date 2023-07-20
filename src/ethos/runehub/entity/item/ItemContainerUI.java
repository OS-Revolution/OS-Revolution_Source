package ethos.runehub.entity.item;

public class ItemContainerUI  implements ItemContainer<GameItem>{
    @Override
    public GameItem get(int slot) {
        return null;
    }

    @Override
    public int getSlot(GameItem item) {
        return 0;
    }

    @Override
    public int slotsRemaining() {
        return 0;
    }

    @Override
    public int capacity() {
        return 0;
    }

    @Override
    public int getCount(GameItem item) {
        return 0;
    }

    @Override
    public GameItem[] getAll(GameItem item) {
        return new GameItem[0];
    }

    @Override
    public Integer[] getAllEmptySlots() {
        return new Integer[0];
    }

    @Override
    public void swap(int var1, int var2) {

    }
}
