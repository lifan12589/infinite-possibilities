package com.infinitePossibilities.wyc.dao;

import com.infinitePossibilities.wyc.entity.TransactionLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "TransactionLogDao")
public interface TransactionLogDao {
    int deleteByPrimaryKey(String id);

    int insert(TransactionLog record);

    int insertSelective(TransactionLog record);

    TransactionLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TransactionLog record);

    int updateByPrimaryKey(TransactionLog record);

    int selectCount(String id);
}