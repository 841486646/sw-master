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
import com.swtec.sw.persist.model.Resource;
import com.swtec.sw.persist.model.ext.ResourceExt;
import com.swtec.sw.service.ResourceService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

/**
 * 资源管理控制类
 * @author shaowei
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController{
	@javax.annotation.Resource
	private ResourceService resourceService;
	
	/**
	 * 跳转到资源列表页面
	 */
	@RequiresPermissions("sys:resource:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("showTypes", ShowType.values());
		model.addAttribute("menuTypes", MenuType.values());
		return "sys/resource";
	}
	
	/**
     * 查询资源列表，以TreeGrid展示.
     */
	@RequiresPermissions("sys:resource:view")
    @RequestMapping(value = "/listTree.grid", method = RequestMethod.POST)
    @ResponseBody
    public DataGrid listTree(HttpServletRequest req) {
    	List<ResourceExt> resources = resourceService.listTree();
		return new DataGrid(resources.size(), resources);
    }
    
    /**
	 * 保存资源信息
	 */
	@RequiresPermissions("sys:resource:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, Resource resource) {
		resourceService.insert(resource);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 更新资源信息
	 */
	@RequiresPermissions("sys:resource:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, Resource resource) {
		resourceService.update(resource);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定资源信息
	 */
	@RequiresPermissions("sys:resource:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer resourceId) {
		resourceService.delete(resourceId);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
}
