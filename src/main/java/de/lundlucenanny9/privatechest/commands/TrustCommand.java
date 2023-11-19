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

public class TrustCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only a Player can use this command.");
            return false;
        }
        if(args.length != 1){
            sender.sendMessage(Privatechest.mTooLessArguments);
            return false;
        }else if(!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
            sender.sendMessage(Privatechest.mPlayerNotOnline.replace("$PLAYER$", args[0]));
            return false;
        }
        Player p = (Player) sender;
        for(PlayerChests pC : Privatechest.pChests){
            if(pC.getPlayer().equals(p.getUniqueId())){
                if(pC.getTrusted().contains(Bukkit.getPlayer(args[0]).getUniqueId())){
                    pC.removeTrusted(Bukkit.getPlayer(args[0]));
                    sender.sendMessage(Privatechest.mDetrust.replace("$PLAYER$", args[0]));
                    return true;
                }else {
                    pC.addTrusted(Bukkit.getPlayer(args[0]));
                    sender.sendMessage(Privatechest.mTrust.replace("$PLAYER$", args[0]));
                    return true;
                }
            }
        }
        PlayerChests pC = new PlayerChests((Player) sender);
        pC.addTrusted(Bukkit.getPlayer(args[0]));
        Privatechest.pChests.add(pC);
        sender.sendMessage(Privatechest.mTrust.replace("$PLAYER$", args[0]));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> lst = new ArrayList<>();
        for(Player p : Bukkit.getOnlinePlayers()){
            lst.add(p.getName());
        }
        return lst;
    }
}
