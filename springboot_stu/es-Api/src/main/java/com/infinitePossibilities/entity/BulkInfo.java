package com.infinitePossibilities.entity;

import java.sql.Timestamp;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table bulk_data
 */
public class BulkInfo {
    /**
     * Database Column Remarks:
     *   主键id，默认自增长
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulk_data.id
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    private Long id;

    /**
     * Database Column Remarks:
     *   事项名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulk_data.itemName
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    private String itemname;

    /**
     * Database Column Remarks:
     *   事项代码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulk_data.itemCode
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    private String itemcode;

    /**
     * Database Column Remarks:
     *   申请类型
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulk_data.targetType
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    private String targettype;

    /**
     * Database Column Remarks:
     *   申请数量
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulk_data.num
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    private Integer num;

    /**
     * Database Column Remarks:
     *   申请时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulk_data.input_date
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    private Timestamp inputDate;

    /**
     * Database Column Remarks:
     *   类型说明
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column bulk_data.tags
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    private String tags;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulk_data.id
     *
     * @return the value of bulk_data.id
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulk_data.id
     *
     * @param id the value for bulk_data.id
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulk_data.itemName
     *
     * @return the value of bulk_data.itemName
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulk_data.itemName
     *
     * @param itemname the value for bulk_data.itemName
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public void setItemname(String itemname) {
        this.itemname = itemname == null ? null : itemname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulk_data.itemCode
     *
     * @return the value of bulk_data.itemCode
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public String getItemcode() {
        return itemcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulk_data.itemCode
     *
     * @param itemcode the value for bulk_data.itemCode
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode == null ? null : itemcode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulk_data.targetType
     *
     * @return the value of bulk_data.targetType
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public String getTargettype() {
        return targettype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulk_data.targetType
     *
     * @param targettype the value for bulk_data.targetType
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public void setTargettype(String targettype) {
        this.targettype = targettype == null ? null : targettype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulk_data.num
     *
     * @return the value of bulk_data.num
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public Integer getNum() {
        return num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulk_data.num
     *
     * @param num the value for bulk_data.num
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulk_data.input_date
     *
     * @return the value of bulk_data.input_date
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public Timestamp getInputDate() {
        return inputDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulk_data.input_date
     *
     * @param inputDate the value for bulk_data.input_date
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public void setInputDate(Timestamp inputDate) {
        this.inputDate = inputDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column bulk_data.tags
     *
     * @return the value of bulk_data.tags
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public String getTags() {
        return tags;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column bulk_data.tags
     *
     * @param tags the value for bulk_data.tags
     *
     * @mbg.generated Fri Feb 26 14:34:03 CST 2021
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }
}