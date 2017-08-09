package com.AustinPilz.CustomSoundManagerAPI.Components;

import com.AustinPilz.CustomSoundManagerAPI.CustomSoundManagerAPI;
import com.AustinPilz.CustomSoundManagerAPI.Manager.SoundManager;
import com.AustinPilz.CustomSoundManagerAPI.Runnable.UpdatePlayingSounds;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CustomSound {

    private SoundManager soundManager;
    private Player player;
    private Location location;
    private String resourcePackValue;
    private int timeLeftSeconds;
    private int volume;
    private int secondPassTask;
    private boolean isPlaying;

    public CustomSound(SoundManager sm, Player p, Location l, String r, int length, int v)
    {
        soundManager = sm;
        player = p;
        location = l;
        resourcePackValue = r;
        timeLeftSeconds = length;
        volume = v;
        isPlaying = false;
        secondPassTask = -1;
    }

    /**
     * Returns the player
     * @return
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Begins playback of the sound for the player
     */
    public void play()
    {
        if (!isPlaying())
        {
            isPlaying = true; //Mark as playing
            player.playSound(location, resourcePackValue, volume, 1);

            //Add the sound to the player's current sound list
            soundManager.addCurrentlyPlayingSound(this);

            //Add the sound to the global currently playing sound list
            CustomSoundManagerAPI.soundController.addCurrentlyPlayingSound(this);

            //Schedule task
            secondPassTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(CustomSoundManagerAPI.instance, new UpdatePlayingSounds(this), 20, 20);
        }
    }

    /**
     * Stops the current sound
     */
    public void stop()
    {
        if (isPlaying)
        {
            player.stopSound(resourcePackValue);
            isPlaying = false;

            //Remove from lists
            soundManager.removeCurrentlyPlayingSound(this);
            CustomSoundManagerAPI.soundController.removeCurrentlyPlayingSound(this);

            //Cancel task
            Bukkit.getScheduler().cancelTask(secondPassTask);
        }
    }

    /**
     * Returns if the sound is currently being played for the player
     * @return
     */
    public boolean isPlaying()
    {
        return isPlaying;
    }

    /**
     * Decrements the time left of the sound
     */
    public void secondPass()
    {
        if (isPlaying())
        {
            timeLeftSeconds--;

            if (timeLeftSeconds <= 0)
            {
                //The sound has finished playing
                stop();
            }
        }
    }
}
