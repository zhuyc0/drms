package com.why.drms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.why.drms.entity.MenuEntity;
import com.why.drms.entity.Result;
import com.why.drms.service.MenuService;
import com.why.drms.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author why
 * @since 2019-04-01
 */
@RestController
@RequestMapping("/menuEntity")
public class MenuController {

    @Autowired
    MenuService service;

    @GetMapping("menu")
    public Result getMenuByRole(Authentication authentication){
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        AtomicReference<String> authority = new AtomicReference<>();
        authorities.forEach(item->authority.set(item.getAuthority()));
        QueryWrapper<MenuEntity> wrapper = new QueryWrapper<>();
        String role = authority.get();
        wrapper.like("role",authority.get().split("_")[1]);
        List<MenuEntity> list = service.list(wrapper);
        return ResultUtil.success(list);
    }

}
