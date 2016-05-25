package com.swtec.sw.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.utils.Constants;

/**
 * 控制器基类，封装公共代码
 * 
 * @author shaowei
 *
 */
public class BaseController {
	/**
	 * 将对象转换成JSON返回前台
	 * 
	 * @param obj
	 * @param response
	 */
	public void writeJson(Object obj, HttpServletResponse response) {
		try {
			String json = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到session中的user对象
	 * 
	 * @param request
	 * @return
	 */
	public User getCurrentUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(Constants.CURRENT_USER);
	}

}
