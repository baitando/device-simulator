package com.baitando.iot.devicesimulator.template.service;

import com.baitando.iot.devicesimulator.template.service.data.TemplateData;
import com.baitando.iot.devicesimulator.template.service.data.TemplateDefinition;

import java.util.List;

/**
 * Manage templates.
 */
public interface TemplateService {

    TemplateDefinition readTemplateDefinitionFromFile(String templateDefinitionFilePath) throws TemplateDefinitionReadingException;

    List<TemplateData> readTemplateDataFromFile(String templateDataFilePath) throws TemplateDataReadingException;

    /**
     * Process a template, i.e. take the template definition and the data and render the result.
     *
     * @param templateDefinition Definition of the template.
     * @param templateData       Data to render.
     * @return The rendered template.
     * @throws TemplateProcessingException There was a problem while processing the template.
     */
    String processTemplate(TemplateDefinition templateDefinition, TemplateData templateData) throws TemplateProcessingException;
}
