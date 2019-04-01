package com.why.drms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.why.drms.entity.CampusEntity;
import com.why.drms.entity.LayuiPage;
import com.why.drms.entity.LayuiQuery;
import com.why.drms.entity.Result;
import com.why.drms.enums.SystemErrorEnum;
import com.why.drms.service.CampusService;
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
@RequestMapping("/campusEntity")
public class CampusController {

    @Autowired
    CampusService service;

    @GetMapping("campuses")
    public Result getCampusList(LayuiPage lp, LayuiQuery entity){
        QueryWrapper<CampusEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(entity.getName())){
            wrapper.like("campus",entity.getName());
        }
        if (entity.getStartDate()!=null){
            wrapper.ge("create_time",entity.getStartDate());
        }
        if (entity.getEndDate()!=null){
            wrapper.le("create_time",entity.getEndDate());
        }
        if (lp.getPage()==null){
            List<CampusEntity> list = service.list();
            return ResultUtil.success(list,list.size());
        }else{
            IPage<CampusEntity> page = service.page(new Page<>(lp.getPage(), lp.getPageSize()),wrapper);
            return ResultUtil.success(page.getRecords(),Math.toIntExact(page.getTotal()));
        }
    }

    @DeleteMapping("campus")
    @PreAuthorize("hasAnyAuthority('SYS')")
    public Result delCampus(@RequestBody Integer id){
        if (id==null || id<0){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        CampusEntity entity = new CampusEntity();
        entity.setId(id);
        entity.setStatus(0);
        if (service.removeById(id)){
            return ResultUtil.successMsg("删除成功！");
        }
        return ResultUtil.error(SystemErrorEnum.DEL_ERROR);
    }

    @PutMapping("campus")
    @PreAuthorize("hasAnyAuthority('SYS')")
    public Result putCampus(@RequestBody CampusEntity entity){
        if (entity.getId()==null || entity.getId()<0){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if (service.updateById(entity)){
            return ResultUtil.successMsg("修改成功！");
        }
        return ResultUtil.error(SystemErrorEnum.UPDATE_ERROR);
    }

    @PostMapping("campus")
    @PreAuthorize("hasAnyAuthority('SYS')")
    public Result addCampus(@RequestBody CampusEntity entity){
        if (StringUtils.isBlank(entity.getCampus())){
            return ResultUtil.error(SystemErrorEnum.PARAM_NULL);
        }
        if(service.save(entity)){
            return ResultUtil.successMsg("添加成功！");
        }
        return ResultUtil.error(SystemErrorEnum.ADD_ERROR);
    }
}
