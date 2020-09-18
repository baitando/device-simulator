package com.baitando.iot.devicesimulator.template.service.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderExtractor {

    private static final String EXTRACTOR_REGEX = ".*\\$\\{(.*?)\\}.*";

    static List<String> extractPlaceholders(String template) {
        List<String> placeholders = new ArrayList<>();
        Pattern pattern = Pattern.compile(EXTRACTOR_REGEX);
        Matcher matcher = pattern.matcher(template);

        while (matcher.find()) {
            placeholders.add(matcher.group(1));
        }
        return placeholders;
    }
}
