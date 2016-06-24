package com.swtec.sw.service.impl.mt;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.MtTemplateMapperExt;
import com.swtec.sw.persist.model.MtTemplate;
import com.swtec.sw.persist.model.MtTemplateExample;
import com.swtec.sw.persist.model.ext.MtTemplateExt;
import com.swtec.sw.service.impl.BaseServiceImpl;
import com.swtec.sw.service.mt.MtTemplateService;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;
@Service
public class MtTemplateServiceImpl extends BaseServiceImpl implements MtTemplateService{
	@Resource
	private MtTemplateMapperExt mtTemplateMapperExt;
	
	@Override
	public List<MtTemplate> list(MtTemplateExt mtTemplate) {
		MtTemplateExample selParam=new MtTemplateExample();
		MtTemplateExample.Criteria cri = selParam.createCriteria();
		//查询类型
		if(mtTemplate.getType()!=null){
			cri.andTypeEqualTo(mtTemplate.getType());
		}
		selParam.setPageSize(mtTemplate.getRows());
		selParam.setPageBegin(getBegin(mtTemplate.getPage(), mtTemplate.getRows()));
		List<MtTemplate> mtTemplates = null;
		try {
			mtTemplate.setTotal(mtTemplateMapperExt.countByExample(selParam));
			mtTemplates = mtTemplateMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtTemplates;
	}

	@Override
	public void insert(MtTemplate mtTemplate) {
		if(mtTemplate == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = mtTemplateMapperExt.insertSelective(mtTemplate);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public MtTemplate selectByPrimaryKey(Integer id) {
		if(id==null || id==0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		MtTemplate mtTemplate = null;
		try {
			mtTemplate = mtTemplateMapperExt.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtTemplate;
	}

}
