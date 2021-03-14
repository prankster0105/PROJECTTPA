package me.prankster.PROJECTTPA.commands;

import me.prankster.PROJECTTPA.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaAccept implements CommandExecutor {
    private Main plugin;

    public TpaAccept(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpaccept").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player plr = null;

        if (sender instanceof Player) {
            plr = (Player)sender;
        }

        if (cmd.getName().equalsIgnoreCase("tpaccept")) {
            if (!(plr == null)) {
                if (!(Main.checkForRequest(plr.getName()))) {
                    plr.sendMessage(ChatColor.RED + "You have no existing TPA requests!");
                    return false;
                }

                Main.acceptRequest(plr.getName());
            } else {
                sender.sendMessage(ChatColor.RED + "The console cannot execute this command!");
                return false;
            }

            return true;
        }

        return false;
    }
}
