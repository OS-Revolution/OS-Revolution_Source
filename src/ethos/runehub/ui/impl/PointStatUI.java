package ethos.runehub.ui.impl;

import ethos.model.players.Player;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.PointController;
import ethos.runehub.ui.component.impl.TextComponent;

import java.text.NumberFormat;
import java.util.Map;

public class PointStatUI extends JournalUI{

    @Override
    public void onOpen() {
        super.onOpen();
        titleTextComponent.setText("Point Totals");
        this.drawContent();
        this.updateUI();
    }

    private void drawContent() {
        for (int i = 0; i < PointController.PointType.values().length; i++) {
            TextComponent textComponent = entryTextComponent[i + 1];
            PointController.PointType pointType = PointController.PointType.values()[i];

            textComponent.setText(pointType.toString() + " - @blu@" + NumberFormat.getInstance().format(
                    this.getPlayer().getAttributes().getPointController().getPoints(i)
            ));
        }

        this.updateUI();
    }

    public PointStatUI(Player player) {
        super(player);
    }
}
