package ethos.runehub.content.instance.impl.rift;

import ethos.clip.Region;
import ethos.model.npcs.NPC;
import ethos.model.npcs.NPCHandler;
import ethos.model.players.Player;
import ethos.runehub.content.instance.impl.TimedInstance;
import ethos.runehub.content.rift.RiftDifficulty;
import ethos.runehub.content.rift.RiftFloor;
import ethos.runehub.content.rift.RiftFloorDAO;
import ethos.runehub.skill.Skill;
import org.runehub.api.util.math.geometry.Point;
import org.runehub.api.util.math.geometry.impl.Rectangle;

import java.util.*;

public class RiftInstance extends TimedInstance {

    public void start(Player player) {
        floors.add(0, floor);

        player.getPA().movePlayer(floor.getStartX(), floor.getStartY(), 0);
        player.getPA().multiWay(1);

        player.getAttributes().setInRift(true);
        player.getAttributes().setStartingFloor(floor.getFloorId());

        this.spawnMobs();
    }

    protected int getNextFloor() {
        int floor = this.getStartingFloor();
        return floor == this.floor.getFloorId() ? getNextFloor() : floor;
    }

    private int getStartingFloor() {
        return Skill.SKILL_RANDOM.nextInt(4);
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

    public void leave(Player player) {
        player.getPA().multiWay(0);
        player.getPlayerAssistant().movePlayer(3087, 3251, 0);
    }

    public void reenterRift(Player player) {
        player.getPA().movePlayer(floors.get(0).getStartX(), floors.get(0).getStartY(), 0);
        player.getPA().multiWay(1);
    }

    private void spawnBoss() {
        this.addMobToFloor(NPCHandler.spawnRiftNpc(2215, this.getOwner().getX(), this.getOwner().getY(), 0, 1, 20, 3, 5, 5));
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

    public Map<Integer, List<NPC>> getSpawnedMobs() {
        return spawnedMobs;
    }

    public void despawnAllMobs() {
        spawnedMobs.keySet().forEach(key -> spawnedMobs.get(key).forEach(npc -> {
            npc.isDead = true;
            npc.applyDead = true;
        }));
        this.spawnedMobs.clear();
    }

    public LinkedList<RiftFloor> getFloors() {
        return floors;
    }

    public RiftInstance(int id, Player owner, Rectangle area, long durationMS, long instanceStartTimestamp, int floorId,
                        RiftDifficulty difficulty, int elitePackCap, int mobCap, RiftFloor floor) {
        super(id, owner, area, durationMS, instanceStartTimestamp, floorId);
        this.difficulty = difficulty;
        this.floors = new LinkedList<>();
        this.spawnedMobs = new HashMap<>();
        this.elitePackCap = elitePackCap;
        this.mobCap = mobCap;
        this.engagedMobs = new HashMap<>();
        this.floor = floor;
    }

    private final int elitePackCap;
    private final int mobCap;
    private RiftFloor floor;
    private final RiftDifficulty difficulty;
    private final LinkedList<RiftFloor> floors;
    private final Map<Integer, List<NPC>> spawnedMobs;
    private final Map<Integer, Boolean> engagedMobs;
}
