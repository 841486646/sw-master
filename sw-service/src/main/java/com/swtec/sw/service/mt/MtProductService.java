package com.swtec.sw.service.mt;

import java.util.List;

import com.swtec.sw.persist.model.MtProduct;
import com.swtec.sw.persist.model.ext.MtProductExt;
import com.swtec.sw.service.BaseService;

/**
 * 维修产品业务类
 * @author shaowei
 *
 */
public interface MtProductService extends BaseService {
	/**
	 * 查询列表信息
	 * @param role 产品条件封装
	 * @return 产品列表
	 */
	List<MtProduct> list(MtProductExt mtProduct);
	/**
	 * 查询所有列表信息
	 * @return 产品列表
	 */
	List<MtProduct> listAll();
	
	/**
	 * 新增产品
	 * @param mtProduct 产品信息
	 */
	void insert(MtProduct mtProduct);
	
	/**
	 * 更新产品信息
	 * @param mtProduct 产品信息
	 */
	void update(MtProduct mtProduct);
	
	/**
	 * 删除指定产品
	 * @param mtProductId 产品ID
	 */
	void delete(Integer mtProductId);
}
