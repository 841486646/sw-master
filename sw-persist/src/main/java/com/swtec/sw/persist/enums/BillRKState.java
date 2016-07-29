package com.swtec.sw.persist.enums;

public enum BillRKState {
	
	sh_in("订单未完结"), sh_success("订单完结成功"), sh_reject("订单取消");

    private final String info;

    private BillRKState(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
