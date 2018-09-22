package com.robcio.soundboard2.voice;

import com.badlogic.gdx.audio.Sound;
import com.robcio.soundboard2.loader.model.VoiceModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Voice {
    private String name;
    private Sound sound;

    public static Voice of(final VoiceModel model, final Sound sound) {
        return new Voice(model.getName(), sound);
    }
}
