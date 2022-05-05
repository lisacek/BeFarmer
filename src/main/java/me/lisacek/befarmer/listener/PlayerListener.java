package me.lisacek.befarmer.listener;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.lisacek.befarmer.cons.RecipeItem;
import me.lisacek.befarmer.managers.RecipeManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR)
            return;
        NBTItem nbtItem = new NBTItem(item);
        if (!nbtItem.hasKey("hoeId"))
            return;
        Bukkit.getLogger().info("Interact event");
    }

    @EventHandler
    public void onHarvest(BlockBreakEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR)
            return;
        NBTItem nbtItem = new NBTItem(item);
        if (!nbtItem.hasKey("hoeId"))
            return;

        BlockData bdata = event.getBlock().getBlockData();
        if (!(bdata instanceof Ageable)) return;

        Ageable age = (Ageable) bdata;
        if (age.getAge() == age.getMaximumAge()) {
            event.getBlock().getDrops(nbtItem.getItem()).forEach(stack -> {
                if (nbtItem.hasKey(stack.getType().name())) {
                    nbtItem.setInteger(stack.getType().name(), nbtItem.getInteger(stack.getType().name()) + stack.getAmount());
                } else {
                    nbtItem.setInteger(stack.getType().name(), stack.getAmount());
                }
                Bukkit.getLogger().info("Total: " + nbtItem.getInteger(stack.getType().name()));
            });
        }


    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent event) {
        RecipeManager.getInstance().getRecipes().forEach(recipe -> {
            List<RecipeItem> validItems = new ArrayList<>();
            for (int x = 0; x < event.getInventory().getMatrix().length; x++) {
                if (event.getInventory().getMatrix()[x] == null) continue;
                RecipeItem recipeItem = recipe.getRecipeItem(x);
                if (recipeItem == null) continue;
                if (recipeItem.isValid(event.getInventory().getMatrix()[x], recipeItem.getType())) {
                    validItems.add(recipeItem);
                }
            }

            if (validItems.size() == recipe.getRecipeItemsAmount() && recipe.getRecipeItemsAmount() != 0) {
                event.getInventory().setResult(recipe.getResult());
            }
        });
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (event.getSlotType() != InventoryType.SlotType.RESULT) {
            if (event.getInventory().getType() == InventoryType.WORKBENCH) {
                if (event.getCurrentItem() == null) return;
                if (event.getCurrentItem().getType() == Material.AIR) return;
                if (event.getCursor() == null) return;
                if (event.getCursor().getType() == Material.AIR) return;
                ItemStack stack = event.getCurrentItem().clone();
                NBTItem item = new NBTItem(stack);
                if (item.hasKey("hoeMat")) {
                    event.setCancelled(true);
                }
            }
        }
        if (event.getSlotType() == InventoryType.SlotType.RESULT) {
            if (event.getInventory().getType() == InventoryType.WORKBENCH) {
                if (event.getCurrentItem() == null) return;
                if (event.getCurrentItem().getType() == Material.AIR) return;
                ItemStack stack = event.getCurrentItem().clone();
                NBTItem item = new NBTItem(stack);
                if (item.hasKey("hoeMat")) {
                    event.getInventory().clear();
                    event.setCurrentItem(stack);
                }
            }
        }
    }

}
