package ethos.runehub.skill.artisan.construction;

import ethos.runehub.entity.item.GameItem;

public enum Hotspot {
    LECTERN(new String[]{"Oak Lectern", "Eagle Lectern", "Demon Lectern", "Teak Eagle Lectern", "Teak Demon Lectern",
            "Mahogany Eagle Lectern", "Mahogany Demon Lectern", "Marble Lectern"},
            new GameItem[][]{
                    new GameItem[]{new GameItem(8778)},
                    new GameItem[]{new GameItem(8778, 2)},
                    new GameItem[]{new GameItem(8778, 2)},
                    new GameItem[]{new GameItem(8780, 2)},
                    new GameItem[]{new GameItem(8780, 2)},
                    new GameItem[]{new GameItem(8782, 2), new GameItem(4692)},
                    new GameItem[]{new GameItem(8782, 2), new GameItem(4692)},
                    new GameItem[]{new GameItem(8786), new GameItem(4703), new GameItem(4692)}
            },
            new int[] {13642,13643,13644,13645,13646,13647,13648,18245},
            new String[]{"Creates basic magic tablets","Creates some magic teleport tablets","Creates some magic spell tablets","Creates most magic teleport tablets","Creates most magic spell tablets","Creates all magic teleport tablets", "Creates all magic spell tablets","Creates all magic tablets"},
            new int[] {60,120,120,180,180,580,580,1800},
            new int[] {40,47,47,57,57,67,67,77});

    public String[] getAvailableNodes() {
        return availableNodes;
    }

    public GameItem[][] getConstructionCost() {
        return constructionCost;
    }

    public int[] getBuiltNodeId() {
        return builtNodeId;
    }


    public String[] getDescription() {
        return description;
    }

    public int[] getLevel() {
        return level;
    }

    public int[] getXp() {
        return xp;
    }

    private Hotspot(String[] availableNodes, GameItem[][] constructionCost, int[] builtNodeId, String[] description, int[] xp, int[] level) {
        this.availableNodes = availableNodes;
        this.constructionCost = constructionCost;
        this.builtNodeId = builtNodeId;
        this.description = description;
        this.xp = xp;
        this.level = level;
    }

    private final String[] availableNodes;
    private final GameItem[][] constructionCost;
    private final int[] builtNodeId, xp, level;
    private final String[] description;
}
