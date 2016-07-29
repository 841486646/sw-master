package com.swtec.sw.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.BillMapperExt;
import com.swtec.sw.persist.mapper.ReceivablesMapperExt;
import com.swtec.sw.persist.model.Bill;
import com.swtec.sw.persist.model.Receivables;
import com.swtec.sw.persist.model.ReceivablesExample;
import com.swtec.sw.persist.model.ext.ReceivablesExt;
import com.swtec.sw.service.ReceivablesService;
import com.swtec.sw.utils.DateUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;
/**
 * 
 * @author chengkang
 *
 */
@Service
public class ReceivablesServiceImpl extends BaseServiceImpl implements ReceivablesService{
	
	@javax.annotation.Resource
	private ReceivablesMapperExt receivablesMapperExt;
	@javax.annotation.Resource
	private BillMapperExt billMapperExt;
	
	@Override
	public List<Receivables> list(ReceivablesExt receivables) {
		ReceivablesExample selParam=new ReceivablesExample();
		ReceivablesExample.Criteria cri=selParam.createCriteria();
		//根据完结状态查询
		if(receivables.getrState()!=null){
			cri.andRStateEqualTo(receivables.getrState());
		}
		selParam.setPageSize(receivables.getRows());
		selParam.setPageBegin(getBegin(receivables.getPage(), receivables.getRows()));
		List<Receivables> receivablesList=null;
		try {
			receivables.setTotal(receivablesMapperExt.countByExample(selParam));
			receivablesList = receivablesMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return receivablesList;
	}

	@Override
	public void insert(Receivables receivables,String receivablesTimes) {
		if(receivables == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			if(!receivablesTimes.equals("") && receivablesTimes!=null){
				receivables.setReceivablesTime(DateUtil.str2Dt(receivablesTimes,"yyyy-MM-dd"));
			}
			//0:收款完结情况下 bill 单号状态为完结
			if(receivables.getrState()==0){
				Bill bill=new Bill();
				bill.setId(receivables.getBillId());
				bill.setState("sh_success");
				bill.setExamineUserId(receivables.getCreateUserId());
				billMapperExt.updateByPrimaryKeySelective(bill);
			}
			effectLine = receivablesMapperExt.insertSelective(receivables);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(Receivables receivables,String receivablesTimes) {
		if(receivables==null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if(receivables.getId().equals("") || receivables.getId()==0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		int effectLine = 0;
		try {
			if(!receivablesTimes.equals("") && receivablesTimes!=null){
				receivables.setReceivablesTime(DateUtil.str2Dt(receivablesTimes,"yyyy-MM-dd"));
			}
			//0:收款完结情况下 bill 单号状态为完结
			if(receivables.getrState()==0){
				Bill bill=new Bill();
				bill.setId(receivables.getBillId());
				bill.setState("sh_success");
				bill.setExamineUserId(receivables.getCreateUserId());
				billMapperExt.updateByPrimaryKeySelective(bill);
			}
			effectLine = receivablesMapperExt.updateByPrimaryKeySelective(receivables);
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
			effectLine = receivablesMapperExt.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public Receivables selectByIdReceivables(Integer id) {
		if(id == null || id == 0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		Receivables effectLine = null;
		try {
			effectLine = receivablesMapperExt.selectByPrimaryKeyReceivables(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == null){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
		return effectLine;
	}
	
}
