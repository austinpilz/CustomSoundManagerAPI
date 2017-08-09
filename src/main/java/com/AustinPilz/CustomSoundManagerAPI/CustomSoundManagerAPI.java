package com.AustinPilz.CustomSoundManagerAPI;
import com.AustinPilz.CustomSoundManagerAPI.Manager.PlayerController;
import com.AustinPilz.CustomSoundManagerAPI.Manager.SoundController;
import com.AustinPilz.CustomSoundManagerAPI.Runnable.UpdatePlayingSounds;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomSoundManagerAPI extends JavaPlugin {
    public static final String pluginName = "Custom Sound Manager API";
    public static final String pluginVersion = "1.0";
    public static final String consolePrefix = "[CustomSoundManagerAPI] ";

    public static final Logger log = Logger.getLogger("Minecraft");
    public static CustomSoundManagerAPI instance;


    ////
    public static PlayerController playerController;
    public static SoundController soundController;

    @Override
    public void onEnable()
    {
        //Start "timer"
        long startMili = System.currentTimeMillis();

        instance  = this;

        //Setup player controller
        playerController = new PlayerController();
        soundController = new SoundController();

        //Startup complete
        log.log(Level.INFO, consolePrefix + "Startup complete - took " + (System.currentTimeMillis() - startMili) + " ms");
    }
}
