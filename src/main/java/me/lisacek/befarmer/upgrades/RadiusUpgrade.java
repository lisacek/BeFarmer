package me.lisacek.befarmer.upgrades;

import me.lisacek.befarmer.cons.Recipe;

import java.util.HashMap;
import java.util.Map;

public class RadiusUpgrade {

    private final Map<Integer, Integer> radiusLevels = new HashMap<>();

    private final Recipe recipe;


    public RadiusUpgrade(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Map<Integer, Integer> getRadiusLevels() {
        return radiusLevels;
    }

}
