package com.swtec.sw.service;

import java.util.List;

import com.swtec.sw.persist.model.Role;
import com.swtec.sw.persist.model.ext.RoleExt;

/**
 * 角色业务类
 * @author shaowei
 *
 */
public interface RoleService extends BaseService {
	/**
	 * 根据角色名称查询角色信息
	 * @param roleName 指定角色名称
	 * @return 角色对象
	 */
	public Role findByRoleName(String roleName);
	/**
	 * 查询角色列表信息
	 * @param role 角色条件封装
	 * @return 角色列表
	 */
	List<Role> list(RoleExt role);

	/**
	 * 添加角色
	 * @param role 角色对象
	 */
	void insert(Role role);
	
	/**
	 * 更新角色信息
	 * @param role 角色对象
	 */
	public void update(Role role);
	
	/**
	 * 删除知道角色信息
	 * @param roleId 指定角色ID
	 */
	public void delete(Integer roleId);
	
	/**
	 * 查询用户的角色列表，返回全部角色，标识用户已有的角色
	 * @param userId 用户ID
	 * @return 角色列表
	 */
	public List<RoleExt> findRolesWithUserChecked(Integer userId);
	
	/**
	 * 分配保存和更新角色
	 * @param userId 用户ID
	 * @param roleIds 角色id列表
	 * @return
	 */
	public void giveRoles(Integer userId, String roleIds);

}
