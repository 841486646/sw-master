package com.swtec.sw.persist.model.ext;

import java.math.BigDecimal;

import com.swtec.sw.persist.model.Receivables;
/**
 * 销售维修
 * @author chengkang
 *
 */
public class ReceivablesExt extends Receivables{
	private static final long serialVersionUID = -4280801435552514257L;
	private int page;
	private int rows;
	private int total;
	private boolean selected;
	private String orderNumber;
	private BigDecimal billTotalPrice;
	private String BillState;
	private Integer BillId;
	private BigDecimal otherExpenses;
	
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
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public BigDecimal getBillTotalPrice() {
		return billTotalPrice;
	}
	public void setBillTotalPrice(BigDecimal billTotalPrice) {
		this.billTotalPrice = billTotalPrice;
	}
	public String getBillState() {
		return BillState;
	}
	public void setBillState(String billState) {
		BillState = billState;
	}
	public Integer getBillId() {
		return BillId;
	}
	public void setBillId(Integer billId) {
		BillId = billId;
	}
	public BigDecimal getOtherExpenses() {
		return otherExpenses;
	}
	public void setOtherExpenses(BigDecimal otherExpenses) {
		this.otherExpenses = otherExpenses;
	}
	
}
