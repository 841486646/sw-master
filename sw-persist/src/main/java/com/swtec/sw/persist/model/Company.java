package com.swtec.sw.persist.model;

import java.io.Serializable;
import java.util.Date;

public class Company implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sw_company
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 6961576997945775297L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_company.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_company.company_name
     *
     * @mbggenerated
     */
    private String companyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_company.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_company.id
     *
     * @return the value of sw_company.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_company.id
     *
     * @param id the value for sw_company.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_company.company_name
     *
     * @return the value of sw_company.company_name
     *
     * @mbggenerated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_company.company_name
     *
     * @param companyName the value for sw_company.company_name
     *
     * @mbggenerated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_company.create_time
     *
     * @return the value of sw_company.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_company.create_time
     *
     * @param createTime the value for sw_company.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}