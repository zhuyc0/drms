package com.why.drms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.why.drms.entity.DormitoryRoomEntity;
import com.why.drms.entity.LayuiPage;
import com.why.drms.entity.Result;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.service.DormitoryRoomService;
import com.why.drms.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("/dormitoryRoomEntity")
public class DormitoryRoomController {
    @Autowired
    DormitoryRoomService service;

    @GetMapping("rooms")
    public Result getRooms(LayuiPage lp, DormitoryRoomEntity entity){
        QueryWrapper<DormitoryRoomEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getRoomName())){
            wrapper.like("room_name",entity.getRoomName());
        }
        if (entity.getCampusId()!=null && entity.getCampusId()>0){
            wrapper.eq("campus_id",entity.getCampusId());
        }
        if (entity.getOpen()!=null && entity.getOpen()>=0){
            wrapper.eq("open",entity.getOpen());
        }
        if (entity.getFloorId()!=null && entity.getFloorId()>=0){
            wrapper.eq("floor_id",entity.getFloorId());
        }
        IPage<DormitoryRoomEntity> page = service.page(new Page<>(lp.getPage(), lp.getPageSize()), wrapper);
        return ResultUtil.success(page.getRecords(),(int)page.getTotal());
    }

    @PostMapping("room")
    @PreAuthorize("hasRole('SYS')")
    public Result addRoom(@RequestBody DormitoryRoomEntity entity){
        if (service.save(entity)){
            return ResultUtil.successMsg("添加成功！");
        }
        return ResultUtil.error(SystemErrorEnum.ADD_ERROR);
    }

    @PutMapping("room")
    @PreAuthorize("hasRole('SYS')")
    public Result putRoom(@RequestBody DormitoryRoomEntity entity){
        if (entity.getId()==null||entity.getId()<0){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.updateById(entity)){
            return ResultUtil.successMsg("修改成功！");
        }
        return ResultUtil.error(SystemErrorEnum.UPDATE_ERROR);
    }

    @DeleteMapping("room")
    @PreAuthorize("hasRole('SYS')")
    public Result delRoom(@RequestBody List<Integer> ids){
        if (ids==null||ids.size()<1){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.removeByIds(ids)){
            return ResultUtil.successMsg("删除成功！");
        }
        return ResultUtil.error(SystemErrorEnum.DEL_ERROR);
    }

    @GetMapping("getroombyfloorid")
    public Result getRoomByFloorId(Integer floorId){
        if (floorId==null || floorId<1){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        QueryWrapper<DormitoryRoomEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("floor_id",floorId);
        List<DormitoryRoomEntity> list = service.list(wrapper);
        return ResultUtil.success(list,list.size());
    }
}
