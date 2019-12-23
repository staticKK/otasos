package com.jointcorp.otasos.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "device_bind_info")
public class DeviceBindInfo {

    @Id
    private Long id;
    private Long uid;
    private String serialNumber;
    private String state;

    public DeviceBindInfo() {
    }

    public DeviceBindInfo(Long uid, String serialNumber, String state) {
        this.uid = uid;
        this.serialNumber = serialNumber;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
