package com.AustinPilz.CustomSoundManagerAPI.Manager;

import com.AustinPilz.CustomSoundManagerAPI.Manager.SoundManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerController {
    private ConcurrentHashMap<Player, SoundManager> players;

    public PlayerController()
    {
        players = new ConcurrentHashMap<>();
    }

    /**
     * Returns the sound manager for the provided player, creating one if one does not exist already
     * @param player
     * @return
     */
    public SoundManager getPlayerSoundManager(Player player)
    {
        if (!doesPlayerHaveExistingSoundManager(player))
        {
            createPlayerSoundManager(player);
        }

        return players.get(player);
    }

    /**
     * Returns if the player has a valid sound manager in memory
     * @param player
     * @return
     */
    private boolean doesPlayerHaveExistingSoundManager(Player player)
    {
        return players.containsKey(player);
    }

    /**
     * Creates a new sound manager for the supplied player
     * @param player
     */
    private void createPlayerSoundManager(Player player)
    {
        players.put(player, new SoundManager(player));
    }
}
