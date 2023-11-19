package de.lundlucenanny9.privatechest.listeners;

import de.lundlucenanny9.privatechest.Privatechest;
import de.lundlucenanny9.privatechest.utils.PlayerChests;
import de.lundlucenanny9.privatechest.utils.Position;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;



public class PlayerInteractListener implements Listener {
    @EventHandler
    void onEvent(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(!e.hasBlock()) return;
        Block block = e.getClickedBlock();
        if(!Privatechest.chestTypes.contains(block.getType())) return;
        if(Privatechest.locations.contains(new Position(block.getLocation()).toWord())){
            for(PlayerChests pC : Privatechest.pChests) {
                if(pC.getLocationString().contains(new Position(block.getLocation()).toWord())){
                    if(!(pC.getPlayer().equals(p.getUniqueId()) || Privatechest.access.contains(p.getUniqueId()) || pC.getTrusted().contains(p.getUniqueId()))){
                        e.setCancelled(true);
                        p.sendTitle(" ", "You are not allowed to access this chest.");
                        return;
                    }
                    return;
                }
            }
        }
    }
}
