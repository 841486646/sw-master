package com.swtec.sw.persist.model.ext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * combotree对象封装
 * @author shaowei
 *
 */
public class Combotree implements Serializable{
	private static final long serialVersionUID = -7446457946979131487L;
	private int id;
	private String text;
	private String value;
	private boolean checked;
	private List<Combotree> children;
	public List<Combotree> getChildren() {
        if (children == null) {
            children =new ArrayList<Combotree>();
        }
        return children;
    }

    public void setChildren(List<Combotree> children) {
        this.children = children;
    }

    /**
     * 是否有子级
     * @return
     */
    public boolean isHasChildren() {
        return !getChildren().isEmpty();
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
