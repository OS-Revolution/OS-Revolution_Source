package ethos.runehub.content.loyalty;

import ethos.model.players.Player;

public class LoyaltyProgramController {

    private static LoyaltyProgramController instance = null;

    public static LoyaltyProgramController getInstance() {
        if (instance == null)
            instance = new LoyaltyProgramController();
        return instance;
    }

    public void onDailyLogin(Player player) {

    }

    private int getLoyaltyTier() {
        return 0;
    }

    private LoyaltyProgramController(){}
}
