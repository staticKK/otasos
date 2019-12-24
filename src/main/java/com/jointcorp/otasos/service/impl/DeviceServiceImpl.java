package com.jointcorp.otasos.service.impl;

import com.jointcorp.base.Constants;
import com.jointcorp.base.MsgInterpreter;
import com.jointcorp.common.util.DateTimeUnit;
import com.jointcorp.common.util.DateTimeUtil;
import com.jointcorp.common.util.MessageResult;
import com.jointcorp.otasos.config.AppConfig;
import com.jointcorp.otasos.entity.Device;
import com.jointcorp.otasos.entity.DeviceBindInfo;
import com.jointcorp.otasos.mapper.DeviceBindMapper;
import com.jointcorp.otasos.mapper.DeviceMapper;
import com.jointcorp.otasos.service.DeviceService;
import com.jointcorp.otasos.utils.FileUtil;
import com.jointcorp.otasos.utils.MathUtil;
import com.jointcorp.otasos.utils.UploadAndDown;
import com.jointcorp.otasos.vo.DeviceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceBindMapper deviceBindMapper;
    @Autowired
    private AppConfig appConf;

    @Override
    public MessageResult insertNewType(String type,String info) throws Exception {
        deviceMapper.insertNewType(type,info);
        return  MsgInterpreter.success();
    }

    @Override
    public MessageResult saveFirmware(String type, String version, MultipartFile fmFile) throws Exception {
        String fp = appConf.getFirmwareFilePath() + type;
        File file = new File(fp);
        if(!file.exists()){
            file.mkdir(); //如果目录不存在则创建目录
        }
        String fileName = UploadAndDown.upload(fmFile, fp+"/");

        Device device = new Device();
        device.setDeviceName(type);

        device.setFirmwareName(fileName);
        device.setVersion(version);
        device.setUpdateTime(DateTimeUtil.format(LocalDateTime.now(), DateTimeUnit.ymdhmsSeg_));

        //根据设备型号，不存在新增，存在就修改
        Example example = new Example(Device.class);
        example.createCriteria().andEqualTo("deviceName",type);
        Device deviceexist = selectOneDevice(example);
        if(deviceexist == null) {
            deviceMapper.insertSelective(device);
        } else {
            device.setId(deviceexist.getId());
            deviceMapper.updateByPrimaryKeySelective(device);
        }
        return MsgInterpreter.success();
    }

    @Override
    public MessageResult bindDevice(Long uid, String serialNumber,String reqType) throws Exception {
        Example example = new Example(DeviceBindInfo.class);
        example.createCriteria().andEqualTo("serialNumber",serialNumber);
        DeviceBindInfo bindInfo = selectOneDeviceBindInfo(example);

        /*
         1.如果设备没有绑定，直接绑定
         2.如果设备已绑定，并且uid和系统uid相同，直接返回绑定成功，如果uid和系统uid不同，提示app解绑
         */
        if("bind".equals(reqType)) {
            if(bindInfo == null) {
                bindInfo = new DeviceBindInfo(uid,serialNumber,"bind");
                deviceBindMapper.insert(bindInfo);
                return MsgInterpreter.success();
            }
            if("bind".equals(bindInfo.getState())) {
                if(bindInfo.getUid().longValue() != uid.longValue()) {
                    return MsgInterpreter.build(Constants.DEVICE_BIND);
                } else {
                    return MsgInterpreter.success();
                }
            }
            else {
                bindInfo.setState("bind");
                bindInfo.setUid(uid);
                deviceBindMapper.updateByPrimaryKey(bindInfo);
                return MsgInterpreter.success();
            }

        } else if("unbind".equals(reqType)) {
            if(bindInfo != null) {
                deviceBindMapper.deleteByPrimaryKey(bindInfo.getId());
                return MsgInterpreter.success();
            }
        }
        return MsgInterpreter.build(Constants.PARAMERR_STATUS_ERROR,"非法的state状态值");
    }

    @Override
    public MessageResult update(String type, String version) throws Exception {
        //根据设备型号，不存在新增，存在就修改
        Example example = new Example(Device.class);
        example.createCriteria().andEqualTo("deviceName",type);
        Device device = selectOneDevice(example);
        return update(device,version);
    }

    private MessageResult update(Device device,String version) {
        if(device ==  null){
            return MsgInterpreter.build(Constants.DEVICE_ISNOTFOUND,null);
        }
        if(!MathUtil.matche(version)){
            return MsgInterpreter.build(Constants.PARAMERR_STATUS_ERROR,null);
        }
        String ver = device.getVersion(); //版本号
        //根据固件版本判断是否需要升级
        if(Integer.parseInt(version) >= Integer.parseInt(ver)){
            return MsgInterpreter.build(Constants.DEVICE_UPDATE_N,null);
        }
        //文件所在路径
        String filePath = appConf.getFirmwareFilePath() + device.getDeviceName() + "/" + device.getFirmwareName();
        //判断文件是否存在
        boolean flag = FileUtil.getInstance().isExists(filePath);
        if(!flag){
            return MsgInterpreter.build(Constants.DEVICE_UPDATE_FILE_NOTEXISIT,null);
        }
        String url = appConf.getOtaUrl()+device.getDeviceName() + "/" + device.getFirmwareName();
        DeviceMessage deviceMessage=new DeviceMessage();
        deviceMessage.setVersion(ver);
        deviceMessage.setUrl(url);

        return MsgInterpreter.build(Constants.DEVICE_UPDATE_Y,deviceMessage);
    }

    private Device selectOneDevice(Example example) {
        List<Device> list = deviceMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    private DeviceBindInfo selectOneDeviceBindInfo(Example example) {
        List<DeviceBindInfo> list = deviceBindMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }
}
