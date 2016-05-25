package com.swtec.sw.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.SupplierMapperExt;
import com.swtec.sw.persist.model.Supplier;
import com.swtec.sw.persist.model.SupplierExample;
import com.swtec.sw.persist.model.ext.SupplierExt;
import com.swtec.sw.service.SupplierService;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

@Service
public class SupplierServiceImpl extends BaseServiceImpl implements SupplierService{
	
	@javax.annotation.Resource
	private SupplierMapperExt supplierMapperExt;
	
	@Override
	public List<Supplier> list(SupplierExt supplierExt) {
		SupplierExample selParam=new SupplierExample();
		SupplierExample.Criteria cri=selParam.createCriteria();
		//手机号码查询 
		if(!MyStringUtil.isEmpty(supplierExt.getPhone())){
			cri.andPhoneLike("%"+supplierExt.getPhone()+"%");
		}
		//供应商名称查询
		if(!MyStringUtil.isEmpty(supplierExt.getSupplierName())){
			cri.andSupplierNameLike("%"+supplierExt.getSupplierName()+"%");
		}
		selParam.setPageSize(supplierExt.getRows());
		selParam.setPageBegin(getBegin(supplierExt.getPage(), supplierExt.getRows()));
		List<Supplier> suppliers=null;
		try {
			supplierExt.setTotal(supplierMapperExt.countByExample(selParam));
			suppliers = supplierMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return suppliers;
	}

	@Override
	public void insert(Supplier supplier) {
		if(supplier == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if (MyStringUtil.isEmpty(supplier.getSupplierName())
			||MyStringUtil.isEmpty(supplier.getName())){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = supplierMapperExt.insertSelective(supplier);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(Supplier supplier) {
		if(supplier==null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if(supplier.getId()==null || supplier.getId()==0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = supplierMapperExt.updateByPrimaryKeySelective(supplier);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void delete(Integer id) {
		if(id == null || id == 0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = supplierMapperExt.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

}
