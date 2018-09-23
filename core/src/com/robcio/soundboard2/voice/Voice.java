package com.robcio.soundboard2.voice;

import com.badlogic.gdx.audio.Sound;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.loader.model.VoiceModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Voice {
    private String name;
    private Sound sound;
    private Integer filter;

    public static Voice of(final FilterMap filterMap, final VoiceModel model, final Sound sound) {
        final Integer filter = filterMap.getFilter(model.getFilter());
        return new Voice(model.getName(), sound, filter);
    }
}
