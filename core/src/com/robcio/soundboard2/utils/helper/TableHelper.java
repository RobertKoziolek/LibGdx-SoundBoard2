package com.robcio.soundboard2.utils.helper;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static com.robcio.soundboard2.constants.Strings.LIST_IS_EMPTY;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TableHelper {
    public static void markIfEmpty(final Table table) {
        if (table.getCells().size == 0) {
            table.add(LIST_IS_EMPTY);
        }
    }
}
