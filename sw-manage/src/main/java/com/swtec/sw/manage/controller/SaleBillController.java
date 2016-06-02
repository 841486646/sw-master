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
import com.swtec.sw.persist.enums.CompanyType;
import com.swtec.sw.persist.model.Bill;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.BillExt;
import com.swtec.sw.persist.model.ext.UserExt;
import com.swtec.sw.service.BillService;
import com.swtec.sw.service.CommodityBillService;
import com.swtec.sw.service.CommodityService;
import com.swtec.sw.service.UserService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.DateUtil;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;
/**
 * 销售单号管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/saleBill")
public class SaleBillController {
	
	@javax.annotation.Resource
	private BillService billService;
	
	@javax.annotation.Resource
	private CommodityBillService commodityBillService;
	
	@javax.annotation.Resource
	private UserService userService;
	
	@javax.annotation.Resource
	private CommodityService commodityService;
	
	/**
	 * 查询入库信息
	 */
	@RequiresPermissions("sale:bill:billView")
	@RequestMapping(value = "/commodityStorageList.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid CommodityStorageList(HttpServletRequest request, ModelMap model, BillExt billExt) {
		List<Bill> bills = billService.list(billExt);
		return new DataGrid(billExt.getTotal(), bills);
	}
	/**
	 * 打开添加页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sale:bill:billView")
	@RequestMapping(value = "/toInsertBill", method = RequestMethod.GET)
	public String toInsertBill(ModelMap model){
		model.addAttribute("companyTypes", CompanyType.values());
		//查询所有user用户信息
		UserExt user=new UserExt();
		List<User> usersList=userService.notPageList(user);
		model.addAttribute("usersList", usersList);
		//入库的类别
		model.addAttribute("billRKTypes", BillRKType.values());
		//随机生成4位数字
		String randomNumber=MyStringUtil.random(4);
		//获取当前时间戳
		String date=DateUtil.nowDateTime().replace("-","").replace(" ","").replace(":","");
		String XSrandomNumber="XS-"+date+"-"+randomNumber;
		model.addAttribute("XSrandomNumber", XSrandomNumber);
		return "sale/saleBillList";
	}
	@RequestMapping(value = "/saveBill", method = RequestMethod.POST)
	@ResponseBody
	public RespResult saveBill(HttpServletRequest request, ModelMap model, Bill bill) {
		billService.insert(request,bill);
		return RespResult.getInstance(RespCode.SUCCESS);
	}

}
