package com.why.drms.exception;

import com.why.drms.enums.SystemErrorEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月25日 17:50
 * @desc DrmsExcption 系统自定义异常类
 */
@Getter
@Setter
public class DrmsException extends RuntimeException{
    private static final long serialVersionUID = -8292238900312101231L;
    /**
     * 异常代码
     */
    Integer code;

    public DrmsException(Integer code,String msg){
        super(msg);
        this.code = code;
    }
    public DrmsException(SystemErrorEnum error){
        super(error.getMsg());
        this.code = error.getCode();
    }
}
