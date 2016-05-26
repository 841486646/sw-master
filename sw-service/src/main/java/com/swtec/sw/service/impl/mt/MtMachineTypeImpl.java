package com.swtec.sw.service.impl.mt;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.MtMachineTypeMapperExt;
import com.swtec.sw.persist.model.MtMachineType;
import com.swtec.sw.persist.model.MtMachineTypeExample;
import com.swtec.sw.persist.model.ext.MtMachineTypeExt;
import com.swtec.sw.service.impl.BaseServiceImpl;
import com.swtec.sw.service.mt.MtMachineTypeService;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

@Service
public class MtMachineTypeImpl extends BaseServiceImpl implements MtMachineTypeService {
	@Resource
	private MtMachineTypeMapperExt mtMachineTypeMapperExt;

	@Override
	public List<MtMachineType> listByProductId(Integer productId) {
		if(productId == null || productId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		MtMachineTypeExample selParam = new MtMachineTypeExample();
		MtMachineTypeExample.Criteria cri = selParam.createCriteria();
		cri.andProductIdEqualTo(productId);
		List<MtMachineType> mtMachineTypes = null;
		try {
			mtMachineTypes = mtMachineTypeMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineTypes;
	}

	@Override
	public MtMachineType selectById(Integer machineTypeId) {
		if(machineTypeId == null || machineTypeId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		MtMachineType mtMachineType = null;
		try {
			mtMachineType = mtMachineTypeMapperExt.selectByPrimaryKey(machineTypeId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineType;
	}

	@Override
	public void insert(MtMachineType mtMachineType) {
		if(mtMachineType == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(MyStringUtil.isEmpty(mtMachineType.getName()) ||
				MyStringUtil.isEmpty(mtMachineType.getImgUrl()) ){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = mtMachineTypeMapperExt.insertSelective(mtMachineType);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public List<MtMachineType> list(MtMachineTypeExt mtMachineType) {
		MtMachineTypeExample selParam = new MtMachineTypeExample();
		MtMachineTypeExample.Criteria cri = selParam.createCriteria();
		if(MyStringUtil.isNotBlank(mtMachineType.getName())){
			cri.andNameEqualTo(mtMachineType.getName());
		}
		
		selParam.setPageSize(mtMachineType.getRows());
		selParam.setPageBegin(getBegin(mtMachineType.getPage(), mtMachineType.getRows()));
		List<MtMachineType> mtMachineTypes = null;
		try {
			mtMachineType.setTotal(mtMachineTypeMapperExt.countByExample(selParam));
			mtMachineTypes = mtMachineTypeMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineTypes;
	}

	@Override
	public void update(MtMachineType mtMachineType) {
		if(mtMachineType == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(mtMachineType.getId() == null || mtMachineType.getId() == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = mtMachineTypeMapperExt.updateByPrimaryKeySelective(mtMachineType);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void delete(Integer mtMachineTypeId) {
		if(mtMachineTypeId == null || mtMachineTypeId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = mtMachineTypeMapperExt.deleteByPrimaryKey(mtMachineTypeId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public List<MtMachineType> listAll() {
		List<MtMachineType> mtMachineTypes = null;
		try {
			mtMachineTypes = mtMachineTypeMapperExt.selectByExample(null);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineTypes;
	}
}
