package com.example.alfianhadipratama.sistemparkir.Scan;

/**
 * Created by Alfian Hadi Pratama on 29/11/2016.
 */

public final class IntentResult {

    private final String contents;
    private final String formatName;

    IntentResult(String contents, String formatName) {
        this.contents = contents;
        this.formatName = formatName;
    }

    public String getContents() {
        return contents;
    }

    public String getFormatName() {
        return formatName;
    }
}
