package com.swtec.sw.manage.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swtec.sw.persist.enums.ShowType;
import com.swtec.sw.persist.model.CommodityCategory;
import com.swtec.sw.persist.model.ext.Combotree;
import com.swtec.sw.persist.model.ext.CommodityCategoryExt;
import com.swtec.sw.service.CommodityCategoryService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

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
		return "commodityCategory/commodityCategory";
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
	/**
	 * 查询商品类别列表，返回全部类别,已tree的形式返回
	 */
	@RequiresPermissions("warehouse:commodityCategory:treeView")
	@RequestMapping(value = "/findCommodityCategoryCheckedTree", method = RequestMethod.GET)
	@ResponseBody
	public List<Combotree> findCommodityCategoryCheckedTree(HttpServletRequest request, ModelMap model, 
			Integer roleId) {
		List<Combotree> combotrees = commodityCategoryService.listComboTree();
		return combotrees;
	}
    /**
	 * 保存信息
	 */
	@RequiresPermissions("warehouse:commodityCategory:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, CommodityCategory commodityCategory) {
		commodityCategoryService.insert(commodityCategory);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	/**
	 * 更新资源信息
	 */
	@RequiresPermissions("warehouse:commodityCategory:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, CommodityCategory commodityCategory) {
		commodityCategoryService.update(commodityCategory);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定资源信息
	 */
	@RequiresPermissions("warehouse:commodityCategory:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer id) {
		commodityCategoryService.delete(id);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
}
