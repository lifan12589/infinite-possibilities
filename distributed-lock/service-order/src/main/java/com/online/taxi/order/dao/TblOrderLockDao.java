package com.online.taxi.order.dao;

import com.online.taxi.order.entity.TblOrderLock;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Mapper
@Component(value = "TblOrderLockDao")
public interface TblOrderLockDao {
    int deleteByPrimaryKey(Integer orderId);

    int insert(TblOrderLock record);

    int insertSelective(TblOrderLock record);

    TblOrderLock selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(TblOrderLock record);

    int updateByPrimaryKey(TblOrderLock record);
}