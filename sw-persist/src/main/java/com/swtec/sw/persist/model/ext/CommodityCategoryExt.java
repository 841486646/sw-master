package com.swtec.sw.persist.model.ext;

import java.util.ArrayList;
import java.util.List;

import com.swtec.sw.persist.model.CommodityCategory;
/**
 * 商品类别对象封装类
 * @author chengkang
 *
 */
public class CommodityCategoryExt extends CommodityCategory{
	private static final long serialVersionUID = -3709399187752123001L;
	private List<CommodityCategoryExt> children;
	
	public CommodityCategoryExt() {
	}

	public List<CommodityCategoryExt> getChildren() {
        if (children == null) {
            children =new ArrayList<CommodityCategoryExt>();
        }
        return children;
    }

    public void setChildren(List<CommodityCategoryExt> children) {
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
