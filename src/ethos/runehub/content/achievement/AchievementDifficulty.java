package ethos.runehub.content.achievement;

import ethos.util.Misc;

public enum AchievementDifficulty {

    EASY,MEDIUM,HARD,ELITE;

    @Override
    public String toString() {
        return Misc.capitalize(name().toLowerCase());
    }
}
