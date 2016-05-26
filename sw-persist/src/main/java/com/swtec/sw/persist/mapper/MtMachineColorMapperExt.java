package com.swtec.sw.persist.mapper;

import java.util.List;

import com.swtec.sw.persist.model.MtMachineColor;

public interface MtMachineColorMapperExt extends MtMachineColorMapper {

	/**
	 * 根据机型ID查询列表信息
	 * @return 颜色列表
	 */
	List<MtMachineColor> listByMachineTypeId(Integer machineTypeId);
}
