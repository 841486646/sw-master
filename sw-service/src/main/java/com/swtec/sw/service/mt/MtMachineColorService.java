package com.swtec.sw.service.mt;

import java.util.List;

import com.swtec.sw.persist.model.MtMachineColor;
import com.swtec.sw.persist.model.ext.MtMachineColorExt;
import com.swtec.sw.service.BaseService;

/**
 * 机器颜色业务类
 * @author shaowei
 *
 */
public interface MtMachineColorService extends BaseService {
	/**
	 * 根据机型ID查询列表信息
	 * @return 颜色列表
	 */
	List<MtMachineColor> listByMachineTypeId(Integer machineTypeId);
	/**
	 * 查询所有列表信息
	 */
	List<MtMachineColor> listAll();

	/**
	 * 查询列表信息
	 */
	List<MtMachineColor> list(MtMachineColorExt mtMachineColor);

	/**
	 * 新增
	 */
	void insert(MtMachineColor mtMachineColor);

	/**
	 * 更新
	 */
	void update(MtMachineColor mtMachineColor);

	/**
	 * 删除
	 */
	void delete(Integer mtMachineColorId);
}
