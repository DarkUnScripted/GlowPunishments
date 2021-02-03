package net.darkunscripted.GlowPunishments.managers;

import net.darkunscripted.GlowPunishments.Main;
import net.darkunscripted.GlowPunishments.data.PunishmentData;
import net.darkunscripted.GlowPunishments.domain.Punishment;
import net.darkunscripted.GlowPunishments.utils.Utils;
import org.bukkit.configuration.ConfigurationSection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PunishmentManager {

    static Main plugin = Main.getPlugin(Main.class);

    public static void loadPunishments(){
        if(plugin.getConfig().isConfigurationSection("punishments")) {
            ConfigurationSection PunishmentSection = plugin.getConfig().getConfigurationSection("punishments");
            for(String punishmentName : new ArrayList<String>(PunishmentSection.getKeys(false))){
                String name = punishmentName;
                if(plugin.getConfig().contains("punishments." + punishmentName + ".command") && plugin.getConfig().contains("punishments." + punishmentName + ".duration")){
                    String command = plugin.getConfig().getString("punishments." + punishmentName + ".command");
                    ArrayList<String> durations = (ArrayList<String>) plugin.getConfig().getStringList("punishments." + punishmentName + ".duration");
                    String permission = plugin.getConfig().getString("punishments." + punishmentName + ".permission");
                    Punishment punishment = new Punishment(name, command, permission);
                    punishment.setDuration(durations);
                }
            }
        }
    }

    public static void savePlayerPunishments(){
        for(Map.Entry<UUID, ArrayList<Punishment>> punishments : PunishmentData.playerPunishments.entrySet()){
            try{
                ArrayList<String> punishmentNames = new ArrayList<String>();
                for(Punishment punishment : punishments.getValue()) {
                    punishmentNames.add(punishment.getName());
                }
                plugin.getManager().punishmentscfg.set("players." + punishments.getKey() + ".punishments", punishmentNames);
                plugin.getManager().punishmentscfg.save(plugin.getManager().punishmentsfile);
            }catch (IOException e){
                plugin.getServer().getConsoleSender().sendMessage(Utils.chat("&c[ERROR] Could not save punishments to file!"));
            }
        }
    }

    public static void loadPlayerPunishments(){
        ConfigurationSection playerSection = plugin.getManager().punishmentscfg.getConfigurationSection("players");
        for(String playerUUID : playerSection.getKeys(false)){
            ArrayList<Punishment> punishments = new ArrayList<Punishment>();
            List<String> punishmentNames = plugin.getManager().punishmentscfg.getStringList("players." + playerUUID + ".punishments");
            for(String punishment : punishmentNames){
                for(Punishment punishmentObject : PunishmentData.punishments){
                    if(punishmentObject.getName().equalsIgnoreCase(punishment)){
                        punishments.add(punishmentObject);
                    }
                }
            }
            PunishmentData.playerPunishments.put(UUID.fromString(playerUUID), punishments);
        }
    }

}
