package com.swtec.sw.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.CommodityCategoryMapperExt;
import com.swtec.sw.persist.model.CommodityCategory;
import com.swtec.sw.persist.model.CommodityCategoryExample;
import com.swtec.sw.persist.model.ext.Combotree;
import com.swtec.sw.persist.model.ext.CommodityCategoryExt;
import com.swtec.sw.service.CommodityCategoryService;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;
@Service
public class CommodityCategoryServiceImpl extends BaseServiceImpl implements CommodityCategoryService{
	
	@javax.annotation.Resource
	private CommodityCategoryMapperExt commodityCategoryMapperExt;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CommodityCategoryExt> listTree() {
		List<CommodityCategory> resources = findAllWithSort();
		if (resources == null || resources.size() == 0) {
            return Collections.EMPTY_LIST;
        }

		CommodityCategoryExt root = convertToCommodityCategoryExt(resources.remove(resources.size() - 1));
		recursiveResouceTree(root, resources);
        return root.getChildren();
	}
	
	/**
	 * 递归处理资源树列表
	 */
	private void recursiveResouceTree(CommodityCategoryExt commodityCategoryExt, List<CommodityCategory> commodityCategorys) {
        for (int i = commodityCategorys.size() - 1; i >= 0; i--) {
        	CommodityCategory commodityCategory = commodityCategorys.get(i);
            if (commodityCategory.getParentId().equals(commodityCategoryExt.getId())) {
            	commodityCategoryExt.getChildren().add(convertToCommodityCategoryExt(commodityCategory));
            	commodityCategorys.remove(i);
            }
        }

        for (CommodityCategoryExt sub : commodityCategoryExt.getChildren()) {
        	recursiveResouceTree(sub, commodityCategorys);
        }
    }

	@Override
	public List<CommodityCategory> findAllWithSort() {
		CommodityCategoryExample selParams = new CommodityCategoryExample();
		selParams.setOrderByClause("parent_id desc, weight desc");
		List<CommodityCategory> commodityCategory = null;
		try {
			commodityCategory = commodityCategoryMapperExt.selectByExample(selParams);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return commodityCategory;
	}
	/**
	 * 转换资源数据到拓展类型
	 */
	private CommodityCategoryExt convertToCommodityCategoryExt(CommodityCategory commodityCategory) {
		CommodityCategoryExt ext = new CommodityCategoryExt();
		BeanUtils.copyProperties(commodityCategory, ext);
		return ext;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Combotree> listComboTree() {
		//角色已经关联的资源id集合
		Set<Integer> ids = new HashSet<Integer>();
		List<CommodityCategory> commodityCategorys = findAllWithSort();
		if (commodityCategorys == null || commodityCategorys.size() == 0) {
            return Collections.EMPTY_LIST;
        }

		Combotree root = convertToCombotree(commodityCategorys.remove(commodityCategorys.size() - 1), ids);
        recursiveResouceTree(root, commodityCategorys, ids);
        return root.getChildren();
	}
	/**
	 * 转换资源数据到Combotree类型
	 */
	private Combotree convertToCombotree(CommodityCategory commodityCategory, Set<Integer> ids) {
		Combotree combotree = new Combotree();
		combotree.setId(commodityCategory.getId());
		combotree.setText(commodityCategory.getCategoryName());
		combotree.setValue(commodityCategory.getParentIds());
		if(ids.contains(commodityCategory.getId())){
			combotree.setChecked(true);
		}
		return combotree;
	}
	/**
	 * 递归处理资源树列表
	 */
	private void recursiveResouceTree(Combotree combotree, List<CommodityCategory> commodityCategorys, Set<Integer> ids) {
		for (int i = commodityCategorys.size() - 1; i >= 0; i--) {
			CommodityCategory commodityCategory = commodityCategorys.get(i);
			if (commodityCategory.getParentId().equals(combotree.getId())) {
				combotree.getChildren().add(convertToCombotree(commodityCategory, ids));
				commodityCategorys.remove(i);
			}
		}
		
		for (Combotree sub : combotree.getChildren()) {
			recursiveResouceTree(sub, commodityCategorys, ids);
		}
	}

	@Override
	public void insert(CommodityCategory commodityCategory) {
		if(commodityCategory == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if (MyStringUtil.isEmpty(commodityCategory.getCategoryName()) 
				|| commodityCategory.getParentId() == null
				|| commodityCategory.getParentId() == 0
				|| MyStringUtil.isEmpty(commodityCategory.getParentIds())){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		commodityCategory.setParentIds(commodityCategory.getParentIds() + commodityCategory.getParentId() + "/");
		int effectLine = 0;
		try {
			effectLine = commodityCategoryMapperExt.insertSelective(commodityCategory);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(CommodityCategory commodityCategory) {
		if(commodityCategory==null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		if(commodityCategory.getId()==null || commodityCategory.getId()==0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = commodityCategoryMapperExt.updateByPrimaryKeySelective(commodityCategory);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void delete(Integer id) {
		if(id == null || id == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = commodityCategoryMapperExt.deleteByPrimaryKey(id);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

}
