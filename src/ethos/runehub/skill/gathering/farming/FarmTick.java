package ethos.runehub.skill.gathering.farming;

import ethos.model.players.Player;

public class FarmTick implements Runnable{
    @Override
    public void run() {
        System.out.println("Doing Farm Tick");
        if (!player.disconnected) {
            int regionX = player.absX >> 3;
            int regionY = player.absY >> 3;
            int regionId = ((regionX / 8) << 8) + (regionY / 8);
            if (player.getContext().getPlayerSaveData().farmingConfig().containsKey(regionId)) {
                final int varbit = player.getContext().getPlayerSaveData().farmingConfig().get(regionId).stream().mapToInt(FarmingConfig::varbit).sum();
                player.getPA().sendConfig(529, varbit);
            }
        } else {
            player.getAttributes().getFarmTickExecutorService().shutdownNow();
        }
    }


    public FarmTick(Player player) {
        this.player = player;
    }

    private final Player player;
}
