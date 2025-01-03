package fr.chairgamertag87.rambosho;

import org.bukkit.plugin.java.JavaPlugin;

public class Rambosho extends JavaPlugin {

    private static Rambosho instance;

    @Override
    public void onEnable() {
        PPCGUIManager guiManager = new PPCGUIManager();

        getServer().getPluginManager().registerEvents(new PPCEventListener(guiManager), this);

        getCommand("ppc").setExecutor(new PPCCommand(guiManager));
        getCommand("ppcaccept").setExecutor(new PPCAcceptCommand(guiManager));
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin Rambosho désactivé !");
    }

    public static Rambosho getInstance() {
        return instance;
    }
}