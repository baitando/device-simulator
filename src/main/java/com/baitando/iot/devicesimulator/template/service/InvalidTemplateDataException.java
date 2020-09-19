package com.baitando.iot.devicesimulator.template.service;

import java.util.List;

/**
 * Indicates that the placeholders defined in the template data do not match the ones in the template definition.
 */
public class InvalidTemplateDataException extends RuntimeException {

    /**
     * Create an instance of the exception with the given parameters.
     *
     * @param templateDefinitionPlaceholders Placeholders from the template definition.
     * @param templateDataPlaceholders Placeholders from the template data.
     */
    public InvalidTemplateDataException(List<String> templateDefinitionPlaceholders, List<String> templateDataPlaceholders) {
        super(String.format("Placeholders from template data do not match the ones from the template definition [templateDefinitionPlaceholders=%s, templateDataPlaceholders=%s]", templateDefinitionPlaceholders, templateDataPlaceholders));
    }
}
