package com.jointcorp.otasos.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 联系人
 */
public class Contacts {

    @Id
    private Long id;

    @NotNull
    @Min(1)
    private Long uid; //用户ID

    @NotBlank
    private String uname; //我的名字

    @NotBlank
    private String phone;

    private String remake; //备注

    private int available; //是否有效  1有效，其他无效,默认1

    public Contacts() {
        this.available = 1;
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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {


        this.available = available;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", uid=" + uid +
                ", uname='" + uname + '\'' +
                ", phone='" + phone + '\'' +
                ", remake='" + remake + '\'' +
                ", available=" + available +
                '}';
    }
}
