package ethos.runehub.entity.combat;

import org.runehub.api.model.entity.Entity;

public class CombatContext {

    public Entity getAttacker() {
        return attacker;
    }

    public Entity getDefender() {
        return defender;
    }

    public CombatContext(Entity attacker, Entity defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    private final Entity attacker,defender;
}
