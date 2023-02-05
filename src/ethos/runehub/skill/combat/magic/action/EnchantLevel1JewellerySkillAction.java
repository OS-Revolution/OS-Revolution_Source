package ethos.runehub.skill.combat.magic.action;

import com.google.common.base.Preconditions;
import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.skill.SkillAction;
import ethos.runehub.skill.SkillUtils;
import ethos.runehub.skill.combat.magic.Enchantable;
import ethos.runehub.skill.combat.magic.EnchantsCache;
import ethos.runehub.skill.combat.magic.EnchantsDAO;
import ethos.runehub.skill.combat.magic.MagicTablet;
import ethos.runehub.skill.combat.magic.spell.Rune;
import ethos.runehub.skill.combat.magic.spell.RuneCache;
import ethos.runehub.skill.combat.magic.spell.Spell;
import org.runehub.api.util.SkillDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EnchantLevel1JewellerySkillAction extends SkillAction {


    @Override
    protected void onActionStart() {
        this.getActor().startAnimation(spell.getAnimationId());
        this.getActor().gfx100(spell.getGfxId());
        this.deleteRunes(this.getRuneItemIdsToRemove(this.getRuneIdentifiersToRemove()));
        this.getActor().getItems().deleteItem(targetItemId,1);
    }

    @Override
    protected void onActionStop() {

    }

    @Override
    protected void onTick() {
        this.getActor().getItems().addItem(EnchantsCache.getInstance().read(targetItemId).getEchantedItemId(), 1);
        this.getActor().getSkillController().addXP(this.getSkillId(), spell.getCastXP());
        this.stop();
    }

    @Override
    protected void onUpdate() {

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
        Preconditions.checkArgument(EnchantsCache.getInstance().read(targetItemId) != null, "This item can not be enchanted.");
    }

    @Override
    protected void validateWorldRequirements() {}

    @Override
    protected void updateAnimation() {}

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
//        identifiers.forEach(System.out::println);
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
//        System.out.println("=========================");
//        runesToDelete.forEach(System.out::println);
        for (int j = 0; j < runesToDelete.size(); j++) {
//            System.out.println("Checking for rune: " + spell.getRunes()[j][0]);
            if (this.getActor().getItems().playerHasItem(runesToDelete.get(j), spell.getRunes()[j][1])) {
//                System.out.println("Deleting rune: " + runesToDelete.get(j));
                this.getActor().getItems().deleteItem2(runesToDelete.get(j), spell.getRunes()[j][1]);
            }
        }
    }

    public EnchantLevel1JewellerySkillAction(Player actor, int targetItemId, Spell spell) {
        super(actor, SkillDictionary.Skill.MAGIC.getId(), spell.getTicks());
        this.spell = spell;
        this.targetItemId = targetItemId;
//        EnchantsDAO.getInstance().create(new Enchantable(1637,2550,1155));
//        EnchantsDAO.getInstance().create(new Enchantable(11072,11074,1155));
//        EnchantsDAO.getInstance().create(new Enchantable(1656,3853,1155));
//        EnchantsDAO.getInstance().create(new Enchantable(1694,1727,1155));
    }

    private final int targetItemId;
    private final Spell spell;
}
