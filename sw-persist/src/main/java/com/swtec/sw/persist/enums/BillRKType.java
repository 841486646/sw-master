package com.swtec.sw.persist.enums;

public enum BillRKType {
	
	rk_scru("生产入库"), rk_th("借出退回"), rk_qt("其他入库");

    private final String info;

    private BillRKType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
