package com.swtec.sw.service;

import java.util.List;

import com.swtec.sw.persist.model.Supplier;
import com.swtec.sw.persist.model.ext.SupplierExt;

/**
 * 供应商资料
 * @author chengkang
 *
 */
public interface SupplierService extends BaseService{
	/**
	 * 查询
	 * @param commodity
	 * @return
	 */
	List<Supplier> list(SupplierExt supplierExt);
	
	/**
	 * 增加
	 * @param commodity
	 */
	void insert(Supplier supplier);
	
	/**
	 * 更新供应商信息
	 * @param commodity
	 */
	void update(Supplier supplier);
	
	/**
	 * 删除供应商信息
	 * @param commodity 资源ID
	 */
	public void delete(Integer id);


}
