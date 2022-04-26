package p1xel.messagesender.Commands.Storage;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import p1xel.messagesender.MessageSender;

import java.io.File;
import java.io.IOException;

public class Locale {

    public static FileConfiguration get() {
        if (MessageSender.getInstance().getConfig().getString("Language").equalsIgnoreCase("zh_CN")) {
            File folder = new File(MessageSender.getInstance().getDataFolder(), "/locale");
            File file = new File(folder, "zh_CN.yml");
            return YamlConfiguration.loadConfiguration(file);
        }

        if (MessageSender.getInstance().getConfig().getString("Language").equalsIgnoreCase("Eng")) {
            File folder = new File(MessageSender.getInstance().getDataFolder(), "/locale");
            File file = new File(folder, "Eng.yml");
            return YamlConfiguration.loadConfiguration(file);
        }

        File folder = new File(MessageSender.getInstance().getDataFolder(), "/locale");
        File file = new File(folder, "Eng.yml");
        return YamlConfiguration.loadConfiguration(file);

    }

    public static void set(String path, Object value) {

        if (MessageSender.getInstance().getConfig().getString("Language").equalsIgnoreCase("zh_CN")) {
            File folder = new File(MessageSender.getInstance().getDataFolder(), "/locale");
            File file = new File(folder, "zh_CN.yml");
            FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);

            yaml.set(path, value);

            try {
                yaml.save(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (MessageSender.getInstance().getConfig().getString("Language").equalsIgnoreCase("Eng")) {
            File folder = new File(MessageSender.getInstance().getDataFolder(), "/locale");
            File file = new File(folder, "Eng.yml");
            FileConfiguration yaml = YamlConfiguration.loadConfiguration(file);

            yaml.set(path, value);

            try {
                yaml.save(file);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }



    }

    public static void createLocaleFile() {

        File zhCNfolder = new File(MessageSender.getInstance().getDataFolder(), "/locale");
        File zhCNFile = new File(zhCNfolder, "zh_CN.yml");
        FileConfiguration zhCNyaml = YamlConfiguration.loadConfiguration(zhCNFile);

        if (!zhCNFile.exists()) {

            try {
                zhCNFile.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            zhCNyaml.set("Language", "简体中文");

            zhCNyaml.set("commands.top", "&7&m                         ");
            zhCNyaml.set("commands.help", "&b/MessageSender help 查看帮助");
            zhCNyaml.set("commands.send-message", "&b/MessageSender message <玩家> <消息> 发送普通文本消息");
            zhCNyaml.set("commands.send-title", "&b/MessageSender title <玩家> <主标题> <副标题> 发送标题文本");
            zhCNyaml.set("commands.send-actionbar", "&b/MessageSender actionbar <玩家> <消息> 发送物品栏上方文本");
            zhCNyaml.set("commands.reload", "&b/MessageSender reload 重载配置文件");
            zhCNyaml.set("commands.bottom", "&7&m                         ");

            zhCNyaml.set("reload-success", "&a配置文件重载成功!");
            zhCNyaml.set("invalid-player", "&c玩家没找到, 可尝试@a发送全部玩家");
            zhCNyaml.set("message-sent", "&a消息发送成功!");
            zhCNyaml.set("no-perm", "&c你没有权限执行该指令!");

            try {
                zhCNyaml.save(zhCNFile);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }

        File Engfolder = new File(MessageSender.getInstance().getDataFolder(), "/locale");
        File EngFile = new File(Engfolder, "Eng.yml");
        FileConfiguration Engyaml = YamlConfiguration.loadConfiguration(EngFile);

        if (!EngFile.exists()) {

            try {
                EngFile.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            Engyaml.set("Language", "English");

            Engyaml.set("commands.top", "&7&m                         ");
            Engyaml.set("commands.help", "&b/MessageSender help View help");
            Engyaml.set("commands.send-message", "&b/MessageSender message <player> <message> send message to player.");
            Engyaml.set("commands.send-title", "&b/MessageSender title <player> <title> <subtitle> send title message.");
            Engyaml.set("commands.send-actionbar", "&b/MessageSender actionbar <player> <message> send actionbar message");
            Engyaml.set("commands.reload", "&b/MessageSender reload Reload configuration.");
            Engyaml.set("commands.bottom", "&7&m                         ");

            Engyaml.set("reload-success", "&aConfiguration reloaded successful!");
            Engyaml.set("invalid-player", "&cPlayer is invalid or offline. You can use @a to send to all online players");
            Engyaml.set("message-sent", "&aMessage sent successful!");
            Engyaml.set("no-perm", "&cYou have no permission to do that!");

            try {
                Engyaml.save(EngFile);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        }


    }

    public static String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', get().getString(path));
    }

    public static String parsePapi(String message, Player p) {
        return PlaceholderAPI.setPlaceholders(p, message.replaceAll("%s", " "));
    }

}
