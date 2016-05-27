package com.swtec.sw.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.swtec.sw.persist.model.Bill;
import com.swtec.sw.persist.model.CommodityBill;
import com.swtec.sw.persist.model.ext.BillExt;
/**
 * 商品单号接口
 * @author chengkang
 *
 */
public interface BillService extends BaseService{
	/**
	 * 查询列表
	 * @param billExt
	 * @return
	 */
	List<Bill> list(BillExt billExt);
	
	/**
	 * 增加
	 * @param bill
	 */
	public void insert(HttpServletRequest request,Bill bill);
	
	/**
	 * 更新
	 * @param customer
	 */
	public void update(Bill bill);
	
	public void updateComBill(HttpServletRequest request,Bill bill);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id);
	
    /**
     * 根据主键id查询单个信息
     */
    public Bill selectByPrimaryKey(Integer id);

}
