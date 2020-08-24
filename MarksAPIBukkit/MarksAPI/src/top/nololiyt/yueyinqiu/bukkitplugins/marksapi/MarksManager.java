package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import org.bukkit.Location;
import org.bukkit.permissions.Permission;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarkSaveResult;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.permissioncheckers.PermissionChecker;

import java.util.*;

public class MarksManager
{
    public static final char SEPARATOR = '_';
    
    private static MarksManager instance = new MarksManager();
    @NotNull
    public static MarksManager getInstance()
    {
        return instance;
    }
    
    private MarksManager()
    {
    }
    
    private MarksOperatorList<MarksSaver> marksSaverList = new MarksOperatorList<>();
    
    @NotNull
    public MarksOperatorList<MarksSaver> marksSavers()
    {
        return marksSaverList;
    }
    
    private MarksOperatorList<MarksProvider> marksProviderList = new MarksOperatorList<>();
    
    @NotNull
    public MarksOperatorList<MarksProvider> marksProviders()
    {
        return marksProviderList;
    }
    
    @NotNull
    public MarkSaveResult saveMark(@NotNull String markKey,
                                   @NotNull Location mark,
                                   @NotNull String marksSaverPrefix,
                                   @NotNull PermissionChecker permissionChecker)
    {
        MarksSaver saver = marksSaverList.get(marksSaverPrefix);
        if (saver != null)
            return saver.saveMark(markKey, mark, permissionChecker);
        else
            return new NoSuchSaverResult("Could not find the saver: " + marksSaverPrefix + ".");
    }
    @NotNull
    public MarkSaveResult saveMark(@NotNull String markKeyWithPrefix,
                                   @NotNull Location mark,
                                   @NotNull PermissionChecker permissionChecker)
    {
        StringKeyValue keyValue = splitPrefix(markKeyWithPrefix);
        if(keyValue == null)
            return new NoSuchSaverResult("Prefix not found.");
        return saveMark(keyValue.key, mark, keyValue.value, permissionChecker);
    }
    
    @Nullable
    public Location getMark(@NotNull String markKey,
                            @NotNull String providerPrefix,
                            @NotNull PermissionChecker permissionChecker)
    {
        MarksProvider provider = marksProviderList.get(providerPrefix);
        return provider == null ? null :
                provider.getMark(markKey, permissionChecker);
    }
    
    @Nullable
    public Location getMark(@NotNull String markKeyWithPrefix,
                            @NotNull PermissionChecker permissionChecker)
    {
        StringKeyValue keyValue = splitPrefix(markKeyWithPrefix);
        if(keyValue == null)
            return null;
        return getMark(keyValue.key, keyValue.value, permissionChecker);
    }
    
    @Nullable
    private StringKeyValue splitPrefix(@NotNull String marKeyWithPrefix)
    {
        int sepIndex = marKeyWithPrefix.indexOf(SEPARATOR);
        if (sepIndex == -1)
            return null;
        return new StringKeyValue(
                marKeyWithPrefix.substring(0, sepIndex), marKeyWithPrefix.substring(sepIndex));
    }
    
    @NotNull
    public List<String> getAllMarksKey(@NotNull PermissionChecker permissionChecker)
    {
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (MarksProvider marksProvider : marksProviderList.getAll())
        {
            String key = marksProvider.getPrefix();
            for (String item : marksProvider.getAllMarksKey(permissionChecker))
            {
                stringBuilder.append(key)
                        .append(SEPARATOR)
                        .append(item);
                
                result.add(stringBuilder.toString());
                stringBuilder.setLength(0);
            }
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
    
    private class StringKeyValue
    {
        private String key;
        private String value;
    
        public StringKeyValue(String key, String value)
        {
            this.key = key;
            this.value = value;
        }
    }
}