package com.swtec.sw.persist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.swtec.sw.persist.model.ResourceRole;

public interface ResourceRoleMapperExt extends ResourceRoleMapper {
	/**
     * 批量插入用户角色关系
     * 
     * @param relations
     * @return
     */
	int insertBatch(@Param("relations") List<ResourceRole> resourceRoles);
}
