package com.swtec.sw.service.mt;

import java.util.List;

import com.swtec.sw.persist.model.MtMachineBug;
import com.swtec.sw.persist.model.ext.Combobox;
import com.swtec.sw.persist.model.ext.MtMachineBugExt;
import com.swtec.sw.service.BaseService;

/**
 * 故障业务类
 * @author shaowei
 *
 */
public interface MtMachineBugService extends BaseService {
	/**
	 * 根据机型ID和颜色ID查询列表信息
	 * @return 故障列表
	 */
	List<MtMachineBug> listByMachineTypeIdAndColorId(Integer machineTypeId, Integer colorId);

	/**
	 * 根据机型ID和颜色ID查询列表信息
	 * @return 故障列表
	 */
	String listByMachineTypeIdAndColorIdToHtml(Integer machineTypeId, Integer colorId);

	/**
	 * 查询列表
	 */
	List<MtMachineBug> list(MtMachineBugExt mtMachineBug);
	
	/**
	 * 查询列表
	 */
	List<MtMachineBugExt> listExt(MtMachineBugExt mtMachineBug);

	/**
	 * 新增
	 */
	void insert(MtMachineBug mtMachineBug);

	/**
	 * 更新
	 */
	void update(MtMachineBug mtMachineBug);

	/**
	 * 删除
	 */
	void delete(Integer mtMachineBugId);

	/**
	 * 新建故障时，查询所有的产品机型列表，combobox类型返回
	 */
	List<Combobox> findProductAndMachineTypes();
}
