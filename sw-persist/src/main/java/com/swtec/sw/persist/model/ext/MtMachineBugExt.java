package com.swtec.sw.persist.model.ext;

import com.swtec.sw.persist.model.MtMachineBug;

/**
 * 故障对象拓展类
 * @author shaowei
 *
 */
public class MtMachineBugExt extends MtMachineBug {
	private static final long serialVersionUID = -794379540910897657L;
	private int page;
	private int rows;
	private int total;
	private String colorName;
	private String productName;
	private String machinetypeName;
	
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getMachinetypeName() {
		return machinetypeName;
	}
	public void setMachinetypeName(String machinetypeName) {
		this.machinetypeName = machinetypeName;
	}
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
