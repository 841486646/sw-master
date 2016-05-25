package com.swtec.sw.persist.enums;
/**
 * 客户分组
 * @author chengkang
 *
 */
public enum CustomerGrouping {
	enterprise("企业客户"), personal("个人客户"), peer("同行客户");
	
	 	private final String info;

	    private CustomerGrouping(String info) {
	        this.info = info;
	    }

	    public String getInfo() {
	        return info;
	    }
}
