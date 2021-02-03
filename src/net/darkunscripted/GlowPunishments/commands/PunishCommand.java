package net.darkunscripted.GlowPunishments.commands;

import net.darkunscripted.GlowPunishments.managers.GUIManager;
import net.darkunscripted.GlowPunishments.utils.Utils;
import net.minecraft.server.v1_16_R3.CommandExecute;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PunishCommand implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if(s instanceof Player){
            Player p = (Player) s;
            if(p.hasPermission("litebans.history")){
                if (args.length == 0) {
                    p.sendMessage(Utils.chat("&b&lGlowMC &7>> &c/punish <player>"));
                } else if (args.length == 1) {
                    boolean checker = true;
                    for (OfflinePlayer player : Bukkit.getServer().getOfflinePlayers()) {
                        if (player.getName().equalsIgnoreCase(args[0])) {
                            checker = false;
                            GUIManager.PunishmentGUI(player.getUniqueId(), p);
                        }
                    }
                    if(checker){
                        p.sendMessage(Utils.chat("&b&lGlowMC &7>> &cThere has never logged a player online with that name!"));
                    }
                } else {
                    p.sendMessage(Utils.chat("&b&lGlowMC &7>> &c/punish <player>"));
                }
            }
        }else{
            s.sendMessage(Utils.chat("&c[ERROR] Only a player can perform this command!"));
        }
        return false;
    }

}
