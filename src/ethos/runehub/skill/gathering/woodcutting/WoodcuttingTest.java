package ethos.runehub.skill.gathering.woodcutting;

import ethos.Server;
import ethos.event.CycleEventHandler;
import ethos.model.players.PlayerHandler;
import ethos.model.players.PlayerSave;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WoodcuttingTest {

    public static void main(String[] args) {
        GAME_THREAD.scheduleAtFixedRate(SERVER_TASKS, 0, 600, TimeUnit.MILLISECONDS);


//        GatheringSkill woodcutting = new Woodcutting();

//        woodcutting.gather(GatheringNodeDAO.getInstance().read(1276));
    }

    private static final Runnable SERVER_TASKS = () -> {
        try {
            CycleEventHandler.getSingleton().process();
            Server.getEventHandler().process();
        } catch (Throwable t) {
            t.printStackTrace();
            t.getCause();
            t.getMessage();
            t.fillInStackTrace();
            System.out.println("Server tasks - Check for error");
            PlayerHandler.stream().filter(Objects::nonNull).forEach(PlayerSave::save);
        }
    };

    private static final ScheduledExecutorService GAME_THREAD = Executors.newSingleThreadScheduledExecutor();
}
