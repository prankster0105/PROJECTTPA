package me.prankster.PROJECTTPA.other;

import me.prankster.PROJECTTPA.Main;
import org.bukkit.plugin.java.JavaPlugin;

public class GetPlugin {
    private Main plugin;

    public static JavaPlugin returnPlugin() {
        return new Main();
    }
}
