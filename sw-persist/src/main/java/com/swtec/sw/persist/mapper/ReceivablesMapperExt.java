package com.swtec.sw.persist.mapper;

import com.swtec.sw.persist.model.Receivables;

public interface ReceivablesMapperExt extends ReceivablesMapper {
	/**
	 * 根据id查询一条数据
	 */
	 Receivables selectByPrimaryKeyReceivables(Integer id);
}
