package ethos.runehub.skill.artisan.fletching;

import ethos.model.items.ItemAssistant;
import ethos.model.players.Player;
import ethos.runehub.skill.artisan.ArtisanSkill;
import ethos.runehub.skill.artisan.fletching.bows.UnstrungBowCache;
import org.runehub.api.util.SkillDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Fletching extends ArtisanSkill {

    public void sendItemSelectionFrame(int baseId) {
        final List<Integer> fletchables = FletchableDAO.getInstance().getAllEntries().stream()
                .filter(fletchable -> Arrays.stream(fletchable.getMaterials()).anyMatch(gameItem -> gameItem.getId() == baseId))
                .map(Fletchable::getProductId)
                .collect(Collectors.toList());
        this.getPlayer().getPA().sendFrame164(8880);
        this.getPlayer().getPA().sendFrame126("What would you like to make?", 8879);
        if (fletchables.size() == 1) {
            fletchables.add(0,-1);
            fletchables.add(-1);
        } else if (fletchables.size() == 2) {
            fletchables.add(-1);
        }
        this.getPlayer().getPA().sendFrame246(8883, 190, fletchables.get(1));
        this.getPlayer().getPA().sendFrame126(ItemAssistant.getItemName(fletchables.get(1)), 8889);
        this.getPlayer().getPA().sendFrame246(8884, 190, fletchables.get(0));
        this.getPlayer().getPA().sendFrame126(ItemAssistant.getItemName(fletchables.get(0)), 8893);
        this.getPlayer().getPA().sendFrame246(8885, 190, fletchables.get(2));
        this.getPlayer().getPA().sendFrame126(ItemAssistant.getItemName(fletchables.get(2)), 8897);
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.FLETCHING.getId();
    }

    public Fletching(Player player) {
        super(player);
    }
}
