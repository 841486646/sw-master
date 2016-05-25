package com.swtec.sw.persist.enums;
/**
 * 客户类型
 * @author chengkang
 *
 */
public enum CustomerType {
	retail("零售客户"), wholesale("批发客户");
	
	private final String info;

    private CustomerType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
