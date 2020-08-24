package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.teleporter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.MarksManager;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.permissioncheckers.CommandSenderChecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Plugin extends JavaPlugin implements CommandExecutor, TabCompleter
{
    @Override
    public void onEnable()
    {
        Bukkit.getPluginCommand("teleporter").setExecutor(this);
    }
    
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if (strings.length != 1)
            return false;
        if (!(commandSender instanceof Player))
            return false;
        Location mark =
                MarksManager.getInstance().getMark(strings[0],
                        new CommandSenderChecker(commandSender));
    
        if (mark != null)
            ((Player) commandSender).teleport(mark);
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if(strings.length > 1)
            return Collections.emptyList();
        List<String> marks = MarksManager.getInstance().getAllMarksKey(
                new CommandSenderChecker(commandSender));
        if(strings.length == 0)
        {
            return marks;
        }
        List<String> result = new ArrayList<>();
        for(String mark : marks)
        {
            if(mark.startsWith(strings[0]))
            {
                result.add(mark);
            }
        }
        return result;
    }
}
