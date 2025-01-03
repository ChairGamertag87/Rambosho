package fr.chairgamertag87.rambosho;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PPCCommand implements CommandExecutor {

    public PPCCommand(PPCGUIManager guiManager) {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Seuls les joueurs peuvent utiliser cette commande !");
            return true;
        }

        Player senderPlayer = (Player) sender;

        if (args.length != 1) {
            senderPlayer.sendMessage("§eUtilisation : /ppc <nom_du_joueur>");
            return true;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);

        if (targetPlayer == null || !targetPlayer.isOnline()) {
            senderPlayer.sendMessage("§cLe joueur " + args[0] + " n'est pas en ligne !");
            return true;
        }

        if (targetPlayer.equals(senderPlayer)) {
            senderPlayer.sendMessage("§cVous ne pouvez pas vous inviter vous-même !");
            return true;
        }

        Component invitationMessage = Component.text("§a" + senderPlayer.getName() + " vous invite à jouer à Pierre-Papier-Ciseaux ! ")
                .append(Component.text("§e[Accepter]")
                        .hoverEvent(HoverEvent.showText(Component.text("Cliquez pour accepter l'invitation !")))
                        .clickEvent(ClickEvent.runCommand("/ppcaccept " + senderPlayer.getName())));

        targetPlayer.sendMessage(invitationMessage);

        senderPlayer.sendMessage("§aInvitation envoyée à " + targetPlayer.getName() + " !");
        senderPlayer.playSound(senderPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.5f);

        return true;
    }
}