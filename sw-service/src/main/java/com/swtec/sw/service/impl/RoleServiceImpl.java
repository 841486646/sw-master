package com.swtec.sw.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.RoleMapperExt;
import com.swtec.sw.persist.mapper.UserRoleMapperExt;
import com.swtec.sw.persist.model.Role;
import com.swtec.sw.persist.model.RoleExample;
import com.swtec.sw.persist.model.UserRole;
import com.swtec.sw.persist.model.UserRoleExample;
import com.swtec.sw.persist.model.ext.RoleExt;
import com.swtec.sw.service.RoleService;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

@Service
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
	@Resource
	private RoleMapperExt roleMapperExt;
	@Resource
	private UserRoleMapperExt userRoleMapperExt;

	@Override
	public Role findByRoleName(String roleName) {
		RoleExample selParam = new RoleExample();
		RoleExample.Criteria cri = selParam.createCriteria();
		cri.andRoleNameEqualTo(roleName);
		List<Role> roles = null;
		try {
			roles = roleMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if (roles == null || roles.size() == 0) {
			return null;
		}
		return roles.get(0);
	}
	
	@Override
	public List<Role> list(RoleExt role) {
		RoleExample selParam = new RoleExample();
		RoleExample.Criteria cri = selParam.createCriteria();
		if(MyStringUtil.isNotBlank(role.getRoleName())){
			cri.andRoleNameEqualTo(role.getRoleName());
		}
		if(role.getIsShow() != null){
			cri.andIsShowEqualTo(role.getIsShow());
		}
		if(MyStringUtil.isNotBlank(role.getRoleDesc())){
			cri.andRoleDescLike(role.getRoleDesc());
		}
		
		selParam.setPageSize(role.getRows());
		selParam.setPageBegin(getBegin(role.getPage(), role.getRows()));
		List<Role> roles = null;
		try {
			role.setTotal(roleMapperExt.countByExample(selParam));
			roles = roleMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return roles;
	}

	@Override
	public void insert(Role role) {
		if(role == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(MyStringUtil.isEmpty(role.getRoleName()) ||
				role.getCreateId() == null ||
				role.getCreateId() == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		Role roleExist = findByRoleName(role.getRoleName());
		if (roleExist != null) {
			throw new BizException(RespCode.ROLE_EXIST);
		}
		
		int effectLine = 0;
		try {
			effectLine = roleMapperExt.insertSelective(role);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(Role role) {
		if(role == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(role.getId() == null || role.getId() == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = roleMapperExt.updateByPrimaryKeySelective(role);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void delete(Integer roleId) {
		if(roleId == null || roleId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = roleMapperExt.deleteByPrimaryKey(roleId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public List<RoleExt> findRolesWithUserChecked(Integer userId) {
		if(userId == null || userId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		try {
			return roleMapperExt.findRolesWithUserChecked(userId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
	}

	@Override
	public void giveRoles(Integer userId, String roleIds) {
		if(userId == null || userId == 0 || MyStringUtil.isEmpty(roleIds)){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		UserRoleExample example = new UserRoleExample();
		UserRoleExample.Criteria cri = example.createCriteria();
		cri.andUserIdEqualTo(userId);
		
		// 组织数据
		String[] roleIdArray = roleIds.split(",");
        List<UserRole> userRoles = new ArrayList<UserRole>();
        UserRole tempUR = null;
        for (int i = 0; i < roleIdArray.length; i++) {
            if (MyStringUtil.isNumeric(roleIdArray[i])) {
                tempUR = new UserRole();
                tempUR.setRoleId(Integer.parseInt(roleIdArray[i]));
                tempUR.setUserId(userId);
                userRoles.add(tempUR);
            }
        }
        
		int effectLine = 0;
		try {
			userRoleMapperExt.deleteByExample(example);
			effectLine = userRoleMapperExt.insertBatch(userRoles);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

}
