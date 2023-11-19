package de.lundlucenanny9.privatechest.listeners;

import de.lundlucenanny9.privatechest.Privatechest;
import de.lundlucenanny9.privatechest.utils.Position;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Iterator;
import java.util.List;

public class EntityExplodeListener implements Listener {
    @EventHandler
    void onEntityExplode(EntityExplodeEvent e){
        List<Block> destroyed = e.blockList();
        Iterator<Block> it = destroyed.iterator();
        while (it.hasNext()){
            Block block = it.next();
            Location blockLocation = block.getLocation();
            if(Privatechest.locations.contains(new Position(blockLocation).toWord())){

                it.remove();
            }

        }
    }
}
