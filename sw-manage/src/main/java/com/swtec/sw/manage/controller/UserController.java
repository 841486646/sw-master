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
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.RoleExt;
import com.swtec.sw.persist.model.ext.UserExt;
import com.swtec.sw.service.ResourceService;
import com.swtec.sw.service.RoleService;
import com.swtec.sw.service.UserService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

/**
 * 用户管理控制类
 * @author shaowei
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Resource
	private UserService userService;
	
	@Resource
	private ResourceService resourceService;
	
	@Resource
	private RoleService roleService;

	/**
	 * 跳转到用户列表页面
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("userStates", UserState.values());
		return "sys/user";
	}
	
	/**
	 * 查询用户列表，返回grid的json数据
	 */
	@RequiresPermissions("sys:user:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, UserExt user) {
		List<User> users = userService.list(user);
		return new DataGrid(user.getTotal(), users);
	}
	
	/**
	 * 保存用户信息
	 */
	@RequiresPermissions("sys:user:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, User user) {
		userService.insert(user);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 更新用户信息
	 */
	@RequiresPermissions("sys:user:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, User user) {
		userService.update(user);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定用户信息
	 */
	@RequiresPermissions("sys:user:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer userId) {
		userService.delete(userId);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 修改用户密码
	 */
	@RequiresPermissions("sys:user:repwd")
	@RequestMapping(value = "/resetpwd", method = RequestMethod.POST)
	@ResponseBody
	public RespResult resetpwd(HttpServletRequest request, ModelMap model, 
			Integer id, String pwd, String loginName) {
		userService.resetpwd(id, pwd, loginName);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 查询用户的角色树形列表，返回全部角色，标识用户已有的角色
	 */
	@RequiresPermissions("sys:user:giverole")
	@RequestMapping(value = "/findRolesWithUserChecked", method = RequestMethod.POST)
	@ResponseBody
	public RespResult findRolesWithUserChecked(HttpServletRequest request, ModelMap model, 
			Integer userId) {
		List<RoleExt> roles = roleService.findRolesWithUserChecked(userId);
		return RespResult.getInstance(RespCode.SUCCESS, roles);
	}
	
	/**
	 * 分配保存的角色
	 */
	@RequiresPermissions("sys:user:giverole")
	@RequestMapping(value = "/giveRoles", method = RequestMethod.POST)
	@ResponseBody
	public RespResult giveRoles(HttpServletRequest request, ModelMap model, 
			Integer userId, String roleIds) {
		roleService.giveRoles(userId, roleIds);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
}
