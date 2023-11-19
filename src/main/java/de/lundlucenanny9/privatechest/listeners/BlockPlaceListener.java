package de.lundlucenanny9.privatechest.listeners;

import de.lundlucenanny9.privatechest.Privatechest;
import de.lundlucenanny9.privatechest.utils.PlayerChests;
import de.lundlucenanny9.privatechest.utils.Position;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {
    @EventHandler
    void onEvent(BlockPlaceEvent e){
        Player p = e.getPlayer();
        Location eloc = e.getBlock().getLocation();
        /*
        for (Location loc : Privatechest.locations) {
            if (loc.distance(eloc) < Privatechest.blockRadius) {
                for (PlayerChests pC : Privatechest.pChests) {
                    if (pC.getLocations().contains(eloc)) {
                        if (!(pC.getPlayer().equals(p) || pC.getTrusted().contains(p) || Privatechest.access.contains(p))) {
                            e.setCancelled(true);
                            return;
                        }break;
                    }
                }
            }
        }
        */
        if(Privatechest.chestTypes.contains(e.getBlock().getType())) {
            for (PlayerChests playerChests : Privatechest.pChests) {
                if (playerChests.getPlayer().equals(p.getUniqueId())) {
                    playerChests.addChest(eloc);
                    Privatechest.locations.add(new Position(eloc).toWord());
                    p.sendMessage(Privatechest.mPlace);
                    return;
                }
            }
            Privatechest.pChests.add(new PlayerChests(p, eloc));
            Privatechest.locations.add(new Position(eloc).toWord());
            p.sendMessage(Privatechest.mPlace);
        }
    }
}
