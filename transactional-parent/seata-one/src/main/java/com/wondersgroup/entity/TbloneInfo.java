package com.wondersgroup.entity;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tbl_one
 */
public class TbloneInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_one.id
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_one.name
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_one.id
     *
     * @return the value of tbl_one.id
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_one.id
     *
     * @param id the value for tbl_one.id
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tbl_one.name
     *
     * @return the value of tbl_one.name
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tbl_one.name
     *
     * @param name the value for tbl_one.name
     *
     * @mbg.generated Mon May 17 11:02:49 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}