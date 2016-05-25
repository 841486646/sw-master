package com.swtec.sw.persist.mapper;

import java.util.List;

import com.swtec.sw.persist.model.Resource;

public interface ResourceMapperExt extends ResourceMapper {
	/**
	 * 根据用户ID查询用户对应的权限列表
	 * @param userId 用户名ID
	 * @return 权限列表
	 */
	List<Resource> findPermissionsByUId(Integer userId);
}
