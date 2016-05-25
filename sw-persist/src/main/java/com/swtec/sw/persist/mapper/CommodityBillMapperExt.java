package com.swtec.sw.persist.mapper;

import java.util.List;

import com.swtec.sw.persist.model.CommodityBill;
import com.swtec.sw.persist.model.CommodityBillExample;

public interface CommodityBillMapperExt extends CommodityBillMapper {
	/**
	 * 查询所有数据
	 * @param example
	 * @return
	 */
	List<CommodityBill> selectCommodityBillList(CommodityBillExample example);
}
