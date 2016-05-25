package com.swtec.sw.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swtec.sw.persist.model.ext.RoleExt;
import com.swtec.sw.service.RoleService;

/**
 * 角色业务单元测试
 * 
 * @author shaowei
 */
public class RoleServiceTest {
	RoleService roleService = null;

	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-service.xml");
		roleService = (RoleService) ac.getBean(RoleService.class);
	}

	@Test
	public void testFindRolesWithUserChecked() {
		List<RoleExt> roles = roleService.findRolesWithUserChecked(1);
		Assert.assertNotNull(roles);
		for (RoleExt role : roles) {
			System.out.println("id:" + role.getId() + ",name:" + role.getRoleName()
					+ ",isSelected:" + role.isSelected());
		}
	}
}
