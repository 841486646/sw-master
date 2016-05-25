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

import com.swtec.sw.persist.model.Supplier;
import com.swtec.sw.persist.model.ext.SupplierExt;
import com.swtec.sw.service.SupplierService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;
/**
 * 供应商控制类
 * @author chengkang
 *
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController{
	@Resource
	SupplierService supplierService;
	/**
	 * 跳转到供应商界面
	 */
	@RequiresPermissions("datas:supplier:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		return "datas/supplier";
	}
	/**
	 * 查询供应商管理页面，返回grid的json数据
	 */
	@RequiresPermissions("datas:supplier:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, SupplierExt supplierExt) {
		List<Supplier> suppliers = supplierService.list(supplierExt);
		return new DataGrid(supplierExt.getTotal(), suppliers);
	}
	/**
	 * 增加供应商信息
	 * @param request
	 * @param model
	 * @param commodity
	 * @return
	 */
	@RequiresPermissions("datas:supplier:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, Supplier supplier) {
		supplierService.insert(supplier);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 更新供应商信息
	 * @param request
	 * @param model
	 * @param commodity
	 * @return
	 */
	@RequiresPermissions("datas:supplier:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, Supplier supplier) {
		supplierService.update(supplier);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定供应商信息
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequiresPermissions("datas:supplier:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer id) {
		supplierService.delete(id);
		return RespResult.getInstance(RespCode.SUCCESS);
	}

}
