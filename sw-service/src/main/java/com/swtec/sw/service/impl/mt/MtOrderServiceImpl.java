package com.swtec.sw.service.impl.mt;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.swtec.sw.persist.mapper.MtMachineBugMapperExt;
import com.swtec.sw.persist.mapper.MtOrderMapperExt;
import com.swtec.sw.persist.model.MtMachineBug;
import com.swtec.sw.persist.model.MtOrder;
import com.swtec.sw.persist.model.MtOrderExample;
import com.swtec.sw.persist.model.ext.MtOrderExt;
import com.swtec.sw.service.impl.BaseServiceImpl;
import com.swtec.sw.service.mt.MtOrderService;
import com.swtec.sw.utils.DateUtil;
import com.swtec.sw.utils.HttpRequest;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

@Service
public class MtOrderServiceImpl extends BaseServiceImpl implements MtOrderService {
	@Resource
	private MtOrderMapperExt mtOrderMapperExt;
	@Resource
	private MtMachineBugMapperExt mtMachineBugMapperExt;

	@Override
	public void insert(MtOrder mtOrder) {
		if(mtOrder == null
				|| MyStringUtil.isBlank(mtOrder.getMobile())
				|| MyStringUtil.isBlank(mtOrder.getMachineBugIds())){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		String[] ids = mtOrder.getMachineBugIds().split(",");
		MtMachineBug mtMachineBug = null;
		BigDecimal totalPrice = new BigDecimal(0);
		StringBuilder bugs = new StringBuilder();
		for (String id : ids) {
			mtMachineBug = mtMachineBugMapperExt.selectByPrimaryKey(Integer.parseInt(id));
			if(mtMachineBug != null){
				totalPrice = totalPrice.add(mtMachineBug.getPrice());
				bugs.append(mtMachineBug.getName()).append("--").append(mtMachineBug.getPrice()).append("--");
			}
		}
		mtOrder.setBugs(bugs.toString());
		mtOrder.setTotalPrice(totalPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
		
		int effectLine = 0;
		try {
			effectLine = mtOrderMapperExt.insertSelective(mtOrder);
			//发送短信
			if(effectLine==1){
				String count="mobile="+HttpRequest.mobile+"&content="+DateUtil.dt2Str(mtOrder.getCreateTime(), DateUtil.DT_FMT_5)+"手机"+mtOrder.getMobile()+HttpRequest.content+mtOrder.getBugDetail()+HttpRequest.autograph+"&appkey="+HttpRequest.appkey;
				JSONObject obj=HttpRequest.sendPost(HttpRequest.short_message,count);
				System.out.println("短信============！"+obj);
			}
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public List<MtOrder> list(MtOrderExt mtOrder) {
		MtOrderExample selParam = new MtOrderExample();
		selParam.setOrderByClause("create_time desc");
		MtOrderExample.Criteria cri = selParam.createCriteria();
		if(MyStringUtil.isNotBlank(mtOrder.getMobile())){
			cri.andMobileEqualTo(mtOrder.getMobile());
		}
		if(MyStringUtil.isNotBlank(mtOrder.getRealName())){
			cri.andRealNameEqualTo(mtOrder.getRealName());
		}
		// 查询起时间
		if (MyStringUtil.isNotBlank(mtOrder.getStartTime())) {
			Date stTime = DateUtil.str2Dt(mtOrder.getStartTime() + " 00:00:00", DateUtil.DT_FMT_5);
			if (null != stTime) {
				cri.andCreateTimeGreaterThanOrEqualTo(stTime);
			}
		}
		// 查询截止时间
		if (MyStringUtil.isNotBlank(mtOrder.getEndTime())) {
			Date edTime = DateUtil.str2Dt(mtOrder.getEndTime() + " 23:59:59", DateUtil.DT_FMT_5);
			if (null != edTime) {
				cri.andCreateTimeLessThanOrEqualTo(edTime);
			}
		}
		
		selParam.setPageSize(mtOrder.getRows());
		selParam.setPageBegin(getBegin(mtOrder.getPage(), mtOrder.getRows()));
		List<MtOrder> mtOrders = null;
		try {
			mtOrder.setTotal(mtOrderMapperExt.countByExample(selParam));
			mtOrders = mtOrderMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtOrders;
	}
	
}
