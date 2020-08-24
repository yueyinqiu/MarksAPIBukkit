package top.nololiyt.yueyinqiu.bukkitplugins.marksapi;

import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.Mark;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarkSaveResult;
import top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities.MarksSaverInfo;

public abstract class MarksSaver
{
    public abstract MarksSaverInfo getInfo();
    public abstract MarkSaveResult saveMark(Mark mark);
}
