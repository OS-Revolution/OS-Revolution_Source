package ethos.model.players;

public enum ClientGameTimer {
	VENGEANCE(0), OVERLOAD(1), ANTIFIRE(2), ANTIVENOM(3), ANTIPOISON(4), TELEBLOCK(5), STAMINA(6), FARMING(7), EXPERIENCE(8), PEST_CONTROL(9), DROPS(10),
	WOODCUTTING_EFFICIENCY(14),WOODCUTTING_POWER(12),WOODCUTTING_GAINS(13),
	MINING_EFFICIENCY(15),MINING_POWER(16),MINING_GAINS(17),
	FISHING_EFFICIENCY(18),FISHING_POWER(20),FISHING_GAINS(19),
	SKILL_EFFICIENCY(21),SKILL_POWER(21),SKILL_GAINS(21);

	private final int timerId;

	private ClientGameTimer(int timerId) {
		this.timerId = timerId;
	}

	public int getTimerId() {
		return timerId;
	}

	public static ClientGameTimer getEfficiencyTimerForSkillId(int skillId) {
		switch (skillId) {
			case 8:
				return WOODCUTTING_EFFICIENCY;
			case 10:
				return FISHING_EFFICIENCY;
			case 14:
				return MINING_EFFICIENCY;
			default: return SKILL_EFFICIENCY;
		}
	}

	public static ClientGameTimer getPowerTimerForSkillId(int skillId) {
		switch (skillId) {
			case 8:
				return WOODCUTTING_POWER;
			case 10:
				return FISHING_POWER;
			case 14:
				return MINING_POWER;
			default: return SKILL_POWER;
		}
	}

	public static ClientGameTimer getGainsTimerForSkillId(int skillId) {
		switch (skillId) {
			case 8:
				return WOODCUTTING_GAINS;
			case 10:
				return FISHING_GAINS;
			case 14:
				return MINING_GAINS;
			default: return SKILL_EFFICIENCY;
		}
	}
}
