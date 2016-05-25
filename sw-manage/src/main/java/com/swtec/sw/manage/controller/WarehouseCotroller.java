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

import com.swtec.sw.persist.enums.CompanyType;
import com.swtec.sw.persist.enums.WarehouseType;
import com.swtec.sw.persist.model.Commodity;
import com.swtec.sw.persist.model.ext.CommodityExt;
import com.swtec.sw.service.CommodityService;
import com.swtec.sw.utils.DataGrid;
/**
 * 仓库控制管理类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/warehouse")
public class WarehouseCotroller {
	@Resource
	CommodityService commodityService;
	
	/**
	 * 跳转到商品库存预警页面
	 */
	@RequiresPermissions("warehouse:commodity:view")
	@RequestMapping(value = "/stockEarlyWarningList", method = RequestMethod.GET)
	public String toStockEarlyWarning(HttpServletRequest request, ModelMap model) {
		model.addAttribute("companyTypes", CompanyType.values());
		model.addAttribute("warehouseTypes", WarehouseType.values());
		return "warehouse/stockEarlyWarning";
	}
	
	/**
	 * 查询商品管理表，返回grid的json数据
	 */
	@RequiresPermissions("warehouse:commodity:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request,String ids, ModelMap model, CommodityExt commodity,String type) {
		//String type="1";
		List<Commodity> commoditys = commodityService.list(commodity,ids,type);
		return new DataGrid(commodity.getTotal(), commoditys);
	}
}
