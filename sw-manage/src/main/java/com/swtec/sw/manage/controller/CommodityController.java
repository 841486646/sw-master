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

import com.swtec.sw.persist.enums.UserState;
import com.swtec.sw.persist.model.Commodity;
import com.swtec.sw.persist.model.ext.CommodityExt;
import com.swtec.sw.service.CommodityService;
import com.swtec.sw.utils.DataGrid;

@Controller
@RequestMapping("/commodity")
public class CommodityController extends BaseController{
	@Resource
	CommodityService commodityService;
	/**
	 * 跳转到商品管理页面
	 */
	@RequiresPermissions("warehouse:commodity:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("userStates", UserState.values());
		return "warehouse/commodityList";
	}
	
	/**
	 * 查询商品管理表，返回grid的json数据
	 */
	@RequiresPermissions("warehouse:commodity:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, CommodityExt commodity) {
		List<Commodity> commoditys = commodityService.list(commodity);
		return new DataGrid(commodity.getTotal(), commoditys);
	}

}
