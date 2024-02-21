package ethos.runehub.event;

import ethos.runehub.TimeUtils;
import ethos.runehub.event.chest.PromotionalChestEventImpl;
import ethos.runehub.event.dnd.*;
import ethos.runehub.event.mob.MobAggressionEvent;
import ethos.runehub.event.shop.impl.GeneralStoreRestockEvent;
import ethos.runehub.event.shop.impl.SpecialtyShopRestockEvent;
import ethos.runehub.event.skill.cooking.BrewingCycleEvent;
import ethos.runehub.event.skill.farming.*;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FixedScheduledEventController {

    private static FixedScheduledEventController instance = null;

    public static FixedScheduledEventController getInstance() {
        if (instance == null)
            instance = new FixedScheduledEventController();
        return instance;
    }

    public void startEvent(FixedScheduleEvent event) {
        final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime nextRun = this.getNextCycle(event.getRate());
        final Duration initialDelay = Duration.between(now, nextRun);
        executorService.scheduleAtFixedRate(
                event,
                initialDelay.toMillis(),
                Duration.ofMillis(event.getRate()).toMillis(),
                TimeUnit.MILLISECONDS);
    }

    public void forceEvent(int index) {
        executorService.execute(fixedScheduleEvents[index]);
    }

    public void forceEvent(FixedScheduleEvent event) {
        executorService.execute(event);
    }

    public ZonedDateTime getNextCycle(FixedScheduleEvent event) {
        return this.getNextCycle(event.getRate());
    }
    public ZonedDateTime getFirstRun(FixedScheduleEvent event) {
        final ZoneId ZID = ZoneId.of("UTC");
        final ZonedDateTime now = ZonedDateTime.now(ZID);
        final ZonedDateTime start = now.withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        System.out.println(event.getName() + " rate: " + event.getRate());
        final ZonedDateTime firstRun = start.plus(event.getRate(), ChronoUnit.MILLIS);
        return firstRun;
    }

    private ZonedDateTime getNextCycle(long msRate) {
        final ZoneId ZID = ZoneId.of("UTC");
        final ZonedDateTime now = ZonedDateTime.now(ZID);
        final ZonedDateTime start = now.withMonth(1).withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0);
        final ZonedDateTime firstRun = start.plus(msRate, ChronoUnit.MILLIS);
        final long firstRunMS = now.toInstant().toEpochMilli() - firstRun.toInstant().toEpochMilli();
        final long quotient = firstRunMS / msRate;
        final ZonedDateTime previousRun = firstRun.plus(quotient * msRate, ChronoUnit.MILLIS);
        final ZonedDateTime nextRun = previousRun.plus(msRate,ChronoUnit.MILLIS);
//        System.out.println(" First Run ZDT: " + TimeUtils.getZDTString(firstRun));
//        System.out.println(" Previous run ZDT: " + TimeUtils.getZDTString(previousRun));
//        System.out.println(" Next run ZDT: " + TimeUtils.getZDTString(nextRun));
//        System.out.println(" Next run2 ZDT: " + TimeUtils.getZDTString(now.plus(msRate,ChronoUnit.MILLIS)));
        return firstRun.isAfter(now) ? firstRun : nextRun;
    }

    private FixedScheduledEventController() {
        this.executorService = Executors.newScheduledThreadPool(1);
        this.fixedScheduleEvents = new FixedScheduleEvent[]{
                new SkillOfTheHourFixedScheduleEvent(),
                new FishingPlatformUpdateFixedScheduleEvent(),
                new TravellingCommodityMerchantEvent(),
//                new PromotionalChestEventImpl(),
                new BiWeeklyResetEvent(),
                new GeneralStoreRestockEvent(),
                new SpecialtyShopRestockEvent(),
                new DailyResetEvent(),
                new PlayPassStartSeasonEvent(),
                new FruitTreePatchGrowthCycleEvent(),
                new AllotmentPatchGrowthCycleEvent(),
                new FlowerPatchGrowthCycleEvent(),
                new WeedGrowthCycleEvent(),
                new HerbPatchGrowthCycleEvent(),
                new HopsPatchGrowthCycleEvent(),
                new TreePatchGrowthCycleEvent(),
                new BushPatchGrowthCycleEvent(),
                new BrewingCycleEvent()
        };
    }

    public FixedScheduleEvent[] getFixedScheduleEvents() {
        return fixedScheduleEvents;
    }

    private final ScheduledExecutorService executorService;
    private final List<Future<?>> futures = new ArrayList<>();
    private final FixedScheduleEvent[] fixedScheduleEvents;
}
