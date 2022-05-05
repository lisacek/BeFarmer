package me.lisacek.befarmer.upgrades;

import me.lisacek.befarmer.cons.Recipe;

public class FortuneUpgrade {

  private final int maxLevel;
  private final Recipe recipe;

    public FortuneUpgrade(int maxLevel, Recipe recipe) {
        this.maxLevel = maxLevel;
        this.recipe = recipe;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public Recipe getRecipe() {
        return recipe;
    }

}
