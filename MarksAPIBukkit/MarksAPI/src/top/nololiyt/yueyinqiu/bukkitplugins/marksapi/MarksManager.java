package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import org.bukkit.Location;
import org.bukkit.permissions.Permission;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarkSaveResult;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksProviderInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksSaverInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.permissioncheckers.PermissionChecker;

import java.util.*;

public class MarksManager
{
    private static MarksManager instance = new MarksManager();
    
    @NotNull
    public static MarksManager getInstance()
    {
        return instance;
    }
    
    private MarksManager()
    {
    }
    
    private MarksSaverList marksSaverList = new MarksSaverList();
    
    @NotNull
    public MarksSaverList getMarksSaverList()
    {
        return marksSaverList;
    }
    
    private MarksProviderList marksProviderList = new MarksProviderList();
    
    @NotNull
    public MarksProviderList getMarksProviderList()
    {
        return marksProviderList;
    }
    
    @NotNull
    public Map<MarksSaverInfo, MarkSaveResult> saveMark(@NotNull String key,
                                                        @NotNull Location mark,
                                                        @NotNull PermissionChecker permissionChecker,
                                                        MarksSaverInfo... marksSavers)
    {
        Map<MarksSaverInfo, MarkSaveResult> result = new HashMap<>();
        for (MarksSaverInfo marksSaverInfo : marksSavers)
        {
            MarksSaver saver = marksSaverList.getSaver(marksSaverInfo);
            if (saver != null)
                result.put(marksSaverInfo, saver.saveMark(key, mark, permissionChecker));
            else
                result.put(marksSaverInfo, new NoSuchSaverResult(
                        "Could not find the saver: " + marksSaverInfo.getKey() + "."));
        }
        return result;
    }
    
    
    @NotNull
    public Map<MarksSaverInfo, MarkSaveResult> saveMark(@NotNull String key,
                                                        @NotNull Location mark,
                                                        @NotNull PermissionChecker permissionChecker)
    {
        Map<MarksSaverInfo, MarkSaveResult> result = new HashMap<>();
        for (MarksSaver saver : marksSaverList.getAllProviders())
        {
            result.put(saver.getInfo(), saver.saveMark(key, mark, permissionChecker));
        }
        return result;
    }
    
    @NotNull
    public Map<MarksProviderInfo, Location> getMarks(@NotNull String key
            ,@NotNull PermissionChecker permissionChecker, MarksProviderInfo... marksProvidersInfo)
    {
        Map<MarksProviderInfo, Location> result = new HashMap<>();
        for (MarksProviderInfo marksProviderInfo : marksProvidersInfo)
        {
            MarksProvider provider = marksProviderList.getProvider(marksProviderInfo);
            if (provider != null)
            {
                Location mark = provider.getMark(key, permissionChecker);
                if (mark != null)
                    result.put(provider.getInfo(), mark);
            }
        }
        return result;
    }
    
    @NotNull
    public Map<MarksProviderInfo, Location> getMarks(@NotNull String key,
                                                     @NotNull PermissionChecker permissionChecker)
    {
        Map<MarksProviderInfo, Location> result = new HashMap<>();
        for (MarksProvider marksProvider : marksProviderList.getAllProviders())
        {
            Location mark = marksProvider.getMark(key, permissionChecker);
            if (mark != null)
                result.put(marksProvider.getInfo(), mark);
        }
        return result;
    }
    
    @NotNull
    public List<String> getAllMarksKey(@NotNull PermissionChecker permissionChecker)
    {
        List<String> result = new ArrayList<>();
        for (MarksProvider marksProvider : marksProviderList.getAllProviders())
        {
            result.addAll(marksProvider.getAllMarksKey(permissionChecker));
        }
        return result;
    }
    
    private class NoSuchSaverResult implements MarkSaveResult
    {
        private String message;
        
        private NoSuchSaverResult(String message)
        {
            this.message = message;
        }
        
        @Override
        public MarkSaveResult.ResultState getState()
        {
            return MarkSaveResult.ResultState.NO_SUCH_SAVER;
        }
        
        @Override
        public Exception getException()
        {
            return null;
        }
    
        @Override
        public Permission getPermission()
        {
            return null;
        }
    
        @Override
        public String getMessage()
        {
            return message;
        }
    }
}