package ethos.runehub.loot;

import ethos.model.players.Player;
import ethos.runehub.TimeUtils;
import ethos.runehub.entity.player.PlayerSaveDataSerializer;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.util.IDManager;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

public class RewardCodeController {

    private static RewardCodeController instance = null;

    public static RewardCodeController getInstance() {
        if (instance == null)
            instance = new RewardCodeController();
        return instance;
    }

    public void createCode(Player player,RewardCode newCode) {
        Optional<RewardCode> validCode = RewardCodeCache.getInstance().readAll().stream().filter(rewardCode -> rewardCode.getUserCode().equalsIgnoreCase(newCode.getUserCode())).findAny();

        if (validCode.isPresent()) {
            if (this.isExpired(validCode.get())) {
                this.addCode(newCode);
                player.sendMessage("Reward code: " + newCode.getUserCode() + " created!");
            } else {
                player.sendMessage("A code with this name already exists and is active.");
            }
        } else {
            this.addCode(newCode);
            player.sendMessage("Reward code: " + newCode.getUserCode() + " created!");
        }
    }

    public String generateCode() {
        UUID uuid = UUID.randomUUID();
        String s = Long.toString(uuid.getMostSignificantBits(), 16).substring(4);
        Optional<RewardCode> validCode = RewardCodeCache.getInstance().readAll().stream().filter(rewardCode -> rewardCode.getUserCode().equalsIgnoreCase(s)).findAny();


        if (validCode.isPresent()) {
            if (this.isExpired(validCode.get())) {
                this.addCode(new RewardCode(IDManager.getUUID(),s, TimeUtils.getDaysAsMS(7),System.currentTimeMillis()));
                Logger.getGlobal().info("Generated Code: " + s);
            } else {
                Logger.getGlobal().warning("Failed to generate reward code: " + s + "\nReason: Code exists and is active.");
                return generateCode();
            }
        } else {
            this.addCode(new RewardCode(IDManager.getUUID(),s, TimeUtils.getDaysAsMS(7),System.currentTimeMillis()));
            Logger.getGlobal().info("Generated Code: " + s);
        }
        return s;
    }

    public Optional<RewardCode> getRewardCode(String userCode) {
        Optional<RewardCode> validCode = RewardCodeCache.getInstance().readAll().stream().filter(rewardCode -> rewardCode.getUserCode().equalsIgnoreCase(userCode)).findAny();
        if (validCode.isEmpty()) {
            validCode = RewardCodeDAO.getInstance().getAllEntries().stream().filter(rewardCode -> rewardCode.getUserCode().equalsIgnoreCase(userCode)).findAny();
        }
        return validCode;
    }

    public void redeem(Player player, String enteredCode) {
        Optional<RewardCode> code = this.getRewardCode(enteredCode);
        if (player.getContext().getPlayerSaveData().getClaimedRewardCodes() == null) {
            player.getContext().getPlayerSaveData().setClaimedRewardCodes(new ArrayList<>());
        }
        if (code.isPresent()) {
            if(!player.getContext().getPlayerSaveData().getClaimedRewardCodes().contains(code.get().getUuid())) {
                if (!this.isExpired(code.get())) {
                    player.getContext().getPlayerSaveData().getClaimedRewardCodes().add(code.get().getUuid());
                    player.getItems().addItem(code.get().getRewardItemId(), code.get().getRewardItemAmount());
                    player.sendMessage("Code Redeemed for #" + code.get().getRewardItemAmount() + " @" + code.get().getRewardItemId() + " !");
                } else {
                    player.sendMessage("Code is expired.");
                }
            } else {
                player.sendMessage("You've already redeemed this code.");
            }
        } else {
            player.sendMessage("Code does not exist.");
        }

    }

    public void addCode(RewardCode rewardCode) {
        RewardCodeDAO.getInstance().create(rewardCode);
    }

    public void requestCodeEntryFromPlayer(Player player) {
        player.getPA().closeAllWindows();
        player.getOutStream().createFrame(28);
    }

    private LootTable getCurrentLootRotation() {
        return null;
    }

    private boolean isExpired(RewardCode code) {
        return System.currentTimeMillis() > (code.getCreationTimestamp() + code.getDurationMs());
    }

    private boolean validateCode(Optional<RewardCode> rewardCode) {
        return rewardCode.isPresent() && !isExpired(rewardCode.get());
    }

    private RewardCodeController() {
        this.addCode(
                new RewardCode(
                        IDManager.getUUID(),
                        "assless-chaps",
                        30000,
                        System.currentTimeMillis()
                )
        );
    }
}
