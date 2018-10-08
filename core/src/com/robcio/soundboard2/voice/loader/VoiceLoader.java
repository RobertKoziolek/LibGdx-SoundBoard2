package com.robcio.soundboard2.voice.loader;

import com.badlogic.gdx.audio.Sound;
import com.robcio.soundboard2.filter.FilterMap;
import com.robcio.soundboard2.utils.assets.Assets;
import com.robcio.soundboard2.voice.Voice;
import com.robcio.soundboard2.voice.loader.model.VoiceModel;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VoiceLoader {

    private List<VoiceModel> voiceModelList;
    @Getter
    private Map<String, List<Voice>> suiteMap = new HashMap<>();
    @Getter
    final private FilterMap filterMap = new FilterMap();

    public void load() {
        voiceModelList = new JsonVoiceLoader().load(filterMap);
    }

    public List<Voice> getVoiceList() {
        if (CollectionUtils.isEmpty(voiceModelList)) {
            throw new IllegalStateException("Cannot get voices without loading them first");
        }
        final List<Voice> voiceList = new LinkedList<>();
        for (final VoiceModel model : voiceModelList) {
            final Sound sound = Assets.getSound(model.getFile());
            final Voice voice = Voice.of(filterMap, model, sound);
            voiceList.add(voice);
            if (CollectionUtils.isNotEmpty(model.getSuite())) {
                for (final String suite : model.getSuite()) {
                    if (!suiteMap.containsKey(suite)) {
                        suiteMap.put(suite, new LinkedList<Voice>());
                    }
                    suiteMap.get(suite)
                            .add(voice);
                }
            }
        }
        return voiceList;
    }

    public List<Voice> getLoadedVoices() {
        final List<Voice> voiceList = new LinkedList<>();
        for (final VoiceModel model : voiceModelList) {
            try {
                final Sound sound = Assets.getSound(model.getFile());
                voiceList.add(Voice.of(filterMap, model, sound));
            } catch (final Exception exception) {
                break;
//            }
//                catch (final GdxRuntimeException exception){
//                break;
            }
        }
        return voiceList;
    }
}
