package me.lisacek.befarmer.cons;

import org.bukkit.Material;

public class Crop {

    private final String name;

    private final Material material;

    public Crop(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public Material getMaterial() {
        return material;
    }

}
