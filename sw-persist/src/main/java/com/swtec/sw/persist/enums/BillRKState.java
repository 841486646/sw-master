package com.swtec.sw.persist.enums;

public enum BillRKState {
	
	sh_in("审核中"), sh_success("审核成功"), sh_reject("审核驳回");

    private final String info;

    private BillRKState(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
