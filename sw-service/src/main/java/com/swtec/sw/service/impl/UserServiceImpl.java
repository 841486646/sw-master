package com.swtec.sw.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;
import com.swtec.sw.persist.enums.MenuType;
import com.swtec.sw.persist.enums.UserState;
import com.swtec.sw.persist.mapper.UserMapperExt;
import com.swtec.sw.persist.model.Resource;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.UserExample;
import com.swtec.sw.persist.model.ext.UserExt;
import com.swtec.sw.service.ResourceService;
import com.swtec.sw.service.UserService;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;
import com.swtec.sw.utils.security.Md5Utils;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserMapperExt userMapperExt;
	@javax.annotation.Resource
	private ResourceService resourceService;

	@Override
	public User findByUserName(String userName) {
		UserExample selParam = new UserExample();
		UserExample.Criteria cri = selParam.createCriteria();
		cri.andLoginNameEqualTo(userName);
		List<User> users = null;
		try {
			users = userMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if (users == null || users.size() == 0) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public Set<String> findStringRoles(User user) {
		// TODO 暂时未用到角色标识符来定义权限，后期需要再实现
		return null;
	}

	@Override
	public Set<String> findStringPermissions(User user) {
		Set<String> permissions = Sets.newHashSet();
		List<Resource> resources = resourceService.findPermissionsByUId(user.getId());
		if(resources == null || resources.size() ==  0){
			return permissions;
		}
		for (Resource resource : resources) {
			// 不可用 即没查到 或者字符串不存在
			if (resource == null || MenuType.menu == resource.getMenuType() || MyStringUtil.isEmpty(resource.getPermission())) {
				continue;
			}

			permissions.add(resource.getPermission());
		}
		return permissions;
	}

	@Override
	public User login(String username, String password) {
		User user = findByUserName(username);
		if (user == null) {
			throw new BizException(RespCode.NO_ACCOUNT);
		}
		if (!matches(user, password)) {
			throw new BizException(RespCode.USERNAME_PWD_ERR);
		}
		if (UserState.normal != user.getState()) {
			throw new BizException(RespCode.DISABLE_ACCOUNT);
		}
		return user;
	}

	@Override
	public void updatelstLoginTime(User user) {
		if(user == null || user.getId() == null || user.getId() == 0){
			log.info("updatelstLoginTime error, user is null");
			return;
		}
		User userSelective = new User();
		userSelective.setId(user.getId());
		userSelective.setLastLoginTime(new Date());
		try {
			userMapperExt.updateByPrimaryKeySelective(userSelective);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
	}

	private boolean matches(User user, String newPassword) {
		return user.getPwd().trim()
				.equals(Md5Utils.hash(user.getLoginName().trim() + newPassword.trim()
						+ user.getSalt().trim()));
	}

	@Override
	public void insert(User user) {
		if(user == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(MyStringUtil.isEmpty(user.getLoginName()) ||
				MyStringUtil.isEmpty(user.getPwd())){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		User userExist = findByUserName(user.getLoginName());
		if (userExist != null) {
			throw new BizException(RespCode.USER_EXIST);
		}
		
		user.setSalt(RandomStringUtils.randomAlphanumeric(10));
		user.setPwd(Md5Utils.hash(user.getLoginName().trim() + user.getPwd()
				+ user.getSalt().trim()));
		int effectLine = 0;
		try {
			effectLine = userMapperExt.insertSelective(user);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public List<User> list(UserExt user) {
		UserExample selParam = new UserExample();
		UserExample.Criteria cri = selParam.createCriteria();
		if(MyStringUtil.isNotBlank(user.getLoginName())){
			cri.andLoginNameEqualTo(user.getLoginName());
		}
		if(MyStringUtil.isNotBlank(user.getMobile())){
			cri.andMobileEqualTo(user.getMobile());
		}
		if(MyStringUtil.isNotBlank(user.getNickName())){
			cri.andNickNameLike(user.getNickName());
		}
		
		selParam.setPageSize(user.getRows());
		selParam.setPageBegin(getBegin(user.getPage(), user.getRows()));
		List<User> users = null;
		try {
			user.setTotal(userMapperExt.countByExample(selParam));
			users = userMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return users;
	}

	@Override
	public void update(User user) {
		if(user == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(user.getId() == null || user.getId() == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = userMapperExt.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void delete(Integer userId) {
		if(userId == null || userId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = userMapperExt.deleteByPrimaryKey(userId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void resetpwd(Integer userId, String pwd, String loginName) {
		if(userId == null || userId == 0 || MyStringUtil.isEmpty(pwd)
				|| MyStringUtil.isEmpty(loginName)){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		User user = new User();
		user.setId(userId);
		user.setSalt(RandomStringUtils.randomAlphanumeric(10));
		user.setPwd(Md5Utils.hash(loginName.trim() + pwd
				+ user.getSalt().trim()));
		int effectLine = 0;
		try {
			effectLine = userMapperExt.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public List<User> notPageList(UserExt user) {
		UserExample selParam = new UserExample();
		List<User> users = null;
		try {
			user.setTotal(userMapperExt.countByExample(selParam));
			users = userMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return users;
	}

}
