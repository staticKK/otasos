package com.jointcorp.otasos.vo;

/**
 * Created by Administrator on 2017/7/14.
 */
public class DeviceMessage {
    private String url;
    private String version;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "DeviceMessage{" +
                ", url='" + url + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
