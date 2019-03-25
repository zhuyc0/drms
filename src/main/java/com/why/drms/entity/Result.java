package com.why.drms.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月25日 17:59
 * @desc Result 统一返回类型POJO
 */
@Data
@ToString
@NoArgsConstructor
public class Result<T> {
    Integer code;
    String msg;
    T data;
    Integer count;

    public Result(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
