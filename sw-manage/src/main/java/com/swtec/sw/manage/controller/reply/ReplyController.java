package com.swtec.sw.manage.controller.reply;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swtec.sw.manage.controller.BaseController;
import com.swtec.sw.persist.model.ext.MtProductExt;
import com.swtec.sw.persist.model.ext.MtTemplateExt;
import com.swtec.sw.service.mt.MtProductService;
import com.swtec.sw.service.mt.MtTemplateService;
/**
 * 数据回复页面控制类
 * 
 * @author chengkang
 */
@Controller
@RequestMapping("/reply")
public class ReplyController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private MtProductService mtProdectService;
	@Resource
	private MtTemplateService mtTemplateService;

	/**
	 * 维修站页面首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		model.addAttribute("bannerType",1);
		MtProductExt m=new MtProductExt();
		m.setType(1);
		m.setRows(10);
		model.addAttribute("products", mtProdectService.list(m));
		return "reply/index";
	}

	/**
	 * 打开数据恢复工程师忠告
	 */
	@RequestMapping(value = "/openCommonProblem")
	public String openCommonProblem(HttpServletRequest request, ModelMap model) {
		return "reply/commonProblem";
	}
	/**
	 * 打开数据恢复报价单
	 */
	@RequestMapping(value = "/openRecoveryPrice")
	public String openRecoveryPrice(HttpServletRequest request, ModelMap model) {
		return "reply/recoveryPrice";
	}
	/**
	 * 打开签订保密协议
	 */
	@RequestMapping(value = "/openConfidentialityAgreement")
	public String openConfidentialityAgreement(HttpServletRequest request, ModelMap model) {
		return "reply/confidentialityAgreement";
	}
	/**
	 * 成功案例title
	 */
	@RequestMapping(value = "/templateTitle", method = RequestMethod.GET)
	public String templateTitle(HttpServletRequest request, ModelMap model) {
		MtTemplateExt mtTemplate=new MtTemplateExt();
		mtTemplate.setType(0);
		mtTemplate.setRows(20);
		model.addAttribute("templateList", mtTemplateService.list(mtTemplate));
		return "reply/templateTitle";
	}
	/**
	 * 成功案例content
	 */
	@RequestMapping(value = "/templateContent", method = RequestMethod.GET)
	public String templateContent(HttpServletRequest request, ModelMap model,Integer id) {
		model.addAttribute("template", mtTemplateService.selectByPrimaryKey(id));
		return "reply/templateContent";
	}
}
