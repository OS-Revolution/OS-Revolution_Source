package ethos.runehub.skill.artisan.smithing;

import ethos.model.players.Player;
import ethos.runehub.content.upgrading.UpgradeRule;
import ethos.runehub.skill.artisan.ArtisanSkill;
import org.runehub.api.util.SkillDictionary;

import java.util.*;
import java.util.stream.Collectors;

public class Smithing extends ArtisanSkill {

    public Smeltable getBestSmeltableInInventory() {
        final List<Smeltable> bars = new ArrayList<>();
        for (int i = 0; i < this.getPlayer().playerItems.length; i++) {
            int itemId = this.getPlayer().playerItems[i] - 1;
            for (int j = 0; j < Smeltable.values().length; j++) {
                if (Smeltable.values()[j].getProductId() == itemId && this.getPlayer().getSkillController().getLevel(this.getId()) >= Smeltable.values()[j].getLevelRequired()) {
                    bars.add(Smeltable.values()[j]);
                }
            }
        }
        bars.sort(Comparator.comparingInt(Smeltable::getLevelRequired));
        Collections.reverse(bars);
        if (!bars.isEmpty())
            return bars.get(0);
        return null;
    }

    public float getUpgradeBonus() {
        return this.getPlayer().getSkillController().getLevel(SkillDictionary.Skill.SMITHING.getId()) * 0.005f;
    }

    public int getBestSmeltableInInventoryId() {
        return this.getBestSmeltableInInventory() == null ? -1 : this.getBestSmeltableInInventory().getProductId();
    }

    public Smithing(Player player) {
        super(player);
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.SMITHING.getId();
    }
}
