package com.swtec.sw.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swtec.sw.persist.enums.BillRKType;
import com.swtec.sw.persist.model.CommodityBill;
import com.swtec.sw.persist.model.ext.CommodityBillExt;
import com.swtec.sw.service.CommodityBillService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

@Controller
@RequestMapping("/commodityBill")
public class CommodityBillController extends BaseController{
	
	@javax.annotation.Resource
	private CommodityBillService commodityBillService;
	
	/**
	 * 跳转到商品入库页面
	 */
	@RequiresPermissions("warehouse:commodityBill:commodityBillView")
	@RequestMapping(value = "/toCommodityBillList", method = RequestMethod.GET)
	public String toCommodityStorage(HttpServletRequest request, ModelMap model) {
		model.addAttribute("billRKTypes", BillRKType.values());
		return "bill/billList";
	}
	/**
	 *查询商品单号信息
	 */
	@RequiresPermissions("warehouse:commodityBill:commodityBillView")
	@RequestMapping(value = "/commodityBillList.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid CommodityStorageList(HttpServletRequest request, ModelMap model, CommodityBillExt commodityBillExt) {
		List<CommodityBill> bills = commodityBillService.list(commodityBillExt);
		return new DataGrid(commodityBillExt.getTotal(), bills);
	}
	/**
	 * 增加单号商品信息
	 * @param request
	 * @param model
	 * @param commodityBill
	 * @return
	 */
	@RequiresPermissions("warehouse:commodityBill:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, CommodityBill commodityBill) {
		commodityBillService.insert(commodityBill);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	/**
	 * 更新一条单号商品信息
	 * @param request
	 * @param model
	 * @param commodityBill
	 * @return
	 */
	@RequiresPermissions("warehouse:commodityBill:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, CommodityBill commodityBill) {
		commodityBillService.update(commodityBill);
		return RespResult.getInstance(RespCode.SUCCESS);
	}

}
