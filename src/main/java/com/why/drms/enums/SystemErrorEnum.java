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
     */
    PARAM_NULL(10,"传入参数为空！"),
    /**
     */
    PASS_ERROR(11,"原密码不正确！"),
    /**
     */
    PASS_EQ(12,"请勿使用原密码！"),
    /**
     */
    UPDATE_ERROR(20,"修改失败！"),
    /**
     */
    DEL_ERROR(30,"删除失败！"),
    /**
     */
    ADD_ERROR(30,"添加失败！"),
    /**
     */
    SYSTEM_ERROR(500,"服务器异常！"),
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
