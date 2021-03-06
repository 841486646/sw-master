package com.swtec.sw.persist.model;

import java.io.Serializable;
import java.util.Date;

public class CommodityCategory implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = -2579344551170109809L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_commodity_category.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_commodity_category.category_name
     *
     * @mbggenerated
     */
    private String categoryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_commodity_category.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_commodity_category.remarks
     *
     * @mbggenerated
     */
    private String remarks;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_commodity_category.parent_id
     *
     * @mbggenerated
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_commodity_category.parent_ids
     *
     * @mbggenerated
     */
    private String parentIds;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_commodity_category.weight
     *
     * @mbggenerated
     */
    private Integer weight;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_commodity_category.is_show
     *
     * @mbggenerated
     */
    private String isShow;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_commodity_category.id
     *
     * @return the value of sw_commodity_category.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_commodity_category.id
     *
     * @param id the value for sw_commodity_category.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_commodity_category.category_name
     *
     * @return the value of sw_commodity_category.category_name
     *
     * @mbggenerated
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_commodity_category.category_name
     *
     * @param categoryName the value for sw_commodity_category.category_name
     *
     * @mbggenerated
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_commodity_category.create_time
     *
     * @return the value of sw_commodity_category.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_commodity_category.create_time
     *
     * @param createTime the value for sw_commodity_category.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_commodity_category.remarks
     *
     * @return the value of sw_commodity_category.remarks
     *
     * @mbggenerated
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_commodity_category.remarks
     *
     * @param remarks the value for sw_commodity_category.remarks
     *
     * @mbggenerated
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_commodity_category.parent_id
     *
     * @return the value of sw_commodity_category.parent_id
     *
     * @mbggenerated
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_commodity_category.parent_id
     *
     * @param parentId the value for sw_commodity_category.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_commodity_category.parent_ids
     *
     * @return the value of sw_commodity_category.parent_ids
     *
     * @mbggenerated
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_commodity_category.parent_ids
     *
     * @param parentIds the value for sw_commodity_category.parent_ids
     *
     * @mbggenerated
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_commodity_category.weight
     *
     * @return the value of sw_commodity_category.weight
     *
     * @mbggenerated
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_commodity_category.weight
     *
     * @param weight the value for sw_commodity_category.weight
     *
     * @mbggenerated
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_commodity_category.is_show
     *
     * @return the value of sw_commodity_category.is_show
     *
     * @mbggenerated
     */
    public String getIsShow() {
        return isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_commodity_category.is_show
     *
     * @param isShow the value for sw_commodity_category.is_show
     *
     * @mbggenerated
     */
    public void setIsShow(String isShow) {
        this.isShow = isShow == null ? null : isShow.trim();
    }
}