package fr.chairgamertag87.rambosho;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PPCEventListener implements Listener {

    private final PPCGUIManager guiManager;

    public PPCEventListener(PPCGUIManager guiManager) {
        this.guiManager = guiManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        String inventoryTitle = event.getView().getTitle();
        System.out.println("Titre de l'inventaire : " + inventoryTitle);

        System.out.println("Inventaire protégé détecté : " + inventoryTitle + ". Interaction annulée.");
        event.setCancelled(true);

        if (event.getClickedInventory() != null && event.getClickedInventory().equals(player.getInventory())) {
            event.setCancelled(true);
        } else if (event.getClickedInventory() != null) {
            event.setCancelled(true);
        }

        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }

        if (event.getClickedInventory() != null && event.getClickedInventory().equals(player.getInventory())) {
            return;
        }

        Material choice = clickedItem.getType();

        guiManager.recordChoice(player, choice);

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
    }
}