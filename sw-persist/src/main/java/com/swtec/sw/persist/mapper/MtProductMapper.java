package com.swtec.sw.persist.mapper;

import com.swtec.sw.persist.model.MtProduct;
import com.swtec.sw.persist.model.MtProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MtProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    int countByExample(MtProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    int deleteByExample(MtProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    int insert(MtProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    int insertSelective(MtProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    List<MtProduct> selectByExample(MtProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    MtProduct selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MtProduct record, @Param("example") MtProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MtProduct record, @Param("example") MtProductExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MtProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_product
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MtProduct record);
}