package com.jointcorp.otasos.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Created by Administrator on 2017/7/11.
 */
public class Device {
    @Id
    private int id;
//    private int update; //不需要持久化
    @Column(name = "deviceName")
    private String deviceName; //设备名称
    private String info;  //备注信息
    @Transient
    private String filePath; //文件路径，不需要持久化
    @Transient
    private String url; //下载地址，不需要持久化
    @Column(name = "firmwareName")
    private String firmwareName; //固件名称
    private String version; //固件版本号
    @Column(name = "updateTime")
    private String updateTime; //更新时间

    public Device() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFirmwareName() {
        return firmwareName;
    }

    public void setFirmwareName(String firmwareName) {
        this.firmwareName = firmwareName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
