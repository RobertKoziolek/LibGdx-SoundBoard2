package com.robcio.soundboard2.loader.model;

import lombok.Getter;

import java.util.List;

@Getter
public class PacketModel {
    private String name;
    private String filter;
    private String folder;
    private String format;
    private List<VoiceModel> voiceModels;
}
