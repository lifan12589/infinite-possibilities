package com.infinitePossibilities.entity;

import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table product
 */
public class ProductInfo {
    /**
     * Database Column Remarks:
     *   主键id，默认自增长
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.id
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   事项名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.itemName
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    private String itemname;

    /**
     * Database Column Remarks:
     *   事项代码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.itemCode
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    private String itemcode;

    /**
     * Database Column Remarks:
     *   申请类型
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.targetType
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    private String targettype;

    /**
     * Database Column Remarks:
     *   申请数量
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.num
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    private Integer num;

    /**
     * Database Column Remarks:
     *   申请时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.input_date
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    private Timestamp inputDate;

    /**
     * Database Column Remarks:
     *   类型说明
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product.tags
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    private String tags;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.id
     *
     * @return the value of product.id
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.id
     *
     * @param id the value for product.id
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.itemName
     *
     * @return the value of product.itemName
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.itemName
     *
     * @param itemname the value for product.itemName
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.itemCode
     *
     * @return the value of product.itemCode
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public String getItemcode() {
        return itemcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.itemCode
     *
     * @param itemcode the value for product.itemCode
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode == null ? null : itemcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.targetType
     *
     * @return the value of product.targetType
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public String getTargettype() {
        return targettype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.targetType
     *
     * @param targettype the value for product.targetType
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public void setTargettype(String targettype) {
        this.targettype = targettype == null ? null : targettype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.num
     *
     * @return the value of product.num
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.num
     *
     * @param num the value for product.num
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.input_date
     *
     * @return the value of product.input_date
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public Timestamp getInputDate() {
        return inputDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.input_date
     *
     * @param inputDate the value for product.input_date
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public void setInputDate(Timestamp inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product.tags
     *
     * @return the value of product.tags
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product.tags
     *
     * @param tags the value for product.tags
     *
     * @mbg.generated Tue Feb 23 15:28:47 CST 2021
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }
}