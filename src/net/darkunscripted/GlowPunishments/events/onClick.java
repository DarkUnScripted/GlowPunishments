package net.darkunscripted.GlowPunishments.events;

import net.darkunscripted.GlowPunishments.Main;
import net.darkunscripted.GlowPunishments.data.PunishmentData;
import net.darkunscripted.GlowPunishments.domain.Punishment;
import net.darkunscripted.GlowPunishments.utils.Utils;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class onClick implements Listener {

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(Utils.chat("&bPunishments"))){
            e.setCancelled(true);
            ItemStack item = e.getCurrentItem();
            if(item != null) {
                if(item.getType().equals(Material.BARRIER)){
                    e.getWhoClicked().closeInventory();
                }else if(!item.getType().equals(Material.PLAYER_HEAD)) {
                    String itemName = Utils.strip(item.getItemMeta().getDisplayName());
                    for (Punishment punishment : PunishmentData.punishments) {
                        if (punishment.getName().equalsIgnoreCase(itemName)) {
                            if(e.getWhoClicked().hasPermission(punishment.getPermission())) {
                                String command = punishment.getCommand();
                                ItemStack playerItem = e.getClickedInventory().getItem(e.getClickedInventory().getSize() - 8);
                                SkullMeta playerMeta = (SkullMeta) playerItem.getItemMeta();
                                OfflinePlayer player = playerMeta.getOwningPlayer();
                                String name = player.getName();
                                command = command.replace("{target}", name);
                                command = command.replace("{reason}", punishment.getName());
                                if (PunishmentData.playerPunishments.containsKey(player.getUniqueId())) {
                                    int punishmentAmount = PunishmentData.playerPunishments.get(player.getUniqueId()).size();
                                    if (punishmentAmount >= punishment.getDuration().size()) {
                                        command = command.replace("{duration}", punishment.getDuration().get(punishment.getDuration().size() - 1));
                                    } else {
                                        command = command.replace("{duration}", punishment.getDuration().get(punishmentAmount - 1));
                                    }
                                    PunishmentData.playerPunishments.put(playerMeta.getOwningPlayer().getUniqueId(), new ArrayList<Punishment>());
                                    PunishmentData.playerPunishments.get(playerMeta.getOwningPlayer().getUniqueId()).add(punishment);
                                } else {
                                    command = command.replace("{duration}", punishment.getDuration().get(0));
                                    ArrayList<Punishment> punishments = new ArrayList<Punishment>();
                                    punishments.add(punishment);
                                    PunishmentData.playerPunishments.put(playerMeta.getOwningPlayer().getUniqueId(), punishments);
                                }
                                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), command);
                            }else{
                                e.getWhoClicked().closeInventory();
                                e.getWhoClicked().sendMessage(Utils.chat("&b&lGlowMC &7>> &cYou dont have permission to use this punishment!"));
                            }
                        }
                    }
                }
            }
        }
    }


    @EventHandler
    public void onDrag(InventoryDragEvent e){
        if(e.getView().getTitle().equalsIgnoreCase(Utils.chat("&bPunishments"))){
            e.setCancelled(true);
        }
    }

}
