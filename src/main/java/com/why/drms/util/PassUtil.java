package com.why.drms.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年03月25日 19:06
 * @desc PassUtil 密码工具类
 */
public class PassUtil {
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String encoder(String pass){
        return passwordEncoder.encode(pass);
    }
    public static Boolean matches(String p1,String p2){
        return passwordEncoder.matches(p1,p2);
    }
}
