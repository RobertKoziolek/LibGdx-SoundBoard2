package com.robcio.soundboard2.utils.dispatcher;

import com.robcio.soundboard2.utils.Enablable;

public interface ShareDispatcher extends Enablable {
    void share(String filePath);
}
