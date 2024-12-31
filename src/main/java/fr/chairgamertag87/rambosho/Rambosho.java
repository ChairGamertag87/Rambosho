package fr.chairgamertag87.rambosho;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Rambosho extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("PierrePapierCiseaux est activé !");

        getCommand("ppc").setExecutor(new PPCCommand());

        Bukkit.getPluginManager().registerEvents(new PPCEventListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("PierrePapierCiseaux est désactivé !");
    }

}
