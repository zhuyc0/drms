package com.why.drms.service.impl;

import com.why.drms.entity.UserEntity;
import com.why.drms.mapper.UserMapper;
import com.why.drms.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
