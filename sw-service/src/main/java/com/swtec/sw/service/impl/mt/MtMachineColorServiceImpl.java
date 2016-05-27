package com.swtec.sw.service.impl.mt;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.MtMachineColorMapperExt;
import com.swtec.sw.persist.model.MtMachineColor;
import com.swtec.sw.persist.model.MtMachineColorExample;
import com.swtec.sw.persist.model.ext.MtMachineColorExt;
import com.swtec.sw.service.impl.BaseServiceImpl;
import com.swtec.sw.service.mt.MtMachineColorService;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

@Service
public class MtMachineColorServiceImpl extends BaseServiceImpl implements MtMachineColorService {
	@Resource
	private MtMachineColorMapperExt mtMachineColorMapperExt;

	@Override
	public List<MtMachineColor> listByMachineTypeId(Integer machineTypeId) {
		List<MtMachineColor> mtMachineColors = null;
		try {
			mtMachineColors = mtMachineColorMapperExt.listByMachineTypeId(machineTypeId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineColors;
	}

	@Override
	public List<MtMachineColor> list(MtMachineColorExt mtMachineColor) {
		MtMachineColorExample selParam = new MtMachineColorExample();
		MtMachineColorExample.Criteria cri = selParam.createCriteria();
		if(MyStringUtil.isNotBlank(mtMachineColor.getName())){
			cri.andNameEqualTo(mtMachineColor.getName());
		}
		
		selParam.setPageSize(mtMachineColor.getRows());
		selParam.setPageBegin(getBegin(mtMachineColor.getPage(), mtMachineColor.getRows()));
		List<MtMachineColor> mtMachineColors = null;
		try {
			mtMachineColor.setTotal(mtMachineColorMapperExt.countByExample(selParam));
			mtMachineColors = mtMachineColorMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineColors;
	}

	@Override
	public void insert(MtMachineColor mtMachineColor) {
		if(mtMachineColor == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(MyStringUtil.isEmpty(mtMachineColor.getName())){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = mtMachineColorMapperExt.insertSelective(mtMachineColor);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(MtMachineColor mtMachineColor) {
		if(mtMachineColor == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(mtMachineColor.getId() == null || mtMachineColor.getId() == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = mtMachineColorMapperExt.updateByPrimaryKeySelective(mtMachineColor);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void delete(Integer mtMachineColorId) {
		if(mtMachineColorId == null || mtMachineColorId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = mtMachineColorMapperExt.deleteByPrimaryKey(mtMachineColorId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public List<MtMachineColor> listAll() {
		List<MtMachineColor> mtMachineColors = null;
		try {
			mtMachineColors = mtMachineColorMapperExt.selectByExample(null);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineColors;
	}

}
