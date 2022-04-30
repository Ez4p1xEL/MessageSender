package p1xel.messagesender.Commands.Storage;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import p1xel.messagesender.MessageSender;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Announcement extends BukkitRunnable {

    private List<String> list = getList();

    int time = getTimeBetween();
    boolean ok = true;

    private int n = getList().size() - 1;

    @Override
    public void run() {

        time--;

        if (!getEnable()) {
            return;
        }

        if (n < 0) {
            n = getList().size() - 1;
        }

        if (time <= 0) {
            ok = true;
            time = getTimeBetween();
            if (ok) {

                    String name = getList().get(n);
                n--;

                    for (String message : getMessageList(name)) {
                        if (getTargetPlayer(name).equalsIgnoreCase("@a")) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (MessageSender.isPAPILoaded()) {
                                    message = ChatColor.translateAlternateColorCodes('&', message);
                                    message = PlaceholderAPI.setPlaceholders(p, message);
                                    p.sendMessage(message);
                                    System.out.println(name);
                                    System.out.println(getMessageList(name));
                                    System.out.println(message);
                                } else {
                                    message = ChatColor.translateAlternateColorCodes('&', message);
                                    p.sendMessage(message);
                                }

                            }
                        } else {
                            Player p = Bukkit.getPlayer(getTargetPlayer(name));
                            if (MessageSender.isPAPILoaded()) {
                                message = ChatColor.translateAlternateColorCodes('&', message);
                                message = PlaceholderAPI.setPlaceholders(p, message);
                                p.sendMessage(message);
                                System.out.println(name);
                                System.out.println(getMessageList(name));
                                System.out.println(message);
                            } else {
                                message = ChatColor.translateAlternateColorCodes('&', message);
                                p.sendMessage(message);
                            }
                        }
                    }



                ok = false;

            }

        }

    }

    public static void createAnnouncementFile() {

        File file = new File(MessageSender.getInstance().getDataFolder(), "announcements.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (MessageSender.get().getString("Language").equalsIgnoreCase("zh_CN")) {

                set("Announcements.enable", true);
                set("Announcements.list", Collections.singletonList("example1"));
                set("Announcements.time-between", 30);

                set("Announcements.announcements.example1.message", Arrays.asList("", "&a这是一条简单的公告", ""));
                set("Announcements.announcements.example1.player", "@a");
            }

            if (MessageSender.get().getString("Language").equalsIgnoreCase("Eng")) {

                set("Announcements.enable", true);
                set("Announcements.list", Collections.singletonList("example1"));
                set("Announcements.time-between", 30);

                set("Announcements.announcements.example1.message", Arrays.asList("", "&aThis is a simple announcement", ""));
                set("Announcements.announcements.example1.player", "@a");

            }

        }

    }

    public static FileConfiguration get() {
        File file = new File(MessageSender.getInstance().getDataFolder(), "announcements.yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void set(String path, Object value) {
        File file = new File(MessageSender.getInstance().getDataFolder(), "announcements.yml");
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        yaml.set(path, value);

        try {
            yaml.save(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static boolean getEnable() {
        return get().getBoolean("Announcements.enable");
    }

    public static String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', get().getString(path));
    }

    public static int getTimeBetween() {
        return get().getInt("Announcements.time-between");
    }

    public static List<String> getList() {
        return get().getStringList("Announcements.list");
    }

    public static List<String> getMessageList(String name) {
        return get().getStringList("Announcements.announcements." + name + ".message");
    }

    public static String translateMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String parsePAPI(String m, Player p) {
        if (MessageSender.isPAPILoaded()) {
            return PlaceholderAPI.setPlaceholders(p, translateMessage(m));
        } else {
            return translateMessage(m);
        }
    }

    public static String getTargetPlayer(String name) {
        return get().getString("Announcements.announcements." + name + ".player");
    }

}
