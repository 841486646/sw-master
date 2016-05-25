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

import com.swtec.sw.persist.enums.ShowType;
import com.swtec.sw.persist.model.Role;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.Combotree;
import com.swtec.sw.persist.model.ext.RoleExt;
import com.swtec.sw.service.ResourceService;
import com.swtec.sw.service.RoleService;
import com.swtec.sw.service.UserService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

/**
 * 角色管理控制类
 * @author shaowei
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
	@Resource
	private UserService userService;
	
	@Resource
	private ResourceService resourceService;
	
	@Resource
	private RoleService roleService;

	/**
	 * 跳转到角色列表页面
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("showTypes", ShowType.values());
		return "sys/role";
	}
	
	/**
	 * 查询角色列表，返回grid的json数据
	 */
	@RequiresPermissions("sys:role:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, RoleExt role) {
		List<Role> roles = roleService.list(role);
		return new DataGrid(role.getTotal(), roles);
	}
	
	/**
	 * 保存角色信息
	 */
	@RequiresPermissions("sys:role:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, Role role) {
		User user = getCurrentUser(request);
		role.setCreateId(user.getId());
		roleService.insert(role);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 更新角色信息
	 */
	@RequiresPermissions("sys:role:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, Role role) {
		roleService.update(role);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定角色信息
	 */
	@RequiresPermissions("sys:role:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer roleId) {
		roleService.delete(roleId);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 查询资源树形列表，返回全部资源，标识角色已关联的
	 */
	@RequiresPermissions("sys:role:giveres")
	@RequestMapping(value = "/findResoucesWithRoleChecked", method = RequestMethod.POST)
	@ResponseBody
	public List<Combotree> findResoucesWithRoleChecked(HttpServletRequest request, ModelMap model, 
			Integer roleId) {
		List<Combotree> combotrees = resourceService.listComboTree(roleId);
		return combotrees;
	}
	
	/**
	 * 分配保存的资源
	 */
	@RequiresPermissions("sys:role:giveres")
	@RequestMapping(value = "/giveResources", method = RequestMethod.POST)
	@ResponseBody
	public RespResult giveResources(HttpServletRequest request, ModelMap model, 
			Integer roleId, String resourceIds) {
		resourceService.giveResources(roleId, resourceIds);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
}
