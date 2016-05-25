package com.swtec.sw.persist.enums;

/**
 * 显示类型
 * 菜单类型 menu：菜单，button：按钮
 * @author shaowei
 *
 */
public enum MenuType {

	menu("菜单"), button("按钮");

    private final String info;

    private MenuType(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
