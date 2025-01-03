package fr.chairgamertag87.rambosho;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class PPCAcceptCommand implements CommandExecutor {

    private final PPCGUIManager guiManager;

    public PPCAcceptCommand(PPCGUIManager guiManager) {
        this.guiManager = guiManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seuls les joueurs peuvent utiliser cette commande !");
            return true;
        }

        Player accepter = (Player) sender;

        if (args.length != 1) {
            accepter.sendMessage("§cCommande invalide ! Utilisez : /ppcaccept <joueur>");
            return true;
        }

        Player inviter = Bukkit.getPlayer(args[0]);

        if (inviter == null || !inviter.isOnline()) {
            accepter.sendMessage("§cLe joueur " + args[0] + " n'est pas en ligne !");
            return true;
        }

        accepter.sendMessage("§aVous avez accepté l'invitation de " + inviter.getName() + " !");
        inviter.sendMessage("§a" + accepter.getName() + " a accepté votre invitation !");
        inviter.playSound(inviter.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.5f);

        if (guiManager != null) {
            guiManager.startGame(Arrays.asList(inviter, accepter));
        } else {
            accepter.sendMessage("§cErreur interne : le gestionnaire de GUI n'est pas disponible !");
            Bukkit.getLogger().warning("Le gestionnaire de GUI n'est pas initialisé !");
        }
        return true;
    }
}