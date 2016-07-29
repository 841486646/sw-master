package com.swtec.sw.service;

import java.util.List;

import com.swtec.sw.persist.model.Receivables;
import com.swtec.sw.persist.model.ext.ReceivablesExt;

/**
 * 销售/维修收款接口
 * @author chengkang
 *
 */
public interface ReceivablesService extends BaseService{
	/**
	 * 查询
	 * @param receivables
	 * @return
	 */
	List<Receivables> list(ReceivablesExt receivables);
	/**
	 * 添加
	 * @param receivables
	 */
	void insert(Receivables receivables,String receivablesTimes);
	
	/**
	 * 根据di更新
	 * @param receivables
	 */
	void update(Receivables receivables,String receivablesTimes);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(Integer id);
	/**
	 * 根据Id查询一条信息
	 * @param id
	 * @return
	 */
	Receivables selectByIdReceivables(Integer id);
}
