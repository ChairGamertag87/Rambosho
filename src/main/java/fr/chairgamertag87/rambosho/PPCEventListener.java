package fr.chairgamertag87.rambosho;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;

public class PPCEventListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView view = event.getView();
        String title = view.getTitle();

        if ("Pierre Papier Ciseaux".equals(title)) {
            event.setCancelled(true);

            switch (event.getSlot()) {
                case 2 -> event.getWhoClicked().sendMessage(ChatColor.GREEN + "Vous avez choisi Pierre !");
                case 4 -> event.getWhoClicked().sendMessage(ChatColor.AQUA + "Vous avez choisi Papier !");
                case 6 -> event.getWhoClicked().sendMessage(ChatColor.RED + "Vous avez choisi Ciseaux !");
                default -> event.getWhoClicked().sendMessage(ChatColor.GRAY + "Option invalide.");
            }

            event.getWhoClicked().closeInventory();
        }
    }
}