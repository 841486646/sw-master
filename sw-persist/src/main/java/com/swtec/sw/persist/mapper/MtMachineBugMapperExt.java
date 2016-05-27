package com.swtec.sw.persist.mapper;

import java.util.List;

import com.swtec.sw.persist.model.MtMachineBugExample;
import com.swtec.sw.persist.model.ext.MtMachineBugExt;


public interface MtMachineBugMapperExt extends MtMachineBugMapper {
	/**
	 * 条件列表查询（拓展）
	 * @param example
	 * @return
	 */
	List<MtMachineBugExt> selectExtByExample(MtMachineBugExample example);
}
