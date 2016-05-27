package com.swtec.sw.service;

import java.util.List;

import com.swtec.sw.persist.model.CommodityBill;
import com.swtec.sw.persist.model.Resource;
import com.swtec.sw.persist.model.ext.CommodityBillExt;

public interface CommodityBillService extends BaseService{
	/**
	 * 查询列表
	 * @param customer
	 * @return
	 */
	List<CommodityBill> list(CommodityBillExt commodityBillExt);
	
	/**
	 * 增加
	 * @param commodityBill
	 */
	public void insert(CommodityBill commodityBill);
	
	/**
	 * 更新
	 * @param commodityBill
	 */
	public void update(CommodityBill commodityBill);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer ids);
	
	/**
	 * 查询是否重复
	 * @param billId
	 * @return
	 */
	int selectWhetherToRepeat(Integer billId);
	
}
