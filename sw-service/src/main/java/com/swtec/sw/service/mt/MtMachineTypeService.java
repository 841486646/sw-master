package com.swtec.sw.service.mt;

import java.util.List;

import com.swtec.sw.persist.model.MtMachineType;
import com.swtec.sw.persist.model.ext.MtMachineTypeExt;
import com.swtec.sw.service.BaseService;

/**
 * 机型业务类
 * @author shaowei
 *
 */
public interface MtMachineTypeService extends BaseService {
	/**
	 * 根据产品ID查询列表信息
	 * @return 机型列表
	 */
	List<MtMachineType> listByProductId(Integer productId);
	/**
	 * 查询所有列表信息
	 */
	List<MtMachineType> listAll();

	/**
	 * 根据机型ID查询指定机型信息
	 * @param machineTypeId 机型ID
	 * @return 机型信息
	 */
	MtMachineType selectById(Integer machineTypeId);

	/**
	 * 新增
	 */
	void insert(MtMachineType mtMachineType);

	/**
	 * 查询列表
	 */
	List<MtMachineType> list(MtMachineTypeExt mtMachineType);

	/**
	 * 更新
	 */
	void update(MtMachineType mtMachineType);

	/**
	 * 删除
	 */
	void delete(Integer mtMachineTypeId);
}
