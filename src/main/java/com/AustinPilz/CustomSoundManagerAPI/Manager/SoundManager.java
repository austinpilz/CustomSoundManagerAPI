package com.AustinPilz.CustomSoundManagerAPI.Manager;

import com.AustinPilz.CustomSoundManagerAPI.Components.CustomSound;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Iterator;

public class SoundManager {

    private Player player;
    private HashSet<CustomSound> currentlyPlayingSounds;

    public SoundManager(Player p)
    {
        player = p;
        currentlyPlayingSounds = new HashSet<>();
    }

    /**
     * Plays a custom sound
     * @param location Location at which the sound will be played
     * @param resourcePackValue Name of the custom sound in the resource pack sounds.json
     * @param lengthInSeconds Length in seconds of the sound
     * @param volume Volume of the sound
     * @param interruptCurrent Interrupt currently playing sound, if any
     * @param overlayCurrent Overlay sound on top of any currently playing sounds
     */
    public void playCustomSound(Location location, String resourcePackValue, int lengthInSeconds, int volume, boolean interruptCurrent, boolean overlayCurrent)
    {
        //Check to see if they have another sound already playing
        if (!isSoundCurrentlyPlaying() || (isSoundCurrentlyPlaying() && interruptCurrent) || (isSoundCurrentlyPlaying() && overlayCurrent))
        {
            //Interrupt current sounds if there is one and we're told to do so
            if (isSoundCurrentlyPlaying() && interruptCurrent)
            {
                stopAllSounds();
            }

            //There's no sound playing OR there's a sound already playing, but we were told to interrupt the sound.
            CustomSound sound = new CustomSound(this, player, location, resourcePackValue, lengthInSeconds, volume);
            sound.play();
        }
    }

    /**
     * Returns if the player currently has a sound playing
     * @return
     */
    public boolean isSoundCurrentlyPlaying()
    {
        if (currentlyPlayingSounds.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Stops all currently playing sounds for the player
     */
    public void stopAllSounds()
    {
        Iterator it = currentlyPlayingSounds.iterator();
        while (it.hasNext())
        {
            CustomSound sound = (CustomSound) it.next();
            it.remove();
            sound.stop();
        }
    }

    /**
     * Adds currently playing sound to the players list
     * @param sound
     */
    public void addCurrentlyPlayingSound(CustomSound sound)
    {
        currentlyPlayingSounds.add(sound);
    }

    /**
     * Removes sound from players list
     * @param sound
     */
    public void removeCurrentlyPlayingSound(CustomSound sound)
    {
        currentlyPlayingSounds.remove(sound);
    }
}
