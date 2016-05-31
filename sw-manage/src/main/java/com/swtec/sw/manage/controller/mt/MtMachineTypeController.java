package com.swtec.sw.manage.controller.mt;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.swtec.sw.manage.controller.BaseController;
import com.swtec.sw.persist.model.MtMachineType;
import com.swtec.sw.persist.model.ext.MtMachineTypeExt;
import com.swtec.sw.service.mt.MtMachineTypeService;
import com.swtec.sw.service.mt.MtProductService;
import com.swtec.sw.utils.DataGrid;
import com.swtec.sw.utils.QiniuUpload;
import com.swtec.sw.utils.RespResult;
import com.swtec.sw.utils.enums.RespCode;
import com.swtec.sw.utils.exception.BizException;

/**
 * 维修机型控制类
 * @author shaowei
 */
@Controller
@RequestMapping("/mtMachineType")
public class MtMachineTypeController extends BaseController{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Resource
	private MtMachineTypeService mtMachineTypeService;
	@Resource
	private MtProductService mtProductService;

	/**
	 * 跳转到机型列表页面
	 */
	@RequiresPermissions("mt:machinetype:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String toList(HttpServletRequest request, ModelMap model) {
		model.addAttribute("products", mtProductService.listAll());
		return "mtmg/machineType";
	}
	
	/**
	 * 查询机型列表，返回grid的json数据
	 */
	@RequiresPermissions("mt:machinetype:view")
	@RequestMapping(value = "/list.grid", method = RequestMethod.POST)
	@ResponseBody
	public DataGrid list(HttpServletRequest request, ModelMap model, MtMachineTypeExt mtMachineType) {
		List<MtMachineType> mtProducts = mtMachineTypeService.list(mtMachineType);
		return new DataGrid(mtMachineType.getTotal(), mtProducts);
	}
	
	/**
	 * 保存机型信息
	 */
	@RequiresPermissions("mt:machinetype:create")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public RespResult save(HttpServletRequest request, ModelMap model, MtMachineType mtMachineType) {
		mtMachineTypeService.insert(mtMachineType);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 更新机型信息
	 */
	@RequiresPermissions("mt:machinetype:update")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public RespResult update(HttpServletRequest request, ModelMap model, MtMachineType mtMachineType) {
		mtMachineTypeService.update(mtMachineType);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 删除指定机型信息
	 */
	@RequiresPermissions("mt:machinetype:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public RespResult delete(HttpServletRequest request, ModelMap model, Integer mtMachineTypeId) {
		mtMachineTypeService.delete(mtMachineTypeId);
		return RespResult.getInstance(RespCode.SUCCESS);
	}
	
	/**
	 * 上传图片
	 */
	@RequiresPermissions("mt:machinetype:upload")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public RespResult upload(HttpServletRequest request, ModelMap model, 
			@RequestParam(value = "imgFile") MultipartFile imgFile) {
		if(imgFile == null || imgFile.isEmpty()){
			throw new BizException(RespCode.CONTENT_EMPTY_ERR);
		}
		String realPath = request.getSession().getServletContext()
				.getRealPath("/resources/images/mt/upload");
		// 文件原名称
		String originFileName = imgFile.getOriginalFilename();
		String suffix = originFileName.substring(originFileName.lastIndexOf("."));
		String newFileName = UUID.randomUUID().toString() + suffix;
		try {
			new QiniuUpload().upload(imgFile.getBytes(), "mt/upload/"+newFileName);
			//这里使用Apache的FileUtils方法来进行保存
			FileUtils.copyInputStreamToFile(imgFile.getInputStream(),
					new File(realPath, newFileName));
		} catch (IOException e) {
			log.error("上传文件失败", e);
		}
		return RespResult.getInstance(RespCode.SUCCESS, newFileName);
	}
}
