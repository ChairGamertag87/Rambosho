package fr.chairgamertag87.rambosho;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PPCCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory(null, 9, "Pierre Papier Ciseaux");
            gui.setItem(2, createGuiItem(Material.STONE, "§aPierre"));
            gui.setItem(4, createGuiItem(Material.PAPER, "§bPapier"));
            gui.setItem(6, createGuiItem(Material.SHEARS, "§cCiseaux"));

            player.openInventory(gui);
            return true;
        }

        sender.sendMessage("Seuls les joueurs peuvent exécuter cette commande.");
        return true;
    }

    private ItemStack createGuiItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }
}