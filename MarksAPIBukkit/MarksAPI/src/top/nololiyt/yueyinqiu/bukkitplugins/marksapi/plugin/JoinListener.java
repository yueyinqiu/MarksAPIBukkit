package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.math.BigDecimal;

class JoinListener implements Listener
{
    private VersionManager versionManager;
    
    JoinListener(VersionManager versionManager)
    {
        this.versionManager = versionManager;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void OnPlayerJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        if (!player.hasPermission("marksapi.admin"))
        {
            return;
        }
        
        if(!versionManager.isUpdateCheckerEnabled())
        {
            return;
        }
        
        BigDecimal current = versionManager.getCurrentVersion();
        LatestVersion latest = versionManager.getLatestVersion();
        player.sendMessage("[]: Version: '" + latest.getVersion().toString() + "' is available. " +
                "And you are now using '" + current.toString() + "'. " +
                "Download it at 'https://yueyinqiu.github.io/MarksBukkit/download'.");
    }
}