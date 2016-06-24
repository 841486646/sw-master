package com.swtec.sw.manage.controller.mt;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.xalan.xsltc.compiler.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swtec.sw.persist.model.MtProduct;
import com.swtec.sw.persist.model.MtTemplate;
import com.swtec.sw.persist.model.ext.MtProductExt;
import com.swtec.sw.persist.model.ext.MtTemplateExt;
import com.swtec.sw.service.mt.MtTemplateService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;

/**
 * 模版控制类
 * @author shaowei
 */
@Controller
@RequestMapping("/template")
public class MtTemplateController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private MtTemplateService mtTemplateService;
	
	/**
	 * 跳转到产品列表页面
	 */
	@RequiresPermissions("mt:template:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		return "mtmg/template";
	}
	
	/**
	 * 查询产品列表，返回grid的json数据
	 */
	@RequiresPermissions("mt:template:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, MtTemplateExt mtTemplate) {
		List<MtTemplate> mtTemplates = mtTemplateService.list(mtTemplate);
		return new DataGrid(mtTemplate.getTotal(), mtTemplates);
	}
	
	/**
	 *
	 * 保存
	 */
	@RequiresPermissions("mt:template:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, MtTemplate mtTemplate) {
		mtTemplateService.insert(mtTemplate);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
}
