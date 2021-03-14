package me.prankster.PROJECTTPA.commands;

import me.prankster.PROJECTTPA.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaDeny implements CommandExecutor {
    private Main plugin;

    public TpaDeny(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpdeny").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player plr = null;

        if (commandSender instanceof Player) {
            plr = (Player)commandSender;
        }

        if (command.getName().equalsIgnoreCase("tpdeny")) {
            if (!(plr == null)) {
                if (!(Main.checkForRequest(plr.getName()))) {
                    plr.sendMessage(ChatColor.RED + "You have no existing TPA requests!");

                    return false;
                }

                Main.killRequest(plr.getName());


            } else {
                plr.sendMessage(ChatColor.RED + "The console cannot execute this command!");
            }

            return true;
        }

        return false;
    }
}
