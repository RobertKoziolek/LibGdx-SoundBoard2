package com.robcio.soundboard2.loader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.robcio.soundboard2.loader.model.PacketModel;
import com.robcio.soundboard2.loader.model.PacketsInformationModel;
import com.robcio.soundboard2.loader.model.VoiceModel;
import com.robcio.soundboard2.utils.Assets;

import java.util.ArrayList;
import java.util.List;

public class JsonVoiceLoader {

    public List<VoiceModel> load() {
        final List<VoiceModel> voiceModelList = new ArrayList<>();
        final Json json = new Json();
        final PacketsInformationModel packetManager = json
                .fromJson(PacketsInformationModel.class, Gdx.files.internal("json/packets_information.json"));
        for (final String packetFile : packetManager.getPackets()) {
            final String packetPath = "json/" + packetFile + ".json";
            final PacketModel packetModel = json.fromJson(PacketModel.class, Gdx.files.internal(packetPath));
            for (final VoiceModel voiceModel : packetModel.getVoiceModels()) {
                voiceModel.setFile(packetModel.getFolder(), packetModel.getFormat());
                Assets.loadVoice(voiceModel);
                voiceModelList.add(voiceModel);
            }
        }
        return voiceModelList;
    }
}
