package com.jointcorp.otasos.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "appConf")
public class AppConfig {

    //升级文件url
    private String otaUrl;

    //升级文件路径
    private String firmwareFilePath;

    public String getOtaUrl() {
        return otaUrl;
    }

    public void setOtaUrl(String otaUrl) {
        this.otaUrl = otaUrl;
    }

    public String getFirmwareFilePath() {
        return firmwareFilePath;
    }

    public void setFirmwareFilePath(String firmwareFilePath) {
        this.firmwareFilePath = firmwareFilePath;
    }
}
