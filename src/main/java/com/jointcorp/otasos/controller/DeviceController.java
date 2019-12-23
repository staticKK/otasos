package com.jointcorp.otasos.controller;

import com.jointcorp.base.Constants;
import com.jointcorp.base.MsgInterpreter;
import com.jointcorp.common.util.MessageResult;
import com.jointcorp.otasos.service.DeviceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/firmware")
public class DeviceController{

    @Autowired
    private DeviceService deviceService;

    /**
     * 添加新的设备
     * @param type
     * @param response
     * @throws Exception
     * @author xiao 2016年11月21日 上午10:53:28
     */
    @RequestMapping(value="/saveType",method=RequestMethod.POST)
    @ResponseBody
    public MessageResult saveType(@RequestParam("type") String type,@RequestParam("info") String info, HttpServletResponse response) throws Exception{
        return deviceService.insertNewType(type.toLowerCase(),info);
    }

    /**
     * 上传固件
     * @param version
     * @param type
     * @param fmfile
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value="/saveFirmware",method=RequestMethod.POST)
    public MessageResult saveFirmware(@RequestParam("version") String version, @RequestParam("deviceName") String type,
                             @RequestParam("fmfile") MultipartFile fmfile) throws Exception{
        return deviceService.saveFirmware(type, version, fmfile);
    }

    /**
     * 固件升级，固件升级接口
     * @param type 手环名称
     * @param version  版本号
     * @throws Exception
     *
     */
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    @ResponseBody
    public MessageResult update(String type, String version) throws Exception{
       return deviceService.update(type.toLowerCase(), version);
    }

    @PostMapping("/bindDevice")
    @ResponseBody
    public MessageResult bindDevice(Long uid,String serialNumber,@RequestParam(value = "state",defaultValue = "bind") String state) throws Exception {
        if(uid == null || uid == 0) {
            return MsgInterpreter.build(Constants.PARAMERR_STATUS_ERROR,"用户ID不能为null或者0");
        }
        if(StringUtils.isBlank(serialNumber)) {
            return MsgInterpreter.build(Constants.PARAMERR_STATUS_ERROR,"序列号不能为空");
        }
        return deviceService.bindDevice(uid,serialNumber.toLowerCase(),state);
    }

}
