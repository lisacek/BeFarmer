package me.lisacek.befarmer.cons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LevelReward {

    private final Material material;

    private final int required;

    private final List<String> commands = new ArrayList<>();

    public LevelReward(Material material, int required) {
        this.material = material;
        this.required = required;
    }

    public Material getMaterial() {
        return material;
    }

    public int getRequired() {
        return required;
    }

    public List<String> getCommands() {
        return commands;
    }

}
