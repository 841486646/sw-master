package com.swtec.sw.service.mt;

import java.util.List;

import com.swtec.sw.persist.model.MtTemplate;
import com.swtec.sw.persist.model.ext.MtTemplateExt;
import com.swtec.sw.service.BaseService;

public interface MtTemplateService extends BaseService{
	/**
	 * 查询信息
	 * @param mtTemplate
	 * @return
	 */
	List<MtTemplate> list(MtTemplateExt mtTemplate);
	/**
	 * 增加操作
	 * @param mtTemplate
	 */
	void insert(MtTemplate mtTemplate);
	/**
	 * 根据id查询一条信息
	 * @param id
	 * @return
	 */
	MtTemplate selectByPrimaryKey(Integer id);
}
