package com.swtec.sw.persist.mapper;

import com.swtec.sw.persist.model.AreaCodeInfo;
import com.swtec.sw.persist.model.AreaCodeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaCodeInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    int countByExample(AreaCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    int deleteByExample(AreaCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer areaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    int insert(AreaCodeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    int insertSelective(AreaCodeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    List<AreaCodeInfo> selectByExample(AreaCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    AreaCodeInfo selectByPrimaryKey(Integer areaId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AreaCodeInfo record, @Param("example") AreaCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AreaCodeInfo record, @Param("example") AreaCodeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AreaCodeInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_area_code_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AreaCodeInfo record);
}