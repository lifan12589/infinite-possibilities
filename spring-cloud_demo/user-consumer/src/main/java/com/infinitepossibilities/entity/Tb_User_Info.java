package com.infinitepossibilities.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table tb_user
 */
public class Tb_User_Info implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.id
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.user_name
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.password
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.name
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.age
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private Integer age;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.sex
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private Integer sex;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.birthday
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private Date birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.created
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private Date created;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.updated
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private Date updated;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_user.note
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    private String note;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.id
     *
     * @return the value of tb_user.id
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.id
     *
     * @param id the value for tb_user.id
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.user_name
     *
     * @return the value of tb_user.user_name
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.user_name
     *
     * @param userName the value for tb_user.user_name
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.password
     *
     * @return the value of tb_user.password
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.password
     *
     * @param password the value for tb_user.password
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.name
     *
     * @return the value of tb_user.name
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.name
     *
     * @param name the value for tb_user.name
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.age
     *
     * @return the value of tb_user.age
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public Integer getAge() {
        return age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.age
     *
     * @param age the value for tb_user.age
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.sex
     *
     * @return the value of tb_user.sex
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.sex
     *
     * @param sex the value for tb_user.sex
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.birthday
     *
     * @return the value of tb_user.birthday
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.birthday
     *
     * @param birthday the value for tb_user.birthday
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.created
     *
     * @return the value of tb_user.created
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.created
     *
     * @param created the value for tb_user.created
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.updated
     *
     * @return the value of tb_user.updated
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.updated
     *
     * @param updated the value for tb_user.updated
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_user.note
     *
     * @return the value of tb_user.note
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public String getNote() {
        return note;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_user.note
     *
     * @param note the value for tb_user.note
     *
     * @mbg.generated Tue Jun 01 14:04:13 CST 2021
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}