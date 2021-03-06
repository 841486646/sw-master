package com.swtec.sw.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.swtec.sw.persist.mapper.BillMapperExt;
import com.swtec.sw.persist.mapper.CommodityBillMapperExt;
import com.swtec.sw.persist.mapper.CommodityMapperExt;
import com.swtec.sw.persist.mapper.UserMapperExt;
import com.swtec.sw.persist.model.Bill;
import com.swtec.sw.persist.model.BillExample;
import com.swtec.sw.persist.model.Commodity;
import com.swtec.sw.persist.model.CommodityBill;
import com.swtec.sw.persist.model.ext.BillExt;
import com.swtec.sw.service.BillService;
import com.swtec.sw.utils.DateUtil;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;
/**
 * 
 * @author chengkang
 *
 */
@Service
public class BillServiceImpl extends BaseServiceImpl implements BillService{
	@javax.annotation.Resource
	private BillMapperExt billMapperExt;
	
	@javax.annotation.Resource
	private CommodityBillMapperExt commodityBillMapperExt;
	
	@javax.annotation.Resource
	private UserMapperExt userMapperExt;
	
	@javax.annotation.Resource
	private CommodityMapperExt commodityMapperExt;
	
	@Override
	public List<Bill> list(BillExt billExt,int billType) {
		BillExample selParam=new BillExample();
		BillExample.Criteria cri=selParam.createCriteria();
		//根据单号状态查询
		if(!MyStringUtil.isEmpty(billExt.getState()) ){
			cri.andStateEqualTo(billExt.getState());
		}
		if(billType!=0){
			if(billType==1){
				cri.andTypeLike("%"+"RK_"+"%");
			}
			if(billType==2){
				cri.andTypeLike("%"+"XS_"+"%");
			}
		}
		selParam.setPageSize(billExt.getRows());
		selParam.setPageBegin(getBegin(billExt.getPage(), billExt.getRows()));
		List<Bill> suppliers=null;
		try {
			billExt.setTotal(billMapperExt.countByExample(selParam));
			suppliers = billMapperExt.selectBillReceivables(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return suppliers;
	}

	@SuppressWarnings("unused")
	@Override
	public void insert(HttpServletRequest request,Bill bill) {
		CommodityBill commodityBill=new CommodityBill();
		String billObj=request.getParameter("billObj");
		//获取当前录入时间
		String createTime=request.getParameter("createTimes");
		bill.setCreateTime(DateUtil.stringDate(createTime));
		JSONObject object = new JSONObject();
        JSONArray array = JSON.parseArray(billObj);
		if(bill == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		//录入的商品不能为空
		if(array.size()==0 || array==null){
			throw new BizException(RespCode.COMMODITY_NOTNULL_ERROR);
		}
		int effectLine = 0;
		try {
			JSONObject obj = new JSONObject();
			Double totalPrices=0.00;
			for (int i = 0; i < array.size(); i++) {
	        	String str = array.get(i)+"";
	        	obj = JSON.parseObject(str);
	        	//商品数量*商品单价=总价
	        	Double commodityNumber = obj.getDouble("commodityNumber");
	        	Double unitPrice = obj.getDouble("unitPrice");
	        	Double totalPrice=MyStringUtil.mul(unitPrice, commodityNumber);
	        	totalPrices+=totalPrice;
	        	bill.setTotalPrice(BigDecimal.valueOf(totalPrices));
			}
				effectLine = billMapperExt.insertSelective(bill);
				if(effectLine==1){
					//必须有"" 防止强转错误  添加sw_commodity_bill信息表数据
			        for (int i = 0; i < array.size(); i++) {
			        	String str = array.get(i)+"";
			        	object = JSON.parseObject(str);
			        	//商品id
			        	commodityBill.setCommodityId(object.getInteger("commodityId"));
			        	//商品数量
			        	commodityBill.setCommodityNumber(object.getInteger("commodityNumber"));
			        	//商品单价
			        	commodityBill.setUnitPrice(object.getBigDecimal("unitPrice"));
			        	//单号id
			        	commodityBill.setBillId(bill.getId());
			        	//单号类型
			        	commodityBill.setBillType(bill.getType());
			        	commodityBillMapperExt.insertSelective(commodityBill);
					}
				}
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(Bill bill) {
		if(bill==null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if(bill.getId()==null || bill.getId()==0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		int effectLine = 0;
		try {
			bill.setState("sh_success");
			effectLine = billMapperExt.updateByPrimaryKeySelective(bill);
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
			effectLine = billMapperExt.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public Bill selectByPrimaryKey(Integer id) {
		Bill resource = null;
		try {
			resource = billMapperExt.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.REQ_ID_ERROR, e);
		}
		return resource;
	}

	@SuppressWarnings("unused")
	@Override
	public void updateComBill(HttpServletRequest request, Bill bill) {
		CommodityBill commodityBill=new CommodityBill();
		String billObj=request.getParameter("billObj");
		String id=request.getParameter("id");
		//获取当前录入时间
		String createTime=request.getParameter("createTimes");
		bill.setCreateTime(DateUtil.stringDate(createTime));
		bill.setId(Integer.valueOf(id));
		JSONObject object = new JSONObject();
        JSONArray array = JSON.parseArray(billObj);
		if(bill == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		//录入的商品不能为空
		if(array.size()==0 || array==null){
			throw new BizException(RespCode.COMMODITY_NOTNULL_ERROR);
		}
		int effectLine = 0;
		try {
			JSONObject obj = new JSONObject();
			Double totalPrices=0.00;
			for (int i = 0; i < array.size(); i++) {
	        	String str = array.get(i)+"";
	        	obj = JSON.parseObject(str);
	        	//商品数量*商品单价=总价
	        	Double commodityNumber = obj.getDouble("commodityNumber");
	        	Double unitPrice = obj.getDouble("unitPrice");
	        	Double totalPrice=MyStringUtil.mul(unitPrice, commodityNumber);
	        	totalPrices+=totalPrice;
	        	bill.setTotalPrice(BigDecimal.valueOf(totalPrices));
			}
				effectLine = billMapperExt.updateByPrimaryKeySelective(bill);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}
	
}
