package com.swtec.sw.persist.model.ext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.swtec.sw.persist.model.Resource;

/**
 * 菜单对象
 * @author shaowei
 *
 */
public class Menu extends Resource implements Serializable {
	private static final long serialVersionUID = 7343496900560276474L;
	
	private List<Menu> children;
	
	public Menu(Integer id, String name, String url) {
		setId(id);
		setName(name);
		setUrl(url);
	}

	public List<Menu> getChildren() {
        if (children == null) {
            children =new ArrayList<Menu>();
        }
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    /**
     * 是否有子级
     * @return
     */
    public boolean isHasChildren() {
        return !getChildren().isEmpty();
    }
}