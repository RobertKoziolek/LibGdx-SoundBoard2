package com.robcio.soundboard2.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TextureId {

    ROBCIO_LOGO("robcio_logo"),
    SOUNDBOARD_LOGO("soundboard_logo");

    private String filename;
}

