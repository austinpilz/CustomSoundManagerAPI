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
     */
    public void playCustomSound(Location location, String resourcePackValue, int lengthInSeconds, int volume)
    {
        playCustomSound(location, resourcePackValue, lengthInSeconds, volume, false, true, 0);
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
        playCustomSound(location, resourcePackValue, lengthInSeconds, volume, interruptCurrent, overlayCurrent, 0);
    }

    /**
     * Plays a custom sound
     * @param location Location at which the sound will be played
     * @param resourcePackValue Name of the custom sound in the resource pack sounds.json
     * @param lengthInSeconds Length in seconds of the sound
     * @param volume Volume of the sound
     * @param interruptCurrent Interrupt currently playing sound, if any
     * @param overlayCurrent Overlay sound on top of any currently playing sounds
     * @param transitionSeconds The number of seconds to transition currently playing sounds by
     */
    public void playCustomSound(Location location, String resourcePackValue, int lengthInSeconds, int volume, boolean interruptCurrent, boolean overlayCurrent, int transitionSeconds)
    {
        //Check to see if they have another sound already playing
        if (!areAnySoundsPlaying() || (areAnySoundsPlaying() && interruptCurrent) || (areAnySoundsPlaying() && overlayCurrent))
        {
            //Interrupt current sounds if there is one and we're told to do so
            if (areAnySoundsPlaying() && interruptCurrent)
            {
                if (transitionSeconds > 0)
                {
                    setCurrentlyPlayingSecondsLeft(transitionSeconds);
                }
                else
                {
                    stopAllSounds();
                }
            }

            //There's no sound playing OR there's a sound already playing, but we were told to interrupt the sound.
            CustomSound sound = new CustomSound(this, player, location, resourcePackValue, lengthInSeconds, volume);
            sound.play();
        }
    }

    /**
     * Returns if the resouce pack value sound is currently playing for the player
     * @param resourcePackValue
     * @return
     */
    public boolean isSoundCurrentlyPlaying(String resourcePackValue)
    {
        Iterator it = currentlyPlayingSounds.iterator();
        while (it.hasNext())
        {
            CustomSound sound = (CustomSound) it.next();
            if (sound.getResourcePackValue().equals(resourcePackValue))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns if the player currently has a sound playing
     * @return
     */
    public boolean areAnySoundsPlaying()
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

    /**
     * Sets the seconds left for all currently playing sounds
     * @param secondsLeft
     */
    private void setCurrentlyPlayingSecondsLeft(int secondsLeft)
    {
        Iterator it = currentlyPlayingSounds.iterator();
        while (it.hasNext())
        {
            CustomSound sound = (CustomSound) it.next();

            if (sound.getTimeLeftSeconds() > secondsLeft)
            {
                sound.setTimeLeftSeconds(secondsLeft);
            }
        }
    }
}
