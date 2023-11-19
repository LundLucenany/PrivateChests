package de.lundlucenanny9.privatechest;

import de.lundlucenanny9.privatechest.commands.AccessCommand;
import de.lundlucenanny9.privatechest.commands.TrustCommand;
import de.lundlucenanny9.privatechest.listeners.BlockBreakListener;
import de.lundlucenanny9.privatechest.listeners.BlockPlaceListener;
import de.lundlucenanny9.privatechest.listeners.EntityExplodeListener;
import de.lundlucenanny9.privatechest.listeners.PlayerInteractListener;
import de.lundlucenanny9.privatechest.utils.PlayerChests;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Privatechest extends JavaPlugin {
    public static List<String> locations;
    public static List<PlayerChests> pChests;
    public static List<UUID> access = new ArrayList<>();
    public static int blockRadius;
    public static List<Material> chestTypes = new ArrayList<>();
    public static String mPlace;
    public static String mBreak;
    public static String mTrust;
    public static String mDetrust;
    public static String mTooLessArguments;
    public static String mPlayerNotOnline;
    PluginManager manager = this.getServer().getPluginManager();
    @Override
    public void onEnable() {
        this.saveResource("config.yml", false);
        for(String l : this.getConfig().getStringList("protected_blocks")){
            chestTypes.add(Material.getMaterial(l));
        }
        mPlace = this.getConfig().getString("messages.place");
        mBreak = this.getConfig().getString("messages.break");
        mTrust = this.getConfig().getString("messages.trust");
        mDetrust = this.getConfig().getString("messages.detrust");
        mTooLessArguments = this.getConfig().getString("messages.tooLessArguments");
        mPlayerNotOnline = this.getConfig().getString("messages.playerNotOnline");

        readPChests();
        readLocations();
        manager.registerEvents( new BlockPlaceListener(), this);
        manager.registerEvents( new PlayerInteractListener(), this);
        manager.registerEvents( new BlockBreakListener(), this);
        manager.registerEvents(new EntityExplodeListener(), this);
        getCommand("trust").setExecutor(new TrustCommand());
        getCommand("trust").setTabCompleter(new TrustCommand());
        getCommand("chestoverride").setExecutor(new AccessCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        safePChests();
        safeLocations();

    }

    private void readPChests(){
        Object object;
        File file = new File(this.getDataFolder(), "/pchest");
        if (file.exists()) {
            try {
                InputStream stream = Files.newInputStream(file.toPath()); //datei einlesen
                ObjectInputStream objectStream = new ObjectInputStream(stream);
                object = objectStream.readObject();
                pChests = (List<PlayerChests>) object;
                objectStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            pChests = new ArrayList<PlayerChests>();
        }
    }
    private void readLocations(){
        Object object;
        File file = new File(this.getDataFolder(), "/locations");
        if (file.exists()) {
            try {
                InputStream stream = Files.newInputStream(file.toPath()); //datei einlesen
                ObjectInputStream objectStream = new ObjectInputStream(stream);
                object = objectStream.readObject();

                locations = (List<String>) object;
                objectStream.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            locations = new ArrayList<>();
        }
    }
    private void safePChests(){
        if (pChests.size() > 0) {
            if (!this.getDataFolder().exists()) {
                this.getDataFolder().mkdirs();
            }
            File file = new File(this.getDataFolder(), "/pchest");
            try {
                OutputStream stream = new FileOutputStream((file));
                ObjectOutputStream objectStream = new ObjectOutputStream(stream);
                objectStream.writeObject(pChests);
                objectStream.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
    private void safeLocations(){
        if (locations.size() > 0) {
            if (!this.getDataFolder().exists()) {
                this.getDataFolder().mkdirs();
            }
            File file = new File(this.getDataFolder(), "/locations");
            try {
                OutputStream stream = new FileOutputStream((file));
                ObjectOutputStream objectStream = new ObjectOutputStream(stream);
                objectStream.writeObject(locations);
                objectStream.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}
