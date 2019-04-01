package com.why.drms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.why.drms.entity.UserEntity;
import com.why.drms.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: FGS
 * @description:
 * @author: zhuyc
 * @create: 2018-12-07 17:20
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)){
            throw new UsernameNotFoundException("用户不存在！");
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        UserEntity user = service.getOne(queryWrapper);
        if (user==null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        GrantedAuthority grantedAuthority= new SimpleGrantedAuthority("ROLE_"+user.getRole());
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(grantedAuthority);
        return new User(username,user.getPassword(),list);
    }
}
