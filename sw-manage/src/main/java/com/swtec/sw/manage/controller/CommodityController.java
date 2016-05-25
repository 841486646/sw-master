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
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.CommodityExt;
import com.swtec.sw.service.CommodityService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

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
		model.addAttribute("companyTypes", CompanyType.values());
		model.addAttribute("warehouseTypes", WarehouseType.values());
		return "warehouse/commodity";
	}
	
	/**
	 * 查询商品管理表，返回grid的json数据
	 */
	@RequiresPermissions("warehouse:commodity:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request,String ids, ModelMap model, CommodityExt commodity) {
		String type=null;
		List<Commodity> commoditys = commodityService.list(commodity,ids,type);
		return new DataGrid(commodity.getTotal(), commoditys);
	}
	
	/**
	 * 增加商品信息
	 * @param request
	 * @param model
	 * @param commodity
	 * @return
	 */
	@RequiresPermissions("warehouse:commodity:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, Commodity commodity) {
		//根据session获取当前登录人的id
		User user=this.getCurrentUser(request);
		if(user.getId() != null){
			commodity.setUserId(user.getId());
		}
		commodityService.insert(commodity);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 更新商品信息
	 * @param request
	 * @param model
	 * @param commodity
	 * @return
	 */
	@RequiresPermissions("warehouse:commodity:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, Commodity commodity) {
		//根据session获取当前修改人的id
		User user=this.getCurrentUser(request);
		if(user.getId() != null){
			commodity.setUserUpdateId(user.getId());
		}
		commodityService.update(commodity);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定商品信息
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequiresPermissions("warehouse:commodity:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer id) {
		commodityService.delete(id);
		return RespResult.getInstance(RespCode.SUCCESS);
	}

}
