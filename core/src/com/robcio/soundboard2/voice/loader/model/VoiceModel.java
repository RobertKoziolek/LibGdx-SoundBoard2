package com.robcio.soundboard2.voice.loader.model;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class VoiceModel {
    private String name;
    private String file;
    private List<String> filter;
    private List<String> suite;

    public void setFile(final String folder, final String format) {
        this.file = "sound/" + folder + "/" + file + format;
    }
}
