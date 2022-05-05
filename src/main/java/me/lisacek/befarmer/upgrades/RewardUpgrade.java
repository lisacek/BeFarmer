package me.lisacek.befarmer.upgrades;

import me.lisacek.befarmer.cons.LevelReward;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RewardUpgrade {

    private final Map<Integer, LevelReward> levelRewards = new HashMap<>();
    private final Map<Integer, List<String>> levelCommands = new HashMap<>();

    public RewardUpgrade() {
    }

    public Map<Integer, List<String>> getLevelRewards() {
        return levelCommands;
    }

    public Map<Integer, LevelReward> getLevelCommands() {
        return levelRewards;
    }

}
