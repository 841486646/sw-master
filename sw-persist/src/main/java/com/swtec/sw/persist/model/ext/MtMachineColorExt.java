package com.swtec.sw.persist.model.ext;

import com.swtec.sw.persist.model.MtMachineColor;

/**
 * 维修机器颜色对象拓展类
 * @author shaowei
 *
 */
public class MtMachineColorExt extends MtMachineColor {
	private static final long serialVersionUID = -1104714393198888964L;
	private int page;
	private int rows;
	private int total;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
}
