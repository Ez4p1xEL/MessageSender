package p1xel.messagesender.Commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import p1xel.messagesender.Commands.Storage.Locale;
import p1xel.messagesender.MessageSender;

import javax.annotation.ParametersAreNonnullByDefault;

public class SM implements CommandExecutor {

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;

        if (!p.isOp()) {
            if (!sender.hasPermission("messagesender.use")) {
                sender.sendMessage(Locale.getMessage("no-perm"));
                return true;
            }
        }



        if (args.length == 0) {

            sender.sendMessage(Locale.getMessage("commands.help"));

            return true;

        }


        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("help")) {

                sender.sendMessage(Locale.getMessage("commands.top"));
                sender.sendMessage(Locale.getMessage("commands.help"));
                sender.sendMessage(Locale.getMessage("commands.send-message"));
                sender.sendMessage(Locale.getMessage("commands.send-title"));
                sender.sendMessage(Locale.getMessage("commands.send-actionbar"));
                sender.sendMessage(Locale.getMessage("commands.reload"));
                sender.sendMessage(Locale.getMessage("commands.bottom"));

                return true;

            }

            if (args[0].equalsIgnoreCase("reload")) {

                MessageSender.getInstance().reloadConfig();

                sender.sendMessage(Locale.getMessage("reload-success"));
                return true;

            }

        }

        if (args.length == 2) {

            if (args[0].equalsIgnoreCase("message")) {

                sender.sendMessage(Locale.getMessage("commands.send-message"));

                return true;

            }

            if (args[0].equalsIgnoreCase("title")) {

                sender.sendMessage(Locale.getMessage("commands.send-title"));

                return true;

            }

            if (args[0].equalsIgnoreCase("actionbar")) {

                sender.sendMessage(Locale.getMessage("commands.send-actionbar"));

                return true;

            }

            sender.sendMessage(Locale.getMessage("commands.help"));

            return true;

        }

        if (args.length == 3) {

            if (args[0].equalsIgnoreCase("message")) {

                if (args[1].equalsIgnoreCase("@a")) {

                    sender.sendMessage(Locale.getMessage("message-sent"));

                    for (Player allp : Bukkit.getOnlinePlayers()) {
                        
                        allp.sendMessage(Locale.parsePapi(args[2],allp));
                        
                    }

                    return true;

                } else {
                    
                    Player target = Bukkit.getPlayer(args[1]);
                    
                    if (target == null) {
                        sender.sendMessage(Locale.getMessage("invalid-player"));
                        return true;
                    }

                    sender.sendMessage(Locale.getMessage("message-sent"));
                    target.sendMessage(Locale.parsePapi(args[2],target));
                    return true;
                    
                }

            }

            if (args[0].equalsIgnoreCase("actionbar")) {

                if (args[1].equalsIgnoreCase("@a")) {

                    sender.sendMessage(Locale.getMessage("message-sent"));

                    for (Player allp : Bukkit.getOnlinePlayers()) {

                        allp.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Locale.parsePapi(args[2],allp)));

                    }

                    return true;

                } else {

                    Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        sender.sendMessage(Locale.getMessage("invalid-player"));
                        return true;
                    }

                    sender.sendMessage(Locale.getMessage("message-sent"));
                    target.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Locale.parsePapi(args[2],target)));
                    return true;

                }

            }


        }

        if (args.length == 4) {

            if (args[0].equalsIgnoreCase("title")) {

                if (args[1].equalsIgnoreCase("@a")) {

                    sender.sendMessage(Locale.getMessage("message-sent"));

                    for (Player allp : Bukkit.getOnlinePlayers()) {

                        allp.sendTitle(Locale.parsePapi(args[2],allp), Locale.parsePapi(args[3],allp), MessageSender.get().getInt("title.fadeIn"), MessageSender.get().getInt("title.stay"), MessageSender.get().getInt("title.fadeOut"));

                    }

                    return true;

                } else {

                    Player target = Bukkit.getPlayer(args[1]);

                    if (target == null) {
                        sender.sendMessage(Locale.getMessage("invalid-player"));
                        return true;
                    }

                    sender.sendMessage(Locale.getMessage("message-sent"));
                    target.sendTitle(Locale.parsePapi(args[2],target), Locale.parsePapi(args[3],target), MessageSender.get().getInt("title.fadeIn"), MessageSender.get().getInt("title.stay"), MessageSender.get().getInt("title.fadeOut"));
                    return true;

                }

            }


        }


        if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Locale.getMessage("commands.help"));
            return true;
        }

        if (args[0].equalsIgnoreCase("message")) {
            sender.sendMessage(Locale.getMessage("commands.send-message"));
            return true;
        }

        if (args[0].equalsIgnoreCase("title")) {
            sender.sendMessage(Locale.getMessage("commands.send-title"));
            return true;
        }

        if (args[0].equalsIgnoreCase("actionbar")) {
            sender.sendMessage(Locale.getMessage("commands.send-actionbar"));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            sender.sendMessage(Locale.getMessage("commands-reload"));
            return true;
        }
























        return false;
    }






}
