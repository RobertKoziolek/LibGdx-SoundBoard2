package com.robcio.soundboard2.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SoundId {
    SOUND_BASS_1("sound/bass/bassslap01.ogg"),
    SOUND_BASS_2("sound/bass/bassslap02.ogg"),
    SOUND_CELLO_1("sound/cello/cello01.ogg"),
    SOUND_ORGAN_1("sound/organ/church_organ01.ogg"),
    SOUND_ORGAN_2("sound/organ/church_organ02.ogg"),
    SOUND_ORGAN_3("sound/organ/church_organ03.ogg"),
    SOUND_ORGAN_4("sound/organ/church_organ04.ogg");

    private String filename;
}

