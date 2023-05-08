package ethos.runehub.content.journey;

import ethos.model.players.Player;
import ethos.model.players.PlayerHandler;
import ethos.runehub.RunehubUtils;
import ethos.runehub.content.PointController;
import ethos.runehub.content.achievement.AchievementCache;
import ethos.runehub.db.PlayerCharacterContextDataAccessObject;
import ethos.runehub.world.RegionCache;
import ethos.runehub.world.WorldSettingsController;
import org.runehub.api.util.math.geometry.Point;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class JourneyController {

    public void unlockPath(JourneyPath path) {
        if (!player.getContext().getPlayerSaveData().getUnlockedPath().contains(path.getId())) {
            if (player.getAttributes().getPointController().subtractPoints(PointController.PointType.JOURNEY,1)) {
                player.getContext().getPlayerSaveData().getUnlockedPath().add(path.getId());
                player.sendMessage("You unlock the " + path.getName() + " journey path!");
            } else {
                player.sendMessage("You do not have enough journey points to unlock this.");
            }
        } else {
            player.sendMessage("You've already unlocked this path.");
        }
    }

    public void startJourney(Journey journey) {
        if (isJourneyOnAvailablePath(journey)) {
            if (player.getContext().getPlayerSaveData().getCompetedJourney().contains(player.getContext().getPlayerSaveData().getJourneyId())) {
                if (hasJourneyPrerequisites(journey)) {
                    player.getContext().getPlayerSaveData().setJourneyId(journey.getId());
                    player.getContext().getPlayerSaveData().setJourneyStep(0);
                    player.getContext().getPlayerSaveData().setCurrentJourneyStepProgress(0);
                    player.sendMessage("^Journey You've started the " + journey.getName() + " journey.");
                } else {
                    player.sendMessage("You do not have the pre-requisites to start this.");
                }
            } else {
                player.sendMessage("You must complete your current journey before starting a new one.");
            }
        } else {
            player.sendMessage("You do not have this path unlocked.");
        }
    }

    public void checkJourney(long id, int amount) {
        if (player.getContext().getPlayerSaveData().getJourneyId() > 0) {
            final Journey journey = JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId());
            if (journey.getSteps().length > player.getContext().getPlayerSaveData().getJourneyStep()) {
                final JourneyStep step = JourneyStepCache.getInstance().read(journey.getSteps()[player.getContext().getPlayerSaveData().getJourneyStep()]);
                if (validateJourneyStep(step, id)) {
                    Logger.getGlobal().severe("Passed Step Check");
                    if (validateRegion(step) && validateChunk(step)) {
                        Logger.getGlobal().severe("Passed Location Check");
                        if (validateProgress(step)) {
                            Logger.getGlobal().severe("Passed Progress Check");
                            if (isProgressThreshold(step, amount)) {
                                Logger.getGlobal().severe("Passed Amount Check");
                                completeStep(step);
                            }
                        }
                    }
                }
            }
        }
    }

    public void checkJourney(int id, int amount, JourneyStepType type) {
        if (player.getContext().getPlayerSaveData().getJourneyId() > 0) {
            Logger.getGlobal().severe("Passed on Journey Check");
            final Journey journey = JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId());
            if (journey.getSteps().length > player.getContext().getPlayerSaveData().getJourneyStep()) {
                Logger.getGlobal().severe("Passed Step Check");
                final JourneyStep step = JourneyStepCache.getInstance().read(journey.getSteps()[player.getContext().getPlayerSaveData().getJourneyStep()]);
                if (isSameTarget(id,step)) {
                    Logger.getGlobal().severe("Passed target Check");
                    if (isSameStepType(step,type)) {
                        Logger.getGlobal().severe("Passed Type Check");
                        if (validateRegion(step) && validateChunk(step)) {
                            Logger.getGlobal().severe("Passed Location Check");
                            if (validateProgress(step)) {
                                Logger.getGlobal().severe("Passed Progress Check");
                                if (isProgressThreshold(step, amount)) {
                                    Logger.getGlobal().severe("Passed Amount Check");
                                    completeStep(step);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public double getStepProgressPercent(JourneyStep step) {
        if (Arrays.stream(JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId()).getSteps()).anyMatch(aLong -> step.getId() == aLong)) {
            final double progressOnCurrentStep = ((double) player.getContext().getPlayerSaveData().getCurrentJourneyStepProgress() / (double) step.getRequiredProgress() * 100.0D);
            return progressOnCurrentStep;
        }
        return 0;
    }

    public boolean isStepComplete(JourneyStep step) {
        final int progressOnCurrentStep = (int) Math.floor((double) player.getContext().getPlayerSaveData().getCurrentJourneyStepProgress() / (double)step.getRequiredProgress());
        System.out.println("Val: " + ((double) player.getContext().getPlayerSaveData().getCurrentJourneyStepProgress() / (double)step.getRequiredProgress()));
        return progressOnCurrentStep == 1;
    }

    public boolean isCurrentStepComplete() {
        final Journey journey = JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId());
        final JourneyStep currentStep = JourneyStepCache.getInstance().read(journey.getSteps()[player.getContext().getPlayerSaveData().getJourneyStep()]);
        final int progressOnCurrentStep = (int) Math.floor((double) player.getContext().getPlayerSaveData().getCurrentJourneyStepProgress() / (double)currentStep.getRequiredProgress());
        return progressOnCurrentStep == 1;
    }

    public JourneyStep getCurrentStep() {
        final Journey journey = JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId());
        final JourneyStep currentStep = JourneyStepCache.getInstance().read(journey.getSteps()[player.getContext().getPlayerSaveData().getJourneyStep()]);
        return currentStep;
    }

    public int getJourneyTotalCompletion() {
        final Journey journey = JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId());
        final JourneyStep currentStep = JourneyStepCache.getInstance().read(journey.getSteps()[player.getContext().getPlayerSaveData().getJourneyStep()]);
        final int progressOnCurrentStep = (int) Math.floor((double) player.getContext().getPlayerSaveData().getCurrentJourneyStepProgress() / (double)currentStep.getRequiredProgress());
        return (int) (((double)(player.getContext().getPlayerSaveData().getJourneyStep() + progressOnCurrentStep) / (double) journey.getSteps().length) * 100.0D);
    }

    private boolean hasJourneyPrerequisites(Journey journey) {
        return player.getContext().getPlayerSaveData().getCompetedJourney().containsAll(List.of(journey.getRequiredJourney()));
    }

    private boolean isJourneyOnAvailablePath(Journey journey) {
        return player.getContext().getPlayerSaveData().getUnlockedPath().stream()
                .anyMatch(pathId -> Arrays.stream(JourneyPathCache.getInstance().read(pathId).getJourney()).anyMatch(value -> value == journey.getId()));
    }

    private boolean isSameTarget(int id, JourneyStep step) {
        return Arrays.stream(step.getTargetId()).anyMatch(value -> value == id);
    }

    private boolean isSameStepType(JourneyStep step,JourneyStepType type) {
        return step.getStepType() == type.ordinal();
    }

    private void completeJourney(Journey journey) {
        player.sendMessage("^Journey You've completed the " + journey.getName() + " journey!");
        player.getContext().getPlayerSaveData().getCompetedJourney().add(journey.getId());
    }

    private boolean isJourneyComplete(JourneyStep step, Journey journey) {
        return player.getContext().getPlayerSaveData().getCurrentJourneyStepProgress() == step.getRequiredProgress()
                && player.getContext().getPlayerSaveData().getJourneyStep() == journey.getSteps().length - 1;
    }

    private void completeStep(JourneyStep step) {
        final int currentProgress = player.getContext().getPlayerSaveData().getCurrentJourneyStepProgress();
        player.getContext().getPlayerSaveData().setCurrentJourneyStepProgress(currentProgress + step.getProgressIncrement());
        System.out.println("Progress updated from: " + currentProgress + " to " + (currentProgress + step.getProgressIncrement()));
        if (player.getContext().getPlayerSaveData().getCurrentJourneyStepProgress() >= step.getRequiredProgress()) {
            player.sendMessage("^Journey @mag@You completed a Journey step. Open the Journey log to claim your reward.");
            if (isJourneyComplete(step,JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId()))) {
                completeJourney(JourneyCache.getInstance().read(player.getContext().getPlayerSaveData().getJourneyId()));
            } else {
                player.getContext().getPlayerSaveData().setJourneyStep(player.getContext().getPlayerSaveData().getJourneyStep() + 1);
                player.getContext().getPlayerSaveData().setCurrentJourneyStepProgress(0);
            }
        }
    }

    private boolean isProgressThreshold(JourneyStep step, int amount) {
        return step.getProgressIncrement() <= amount;
    }

    private boolean validateJourneyStep(JourneyStep step, long id) {
        return step.getId() == id;
    }

    private boolean validateRegion(JourneyStep step) {
        return (step.getRegionId() == 0 || RegionCache.getInstance().read(step.getRegionId()).getBoundingShape().contains(new Point(player.getX(), player.getY())));
    }

    private boolean validateChunk(JourneyStep step) {
        return (step.getValidChunks().length == 0 || Arrays.stream(step.getValidChunks()).anyMatch(value -> value == RunehubUtils.getRegionId(player.getX(), player.getY())));
    }

    private boolean validateProgress(JourneyStep step) {
        return player.getContext().getPlayerSaveData().getCurrentJourneyStepProgress() < step.getRequiredProgress();
    }

    public JourneyController(Player player) {
        this.player = player;
    }

    private final Player player;
}
