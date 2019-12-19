package com.jointcorp.otasos.controller;

import com.jointcorp.base.Constants;
import com.jointcorp.base.MsgInterpreter;
import com.jointcorp.common.util.MessageResult;
import com.jointcorp.otasos.entity.Contacts;
import com.jointcorp.otasos.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sos")
public class SOSController {

    @Autowired
    private ContactsService sosService;

    /**
     * 新增联系人
     * @param contacts
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping("/add")
    public MessageResult save(@Validated Contacts contacts, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return MsgInterpreter.error(Constants.PARAMERR_STATUS_ERROR);
        }
        return sosService.add(contacts);
    }

    /**
     * 更新联系人
     * @param contacts
     * @param result
     * @return
     * @throws Exception
     */
    @PostMapping("/update")
    public MessageResult update(@Validated Contacts contacts, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return MsgInterpreter.error(Constants.PARAMERR_STATUS_ERROR);
        }
        return sosService.update(contacts);
    }

    /**
     * 查询我的联系人
     * @param uid
     * @return
     * @throws Exception
     */
    @GetMapping("/queryAll")
    public MessageResult queryAll(Long uid) throws Exception {
        if(uid == null) {
            return MsgInterpreter.build(Constants.PARAMERR_STATUS_ERROR);
        }
        return sosService.queryAll(uid);
    }

    /**
     * 删除联系人
     * @param id
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public MessageResult delete(Long id) throws Exception {
        if(id == null) {
            return MsgInterpreter.build(Constants.PARAMERR_STATUS_ERROR);
        }
        return sosService.delete(id);
    }

    /**
     * 发送sos消息
     * @param uid
     * @param lat 纬度
     * @param lng 经度
     * @return
     * @throws Exception
     */
    @PostMapping("/sos")
    public MessageResult sos(Long uid,String lat,String lng) throws Exception {
        if(uid == null) {
            return MsgInterpreter.build(Constants.PARAMERR_STATUS_ERROR);
        }
        return sosService.sos(uid,lat,lng);
    }
}
