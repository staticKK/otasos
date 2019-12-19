package com.jointcorp.otasos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Table(name = "history_sos")
public class HistorySos {

    @Id
    private long id;
    private long uid; //本系统用户id
    private String phone;
    private String info; //经度,纬度
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "sendTime")
    private LocalDateTime sendTime; //发送时间



    public HistorySos() {
    }

    public HistorySos(long uid, String phone, String info) {
        this.uid = uid;
        this.phone = phone;
        this.info = info;
        this.sendTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
