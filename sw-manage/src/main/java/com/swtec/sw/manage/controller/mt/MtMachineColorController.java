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
import com.swtec.sw.persist.model.MtMachineColor;
import com.swtec.sw.persist.model.ext.MtMachineColorExt;
import com.swtec.sw.service.mt.MtMachineColorService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

/**
 * 维修机器颜色控制类
 * @author shaowei
 */
@Controller
@RequestMapping("/mtMachineColor")
public class MtMachineColorController extends BaseController{
	@Resource
	private MtMachineColorService mtMachineColorService;

	/**
	 * 跳转到颜色列表页面
	 */
	@RequiresPermissions("mt:color:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		return "mtmg/color";
	}
	
	/**
	 * 查询颜色列表，返回grid的json数据
	 */
	@RequiresPermissions("mt:color:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, MtMachineColorExt mtMachineColor) {
		List<MtMachineColor> mtMachineColors = mtMachineColorService.list(mtMachineColor);
		return new DataGrid(mtMachineColor.getTotal(), mtMachineColors);
	}
	
	/**
	 * 保存颜色信息
	 */
	@RequiresPermissions("mt:color:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, MtMachineColor mtMachineColor) {
		mtMachineColorService.insert(mtMachineColor);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 更新颜色信息
	 */
	@RequiresPermissions("mt:color:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, MtMachineColor mtMachineColor) {
		mtMachineColorService.update(mtMachineColor);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定颜色信息
	 */
	@RequiresPermissions("mt:color:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer mtMachineColorId) {
		mtMachineColorService.delete(mtMachineColorId);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
}
