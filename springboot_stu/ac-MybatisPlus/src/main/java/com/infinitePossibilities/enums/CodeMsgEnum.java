package com.infinitePossibilities.enums;

/**
 * 异常类code常量（code值不要重复）
 *
 * @author 154594742@qq.com
 * @date: 2021/2/22 9:42:00
 */
public enum CodeMsgEnum {
    //请求成功
    SUCCESS("0","成功!"),
    //系统异常
    FAIL("1","失败!"),
    //以下是业务异常
    LOGIN_NO_PASS("1001","用户名或密码错误"),
    ;

    /**
     * 状态码
     */
    public String code;

    /**
     * 状态码对应信息
     */
    public String msg;

    CodeMsgEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
