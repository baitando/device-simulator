package com.baitando.iot.devicesimulator.template.service.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TemplateDefinition {

    private final String name;
    private String template;
    private final Map<String, PlaceholderDefinition> placeholders;

    public TemplateDefinition(String name) {
        this.name = name;
        placeholders = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public List<PlaceholderDefinition> getPlaceholders() {
        return new ArrayList<>(placeholders.values());
    }

    public void addPlaceholder(String placeholder) {
        if (placeholders.containsKey(placeholder)) {
            placeholders.get(placeholder).incrementOccurences();
        } else {
            placeholders.put(placeholder, new PlaceholderDefinition(placeholder));
        }
    }

    private boolean placeholderExists(List<PlaceholderDefinition> placeholderDefinitions, String name) {
        boolean exists = false;
        for (PlaceholderDefinition placeholderDefinition : placeholderDefinitions) {
            if (placeholderDefinition.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
}
