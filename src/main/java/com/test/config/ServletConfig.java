package com.test.config;

import lombok.Data;

@Data
public class ServletConfig {
    private String name;
    private String urlMapping;
    private String clazz;

    public ServletConfig() {
    }

    public ServletConfig(String name, String urlMapping, String clazz) {
        this.name = name;
        this.urlMapping = urlMapping;
        this.clazz = clazz;
    }
}