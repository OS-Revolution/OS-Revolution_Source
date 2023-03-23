package ethos.runehub.entity.combat;

import org.runehub.api.model.entity.user.character.CharacterEntity;

public class CombatController<E extends CharacterEntity,T extends CharacterEntity> {

    public static final int MELEE = 0;
    public static final int RANGED = 1;
    public static final int MAGIC = 2;

    public void target(T entity) {
        if (canTarget(entity)) {
            this.setTargetedEntity(entity);
        }
    }

    protected boolean canTarget(T entity) {
        return true;
    }

    public T getTargetedEntity() {
        return targetedEntity;
    }

    public void setTargetedEntity(T targetedEntity) {
        this.targetedEntity = targetedEntity;
    }

    public E getTargetingEntity() {
        return targetingEntity;
    }

    public CombatController(E targetingEntity) {
        this.targetingEntity = targetingEntity;
    }

    protected final E targetingEntity;
    protected T targetedEntity;
}
