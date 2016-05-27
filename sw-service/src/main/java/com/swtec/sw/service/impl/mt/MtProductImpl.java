package com.swtec.sw.service.impl.mt;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.MtProductMapperExt;
import com.swtec.sw.persist.model.MtProduct;
import com.swtec.sw.persist.model.MtProductExample;
import com.swtec.sw.persist.model.ext.MtProductExt;
import com.swtec.sw.service.impl.BaseServiceImpl;
import com.swtec.sw.service.mt.MtProductService;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

@Service
public class MtProductImpl extends BaseServiceImpl implements MtProductService {
	@Resource
	private MtProductMapperExt mtProductMapperExt;
	
	@Override
	public List<MtProduct> list(MtProductExt mtProduct) {
		MtProductExample selParam = new MtProductExample();
		MtProductExample.Criteria cri = selParam.createCriteria();
		if(MyStringUtil.isNotBlank(mtProduct.getName())){
			cri.andNameEqualTo(mtProduct.getName());
		}
		
		selParam.setPageSize(mtProduct.getRows());
		selParam.setPageBegin(getBegin(mtProduct.getPage(), mtProduct.getRows()));
		List<MtProduct> mtProducts = null;
		try {
			mtProduct.setTotal(mtProductMapperExt.countByExample(selParam));
			mtProducts = mtProductMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtProducts;
	}

	@Override
	public List<MtProduct> listAll() {
		List<MtProduct> mtProducts = null;
		try {
			mtProducts = mtProductMapperExt.selectByExample(null);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtProducts;
	}

	@Override
	public void insert(MtProduct mtProduct) {
		if(mtProduct == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(MyStringUtil.isEmpty(mtProduct.getName()) ||
				MyStringUtil.isEmpty(mtProduct.getImgUrl()) ){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = mtProductMapperExt.insertSelective(mtProduct);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(MtProduct mtProduct) {
		if(mtProduct == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(mtProduct.getId() == null || mtProduct.getId() == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = mtProductMapperExt.updateByPrimaryKeySelective(mtProduct);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void delete(Integer mtProductId) {
		if(mtProductId == null || mtProductId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = mtProductMapperExt.deleteByPrimaryKey(mtProductId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

}
