package com.swtec.sw.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swtec.sw.persist.enums.MenuType;
import com.swtec.sw.persist.enums.ShowType;
import com.swtec.sw.persist.model.ext.CommodityCategoryExt;
import com.swtec.sw.service.CommodityCategoryService;
import com.swtec.sw.utils.DataGrid;

@Controller
@RequestMapping("/commodityCategory")
public class CommodityCategoryController {
	
	@javax.annotation.Resource
	private CommodityCategoryService commodityCategoryService;
	/**
	 * 跳转到商品类别列表页面
	 */
	@RequiresPermissions("warehouse:commodityCategory:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("showTypes", ShowType.values());
		model.addAttribute("menuTypes", MenuType.values());
		return "commodityCategory/commodityCategoryList";
	}
	
	/**
     * 查询商品类别列表，以TreeGrid展示.
     */
	@RequiresPermissions("warehouse:commodityCategory:view")
    @RequestMapping(value = "/listTree.grid", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid listTree(HttpServletRequest req) {
    	List<CommodityCategoryExt> commodityCategorys = commodityCategoryService.listTree();
		return new DataGrid(commodityCategorys.size(), commodityCategorys);
    }
}
