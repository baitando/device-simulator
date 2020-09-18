package com.baitando.iot.devicesimulator.template.service;

/**
 * Indicates that there was an issue while reading the template definition from a file.
 */
public class TemplateDefinitionReadingException extends Exception {

    /**
     * Create an instance of the exception with the given parameters.
     *
     * @param templateDefinitionFilePath Path to the template definition file.
     */
    public TemplateDefinitionReadingException(String templateDefinitionFilePath) {
        super(String.format("Error reading template from file [templateDefinitionFilePath=%s]", templateDefinitionFilePath));
    }
}
