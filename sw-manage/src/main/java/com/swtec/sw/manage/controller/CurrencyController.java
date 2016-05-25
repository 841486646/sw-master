package com.swtec.sw.manage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swtec.sw.persist.enums.CompanyType;
import com.swtec.sw.persist.enums.CustomerGrouping;
import com.swtec.sw.persist.enums.CustomerType;
import com.swtec.sw.persist.enums.WarehouseType;
import com.swtec.sw.service.SupplierService;
/**
 * 通用通用控制器
 * @author chengkang
 *
 */
@Controller
@RequestMapping("/currency")
public class CurrencyController {
	@Resource
	SupplierService supplierService;
	/**
	 * 跳转到供应商界面
	 */
	@RequiresPermissions("currency:supplier:view")
	@RequestMapping(value = "/toSupplierList", method = RequestMethod.GET)
	public String toSupplierList(HttpServletRequest request,String type, ModelMap model) {
		model.addAttribute("type",type);
		return "currency/supplierList";
	}
	/**
	 * 跳转到客户资料界面
	 */
	@RequiresPermissions("currency:customer:view")
	@RequestMapping(value = "/toCustomerList", method = RequestMethod.GET)
	public String toCustomerList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("customerGroupings", CustomerGrouping.values());
		model.addAttribute("customerTypes", CustomerType.values());
		return "currency/customerList";
	}
	/**
	 * 跳转商品页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequiresPermissions("currency:company:view")
	@RequestMapping(value = "/commodityList", method = RequestMethod.GET)
	public String commodityList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("companyTypes", CompanyType.values());
		model.addAttribute("warehouseTypes", WarehouseType.values());
		return "currency/commodityList";
	}
}
