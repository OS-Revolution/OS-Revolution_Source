package ethos.runehub.event.chest;

import ethos.model.players.PlayerHandler;
import ethos.runehub.event.FixedScheduleEvent;
import ethos.runehub.world.WorldSettingsController;
import ethos.util.Misc;

import java.time.Instant;
import java.util.Set;

public class PromotionalChestEventImpl extends PromotionalChestEvent{

    @Override
    protected void onExecute() {
        final int currentEventId = WorldSettingsController.getInstance().getWorldSettings().getCurrentEventId();
        Set<Integer> keys = WorldSettingsController.getInstance().getPromotionalEvents().keySet();
        Integer[] keyArray = keys.toArray(new Integer[keys.size()]);
        int eventIndex = keyArray.length == 1 ? 0 : Misc.random(keyArray.length);
        PromotionalChestEvent event = WorldSettingsController.getInstance().getPromotionalEvents().get(keyArray[eventIndex]);
        if (event.getEventId() != currentEventId) {
            WorldSettingsController.getInstance().getWorldSettings().setCurrentEventId(event.getEventId());
            WorldSettingsController.getInstance().getWorldSettings().setLastChestEventTimestamp(Instant.now().toEpochMilli());
            WorldSettingsController.getInstance().saveSettings();
            event.onExecute();
//            PlayerHandler.executeGlobalMessage("^Promo $" + event.getName() + " is now active!");
            PlayerHandler.executeGlobalMessage("[@pur@Promo@bla@] <ref=" + event.getName() + ",url=https://discordapp.com/channels/681881287166001224/781269609218834454/829260106252156979> is now active!");
        }
    }

    public PromotionalChestEventImpl() {
        super("Promotional Chest Event", 1,"https://discordapp.com/channels/681881287166001224/1073595491637547148/1073601915344134306");
    }
}
