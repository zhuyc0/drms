package com.why.drms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.why.drms.entity.*;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.service.RepairService;
import com.why.drms.service.UserService;
import com.why.drms.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
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
@RequestMapping("/repairEntity")
public class RepairController {

    @Autowired
    RepairService service;

    @Autowired
    UserService userService;

    @PostMapping("repair")
    public Result addViolat(@RequestBody RepairEntity entity, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        UserEntity userEntity = userService.getOne(wrapper);
        entity.setRegId(userEntity.getId());
        entity.setRegName(userEntity.getName());
        if (service.save(entity)){
            return ResultUtil.successMsg("添加成功！");
        }
        return ResultUtil.error(SystemErrorEnum.ADD_ERROR);
    }

    @DeleteMapping("repair")
    public Result delViolat(@RequestBody List<Integer> ids){
        if (ids==null || ids.size()<1){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.removeByIds(ids)){
            return ResultUtil.successMsg("删除成功！");
        }
        return ResultUtil.error(SystemErrorEnum.DEL_ERROR);
    }

    @PutMapping("repair")
    public Result putViolat(@RequestBody RepairEntity entity){
        if (entity.getId()==null || entity.getId()<1){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.updateById(entity)){
            return ResultUtil.successMsg("修改成功！");
        }
        return ResultUtil.error(SystemErrorEnum.UPDATE_ERROR);
    }

    @GetMapping("repairs")
    public Result getViolats(LayuiPage lp, RepairEntity entity, LayuiQuery lq){
        QueryWrapper<RepairEntity> wrapper = new QueryWrapper<>();
        if (entity.getFloorId()!=null && entity.getFloorId()>0){
            wrapper.eq("floor_id",entity.getFloorId());
        }
        if (entity.getRoomId()!=null && entity.getRoomId()>0){
            wrapper.eq("room_id",entity.getRoomId());
        }
        if (entity.getReport()!=null){
            wrapper.eq("report",entity.getReport());
        }
        if (lq.getStartDate()!=null){
            wrapper.ge("create_time",lq.getStartDate());
        }
        if (lq.getEndDate()!=null){
            wrapper.le("create_time",lq.getEndDate());
        }
        if (entity.getType()!=null){
            wrapper.like("type",entity.getType());
        }
        if (entity.getComplete()!=null){
            wrapper.like("complete",entity.getComplete());
        }
        if (StringUtils.isNotBlank(entity.getRoomName())){
            wrapper.like("room_name",entity.getRoomName());
        }
        if (StringUtils.isNotBlank(entity.getRegName())){
            wrapper.like("reg_name",entity.getRegName());
        }
        IPage<RepairEntity> page = service.page(new Page<>(lp.getPage(), lp.getPageSize()), wrapper);
        return ResultUtil.success(page.getRecords(),(int)page.getTotal());
    }
}
