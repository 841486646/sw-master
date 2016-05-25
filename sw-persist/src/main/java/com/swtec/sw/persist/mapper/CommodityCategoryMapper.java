package com.swtec.sw.persist.mapper;

import com.swtec.sw.persist.model.CommodityCategory;
import com.swtec.sw.persist.model.CommodityCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    int countByExample(CommodityCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    int deleteByExample(CommodityCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    int insert(CommodityCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    int insertSelective(CommodityCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    List<CommodityCategory> selectByExample(CommodityCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    CommodityCategory selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") CommodityCategory record, @Param("example") CommodityCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") CommodityCategory record, @Param("example") CommodityCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(CommodityCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_commodity_category
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CommodityCategory record);
}