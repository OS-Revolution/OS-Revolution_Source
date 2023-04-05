package ethos.runehub.skill.support.slayer;

import ethos.Server;
import ethos.clip.Region;
import ethos.model.players.Player;
import ethos.runehub.RunehubConstants;
import ethos.runehub.dialog.DialogSequence;
import ethos.runehub.entity.mob.hostile.HostileMobContext;
import ethos.runehub.entity.mob.hostile.HostileMobIdContextLoader;
import ethos.runehub.markup.MarkupUtils;
import ethos.runehub.skill.support.SupportSkill;
import ethos.runehub.skill.support.slayer.master.SlayerMasterDAO;
import ethos.runehub.world.WorldSettingsController;
import org.runehub.api.model.world.region.RegionDataAccessObject;
import org.runehub.api.util.SkillDictionary;
import org.runehub.api.util.math.geometry.Point;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Slayer extends SupportSkill {

    public void checkSlayerTask() {
        if (this.getPlayer().getSlayerSave().getAssignmentId() != -1) {
            final SlayerAssignment assignment = SlayerAssignmentDAO.getInstance().read(this.getPlayer().getSlayerSave().getAssignmentId());
            if (assignment.getRequiredMobIds().length == 1) {
                this.getPlayer().sendMessage("You must slay $" + this.getPlayer().getSlayerSave().getAssignedAmount() + " more " + HostileMobIdContextLoader.getInstance().read(assignment.getRequiredMobIds()[0]).getName());
            } else {
                this.getPlayer().sendMessage("You must slay $" + this.getPlayer().getSlayerSave().getAssignedAmount() + " more " + SlayerAssignmentDAO.getInstance().read(this.getPlayer().getSlayerSave().getAssignmentId()).getCategory());
            }

        } else {
            this.getPlayer().sendMessage("You do not have a task.");
        }
        this.getPlayer().getPA().closeAllWindows();
    }


    public void assignTask(int masterId) {
        if (this.getPlayer().getSlayerSave().getAssignmentId() == -1) {
            final SlayerTask task = this.getSlayerTask(masterId);
            int taskAmount = task.getAssignedAmount().getRandomValue();
            if (Arrays.stream(this.getPlayer().getSlayerSave().getPreferredAssignments()).anyMatch(value -> value == task.getAssignmentId())) {
                taskAmount = (int) (taskAmount * 1.2);
            }
            this.getPlayer().getSlayerSave().setAssignmentId(task.getAssignmentId());
            this.getPlayer().getSlayerSave().setAssignedAmount(taskAmount);
            this.getPlayer().getSlayerSave().setTaskMasterId(masterId);
            this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                    .addNpcChat(masterId, "Kill " + taskAmount + " " + SlayerAssignmentDAO.getInstance().read(task.getAssignmentId()).getCategory() + ".")
                    .build());
            this.updateAchievements(masterId);
        } else {
            this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                    .addNpcChat(masterId, "Please finish, or cancel your current Slayer task first.")
                    .build());
        }
    }

    private void updateAchievements(int master) {
        if (master == 401) {
            this.getPlayer().getAttributes().getAchievementController().completeAchievement(-8981869897408690521L);
        }
    }

    public void cancelCurrentTask(int masterId) {
        if (this.getPlayer().getSlayerSave().getAssignmentId() != -1) {
            if (this.getPlayer().getSlayerSave().getSlayerPoints() >= RunehubConstants.CANCEL_TASK_COST) {
                this.getPlayer().getSlayerSave().setAssignmentId(-1);
                this.getPlayer().getSlayerSave().setAssignedAmount(0);
                this.getPlayer().getSlayerSave().setTotalAssignedAmount(0);
                this.getPlayer().getSlayerSave().setSlayerPoints(this.getPlayer().getSlayerSave().getSlayerPoints() - RunehubConstants.CANCEL_TASK_COST);
                this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                        .addNpcChat(masterId, "I've cancelled your current task.")
                        .build());
            } else {
                this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                        .addNpcChat(masterId, "You need at least " + RunehubConstants.CANCEL_TASK_COST + " Slayer points to do that.")
                        .build());
            }
        } else {
            this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                    .addNpcChat(masterId, "You do not have an active Slayer task.")
                    .build());
        }
    }

    public void blockCurrentTask(int masterId) {
        if (this.getPlayer().getSlayerSave().getAssignmentId() != -1) {
            if (this.getPlayer().getSlayerSave().getSlayerPoints() >= RunehubConstants.BLOCK_TASK_COST) {
                if (Arrays.stream(this.getPlayer().getSlayerSave().getBlockedAssignments()).anyMatch(value -> value == 0L)) {
                    for (int i = 0; i < this.getPlayer().getSlayerSave().getBlockedAssignments().length; i++) {
                        if (this.getPlayer().getSlayerSave().getBlockedAssignments()[i] == 0L) {
                            this.getPlayer().getSlayerSave().getBlockedAssignments()[i] = this.getPlayer().getSlayerSave().getAssignmentId();
                            this.getPlayer().getSlayerSave().setAssignmentId(-1);
                            this.getPlayer().getSlayerSave().setAssignedAmount(0);
                            this.getPlayer().getSlayerSave().setTotalAssignedAmount(0);
                            break;
                        }
                    }
                    this.getPlayer().getSlayerSave().setSlayerPoints(this.getPlayer().getSlayerSave().getSlayerPoints() - RunehubConstants.BLOCK_TASK_COST);
                    this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                            .addNpcChat(masterId, "I've blocked your current task.")
                            .build());
                } else {
                    this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                            .addNpcChat(masterId, "Your block list is full.", "Please unblock something before", "blocking another task.")
                            .build());
                }
            } else {
                this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                        .addNpcChat(masterId, "You need at least " + RunehubConstants.BLOCK_TASK_COST + " Slayer points to do that.")
                        .build());
            }
        } else {
            this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                    .addNpcChat(masterId, "You do not have an active Slayer task.")
                    .build());
        }
    }

    public void preferCurrentTask(int masterId) {
        if (this.getPlayer().getSlayerSave().getAssignmentId() != -1) {
            if (this.getPlayer().getSlayerSave().getSlayerPoints() >= RunehubConstants.PREFER_TASK_COST) {
                if (Arrays.stream(this.getPlayer().getSlayerSave().getPreferredAssignments()).noneMatch(value -> value == this.getPlayer().getSlayerSave().getAssignmentId())) {
                    if (Arrays.stream(this.getPlayer().getSlayerSave().getBlockedAssignments()).anyMatch(value -> value == 0L)) {
                        int newTotalAssigned = (int) ((this.getPlayer().getSlayerSave().getTotalAssignedAmount() * 1.2));
                        int amountToAdd = newTotalAssigned - this.getPlayer().getSlayerSave().getTotalAssignedAmount();
                        this.getPlayer().getSlayerSave().setAssignedAmount(this.getPlayer().getSlayerSave().getAssignedAmount() + amountToAdd);
                        for (int i = 0; i < this.getPlayer().getSlayerSave().getPreferredAssignments().length; i++) {
                            if (this.getPlayer().getSlayerSave().getPreferredAssignments()[i] == 0L) {
                                this.getPlayer().getSlayerSave().getPreferredAssignments()[i] = this.getPlayer().getSlayerSave().getAssignmentId();
                                break;
                            }
                        }
                        this.getPlayer().getSlayerSave().setSlayerPoints(this.getPlayer().getSlayerSave().getSlayerPoints() - RunehubConstants.PREFER_TASK_COST);
                        this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                                .addNpcChat(masterId, "I've preferred your current task.", "You have an increased chance of receiving it and", "it will be automatically extended.")
                                .build());
                    } else {
                        this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                                .addNpcChat(masterId, "Your prefer list is full.", "Please un-prefer something before", "preferring another task.")
                                .build());
                    }
                } else {
                    this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                            .addNpcChat(masterId, "You've already preferred this task.")
                            .build());
                }
            } else {
                this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                        .addNpcChat(masterId, "You need at least " + RunehubConstants.PREFER_TASK_COST + " Slayer points to do that.")
                        .build());
            }
        } else {
            this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                    .addNpcChat(masterId, "You do not have an active Slayer task.")
                    .build());
        }
    }

    public void extendCurrentTask(int masterId) {
        if (this.getPlayer().getSlayerSave().getAssignmentId() != -1) {
            if (this.getPlayer().getSlayerSave().getSlayerPoints() >= RunehubConstants.EXTEND_TASK_COST) {
                int newTotalAssigned = (int) ((this.getPlayer().getSlayerSave().getTotalAssignedAmount() * 1.2));
                int amountToAdd = newTotalAssigned - this.getPlayer().getSlayerSave().getTotalAssignedAmount();
                this.getPlayer().getSlayerSave().setAssignedAmount(this.getPlayer().getSlayerSave().getAssignedAmount() + amountToAdd);
                this.getPlayer().getSlayerSave().setSlayerPoints(this.getPlayer().getSlayerSave().getSlayerPoints() - RunehubConstants.EXTEND_TASK_COST);
                this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                        .addNpcChat(masterId, "I've extended your current task.", "You must now kill " + newTotalAssigned + " "
                                + SlayerAssignmentDAO.getInstance().read(this.getPlayer().getSlayerSave().getAssignmentId()).getCategory() + ".")
                        .build());
            } else {
                this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                        .addNpcChat(masterId, "You need at least " + RunehubConstants.EXTEND_TASK_COST + " Slayer points to do that.")
                        .build());
            }
        } else {
            this.getPlayer().getDH().sendDialogueSequence(new DialogSequence.DialogSequenceBuilder(this.getPlayer())
                    .addNpcChat(masterId, "You do not have an active Slayer task.")
                    .build());
        }
    }

    public void unblockTask(int index) {
        if (this.getPlayer().getItems().playerHasItem(995, RunehubConstants.UNBLOCK_TASK_COST)) {
            this.getPlayer().getItems().deleteItem2(995, RunehubConstants.UNBLOCK_TASK_COST);
            this.getPlayer().getSlayerSave().getBlockedAssignments()[index] = 0L;
        }
    }

    public void unpreferTask(int index) {
        if (this.getPlayer().getItems().playerHasItem(995, RunehubConstants.UNBLOCK_TASK_COST)) {
            this.getPlayer().getItems().deleteItem2(995, RunehubConstants.UNBLOCK_TASK_COST);
            this.getPlayer().getSlayerSave().getPreferredAssignments()[index] = 0L;
        }
    }

    public void onAssignedMobDeath(int mobId, int mobDeathX, int mobDeathY, int mobDeathZ) {
        final SlayerAssignment assignment = SlayerAssignmentDAO.getInstance().read(this.getPlayer().getSlayerSave().getAssignmentId());
        final HostileMobContext mobContext = HostileMobIdContextLoader.getInstance().read(mobId);

        if (assignment != null
                && this.getPlayer().getSlayerSave().getAssignedAmount() > 0
                && mobContext != null
                && mobContext.getCategory().stream().anyMatch(cat -> cat.contains(assignment.getCategory()))
                && (assignment.getRequiredMobIds() != null || Arrays.stream(Objects.requireNonNull(assignment).getRequiredMobIds()).anyMatch(value -> value == mobId))) {
            final SlayerTask task = this.getSlayerTask(this.getPlayer().getSlayerSave().getTaskMasterId(), assignment.getId());
            if (task != null
                    && task.getLocation().length > 0
                    && Arrays.stream(task.getLocation()).noneMatch(value -> RegionDataAccessObject.getInstance().read(value).getBoundingShape().contains(new Point(this.getPlayer().absX, this.getPlayer().absY)))) {
                this.getPlayer().sendMessage("This did not count towards your Slayer task.");
            } else {
                this.getPlayer().getSlayerSave().setAssignedAmount(this.getPlayer().getSlayerSave().getAssignedAmount() - 1);
            }

            this.attemptSuperiorMonsterSpawn(assignment, mobId, mobDeathX, mobDeathY, mobDeathZ);
            if (SuperiorSlayerMonsterCache.getInstance().containsKey(mobId))
                this.getPlayer().getSkillController().addXP(this.getId(), HostileMobIdContextLoader.getInstance().read(mobId).getHitpoints() * 10);
            else
                this.getPlayer().getSkillController().addXP(this.getId(), mobContext.getHitpoints());
            System.out.println("Slayer Task Progress Update: " + this.getPlayer().getSlayerSave().getAssignedAmount() + " kill remaining.");
            if (this.getPlayer().getSlayerSave().getAssignedAmount() == 0) {
                this.completeAssignedTask();
            }
        }
    }

    public SlayerTask getSlayerTask(int npcId) {
        final List<SlayerTask> availableTasks = Arrays.stream(SlayerMasterDAO.getInstance().read(npcId).getTasks())
                .filter(slayerTask -> (SlayerAssignmentDAO.getInstance().read(slayerTask.getAssignmentId()).isMembers() && getPlayer().getAttributes().isMember())
                        || !SlayerAssignmentDAO.getInstance().read(slayerTask.getAssignmentId()).isMembers())
                .filter(slayerTask -> (slayerTask.getUnlockId() == -1 || this.getPlayer().getSlayerSave().getUnlockedKnowledge()[slayerTask.getUnlockId()]))
                .filter(task -> SlayerAssignmentDAO.getInstance().read(task.getAssignmentId()).getSlayerLevel() <= this.getPlayer().getSkillController().getLevel(this.getId()))
                .filter(task -> task.getCombatLevelRequirement() <= this.getPlayer().combatLevel)
                .filter(task -> Arrays.stream(this.getPlayer().getSlayerSave().getBlockedAssignments()).noneMatch(value -> value == task.getAssignmentId()))
                .collect(Collectors.toList());
        float roll = SKILL_RANDOM.nextFloat();
        double sum = availableTasks
                .stream()
                .mapToDouble(SlayerTask::getWeight).sum();
        SlayerTask rolledTask = availableTasks.get(SKILL_RANDOM.nextInt(availableTasks.size()));
        int rolledTaskWeight = rolledTask.getWeight();
        if (Arrays.stream(this.getPlayer().getSlayerSave().getPreferredAssignments()).anyMatch(value -> value == rolledTask.getAssignmentId())) {
            rolledTaskWeight = rolledTaskWeight * 2;
        }
        return (rolledTaskWeight / sum) <= roll ? rolledTask : getSlayerTask(npcId);
    }

    private void attemptSuperiorMonsterSpawn(SlayerAssignment assignment, int mobId, int mobDeathX, int mobDeathY, int mobDeathZ) {
        SuperiorSlayerMonsterCache.getInstance().readAll().stream()
                .filter(superiorSlayerMonster -> superiorSlayerMonster.getSlayerCategory().equalsIgnoreCase(assignment.getCategory()))
                .filter(superiorSlayerMonster -> superiorSlayerMonster.getExclusiveMobIds().length == 0 || Arrays.stream(superiorSlayerMonster.getExclusiveMobIds()).anyMatch(value -> value == mobId)).findAny().ifPresent(superiorSlayerMonster -> {
                    if (!Region.blockedNorth(mobDeathX, mobDeathY, mobDeathZ)
                            && !Region.blockedSouth(mobDeathX, mobDeathY, mobDeathZ)
                            && !Region.blockedWest(mobDeathX, mobDeathY, mobDeathZ)
                            && !Region.blockedEast(mobDeathX, mobDeathY, mobDeathZ)
                            && !Region.blockedNorthEast(mobDeathX, mobDeathY, mobDeathZ)
                            && !Region.blockedNorthWest(mobDeathX, mobDeathY, mobDeathZ)
                            && !Region.blockedSouthEast(mobDeathX, mobDeathY, mobDeathZ)
                            && !Region.blockedSouthWest(mobDeathX, mobDeathY, mobDeathZ)
                    ) {
                        if (SKILL_RANDOM.nextInt(200) == 1 && this.getPlayer().getSlayerSave().getUnlockedKnowledge()[9]) {
                            HostileMobContext context = HostileMobIdContextLoader.getInstance().read(superiorSlayerMonster.getMobId());
                            Server.npcHandler.spawnNpc(this.getPlayer(),
                                    superiorSlayerMonster.getMobId(),
                                    mobDeathX,
                                    mobDeathY,
                                    mobDeathZ,
                                    1,
                                    context.getHitpoints(),
                                    context.getMaxHit(),
                                    200,
                                    200,
                                    true,
                                    false);
                            this.getPlayer().sendMessage(MarkupUtils.highlightText(MarkupUtils.RED, "A superior foe has appeared..."));

                        }
                    }
                });

    }

    private SlayerTask getSlayerTask(int masterId, long assignmentId) {
        return Arrays.stream(SlayerMasterDAO.getInstance().read(masterId).getTasks()).filter(slayerTask -> slayerTask.getAssignmentId() == assignmentId).findFirst()
                .orElse(null);
    }

    private void completeAssignedTask() {
        int basePoints = 0;
        this.incrementTaskStreak();
        if (this.getPlayer().getSlayerSave().getTaskStreak() >= 5) {
            basePoints = SlayerMasterDAO.getInstance().read(this.getPlayer().getSlayerSave().getTaskMasterId()).getPointsPerTask();
            if (this.getPlayer().getSlayerSave().getTaskStreak() % 10 == 0) {
                basePoints = SlayerMasterDAO.getInstance().read(this.getPlayer().getSlayerSave().getTaskMasterId()).getPointsPer10thTask();
            } else if (this.getPlayer().getSlayerSave().getTaskStreak() % 50 == 0) {
                basePoints = SlayerMasterDAO.getInstance().read(this.getPlayer().getSlayerSave().getTaskMasterId()).getPointsPer50thTask();
            } else if (this.getPlayer().getSlayerSave().getTaskStreak() % 100 == 0) {
                basePoints = SlayerMasterDAO.getInstance().read(this.getPlayer().getSlayerSave().getTaskMasterId()).getPointsPer100thTask();
            } else if (this.getPlayer().getSlayerSave().getTaskStreak() % 250 == 0) {
                basePoints = SlayerMasterDAO.getInstance().read(this.getPlayer().getSlayerSave().getTaskMasterId()).getPointsPer250thTask();
            } else if (this.getPlayer().getSlayerSave().getTaskStreak() % 1000 == 0) {
                basePoints = SlayerMasterDAO.getInstance().read(this.getPlayer().getSlayerSave().getTaskMasterId()).getPointsPer1000thTask();
            }

            if (WorldSettingsController.getInstance().getWorldSettings().getWeekendEventId() == 3) {
                basePoints *= 2;
            }

            this.getPlayer().sendMessage("You've completed $" + this.getPlayer().getSlayerSave().getTaskStreak() + " tasks in a row and earned $" + basePoints + " Slayer points!");
        } else {
            this.getPlayer().sendMessage("You've completed $" + this.getPlayer().getSlayerSave().getTaskStreak() + " tasks in a row. You must complete $" + (5 - this.getPlayer().getSlayerSave().getTaskStreak()) + " more to begin earning points.");
        }

        this.addSlayerPoints(basePoints);
        this.incrementTaskTotal();
        this.getPlayer().getSlayerSave().setAssignmentId(-1);
    }

    private void incrementTaskStreak() {
        this.getPlayer().getSlayerSave().setTaskStreak(this.getPlayer().getSlayerSave().getTaskStreak() + 1);
    }

    private void incrementTaskTotal() {
        this.getPlayer().getSlayerSave().setTasksCompleted(this.getPlayer().getSlayerSave().getTasksCompleted() + 1);
    }

    private void addSlayerPoints(int amount) {
        this.getPlayer().getSlayerSave().setSlayerPoints(this.getPlayer().getSlayerSave().getSlayerPoints() + amount);
    }

    public Slayer(Player player) {
        super(player);
    }

    @Override
    public int getId() {
        return SkillDictionary.Skill.SLAYER.getId();
    }
}
