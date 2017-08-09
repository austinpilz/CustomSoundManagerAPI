package com.AustinPilz.CustomSoundManagerAPI.Runnable;

import com.AustinPilz.CustomSoundManagerAPI.Components.CustomSound;
import com.AustinPilz.CustomSoundManagerAPI.Manager.SoundController;

import java.util.Iterator;
import java.util.Map;

public class UpdatePlayingSounds implements Runnable {

    private CustomSound customSound;

    public UpdatePlayingSounds(CustomSound sound)
    {
        customSound = sound;
    }

    @Override
    public void run()
    {
       customSound.secondPass();
    }
}
