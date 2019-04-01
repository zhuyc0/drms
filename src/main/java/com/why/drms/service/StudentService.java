package com.why.drms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.why.drms.entity.StudentEntity;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author why
 * @since 2019-03-25
 */
public interface StudentService extends IService<StudentEntity> {
    String saveByExcel(List<StudentEntity> list);
}
