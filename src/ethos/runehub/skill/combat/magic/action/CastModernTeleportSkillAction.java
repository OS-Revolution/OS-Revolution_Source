package ethos.runehub.skill.combat.magic.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.combat.magic.spell.Rune;
import ethos.runehub.skill.combat.magic.spell.RuneCache;
import ethos.runehub.skill.combat.magic.spell.Spell;
import org.runehub.api.util.SkillDictionary;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CastModernTeleportSkillAction extends SkillAction {


    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(spell.getAnimationId());
    }

    @Override
    protected void onActionStop() {
        this.getActor().startAnimation(715);
        this.getActor().gfx0(-1);
        this.getActor().teleporting = false;
    }

    @Override
    protected void onTick() {
        final Point selectedTeleportPoint = teleportArea.getAllPoints().get(Skill.SKILL_RANDOM.nextInt(teleportArea.getAllPoints().size()));
        this.getActor().getPA().movePlayer(selectedTeleportPoint.getX(),selectedTeleportPoint.getY(),0);
        this.getActor().getSkillController().addXP(this.getSkillId(),spell.getCastXP());
        this.deleteRunes(this.getRuneItemIdsToRemove(this.getRuneIdentifiersToRemove()));
        this.stop();
    }

    @Override
    protected void onUpdate() {
        if (this.getElapsedTicks() == 2) {
            this.getActor().gfx100(spell.getGfxId());
        }
    }

    @Override
    protected void validateInventory() {
    }

    @Override
    protected void validateLevelRequirements() {
        Preconditions.checkArgument(this.getActor().getSkillController().getLevel(this.getSkillId()) >= spell.getLevel(), "You need a $"
                + RunehubUtils.getSkillName(this.getSkillId()) + " level of $"
                + spell.getLevel() + " to do this.");
    }

    @Override
    protected void validateItemRequirements() {
        Preconditions.checkArgument(Arrays.stream(spell.getRunes()).allMatch(rune ->
                        (Arrays.stream(RuneCache.getInstance().read(rune[0]).getValidEquipment()).anyMatch(equipmentId -> this.getActor().getItems().isWearingItem(equipmentId)))
                                || Arrays.stream(RuneCache.getInstance().read(rune[0]).getValidItems()).anyMatch(itemId -> (this.getActor().getItems().playerHasItem(itemId, rune[1]))))
                , "You do not have the required runes."
        );
    }

    @Override
    protected void validateWorldRequirements() {
//        Preconditions.checkArgument(this.getActor().getContext().getPlayerSaveData().getLecternHotspot() > 0, "You must be at a lectern to do this.");
//        Preconditions.checkArgument(Server.getGlobalObjects().exists(this.getActor().getContext().getPlayerSaveData().getLecternHotspot(),  3092, 3223),"You must be at a lectern to do this.");
    }

    @Override
    protected void updateAnimation() {
        this.getActor().startAnimation(793);
    }

    @Override
    protected void addItems(int id, int amount) {

    }

    private List<Integer> getRuneIdentifiersToRemove() {
        final List<Integer> usedRunes = new ArrayList<>(spell.getRunes().length);
        for (int i = 0; i < spell.getRunes().length; i++) {
            Rune rune = RuneCache.getInstance().read(spell.getRunes()[i][0]);
            if (Arrays.stream(rune.getValidEquipment()).noneMatch(value -> this.getActor().getItems().isWearingItem(value))) {
                usedRunes.add(rune.getRuneId().ordinal());
            }
        }
        return usedRunes;
    }

    private List<Integer> getRuneItemIdsToRemove(List<Integer> identifiers) {
        final List<Integer> usedRunes = new ArrayList<>(spell.getRunes().length);
        for (int i = 0; i < identifiers.size(); i++) {
            Rune rune = RuneCache.getInstance().read(identifiers.get(i));
            for (int j = 0; j < rune.getValidItems().length; j++) {
                if (this.getActor().getItems().playerHasItem(rune.getValidItems()[j])) {
                    usedRunes.add(rune.getValidItems()[j]);
                    break;
                }
            }
        }
        return usedRunes;
    }

    private void deleteRunes(List<Integer> runesToDelete) {
        for (int j = 0; j < runesToDelete.size(); j++) {
            if (this.getActor().getItems().playerHasItem(runesToDelete.get(j), spell.getRunes()[j][1])) {
                this.getActor().getItems().deleteItem2(runesToDelete.get(j), spell.getRunes()[j][1]);
            }
        }
    }

    public CastModernTeleportSkillAction(Player actor, Rectangle teleportArea, Spell spell) {
        super(actor, SkillDictionary.Skill.MAGIC.getId(), spell.getTicks());
        this.teleportArea = teleportArea;
        this.spell = spell;
    }

    private final Spell spell;
    private final Rectangle teleportArea;
}
