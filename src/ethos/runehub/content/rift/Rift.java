package ethos.runehub.content.rift;

import ethos.clip.Region;
import ethos.event.Event;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.content.rift.party.Party;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import ethos.runehub.skill.Skill;
import org.runehub.api.util.math.geometry.Point;

import java.time.Duration;
import java.util.*;

public class Rift extends Event<Player> {

    public static final int NEPHALEM_RIFT_COST = 1000;

    @Override
    public void execute() {
        leave(attachment);
    }

    public void leave(Player player) {
        player.getPA().multiWay(0);
        player.getPlayerAssistant().movePlayer(3087, 3251, 0);
    }

    public void reenterRift(Player player) {
        player.getPA().movePlayer(this.getFloors().get(0).getStartX(), this.getFloors().get(0).getStartY(), 0);
        player.getPA().multiWay(1);
    }

    public void start(Player player) {
        this.floor = RiftFloorDAO.getInstance().read(this.getStartingFloor());
        floors.add(0, floor);

        player.getPA().movePlayer(floor.getStartX(), floor.getStartY(), 0);
        player.getPA().multiWay(1);

        player.getAttributes().setInRift(true);
        player.getAttributes().setStartingFloor(floor.getFloorId());

        this.spawnMobs();
    }

    public void nextFloor(Player player) {
        this.floor = RiftFloorDAO.getInstance().read(this.getNextFloor());
        floors.add(floors.size(), floor);

        player.getPA().multiWay(1);
        player.getPA().movePlayer(floor.getStartX(), floor.getStartY(), 0);


        if (!spawnedMobs.containsKey(floor.getFloorId())) {
            this.spawnMobs();
        }
    }

    public void previousFloor(Player player) {
        RiftFloor previousFloor = floor;
        if (previousFloor.getFloorId() == player.getAttributes().getStartingFloor()) {
            leave(player);
        } else if (floors.size() > 1) {
            for (int i = 0; i < floors.size(); i++) {
                if (floors.get(i).getFloorId() == floor.getFloorId()) {
                    previousFloor = floors.get(i - 1);
                }
            }
            floor = previousFloor;
            player.getPA().movePlayer(previousFloor.getStartX(), previousFloor.getStartY(), 0);
        } else {
            leave(player);
        }
    }

    private void spawnBoss() {
        Player partyMember = Arrays.stream(party.getMembers()).filter(player -> floor.getBoundingBox().contains(new Point(player.absX,player.absY)))
                .findFirst().orElse(party.getMembers()[0]);
        this.addMobToFloor(NPCHandler.spawnRiftNpc(2215, partyMember.getX(), partyMember.getY(), 0, 1, 20, 3, 5, 5));
    }

    private void moveParty(Point point) {
        Arrays.stream(party.getMembers()).forEach(player -> player.getPA().movePlayer(point.getX(), point.getY(), 0));
    }

    private void setRiftConditions() {
        Arrays.stream(party.getMembers()).forEach(player -> {
            player.getPA().multiWay(1);
            player.getAttributes().setInRift(true);
        });
    }

    public void despawnAllMobs() {
        spawnedMobs.keySet().forEach(key -> spawnedMobs.get(key).forEach(npc -> {
            npc.isDead = true;
            npc.applyDead = true;
        }));
        this.spawnedMobs.clear();
    }

    private int[] getSelectedMob(int[] pool) {
        int[] mobs = new int[3];
        for (int i = 0; i < mobs.length; i++) {
            int type = pool[Skill.SKILL_RANDOM.nextInt(pool.length)];
            if (mobs[i] != type) {
                mobs[i] = type;
            }
        }
        return mobs;
    }

    protected void spawnMobs() {
        floor.getBoundingBox().getAllPoints().stream().filter(point -> Region.getClipping(point.getX(), point.getY(), 0) == 0)
                .filter(point -> !spawnedMobs.containsKey(floor.getFloorId()) || spawnedMobs.get(floor.getFloorId()).size() <= mobCap)
                .filter(point -> Skill.SKILL_RANDOM.nextInt(100) <= 5)
                .forEach(point -> {
                    this.spawnElite(point);
                    this.spawnChampion(point);
                    this.spawnTrash(point);
                });
    }

    private void spawnElite(Point point) {
        int[] eliteMobs = this.getSelectedMob(floor.getEliteMobPool());
        if (rolledPackSpawn(1)) {
            for (int i = 0; i < 3; i++) { //3 = pack size
                int eliteMobType = eliteMobs[Skill.SKILL_RANDOM.nextInt(eliteMobs.length)];
                NPC mob = NPCHandler.spawnRiftNpc(eliteMobType, point.getX(), point.getY(), 0, 1, getMobHP(45), 4* difficulty.ordinal(), 40* difficulty.ordinal(), 40* difficulty.ordinal());
                mob.riftMobType = 1;
                this.addMobToFloor(mob);
                engagedMobs.put(mob.npcType, false);
            }
        }
    }

    private void spawnChampion(Point point) {
        int[] eliteMobs = this.getSelectedMob(floor.getEliteMobPool());
        if (rolledPackSpawn(2)) {
            for (int i = 0; i < 3; i++) { //3 = pack size
                int eliteMobType = eliteMobs[Skill.SKILL_RANDOM.nextInt(eliteMobs.length)];
                NPC mob = NPCHandler.spawnRiftNpc(eliteMobType, point.getX(), point.getY(), 0, 1, getMobHP(70), 5* difficulty.ordinal(), 50* difficulty.ordinal(), 50* difficulty.ordinal());
                mob.riftMobType = 2;
                this.addMobToFloor(mob);
                engagedMobs.put(mob.npcType, false);

            }
        }
    }

    private int getMobHP(int baseHP) {
        return difficulty.ordinal() > 0 ? baseHP * difficulty.ordinal() : baseHP;
    }

    private void spawnTrash(Point point) {
        int[] trashMobs = this.getSelectedMob(floor.getTrashMobPool());
        int trashMobType = trashMobs[Skill.SKILL_RANDOM.nextInt(trashMobs.length)];
        NPC mob = NPCHandler.spawnRiftNpc(trashMobType, point.getX(), point.getY(), 0, 1, getMobHP(30), 3* difficulty.ordinal(), 20* difficulty.ordinal(), 20* difficulty.ordinal());
        this.addMobToFloor(mob);
    }

    private void addMobToFloor(NPC mob) {
        if (spawnedMobs.containsKey(floor.getFloorId())) {
            spawnedMobs.get(floor.getFloorId()).add(mob);
        } else {
            spawnedMobs.put(floor.getFloorId(), new ArrayList<>());
            spawnedMobs.get(floor.getFloorId()).add(mob);
        }
    }

    private boolean rolledPackSpawn(int type) {
        return type == 1 ? Skill.SKILL_RANDOM.nextInt(200) <= 5 : Skill.SKILL_RANDOM.nextInt(200) <= 3;
    }

    public void engagePlayer(Player player,int mobType, int riftMobType) {
        if (!engagedMobs.get(mobType)) {
            engagedMobs.computeIfPresent(mobType, (key, val) -> true);
            if (riftMobType == 1)
                party.sendMessage(player.playerName + " has engaged @blu@" + HostileMobIdContextLoader.getInstance().read(mobType).getName());
            else if (riftMobType == 2)
                party.sendMessage(player.playerName + " has engaged @yel@" + HostileMobIdContextLoader.getInstance().read(mobType).getName());
        }
    }

    public LinkedList<RiftFloor> getFloors() {
        return floors;
    }

    protected int getNextFloor() {
        int floor = this.getStartingFloor();
        return floor == this.floor.getFloorId() ? getNextFloor() : floor;
    }

    protected int getStartingFloor() {
        return Skill.SKILL_RANDOM.nextInt(4);
    }

    public int getElitePackCap() {
        return elitePackCap;
    }

    public int getMobCap() {
        return mobCap;
    }

    public long getTimeLimit() {
        return timeLimit;
    }

    public RiftDifficulty getDifficulty() {
        return difficulty;
    }

    public Map<Integer, Boolean> getEngagedMobs() {
        return engagedMobs;
    }

    public void increaseProgress(float amount) {
        this.currentProgress += amount;
        party.sendMessage("Progress " + (currentProgress * 100) + "%");
        if (currentProgress >= 1.0f) {
            spawnBoss();
            party.sendMessage("You've beaten the rift");
        }
    }

    public Rift(Player player,RiftDifficulty difficulty) {
        this(5, 70, Duration.ofMinutes(10).toMillis(), player,difficulty);
    }

    public Rift(int elitePackCap, int mobCap, long timeLimit, Player player,RiftDifficulty difficulty) {
        super("rift", player, TimeUtils.getMsAsTicks(timeLimit));
        this.elitePackCap = elitePackCap;
        this.mobCap = mobCap;
        this.timeLimit = timeLimit;
        this.party = player.getAttributes().getParty() == null ? new Party(player) : player.getAttributes().getParty();
        this.spawnedMobs = new HashMap<>();
        this.engagedMobs = new HashMap<>();
        this.floors = new LinkedList<>();
        this.difficulty = difficulty;
    }

    private float currentProgress;
    private RiftFloor floor;
    private final int elitePackCap;
    private final int mobCap;
    private final long timeLimit;
    private final Party party;
    private final Map<Integer, List<NPC>> spawnedMobs;
    private final Map<Integer, Boolean> engagedMobs;
    private final LinkedList<RiftFloor> floors;
    private final RiftDifficulty difficulty;


}
