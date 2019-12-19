package com.jointcorp.otasos.mapper;

import com.jointcorp.base.BaseMapper;
import com.jointcorp.otasos.entity.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/7/11.
 */
@Component
public interface DeviceMapper extends BaseMapper<Device> {

    int update1(Device device) throws Exception;

    /**
     * 添加新的设备
     * @param deviceName
     * @return
     * @throws Exception
     */
    int insertNewType(@Param("deviceName") String deviceName,@Param("info") String info) throws Exception;

    Device selectByType(@Param("deviceName") String deviceName);
}
