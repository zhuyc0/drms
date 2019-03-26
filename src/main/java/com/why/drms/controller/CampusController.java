package com.why.drms.controller;


import com.why.drms.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/campuses")
    public Result getCampusList(){
        return null;
    }
}
