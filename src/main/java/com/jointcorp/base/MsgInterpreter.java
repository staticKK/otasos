package com.jointcorp.base;

import com.jointcorp.common.util.MessageResult;

/**
 *  枚举对象解释器
 *
 * @author Xiao
 * @create 2017-10-18 15:59
 **/
public final class MsgInterpreter {

    private MsgInterpreter() {
    }

    public static MessageResult build(Constants msg, Object data) {
        return new MessageResult(msg.getCode(), msg.getInfo(), data);
    }


    public static MessageResult build(Constants msg) {
        return new MessageResult(msg.getCode(), msg.getInfo(), null);
    }

    public static MessageResult build(Object data) {
        return new MessageResult(data);
    }

    public static MessageResult success() {
        return new MessageResult(Constants.RESULT_STATUS_SUCCESS.getCode(),Constants.RESULT_STATUS_SUCCESS.getInfo());
    }


    public static MessageResult error() {
        return new MessageResult(Constants.RESULT_STATUS_FAIL.getCode(),Constants.RESULT_STATUS_FAIL.getInfo());
    }

    public static MessageResult error(Constants msg) {
        return new MessageResult(msg.getCode(),msg.getInfo());
    }

}
