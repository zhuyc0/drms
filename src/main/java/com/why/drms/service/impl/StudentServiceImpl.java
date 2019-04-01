package com.why.drms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.why.drms.entity.StudentEntity;
import com.why.drms.mapper.StudentMapper;
import com.why.drms.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author why
 * @since 2019-03-25
 */
@Service
@Slf4j
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {
    @Autowired
    StudentMapper mapper;

    @Override
    public String saveByExcel(List<StudentEntity> list) {
        AtomicInteger success = new AtomicInteger();
        AtomicInteger update = new AtomicInteger();
        AtomicInteger error = new AtomicInteger();
        list.forEach(entity -> {
            try {
                success.addAndGet(mapper.insert(entity));
            } catch (Exception e) {
                if (e instanceof DuplicateKeyException) {
                    QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
                    wrapper.eq("stu_no", entity.getStuNo());
                    update.addAndGet(mapper.update(entity, wrapper));
                }else {
                    log.error("[添加学生信息]{}",e);
                    error.getAndIncrement();
                }
            }
        });
        return "添加："+success.get()+"条，更新："+update.get()+"条，失败："+error.get()+"条";
    }
}
