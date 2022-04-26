package p1xel.messagesender;

import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import p1xel.messagesender.Commands.SM;
import p1xel.messagesender.Commands.Storage.Locale;

public class MessageSender extends JavaPlugin {

    private static MessageSender instance;

    public static MessageSender getInstance() {
        return instance;
    }

    public static Configuration get() {
        return MessageSender.getInstance().getConfig();
    }


    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        Locale.createLocaleFile();

        Bukkit.getPluginCommand("MessageSender").setExecutor(new SM());

        logger("Plugin loaded!");


    }












    public static void logger(String string) {
        Bukkit.getLogger().info("MessageSender: " + string);
    }





}
