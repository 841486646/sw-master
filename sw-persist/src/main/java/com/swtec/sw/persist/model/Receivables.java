package com.swtec.sw.persist.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Receivables implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sw_receivables
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = -7362292610994556901L;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.bill_id
     *
     * @mbggenerated
     */
    private Integer billId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.c_type
     *
     * @mbggenerated
     */
    private Integer cType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.r_type
     *
     * @mbggenerated
     */
    private Integer rType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.receivables_time
     *
     * @mbggenerated
     */
    private Date receivablesTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.create_user_id
     *
     * @mbggenerated
     */
    private Integer createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.is_invoice
     *
     * @mbggenerated
     */
    private Integer isInvoice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.invoice_number
     *
     * @mbggenerated
     */
    private String invoiceNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.total_price
     *
     * @mbggenerated
     */
    private BigDecimal totalPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.receivables_price
     *
     * @mbggenerated
     */
    private BigDecimal receivablesPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.r_state
     *
     * @mbggenerated
     */
    private Integer rState;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.last_user_id
     *
     * @mbggenerated
     */
    private Integer lastUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sw_receivables.last_user_time
     *
     * @mbggenerated
     */
    private Date lastUserTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.id
     *
     * @return the value of sw_receivables.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.id
     *
     * @param id the value for sw_receivables.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.bill_id
     *
     * @return the value of sw_receivables.bill_id
     *
     * @mbggenerated
     */
    public Integer getBillId() {
        return billId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.bill_id
     *
     * @param billId the value for sw_receivables.bill_id
     *
     * @mbggenerated
     */
    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.c_type
     *
     * @return the value of sw_receivables.c_type
     *
     * @mbggenerated
     */
    public Integer getcType() {
        return cType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.c_type
     *
     * @param cType the value for sw_receivables.c_type
     *
     * @mbggenerated
     */
    public void setcType(Integer cType) {
        this.cType = cType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.r_type
     *
     * @return the value of sw_receivables.r_type
     *
     * @mbggenerated
     */
    public Integer getrType() {
        return rType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.r_type
     *
     * @param rType the value for sw_receivables.r_type
     *
     * @mbggenerated
     */
    public void setrType(Integer rType) {
        this.rType = rType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.receivables_time
     *
     * @return the value of sw_receivables.receivables_time
     *
     * @mbggenerated
     */
    public Date getReceivablesTime() {
        return receivablesTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.receivables_time
     *
     * @param receivablesTime the value for sw_receivables.receivables_time
     *
     * @mbggenerated
     */
    public void setReceivablesTime(Date receivablesTime) {
        this.receivablesTime = receivablesTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.create_user_id
     *
     * @return the value of sw_receivables.create_user_id
     *
     * @mbggenerated
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.create_user_id
     *
     * @param createUserId the value for sw_receivables.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.is_invoice
     *
     * @return the value of sw_receivables.is_invoice
     *
     * @mbggenerated
     */
    public Integer getIsInvoice() {
        return isInvoice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.is_invoice
     *
     * @param isInvoice the value for sw_receivables.is_invoice
     *
     * @mbggenerated
     */
    public void setIsInvoice(Integer isInvoice) {
        this.isInvoice = isInvoice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.invoice_number
     *
     * @return the value of sw_receivables.invoice_number
     *
     * @mbggenerated
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.invoice_number
     *
     * @param invoiceNumber the value for sw_receivables.invoice_number
     *
     * @mbggenerated
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber == null ? null : invoiceNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.total_price
     *
     * @return the value of sw_receivables.total_price
     *
     * @mbggenerated
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.total_price
     *
     * @param totalPrice the value for sw_receivables.total_price
     *
     * @mbggenerated
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.receivables_price
     *
     * @return the value of sw_receivables.receivables_price
     *
     * @mbggenerated
     */
    public BigDecimal getReceivablesPrice() {
        return receivablesPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.receivables_price
     *
     * @param receivablesPrice the value for sw_receivables.receivables_price
     *
     * @mbggenerated
     */
    public void setReceivablesPrice(BigDecimal receivablesPrice) {
        this.receivablesPrice = receivablesPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.r_state
     *
     * @return the value of sw_receivables.r_state
     *
     * @mbggenerated
     */
    public Integer getrState() {
        return rState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.r_state
     *
     * @param rState the value for sw_receivables.r_state
     *
     * @mbggenerated
     */
    public void setrState(Integer rState) {
        this.rState = rState;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.last_user_id
     *
     * @return the value of sw_receivables.last_user_id
     *
     * @mbggenerated
     */
    public Integer getLastUserId() {
        return lastUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.last_user_id
     *
     * @param lastUserId the value for sw_receivables.last_user_id
     *
     * @mbggenerated
     */
    public void setLastUserId(Integer lastUserId) {
        this.lastUserId = lastUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sw_receivables.last_user_time
     *
     * @return the value of sw_receivables.last_user_time
     *
     * @mbggenerated
     */
    public Date getLastUserTime() {
        return lastUserTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sw_receivables.last_user_time
     *
     * @param lastUserTime the value for sw_receivables.last_user_time
     *
     * @mbggenerated
     */
    public void setLastUserTime(Date lastUserTime) {
        this.lastUserTime = lastUserTime;
    }
}