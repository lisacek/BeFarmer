package me.lisacek.befarmer.cons;

import me.lisacek.befarmer.BeFarmer;

import java.util.HashMap;
import java.util.Map;

public class User {

    private final Map<String, Integer> statistics = new HashMap<>();

    private void preLoadStats() {
        BeFarmer.getInstance().getAvailableCrops().forEach(crop -> {
            statistics.put(crop.getName().toUpperCase(), 0);
        });
        statistics.put("UPGRADES", 0);
        statistics.put("REPLANTS", 0);
    }

    public void addStatistic(String statistic, int value) {
        statistics.put(statistic, value);
    }

    public int getStatistic(String statistic) {
        return statistics.getOrDefault(statistic, 0);
    }

    public void setStatistic(String statistic, int value) {
        statistics.put(statistic, value);
    }

    public void addStatistic(String statistic) {
        statistics.put(statistic, getStatistic(statistic) + 1);
    }

}
