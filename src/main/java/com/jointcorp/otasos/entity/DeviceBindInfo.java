package com.jointcorp.otasos.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "device_bind_info")
public class DeviceBindInfo {

    @Id
    private Long id;
    private Long uid;
    private String mac;
    private String state;

    public DeviceBindInfo() {
    }

    public DeviceBindInfo(Long uid, String mac, String state) {
        this.uid = uid;
        this.mac = mac;
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
