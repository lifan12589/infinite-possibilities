package com.online.taxi.order.service.impl;

import com.online.taxi.order.dao.SaveInfoMapper;
import com.online.taxi.order.entity.SaveInfo;
import com.online.taxi.order.service.SaveInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SaveInfoServerImpl implements SaveInfoServer {


    @Autowired
    SaveInfoMapper saveInfoMapper ;


    @Override
    public SaveInfo selectInfoByid(String id) {
        System.out.println(id);
        return saveInfoMapper.selectInfoByid(id);
    }

    @Override
    public int insert(SaveInfo Info) {
        return saveInfoMapper.insert(Info);
    }
}
