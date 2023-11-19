package de.lundlucenanny9.privatechest.commands;

import de.lundlucenanny9.privatechest.Privatechest;
import de.lundlucenanny9.privatechest.utils.PlayerChests;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AccessCommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only a Player can use this command.");
            return false;
        }

        Player p = (Player) sender;
        if(Privatechest.access.contains(p.getUniqueId())){
            Privatechest.access.remove(p.getUniqueId());
            p.sendMessage("§6You can't access all chests anymore!");
        }
        else {
            Privatechest.access.add(p.getUniqueId());
            p.sendMessage("§6You can now access all chests!");
        }
        return true;
    }
}
