package com.infinitePossibilities.wyc.dao;

import com.infinitePossibilities.wyc.entity.OrderBase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "OrderBaseDao")
public interface OrderBaseDao {
    int deleteByPrimaryKey(String id);

    int insert(OrderBase record);

    int insertSelective(OrderBase record);

    OrderBase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderBase record);

    int updateByPrimaryKey(OrderBase record);
}