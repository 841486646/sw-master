package com.swtec.sw.persist.mapper;

import java.util.List;

import com.swtec.sw.persist.model.Bill;
import com.swtec.sw.persist.model.BillExample;

public interface BillMapperExt extends BillMapper {
	/**
	 * 关联插单号和维修、销售单
	 * @param example
	 * @return
	 */
	List<Bill> selectBillReceivables(BillExample example);
}
