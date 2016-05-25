package com.swtec.sw.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.CustomerMapperExt;
import com.swtec.sw.persist.model.Customer;
import com.swtec.sw.persist.model.CustomerExample;
import com.swtec.sw.persist.model.ext.CustomerExt;
import com.swtec.sw.service.CustomerService;
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
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService{
	
	@javax.annotation.Resource
	private CustomerMapperExt customerMapperExt;
	
	@Override
	public List<Customer> list(CustomerExt customerExt) {
		CustomerExample selParam=new CustomerExample();
		CustomerExample.Criteria cri=selParam.createCriteria();
		//手机号码查询 
		if(!MyStringUtil.isEmpty(customerExt.getPhone())){
			cri.andPhoneEqualTo(customerExt.getPhone());
		}
		//供应商姓名
		if(!MyStringUtil.isEmpty(customerExt.getCustomerName())){
			cri.andCustomerNameLike("%"+customerExt.getCustomerName()+"%");
		}
		//公司
		if(!MyStringUtil.isEmpty(customerExt.getCompanyName())){
			cri.andCompanyNameLike("%"+customerExt.getCompanyName()+"%");
		}
		selParam.setPageSize(customerExt.getRows());
		selParam.setPageBegin(getBegin(customerExt.getPage(), customerExt.getRows()));
		List<Customer> suppliers=null;
		try {
			customerExt.setTotal(customerMapperExt.countByExample(selParam));
			suppliers = customerMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return suppliers;
	}

	@Override
	public void insert(Customer customer) {
		if(customer == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if (MyStringUtil.isEmpty(customer.getCustomerName())
			||MyStringUtil.isEmpty(customer.getCompanyName())){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = customerMapperExt.insertSelective(customer);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(Customer customer) {
		if(customer==null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if(customer.getId()==null || customer.getId()==0){
			throw new BizException(RespCode.REQ_ID_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = customerMapperExt.updateByPrimaryKeySelective(customer);
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
			effectLine = customerMapperExt.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

}
