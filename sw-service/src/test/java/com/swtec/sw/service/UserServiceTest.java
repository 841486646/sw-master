package com.swtec.sw.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swtec.sw.persist.model.User;
import com.swtec.sw.service.UserService;


/**
 * 用户业务单元测试
 * @author shaowei
 */
public class UserServiceTest {
	UserService userService = null;
	
	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext-service.xml");
		userService = (UserService) ac.getBean(UserService.class);
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setLoginName("test001");
		user.setPwd("123456");
		userService.insert(user);
	}
}
