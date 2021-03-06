package com.swtec.sw.persist.model.ext;

import com.swtec.sw.persist.model.Supplier;

/**
 * 供应商对象拓展类
 * @author chngkang
 *
 */
public class SupplierExt extends Supplier{
	private static final long serialVersionUID = -8521504226369375893L;
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
