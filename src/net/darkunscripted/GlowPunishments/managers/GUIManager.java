package net.darkunscripted.GlowPunishments.managers;

import net.darkunscripted.GlowPunishments.data.PunishmentData;
import net.darkunscripted.GlowPunishments.domain.Punishment;
import net.darkunscripted.GlowPunishments.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GUIManager {

    public static void PunishmentGUI(UUID playerUUID, Player StaffMember){
        int slots = PunishmentData.punishments.size();
        while (slots % 9 != 0) {
            slots += 1;
        }
        Inventory punishmentGUI = Bukkit.createInventory(null, slots + 9, Utils.chat("&bPunishments"));
        for(Punishment punishment : PunishmentData.punishments){
            ItemStack item = new ItemStack(Material.PAPER, 1);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Utils.chat("&e&l" + punishment.getName()));
            List<String> lore = new ArrayList<String>();
            int counter = 1;
            for(String duration : punishment.getDuration()){
                lore.add(Utils.chat("&b" + counter + ": " + duration));
            }
            item.setItemMeta(meta);
            punishmentGUI.addItem(item);
        }
        ItemStack closeItem = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = closeItem.getItemMeta();
        closeMeta.setDisplayName(Utils.chat("&c&lClose"));
        punishmentGUI.setItem(punishmentGUI.getSize() - 5, closeItem);

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
        playerHeadMeta.setOwningPlayer(Bukkit.getServer().getOfflinePlayer(playerUUID));
        playerHead.setItemMeta(playerHeadMeta);
        ItemMeta playerMeta = playerHead.getItemMeta();
        playerMeta.setDisplayName(Utils.chat("&e&l" + Bukkit.getServer().getOfflinePlayer(playerUUID).getName()));
        playerHead.setItemMeta(playerMeta);
        punishmentGUI.setItem(punishmentGUI.getSize() - 8, playerHead);

        StaffMember.openInventory(punishmentGUI);
    }

    public static void HistoryGUI(UUID playerUUID, Player StaffMember){
        if(PunishmentData.playerPunishments.containsKey(playerUUID)) {
            int slots = PunishmentData.playerPunishments.get(playerUUID).size();
            while (slots % 9 != 0) {
                slots += 1;
            }
            Inventory HistoryGUI = Bukkit.createInventory(null, slots + 9, Utils.chat("&e&l" + Bukkit.getOfflinePlayer(playerUUID).getName() + "'s &b&lPunishments"));

            for(Punishment punishment : PunishmentData.playerPunishments.get(playerUUID)){
                ItemStack item = new ItemStack(Material.PAPER);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName("&b" + punishment.getName());
                item.setItemMeta(meta);
                HistoryGUI.addItem(item);
            }

            ItemStack closeItem = new ItemStack(Material.BARRIER);
            ItemMeta closeMeta = closeItem.getItemMeta();
            closeMeta.setDisplayName(Utils.chat("&c&lClose"));
            HistoryGUI.setItem(HistoryGUI.getSize() - 5, closeItem);

            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
            playerHeadMeta.setOwningPlayer(Bukkit.getServer().getOfflinePlayer(playerUUID));
            playerHead.setItemMeta(playerHeadMeta);
            ItemMeta playerMeta = playerHead.getItemMeta();
            playerMeta.setDisplayName(Utils.chat("&e&l" + Bukkit.getServer().getOfflinePlayer(playerUUID).getName()));
            playerHead.setItemMeta(playerMeta);
            HistoryGUI.setItem(HistoryGUI.getSize() - 8, playerHead);

            StaffMember.openInventory(HistoryGUI);
        }else{
            StaffMember.sendMessage(Utils.chat("&b&lGlowMC &7>> &cThis player has never been punished!"));
        }
    }

}
