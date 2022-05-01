package ethos.runehub.entity.mob;


import org.runehub.api.io.data.QueryParameter;
import org.runehub.api.io.data.SqlDataType;
import org.runehub.api.io.data.StoredObject;
import org.runehub.api.io.data.StoredValue;
import org.runehub.api.model.entity.EntityContext;
import org.runehub.api.model.entity.item.Drop;

import java.util.ArrayList;
import java.util.List;

@StoredObject(tableName = "hostile_mobs")
public class HostileMobContext extends EntityContext {

    @StoredValue(type = SqlDataType.INTEGER, parameter = QueryParameter.PRIMARY_KEY, id = true)
    private final int id;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int combatLevel;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int size;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int hitpoints;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int maxHit;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int slayerLevel;
    @StoredValue(type = SqlDataType.LONGTEXT)
    private final String name;
    @StoredValue(type = SqlDataType.LONGTEXT)
    private final String release;
    @StoredValue(type = SqlDataType.LONGTEXT)
    private final String wiki;
    @StoredValue(type = SqlDataType.LONGTEXT)
    private final String examine;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean members;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean aggressive;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean poisonous;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean venomous;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean poisonImmune;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean venomImmune;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean slayerMonster;
    @StoredValue(type = SqlDataType.BOOLEAN)
    private final boolean duplicate;
    @StoredValue(type = SqlDataType.BLOB)
    private final List<Drop> drops;
    @StoredValue(type = SqlDataType.BLOB)
    private final List<String> attackType;
    @StoredValue(type = SqlDataType.BLOB)
    private final List<String> attributes;
    @StoredValue(type = SqlDataType.BLOB)
    private final List<String> category;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int defenceLevel;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int defenceSlash;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int defenceCrush;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int defenceStab;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int defenceRanged;
    @StoredValue(type = SqlDataType.INTEGER)
    private final int defenceMage;

    private HostileMobContext(NpcContextBuilder builder) {
        super(builder.id);
        this.id = builder.id;
        this.combatLevel = builder.combatLevel;
        this.size = builder.size;
        this.hitpoints = builder.hitpoints;
        this.maxHit = builder.maxHit;
        this.slayerLevel = builder.slayerLevel;
        this.name = builder.name;
        this.release = builder.release;
        this.examine = builder.examine;
        this.members = builder.members;
        this.aggressive = builder.aggressive;
        this.poisonous = builder.poisonous;
        this.venomous = builder.venomous;
        this.poisonImmune = builder.poisonImmune;
        this.venomImmune = builder.venomImmune;
        this.slayerMonster = builder.slayerMonster;
        this.duplicate = builder.duplicate;
        this.drops = builder.drops;
        this.attackType = builder.attackType;
        this.attributes = builder.attributes;
        this.category = builder.category;
        this.wiki = builder.wiki;
        this.defenceCrush = builder.defenceCrush;
        this.defenceLevel = builder.defenceLevel;
        this.defenceMage = builder.defenceMage;
        this.defenceRanged = builder.defenceRanged;
        this.defenceSlash = builder.defenceSlash;
        this.defenceStab = builder.defenceStab;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public int getSize() {
        return size;
    }

    public int getHitpoints() {
        return hitpoints;
    }

    public int getMaxHit() {
        return maxHit;
    }

    public int getSlayerLevel() {
        return slayerLevel;
    }

    public String getName() {
        return name;
    }

    public String getRelease() {
        return release;
    }

    public String getExamine() {
        return examine;
    }

    public boolean isMembers() {
        return members;
    }

    public boolean isAggressive() {
        return aggressive;
    }

    public boolean isPoisonous() {
        return poisonous;
    }

    public boolean isVenomous() {
        return venomous;
    }

    public boolean isPoisonImmune() {
        return poisonImmune;
    }

    public boolean isVenomImmune() {
        return venomImmune;
    }

    public boolean isSlayerMonster() {
        return slayerMonster;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public List<Drop> getDrops() {
        return drops;
    }

    public List<String> getAttackType() {
        return attackType;
    }

    public List<String> getAttributes() {
        return attributes;
    }

    public List<String> getCategory() {
        return category;
    }

    public String getWiki() {
        return wiki;
    }

    public int getDefenceCrush() {
        return defenceCrush;
    }

    public int getDefenceRanged() {
        return defenceRanged;
    }

    public int getDefenceSlash() {
        return defenceSlash;
    }

    public int getDefenceLevel() {
        return defenceLevel;
    }

    public int getDefenceStab() {
        return defenceStab;
    }

    public int getDefenceMage() {
        return defenceMage;
    }

    public static class NpcContextBuilder {

        private final int id;
        private final String name;
                private List<Drop> drops;
        private List<String> attackType, attributes, category;
        private int combatLevel, size, hitpoints, maxHit, slayerLevel,defenceLevel,defenceStab,
        defenceSlash,defenceCrush,defenceRanged,defenceMage;
        private String release, examine,wiki;
        private boolean members, aggressive, poisonous, venomous, poisonImmune,
                venomImmune, slayerMonster, duplicate;

        public HostileMobContext build() {
            return new HostileMobContext(this);
        }

        public NpcContextBuilder setWiki(String wiki) {
            this.wiki = wiki;
            return this;
        }

        public NpcContextBuilder setDuplicate(boolean duplicate) {
            this.duplicate = duplicate;
            return this;
        }

        public NpcContextBuilder setSlayerMonster(boolean slayerMonster) {
            this.slayerMonster = slayerMonster;
            return this;
        }

        public NpcContextBuilder setVenomImmune(boolean venomImmune) {
            this.venomImmune = venomImmune;
            return this;
        }

        public NpcContextBuilder setPoisonImmune(boolean poisonImmune) {
            this.poisonImmune = poisonImmune;
            return this;
        }

        public NpcContextBuilder setVenomous(boolean venomous) {
            this.venomous = venomous;
            return this;
        }

        public NpcContextBuilder setPoisonous(boolean poisonous) {
            this.poisonous = poisonous;
            return this;
        }

        public NpcContextBuilder setAggressive(boolean aggressive) {
            this.aggressive = aggressive;
            return this;
        }

        public NpcContextBuilder setMembers(boolean members) {
            this.members = members;
            return this;
        }

        public NpcContextBuilder setExamine(String examine) {
            this.examine = examine;
            return this;
        }

        public NpcContextBuilder setReleaseDate(String release) {
            this.release = release;
            return this;
        }

        public NpcContextBuilder setSlayerLevel(int slayerLevel) {
            this.slayerLevel = slayerLevel;
            return this;
        }

        public NpcContextBuilder setMaxHit(int maxHit) {
            this.maxHit = maxHit;
            return this;
        }

        public NpcContextBuilder setHitpoints(int hitpoints) {
            this.hitpoints = hitpoints;
            return this;
        }

        public NpcContextBuilder setSize(int size) {
            this.size = size;
            return this;
        }

        public NpcContextBuilder setCombatLevel(int level) {
            this.combatLevel = level;
            return this;
        }

        public NpcContextBuilder setDefenceLevel(int value) {
            this.defenceLevel = value;
            return this;
        }

        public NpcContextBuilder setDefenceCrush(int value) {
            this.defenceCrush = value;
            return this;
        }

        public NpcContextBuilder setDefenceStab(int value) {
            this.defenceStab = value;
            return this;
        }

        public NpcContextBuilder setDefenceSlash(int value) {
            this.defenceSlash = value;
            return this;
        }

        public NpcContextBuilder setDefenceMage(int value) {
            this.defenceMage = value;
            return this;
        }

        public NpcContextBuilder setDefenceRanged(int value) {
            this.defenceRanged = value;
            return this;
        }

        public NpcContextBuilder setCategories(List<String> categories) {
            this.category = categories;
            return this;
        }

        public NpcContextBuilder addCategory(String category) {
            this.category.add(category);
            return this;
        }

        public NpcContextBuilder setAttributes(List<String> attributes) {
            this.attributes = attributes;
            return this;
        }

        public NpcContextBuilder addAttribute(String attribute) {
            this.attributes.add(attribute);
            return this;
        }

        public NpcContextBuilder setAttackTypes(List<String> attackTypes) {
            this.attackType = attackTypes;
            return this;
        }

        public NpcContextBuilder addAttackType(String attackType) {
            this.attackType.add(attackType);
            return this;
        }

        public NpcContextBuilder addDrop(Drop drop) {
            this.drops.add(drop);
            return this;
        }

        public NpcContextBuilder setDrops(List<Drop> drops) {
            this.drops = drops;
            return this;
        }

        public NpcContextBuilder(int id, String name) {
            this.id = id;
            this.name = name;
            this.drops = new ArrayList<>();
            this.attackType = new ArrayList<>();
            this.attributes = new ArrayList<>();
            this.category = new ArrayList<>();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
