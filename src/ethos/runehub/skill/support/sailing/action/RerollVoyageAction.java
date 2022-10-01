package ethos.runehub.skill.support.sailing.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.support.sailing.SailingUtils;
import ethos.runehub.skill.support.sailing.voyage.Voyage;
import ethos.runehub.skill.support.sailing.voyage.VoyageContext;
import ethos.runehub.skill.support.sailing.voyage.VoyageDAO;
import ethos.runehub.world.wushanko.island.IslandLoader;
import org.runehub.api.model.math.impl.IntegerRange;
import org.runehub.api.util.PreconditionUtils;

public class RerollVoyageAction extends VoyageMenuAction {

    @Override
    protected void onAction() {
        this.getActor().getContext().getPlayerSaveData().setVoyageRerolls(this.getActor().getContext().getPlayerSaveData().getVoyageRerolls() - 1);
        this.getActor().sendMessage("You have #" + this.getActor().getContext().getPlayerSaveData().getVoyageRerolls() + " re-rolls available.");
        this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().remove(voyageSlot);
        this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().add(voyageSlot, generateVoyage());
        this.getActor().getContext().getPlayerSaveData().setVoyagesRerolled(this.getActor().getContext().getPlayerSaveData().getVoyagesRerolled() + 1);
        this.getActor().save();
    }

    @Override
    protected void validate() {
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().getContext().getPlayerSaveData().getVoyageRerolls() == 0), "You do not have any voyage re-rolls available.");
        Preconditions.checkArgument(PreconditionUtils.isFalse(this.getActor().getSkillController().getSailing().getShipSlot(shipSlot).onVoyage(this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().get(voyageSlot))), "You can't re-roll a voyage that you are on.");
        Preconditions.checkArgument(PreconditionUtils.isTrue(this.getActor().getSkillController().getSailing().voyageAvailable(this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().get(voyageSlot))), "Another ship is on this voyage.");
    }

    private Voyage generateVoyage() {
        if (Skill.SKILL_RANDOM.nextInt(50 - this.getSpecialVoyageChance()) == 0) {
            final Voyage voyage = VoyageDAO.getInstance().getAllEntries().get(Skill.SKILL_RANDOM.nextInt(VoyageDAO.getInstance().getAllEntries().size()));
                if (!this.getActor().getContext().getPlayerSaveData().getStoryVoyagesCompleted().contains(voyage.getId())
                        && this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().stream().noneMatch(v -> v.getId() == voyage.getId())) {
                    return voyage;
                }
        } else {
            final int id = new IntegerRange(1, 500000).getRandomValue();
            final int region = this.getScaledVoyageRegion();
            final int island = SailingUtils.getIslandFromRegion(region);
            if (this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().stream().noneMatch(voyage -> voyage.getIsland() == island))
                return new Voyage(
                        id,
                        IslandLoader.getInstance().read(island).getName(),
                        SailingUtils.getStatRangeBasedOnRegion(region),
                        SailingUtils.getStatRangeBasedOnRegion(region),
                        SailingUtils.getStatRangeBasedOnRegion(region),
                        SailingUtils.getDistanceFromRegion(region),
                        region,
                        island,
                        false,
                        false,
                        new VoyageContext(
                                new int[]{
                                        0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0,
                                        0, 0, 0, 0, 0,
                                        0, 0, 0, 0
                                },
                                new int[]{SailingUtils.getLootTableContainerIdForRegion(region), SailingUtils.getLootTableContainerIdForIsland(island)}
                        )
                );
        }
        return generateVoyage();
    }

    private int getScaledVoyageRegion() {
        final long statTotals = this.getActor().getContext().getPlayerSaveData().getLeaguesTravelled();
        if (this.getActor().getContext().getPlayerSaveData().getPreferredRegion() == -1) {
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
        return this.getActor().getContext().getPlayerSaveData().getPreferredRegion();
    }

    private int getSpecialVoyageChance() {
        return Math.round(this.getActor().getSkillController().getSailing().getScore() * 0.03f);
    }

    private int getVoyageIndex(Voyage voyage) {
        int index = 0;
        for (int i = 0; i < this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().size(); i++) {
            if (voyage.getId() == this.getActor().getContext().getPlayerSaveData().getAvailableVoyages().get(i).getId())
                return i;
        }
        return index;
    }

    public RerollVoyageAction(Player actor, int slot, int voyage) {
        super(actor,slot,voyage);
    }
}
