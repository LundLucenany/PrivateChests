package de.lundlucenanny9.privatechest.listeners;

import de.lundlucenanny9.privatechest.Privatechest;
import de.lundlucenanny9.privatechest.utils.PlayerChests;
import de.lundlucenanny9.privatechest.utils.Position;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    @EventHandler
    void onEvent(BlockBreakEvent e){
        Location pos = e.getBlock().getLocation();
        Player p = e.getPlayer();
        if(Privatechest.chestTypes.contains(e.getBlock().getType())){
            if(Privatechest.locations.contains(new Position(pos).toWord())){
                for (PlayerChests pC:Privatechest.pChests) {
                    if(pC.getLocationString().contains(new Position(pos).toWord())){
                        if(pC.getPlayer().equals(p.getUniqueId()) || Privatechest.access.contains(p.getUniqueId()) || pC.getTrusted().contains(p.getUniqueId())){
                            pC.removeChest(pos);
                            Privatechest.locations.remove(new Position(pos).toWord());
                            p.sendMessage(Privatechest.mBreak);
                        }else {
                            e.setCancelled(true);
                        }
                    }
                }
            }
        }

    }
}
