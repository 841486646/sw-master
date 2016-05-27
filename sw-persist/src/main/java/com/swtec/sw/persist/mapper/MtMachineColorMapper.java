package com.swtec.sw.persist.mapper;

import com.swtec.sw.persist.model.MtMachineColor;
import com.swtec.sw.persist.model.MtMachineColorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MtMachineColorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    int countByExample(MtMachineColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    int deleteByExample(MtMachineColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    int insert(MtMachineColor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    int insertSelective(MtMachineColor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    List<MtMachineColor> selectByExample(MtMachineColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    MtMachineColor selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") MtMachineColor record, @Param("example") MtMachineColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") MtMachineColor record, @Param("example") MtMachineColorExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MtMachineColor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_mt_machine_color
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MtMachineColor record);
}