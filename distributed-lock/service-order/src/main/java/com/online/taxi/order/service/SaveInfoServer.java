package com.online.taxi.order.service;

import com.online.taxi.order.entity.SaveInfo;
import org.springframework.stereotype.Service;

@Service
public interface SaveInfoServer {

    SaveInfo selectInfoByid(String id);

    int insert(SaveInfo record);

}
