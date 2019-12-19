package com.jointcorp.otasos.service.impl;

import com.jointcorp.base.Constants;
import com.jointcorp.base.MsgInterpreter;
import com.jointcorp.common.util.MessageResult;
import com.jointcorp.otasos.entity.Contacts;
import com.jointcorp.otasos.mapper.ContactsMapper;
import com.jointcorp.otasos.service.ContactsService;
import com.jointcorp.otasos.utils.LocalCache;
import com.jointcorp.otasos.vo.ContactsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ContantsServiceImpl implements ContactsService {

    private LocalCache localCache = new LocalCache();

    @Autowired
    private ContactsMapper sosMapper;

    @Override
    public MessageResult add(Contacts contacts) throws Exception {
        Example example = new Example(Contacts.class);
        example.createCriteria().andEqualTo("uid",contacts.getUid()).andEqualTo("phone",contacts.getPhone())
                .andEqualTo("available",1);

        int count = sosMapper.selectCountByExample(example);
        if(count != 0) {
            return MsgInterpreter.build(Constants.CONTACTS_EXIST);
        }

        Example countExample = new Example(Contacts.class);
        countExample.createCriteria().andEqualTo("uid",contacts.getUid()).andEqualTo("available",1);
        int ucount = sosMapper.selectCountByExample(countExample);
        if(ucount >=3) {
            return MsgInterpreter.error();
        }
        sosMapper.insert(contacts);
        return MsgInterpreter.success();
    }

    @Override
    public MessageResult update(Contacts contacts) throws Exception {
        sosMapper.updateByPrimaryKeySelective(contacts);
        return MsgInterpreter.success();
    }

    @Override
    public MessageResult delete(Long id) throws Exception {
        Contacts contacts = new Contacts();
        contacts.setId(id);
        contacts.setAvailable(0);
        sosMapper.updateByPrimaryKeySelective(contacts);
        return MsgInterpreter.success();
    }

    @Override
    public MessageResult queryAll(Long uid) throws Exception {
        Example example = new Example(Contacts.class);
        example.createCriteria().andEqualTo("uid",uid).andEqualTo("available",1);
        List<Contacts> list = sosMapper.selectByExample(example);
        return MsgInterpreter.build(ContactsVO.parse(list));
    }

    @Override
    public MessageResult sos(long uid,String lat, String lng) throws Exception {
        //查询所有的联系人
        Example example = new Example(Contacts.class);
        example.createCriteria().andEqualTo("uid",uid).andEqualTo("available",1);

        //所有联系人
        List<Contacts> list = sosMapper.selectByExample(example);


        //手机短信收到的链接，包含经纬度和发送者名字和时间戳,用于网页端展示
        String link = "http://localhost/sos/LANGUAGE.html#"+lat+"/"+lng+"/UNAME/"+System.currentTimeMillis();

        /*
        * 完善发送短信流程
        *
        for(Contacts contact : list) {
            //手机号码
            String mobilePhone = contact.getPhone();
        }
        *
        */






        return MsgInterpreter.success();
    }


}
