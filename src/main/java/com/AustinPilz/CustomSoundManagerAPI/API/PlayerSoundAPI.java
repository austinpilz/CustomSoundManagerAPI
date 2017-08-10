package com.AustinPilz.CustomSoundManagerAPI.API;

import com.AustinPilz.CustomSoundManagerAPI.CustomSoundManagerAPI;
import com.AustinPilz.CustomSoundManagerAPI.Manager.SoundManager;
import org.bukkit.entity.Player;

public class PlayerSoundAPI {

    /**
     * Returns if at least one sound is currently playing for the supplied player
     * @param player
     * @return
     */
    public static boolean isSoundCurrentlyPlayingForPlayer(Player player)
    {
        return CustomSoundManagerAPI.playerController.getPlayerSoundManager(player).areAnySoundsPlaying();
    }

    public static SoundManager getPlayerSoundManager(Player player)
    {
        return CustomSoundManagerAPI.playerController.getPlayerSoundManager(player);
    }
}
