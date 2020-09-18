package com.baitando.iot.devicesimulator.template.service;

/**
 * Indicates that there was an issue while reading the template data from a file.
 */
public class TemplateDataReadingException extends Exception {

    /**
     * Create an instance of the exception with the given parameters.
     *
     * @param templateDataFilePath Path to the template data file.
     */
    public TemplateDataReadingException(String templateDataFilePath) {
        super(String.format("Error reading template from file [templateDataFilePath=%s]", templateDataFilePath));
    }
}
