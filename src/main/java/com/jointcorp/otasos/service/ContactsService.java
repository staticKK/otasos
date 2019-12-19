package com.jointcorp.otasos.service;

import com.jointcorp.common.util.MessageResult;
import com.jointcorp.otasos.entity.Contacts;

public interface ContactsService {

    /**
     * 添加联系人
     * @param contacts
     * @return
     * @throws Exception
     */
    MessageResult add(Contacts contacts) throws Exception;

    /**
     * 修改联系人
     * @param contacts
     * @return
     * @throws Exception
     */
    MessageResult update(Contacts contacts) throws Exception;

    /**
     * 删除联系人
     * @param id
     * @return
     * @throws Exception
     */
    MessageResult delete(Long id) throws Exception;

    /**
     * 查询所有联系人
     * @param uid
     * @return
     * @throws Exception
     */
    MessageResult queryAll(Long uid) throws Exception;

    /**
     * 推送sos消息
     * @param uid 用户Id
     * @param lat 经度
     * @param lng 纬度
     * @return
     * @throws Exception
     */
    MessageResult sos(long uid, String lat, String lng) throws Exception;

}
