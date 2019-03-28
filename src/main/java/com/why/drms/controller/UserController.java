package com.why.drms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.why.drms.entity.*;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.service.DormitoryFloorService;
import com.why.drms.service.UserService;
import com.why.drms.util.PassUtil;
import com.why.drms.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author why
 * @since 2019-03-25
 */
@RestController
@RequestMapping("/userEntity")
public class UserController {

    @Autowired
    UserService service;

    @Autowired
    DormitoryFloorService floorService;

    @GetMapping("info")
    public Result getInfo(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        return ResultUtil.success(user.getUsername());
    }

    @GetMapping("myslef")
    public Result myslef(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        UserEntity userEntity = service.getOne(wrapper);
        DormitoryFloorEntity floorEntity = floorService.getById(userEntity.getFloorId());
        Map<String,Object> map = new HashMap<>();
        map.put("user",userEntity);
        map.put("floor",floorEntity);
        return ResultUtil.success(map);
    }

    @PutMapping("putinfo")
    public Result putinfo(@RequestBody UserEntity entity){
        if (service.updateById(entity)){
            return ResultUtil.successMsg("修改成功！",entity);
        }
        return ResultUtil.error(SystemErrorEnum.UPDATE_ERROR);
    }

    @PostMapping("changepwd")
    public Result changePwd(Authentication authentication,String oldpwd,String newpwd) {
        if (StringUtils.isBlank(oldpwd) || StringUtils.isBlank(newpwd)) {
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (oldpwd.equals(newpwd)) {
            return ResultUtil.error(SystemErrorEnum.PASS_EQ);
        }
        User user = (User) authentication.getPrincipal();
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        UserEntity userEntity = service.getOne(wrapper);
        if (PassUtil.matches(oldpwd,userEntity.getPassword())) {
            userEntity.setPassword(PassUtil.encoder(newpwd));
            if (service.updateById(userEntity)) {
                return ResultUtil.successMsg("修改密码成功！");
            } else {
                return ResultUtil.error(SystemErrorEnum.UPDATE_ERROR);
            }
        }
        return ResultUtil.error(SystemErrorEnum.PASS_ERROR);
    }

    @GetMapping("users")
    public Result getUsers(LayuiPage lp,UserEntity entity){
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getName())){
            wrapper.like("name",entity.getName());
        }
        if (StringUtils.isNotBlank(entity.getUsername())){
            wrapper.like("username",entity.getUsername());
        }
        if (StringUtils.isNotBlank(entity.getRole())){
            wrapper.eq("role",entity.getRole());
        }
        if (entity.getCampusId()!=null && entity.getCampusId()>=0){
            wrapper.eq("floor_id",entity.getCampusId());
        }
        if (entity.getFloorId()!=null && entity.getFloorId()>=0){
            wrapper.eq("floor_id",entity.getFloorId());
        }
        IPage<UserEntity> page = service.page(new Page<>(lp.getPage(), lp.getPageSize()), wrapper);
        return ResultUtil.success(page.getRecords(),(int)page.getTotal());
    }

    @PutMapping("user")
    public Result putUsers(@RequestBody UserEntity entity){
        if (entity.getId()==null||entity.getId()<0){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.updateById(entity)){
            return ResultUtil.successMsg("修改成功！");
        }
        return ResultUtil.error(SystemErrorEnum.UPDATE_ERROR);
    }

    @DeleteMapping("user")
    public Result delUsers(@RequestBody List<Integer> ids){
        if (ids==null||ids.size()<1){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.removeByIds(ids)){
            return ResultUtil.successMsg("删除成功！");
        }
        return ResultUtil.error(SystemErrorEnum.DEL_ERROR);
    }

    @PostMapping("user")
    public Result addUsers(@RequestBody FormUser fu){
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(fu,entity);
        entity.setPassword(PassUtil.encoder(entity.getPassword()));
        if (service.save(entity)){
            return ResultUtil.successMsg("添加成功！");
        }
        return ResultUtil.error(SystemErrorEnum.ADD_ERROR);
    }
}
