package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.Mark;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksProviderInfo;

import java.util.Collection;


public abstract class MarksProvider
{
    public abstract MarksProviderInfo getInfo();
    public abstract Mark getMark(String key);
    public abstract Collection<String> getAllMarksKey();
}
