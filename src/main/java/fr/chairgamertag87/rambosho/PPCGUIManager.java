package fr.chairgamertag87.rambosho;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class PPCGUIManager {

    private final Map<String, List<Player>> activeGames = new HashMap<>();
    private final Map<Player, Integer> scores = new HashMap<>();
    private final Map<Player, Material> choices = new HashMap<>();

    public void startGame(List<Player> players) {
        String gameId = UUID.randomUUID().toString();
        activeGames.put(gameId, players);

        for (Player player : players) {
            scores.put(player, 0);
            openGameGui(player, gameId);
        }
    }

    public void openGameGui(Player player, String gameId) {
        List<Player> players = activeGames.get(gameId);
        if (players == null || players.size() != 2) {
            player.sendMessage("§cErreur : Partie invalide !");
            return;
        }

        Player opponent = getOpponent(players, player);
        if (opponent == null) {
            player.sendMessage("§cErreur : Adversaire introuvable !");
            return;
        }

        int playerScore = scores.getOrDefault(player, 0);
        int opponentScore = scores.getOrDefault(opponent, 0);

        String title = String.format("%s, %d - %d, You", opponent.getName(), opponentScore, playerScore);

        if (title.length() > 32) {
            title = title.substring(0, 32);
        }

        Inventory gui = Bukkit.createInventory(null, 27, title);
        gui.setItem(11, createGuiItem(Material.STONE, "§aPierre"));
        gui.setItem(13, createGuiItem(Material.PAPER, "§bPapier"));
        gui.setItem(15, createGuiItem(Material.SHEARS, "§cCiseaux"));

        player.openInventory(gui);
    }

    public void recordChoice(Player player, Material choice) {
        choices.put(player, choice);

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);

        List<Player> players = getGamePlayers(player);
        if (players == null) return;

        if (choices.keySet().containsAll(players)) {
            resolveGame(players);
        }
    }

    private void resolveGame(List<Player> players) {
        Map<Player, Material> roundChoices = new HashMap<>(choices);

        for (Player player : players) {
            Material playerChoice = roundChoices.get(player);

            for (Player opponent : players) {
                if (!player.equals(opponent)) {
                    Material opponentChoice = roundChoices.get(opponent);
                    if (playerWins(playerChoice, opponentChoice)) {
                        scores.put(player, scores.getOrDefault(player, 0) + 1);
                    }
                }
            }
        }

        for (Player player : players) {
            Material playerChoice = roundChoices.get(player);
            Player opponent = getOpponent(players, player);
            if (opponent == null) continue;

            Material opponentChoice = roundChoices.get(opponent);

            if (playerWins(playerChoice, opponentChoice)) {
                // Victoire
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.2f);
                player.sendMessage("§aVous avez gagné ce round !");
            } else if (playerChoice == opponentChoice) {
                // Égalité
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 0.8f);
                player.sendMessage("§eÉgalité pour ce round !");
            } else {
                // Défaite
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 0.8f);
                player.sendMessage("§cVous avez perdu ce round...");
            }
        }

        choices.clear();
        for (Player player : players) {
            updateGui(player);
        }
    }

    private boolean playerWins(Material playerChoice, Material opponentChoice) {
        return (playerChoice == Material.STONE && opponentChoice == Material.SHEARS) ||
                (playerChoice == Material.PAPER && opponentChoice == Material.STONE) ||
                (playerChoice == Material.SHEARS && opponentChoice == Material.PAPER);
    }

    private Player getOpponent(List<Player> players, Player player) {
        for (Player opponent : players) {
            if (!opponent.equals(player)) {
                return opponent;
            }
        }
        return null;
    }

    private List<Player> getGamePlayers(Player player) {
        for (List<Player> players : activeGames.values()) {
            if (players.contains(player)) {
                return players;
            }
        }
        return null;
    }

    public void updateGui(Player player) {
        List<Player> players = getGamePlayers(player);
        if (players == null) return;

        String gameId = getGameId(players);
        openGameGui(player, gameId);
    }

    private String getGameId(List<Player> players) {
        for (Map.Entry<String, List<Player>> entry : activeGames.entrySet()) {
            if (entry.getValue().equals(players)) {
                return entry.getKey();
            }
        }
        return null;
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