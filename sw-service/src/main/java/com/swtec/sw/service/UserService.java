package com.swtec.sw.service;

import java.util.List;
import java.util.Set;

import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.UserExt;

/**
 * 用户业务类操作接口声明
 * @author shaowei
 *
 */
public interface UserService extends BaseService {

	/**
	 * 根据用户名查询用户信息
	 * @param userName 用户名
	 * @return 用户信息，如果没有查询到用户，返回null
	 */
	User findByUserName(String userName);

	/**
	 * 根据指定用户查询角色字符列表
	 * @param user 用户对象
	 * @return 角色字符列表
	 */
	Set<String> findStringRoles(User user);

	/**
	 * 根据指定用户查询权限字符列表
	 * @param user 用户对象
	 * @return 权限字符列表
	 */
	Set<String> findStringPermissions(User user);

	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户对象
	 */
	User login(String username, String password);

	/**
	 * 更新知道用户登录时间
	 * @param user 用户对象
	 */
	void updatelstLoginTime(User user);
	
	/**
	 * 添加用户
	 * @param user 用户对象信息
	 */
	void insert(User user);

	/**
	 * 查询用户列表
	 * @param user 指定用户信息
	 * @return 用户列表
	 */
	List<User> list(UserExt user);
	/**
	 * 不带分页参数
	 * @param user
	 * @return
	 */
	List<User> notPageList(UserExt user);

	/**
	 * 更新用户信息
	 * @param user 用户信息
	 */
	void update(User user);

	/**
	 * 删除指定用户
	 * @param userId 用户id
	 */
	void delete(Integer userId);
	
	/**
	 * 修改密码
	 * @param userId 用户id
	 * @param pwd 更新的密码
	 */
	void resetpwd(Integer userId, String pwd, String loginName);

}
