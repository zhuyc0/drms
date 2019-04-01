package com.why.drms.handle;

import com.why.drms.entity.Result;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.exception.DrmsException;
import com.why.drms.util.ResponseUtil;
import com.why.drms.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月25日 17:57
 * @desc ExceptionHandle 全局捕获异常
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Object exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception e){
        if(isAjax(request)){
            if(e instanceof DrmsException){
                DrmsException exception = (DrmsException) e;
                log.error("[自定义异常]{}",exception);
                return ResultUtil.error(exception.getCode(),exception.getMessage());
            }else if (e instanceof DuplicateKeyException){
                return ResultUtil.error(SystemErrorEnum.DUPLICATE_KEY);
            }else if (e instanceof AccessDeniedException){
                Result result = new Result(HttpStatus.UNAUTHORIZED.value(),"权限不足！");
                ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), result);
            }{
                log.error("[系统异常]{}",e);
                return ResultUtil.error(SystemErrorEnum.SYSTEM_ERROR);
            }
        }else{
            ModelAndView mv = new ModelAndView("error/500");
            if(e instanceof DrmsException){
                DrmsException exception = (DrmsException) e;
                mv.addObject("exception",exception.getMessage());
                log.error("[自定义异常]{}",e);
            }else{
                log.error("[系统异常]{}",e);
                mv.addObject("exception","服务器异常");
            }
            return mv;
        }
    }

    /**
     * 通过请求头判断
     *是否为ajax请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        String handle = request.getHeader("X-Requested-With");
        return ("XMLHttpRequest".equals(handle));
    }
}
