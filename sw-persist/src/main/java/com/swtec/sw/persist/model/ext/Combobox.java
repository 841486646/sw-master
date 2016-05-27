package com.swtec.sw.persist.model.ext;

import java.io.Serializable;

/**
 * Combobox对象封装
 * @author shaowei
 *
 */
public class Combobox implements Serializable{
	private static final long serialVersionUID = 5280047339963151829L;
	private String value;
	private String text;
	private String group;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
}
