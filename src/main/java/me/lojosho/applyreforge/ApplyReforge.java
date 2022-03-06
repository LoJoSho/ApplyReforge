package me.lojosho.applyreforge;

import me.lojosho.applyreforge.command.CommandApply;
import me.lojosho.applyreforge.command.CommandApplyTab;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ApplyReforge extends JavaPlugin {
    private static ApplyReforge instance;
    private static Logger log = Logger.getLogger("ApplyReforge");

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        if (Bukkit.getPluginManager().getPlugin("Reforges") != null) {
            log.info("Found Reforges... Linking...");
            getServer().getPluginCommand("applyreforge").setExecutor(new CommandApply());
            getServer().getPluginCommand("applyreforge").setTabCompleter(new CommandApplyTab());
        } else {
            log.severe("Could not find Reforges! This plugin will not work without it!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static ApplyReforge getInstance() {
        return instance;
    }
}
