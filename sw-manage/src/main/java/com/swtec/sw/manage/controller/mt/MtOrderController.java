package com.swtec.sw.manage.controller.mt;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swtec.sw.manage.controller.BaseController;
import com.swtec.sw.persist.model.MtOrder;
import com.swtec.sw.persist.model.ext.MtOrderExt;
import com.swtec.sw.service.mt.MtOrderService;
import com.swtec.sw.utils.DataGrid;

/**
 * 订单控制类
 * @author shaowei
 */
@Controller
@RequestMapping("/mtOrder")
public class MtOrderController extends BaseController{
	@Resource
	private MtOrderService mtOrderService;

	/**
	 * 跳转到订单列表页面
	 */
	@RequiresPermissions("mt:order:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		return "mtmg/order";
	}
	
	/**
	 * 查询订单列表，返回grid的json数据
	 */
	@RequiresPermissions("mt:order:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, MtOrderExt mtOrder) {
		List<MtOrder> mtOrders = mtOrderService.list(mtOrder);
		return new DataGrid(mtOrder.getTotal(), mtOrders);
	}
}
