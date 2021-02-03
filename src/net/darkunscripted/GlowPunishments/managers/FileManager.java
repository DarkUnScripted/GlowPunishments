package net.darkunscripted.GlowPunishments.managers;

import net.darkunscripted.GlowPunishments.Main;
import net.darkunscripted.GlowPunishments.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private Main plugin = Main.getPlugin(Main.class);

    //Files & File Configs

    public FileConfiguration punishmentscfg;
    public File punishmentsfile;

    //--------------------

    public void setup(){
        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdir();
        }

        punishmentsfile = new File(plugin.getDataFolder(), "punishments.yml");

        if(!punishmentsfile.exists()){
            try{
                punishmentsfile.createNewFile();
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not create punishments.yml file!"));
            }
        }

        punishmentscfg = YamlConfiguration.loadConfiguration(punishmentsfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&apunishments.yml file has been created!!"));
    }

    public FileConfiguration getPlayers(){
        return punishmentscfg;
    }

    public void savePlayers(){
        try{
            punishmentscfg.save(punishmentsfile);
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&apunishments.yml has been saved"));
        }catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&cCould not save the punishments.yml file"));
        }
    }

    public void reloadPlayers(){
        punishmentscfg = YamlConfiguration.loadConfiguration(punishmentsfile);
        Bukkit.getServer().getConsoleSender().sendMessage(Utils.chat("&apunishments.yml has been reloaded"));
    }

}
