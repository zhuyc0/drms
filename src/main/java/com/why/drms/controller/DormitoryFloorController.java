package com.why.drms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.why.drms.entity.DormitoryFloorEntity;
import com.why.drms.entity.LayuiPage;
import com.why.drms.entity.Result;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.service.DormitoryFloorService;
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
@RequestMapping("/dormitoryFloorEntity")
public class DormitoryFloorController {
    @Autowired
    DormitoryFloorService service;

    @GetMapping("floors")
    public Result getFloors(LayuiPage lp, DormitoryFloorEntity entity){
        QueryWrapper<DormitoryFloorEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getFloorName())){
            wrapper.like("floor_name",entity.getFloorName());
        }
        if (entity.getCampusId()!=null && entity.getCampusId()>0){
            wrapper.eq("campus_id",entity.getCampusId());
        }
        if (entity.getOpen()!=null && entity.getOpen()>=0){
            wrapper.eq("open",entity.getOpen());
        }
        if (entity.getSex()!=null && entity.getSex()>=0){
            wrapper.eq("sex",entity.getSex());
        }
        if (lp.getPage()==null){
            List<DormitoryFloorEntity> list = service.list();
            return ResultUtil.success(list,list.size());
        }
        IPage<DormitoryFloorEntity> page = service.page(new Page<>(lp.getPage(), lp.getPageSize()), wrapper);
        return ResultUtil.success(page.getRecords(),(int)page.getTotal());
    }

    @PostMapping("floor")
    @PreAuthorize("hasRole('SYS')")
    public Result addFloor(@RequestBody DormitoryFloorEntity entity){
        if (service.save(entity)){
            return ResultUtil.successMsg("添加成功！");
        }
        return ResultUtil.error(SystemErrorEnum.ADD_ERROR);
    }

    @PutMapping("floor")
    @PreAuthorize("hasRole('SYS')")
    public Result putFloor(@RequestBody DormitoryFloorEntity entity){
        if (entity.getId()==null||entity.getId()<0){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.updateById(entity)){
            return ResultUtil.successMsg("修改成功！");
        }
        return ResultUtil.error(SystemErrorEnum.UPDATE_ERROR);
    }

    @DeleteMapping("floor")
    @PreAuthorize("hasRole('SYS')")
    public Result delFloor(@RequestBody List<Integer> ids){
        if (ids==null||ids.size()<1){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.removeByIds(ids)){
            return ResultUtil.successMsg("删除成功！");
        }
        return ResultUtil.error(SystemErrorEnum.DEL_ERROR);
    }
}
