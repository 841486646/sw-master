package com.swtec.sw.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.swtec.sw.persist.enums.MenuType;
import com.swtec.sw.persist.mapper.ResourceMapperExt;
import com.swtec.sw.persist.mapper.ResourceRoleMapperExt;
import com.swtec.sw.persist.model.Resource;
import com.swtec.sw.persist.model.ResourceExample;
import com.swtec.sw.persist.model.ResourceRole;
import com.swtec.sw.persist.model.ResourceRoleExample;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.Combotree;
import com.swtec.sw.persist.model.ext.Menu;
import com.swtec.sw.persist.model.ext.ResourceExt;
import com.swtec.sw.service.ResourceService;
import com.swtec.sw.service.UserService;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

@Service
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService {
	@javax.annotation.Resource
	private UserService userService;
	@javax.annotation.Resource
	private ResourceMapperExt resourceMapperExt;
	@javax.annotation.Resource
	private ResourceRoleMapperExt resourceRoleMapperExt;
	
	@Override
	public List<Menu> findMenus(User user) {
		Set<String> permissions = userService.findStringPermissions(user);   
        
        List<Resource> allResources = findAllWithSort();
        List<Resource> menus = new ArrayList<Resource>();
        if (allResources != null && allResources.size() != 0){
        	for(Resource resource : allResources) {
                if(resource.getMenuType() != MenuType.menu) {
                    continue;
                }
                if(!hasPermission(resource, permissions)) {
                    continue;
                }
                menus.add(resource);
            }
        }
        
        return convertToMenus(menus);
	}

	@Override
	public List<Resource> findAllWithSort() {
		ResourceExample selParams = new ResourceExample();
		selParams.setOrderByClause("parent_id desc, weight desc");
		
		List<Resource> resources = null;
		try {
			resources = resourceMapperExt.selectByExample(selParams);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return resources;
	}
	
	@Override
	public boolean hasPermission(Resource resource, Set<String> permissions) {
		if (MyStringUtil.isEmpty(resource.getPermission())) {
			return true;
		}
		for (String permission : permissions) {
			WildcardPermission p1 = new WildcardPermission(permission);
			WildcardPermission p2 = new WildcardPermission(resource.getPermission());
			if (p1.implies(p2) || p2.implies(p1)) {
				return true;
			}
		}
		return false;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> convertToMenus(List<Resource> resources) {
		if (resources == null || resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

        Menu root = convertToMenu(resources.remove(resources.size() - 1));

        recursiveMenu(root, resources);
        List<Menu> menus = root.getChildren();
        removeNoLeafMenu(menus);

        return menus;
	}
	
	/**
	 * 转换单个资源到菜单
	 * @param resource 资源对象
	 * @return 菜单对象
	 */
	private Menu convertToMenu(Resource resource) {
        return new Menu(resource.getId(), resource.getName(),resource.getUrl());
    }
	
	/**
	 * 递归处理菜单资源
	 * @param menu 菜单对象
	 * @param resources 资源列表
	 */
	private void recursiveMenu(Menu menu, List<Resource> resources) {
        for (int i = resources.size() - 1; i >= 0; i--) {
        	Resource resource = resources.get(i);
            if (resource.getParentId().equals(menu.getId())) {
                menu.getChildren().add(convertToMenu(resource));
                resources.remove(i);
            }
        }

        for (Menu subMenu : menu.getChildren()) {
            recursiveMenu(subMenu, resources);
        }
    }
	
	/**
	 * 去除没有叶子节点的菜单
	 * @param menus 菜单列表
	 */
	private void removeNoLeafMenu(List<Menu> menus) {
        if (menus.size() == 0) {
            return;
        }
        for (int i = menus.size() - 1; i >= 0; i--) {
            Menu m = menus.get(i);
            removeNoLeafMenu(m.getChildren());
            if (!m.isHasChildren() && MyStringUtil.isEmpty(m.getUrl())) {
                menus.remove(i);
            }
        }
    }

	@Override
	public Resource selectByPrimaryKey(Integer id) {
		Resource resource = null;
		try {
			resource = resourceMapperExt.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return resource;
	}

	@Override
	public List<Resource> findPermissionsByUId(Integer userId) {
		List<Resource> resources = null;
		try {
			resources = resourceMapperExt.findPermissionsByUId(userId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return resources;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResourceExt> listTree() {
		List<Resource> resources = findAllWithSort();
		if (resources == null || resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

		ResourceExt root = convertToResourceExt(resources.remove(resources.size() - 1));
        recursiveResouceTree(root, resources);
        return root.getChildren();
	}
	
	/**
	 * 递归处理资源树列表
	 */
	private void recursiveResouceTree(ResourceExt resourceExt, List<Resource> resources) {
        for (int i = resources.size() - 1; i >= 0; i--) {
        	Resource resource = resources.get(i);
            if (resource.getParentId().equals(resourceExt.getId())) {
            	resourceExt.getChildren().add(convertToResourceExt(resource));
                resources.remove(i);
            }
        }

        for (ResourceExt sub : resourceExt.getChildren()) {
        	recursiveResouceTree(sub, resources);
        }
    }
	
	/**
	 * 递归处理资源树列表
	 */
	private void recursiveResouceTree(Combotree combotree, List<Resource> resources, Set<Integer> resourceIds) {
		for (int i = resources.size() - 1; i >= 0; i--) {
			Resource resource = resources.get(i);
			if (resource.getParentId().equals(combotree.getId())) {
				combotree.getChildren().add(convertToCombotree(resource, resourceIds));
				resources.remove(i);
			}
		}
		
		for (Combotree sub : combotree.getChildren()) {
			recursiveResouceTree(sub, resources, resourceIds);
		}
	}
	
	/**
	 * 转换资源数据到Combotree类型
	 */
	private Combotree convertToCombotree(Resource resource, Set<Integer> resourceIds) {
		Combotree combotree = new Combotree();
		combotree.setId(resource.getId());
		combotree.setText(resource.getName());
		if(resourceIds.contains(resource.getId())){
			combotree.setChecked(true);
		}
		return combotree;
	}

	/**
	 * 转换资源数据到拓展类型
	 */
	private ResourceExt convertToResourceExt(Resource resource) {
		ResourceExt ext = new ResourceExt();
		BeanUtils.copyProperties(resource, ext);
		return ext;
	}

	@Override
	public void insert(Resource resource) {
		if(resource == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if (MyStringUtil.isEmpty(resource.getName()) 
				|| resource.getParentId() == null
				|| resource.getParentId() == 0
				|| MyStringUtil.isEmpty(resource.getParentIds())
				|| MyStringUtil.isEmpty(resource.getPermission())) {
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		resource.setParentIds(resource.getParentIds() + resource.getParentId() + "/");
		 
		int effectLine = 0;
		try {
			effectLine = resourceMapperExt.insertSelective(resource);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(Resource resource) {
		if(resource == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(resource.getId() == null || resource.getId() == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = resourceMapperExt.updateByPrimaryKeySelective(resource);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void delete(Integer resourceId) {
		if(resourceId == null || resourceId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = resourceMapperExt.deleteByPrimaryKey(resourceId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Combotree> listComboTree(Integer roleId) {
		if(roleId == null || roleId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		ResourceRoleExample example = new ResourceRoleExample();
		ResourceRoleExample.Criteria cri = example.createCriteria();
		cri.andRoleIdEqualTo(roleId);
		List<ResourceRole> resourceRoles = resourceRoleMapperExt.selectByExample(example);
		//角色已经关联的资源id集合
		Set<Integer> resourceIds = new HashSet<Integer>();
		if (resourceRoles != null && resourceRoles.size() != 0) {
			for (ResourceRole resourceRole : resourceRoles) {
				resourceIds.add(resourceRole.getResourceId());
			}
        }
		
		List<Resource> resources = findAllWithSort();
		if (resources == null || resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

		Combotree root = convertToCombotree(resources.remove(resources.size() - 1), resourceIds);
        recursiveResouceTree(root, resources, resourceIds);
        return root.getChildren();
	}

	@Override
	public void giveResources(Integer roleId, String resourceIds) {
		if(roleId == null || roleId == 0 || MyStringUtil.isEmpty(resourceIds)){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		ResourceRoleExample example = new ResourceRoleExample();
		ResourceRoleExample.Criteria cri = example.createCriteria();
		cri.andRoleIdEqualTo(roleId);
		
		// 组织数据
		String[] resourceIdArray = resourceIds.split(",");
        List<ResourceRole> resourceRoles = new ArrayList<ResourceRole>();
        ResourceRole tempRR = null;
        for (int i = 0; i < resourceIdArray.length; i++) {
            if (MyStringUtil.isNumeric(resourceIdArray[i])) {
            	tempRR = new ResourceRole();
            	tempRR.setResourceId(Integer.parseInt(resourceIdArray[i]));
            	tempRR.setRoleId(roleId);
            	resourceRoles.add(tempRR);
            }
        }
        
		int effectLine = 0;
		try {
			resourceRoleMapperExt.deleteByExample(example);
			effectLine = resourceRoleMapperExt.insertBatch(resourceRoles);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}
}
