package ethos.runehub.content.instance.impl;

import ethos.runehub.entity.item.GameItem;
import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
@StoredObject(tableName = "instance_bosses")
public class InstanceBoss {

    public int getNpcId() {
        return npcId;
    }

    public int getMaxHit() {
        return maxHit;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public boolean isMembers() {
        return members;
    }

    public GameItem[] getPayment() {
        return payment;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setHitpoints(int hitpoints) {
        this.hitpoints = hitpoints;
    }

    public void setMaxHit(int maxHit) {
        this.maxHit = maxHit;
    }

    public void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }

    public InstanceBoss(int npcId, int maxHit, int attack, int defence, int hitpoints, int respawnTime, boolean members, GameItem[] payment) {
        this.npcId = npcId;
        this.maxHit = maxHit;
        this.attack = attack;
        this.defence = defence;
        this.hitpoints = hitpoints;
        this.respawnTime = respawnTime;
        this.members = members;
        this.payment = payment;
    }

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int npcId;
    @StoredValue(type = SqlDataType.INTEGER)
    private int maxHit;
    @StoredValue(type = SqlDataType.INTEGER)
    private int attack;
    @StoredValue(type = SqlDataType.INTEGER)
    private int defence;
    @StoredValue(type = SqlDataType.INTEGER)
    private int hitpoints;
    @StoredValue(type = SqlDataType.INTEGER)
    private int respawnTime;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.JSON)
    private final GameItem[] payment;
}
