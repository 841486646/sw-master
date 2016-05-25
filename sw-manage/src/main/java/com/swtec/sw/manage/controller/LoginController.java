package com.swtec.sw.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.Menu;
import com.swtec.sw.service.ResourceService;
import com.swtec.sw.service.UserService;
import com.swtec.sw.utils.Constants;

/**
 * 登录控制类
 * @author shaowei
 */
@Controller
public class LoginController extends BaseController{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private UserService userService;
	
	@Resource
	private ResourceService resourceService;

	@RequestMapping(value = { "/{login:login;?.*}" })
	public String login(HttpServletRequest request, ModelMap model) {
		log.info("to login page...");

		// 登录失败了 提取错误消息
		Exception shiroLoginFailureEx = (Exception) request
				.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		if (shiroLoginFailureEx != null) {
			model.addAttribute(Constants.ERROR, shiroLoginFailureEx.getMessage());
		}

		// 如果用户直接到登录页面 先退出一下
		// 原因：isAccessAllowed实现是subject.isAuthenticated()---->即如果用户验证通过 就允许访问
		// 这样会导致登录一直死循环
		Subject subject = SecurityUtils.getSubject();
		if (subject != null && subject.isAuthenticated()) {
			subject.logout();
		}
		return "login";
	}
	
	@RequestMapping(value = { "/main" })
	public String loginSuccess(HttpServletRequest request, ModelMap model) {
		log.info("to index page...");

		User user = this.getCurrentUser(request);
		model.addAttribute("user", user);
		
		List<Menu> menus = resourceService.findMenus(user);
		String json = JSON.toJSONStringWithDateFormat(menus, "yyyy-MM-dd HH:mm:ss");
		model.addAttribute("menus", json);
		
		return "main";
	}
}
