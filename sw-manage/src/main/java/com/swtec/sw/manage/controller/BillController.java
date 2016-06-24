package com.swtec.sw.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swtec.sw.persist.enums.BillRKState;
import com.swtec.sw.persist.enums.BillRKType;
import com.swtec.sw.persist.enums.CompanyType;
import com.swtec.sw.persist.model.Bill;
import com.swtec.sw.persist.model.Commodity;
import com.swtec.sw.persist.model.CommodityBill;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.BillExt;
import com.swtec.sw.persist.model.ext.CommodityBillExt;
import com.swtec.sw.persist.model.ext.CommodityExt;
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
 * 单据控制器（包括入库）
 * @author chengkang
 *
 */
@Controller
@RequestMapping("/bill")
public class BillController extends BaseController{
	@javax.annotation.Resource
	private BillService billService;
	
	@javax.annotation.Resource
	private CommodityBillService commodityBillService;
	
	@javax.annotation.Resource
	private UserService userService;
	
	@javax.annotation.Resource
	private CommodityService commodityService;
	
	/**
	 * 跳转到商品入库页面
	 */
	@RequiresPermissions("warehouse:bill:billView")
	@RequestMapping(value = "/toBillList", method = RequestMethod.GET)
	public String toCommodityStorage(HttpServletRequest request, ModelMap model) {
		UserExt user=new UserExt();
		List<User> usersList=userService.notPageList(user);
		model.addAttribute("usersList", usersList);
		model.addAttribute("billRKTypes", BillRKType.values());
		return "bill/billList";
	}
	/**
	 * 查询入库信息
	 */
	@RequiresPermissions("warehouse:bill:billView")
	@RequestMapping(value = "/commodityStorageList.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid CommodityStorageList(HttpServletRequest request, ModelMap model, BillExt billExt) {
		int billType=1;
		List<Bill> bills = billService.list(billExt,billType);
		return new DataGrid(billExt.getTotal(), bills);
	}
	/**
	 * 打开添加页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("warehouse:bill:saveBill")
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
		String date=DateUtil.nowDateTime().replace("-","").replace(" ","").replace(":","");
		String RKrandomNumber="RK-"+date+"-"+randomNumber;
		model.addAttribute("RKrandomNumber", RKrandomNumber);
		return "bill/insertBill";
	}
	@RequestMapping(value = "/saveBill", method = RequestMethod.POST)
	@ResponseBody
	public RespResult saveBill(HttpServletRequest request, ModelMap model, Bill bill) {
		billService.insert(request,bill);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	/**
	 * 删除单号下面所有的商品
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequiresPermissions("warehouse:bill:deleteBill")
	@RequestMapping(value = "/deleteBill", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer id) {
		billService.delete(id);
		CommodityBillExt commodityBillExt=new CommodityBillExt();
		commodityBillExt.setBillId(id);
		commodityBillExt.setRows(20);
		List<CommodityBill> list=commodityBillService.list(commodityBillExt);
		if(list.size()!=0 || list!=null){
			for (int i = 0; i < list.size(); i++) {
				commodityBillService.delete(list.get(i).getId());
			}
		}
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	/**
	 * 打开修改页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("warehouse:bill:updateBill")
	@RequestMapping(value = "/toupdateBill", method = RequestMethod.GET)
	public String toupdateBill(ModelMap model,Integer id){
		//查询单号信息
		if(id!=null){
			Bill bill=billService.selectByPrimaryKey(id);
			model.addAttribute("billView", bill);
		}
		model.addAttribute("companyTypes", CompanyType.values());
		//查询所有user用户信息
		UserExt user=new UserExt();
		List<User> usersList=userService.notPageList(user);
		model.addAttribute("usersList", usersList);
		//入库的类别
		model.addAttribute("billRKTypes", BillRKType.values());
		//查询所有商品信息
		CommodityExt commodity=new CommodityExt();
		List<Commodity> commodityList=commodityService.notPageList(commodity);
		model.addAttribute("commodityList", commodityList);
		return "bill/updateBill";
	}
	/**
	 * 更新一条数据
	 * @param request
	 * @param model
	 * @param bill
	 * @return
	 */
	@RequiresPermissions("warehouse:bill:updateBill")
	@RequestMapping(value = "/updateBill", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, Bill bill) {
		//根据session获取当前修改人的id
		User user=this.getCurrentUser(request);
		if(user.getId() != null){
			bill.setExamineUserId(user.getId());
		}
		billService.update(bill);
		//根据biiId获取商品单号里面商品信息，并且进行入库的操作
		if(bill.getId()!=null){
			CommodityBillExt commodityBillExt=new CommodityBillExt();
			commodityBillExt.setBillId(bill.getId());
			commodityBillExt.setRows(50);
			List<CommodityBill> list=commodityBillService.list(commodityBillExt);
			if(list.size()!=0 || list!=null){
				for (int i = 0; i < list.size(); i++) {
					Commodity commodity=new Commodity();
					commodity.setId(list.get(i).getCommodityId());
					//跟新库存数量
					commodity.setCommodityNumber(list.get(i).getCommodityNumber());
					commodityService.updateByIdCommodity(commodity);
				}
			}
		}
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	/**
	 * 跳转到商品入库查询页面
	 */
	@RequiresPermissions("warehouse:bill:billView")
	@RequestMapping(value = "/toBillEarchList", method = RequestMethod.GET)
	public String toBillEarchList(HttpServletRequest request, ModelMap model) {
		UserExt user=new UserExt();
		List<User> usersList=userService.notPageList(user);
		model.addAttribute("usersList", usersList);
		model.addAttribute("billRKTypes", BillRKType.values());
		return "bill/billEarchList";
	}
	/**
	 * 查询入库查询信息
	 */
	@RequiresPermissions("warehouse:bill:billView")
	@RequestMapping(value = "/commodityEarchList.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid commodityEarchList(HttpServletRequest request, ModelMap model, BillExt billExt) {
		int billType=1;
		List<Bill> bills = billService.list(billExt,billType);
		return new DataGrid(billExt.getTotal(), bills);
	}
	/**
	 * 跟新商品单号信息
	 * @param request
	 * @param model
	 * @param bill
	 * @return
	 */
	@RequiresPermissions("warehouse:bill:updateBill")
	@RequestMapping(value = "/updateCommodityBill", method = RequestMethod.POST)
	@ResponseBody
	public RespResult updateBill(HttpServletRequest request, ModelMap model, Bill bill) {
		billService.updateComBill(request,bill);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	/**
	 * 跳转到入库商品查询
	 */
	@RequiresPermissions("warehouse:bill:rkBillView")
	@RequestMapping(value = "/toRKCommodityBillList", method = RequestMethod.GET)
	public String toRKCommodityBillList(HttpServletRequest request, ModelMap model) {
		UserExt user=new UserExt();
		List<User> usersList=userService.notPageList(user);
		model.addAttribute("usersList", usersList);
		model.addAttribute("billRKTypes", BillRKType.values());
		model.addAttribute("billRKStates", BillRKState.values());
		return "bill/rkBillList";
	}
	/**
	 * 入库商品查询
	 */
	@RequiresPermissions("warehouse:bill:rkBillView")
	@RequestMapping(value = "/RKCommodityBillList.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid RKCommodityBillList(HttpServletRequest request, ModelMap model, BillExt billExt) {
		int billType=1;
		List<Bill> bills = billService.list(billExt,billType);
		return new DataGrid(billExt.getTotal(), bills);
	}
}
