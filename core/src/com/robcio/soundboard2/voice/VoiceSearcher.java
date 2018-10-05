package com.robcio.soundboard2.voice;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.apache.commons.lang3.StringUtils;

import static com.robcio.soundboard2.utils.helper.Maths.SEARCH_RATIO;

public class VoiceSearcher {

    private static final String POLISH_CHARS = "ĄąĆćĘęŁłŃńŚśŻżŹź";
    private static final String ENGLISH_CHARS = "AaCcEeLlNnSsZzZz";

    private String searchString;

    public boolean doesQualify(final String voiceName) {
        if (searchString.isEmpty()) {
            return true;
        }
        return FuzzySearch.tokenSetPartialRatio(searchString, translatePolishCharacters(voiceName)) > SEARCH_RATIO;
    }

    public void setSearchString(final String searchString) {
        this.searchString = translatePolishCharacters(searchString);
    }

    private String translatePolishCharacters(final String string) {
        return StringUtils.replaceChars(string, POLISH_CHARS, ENGLISH_CHARS);
    }
}
