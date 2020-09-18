package com.baitando.iot.devicesimulator.template.service.data;

public class PlaceholderDefinition {

    private final String name;
    private int occurrences = 1;

    public PlaceholderDefinition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void incrementOccurences() {
        occurrences++;
    }

    public int getOccurrences() {
        return occurrences;
    }
}
