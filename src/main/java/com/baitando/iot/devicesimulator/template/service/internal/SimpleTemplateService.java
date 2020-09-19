package com.baitando.iot.devicesimulator.template.service.internal;

import com.baitando.iot.devicesimulator.template.service.TemplateDataReadingException;
import com.baitando.iot.devicesimulator.template.service.TemplateDefinitionReadingException;
import com.baitando.iot.devicesimulator.template.service.TemplateProcessingException;
import com.baitando.iot.devicesimulator.template.service.TemplateService;
import com.baitando.iot.devicesimulator.template.service.data.PlaceholderDefinition;
import com.baitando.iot.devicesimulator.template.service.data.TemplateData;
import com.baitando.iot.devicesimulator.template.service.data.TemplateDefinition;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.baitando.iot.devicesimulator.template.service.internal.PlaceholderExtractor.extractPlaceholders;
import static com.baitando.iot.devicesimulator.template.service.internal.TemplateDataConverter.convertToTemplateData;
import static com.baitando.iot.devicesimulator.template.service.internal.TemplateDataVerifier.verifyPlaceholders;
import static com.baitando.iot.devicesimulator.template.service.internal.TemplateDefinitionConverter.convertToTemplateDefinition;

/**
 * Default implementation of a {@link TemplateService}.
 */
@Service
public class SimpleTemplateService implements TemplateService {

    private final Logger logger = LoggerFactory.getLogger(SimpleTemplateService.class);

    @Override
    public TemplateDefinition readTemplateDefinitionFromFile(String templateDefinitionFilePath) throws TemplateDefinitionReadingException {
        logger.info("Reading scenario file [templateDefinitionFilePath={}]", templateDefinitionFilePath);

        try {
            return readTemplateFromFileInternal(templateDefinitionFilePath);
        } catch (IOException e) {
            throw new TemplateDefinitionReadingException(templateDefinitionFilePath);
        }
    }

    private TemplateDefinition readTemplateFromFileInternal(String templateDefinitionFilePath) throws IOException {
        Path path = Paths.get(templateDefinitionFilePath);
        String template = FileUtils.readFileToString(path.toFile(), "UTF-8");

        TemplateDefinition templateDefinition = convertToTemplateDefinition(
                FilenameUtils.removeExtension(path.getFileName().toString()),
                template,
                extractPlaceholders(template));

        logger.debug("Template definition read from file [templateDefinitionFilePath={}, templateName={}, placeholderCount={}]",
                templateDefinitionFilePath, templateDefinition.getName(), templateDefinition.getPlaceholders().size());

        return templateDefinition;
    }


    @Override
    public List<TemplateData> readTemplateDataFromFile(String templateDataFilePath) throws TemplateDataReadingException {
        try {
            return readTemplateDataFromFileInternal(templateDataFilePath);
        } catch (IOException | CsvValidationException e) {
            throw new TemplateDataReadingException(templateDataFilePath);
        }
    }

    public List<TemplateData> readTemplateDataFromFileInternal(String templateDataFilePath) throws IOException, CsvValidationException {
        logger.info("Reading data file [templateDataFilePath={}]", templateDataFilePath);
        String csvInput = FileUtils.readFileToString(Paths.get(templateDataFilePath).toFile(), "UTF-8");

        return convertToTemplateData(csvInput);
    }

    @Override
    public String processTemplate(TemplateDefinition templateDefinition, TemplateData templateData) throws TemplateProcessingException {
        logger.info("Processing template [templateName={}]", templateDefinition.getName());

        verifyPlaceholders(templateDefinition, templateData);

        String template = templateDefinition.getTemplate();
        for (PlaceholderDefinition placeholderDefinition : templateDefinition.getPlaceholders()) {
            logger.debug("Replacing value in template [placeholder={}, value={}, occurrenceCount={}]",
                    placeholderDefinition.getName(),
                    templateData.getData().get(placeholderDefinition.getName()),
                    placeholderDefinition.getOccurrences());

            template = template.replace(String.format("${%s}", placeholderDefinition.getName()),
                    templateData.getData().get(placeholderDefinition.getName()));
        }

        return template;
    }
}
