package ethos.runehub.skill.support.sailing;

import ethos.Server;
import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.event.skill.sailing.VoyageEvent;
import ethos.runehub.skill.support.SupportSkill;
import ethos.runehub.skill.support.sailing.ship.ShipLoader;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.skill.support.sailing.voyage.VoyageDAO;
import ethos.util.Misc;
import org.runehub.api.model.math.impl.IntegerRange;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Sailing2 extends SupportSkill {

    public void assignShipToSlot(int shipId, int slot) {
        final ethos.runehub.skill.support.sailing.ship.Ship ship = ShipLoader.getInstance().read(shipId);
        this.getPlayer().getSailingSaveData().getShipSlot()[slot] = new ethos.runehub.skill.support.sailing.Ship(
                ship.getId(),
                ship.getSeafaring(),
                ship.getMorale(),
                ship.getCombat(),
                ship.getSpeed(),
                0,
                Ship.Status.AVAILABLE.ordinal()
        ).toBitArray();
    }

    public ShipController getShipController(int slot) {
        return new ShipController(this.getPlayer().getSailingSaveData(),slot);
    }

//    public void completeVoyage(int slot) {
//        this.getPlayer().sendMessage("One of your ships has returned to port.");
//        if (SKILL_RANDOM.nextFloat() <= this.getShip(slot).getActiveVoyageSuccessRate()) {
//            this.getShipController(slot).onSuccessfulVoyage();
//        } else {
//            this.getShip(slot).onFailedVoyage();
//        }
//        this.updateShip(slot, this.getShip(slot).getShip());
//    }

    public String getSuccessRatePrint(int slot,Voyage voyage) {
        final ShipController controller = this.getShipController(slot);
        final float value = controller.getVoyageSuccessRate(voyage);
        final DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return decimalFormat.format(value * 100) + "%";
    }

    public void generateDailyVoyages() {
        final Queue<Integer> voyageQueue = new ArrayDeque<>();
        final List<Integer> activeVoyages = new ArrayList<>();
        final int dailyLimit = this.getPlayer().getAttributes().isMember() ? 8 : 5;

        activeVoyages.add(this.getShipController(0).getShip().getVoyage());
        activeVoyages.add(this.getShipController(0).getShip().getVoyage());
        activeVoyages.add(this.getShipController(0).getShip().getVoyage());

        for (int i = 0; i < this.getPlayer().getSailingSaveData().getAvailabeVoyages().length; i++) {
            if (!activeVoyages.contains(this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i])) {
                this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i] = 0;
            }
        }

        activeVoyages.clear();

        final long remainingVoyages = dailyLimit - Arrays.stream(this.getPlayer().getSailingSaveData().getAvailabeVoyages()).filter(voyageId -> voyageId != 0).count();

        for (int i = 0; i < remainingVoyages; i++) {
            Voyage voyage = generateVoyage();
            if (!voyageQueue.contains(voyage.getId())) {
                voyageQueue.add(voyage.getId());
            }
        }

        for (int i = 0; i < this.getPlayer().getSailingSaveData().getAvailabeVoyages().length; i++) {
            if (this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i] == 0 && !voyageQueue.isEmpty()) {
                this.getPlayer().getSailingSaveData().getAvailabeVoyages()[i] = voyageQueue.poll();
            }
        }

    }

    public void assignShipSlotToVoyage(int shipSlot, int voyageId) {
        final ShipController controller = this.getShipController(shipSlot);
        if (controller.isAvailable()) {
            controller.assignVoyage(voyageId);
            Server.getEventHandler().submit(new VoyageEvent(this.getPlayer(), controller.getVoyageDuration(voyageId), shipSlot));
        } else {
            this.getPlayer().sendMessage("This ship is already on a voyage.");
        }
    }

    public Voyage generateVoyage() {
        final int region = this.getScaledVoyageRegion();
        final int island = SailingUtils.getIslandFromRegion(region);
        final List<Voyage> availableVoyages = VoyageDAO.getInstance().getAllEntries().stream()
                .filter(voyage -> voyage.getRegion() == region)
                .filter(voyage -> voyage.getIsland() == island)
                .collect(Collectors.toList());
        return availableVoyages.get(Misc.random(availableVoyages.size() - 1));
    }




    public int getBestAvailableRegion() {
        final long statTotals = this.getPlayer().getContext().getPlayerSaveData().getLeaguesTravelled();
        if (statTotals <= 500 && statTotals > 0) {
            return 0;
        } else if (statTotals <= 3500 && statTotals > 500) {
            return 1;
        } else if (statTotals <= 10000 && statTotals > 3500) {
            return 2;
        } else if (statTotals <= 31000 && statTotals > 10000) {
            return 3;
        } else if (statTotals <= 75000 && statTotals > 31000) {
            return 4;
        } else if (statTotals <= 110000 && statTotals > 75000) {
            return 5;
        } else if (statTotals <= 180000 && statTotals > 110000) {
            return 6;
        } else {
            return 8;
        }
    }

    private int getScaledVoyageRegion() {
//        final int statTotals = this.calculatePlayerCombatTotal() + this.calculatePlayerMoraleTotal() + this.calculatePlayerSeafaringTotal();
        final long statTotals = this.getPlayer().getContext().getPlayerSaveData().getLeaguesTravelled();
        if (this.getPlayer().getContext().getPlayerSaveData().getPreferredRegion() == -1) {
            if (statTotals <= 500 && statTotals > 0) {
                return 0;
            } else if (statTotals <= 3500 && statTotals > 500) {
                return new IntegerRange(0, 1).getRandomValue();
            } else if (statTotals <= 10000 && statTotals > 3500) {
                return new IntegerRange(0, 2).getRandomValue();
            } else if (statTotals <= 31000 && statTotals > 10000) {
                return new IntegerRange(1, 3).getRandomValue();
            } else if (statTotals <= 75000 && statTotals > 31000) {
                return new IntegerRange(2, 4).getRandomValue();
            } else if (statTotals <= 110000 && statTotals > 75000) {
                return new IntegerRange(3, 5).getRandomValue();
            } else if (statTotals <= 180000 && statTotals > 110000) {
                return new IntegerRange(4, 6).getRandomValue();
            } else {
                return new IntegerRange(5, 8).getRandomValue();
            }
        }
        return this.getPlayer().getContext().getPlayerSaveData().getPreferredRegion();
    }

    @Override
    public int getId() {
        return 24;
    }

    public Sailing2(Player player) {
        super(player);
//        this.shipController = new ShipController[3];
//        shipController[0] = new ShipController(player.getSailingSaveData(),0);
//        shipController[1] = new ShipController(player.getSailingSaveData(),1);
//        shipController[2] = new ShipController(player.getSailingSaveData(),2);
    }

//    private final ShipController[] shipController;

}
