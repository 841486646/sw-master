package com.swtec.sw.persist.enums;

public enum SaleType {
	xs_ck("销售出库");

    private final String info;

    private SaleType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
