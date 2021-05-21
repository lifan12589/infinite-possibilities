package com.wondersgroup.mapper;

import com.wondersgroup.entity.TbloneInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "TbloneInfoMapper")
public interface TbloneInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_one
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_one
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    int insert(TbloneInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_one
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    TbloneInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_one
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    List<TbloneInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_one
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    int updateByPrimaryKey(TbloneInfo record);
}