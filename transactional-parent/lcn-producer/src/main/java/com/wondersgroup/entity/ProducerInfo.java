package com.wondersgroup.entity;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table producer
 */
public class ProducerInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column producer.id
     *
     * @mbg.generated Wed Apr 28 16:23:49 CST 2021
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column producer.name
     *
     * @mbg.generated Wed Apr 28 16:23:49 CST 2021
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column producer.id
     *
     * @return the value of producer.id
     *
     * @mbg.generated Wed Apr 28 16:23:49 CST 2021
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column producer.id
     *
     * @param id the value for producer.id
     *
     * @mbg.generated Wed Apr 28 16:23:49 CST 2021
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column producer.name
     *
     * @return the value of producer.name
     *
     * @mbg.generated Wed Apr 28 16:23:49 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column producer.name
     *
     * @param name the value for producer.name
     *
     * @mbg.generated Wed Apr 28 16:23:49 CST 2021
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}