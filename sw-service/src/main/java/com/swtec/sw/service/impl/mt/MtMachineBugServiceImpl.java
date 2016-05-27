package com.swtec.sw.service.impl.mt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swtec.sw.persist.mapper.MtMachineBugMapperExt;
import com.swtec.sw.persist.model.MtMachineBug;
import com.swtec.sw.persist.model.MtMachineBugExample;
import com.swtec.sw.persist.model.MtMachineType;
import com.swtec.sw.persist.model.MtProduct;
import com.swtec.sw.persist.model.ext.Combobox;
import com.swtec.sw.persist.model.ext.MtMachineBugExt;
import com.swtec.sw.service.impl.BaseServiceImpl;
import com.swtec.sw.service.mt.MtMachineBugService;
import com.swtec.sw.service.mt.MtMachineTypeService;
import com.swtec.sw.service.mt.MtProductService;
import com.swtec.sw.utils.MyStringUtil;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;
import com.swtec.sw.utils.exception.DbException;

@Service
public class MtMachineBugServiceImpl extends BaseServiceImpl implements MtMachineBugService {
	@Resource
	private MtMachineBugMapperExt mtMachineBugMapperExt;
	@Resource
	private MtMachineTypeService mtMachineTypeService;
	@Resource
	private MtProductService mtProductService;

	@Override
	public List<MtMachineBug> listByMachineTypeIdAndColorId(Integer machineTypeId, Integer colorId) {
		if(machineTypeId == null || machineTypeId == 0
				|| colorId == null || colorId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		MtMachineBugExample selParam = new MtMachineBugExample();
		MtMachineBugExample.Criteria cri = selParam.createCriteria();
		cri.andMachineTypeIdEqualTo(machineTypeId);
		cri.andMachineColorIdEqualTo(colorId);
		List<MtMachineBug> mtMachineBugs = null;
		try {
			mtMachineBugs = mtMachineBugMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineBugs;
	}

	@Override
	public String listByMachineTypeIdAndColorIdToHtml(Integer machineTypeId, Integer colorId) {
		List<MtMachineBug> mtMachineBugs = listByMachineTypeIdAndColorId(
				machineTypeId, colorId);
		StringBuilder mtMachineBugsHtml = new StringBuilder();
		if(mtMachineBugs != null){
			for (MtMachineBug mtMachineBug : mtMachineBugs) {
				mtMachineBugsHtml.append("<li value='").append(mtMachineBug.getId()).append("'>");
				mtMachineBugsHtml.append(mtMachineBug.getName());
				mtMachineBugsHtml.append("<p>￥<span>").append(mtMachineBug.getPrice()).append("</span></p>");
				mtMachineBugsHtml.append("<input type='checkbox' name='pf_checkbox' id='pf_checkbox_");
				mtMachineBugsHtml.append(mtMachineBug.getId()).append("' value='");
				mtMachineBugsHtml.append(mtMachineBug.getId()).append("' style='display:none'/></li>");
			}
		}
		return mtMachineBugsHtml.toString();
	}

	@Override
	public List<MtMachineBug> list(MtMachineBugExt mtMachineBug) {
		MtMachineBugExample selParam = new MtMachineBugExample();
		MtMachineBugExample.Criteria cri = selParam.createCriteria();
		if(MyStringUtil.isNotBlank(mtMachineBug.getName())){
			cri.andNameEqualTo(mtMachineBug.getName());
		}
		
		selParam.setPageSize(mtMachineBug.getRows());
		selParam.setPageBegin(getBegin(mtMachineBug.getPage(), mtMachineBug.getRows()));
		List<MtMachineBug> mtMachineBugs = null;
		try {
			mtMachineBug.setTotal(mtMachineBugMapperExt.countByExample(selParam));
			mtMachineBugs = mtMachineBugMapperExt.selectByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineBugs;
	}
	
	@Override
	public List<MtMachineBugExt> listExt(MtMachineBugExt mtMachineBug) {
		MtMachineBugExample selParam = new MtMachineBugExample();
		MtMachineBugExample.Criteria cri = selParam.createCriteria();
		if(MyStringUtil.isNotBlank(mtMachineBug.getName())){
			cri.andNameEqualTo(mtMachineBug.getName());
		}
		
		selParam.setPageSize(mtMachineBug.getRows());
		selParam.setPageBegin(getBegin(mtMachineBug.getPage(), mtMachineBug.getRows()));
		List<MtMachineBugExt> mtMachineBugs = null;
		try {
			mtMachineBug.setTotal(mtMachineBugMapperExt.countByExample(selParam));
			mtMachineBugs = mtMachineBugMapperExt.selectExtByExample(selParam);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		return mtMachineBugs;
	}

	@Override
	public void insert(MtMachineBug mtMachineBug) {
		if(mtMachineBug == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(MyStringUtil.isEmpty(mtMachineBug.getName())){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = mtMachineBugMapperExt.insertSelective(mtMachineBug);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine != 1){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void update(MtMachineBug mtMachineBug) {
		if(mtMachineBug == null){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		if(mtMachineBug.getId() == null || mtMachineBug.getId() == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		
		int effectLine = 0;
		try {
			effectLine = mtMachineBugMapperExt.updateByPrimaryKeySelective(mtMachineBug);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public void delete(Integer mtMachineBugId) {
		if(mtMachineBugId == null || mtMachineBugId == 0){
			throw new BizException(RespCode.REQ_PARAM_ERROR);
		}
		int effectLine = 0;
		try {
			effectLine = mtMachineBugMapperExt.deleteByPrimaryKey(mtMachineBugId);
		} catch (Exception e) {
			throw new DbException(RespCode.DB_ERROR, e);
		}
		if(effectLine == 0){
			throw new BizException(RespCode.DB_ERROR_EFFECT_LINE);
		}
	}

	@Override
	public List<Combobox> findProductAndMachineTypes() {
		List<MtMachineType> machineTypes = mtMachineTypeService.listAll();
		List<MtProduct> products = mtProductService.listAll();
		if(machineTypes == null || products == null){
			return null;
		}
		List<Combobox> list = new ArrayList<Combobox>();
		Combobox combobox = null;
		for (MtMachineType machineType : machineTypes) {
			combobox = new Combobox();
			for (MtProduct product : products) {
				if(machineType.getProductId() != null && 
						machineType.getProductId().equals(product.getId())){
					combobox.setGroup(product.getName());
					break;
				}
			}
			combobox.setValue(String.valueOf(machineType.getId()));
			combobox.setText(machineType.getName());
			list.add(combobox);
		}
		return list;
	}
}
