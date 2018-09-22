package com.robcio.soundboard2.loader;

import com.badlogic.gdx.audio.Sound;
import com.robcio.soundboard2.loader.model.VoiceModel;
import com.robcio.soundboard2.utils.Assets;
import com.robcio.soundboard2.voice.Voice;

import java.util.LinkedList;
import java.util.List;

public class VoiceLoader {

    private List<VoiceModel> voiceModelList;

    public void load() {
        voiceModelList = new JsonVoiceLoader().load();
    }

    public List<Voice> getVoiceList() {
        if (voiceModelList == null || voiceModelList.isEmpty()){
            throw new IllegalStateException("Cannot get voices without loading them first");
        }
        final List<Voice> voiceList = new LinkedList<>();
        for (final VoiceModel model : voiceModelList){
            final Sound sound = Assets.getSound(model.getFile());
            voiceList.add(Voice.of(model, sound));
        }
        return voiceList;
    }
}
