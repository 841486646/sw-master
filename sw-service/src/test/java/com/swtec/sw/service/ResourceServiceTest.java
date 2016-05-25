package com.swtec.sw.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swtec.sw.persist.model.Resource;
import com.swtec.sw.service.ResourceService;

/**
 * 资源业务单元测试
 * 
 * @author shaowei
 */
public class ResourceServiceTest {
	ResourceService resourceService = null;

	@Before
	public void setUp() throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:applicationContext-service.xml");
		resourceService = (ResourceService) ac.getBean(ResourceService.class);
	}

	@Test
	public void testFindPermissionsByUId() {
		List<Resource> resources = resourceService.findPermissionsByUId(1);
		Assert.assertNotNull(resources);
		for (Resource resource : resources) {
			System.out.println("id:" + resource.getId() + ",name:" + resource.getName()
					+ ",permission:" + resource.getPermission());
		}
	}
}
