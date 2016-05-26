package com.swtec.sw.service.mt;

import java.util.List;

import com.swtec.sw.persist.model.MtOrder;
import com.swtec.sw.persist.model.ext.MtOrderExt;
import com.swtec.sw.service.BaseService;

/**
 * 订单业务类
 * @author shaowei
 *
 */
public interface MtOrderService extends BaseService {
	/**
	 * 插入记录
	 */
	void insert(MtOrder mtOrder);

	/**
	 * 查询列表
	 */
	List<MtOrder> list(MtOrderExt mtOrder);
}
