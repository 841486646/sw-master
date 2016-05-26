package com.swtec.sw.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.CommodityBillMapperExt;
import com.swtec.sw.persist.model.CommodityBill;
import com.swtec.sw.persist.model.CommodityBillExample;
import com.swtec.sw.persist.model.ext.CommodityBillExt;
import com.swtec.sw.service.CommodityBillService;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;
/**
 * 
 * @author chengkang
 *
 */
@Service
public class CommodityBillServiceImpl extends BaseServiceImpl implements CommodityBillService{
	@javax.annotation.Resource
	private CommodityBillMapperExt commodityBillMapperExt;
	
	@Override
	public List<CommodityBill> list(CommodityBillExt commodityBillExt) {
		CommodityBillExample selParam=new CommodityBillExample();
		CommodityBillExample.Criteria cri=selParam.createCriteria();
		//根据商品id查询数据
		if(commodityBillExt.getCommodityId()!=null){
			cri.andCommodityIdEqualTo(commodityBillExt.getCommodityId());
		}
		//根据单号id查询数据
		if(commodityBillExt.getBillId()!=null){
			cri.andBillIdEqualTo(commodityBillExt.getBillId());
		}
		selParam.setPageSize(commodityBillExt.getRows());
		selParam.setPageBegin(getBegin(commodityBillExt.getPage(), commodityBillExt.getRows()));
		List<CommodityBill> commodityBills=null;
		try {
			commodityBillExt.setTotal(commodityBillMapperExt.countByExample(selParam));
			commodityBills = commodityBillMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return commodityBills;
	}

	@Override
	public void insert(CommodityBill commodityBill) {
		if(commodityBill == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = commodityBillMapperExt.insertSelective(commodityBill);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(CommodityBill commodityBill) {
		if(commodityBill==null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if(commodityBill.getId()==null || commodityBill.getId()==0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = commodityBillMapperExt.updateByPrimaryKeySelective(commodityBill);
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
			effectLine = commodityBillMapperExt.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

}
