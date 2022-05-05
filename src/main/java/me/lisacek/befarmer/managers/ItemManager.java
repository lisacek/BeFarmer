package me.lisacek.befarmer.managers;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.lisacek.befarmer.cons.FarmerItem;
import me.lisacek.befarmer.utils.Colors;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class ItemManager {

    private ItemManager() {
    }

    private static final ItemManager INSTANCE = new ItemManager();

    private final HashMap<String, FarmerItem> items = new HashMap<>();

    public void loadItem(ConfigurationSection section) {
        ItemStack item = new ItemStack(Material.valueOf(section.getString("material")));

        section.getConfigurationSection("enchantments").getKeys(false).forEach(enchantment -> {
            item.addUnsafeEnchantment(Enchantment.getByName(enchantment), section.getInt("enchantments." + enchantment));
        });

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colors.translateColors(section.getString("name")));
        meta.setLore(Colors.translateColors(section.getStringList("lore")));
        item.setItemMeta(meta);

        NBTItem nbtItem = new NBTItem(item);
        nbtItem.setString("hoeId", section.getString("nbt.keys.hoeId"));
        nbtItem.setBoolean("cropUpgrade", false);
        nbtItem.setBoolean("rewardUpgrade", false);
        nbtItem.setBoolean("fortuneUpgrade", false);
        nbtItem.setBoolean("replantUpgrade", false);
        items.put(section.getName(), new FarmerItem(section.getName(), nbtItem.getItem()));
    }

    public FarmerItem getItem(String name) {
        return items.get(name);
    }

    public HashMap<String, FarmerItem> getItems() {
        return items;
    }

    public static ItemManager getInstance() {
        return INSTANCE;
    }

}
