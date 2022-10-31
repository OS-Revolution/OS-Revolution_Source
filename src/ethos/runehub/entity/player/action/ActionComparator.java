package ethos.runehub.entity.player.action;

import java.util.Comparator;

public class ActionComparator implements Comparator<Action> {
    @Override
    public int compare(Action o1, Action o2) {
        return o1.compareTo(o2);
    }
}
