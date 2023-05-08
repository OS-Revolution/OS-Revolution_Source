package ethos.runehub;

import ethos.runehub.skill.Skill;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class WeightedList {
    private final List<Integer> list;
    private final Map<Double, Integer> map;
    private final int maxSize;

    public WeightedList(int size) {
        list = new ArrayList<>();
        map = new TreeMap<>();
        maxSize = size;

        // Initialize the list with 0s
        for (int i = 0; i < size; i++) {
            list.add(0);
        }

        // Calculate the bonus for each score increment
        double bonus = 0.0075 * size;

        // Populate the map with scores and their corresponding indices
        for (double score = 0; score <= 1.0; score += 0.01) {
            int index = Math.min((int) Math.floor(score / bonus), size - 1);
            map.put(score, index);
        }
    }

    public void add(int value, double score) {
        int index = map.get(score);
        if (list.get(index) == 0) {
            list.set(index, value);
        }
    }

    public int getRandom() {
        int index;
        do {
            index = Skill.SKILL_RANDOM.nextInt(maxSize);
        } while (list.get(index) == 0);
        return list.get(index);
    }
}