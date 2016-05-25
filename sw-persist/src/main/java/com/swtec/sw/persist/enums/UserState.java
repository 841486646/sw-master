package com.swtec.sw.persist.enums;

/**
 * 用户状态
 * @author shaowei
 *
 */
public enum UserState {

	block("封禁状态"), normal("正常状态");

    private final String info;

    private UserState(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
