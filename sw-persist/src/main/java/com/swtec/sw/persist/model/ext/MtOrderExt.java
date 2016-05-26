package com.swtec.sw.persist.model.ext;

import com.swtec.sw.persist.model.MtOrder;

/**
 * 维修订单对象拓展类
 * @author shaowei
 */
public class MtOrderExt extends MtOrder {
	private static final long serialVersionUID = -8137647523570425483L;
	private int page;
	private int rows;
	private int total;
	private String startTime;
	private String endTime;
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
