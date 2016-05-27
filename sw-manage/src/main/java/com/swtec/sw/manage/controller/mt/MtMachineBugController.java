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
import com.swtec.sw.persist.model.MtMachineBug;
import com.swtec.sw.persist.model.ext.Combobox;
import com.swtec.sw.persist.model.ext.MtMachineBugExt;
import com.swtec.sw.service.mt.MtMachineBugService;
import com.swtec.sw.service.mt.MtMachineColorService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

/**
 * 维修机器故障控制类
 * @author shaowei
 */
@Controller
@RequestMapping("/mtMachineBug")
public class MtMachineBugController extends BaseController{
	@Resource
	private MtMachineBugService mtMachineBugService;
	@Resource
	private MtMachineColorService mtMachineColorService;

	/**
	 * 跳转到故障列表页面
	 */
	@RequiresPermissions("mt:bug:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("colors", mtMachineColorService.listAll());
		return "mtmg/machineBug";
	}
	
	/**
	 * 查询故障列表，返回grid的json数据
	 */
	@RequiresPermissions("mt:bug:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, MtMachineBugExt mtMachineBug) {
		List<MtMachineBugExt> mtMachineBugs = mtMachineBugService.listExt(mtMachineBug);
		return new DataGrid(mtMachineBug.getTotal(), mtMachineBugs);
	}
	
	/**
	 * 保存故障信息
	 */
	@RequiresPermissions("mt:bug:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, MtMachineBug mtMachineBug) {
		mtMachineBugService.insert(mtMachineBug);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	/**
	 * 新建故障时，查询所有的产品机型列表，combobox类型返回
	 */
	@RequiresPermissions("mt:bug:create")
	@RequestMapping(value = "/findProductAndMachineTypes", method = RequestMethod.POST)
	@ResponseBody
	public List<Combobox> findProductAndMachineTypes(HttpServletRequest request, ModelMap modelmtMachineBug) {
		return mtMachineBugService.findProductAndMachineTypes();
	}
	
	/**
	 * 更新故障信息
	 */
	@RequiresPermissions("mt:bug:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, MtMachineBug mtMachineBug) {
		mtMachineBugService.update(mtMachineBug);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定故障信息
	 */
	@RequiresPermissions("mt:bug:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer mtMachineBugId) {
		mtMachineBugService.delete(mtMachineBugId);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
}
