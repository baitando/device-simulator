package com.baitando.iot.devicesimulator.template.service.data;

import java.util.HashMap;
import java.util.Map;

public class TemplateData {

    private Map<String, String> data;

    public TemplateData() {
        data = new HashMap<>();
    }

    public void addData(String key, String value) {
        data.put(key, value);
    }

    public Map<String, String> getData() {
        return data;
    }
}
