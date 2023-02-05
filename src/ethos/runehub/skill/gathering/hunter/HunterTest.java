package ethos.runehub.skill.gathering.hunter;

import ethos.model.players.Player;

public class HunterTest {

    public static final int[] HUNTER_MOBS = {2120};

    public static void deployTrap(Player player) {
        Trap trap = new Trap(10008,player.absX,player.absY, player.heightLevel);
        player.getAttributes().getDeployedTrapList().add(trap);
    }
}
