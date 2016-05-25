package com.swtec.sw.persist.model.ext;

import com.swtec.sw.persist.model.Warehouse;
/**
 * 仓库对象拓展
 * @author chengkang
 *
 */
public class WarehouseExt extends Warehouse{
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

}
