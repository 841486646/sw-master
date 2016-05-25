package com.swtec.sw.persist.enums;

/**
 * 显示类型
 * show：显示 hidden：不显示
 * @author shaowei
 *
 */
public enum ShowType {

	hidden("隐藏"), show("显示");

    private final String info;

    private ShowType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
