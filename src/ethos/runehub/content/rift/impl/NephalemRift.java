package ethos.runehub.content.rift.impl;

import ethos.model.players.Player;
import ethos.runehub.content.rift.Rift;
import ethos.runehub.content.rift.RiftDifficulty;
import ethos.runehub.content.rift.RiftFloorDAO;
import ethos.runehub.ui.impl.tab.player.NephalemRiftTab;

import java.time.Duration;

public class NephalemRift extends Rift {

    @Override
    public void start(Player player) {
//        player.getAttributes().setRiftEndTimestamp(System.currentTimeMillis() + Duration.ofMinutes(60).toMillis());
        super.start(player);
    }

    public NephalemRift(Player player, RiftDifficulty difficulty) {
        super(player,difficulty);
    }
}
