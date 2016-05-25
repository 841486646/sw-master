package com.swtec.sw.service;

import java.util.List;
import java.util.Set;

import com.swtec.sw.persist.model.Resource;
import com.swtec.sw.persist.model.User;
import com.swtec.sw.persist.model.ext.Combotree;
import com.swtec.sw.persist.model.ext.Menu;
import com.swtec.sw.persist.model.ext.ResourceExt;

/**
 * 资源业务类
 * @author shaowei
 *
 */
public interface ResourceService extends BaseService {
	/**
	 * 查询菜单列表
	 * @param user 用户信息对象
	 * @return 菜单列表
	 */
	public List<Menu> findMenus(User user);
	
	/**
     * 按照parendid和权值取出全部的资源列表
     */
    public List<Resource> findAllWithSort();
    
    /**
     * 判断是否有权限
     * @param resource 资源对象
     * @param permissions 权限字符集合
     * @return
     */
    public boolean hasPermission(Resource resource, Set<String> permissions);
    
    /**
     * 转换资源到菜单
     * @param resources 资源列表
     * @return 菜单列表
     */
    public List<Menu> convertToMenus(List<Resource> resources);
    
    /**
     * 根据主键id查询单个资源信息
     */
    public Resource selectByPrimaryKey(Integer id);

    /**
     * 根据用户ID查询用户对应的权限列表
     * @param userId 用户ID
     * @return
     */
	public List<Resource> findPermissionsByUId(Integer userId);

	/**
	 * 查询资源菜单，处理成树形数据结构返回
	 * @return
	 */
	public List<ResourceExt> listTree();
	
	/**
	 * 查询资源菜单，处理成树形数据结构返回
	 * @return
	 */
	public List<Combotree> listComboTree(Integer roleId);

	/**
	 * 添加资源
	 * @param resource 资源信息
	 */
	public void insert(Resource resource);

	/**
	 * 更新资源
	 * @param resource 资源信息
	 */
	public void update(Resource resource);
	/**
	 * 删除资源
	 * @param resource 资源ID
	 */
	public void delete(Integer resourceId);

	/**
	 * 
	 * @param roleId 角色ID
	 * @param resourceIds 资源ID列表
	 */
	public void giveResources(Integer roleId, String resourceIds);
}
