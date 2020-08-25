package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        saveDefaultConfig();
    
        VersionManager versionManager = new VersionManager(this);
    
        Bukkit.getPluginManager().registerEvents(new JoinListener(versionManager),this);
    }
}
