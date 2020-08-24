package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import com.sun.istack.internal.NotNull;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.bukkit.Location;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.Mark;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarkSaveResult;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksProviderInfo;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksSaverInfo;

import java.util.*;

public class MarksManager
{
    private static MarksManager instance = new MarksManager();
    @NotNull
    public static MarksManager getInstance()
    {
        return instance;
    }
    
    private MarksManager() { }
    
    private MarksSaverList marksSaverList = new MarksSaverList();
    public MarksSaverList getMarksSaverList()
    {
        return marksSaverList;
    }
    
    private MarksProviderList marksProviderList = new MarksProviderList();
    public MarksProviderList getMarksProviderList()
    {
        return marksProviderList;
    }
    
    public Map<MarksSaverInfo, MarkSaveResult> saveMark(Mark mark, MarksSaverInfo... marksSavers)
    {
        Map<MarksSaverInfo, MarkSaveResult> result = new HashMap<>();
        for (MarksSaverInfo marksSaverInfo : marksSavers)
        {
            MarksSaver saver = marksSaverList.getSaver(marksSaverInfo);
            if (saver != null)
            {
                result.put(marksSaverInfo, saver.saveMark(mark));
            }
            else
            {
                String message = "Could not find the saver: " + marksSaverInfo.getKey() + ".";
                result.put(marksSaverInfo, new MarkSaveResult()
                {
                    @Override
                    public ResultState getState()
                    {
                        return ResultState.NO_SUCH_SAVER;
                    }
    
                    @Override
                    public Exception getException()
                    {
                        return null;
                    }
    
                    @Override
                    public String getMessage()
                    {
                        return message;
                    }
                });
            }
        }
        return result;
    }
    
    public Map<MarksProviderInfo, Mark> getMarks(String key)
    {
        Map<MarksProviderInfo, Mark> result = new HashMap<>();
        for (MarksProvider marksProvider : marksProviderList.getAllProviders())
        {
            result.put(marksProvider.getInfo(),marksProvider.getMark(key));
        }
        return result;
    }
    
    public List<String> getAllMarksKey()
    {
        List<String> result = new ArrayList<>();
        for (MarksProvider marksProvider : marksProviderList.getAllProviders())
        {
            result.addAll(marksProvider.getAllMarksKey());
        }
        return result;
    }
}