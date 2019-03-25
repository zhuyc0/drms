package com.why.drms.service.impl;

import com.why.drms.entity.StudentEntity;
import com.why.drms.mapper.StudentMapper;
import com.why.drms.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author why
 * @since 2019-03-25
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

}
