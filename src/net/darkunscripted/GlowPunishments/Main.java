package net.darkunscripted.GlowPunishments;

import net.darkunscripted.GlowPunishments.commands.HistoryCommand;
import net.darkunscripted.GlowPunishments.commands.PunishCommand;
import net.darkunscripted.GlowPunishments.events.onClick;
import net.darkunscripted.GlowPunishments.managers.FileManager;
import net.darkunscripted.GlowPunishments.managers.PunishmentManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private FileManager cfgm;

    @Override
    public void onEnable() {
        loadConfigManager();
        loadConfig();
        PunishmentManager.loadPunishments();
        PunishmentManager.loadPlayerPunishments();
        registerCommands();
        registerEvents();
        registerManagers();
    }

    @Override
    public void onDisable() {
        PunishmentManager.savePlayerPunishments();
    }

    public void registerCommands(){
        getCommand("punish").setExecutor(new PunishCommand());
        getCommand("history").setExecutor(new HistoryCommand());
    }

    public void registerEvents(){
        this.getServer().getPluginManager().registerEvents(new onClick(), this);
    }

    public void registerManagers(){

    }

    public void loadConfigManager(){
        cfgm = new FileManager();
        cfgm.setup();
        cfgm.savePlayers();
        cfgm.reloadPlayers();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public FileManager getManager(){
        return cfgm;
    }

}
