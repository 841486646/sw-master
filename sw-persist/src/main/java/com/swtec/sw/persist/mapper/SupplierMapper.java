package com.swtec.sw.persist.mapper;

import com.swtec.sw.persist.model.Supplier;
import com.swtec.sw.persist.model.SupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SupplierMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    int countByExample(SupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    int deleteByExample(SupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    int insert(Supplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    int insertSelective(Supplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    List<Supplier> selectByExample(SupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    Supplier selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Supplier record, @Param("example") SupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Supplier record, @Param("example") SupplierExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Supplier record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sw_supplier
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Supplier record);
}