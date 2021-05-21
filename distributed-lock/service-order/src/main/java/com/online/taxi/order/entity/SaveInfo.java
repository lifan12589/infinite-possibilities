package com.online.taxi.order.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table save
 */
public class SaveInfo{
    /**
     * Database Column Remarks:
     *   主键id
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.ID
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private String id;

    /**
     * Database Column Remarks:
     *   办件编号
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.APPLYNO
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private String applyno;

    /**
     * Database Column Remarks:
     *   办理人名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.USER_NAME
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private String userName;

    /**
     * Database Column Remarks:
     *   办理人证件号码
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.USER_NUMBER
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private String userNumber;

    /**
     * Database Column Remarks:
     *   办理人地址
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.ADDRESS
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private String address;

    /**
     * Database Column Remarks:
     *   保存时间
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.SAVE_TIME
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private Date saveTime;

    /**
     * Database Column Remarks:
     *   办理状态
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.TYPE
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private String type;

    /**
     * Database Column Remarks:
     *   综合信息
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.COUNT
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private String count;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.SAVE_BOLB
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private byte[] saveBolb;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column save.SAVE_CLOB
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    private String saveClob;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.ID
     *
     * @return the value of save.ID
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.ID
     *
     * @param id the value for save.ID
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.APPLYNO
     *
     * @return the value of save.APPLYNO
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public String getApplyno() {
        return applyno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.APPLYNO
     *
     * @param applyno the value for save.APPLYNO
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setApplyno(String applyno) {
        this.applyno = applyno == null ? null : applyno.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.USER_NAME
     *
     * @return the value of save.USER_NAME
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.USER_NAME
     *
     * @param userName the value for save.USER_NAME
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.USER_NUMBER
     *
     * @return the value of save.USER_NUMBER
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public String getUserNumber() {
        return userNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.USER_NUMBER
     *
     * @param userNumber the value for save.USER_NUMBER
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber == null ? null : userNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.ADDRESS
     *
     * @return the value of save.ADDRESS
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.ADDRESS
     *
     * @param address the value for save.ADDRESS
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.SAVE_TIME
     *
     * @return the value of save.SAVE_TIME
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public Date getSaveTime() {
        return saveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.SAVE_TIME
     *
     * @param saveTime the value for save.SAVE_TIME
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.TYPE
     *
     * @return the value of save.TYPE
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.TYPE
     *
     * @param type the value for save.TYPE
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.COUNT
     *
     * @return the value of save.COUNT
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public String getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.COUNT
     *
     * @param count the value for save.COUNT
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setCount(String count) {
        this.count = count == null ? null : count.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.SAVE_BOLB
     *
     * @return the value of save.SAVE_BOLB
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public byte[] getSaveBolb() {
        return saveBolb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.SAVE_BOLB
     *
     * @param saveBolb the value for save.SAVE_BOLB
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setSaveBolb(byte[] saveBolb) {
        this.saveBolb = saveBolb;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column save.SAVE_CLOB
     *
     * @return the value of save.SAVE_CLOB
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public String getSaveClob() {
        return saveClob;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column save.SAVE_CLOB
     *
     * @param saveClob the value for save.SAVE_CLOB
     *
     * @mbg.generated Tue Dec 01 11:02:58 CST 2020
     */
    public void setSaveClob(String saveClob) {
        this.saveClob = saveClob == null ? null : saveClob.trim();
    }
}