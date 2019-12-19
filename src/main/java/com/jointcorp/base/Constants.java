package com.jointcorp.base;

/**
 * Created by Administrator on 2017/7/11.
 */
public enum Constants {
    RESULT_STATUS_SUCCESS (1,"成功"),

     RESULT_STATUS_FAIL (2,"失败"),

     PARAMERR_STATUS_ERROR(1119,"参数错误"),

     DEVICE_UPDATE_FILE_NOTEXISIT(1050,"升级文件不存在"),

    DEVICE_UPDATE_N(1051,"不需要升级"),

    DEVICE_UPDATE_Y(1052,"需要升级"),

    DEVICE_ISNOTFOUND(1053,"未找到对应的产品信息"),

    DATA_NULL(1122,"请求数据不存在"),
    CONTACTS_EXIST(1017,"联系人已经存在"),
    DEVICE_BIND(1400,"设备已经被绑定");


    private int code;
    private String info;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    Constants(int code, String info) {
        this.code = code;
        this.info = info;
    }
}
