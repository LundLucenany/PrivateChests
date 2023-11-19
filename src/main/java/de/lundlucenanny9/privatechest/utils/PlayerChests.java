package de.lundlucenanny9.privatechest.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerChests implements Serializable{

    @Serial
    private static final long serialVersionUID = -5448482802744192604L;
    private UUID p;
    private List<String> locs = new ArrayList<>();
    private List<UUID> trusted = new ArrayList<>();

    public PlayerChests(Player p, Location loc) {
        this.p = p.getUniqueId();
        locs.add(new Position(loc).toWord());
    }
    public PlayerChests(Player p) {
        this.p = p.getUniqueId();
    }
    public void addChest(Location location){
        locs.add(new Position(location).toWord());
    }
    public void removeChest(Location location){
        locs.remove(new Position(location).toWord());
    }
    public void addTrusted(Player p){
        trusted.add(p.getUniqueId());
    }
    public void removeTrusted(Player p){
        trusted.remove(p.getUniqueId());
    }
    public List<UUID> getTrusted(){
        return trusted;
    }

    public List<String> getLocationString(){
        return locs;

    }
    public UUID getPlayer(){
        return p;
    }
}
