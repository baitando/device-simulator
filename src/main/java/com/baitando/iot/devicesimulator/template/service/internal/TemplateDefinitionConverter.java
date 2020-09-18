package com.baitando.iot.devicesimulator.template.service.internal;

import com.baitando.iot.devicesimulator.template.service.data.TemplateDefinition;

import java.util.List;

public class TemplateDefinitionConverter {

    static TemplateDefinition convertToTemplateDefinition(String name, String template, List<String> placeholders) {
        TemplateDefinition templateDefinition = new TemplateDefinition(name);
        templateDefinition.setTemplate(template);
        placeholders.forEach(templateDefinition::addPlaceholder);

        return templateDefinition;
    }
}
