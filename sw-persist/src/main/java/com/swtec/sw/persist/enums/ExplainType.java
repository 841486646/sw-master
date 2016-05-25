package com.swtec.sw.persist.enums;

public enum ExplainType {
	DI_YU_KU_CUN_XIA_XIAN("低于库存下限");

    private final String info;

    private ExplainType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
