package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.playersasmarks;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.MarksManager;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.MarksProvider;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarkRelatedValues;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Plugin extends JavaPlugin implements MarksProvider
{
    @Override
    public void onEnable()
    {
        MarksManager.getInstance().marksProviders().add(this);
    }
    
    @Override
    public String getPrefix()
    {
        return "expsm";
    }
    
    @Override
    public Location getMark(String s, MarkRelatedValues relatedValues)
    {
        if (relatedValues.getCommandSender() == null ||
                !relatedValues.getCommandSender().hasPermission("playersasmarks.admin"))
            return null;
    
        Player player = Bukkit.getPlayer(s);
        
        if (player == null)
            return null;
    
        return player.getLocation();
    }
    
    @Override
    public Collection<String> getAllMarksKey(MarkRelatedValues relatedValues)
    {
        if (relatedValues.getCommandSender() == null ||
                !relatedValues.getCommandSender().hasPermission("playersasmarks.admin"))
        {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers())
            result.add(player.getName());
        return Collections.unmodifiableCollection(result);
    }
}
