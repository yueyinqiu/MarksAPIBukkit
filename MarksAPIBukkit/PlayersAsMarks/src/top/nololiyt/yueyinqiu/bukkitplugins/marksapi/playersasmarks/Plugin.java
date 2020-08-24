package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.playersasmarks;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.MarksManager;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.MarksProvider;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksProviderInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.permissioncheckers.PermissionChecker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class Plugin extends JavaPlugin implements MarksProvider
{
    @Override
    public void onEnable()
    {
        MarksManager.getInstance().getMarksProviderList().add(this);
    }
    
    @Override
    public MarksProviderInfo getInfo()
    {
        return new MarksProviderInfo("example.playersAsMarks", 0);
    }
    
    @Override
    public Location getMark(String s, PermissionChecker permissionChecker)
    {
        Player player = Bukkit.getPlayer(s);
        if (player == null)
            return null;
        return player.getLocation();
    }
    
    @Override
    public Collection<String> getAllMarksKey(PermissionChecker permissionChecker)
    {
        List<String> result = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers())
            result.add(player.getName());
        return Collections.unmodifiableCollection(result);
    }
}
