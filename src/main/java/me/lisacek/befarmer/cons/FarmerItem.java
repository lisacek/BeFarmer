package me.lisacek.befarmer.cons;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FarmerItem {

    private final String name;

    private final ItemStack item;
    private final List<String> rewards = new ArrayList<>();

    public FarmerItem(String name, ItemStack item) {
        this.name = name;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItem() {
        return item;
    }

    public List<String> getRewards() {
        return rewards;
    }

}
