package com.swtec.sw.persist.mapper;

import com.swtec.sw.persist.model.MtOrder;
import com.swtec.sw.persist.model.MtOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MtOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    int countByExample(MtOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    int deleteByExample(MtOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    int insert(MtOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    int insertSelective(MtOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    List<MtOrder> selectByExample(MtOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    MtOrder selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MtOrder record, @Param("example") MtOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MtOrder record, @Param("example") MtOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MtOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MtOrder record);
}