package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.ui.component.impl.TextComponent;

import java.text.NumberFormat;
import java.util.Map;

public class PlayPassHiscoreUI extends JournalUI {

    @Override
    public void onOpen() {
        super.onOpen();
        titleTextComponent.setText("Play-Pass Hiscores");
        this.drawContent();
        this.updateUI();
    }

    private void drawContent() {
        Map<String,Integer> hiscores = RunehubUtils.getPlayPassHiscores();

        int index = 1;
        for (String playerName : hiscores.keySet()) {
            if (index < entryTextComponent.length) {
                int score = hiscores.get(playerName);
                TextComponent textComponent = entryTextComponent[index];
                if (this.getPlayer().playerName.equalsIgnoreCase(playerName)) {
                    textComponent.setText("@blu@" + index + ". " + this.getPlayer().playerName  + " - " + NumberFormat.getInstance().format(score));
                } else {
                    textComponent.setText(index + ". " + playerName + " - " + NumberFormat.getInstance().format(score));
                }
                index++;
            }
        }
        this.updateUI();
    }

    public PlayPassHiscoreUI(Player player) {
        super(player);
    }
}
