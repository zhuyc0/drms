package com.why.drms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author <a href="zhuyichen">Zhu Yichen</a>
 * @version 1.0
 * @date 2019年04月01日 13:53
 * @desc ChangePass
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangePass implements Serializable {
    private static final long serialVersionUID = 4077803705418187439L;
    private String newpwd;
    private String oldpwd;
}
