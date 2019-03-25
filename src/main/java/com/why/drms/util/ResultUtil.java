package com.why.drms.util;

import com.why.drms.entity.Result;
import com.why.drms.enums.SystemErrorEnum;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月25日 18:00
 * @desc ResultUtil 返回类型工具类
 */
public class ResultUtil{
    public static Result<Object> success(){
        return success(null);
    }
    public static Result<Object> success(Object data){
        Result<Object> resultVO = new Result<>();
        return success(data,null);
    }
    public static Result<Object> success(Object data,Integer count){
        Result<Object> resultVO = new Result<>();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(data);
        return resultVO;
    }
    public static Result error(Integer code ,String msg){
        return error(code,msg,null);
    }
    public static Result error(Integer code ,String msg,Object data){
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
    public static Result<Object> error(SystemErrorEnum error){
        return error(error,null);
    }
    public static Result<Object> error(SystemErrorEnum error, Object data){
        Result<Object> result = error(error);
        result.setData(data);
        return result;
    }
}
