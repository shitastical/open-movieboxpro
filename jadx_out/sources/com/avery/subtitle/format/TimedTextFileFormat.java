package com.avery.subtitle.format;

import com.avery.subtitle.exception.FatalParsingException;
import com.avery.subtitle.model.TimedTextObject;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public interface TimedTextFileFormat {
    TimedTextObject parseFile(String str, InputStream inputStream) throws IOException, FatalParsingException;

    Object toFile(TimedTextObject timedTextObject);
}
