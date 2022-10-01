package ethos.runehub.loot;

import java.util.Arrays;

public class Prize {

    public long getContainerId(long id) {
        return Arrays.stream(containerIds).filter(value -> value == id).findAny().orElse(-1L);
    }

    public long getContainerId(int index) {
        return containerIds[index];
    }

    public long[] getContainerIds() {
        return containerIds;
    }

    public Prize(long[] containerId) {
        this.containerIds = containerId;
    }

    private final long[] containerIds;
}
