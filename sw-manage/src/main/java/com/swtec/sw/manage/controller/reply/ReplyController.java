package com.swtec.sw.manage.controller.reply;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swtec.sw.manage.controller.BaseController;
import com.swtec.sw.persist.model.MtMachineColor;
import com.swtec.sw.persist.model.MtOrder;
import com.swtec.sw.persist.model.MtProduct;
import com.swtec.sw.persist.model.ext.MtProductExt;
import com.swtec.sw.service.mt.MtMachineBugService;
import com.swtec.sw.service.mt.MtMachineColorService;
import com.swtec.sw.service.mt.MtMachineTypeService;
import com.swtec.sw.service.mt.MtOrderService;
import com.swtec.sw.service.mt.MtProductService;
import com.swtec.sw.utils.Constants;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.ValidateCode;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;

/**
 * 数据回复页面控制类
 * 
 * @author shaowei
 */
@Controller
@RequestMapping("/reply")
public class ReplyController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private MtProductService mtProdectService;
	@Resource
	private MtMachineTypeService mtMachineTypeService;
	@Resource
	private MtMachineColorService mtMachineColorService;
	@Resource
	private MtMachineBugService mtMachineBugService;
	@Resource
	private MtOrderService mtOrderService;

	/**
	 * 维修站页面首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		MtProductExt m=new MtProductExt();
		m.setType(1);
		m.setRows(10);
		model.addAttribute("products", mtProdectService.list(m));
		return "reply/index";
	}

	/**
	 * 维修站机型选择页面
	 */
	@RequestMapping(value = "/machineType", method = RequestMethod.GET)
	public String machineType(HttpServletRequest request, ModelMap model, Integer productId) {
		model.addAttribute("machineTypes", mtMachineTypeService.listByProductId(productId));
		return "mt/machineType";
	}

	/**
	 * 维修站故障选择页面
	 */
	@RequestMapping(value = "/machineBug", method = RequestMethod.GET)
	public String machineBug(HttpServletRequest request, ModelMap model, Integer machineTypeId) {
		model.addAttribute("machineType", mtMachineTypeService.selectById(machineTypeId));
		List<MtMachineColor> machineColors = mtMachineColorService
				.listByMachineTypeId(machineTypeId);
		model.addAttribute("machineColors", machineColors);
		String machineBugsHtml = "";
		if (machineColors != null && machineColors.size() > 0) {
			machineBugsHtml = mtMachineBugService.listByMachineTypeIdAndColorIdToHtml(
					machineTypeId, machineColors.get(0).getId());
		}
		model.addAttribute("machineBugsHtml", machineBugsHtml);
		return "mt/machineBug";
	}

	/**
	 * 维修站故障选择页面
	 */
	@RequestMapping(value = "/findMachineBugs")
	@ResponseBody
	public RespResult findMachineBugs(HttpServletRequest request, ModelMap model,
			Integer machineTypeId, Integer colorId) {
		String mtMachineBugsHtml = mtMachineBugService.listByMachineTypeIdAndColorIdToHtml(
				machineTypeId, colorId);
		return RespResult.getInstance(RespCode.SUCCESS, mtMachineBugsHtml);
	}
	
	/**
	 * 个人信息页面
	 */
	@RequestMapping(value = "/personInfo")
	public String personInfo(HttpServletRequest request, ModelMap model,
			String machineBugIds) {
		model.addAttribute("machineBugIds", machineBugIds);
		return "mt/personInfo";
	}
	
	/**
	 * 常见问题页面
	 */
	@RequestMapping(value = "/commonProblem")
	public String commonProblem(HttpServletRequest request, ModelMap model) {
		return "mt/commonProblem";
	}
	
	/**
	 * 服务流程页面
	 */
	@RequestMapping(value = "/serviceProcess")
	public String serviceProcess(HttpServletRequest request, ModelMap model) {
		return "mt/serviceProcess";
	}
	
	/**
	 * 维修条款页面
	 */
	@RequestMapping(value = "/mtItem")
	public String mtItem(HttpServletRequest request, ModelMap model) {
		return "mt/mtItem";
	}
	
	/**
	 * 维修条款页面
	 */
	@RequestMapping(value = "/about")
	public String about(HttpServletRequest request, ModelMap model) {
		return "mt/about";
	}
	
	/**
	 * 微博页面
	 */
	@RequestMapping(value = "/weibo")
	public String weibo(HttpServletRequest request, ModelMap model) {
		return "mt/weibo";
	}
	
	/**
	 * 保修查询页面
	 */
	@RequestMapping(value = "/guarantee")
	public String guarantee(HttpServletRequest request, ModelMap model) {
		return "mt/guarantee";
	}
	
	/**
	 * 保修查询页面
	 */
	@RequestMapping(value = "/findOrder")
	@ResponseBody
	public RespResult findOrder(HttpServletRequest request, ModelMap model) {
		return RespResult.getInstance(RespCode.SUCCESS, "未查询到订单信息");
	}
	
	/**
	 * 保存订单信息
	 */
	@RequestMapping(value = "/saveOrderInfo")
	@ResponseBody
	public RespResult saveOrderInfo(HttpServletRequest request, ModelMap model, MtOrder mtOrder, String code) {
		HttpSession session = request.getSession(); 
		String sessionCode = (String) session.getAttribute(Constants.CODE_IN_SESSON);
		if(MyStringUtil.isBlank(code) || !code.equals(sessionCode)){
			throw new BizException(RespCode.VALID_CODE);
		}
		//验证成功之后，清除code
		session.removeAttribute(Constants.CODE_IN_SESSON);
		mtOrderService.insert(mtOrder);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 获取图片验证码
	 */
	@RequestMapping(value = "/getValidCode")
	public void getValidCode(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		HttpSession session = request.getSession();
		
		ValidateCode vCode = new ValidateCode(120, 40, 4, 50);
		session.setAttribute(Constants.CODE_IN_SESSON, vCode.getCode());
		try {
			vCode.write(response.getOutputStream());
		} catch (IOException e) {
			log.error("获取图片验证码错误", e);
		}
	}
	
}
