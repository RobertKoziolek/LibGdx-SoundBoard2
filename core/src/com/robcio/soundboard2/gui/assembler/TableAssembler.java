package com.robcio.soundboard2.gui.assembler;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.robcio.soundboard2.utils.assets.Assets;

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

    public static TableAssembler table(final Actor actor) {
        final TableAssembler tableAssembler = table();
        tableAssembler.table.add(actor);
        return tableAssembler;
    }

    public static TableAssembler tableOf(final Actor... actors) {
        final TableAssembler tableAssembler = table();
        for (final Actor actor : actors) {
            tableAssembler.table.add(actor)
                                .width(actor.getWidth())
                                .height(actor.getHeight());
        }
        return tableAssembler;
    }

    public static TableAssembler equalizedColumns(final float unitHeight, final Table... tables) {
        final TableAssembler tableAssembler = table();
        equalizeColumns(unitHeight, tables);
        tableAssembler.table.add(tables);
        return tableAssembler;
    }

    public Table assemble() {
        table.align(align);
        table.setFillParent(fillParent);
        return table;
    }

    public TableAssembler alignTop() {
        align(Align.top);
        return this;
    }

    public TableAssembler align(final int align) {
        this.align = align;
        return this;
    }

    public TableAssembler fillParent() {
        this.fillParent = true;
        return this;
    }

    private static void equalizeColumns(final float unitHeight, final Table... tables) {
        int maxSize = 0;
        for (final Table table : tables) {
            maxSize = Math.max(maxSize, table.getCells().size);
        }
        for (final Table table : tables) {
            final int diff = maxSize - table.getCells().size;
            table.add()
                 .height(diff * unitHeight);
        }
    }
}
