package com.swtec.sw.persist.model.ext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.swtec.sw.persist.model.Resource;

/**
 * 资源拓展对象
 * @author shaowei
 *
 */
public class ResourceExt extends Resource implements Serializable {
	private static final long serialVersionUID = -3709399187752123001L;
	private List<ResourceExt> children;
	
	public ResourceExt() {
	}

	public List<ResourceExt> getChildren() {
        if (children == null) {
            children =new ArrayList<ResourceExt>();
        }
        return children;
    }

    public void setChildren(List<ResourceExt> children) {
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