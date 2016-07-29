package com.swtec.sw.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swtec.sw.persist.model.Bill;
import com.swtec.sw.persist.model.Receivables;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.UserExt;
import com.swtec.sw.service.BillService;
import com.swtec.sw.service.ReceivablesService;
import com.swtec.sw.service.UserService;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

/**
 * 销售/维修结算付款控制器
 * @author chengkang
 *
 */
@Controller
@RequestMapping("/receivables")
public class ReceivablesController {
	
	@javax.annotation.Resource
	private UserService userService;
	@javax.annotation.Resource
	private ReceivablesService receivablesService;
	@javax.annotation.Resource
	private BillService billService;
	/**
	 * 打开维修/销售入账添加页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("sale:bill:saveReceivables")
	@RequestMapping(value = "/toInsertReceivables", method = RequestMethod.GET)
	public String toInsertReceivables(Model model,Integer id){
		//查询User用户信息
		UserExt user=new UserExt();
		List<User> usersList=userService.notPageList(user);
		model.addAttribute("usersList", usersList);
		//根据id查询单号信息
		Bill bView=billService.selectByPrimaryKey(id);
		model.addAttribute("bView", bView);
		return "receivables/receivables";
	}
	
	/**
	 * 增加
	 * @param request
	 * @param model
	 * @param receivables
	 * @return
	 */
	@RequiresPermissions("sale:bill:saveReceivables")
	@RequestMapping(value = "/saveReceivables", method = RequestMethod.POST)
	@ResponseBody
	public RespResult saveReceivables(HttpServletRequest request, ModelMap model,Receivables receivables,String receivablesTimes) {
		receivablesService.insert(receivables,receivablesTimes);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	/**
	 * 修改
	 * @param request
	 * @param model
	 * @param receivables
	 * @return
	 */
	@RequiresPermissions("sale:bill:updateReceivables")
	@RequestMapping(value = "/updateReceivables", method = RequestMethod.POST)
	@ResponseBody
	public RespResult updateReceivables(HttpServletRequest request, ModelMap model,Receivables receivables,String receivablesTimes) {
		receivablesService.update(receivables,receivablesTimes);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除
	 * @param request
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sale:bill:deleteReceivables")
	@RequestMapping(value = "/deleteReceivables", method = RequestMethod.POST)
	@ResponseBody
	public RespResult deleteReceivables(HttpServletRequest request,Integer id) {
		receivablesService.delete(id);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	/**
	 * 打开修改页面
	 * @param model
	 * @param id
	 * @return
	 */
	@RequiresPermissions("sale:bill:updateReceivables")
	@RequestMapping(value = "/toUpdateReceivables", method = RequestMethod.GET)
	public String toUpdateReceivables(Model model,Integer id){
		UserExt user=new UserExt();
		List<User> usersList=userService.notPageList(user);
		model.addAttribute("usersList", usersList);
		Receivables receivables=receivablesService.selectByIdReceivables(id);
		model.addAttribute("rView", receivables);
		return "receivables/updateReceivables";
	}
}
