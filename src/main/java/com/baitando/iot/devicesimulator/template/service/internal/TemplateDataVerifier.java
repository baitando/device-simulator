package com.baitando.iot.devicesimulator.template.service.internal;

import com.baitando.iot.devicesimulator.template.service.InvalidTemplateDataException;
import com.baitando.iot.devicesimulator.template.service.data.PlaceholderDefinition;
import com.baitando.iot.devicesimulator.template.service.data.TemplateData;
import com.baitando.iot.devicesimulator.template.service.data.TemplateDefinition;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class TemplateDataVerifier {

    private TemplateDataVerifier() {
        // Prevent instantiation
    }

    static void verifyPlaceholders(TemplateDefinition templateDefinition, TemplateData templateData) {
        List<String> templateDefinitionPlaceholders = extractPlaceholdersFromTemplateDefinition(templateDefinition);
        List<String> templateDataPlaceholders = extractPlaceholdersFromTemplateData(templateData);

        if (!CollectionUtils.isEqualCollection(templateDataPlaceholders, templateDefinitionPlaceholders)) {
            throw new InvalidTemplateDataException(templateDefinitionPlaceholders, templateDataPlaceholders);
        }
    }

    private static List<String> extractPlaceholdersFromTemplateDefinition(TemplateDefinition templateDefinition) {
        return templateDefinition
                .getPlaceholders()
                .stream()
                .map(PlaceholderDefinition::getName)
                .collect(Collectors.toList());
    }

    private static List<String> extractPlaceholdersFromTemplateData(TemplateData templateData) {
        return new ArrayList<>(templateData.getData().keySet());
    }
}
