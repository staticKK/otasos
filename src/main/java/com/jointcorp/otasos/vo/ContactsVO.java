package com.jointcorp.otasos.vo;

import com.jointcorp.otasos.entity.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactsVO {

    private Long id;
    private Long uid; //用户ID
    private String uname; //我的名字
    private String phone;
    private String remake; //备注

    public ContactsVO() {
    }

    public ContactsVO(Long id, Long uid, String uname, String phone, String remake) {
        this.id = id;
        this.uid = uid;
        this.uname = uname;
        this.phone = phone;
        this.remake = remake;
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

    public static ContactsVO parse(Contacts contacts) {
        return new ContactsVO(contacts.getId(),contacts.getUid(),contacts.getUname(),contacts.getPhone(),contacts.getRemake());
    }

    public static List<ContactsVO> parse(List<Contacts> contactsList) {
        List<ContactsVO> list = new ArrayList<>();
        contactsList.forEach(e ->{
            list.add(parse(e));
        });
        return list;
    }
}
