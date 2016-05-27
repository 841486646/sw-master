package com.swtec.sw.persist.mapper;

import com.swtec.sw.persist.model.Commodity;

public interface CommodityMapperExt extends CommodityMapper {
	/**
	 * 更新库存数量
	 * @param record
	 * @return
	 */
	int updateByIdCommodity(Commodity record);
}
