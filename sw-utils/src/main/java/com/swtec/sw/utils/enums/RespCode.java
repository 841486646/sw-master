package com.swtec.sw.utils.enums;

/**
 * 响应Code枚举.
 */
public enum RespCode {
    /** 成功. */
    SUCCESS("0", "成功"),

    /** 请求参数不正确. */
    REQ_PARAM_ERROR("101", "请求参数不正确"),

    /** 请求参数验证失败. */
    REQ_PARAM_ANALYSE_ERROR("102", "请求参数验证失败"),

    /** 请求参数解码错误. */
    REQ_PARAM_DECODE_ERROR("103", "请求参数解码错误"),

    /** 非法访问. */
    REQ_NOT_ALLOWED("104", "非法访问"),

    /** 无此账号. */
    NO_ACCOUNT("105", "无此账号"),
    
    /** 无用户信息. */
    NO_USERINFO("107", "用户信息为空"),

    /** 账号已被禁用，请联系客服了解详情. */
    DISABLE_ACCOUNT("108", "账号已被禁用，请联系客服了解详情"),
    
    /** 帐号未登录. */
    USER_NOT_LOGIN("109", "帐号未登录"),
    
    /** 用户名或密码错误. */
    USERNAME_PWD_ERR("110", "用户名或密码错误"),
    
    /** 用户已存. */
    USER_EXIST("111", "用户已存在"),
    
    /** 角色已存. */
    ROLE_EXIST("112", "角色已存在"),
    
    /** 请求参数不正确. */
    REQ_ID_ERROR("114", "ID参数错误"),
    
    /** 帐号未登录过期. */
    USER_SESSION_ERR("115", "登录账号过期"),
    
    /** 库存上限不能低于库存下限. */
    NUMBER_lARGE_ERR("116", "库存上限不能低于库存下限"),
    
    /** 验证码错误. */
    VALID_CODE("117", "验证码错误"),
    
    /** 验证码错误. */
    CONTENT_EMPTY_ERR("118", "上传文件，内容为空"),

    /*
     * 系统错误
     */
    /** 数据库处理错误. */
    DB_ERROR("400", "数据库处理错误"),

    /** 未查询到数据. */
    DB_ERROR_NO_DATA("401", "未查询到数据"),
    
    /** 操作数据库影响行数错误. */
    DB_ERROR_EFFECT_LINE("402", "操作数据库影响行数错误"),

    /** 服务端错误. */
    SERVER_ERROR("500", "服务端错误"),

    /** 业务异常. */
    SERVER_BIZ_ERROR("501", "业务异常"),
	
    /** 时间转换错误. */
    DATE_CONVERSION_ERROR("502", "时间转换错误"),
    
    /** 商品单号不能为空. */
    COMMODITY_NOTNULL_ERROR("503", "请录入商品."),
    
    /** 商品单号不能为空. */
    COMMODITY_REPEAT_ERROR("504", "录入的商品重复.");

    /** 响应Code. */
    private String code;
    /** 响应消息. */
    private String msg;

    /**
     * 构造方法.
     * @param code 响应Code
     * @param msg 响应消息
     */
    RespCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 转换成字符串（Json格式）.
     */
    public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("{");
        buf.append("\"code\":").append(code).append(",");
        buf.append("\"msg\":\"").append(msg).append("\"");
        buf.append("}");
        return buf.toString();
    }

    /**
     * 获得响应Code.
     * @return 响应Code
     */
    public String getCode() {
        return code;
    }

    /**
     * 获得响应消息.
     * @return 响应消息
     */
    public String getMsg() {
        return msg;
    }
}
