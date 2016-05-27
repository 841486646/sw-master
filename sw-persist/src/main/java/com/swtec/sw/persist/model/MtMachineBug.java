package com.swtec.sw.persist.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MtMachineBug implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sw_mt_machine_bug
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = -6985475277006503952L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_mt_machine_bug.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_mt_machine_bug.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_mt_machine_bug.price
     *
     * @mbggenerated
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_mt_machine_bug.machine_type_id
     *
     * @mbggenerated
     */
    private Integer machineTypeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_mt_machine_bug.machine_color_id
     *
     * @mbggenerated
     */
    private Integer machineColorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_mt_machine_bug.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_mt_machine_bug.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_mt_machine_bug.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_mt_machine_bug.id
     *
     * @return the value of sw_mt_machine_bug.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_mt_machine_bug.id
     *
     * @param id the value for sw_mt_machine_bug.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_mt_machine_bug.name
     *
     * @return the value of sw_mt_machine_bug.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_mt_machine_bug.name
     *
     * @param name the value for sw_mt_machine_bug.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_mt_machine_bug.price
     *
     * @return the value of sw_mt_machine_bug.price
     *
     * @mbggenerated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_mt_machine_bug.price
     *
     * @param price the value for sw_mt_machine_bug.price
     *
     * @mbggenerated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_mt_machine_bug.machine_type_id
     *
     * @return the value of sw_mt_machine_bug.machine_type_id
     *
     * @mbggenerated
     */
    public Integer getMachineTypeId() {
        return machineTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_mt_machine_bug.machine_type_id
     *
     * @param machineTypeId the value for sw_mt_machine_bug.machine_type_id
     *
     * @mbggenerated
     */
    public void setMachineTypeId(Integer machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_mt_machine_bug.machine_color_id
     *
     * @return the value of sw_mt_machine_bug.machine_color_id
     *
     * @mbggenerated
     */
    public Integer getMachineColorId() {
        return machineColorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_mt_machine_bug.machine_color_id
     *
     * @param machineColorId the value for sw_mt_machine_bug.machine_color_id
     *
     * @mbggenerated
     */
    public void setMachineColorId(Integer machineColorId) {
        this.machineColorId = machineColorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_mt_machine_bug.description
     *
     * @return the value of sw_mt_machine_bug.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_mt_machine_bug.description
     *
     * @param description the value for sw_mt_machine_bug.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_mt_machine_bug.create_time
     *
     * @return the value of sw_mt_machine_bug.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_mt_machine_bug.create_time
     *
     * @param createTime the value for sw_mt_machine_bug.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_mt_machine_bug.update_time
     *
     * @return the value of sw_mt_machine_bug.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_mt_machine_bug.update_time
     *
     * @param updateTime the value for sw_mt_machine_bug.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}