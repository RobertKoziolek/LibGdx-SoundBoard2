package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.robcio.soundboard2.utils.Assets;

import static com.robcio.soundboard2.gui.constants.Numeral.OPTION_HEIGHT;

public class TableAssembler {

    private final Table table;
    private int align = Align.center;
    private boolean fillParent = false;

    private TableAssembler(final String text) {
        table = new Table(Assets.getSkin());
        if (text != null) {
            table.add(text)
                 .row();
        }
    }

    public static TableAssembler table() {
        return new TableAssembler(null);
    }

    public static TableAssembler table(final String text) {
        return new TableAssembler(text);
    }

    public static TableAssembler tableOf(final Actor... buttons) {
        final TableAssembler tableAssembler = table();
        for (final Actor button : buttons) {
            tableAssembler.table.add(button)
                                .width(button.getWidth())
                                .height(button.getHeight());
        }
        return tableAssembler;
    }

    public static TableAssembler equalizedDoubleColumn(final Table firstColumn, final Table secondColumn) {
        final TableAssembler tableAssembler = TableAssembler.table();
        equalizeFilterColumns(firstColumn, secondColumn);
        tableAssembler.table.add(firstColumn, secondColumn);
        return tableAssembler;
    }

    public Table assemble() {
        table.align(align);
        table.setFillParent(fillParent);
        return table;
    }

    public TableAssembler align(final int align) {
        this.align = align;
        return this;
    }

    public TableAssembler fillParent() {
        this.fillParent = true;
        return this;
    }

    private static void equalizeFilterColumns(final Table firstColumn, final Table secondColumn) {
        final int firstColumnSize = firstColumn.getCells().size;
        final int secondColumnSize = secondColumn.getCells().size;
        final int diff = Math.abs(secondColumnSize - firstColumnSize);

        Table shorterColumn = firstColumn;
        if (firstColumnSize > secondColumnSize) {
            shorterColumn = secondColumn;
        }
        shorterColumn.add()
                     .height(diff * OPTION_HEIGHT);
    }
}
