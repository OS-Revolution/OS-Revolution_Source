package ethos.runehub.content.job;

import ethos.runehub.entity.item.ArtisanSkillItemReactionDAO;
import ethos.runehub.entity.item.ItemInteractionDAO;
import ethos.runehub.skill.Skill;
import ethos.runehub.skill.artisan.ArtisanSkillItemReaction;
import ethos.runehub.skill.artisan.smithing.Smeltable;
import ethos.runehub.skill.artisan.smithing.SmithingItem;
import ethos.runehub.skill.artisan.smithing.SmithingItemDAO;
import ethos.runehub.skill.node.impl.Node;
import ethos.runehub.skill.node.impl.gatherable.impl.WoodcuttingNode;
import ethos.runehub.skill.node.io.WoodcuttingNodeDAO;
import ethos.util.Misc;
import org.runehub.api.io.load.impl.ItemIdContextLoader;
import org.runehub.api.io.load.impl.LootTableLoader;
import org.runehub.api.model.collection.WeightedCollection;
import org.runehub.api.model.entity.item.loot.Loot;
import org.runehub.api.model.entity.item.loot.LootTable;
import org.runehub.api.model.entity.item.loot.LootTableEntry;
import org.runehub.api.model.math.impl.IntegerRange;
import org.runehub.api.util.SkillDictionary;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JobUtils {

    public  static String getScorePercent(float score) {
        double val = score * 100.D;
        DecimalFormat df = new DecimalFormat("###.##");

        return df.format(val) + "%";
    }

    public static String getJobScoreString(float score) {
        if (score < 0.20) {
            return "bad";
        } else if (score >= 0.20 && score < 0.4) {
            return "low";
        } else if (score >= 0.40 && score < 0.6) {
            return "fair";
        } else if (score >= 0.60 && score < 0.8) {
            return "good";
        } else if (score >= 0.8 && score < 1) {
            return "high";
        } else {
            return "perfect";
        }
    }

    public static int getDifficulty(int level, float score) {
        // set base difficulty as level mod 5 (0-4)
        int difficulty = level % 5;

        // adjust difficulty based on score
        for (int i = 0; i < (score * 100); i += 1) {
            double rand = Math.random();
            if (rand < 0.0075 && difficulty < 4) {
                difficulty += 1;
            }
        }

        return difficulty;
    }

    public static int getBonusPay(Job job) {
        return (int) ((ItemIdContextLoader.getInstance().read(job.getTargetId()).getValue()
                        * 0.65) * job.getQuota());
    }

//    public static int calculateScore(int difficulty, int quota) {
//        if (quota < 50 || quota > 2000) {
//            throw new IllegalArgumentException("Quota must be between 50 and 2000");
//        }
//
//        double ratio = (double) (quota - 50) / 1950;
//        double powRatio = Math.pow(ratio, 1.0 / (difficulty + 1));
//        double score = powRatio * 20;
//        score = Math.pow(score, 1.0 / (difficulty + 1));
//        return (int)Math.round(score);
//    }

    public static int getXpReward(Job job) {
        int quota = job.getQuota();
        int difficulty = job.getDifficulty();
        if (job.getQuota() < 50 || job.getQuota() > 2000) {
            return ((20 + difficulty)* job.getQuota()) * 2;
        }

        double percentOfMaxQuota = (quota / 2000.0D )* 100.0D;
        int val = (int)(20*(percentOfMaxQuota/100.0f));

        return ((val + difficulty) * job.getQuota()) * 2;
    }

    public static float getJobScoreBonus(int difficulty, int skillId, int streak) {
        float baseValue = Math.max(0.02f * difficulty, 0.02f);
        float bonus = (float) (Math.floor(streak / 5.0) * 0.01f);
        float value = baseValue + bonus;
        switch (skillId) {
            default:
                value = baseValue + bonus;
        }
        return Math.min(1.0f,value);
    }

    public static Job generateWoodcuttingJob(int level, int skillId, float score) {
        List<WoodcuttingNode> nodes = WoodcuttingNodeDAO.getInstance().getAllEntries();
        List<WoodcuttingNode> potentialNodes = nodes.stream()
                .filter(node -> node.getLevelRequirement() <= level)
                .sorted(Comparator.comparingInt(WoodcuttingNode::getLevelRequirement))
                .collect(Collectors.toList());
        WeightedCollection<WoodcuttingNode> weightedCollection = new WeightedCollection<>();

        for (int i = 0; i < potentialNodes.size(); i++) {
            double weight = 100 - potentialNodes.get(i).getLevelRequirement();
            weightedCollection.add(weight, potentialNodes.get(i));
        }

        WoodcuttingNode selectedNode = weightedCollection.next(score);

        int logId = LootTableLoader.getInstance().read(selectedNode.getGatherableItemTableId()).getLootTableEntries().get(0).getId();
        int difficulty = getDifficulty(level,score);
        int quota = 50 + ((15 * difficulty) * (5 * difficulty)) + Misc.random(200);
        int basePay = 5000 + (difficulty * 2500) * difficulty;
       Job job = new Job(logId, quota, 0, difficulty, basePay, 8);
        return job;
    }

    public static Job generateCookingJob(int level, int skillId, float score) {
        List<ArtisanSkillItemReaction> nodes = ItemInteractionDAO.getInstance().getAllEntries()
                .stream()
                .filter(interaction -> interaction.getReactionKey() == 7)
                .map(interaction -> ArtisanSkillItemReactionDAO.getInstance().read(interaction.getReactionUuid()))
                .collect(Collectors.toList());
        List<ArtisanSkillItemReaction> potentialNodes = nodes.stream()
                .filter(node -> node.getLevelRequired() <= level)
                .sorted(Comparator.comparingInt(ArtisanSkillItemReaction::getLevelRequired))
                .collect(Collectors.toList());
        WeightedCollection<ArtisanSkillItemReaction> weightedCollection = new WeightedCollection<>();

        for (int i = 0; i < potentialNodes.size(); i++) {
            double weight = 100 - potentialNodes.get(i).getLevelRequired();
            weightedCollection.add(weight, potentialNodes.get(i));
        }

        ArtisanSkillItemReaction selectedNode = weightedCollection.next(score);

        int targetId = selectedNode.getProductItemId();
        int difficulty = getDifficulty(level,score);
        int quota = 50 + ((15 * difficulty) * (5 * difficulty)) + Misc.random(200);
        int basePay = 5000 + (difficulty * 2500) * difficulty;
        Job job = new Job(targetId, quota, 0, difficulty, basePay, 7);

        return job;
    }

    public static Job generateSmithingJob(int level, float score) {
        List<Smeltable> smeltableList = Arrays.stream(Smeltable.values())
                .filter(smeltable -> smeltable.getLevelRequired() <= level)
                .sorted(Comparator.comparingInt(Smeltable::getLevelRequired))
                .collect(Collectors.toList());
        List<SmithingItem> smithingItems =SmithingItemDAO.getInstance().getAllEntries().stream()
                .filter(item -> item.getLevelRequired() <= level)
                .sorted(Comparator.comparingInt(SmithingItem::getLevelRequired))
                .collect(Collectors.toList());
        int roll = Skill.SKILL_RANDOM.nextInt(2);
        int targetId = 0;//selectedNode.getProductItemId();
        int difficulty = getDifficulty(level,score);
        int quota = 50 + ((15 * difficulty) * (5 * difficulty)) + Misc.random(200);
        int basePay = 5000 + (difficulty * 2500) * difficulty;

        if (roll == 1) {
            WeightedCollection<Smeltable> weightedCollection = new WeightedCollection<>();

            for (int i = 0; i < smeltableList.size(); i++) {
                double weight = 100 - smeltableList.get(i).getLevelRequired();
                weightedCollection.add(weight, smeltableList.get(i));
            }

            Smeltable selectedNode = weightedCollection.next(score);
            targetId = selectedNode.getProductId();
        } else {
            WeightedCollection<SmithingItem> weightedCollection = new WeightedCollection<>();

            for (int i = 0; i < smithingItems.size(); i++) {
                double weight = 100 - smithingItems.get(i).getLevelRequired();
                weightedCollection.add(weight, smithingItems.get(i));
            }

            SmithingItem selectedNode = weightedCollection.next(score);
            targetId = selectedNode.getItemId();
        }

        Job job = new Job(targetId, quota, 0, difficulty, basePay, SkillDictionary.Skill.SMITHING.getId());

        return job;
    }


    public static int selectNode(int size, double score) {
        int selectedIndex = Skill.SKILL_RANDOM.nextInt(size);
        int maxRolls = (int) Math.floor((1 - score) / 0.0075);

        for (int i = 0; i < maxRolls; i++) {
            if (Skill.SKILL_RANDOM.nextDouble() < 0.0075) {
                selectedIndex++;
                if (selectedIndex >= size) {
                    selectedIndex = size - 1;
                    break;
                }
            }
        }

        return selectedIndex;
    }
}
