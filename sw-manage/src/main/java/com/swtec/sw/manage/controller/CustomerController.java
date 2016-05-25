package com.swtec.sw.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swtec.sw.persist.enums.CustomerGrouping;
import com.swtec.sw.persist.enums.CustomerType;
import com.swtec.sw.persist.model.Customer;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.CustomerExt;
import com.swtec.sw.service.CustomerService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{
	@Resource
	CustomerService customerService;
	
	/**
	 * 跳转到客户资料界面
	 */
	@RequiresPermissions("datas:customer:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("customerGroupings", CustomerGrouping.values());
		model.addAttribute("customerTypes", CustomerType.values());
		return "datas/customer";
	}
	
	/**
	 * 客户资料管理页面，返回grid的json数据
	 */
	@RequiresPermissions("datas:customer:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, CustomerExt customer) {
		List<Customer> customers = customerService.list(customer);
		return new DataGrid(customer.getTotal(), customers);
	}
	/**
	 * 增加客户资料信息
	 * @param request
	 * @param model
	 * @param customer
	 * @return
	 */
	@RequiresPermissions("datas:customer:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, Customer customer) {
		User users=this.getCurrentUser(request);
		if(users==null){
			throw new BizException(RespCode.USER_SESSION_ERR);
		}else{
			customer.setUserId(users.getId());
		}
		customerService.insert(customer);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 更新客户资料信息
	 * @param request
	 * @param model
	 * @param customer
	 * @return
	 */
	@RequiresPermissions("datas:customer:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, Customer customer) {
		customerService.update(customer);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定客户资料信息
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequiresPermissions("datas:customer:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer id) {
		customerService.delete(id);
		return RespResult.getInstance(RespCode.SUCCESS);
	}

}
