package com.baitando.iot.devicesimulator.cli;

import com.baitando.iot.devicesimulator.gateway.service.GatewayService;
import com.baitando.iot.devicesimulator.gateway.service.MessageSendingException;
import com.baitando.iot.devicesimulator.template.service.TemplateDataReadingException;
import com.baitando.iot.devicesimulator.template.service.TemplateDefinitionReadingException;
import com.baitando.iot.devicesimulator.template.service.TemplateService;
import com.baitando.iot.devicesimulator.template.service.data.TemplateData;
import com.baitando.iot.devicesimulator.template.service.data.TemplateDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * A command line command which reads a template definition and template data from two files. The loaded data is
 * processed, i.e. device to cloud messages are derived. The created messages are then sent to the IoT Hub using
 * the configured gateway connection.
 */
@Component
@Command(name = "simulator", mixinStandardHelpOptions = true)
public class SimulatorCommand implements Callable<Integer> {

    @Option(names = "-t", description = "template file path")
    private String templateFilePath;

    @Option(names = "-d", description = "data file path")
    private String dataFilePath;

    private final Logger logger = LoggerFactory.getLogger(SimulatorCommand.class);

    private final TemplateService templateService;
    private final GatewayService gatewayService;

    public SimulatorCommand(TemplateService templateService, GatewayService gatewayService) {
        this.templateService = templateService;
        this.gatewayService = gatewayService;
    }

    @Override
    public Integer call() {
        logger.info("Handling gateway run [templateFilePath={}, dataFilePath={}]", templateFilePath, dataFilePath);

        try {
            TemplateDefinition templateDefinition = templateService.readTemplateDefinitionFromFile(templateFilePath);
            List<TemplateData> templateData = templateService.readTemplateDataFromFile(dataFilePath);

            List<String> messages = new ArrayList<>(templateData.size());
            for (TemplateData templateDataEntry : templateData) {
                messages.add(templateService.processTemplate(templateDefinition, templateDataEntry));
            }
            gatewayService.sendMessages(messages);
            return 0;
        } catch (TemplateDataReadingException e) {
            return 10;
        } catch (TemplateDefinitionReadingException e) {
            return 11;
        } catch (MessageSendingException e) {
            return 20;
        }
    }
}
