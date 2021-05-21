package com.infinitePossibilities.dao;

import com.infinitePossibilities.entity.SaveInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper
@Component(value = "SaveInfoMapper")
public interface SaveInfoMapper {

    int insert(SaveInfo record);

    List<SaveInfo> selectAll();

    SaveInfo selectInfoByid(String id);

    int updateByPrimaryid(SaveInfo record);

}