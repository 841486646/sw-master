package com.swtec.sw.persist.mapper;

import java.util.List;

import com.swtec.sw.persist.model.ext.RoleExt;

public interface RoleMapperExt extends RoleMapper {
	/**
	 * 查询用户的角色列表，返回全部角色，标识用户已有的角色
	 * @param userId 用户ID
	 * @return 角色列表
	 */
	List<RoleExt> findRolesWithUserChecked(Integer userId);
}
