package ethos.phantasye.job;

import ethos.model.players.Player;
import ethos.model.players.skills.Skill;
import ethos.phantasye.job.pay.Payment;
import ethos.phantasye.job.pay.PaymentFactory;
import ethos.phantasye.job.pay.impl.CoinPaymentFactory;
import ethos.phantasye.job.pay.impl.RandomPaymentModifier;
import org.rhd.api.io.loader.LootContainerLoader;
import org.rhd.api.model.LootContainerType;
import org.rhd.api.model.LootTableContainer;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Employer {

    private final Player employee;

    public Employer(Player employee) {
        this.employee = employee;
    }


    /**
     * Creates the job
     */
    public void assignJob() {
        try {
            final int skillId = this.selectSkill();
            final Job.Difficulty difficulty = this.assignDifficulty(skillId);
            final int targetId = this.assignTarget(difficulty, skillId);
            final int quota = this.assignQuota(difficulty);
            final Job job = new Job.JobBuilder()
                    .forSkill(skillId)
                    .withDifficulty(difficulty)
                    .withTargetId(targetId)
                    .withQuota(quota)
                    .build();

            employee.assignJob(job);
        } catch (IllegalArgumentException e) {
            this.assignJob();
        }

    }

    public void forceJob() {
        final int skillId = 8;
        final Job.Difficulty difficulty = Job.Difficulty.EASY;
        final int targetId = 1513;
        final int quota = this.assignQuota(difficulty);
        final Job job = new Job.JobBuilder()
                .forSkill(skillId)
                .withDifficulty(difficulty)
                .withTargetId(targetId)
                .withQuota(quota)
                .build();

        employee.assignJob(job);
    }

    /**
     * Creates a list of jobs sorted by skill and difficulty and selects a random one
     *
     * @param difficulty the difficulty level to sort by
     * @param skill      the skill to sort by
     * @return target's itemID
     */
    private int assignTarget(Job.Difficulty difficulty, int skill) throws IllegalArgumentException {
            final List<JobTarget> availableJobs = Job.JOB_REPOSITORY.getRepository().readAll().stream()
                    .filter(jobTarget -> jobTarget.getDifficulty() == difficulty)
                    .filter(jobTarget -> jobTarget.getSkillId() == skill)
                    .collect(Collectors.toList());
            if(availableJobs.isEmpty() && difficulty.ordinal() > 0) {
                return assignTarget(Job.Difficulty.previous(difficulty),skill);
            }
            final int index = new Random().nextInt(availableJobs.size());
            return availableJobs.get(index).getItemId();
    }

    /**
     * Assigns a job quota scaled with the difficulty level
     *
     * @param difficulty the difficulty to scale to
     * @return baseValue + random modifier
     */
    private int assignQuota(Job.Difficulty difficulty) {
        final Random random = new Random();
        return difficulty.getQuotaMin() + random.nextInt(difficulty.getQuotaMin());
    }

    /**
     * Returns a difficulty level based on the employee's level in the selected skill
     *
     * @param skill the ID of the skill selected
     * @return difficulty
     */
    private Job.Difficulty assignDifficulty(int skill) {
        final int employeeLevel = employee.playerLevel[skill];
        if (within(employeeLevel, 1, 20)) {
            return Job.Difficulty.EASY;
        } else if (within(employeeLevel, 21, 50)) {
            return Job.Difficulty.MEDIUM;
        } else if (within(employeeLevel, 51, 80)) {
            return Job.Difficulty.HARD;
        } else
            return Job.Difficulty.LEGENDARY;
    }

    /**
     * Returns a random int from the available skill ID's
     *
     * @return skillId
     */
    private int selectSkill() {
        final List<Integer> skillExceptions = new ArrayList<>();

        skillExceptions.add(Skill.ATTACK.ordinal());
        skillExceptions.add(Skill.STRENGTH.ordinal());
        skillExceptions.add(Skill.DEFENCE.ordinal());
        skillExceptions.add(Skill.RANGED.ordinal());
        skillExceptions.add(Skill.MAGIC.ordinal());
        skillExceptions.add(Skill.PRAYER.ordinal());
        skillExceptions.add(Skill.FIREMAKING.ordinal());
        skillExceptions.add(Skill.HITPOINTS.ordinal());
        skillExceptions.add(Skill.AGILITY.ordinal());
        skillExceptions.add(Skill.FIREMAKING.ordinal());
        skillExceptions.add(Skill.SLAYER.ordinal());

        final Random random = new Random();
        final int skillId = random.nextInt(Skill.values().length);
        return skillExceptions.contains(skillId) ? selectSkill() : skillId;
    }

    private int xpForJob(Job job) {
        int baseXp = job.getDifficulty().getXpMin();
        int modifier = job.getDifficulty().getXpMin();

        for (int i = 0; i < job.getQuota().value(); i++) {
            if (i % 100 == 0) {
                baseXp += 1000;
            }
        }

        return baseXp + new Random().nextInt(modifier);
    }

    public void pay(Job job) {
        final Payment cashPayment = makeCoinPayment(job);
        final int xp = xpForJob(job);
        final int amount = cashPayment.make();

//        employee.transact(new CurrencyDeposit(employee, amount)); TODO Fix this
        employee.getPA().addSkillXP(xp,job.getSkillId(),true);
        //TODO roll job
        final LootTableContainer container = LootContainerLoader.getInstance().getLootContainer(job.getSkillId(), LootContainerType.MISC);
        if (container != null) {
            employee.getContext().getJobScore().increment();
            container.roll(job.getDifficulty().getMagicFindBonus()).roll() //TODO add magic find
                    .forEach(loot -> {
                        employee.getItems().addItem(loot.getItemId(),loot.getAmount());
                    });
        }
        employee.sendMessage("You've received " + NumberFormat.getInstance().format(amount) + " Coins and " +
                NumberFormat.getInstance().format(xp) + " XP!");
    }

    private Payment makeCoinPayment(Job job) {
        switch (job.getDifficulty()) {
            case EASY:
                return new RandomPaymentModifier(PaymentFactory.getPayment(new CoinPaymentFactory(job.getDifficulty().getBasePay() + employeeBaseWage())), 10000 + employeeBonusWage());
            case MEDIUM:
                return new RandomPaymentModifier(PaymentFactory.getPayment(new CoinPaymentFactory(job.getDifficulty().getBasePay() + employeeBaseWage())), 50000 + employeeBonusWage());
            case HARD:
                return new RandomPaymentModifier(PaymentFactory.getPayment(new CoinPaymentFactory(job.getDifficulty().getBasePay() + employeeBaseWage())), 100000 + employeeBonusWage());
            case LEGENDARY:
                return new RandomPaymentModifier(PaymentFactory.getPayment(new CoinPaymentFactory(job.getDifficulty().getBasePay() + employeeBaseWage())), 500000 + employeeBonusWage());
            default:
                return null;
        }

    }

    private int employeeBaseWage() {
        return dividesInto(employee.getContext().getJobScore().value(),Employee.POINT_VALUE) * Employee.BASE_PAY;
    }

    private int employeeBonusWage() {
        return dividesInto(employee.getContext().getJobScore().value(),Employee.POINT_VALUE) * Employee.BONUS_PAY;
    }

    /**
     * TODO
     * make this a utility method
     */
    private static boolean within(int value, int min, int max) {
        return (value >= min && value <= max);
    }

    private static int dividesInto(int a, int b) {
        int iterations = 0;
        for (int i = 0; i < a; i++) {
            if (i % b == 0) {
                iterations++;
            }
        }
        return iterations;
    }

}
