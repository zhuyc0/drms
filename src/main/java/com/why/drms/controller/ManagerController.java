package com.why.drms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.why.drms.entity.CampusEntity;
import com.why.drms.entity.DormitoryFloorEntity;
import com.why.drms.entity.Result;
import com.why.drms.entity.UserEntity;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.service.CampusService;
import com.why.drms.service.DormitoryFloorService;
import com.why.drms.service.UserService;
import com.why.drms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月28日 13:31
 * @desc ManagerController 管理区域
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    UserService userservice;
    @Autowired
    DormitoryFloorService floorService;
    @Autowired
    CampusService campusService;

    @GetMapping("getfloorandroominfo")
    public Result getFoolrAndRoom(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        UserEntity entity = userservice.getOne(wrapper);
        if (entity.getCampusId()==null || entity.getFloorId()==null){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        CampusEntity campusEntity = campusService.getById(entity.getCampusId());
        DormitoryFloorEntity floorEntity = floorService.getById(entity.getFloorId());
        Map<String,Object> map = new HashMap<>();
        map.put("campusId",campusEntity.getId());
        map.put("campusName",campusEntity.getCampus());
        map.put("floorId",floorEntity.getId());
        map.put("floorName",floorEntity.getFloorName());
        return ResultUtil.success(map);
    }
}
