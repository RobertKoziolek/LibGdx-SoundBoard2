package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.robcio.soundboard2.enumeration.Setting;
import com.robcio.soundboard2.utils.Settings;
import com.robcio.soundboard2.utils.assets.Assets;
import com.robcio.soundboard2.utils.dispatcher.ToastDispatcher;

import static com.robcio.soundboard2.constants.Numeral.MIN_KNOB_HEIGHT;
import static com.robcio.soundboard2.constants.Numeral.MIN_KNOB_WIDTH;

public class SliderAssembler {
    private final Slider slider;
    private Setting setting;
    private String changeText;

    private SliderAssembler(final float min, final float max, final float stepSize) {
        this.slider = new Slider(min, max, stepSize, false, Assets.getSkin());
    }

    public static SliderAssembler sliderOf(final float min, final float max, final float stepSize) {
        return new SliderAssembler(min, max, stepSize);
    }

    public Slider assemble() {
        final Slider.SliderStyle style = slider.getStyle();
        style.knob.setMinWidth(MIN_KNOB_WIDTH);
        style.knob.setMinHeight(MIN_KNOB_HEIGHT);
        if (setting != null) {
            final float sizeValue = Settings.get(setting);
            slider.setValue(sizeValue);
        }
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                if (setting != null) {
                    Settings.put(setting, slider.getValue());
                }
                if (changeText != null) {
                    ToastDispatcher.showText(changeText);
                }
            }
        });
        return slider;
    }


    public SliderAssembler withSetting(final Setting setting) {
        if (!setting.getTypeClass()
                    .equals(Float.class)) {
            throw new IllegalStateException("Cannot assemble a slider with non-float setting");
        }
        this.setting = setting;
        return this;
    }

    public SliderAssembler withChangeText(final String changeText) {
        this.changeText = changeText;
        return this;
    }
}
