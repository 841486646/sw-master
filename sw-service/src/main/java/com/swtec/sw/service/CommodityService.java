package com.swtec.sw.service;

import java.util.List;

import com.swtec.sw.persist.model.Commodity;
import com.swtec.sw.persist.model.ext.CommodityExt;
/**
 * 商品
 * @author chengkang
 *
 */
public interface CommodityService extends BaseService{
	/**
	 * 查询
	 * @param commodity
	 * @return
	 */
	List<Commodity> list(CommodityExt commodity,String identifierString,String type);
	
	/**
	 * 不需要分页查询全部商品信息
	 * @param commodity
	 * @return
	 */
	List<Commodity> notPageList(CommodityExt commodity);
	
	/**
	 * 添加指定商品
	 * @param commodity
	 */
	void insert(Commodity commodity);
	
	/**
	 * 更新指定商品
	 * @param commodity
	 */
	void update(Commodity commodity);
	/**
	 * 更新 库存数量相加
	 * @param commodity
	 */
	void updateByIdCommodity(Commodity commodity);
	
	/**
	 * 删除商品
	 * @param commodity 资源ID
	 */
	public void delete(Integer id);

}
