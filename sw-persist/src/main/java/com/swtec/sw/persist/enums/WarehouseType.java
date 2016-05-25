package com.swtec.sw.persist.enums;
/**
 * 
 * @author 仓库类型
 *chengkang
 */
public enum WarehouseType {
	whole("其它仓库"), huaPu("华普库");

    private final String info;

    private WarehouseType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
