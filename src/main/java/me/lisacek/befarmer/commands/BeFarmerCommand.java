package me.lisacek.befarmer.commands;

import me.lisacek.befarmer.managers.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BeFarmerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        player.getInventory().addItem(ItemManager.getInstance().getItems().values().stream().findFirst().get().getItem());
        return true;
    }

}
