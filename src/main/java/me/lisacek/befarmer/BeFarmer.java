package me.lisacek.befarmer;

import me.lisacek.befarmer.commands.BeFarmerCommand;
import me.lisacek.befarmer.cons.Crop;
import me.lisacek.befarmer.listener.PlayerListener;
import me.lisacek.befarmer.managers.ItemManager;
import me.lisacek.befarmer.managers.RecipeManager;
import me.lisacek.befarmer.sql.ConnectionManager;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class BeFarmer extends JavaPlugin {

    private static BeFarmer instance;

    private final ConnectionManager sql = new ConnectionManager();

    private YamlConfiguration config;

    private final List<Crop> availableCrops = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        sql.initialize();
        config.getConfigurationSection("tools").getKeys(false).forEach(tool -> {
            ItemManager.getInstance().loadItem(config.getConfigurationSection("tools." + tool));
        });
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("befarmer").setExecutor(new BeFarmerCommand());
        RecipeManager.getInstance().loadRecipes();
        getLogger().info("BeFarmer is enabled!");
    }

    @Override
    public void onDisable() {
        sql.close();
       getLogger().info("plugin disabled!");
    }

    public List<Crop> getAvailableCrops() {
        return availableCrops;
    }

    public ConnectionManager getSql() {
        return sql;
    }

    public YamlConfiguration getConfig() {
        return config;
    }

    public static BeFarmer getInstance() {
        return instance;
    }

    public void loadConfig() {
        File configFile = new File(getDataFolder(), "config.yml");
        config = new YamlConfiguration();
        if (!configFile.exists()) {
            getLogger().info("Config file not found, creating...");
            saveResource("config.yml", false);
        }
        try {
            config.load(configFile);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }
}
