package com.swtec.sw.persist.model.ext;

import com.swtec.sw.persist.model.Commodity;
/**
 * 商品对象拓展
 * @author chengkang
 *
 */
public class CommodityExt extends Commodity{
	private String categoryName;//商品类型名称
	private static final long serialVersionUID = -4280801435552514257L;
	private int page;
	private int rows;
	private int total;
	private boolean selected;
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
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
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
