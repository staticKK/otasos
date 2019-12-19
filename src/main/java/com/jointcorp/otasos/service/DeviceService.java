package com.jointcorp.otasos.service;

import com.jointcorp.common.util.MessageResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * 设备相关业务类
 * @author xyc
 * @date 2015年11月23日 上午10:00:01
 */
public interface DeviceService {

    /**
     * 添加新设备
     * @param type
     * @param info
     * @return
     * @throws Exception
     */
    MessageResult insertNewType(String type,String info) throws Exception;

    /**
     * 固件更新
     * @param type 设备类型，如：j055
     * @param version 当前版本号
     * @return
     * @throws Exception
     *
     * @author xyc 2015年11月23日 上午10:00:39
     */
    MessageResult update(String type, String version) throws Exception;


    /**
     * 上传固件
     * @param type
     * @param version
     * @param file
     * @return
     * @throws Exception
     */
    MessageResult saveFirmware(String type, String version,MultipartFile file) throws Exception;

    /**
     * 绑定设备
     * @param uid
     * @param mac
     * @return
     * @throws Exception
     */
    MessageResult bindDevice(Long uid, String mac,String state) throws Exception;
}
