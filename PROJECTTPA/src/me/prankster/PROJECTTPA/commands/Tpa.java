package me.prankster.PROJECTTPA.commands;

import me.prankster.PROJECTTPA.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.Locale;


public class Tpa implements CommandExecutor {
    private Main plugin;

    public Tpa(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpa").setExecutor(this);
    }

    @Override

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player plr = null;

        if (sender instanceof Player) {
            plr = (Player)sender;
        }

        if (cmd.getName().equalsIgnoreCase("tpa")) {
            if (!(plr == null)) {
                if (args.length > 0) {
                    final Player target = plugin.getServer().getPlayer(args[0]);

                    if (target == null) {
                        plr.sendMessage(ChatColor.RED + "That player is offline or could not be found. Check if there's any mispelling or capitalisation");

                        return false;
                    }

                    if (target == plr) {
                        plr.sendMessage(ChatColor.RED + "You cannot sent a TPA to yourself!");
                        return false;
                    }

                    if (Main.checkForRequest(target.getName())) {
                        plr.sendMessage(ChatColor.RED + "You have already sent a teleportation request to that player!");

                        return false;
                    }

                    Main.sendRequest(plr, target);
                } else {
                    sender.sendMessage(ChatColor.RESET + "Send teleportation request to someone");
                    sender.sendMessage(ChatColor.YELLOW + "Usage: /tpa <Player Name>");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "The console cannot perform this command!");
            }

            return true;
        }

        return false;
    };
}
