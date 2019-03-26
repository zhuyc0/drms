package com.why.drms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.why.drms.entity.DormitoryFloorEntity;
import com.why.drms.entity.Result;
import com.why.drms.entity.UserEntity;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.service.DormitoryFloorService;
import com.why.drms.service.UserService;
import com.why.drms.util.PassUtil;
import com.why.drms.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
}
