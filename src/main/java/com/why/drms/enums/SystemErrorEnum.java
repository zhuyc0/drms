package com.why.drms.enums;

import lombok.AllArgsConstructor;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月25日 17:54
 * @desc SystemErrorEnum 系统错误枚举
 */
@AllArgsConstructor
public enum SystemErrorEnum {
    /**
     * 服务器异常
     */
    SYSTEM_ERROR(500,"服务器异常"),
    ;
    /**
     * 错误代码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }}
