package de.lundlucenanny9.privatechest.utils;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;
import java.util.UUID;

public class Position implements Serializable {
    private int x;
    private int y;
    private int z;
    private UUID world;
    public Position(World world, int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world.getUID();

    }
    public Position(Location location){
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.world = location.getWorld().getUID();
    }
    public Location getPosition(){
        return new Location(Bukkit.getWorld(world), x, y, z);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setWorld(World world) {
        this.world = world.getUID();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public World getWorld() {
        return Bukkit.getWorld(world);
    }
    public void setPosition(Location loc){
        world = loc.getWorld().getUID();
        x = loc.getBlockX();
        y = loc.getBlockY();
        z = loc.getBlockZ();

    }
    public String toWord(){
        return world + "_" + x + "_" + y + "_" + z;
    }
}
