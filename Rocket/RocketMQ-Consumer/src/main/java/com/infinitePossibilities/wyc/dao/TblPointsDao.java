package com.infinitePossibilities.wyc.dao;

import com.infinitePossibilities.wyc.entity.TblPoints;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "TblPointsDao")
public interface TblPointsDao {
    int deleteByPrimaryKey(String id);

    int insert(TblPoints record);

    int insertSelective(TblPoints record);

    TblPoints selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TblPoints record);

    int updateByPrimaryKey(TblPoints record);
}