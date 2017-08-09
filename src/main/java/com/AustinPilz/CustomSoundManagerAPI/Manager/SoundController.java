package com.AustinPilz.CustomSoundManagerAPI.Manager;

import com.AustinPilz.CustomSoundManagerAPI.Components.CustomSound;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class SoundController {
    ConcurrentHashMap<Player, CustomSound> currentlyPlayingSounds;

    public SoundController()
    {
        currentlyPlayingSounds = new ConcurrentHashMap<>();
    }

    /**
     * Adds sound that is currently playing
     * @param sound
     */
    public void addCurrentlyPlayingSound(CustomSound sound)
    {
        currentlyPlayingSounds.put(sound.getPlayer(), sound);
    }

    /**
     * Removes sound that is currently playing
     * @param sound
     */
    public void removeCurrentlyPlayingSound(CustomSound sound)
    {
        currentlyPlayingSounds.remove(sound);
    }

    /**
     * Returns all of the currently playing sounds
     * @return
     */
    public ConcurrentHashMap<Player, CustomSound> getCurrentlyPlayingSounds()
    {
        return currentlyPlayingSounds;
    }
}
