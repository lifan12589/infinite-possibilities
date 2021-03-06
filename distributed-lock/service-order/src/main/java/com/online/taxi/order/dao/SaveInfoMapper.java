package com.online.taxi.order.dao;


import com.online.taxi.order.entity.SaveInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Component(value = "SaveInfoMapper")
public interface SaveInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table save
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    int insert(SaveInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table save
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    List<SaveInfo> selectAll();

    SaveInfo selectInfoByid(String id);



}