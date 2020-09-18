package com.baitando.iot.devicesimulator.template.service;

/**
 * Indicates that there was an issue while processing the template.
 */
public class TemplateProcessingException extends RuntimeException {

    /**
     * Create an instance of the exception with the given parameters.
     *
     * @param name Name of the template, which does not exist.
     */
    public TemplateProcessingException(String name) {
        super(String.format("Template not found [templateName=%s]", name));
    }
}
