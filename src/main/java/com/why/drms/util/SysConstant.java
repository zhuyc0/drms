package com.why.drms.util;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月25日 18:00
 * @desc SysConstant 常量工具类
 */
public class SysConstant {

    private SysConstant() {
    }

    /**
     * excel
     */
    public static final String XLSX_SUFFIX = ".xlsx";

    /**
     * excel
     */
    public static final String XLSX_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    /**
     * 图形验证码 session key
     */
    public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    /**
     * 手机验证码 session key
     */
    public static final String SESSION_KEY_SMS_CODE = "SESSION_KEY_SMS_CODE";

    /**
     * 返回报文头 json格式，编码 utf-8
     */
    public static final String JSON_UTF8 = "application/json;charset=utf-8";

    /**
     * 返回 html
     */
    public static final String HTML_UTF8 = "text/html;charset=utf-8";

    /**
     * 权限不足 URL
     */
    public static final String ACCESS_DENY_URL = "403";

    /**
     * 错误地址
     */
    public static final String ERROR_URL = "500";

    /**
     * 资源没找到地址
     */
    public static final String NO_RESOURCE_URL="404";

    /**
     * 加密盐
     */
    public static final String SALT = "study";


}
