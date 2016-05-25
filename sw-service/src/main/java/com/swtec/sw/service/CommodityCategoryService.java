package com.swtec.sw.service;

import java.util.List;

import com.swtec.sw.persist.model.CommodityCategory;
import com.swtec.sw.persist.model.ext.Combotree;
import com.swtec.sw.persist.model.ext.CommodityCategoryExt;

public interface CommodityCategoryService extends BaseService{
	/**
	 * 查询商品类别菜单，处理成树形数据结构返回
	 * @return
	 */
	public List<CommodityCategoryExt> listTree();
	
	/**
     * 按照parendid和权值取出全部的资源列表
     */
    public List<CommodityCategory> findAllWithSort();
    
	/**
	 * 查询资源菜单，处理成树形数据结构返回
	 * @return
	 */
	public List<Combotree> listComboTree();
	
	/**
	 * 添加一条商品类别信息
	 * @param commodityCategory
	 */
	public void insert(CommodityCategory commodityCategory);
	
	/**
	 * 更新商品类别信息
	 * @param resource 商品类别信息
	 */
	public void update(CommodityCategory commodityCategory);
	/**
	 * 删除商品类别信息
	 * @param commodityCategory 资源ID
	 */
	public void delete(Integer id);
    

}
