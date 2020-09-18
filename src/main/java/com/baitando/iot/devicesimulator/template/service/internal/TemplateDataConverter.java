package com.baitando.iot.devicesimulator.template.service.internal;

import com.baitando.iot.devicesimulator.template.service.data.TemplateData;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.CSVReaderHeaderAwareBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TemplateDataConverter {

    static List<TemplateData> convertToTemplateData(String csvInput) throws IOException, CsvValidationException {
        List<TemplateData> templateDataList = new ArrayList<>();
        Reader reader = new StringReader(csvInput);
        CSVReaderHeaderAware csvReader = new CSVReaderHeaderAwareBuilder(reader)
                .build();

        Map<String, String> data = csvReader.readMap();
        while (data != null) {
            TemplateData templateData = new TemplateData();
            data.forEach(templateData::addData);
            data = csvReader.readMap();
            templateDataList.add(templateData);
        }
        return templateDataList;
    }
}
