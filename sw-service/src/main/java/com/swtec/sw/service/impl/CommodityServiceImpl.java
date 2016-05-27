package com.swtec.sw.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.CommodityMapperExt;
import com.swtec.sw.persist.model.Commodity;
import com.swtec.sw.persist.model.CommodityExample;
import com.swtec.sw.persist.model.ext.CommodityExt;
import com.swtec.sw.service.CommodityService;
import com.swtec.sw.utils.DateUtil;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;
@Service
public class CommodityServiceImpl extends BaseServiceImpl implements CommodityService{
	@Resource
	CommodityMapperExt commodityMapperExt;
	
	@Override
	public List<Commodity> list(CommodityExt commodity,String ids,String type) {
		CommodityExample selParam=new CommodityExample();
		CommodityExample.Criteria cri=selParam.createCriteria();
		if(ids != null ){
			//根据/循环截取
			String[] strArray= ids.split(",");
			List<Integer>idsList =new ArrayList<Integer>();
			for (int i = 0; i < strArray.length; i++) {
				idsList.add(Integer.valueOf(strArray[i]));
			}
			cri.andCommodityCategoryIdIn(idsList);
		}
		//商品名称模糊查询
		if(!MyStringUtil.isEmpty(commodity.getCommodityName())){
			cri.andCommodityNameLike("%"+commodity.getCommodityName()+"%");
		}
		//商品编号
		if(!MyStringUtil.isEmpty(commodity.getCommodityIdentifier())){
			cri.andCommodityIdentifierLike("%"+commodity.getCommodityIdentifier()+"%");
		}
		//仓库查询
		if(!MyStringUtil.isEmpty(commodity.getWarehouseType())){
			cri.andWarehouseTypeEqualTo(commodity.getWarehouseType());
		}
		//type为一  库存预警查询
		if(type=="1" || !MyStringUtil.isEmpty(type)){
			cri.addCriterion("lower_number > commodity_number");
		}
		selParam.setPageSize(commodity.getRows());
		selParam.setPageBegin(getBegin(commodity.getPage(), commodity.getRows()));
		List<Commodity> commoditys=null;
		try {
			commodity.setTotal(commodityMapperExt.countByExample(selParam));
			commoditys = commodityMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return commoditys;
	}

	@Override
	public void insert(Commodity commodity) {
		if(commodity == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if (commodity.getCommodityCategoryId() == null
				|| commodity.getCommodityCategoryId() == 0
				|| MyStringUtil.isEmpty(commodity.getCommodityName())
				|| commodity.getSellingPrice() == null
				|| commodity.getCostPrice() == null
				|| commodity.getUpperLimitNumber() ==null
				|| commodity.getUpperLimitNumber() ==0
				|| commodity.getLowerNumber() == null 
				||commodity.getWholesalePrice() == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if(commodity.getLowerNumber()>commodity.getUpperLimitNumber()){
			throw new BizException(RespCode.NUMBER_lARGE_ERR);
		}
		int effectLine = 0;
		//获取录入时间
		commodity.setCreateTime(DateUtil.dateTime(DateUtil.nowDateTime()));
		//库存数量
		commodity.setCommodityNumber(0);
		try {
			effectLine = commodityMapperExt.insertSelective(commodity);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(Commodity commodity) {
		if(commodity==null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if(commodity.getId()==null || commodity.getId()==0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = commodityMapperExt.updateByPrimaryKeySelective(commodity);
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
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = commodityMapperExt.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public List<Commodity> notPageList(CommodityExt commodity) {
		CommodityExample selParam=new CommodityExample();
		List<Commodity> commoditys=null;
		try {
			commoditys = commodityMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return commoditys;
	}

	@Override
	public void updateByIdCommodity(Commodity commodity) {
		if(commodity==null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if(commodity.getId()==null || commodity.getId()==0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = commodityMapperExt.updateByIdCommodity(commodity);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

}
