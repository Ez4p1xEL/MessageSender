package p1xel.messagesender;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import p1xel.messagesender.Commands.SM;
import p1xel.messagesender.Commands.Storage.Announcement;
import p1xel.messagesender.Commands.Storage.Locale;

public class MessageSender extends JavaPlugin {

    private static MessageSender instance;

    public static MessageSender getInstance() {
        return instance;
    }

    public static Configuration get() {
        return MessageSender.getInstance().getConfig();
    }

    public static boolean isPAPILoaded() {
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        Locale.createLocaleFile();
        Announcement.createAnnouncementFile();
        new Announcement().runTaskTimer(this, 0L, 20L);
        Bukkit.getPluginCommand("MessageSender").setExecutor(new SM());

        if (isPAPILoaded()) {
            logger(Locale.getMessage("papi-loaded"));
        } else {
            logger(Locale.getMessage("papi-not-loaded"));
        }

        logger("Plugin loaded!");

    }


    public static void logger(String string) {
        Bukkit.getLogger().info("MessageSender: " + string);
    }


}