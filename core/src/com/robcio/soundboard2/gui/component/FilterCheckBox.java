package com.robcio.soundboard2.gui.component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.robcio.soundboard2.utils.Assets;
import lombok.Getter;

public class FilterCheckBox extends CheckBox {

    @Getter
    final private int filterBit;

    public FilterCheckBox(final String text, final int filterBit, final FilterBoxCommand doubleTapCommand) {
        super(text, Assets.getSkin());
        this.filterBit = filterBit;

        final FilterCheckBox filterCheckBox = this;
        setChecked(true);
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (getTapCount() == 2) {
                    doubleTapCommand.execute(filterCheckBox);
                }
            }
        });
    }

    public interface FilterBoxCommand{
        void execute(FilterCheckBox filterCheckBox);
    }
}
