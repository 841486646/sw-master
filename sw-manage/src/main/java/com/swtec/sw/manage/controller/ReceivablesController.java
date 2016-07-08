package com.swtec.sw.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 销售/维修结算付款控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/receivables")
public class ReceivablesController {
	
	@RequestMapping(value = "/insertReceivables", method = RequestMethod.GET)
	public String insertReceivables(){
		return "receivables/receivables";
	}

}
