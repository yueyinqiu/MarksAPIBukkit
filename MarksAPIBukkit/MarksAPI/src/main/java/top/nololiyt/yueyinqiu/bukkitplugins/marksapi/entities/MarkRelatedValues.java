package top.nololiyt.yueyinqiu.bukkitplugins.marksapi.entities;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents some related values to get a mark. It will be possible to get per player marks by using this.
 * These values could be set differently by different authors, so you may check out the following page to know our suggestions.
 * https://github.com/yueyinqiu/MarksAPIBukkit/wiki/Suggestions-On-RelatedValues
 */
public interface MarkRelatedValues
{
    /**
     * Returns the related command sender.
     *
     * @return The related command sender.
     */
    CommandSender getCommandSender();
}