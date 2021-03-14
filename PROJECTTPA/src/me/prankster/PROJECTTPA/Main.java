package me.prankster.PROJECTTPA;

import me.prankster.PROJECTTPA.commands.Tpa;
import me.prankster.PROJECTTPA.commands.TpaAccept;
import me.prankster.PROJECTTPA.commands.TpaDeny;
import me.prankster.PROJECTTPA.data.CurrentRequests;
import me.prankster.PROJECTTPA.other.GetPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Map;

public class Main extends JavaPlugin {

    BukkitScheduler scheduler = Bukkit.getScheduler();

    static Map<String, String> Requests = CurrentRequests.Requests;

    public JavaPlugin Main() {
        return (JavaPlugin)this;
    }

    public static boolean checkForRequest(String recipientName) {
        if (Requests.containsKey(recipientName)) {
            return true;
        }

        return false;
    }

    public static void acceptRequest(String key) {
        if (Requests.containsKey(key)) {
            Player winner = Bukkit.getServer().getPlayer(Requests.get(key));
            Player recipient = Bukkit.getServer().getPlayer(key);

            if (!(winner == null)) {
                winner.sendMessage(ChatColor.YELLOW + "Teleporting in 5 seconds...");
            }

            if (!(recipient == null)) {
                recipient.sendMessage(ChatColor.YELLOW + "Teleporting " + Requests.get(key) + " to you in 5 seconds...");

                Bukkit.getScheduler().runTaskLater(GetPlugin.returnPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        winner.teleport(recipient);
                    }
                }, 100L);
            }

            Requests.remove(key);
        }
    }

    public static void killRequest(String key) {
        if (Requests.containsKey(key)) {
            Player loser = Bukkit.getServer().getPlayer(Requests.get(key));
                if (!(loser == null)) {
                    loser.sendMessage(ChatColor.YELLOW + "Your teleportation request was declined.");
                }

                Requests.remove(key);

        }
    }

    public static void sendRequest(Player sender, Player recipient) {
        sender.sendMessage(ChatColor.YELLOW + "Sending teleportation request to " + recipient.getName() + ".");

        recipient.sendMessage(ChatColor.YELLOW + sender.getName() + " is sending teleportation request to you. Use /tpaccept to accept, /tpdeny to deny.");

        Requests.put(recipient.getName(),sender.getName());
    }

    @Override

    public void onEnable() {
        registerCommands();
    }

    void registerCommands() {
        new Tpa(this);
        new TpaAccept(this);
        new TpaDeny(this);
    }
}
