package com.swtec.sw.manage.filter;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

/**
 *	统一异常处理类
 */
@ControllerAdvice
public class DefaultExceptionHandler {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 基础父类异常处理
     */
    @ExceptionHandler({Exception.class})
    public ModelAndView processBaseException(HttpServletRequest request, HttpServletResponse response, Exception e) {
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request
				.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
			doLog(e, request);
			// 如果不是异步请求
			ModelAndView mv = new ModelAndView();
	        mv.addObject("exception", e);
	        mv.setViewName("error/exception");
	        return mv;
		}else{
			if(!MyStringUtil.isEmpty(request.getRequestURI()) && 
					request.getRequestURI().lastIndexOf(".grid") > 0){
				doLog(e, request);
				writeJson(new DataGrid(0, new ArrayList<Object>()), response);
				return null;
			}
			
			if(e instanceof BizException){
				BizException bize = (BizException)e;
				writeJson(RespResult.getInstance(bize.getErrorCode(), bize.getMessage()), response);
			} else if(e instanceof DbException){
				log.error("操作数据库异常", e);
				DbException dbe = (DbException)e;
				writeJson(RespResult.getInstance(dbe.getErrorCode(), dbe.getMessage()), response);
			} else if(e instanceof UnauthorizedException){
				if (log.isErrorEnabled()) {
					log.error("用户权限验证失败:" + e.getMessage());
				}
				writeJson(RespResult.getInstance(RespCode.REQ_NOT_ALLOWED), response);
			} else{
				log.error("系统错误，未知异常", e);
				writeJson(RespResult.getInstance(RespCode.SERVER_ERROR), response);
			}
	        return null;
		}
    }
    
	private void writeJson(Object obj, HttpServletResponse response){
		try {
			String json = JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/json;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 按异常类似打印日志
	 */
	private void doLog(Exception e, HttpServletRequest request){
		if(e instanceof BizException){
			BizException bize = (BizException)e;
			if (log.isErrorEnabled()) {
				log.error("业务异常，结果码：" + bize.getErrorCode() + ", 异常信息：" + bize.getMessage()
						+ ", 请求地址：" + request.getRequestURI());
			}
		} else if(e instanceof DbException){
			log.error("操作数据库异常", e);
		} else if(e instanceof UnauthorizedException){
			if (log.isErrorEnabled()) {
				log.error("用户权限验证失败:" + e.getMessage());
			}
		} else{
			log.error("系统错误，未知异常", e);
		}
	}
}
