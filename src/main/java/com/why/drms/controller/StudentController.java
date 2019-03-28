package com.why.drms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.why.drms.entity.Result;
import com.why.drms.entity.StudentEntity;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.service.StudentService;
import com.why.drms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author why
 * @since 2019-03-25
 */
@RestController
@RequestMapping("/studentEntity")
public class StudentController {
    @Autowired
    StudentService service;

    @PutMapping("student")
    public Result putStu(@RequestBody StudentEntity entity){
        if (entity.getId()==null||entity.getId()<1){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.updateById(entity)){
            return ResultUtil.successMsg("修改成功!");
        }
        return ResultUtil.error(SystemErrorEnum.UPDATE_ERROR);
    }

    @GetMapping("student")
    public Result getStus(Integer roomId){
        if (roomId==null || roomId<1){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("room_id",roomId);
        List<StudentEntity> list = service.list(wrapper);
        return ResultUtil.success(list,list.size());
    }

    @DeleteMapping("student")
    public Result delStu(@RequestBody List<Integer> ids){
        if (ids.size()<1){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.removeByIds(ids)){
            return ResultUtil.successMsg("删除成功!");
        }
        return ResultUtil.error(SystemErrorEnum.DEL_ERROR);
    }
}
