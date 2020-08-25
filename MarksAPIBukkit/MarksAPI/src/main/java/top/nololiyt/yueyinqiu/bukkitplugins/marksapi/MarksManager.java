package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import org.bukkit.Location;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarkRelatedValues;

import java.util.*;

/**
 * Represents a marks manager.
 */
public class MarksManager
{
    private static final char SEPARATOR = '_';
    private static final char SEPARATOR_LENGTH = 1;
    
    private static MarksManager instance = new MarksManager();
    /**
     * Returns a marks manager.
     */
    
    public static MarksManager getInstance()
    {
        return instance;
    }
    
    private MarksManager()
    {
    }
    
    private MarksProviderList marksProviderList = new MarksProviderList();
    
    /**
     * Returns the marks provider list of this manager. You can add the provider into it.
     * @return The marks provider list.
     */
    
    public MarksProviderList marksProviders()
    {
        return marksProviderList;
    }
    
    /**
     * Get a mark.
     * @param markKey The key of the mark without the provider prefix.
     * @param providerPrefix The provider prefix.
     * @param markRelatedValues Some related values.
     * @return The mark; or <code>null</code> if it wasn't found.
     */
    
    public Location getMark( String markKey,
                             String providerPrefix,
                             MarkRelatedValues markRelatedValues)
    {
        MarksProvider provider = marksProviderList.get(providerPrefix);
        return provider == null ? null :
                provider.getMark(markKey, markRelatedValues);
    }
    
    /**
     * Get a mark.
     * @param markKeyWithPrefix The key of the mark with the provider prefix.
     * @param markRelatedValues Some related values.
     * @return The mark; or <code>null</code> if it wasn't found.
     */
    
    public Location getMark( String markKeyWithPrefix,
                             MarkRelatedValues markRelatedValues)
    {
        StringKeyValue keyValue = splitPrefix(markKeyWithPrefix);
        if(keyValue == null)
            return null;
        return getMark(keyValue.value, keyValue.key, markRelatedValues);
    }
    
    
    private StringKeyValue splitPrefix( String marKeyWithPrefix)
    {
        int sepIndex = marKeyWithPrefix.indexOf(SEPARATOR);
        if (sepIndex == -1)
            return null;
        return new StringKeyValue(
                marKeyWithPrefix.substring(0, sepIndex),
                marKeyWithPrefix.substring(sepIndex + SEPARATOR_LENGTH));
    }
    
    /**
     * Get all marks' keys.
     * @param markRelatedValues Some related values.
     * @return The keys.
     */
    
    public List<String> getAllMarksKey( MarkRelatedValues markRelatedValues)
    {
        List<String> result = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (MarksProvider marksProvider : marksProviderList.getAll())
        {
            String key = marksProvider.getPrefix();
            for (String item : marksProvider.getAllMarksKey(markRelatedValues))
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