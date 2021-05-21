package com.infinitePossibilities.mapper;

import com.infinitePossibilities.entity.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    int insert(ProductInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    List<ProductInfo> selectAll();
}