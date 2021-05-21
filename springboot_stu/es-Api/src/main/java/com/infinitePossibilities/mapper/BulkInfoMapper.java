package com.infinitePossibilities.mapper;

import com.infinitePossibilities.entity.BulkInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BulkInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulk_data
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulk_data
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    int insert(BulkInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulk_data
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    BulkInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulk_data
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    List<BulkInfo> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bulk_data
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    int updateByPrimaryKey(BulkInfo record);
}